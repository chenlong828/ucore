package com.uuweaver.ucore.rest.interfaces;

import com.uuweaver.ucore.rest.dispatch.ObjectRESTResult;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Description: 实现REST请求的时候的返回对象，该对象将被序列化成json字符串。
 * User: chenlong828
 * Date: 10/7/12
 * Time: 8:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonRESTResult extends ObjectRESTResult {
    @Override
    protected String convertObjToHttpResponse() {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        String json = "{}";
        JsonGenerator gen = null;
        try {
            ResultWrapper wrapper = new ResultWrapper();
            wrapper.setReturnObj(getReturnObj());
            wrapper.setStatusCode(getStatusCode());

            gen = new JsonFactory().createJsonGenerator(writer);
            mapper.writeValue(gen, wrapper);
            json = writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (null != gen) {
                    gen.close();
                }
            } catch (IOException e) {
                gen = null;
                e.printStackTrace();
            }

            try {
                if (null != gen) {
                    writer.close();
                }
            } catch (IOException e) {
                writer = null;
                e.printStackTrace();
            }
        }
        return json;
    }
}
