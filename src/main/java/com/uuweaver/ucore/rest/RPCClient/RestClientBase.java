package com.uuweaver.ucore.rest.RPCClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * User: ChenLong
 * Created Date: 4/7/13 10:08 下午
 * Description:
 */
public abstract class RestClientBase {

	private static final Logger logger = LoggerFactory.getLogger(RestClientBase.class);

	protected String baseHost = null;

	public String getBaseHost() {
		return baseHost;
	}

	public void setBaseHost(String baseHost) {
		this.baseHost = baseHost;
	}

	protected void setURI(HttpRequestBase requestBase, String requestURI) throws URISyntaxException {
		String uri;
		if (this.getBaseHost() != null) {
			uri = this.getBaseHost() + requestURI;
		} else {
			uri = requestURI;
		}
		requestBase.setURI(new URI(uri));
	}

	protected void initHttpHeader(HttpRequestBase requestBase, Map<String, String> headerParam) {
		if (headerParam != null) {
			for (String key : headerParam.keySet()) {
				requestBase.addHeader(key, headerParam.get(key));
			}
		}
	}

	protected String getResponse(HttpRequestBase requestBase) {
		try {
			DefaultHttpClient client = new DefaultHttpClient();

			HttpResponse response = client.execute(requestBase);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity, "UTF-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Http request failed on {}", requestBase.getURI().toString());
		}
		return null;
	}
}
