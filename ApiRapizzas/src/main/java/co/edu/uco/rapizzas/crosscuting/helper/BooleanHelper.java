package co.edu.uco.rapizzas.crosscuting.helper;

public class BooleanHelper {
	
	private static final boolean BOOLEAN_DEFAULT = false;
	
	private BooleanHelper () {
		
	}
	
	public static boolean getDefault() {
		return BOOLEAN_DEFAULT;
	}
	
	public static boolean getDeafult(final boolean value) {
		return ObjectHelper.getDefault(value, getDefault());
	}

}
