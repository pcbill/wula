package wula.http;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class HttpServer {

	private Tomcat tomcat;
	
	
	// doc path = web/webapps/context
	private final String tomcatBaseDir = "web";
	private final String webappsDir = "webapps";
	private final String contextPathDir = "context";

	public HttpServer(int port, String webapp) {
			tomcat = new Tomcat();
			tomcat.setPort(port);

			configDirArchitecture();
			setWebAppName(webapp);
	}
	

	
	public void startUp() {
		
		try {
			
			tomcat.start();
			new Thread(new Runnable() {
				@Override
				public void run() {
					tomcat.getServer().await();
				}
			}).start();

		} catch (LifecycleException e) {
			e.printStackTrace();
		}
	}
	
	private void configDirArchitecture() {
		try {

			String	currentDir = new File(".").getCanonicalPath();
			
			String baseDir = currentDir + File.separatorChar + tomcatBaseDir;
			tomcat.setBaseDir(baseDir);
		
			String appsDir = baseDir + File.separatorChar + webappsDir;
			tomcat.getHost().setAppBase(appsDir);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private HttpServer setWebAppName(String contextPath) {
		try {

			tomcat.addWebapp("/"+contextPath, contextPathDir );
			return this;

		} catch (ServletException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
