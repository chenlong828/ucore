package com.uuweaver.ucore.util;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: neil
 * Date: 12-9-28
 * Time: 上午10:26
 * To change this template use File | Settings | File Templates.
 */
public class ReturnInfo<E> implements Serializable {

    /**
     * 业务执行结果码
     */
    private int resultCode;

    /**
     * 业务执行结果消息
     */
    private String msg;

    /**
     * 业务查询需要返回的对象
     */
    private E obj;

    private List<E> objList = null;

    public String toJSONString() {

        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        String json = "{}";
        JsonGenerator gen = null;
        try {
            gen = new JsonFactory().createJsonGenerator(writer);
            mapper.writeValue(gen, this);
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

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resutlCode) {
        this.resultCode = resutlCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public E getObj() {
        return obj;
    }

    public void setObj(E obj) {
        this.obj = obj;
    }

    public List<E> getObjList() {
        return objList;
    }

    public void setObjList(List<E> objList) {
        this.objList = objList;
    }

    public int size() {
        int size = 0;
        if (null != objList) {
            size = objList.size();
        }

        return size;
    }
}
