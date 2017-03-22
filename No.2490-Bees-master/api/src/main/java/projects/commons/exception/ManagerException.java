package projects.commons.exception;

@SuppressWarnings("serial")
public class ManagerException extends ServiceException {

	public ManagerException() {
		super();
	}

	public ManagerException(String s) {
		super(s);
	}
	
	public ManagerException(Throwable e){
        super(e);
    }

	public ManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}