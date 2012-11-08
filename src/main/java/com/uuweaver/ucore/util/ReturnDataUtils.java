package com.uuweaver.ucore.util;

import com.uuweaver.ucore.rest.interfaces.JsonRESTResult;
import com.uuweaver.ucore.rest.interfaces.RESTStatusCode;

import java.util.List;
import java.util.logging.Logger;


/**
 * Created with IntelliJ IDEA.
 * User: ctcloud-user
 * Date: 12-9-26
 * Time: 下午3:10
 * To change this template use File | Settings | File Templates.
 */
public class ReturnDataUtils {

    private static final Logger logger = Logger.getLogger(ReturnDataUtils.class.getName());

    public static final String ERRORMSG = "服务出错";

    public static final String SUCCESSMSG = "操作成功";

    public static <E> ReturnInfo createErrorData(String errorMsg, E object) {
        ReturnInfo<E> returnData = new ReturnInfo<E>();
        returnData.setResultCode(RESTStatusCode.ERROR);
        returnData.setMsg(errorMsg);
        returnData.setObj(object);
        return returnData;
    }

    public static ReturnInfo createErrorData(String errorMsg) {
        ReturnInfo returnData = new ReturnInfo();
        returnData.setResultCode(RESTStatusCode.ERROR);
        returnData.setMsg(errorMsg);
        return returnData;
    }

    public static ReturnInfo createErrorData() {
        ReturnInfo returnData = new ReturnInfo();
        returnData.setResultCode(RESTStatusCode.ERROR);
        returnData.setMsg(ERRORMSG);
        return returnData;
    }

//    public static  ReturnData createErrorData(String errorMsg) {
//        ReturnData<String> returnData = new ReturnData<String>();
//        returnData.setResultCode(RESTStatusCode.ERROR);
//        returnData.setObj(errorMsg);
//        return returnData;
//    }

    public static <E>  ReturnInfo createSuccessData(String msg, E object){
        ReturnInfo<E> returnData = new ReturnInfo<E>();
        returnData.setResultCode(RESTStatusCode.OK);
        returnData.setMsg(msg);
        returnData.setObj(object);
        return returnData;
    }

    public static <E>  ReturnInfo createSuccessData(E object){
        ReturnInfo<E> returnData = new ReturnInfo<E>();
        returnData.setResultCode(RESTStatusCode.OK);
        returnData.setMsg(SUCCESSMSG);
        returnData.setObj(object);
        return returnData;
    }

    public static <E>  ReturnInfo createSuccessData(String msg, List<E> objects){
        ReturnInfo<E> returnData = new ReturnInfo<E>();
        returnData.setResultCode(RESTStatusCode.OK);
        returnData.setMsg(msg);
        returnData.setObjList(objects);
        return returnData;
    }

    public static <E>  ReturnInfo createSuccessData(List<E> objects){
        ReturnInfo<E> returnData = new ReturnInfo<E>();
        returnData.setResultCode(RESTStatusCode.OK);
        returnData.setMsg(SUCCESSMSG);
        returnData.setObjList(objects);
        return returnData;
    }

    public static  ReturnInfo createSuccessData(){
        return createSuccessData(SUCCESSMSG, null);
    }

//    public static <E>  ReturnSuccess createSuccessJSONArray(E obj){
//
//        JSONArray jsonArray = new JSONArray();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("result", "200");
//        jsonObject.put("msg", SUCCESSMSG);
//        jsonObject.put("data", list.toString());
//        jsonArray.add(jsonObject);
//        return jsonArray.toJSONString();
//    }


    public static <E> JsonRESTResult toJSONRestResult(ReturnInfo<E> msg){
        JsonRESTResult result = new JsonRESTResult();
        result.setStatusCode(msg.getResultCode());
        result.setMsg(msg.getMsg());
        result.setReturnObj(msg.getObj());
        return result;
    }

    public static <E> JsonRESTResult toJSONArrayRestResult(ReturnInfo<List<E>> msg){
        JsonRESTResult result = new JsonRESTResult();
        result.setStatusCode(msg.getResultCode());
        result.setMsg(msg.getMsg());
        result.setReturnObj(msg.getObjList());
        return result;
    }

    public static ReturnInfo createErrorData(int code, String errorMsg) {
        ReturnInfo returnData = new ReturnInfo();
        returnData.setResultCode(code);
        returnData.setMsg(errorMsg);
        return returnData;
    }
}
