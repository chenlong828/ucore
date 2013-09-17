package com.uuweaver.ucore.rest.RPCClient;

import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: ChenLong
 * Created Date: 4/7/13 9:40 下午
 * Description: 实现HTTPClient的调用封装，更便于调用本base模式下的GET接口
 */
public class RestGetClient extends RestClientBase {

	private static final Logger logger = LoggerFactory.getLogger(RestGetClient.class);

	public String callRestRPC(String requestURI, Map<String, String> headerParam) {
		try {
			HttpGet httpGet = new HttpGet();
			setURI(httpGet, requestURI);

			initHttpHeader(httpGet, headerParam);

			return getResponse(httpGet);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			logger.error("Error request uri: {}", requestURI);
		}
		return null;
	}
}
