package wula.http;

public class HttpPostFailException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public HttpPostFailException(String courceMsg) {
		super("post fail: "+courceMsg);
	}
}
