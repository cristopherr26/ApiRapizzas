package co.edu.uco.rapizzas.crosscuting.helper;

public class IntegerHelper {
	
	private static final int INTEGER_DEFAULT = 0;

	private IntegerHelper() {
		
	}

	public static int getDefault() {
		return INTEGER_DEFAULT;
	}

	public static int getDefault(final int value) {
		return ObjectHelper.getDefault(value, getDefault());
	}
	
	public static boolean isDifferentOfDefault(final Integer value) {
        return value != null && value != getDefault();
    }

}
