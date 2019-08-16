package system.domain;

public class TimeConstraintException extends RuntimeException {

	public TimeConstraintException(String message) {
		super(message);
	}
	
	public TimeConstraintException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
