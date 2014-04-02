package wula.http;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Date;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HTTP {
	private  final Logger log = LoggerFactory.getLogger(HTTP.class);
	
	public  Response execute(Request request) {
		try {
			
			return request.execute();
			
		} catch (ClientProtocolException e) {
			log.error(e.getMessage(), e);
			return null;
		} catch (IOException e) {
			log.error(new Date().getTime()+": "+e.getMessage());
			return null;
		}
	}

	
	public  Document get(String url){
		log.info("get url:"+url);
		try {
			
			return Jsoup.connect(url).get();
			
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}
	
	public  Document post(String url, String postContent) {
		try {
			
			Response response = Request.Post(url).bodyString(postContent,ContentType.create("text/plain", "UTF-8")).execute();
			return response.handleResponse(new ResponseHandler<Document>() {
				@Override
				public Document handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
					HttpEntity entity = response.getEntity();
					if (entity == null) {
						log.error("Response contains no content");
						throw new ClientProtocolException("Response contains no content");
					}

					String content = EntityUtils.toString(entity);
					log.info("response < "+content);
					
					return Jsoup.parse(content);
				}
			});
			
		} catch (UnsupportedCharsetException e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException(e);
		} catch (ClientProtocolException e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException(e);
		}	
	}
	
	public  Document post(String url, Map<String,String> postContent) {
		try 
		{
			return Jsoup.connect(url).data(postContent).post();
		} 
		catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
}
