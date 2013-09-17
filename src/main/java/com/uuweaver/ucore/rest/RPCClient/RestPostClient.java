package com.uuweaver.ucore.rest.RPCClient;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: ChenLong
 * Created Date: 4/7/13 10:08 下午
 * Description: 实现HTTPClient的调用封装，更便于调用本base模式下的POST接口
 */
public class RestPostClient extends RestClientBase {

	private static final Logger logger = LoggerFactory.getLogger(RestPostClient.class);

	public String callRestRPC(String requestURI, Map<String, String> headerParam, Map<String, String> postParam) {
		try {
			HttpPost httpPost = new HttpPost();

			setURI(httpPost, requestURI);

			initHttpHeader(httpPost, headerParam);

			if (postParam != null) {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				for (String key : postParam.keySet()) {
					params.add(new BasicNameValuePair(key, postParam.get(key)));
				}
				HttpEntity entity = new UrlEncodedFormEntity(params, "utf-8");
				httpPost.setEntity(entity);
			}

			return getResponse(httpPost);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			logger.error("Error request uri: {}", requestURI);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("Error request param on : {}", requestURI);
		}
		return null;
	}

}
