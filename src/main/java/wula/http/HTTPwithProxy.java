package wula.http;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HTTPwithProxy extends HTTP {

private static final Logger log = LoggerFactory.getLogger(HTTPwithProxy.class);
	
	private String proxyUser = "bill1wu";
	private String proxyPwd = "09120912";
	
	private String proxyHostName = "fwgate";
	private int proxyPort= 8080;
	
	private Executor executor;
	
	HTTPwithProxy() {
		executor = Executor.newInstance().auth(new HttpHost(proxyHostName, proxyPort), proxyUser, proxyPwd);
	}
	
	@Override
	public Response execute(Request request) {
		try {
			
			return executor.execute(request);
			
		} catch (ClientProtocolException e) {
			log.error(e.getMessage(), e);
			return null;
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
}
