package com.uuweaver.ucore.util;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: ctcloud-user
 * Date: 12-9-29
 * Time: 下午2:48
 * JSONArray工具类，便于输出JSONArray对象
 */
public class JSONArrayList<E> extends ArrayList<E> {

    public  String toString(){
        JSONArray jsonArray = new JSONArray();
        Iterator iterator =  this.iterator();
        while(iterator().hasNext()){
            E obj = (E)iterator.next();
            jsonArray.add(obj.toString());

        }
        return jsonArray.toString();
    }
}
