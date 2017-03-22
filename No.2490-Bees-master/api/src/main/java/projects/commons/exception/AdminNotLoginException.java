package projects.commons.exception;

@SuppressWarnings("serial")
public class AdminNotLoginException extends ServiceException {

	public AdminNotLoginException() {
		super();
	}

	public AdminNotLoginException(String s) {
		super(s);
	}
	
	public AdminNotLoginException(Throwable e){
        super(e);
    }

	public AdminNotLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}