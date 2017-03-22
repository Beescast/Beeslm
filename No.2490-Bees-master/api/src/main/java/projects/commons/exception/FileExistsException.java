/**
 * Filename：FileExistsException.java
 * Created by: qgl
 * Created on: 2011-3-22 上午12:34:00
 * Last modified by：$Author$
 * Last modified on: $Date$
 * Revision: $Revision$
 */
package projects.commons.exception;

@SuppressWarnings("serial")
public class FileExistsException extends ServiceException {

	public FileExistsException() {
		super();
	}

	public FileExistsException(String s) {
		super(s);
	}
	
	public FileExistsException(Throwable e){
        super(e);
    }

	public FileExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}