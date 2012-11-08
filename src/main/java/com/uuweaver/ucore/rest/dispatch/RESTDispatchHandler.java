package com.uuweaver.ucore.rest.dispatch;

import com.uuweaver.ucore.util.PkgScanner;
import com.uuweaver.ucore.util.SpringContextUtil;
import org.eclipse.jetty.http.HttpMethods;
import org.eclipse.jetty.http.PathMap;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Description: 新版的RESTDispatcher分发类，实现基于RESTAnnocation修饰的RESTCmdlet的自动分发
 * User: chenlong828
 * Date: 10/5/12
 * Time: 9:20 PM
 */
public class RESTDispatchHandler extends AbstractHandler {

    private static transient Logger logger = LoggerFactory.getLogger(RESTDispatchHandler.class);

    private PathMap postCmdletMapper;
    private PathMap getCmdletMapper;

    private List<String> packageList;

    public RESTDispatchHandler() {
        postCmdletMapper = new PathMap(100);
        getCmdletMapper = new PathMap(100);
    }

    public void setPackageList(List<String> packageList) {
        this.packageList = packageList;
    }


    public void Init() throws IOException {
        for (String package_name : packageList) {
            ScanHandlersInPackage(package_name);
        }
    }

    private void ScanHandlersInPackage(String package_name) throws IOException {
        List<String> class_list;
        class_list = PkgScanner.getClassInPackage(package_name);

        for (String class_name : class_list) {
            Annotation[] annotations;
            try {
                annotations = Class.forName(class_name).getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof RESTAnnotate) {
                        RegisterCmdlet(class_name, (RESTAnnotate) annotation);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    private void RegisterCmdlet(String class_name, RESTAnnotate annotation) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String methods = annotation.Methods();
        String URI = annotation.URL();
//        AbstractRESTCmdlet cmdlet = (AbstractRESTCmdlet)Class.forName(class_name).newInstance();

        AbstractRESTCmdlet cmdlet = (AbstractRESTCmdlet) SpringContextUtil.getBeanByClassName(class_name);

        logger.info(String.format("Attaching %s %s on handler: %s",
                methods, URI, class_name));

        if (methods.equals(HttpMethods.GET)) {
            getCmdletMapper.put(URI, cmdlet);
        } else if (methods.equals(HttpMethods.POST)) {
            postCmdletMapper.put(URI, cmdlet);
        }
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String request_method = baseRequest.getMethod();
        AbstractRESTCmdlet cmdlet;

        if (request_method.equals(HttpMethods.GET)) {
            cmdlet = (AbstractRESTCmdlet) getCmdletMapper.getMatch(baseRequest.getUri().getPath()).getValue();
        } else if (request_method.equals(HttpMethods.POST)) {
            cmdlet = (AbstractRESTCmdlet) postCmdletMapper.getMatch(baseRequest.getUri().getPath()).getValue();
        } else {
            return;
        }

        AbstractRESTResult result = cmdlet.Execute(baseRequest);

        response.getWriter().write(result.toHttpResponse());

        baseRequest.setHandled(result.isHandled());
    }
}
