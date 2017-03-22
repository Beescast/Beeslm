package projects.commons.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 2221019601835886746L;
	
	public ServiceException() {
		super();
	}

	public ServiceException(String s) {
		super(s);
	}
	
	public ServiceException(Throwable e){
        super(e);
    }
	
	public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}