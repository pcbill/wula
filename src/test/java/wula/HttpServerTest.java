package wula;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import wula.http.HttpServer;

public class HttpServerTest {

	private HttpServer server;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() {
		server = new HttpServer(8080, "dummy");
	}
	
	@Test
	public void initAndStartUp() {
		server.startUp();
	}
	
}
