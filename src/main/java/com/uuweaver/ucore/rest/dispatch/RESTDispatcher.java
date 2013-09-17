package com.uuweaver.ucore.rest.dispatch;

import com.uuweaver.ucore.rest.UTF8Request;
import com.uuweaver.ucore.rest.handlers.DebugRESTCmdlet;
import com.uuweaver.ucore.util.SpringContextUtils;
import org.eclipse.jetty.http.HttpMethods;
import org.eclipse.jetty.http.PathMap;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Description: RESTDispatcher分发类，实现基于RESTAnnocation修饰的RESTCmdlet的自动分发
 * User: chenlong828
 * Date: 10/5/12
 * Time: 9:20 PM
 */
public class RESTDispatcher extends AbstractHandler {

	private static transient Logger logger = LoggerFactory.getLogger(RESTDispatcher.class);

	private PathMap postCmdletMapper;
	private PathMap getCmdletMapper;

	public RESTDispatcher() {
		postCmdletMapper = new PathMap(100);
		getCmdletMapper = new PathMap(100);

		getCmdletMapper.put("/*", new DebugRESTCmdlet());
		postCmdletMapper.put("/*", new DebugRESTCmdlet());
	}

	public void init() throws IOException {
		scanHandler();
	}

	private void scanHandler() throws IOException {
		Reflections reflections = SpringContextUtils.getBean("reflections", Reflections.class);
		Set<Class<?>> annotateClazzs = reflections.getTypesAnnotatedWith(RESTAnnotate.class);
		for (Class clazz : annotateClazzs) {
			Annotation[] annotations = clazz.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof RESTAnnotate) {
					try {
						registerCmdlet(clazz, (RESTAnnotate) annotation);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void registerCmdlet(Class clazz, RESTAnnotate annotation) throws ClassNotFoundException {
		String methods = annotation.Methods();
		String URI = annotation.URL();

		AbstractRESTCmdlet cmdlet = (AbstractRESTCmdlet) SpringContextUtils.getBeanByClass(clazz);

		logger.debug(String.format("Attaching %s %s on handler: %s", methods, URI, clazz.getName()));

		if (methods.equals(HttpMethods.GET)) {
			getCmdletMapper.put(URI, cmdlet);
		} else if (methods.equals(HttpMethods.POST)) {
			postCmdletMapper.put(URI, cmdlet);
		}
	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		baseRequest.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String request_method = baseRequest.getMethod();
		AbstractRESTCmdlet cmdlet;

		UTF8Request req = new UTF8Request(baseRequest);

		if (request_method.equals(HttpMethods.GET)) {
			cmdlet = (AbstractRESTCmdlet) getCmdletMapper.getMatch(baseRequest.getUri().getPath()).getValue();
		} else if (request_method.equals(HttpMethods.POST)) {
			cmdlet = (AbstractRESTCmdlet) postCmdletMapper.getMatch(baseRequest.getUri().getPath()).getValue();
		} else {
			return;
		}

		logger.info("---> URI: {}, Method: {}", new Object[] { req.getRequestURI(), req.getMethod() });
		AbstractRESTResult result = cmdlet.execute(req);
		logger.info("---> Response: {}", new Object[] { result.toHttpResponse() });

		result.setHandled(true);
		response.getWriter().write(result.toHttpResponse());

		baseRequest.setHandled(result.isHandled());
	}
}
