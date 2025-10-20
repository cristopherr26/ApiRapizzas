package co.edu.uco.rapizzas.crosscuting.exception;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;

public final class RapizzasException extends RuntimeException {

	private static final long serialVersionUID = 5751544605108203354L;
	
	private Throwable rootException;
	private String userMessage;
	private String technicalMessage;
	
	
	private RapizzasException(final Throwable rootException, final String userMessage, final String technicalMessage) {
		setRootException(rootException);
		setUserMessage(userMessage);
		setTechnicalMessage(technicalMessage);
	}
	
	public static RapizzasException create(final String userMessage) {
		return new RapizzasException(new Exception(), userMessage, userMessage);
	}
	
	public static RapizzasException create(final String userMessage, final String technicalMessage) {
		return new RapizzasException(new Exception(), userMessage, technicalMessage);
	}
	
	public static RapizzasException create(final Throwable rootException, final String userMessage, final String technicalMessage) {
		return new RapizzasException(rootException, userMessage, technicalMessage);
	}

	public Throwable getRootException() {
		return rootException;
	}
	
	private void setRootException(final Throwable rootException) {
		this.rootException = ObjectHelper.getDefault(rootException, new Exception());
	}
	
	public String getUserMessage() {
		return userMessage;
	}
	
	private void setUserMessage(final String userMessage) {
		this.userMessage = TextHelper.getDefaultWithTrim(userMessage);
	}
	
	public String getTechnicalMessage() {
		return technicalMessage;
	}
	
	private void setTechnicalMessage(final String technicalMessage) {
		this.technicalMessage = TextHelper.getDefaultWithTrim(technicalMessage);
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
