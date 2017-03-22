package projects.commons.exception;

@SuppressWarnings("serial")
public class DaoException extends RuntimeException {

	public DaoException() {
		super();
	}

	public DaoException(String s) {
		super(s);
	}
	
	public DaoException(Throwable e){
        super(e);
    }

	public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}