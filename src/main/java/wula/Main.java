package wula;


public class Main {

	public static void main(String[] args) {
		new HttpServer(8080).init("webApp").startUp();
	}
	
}
