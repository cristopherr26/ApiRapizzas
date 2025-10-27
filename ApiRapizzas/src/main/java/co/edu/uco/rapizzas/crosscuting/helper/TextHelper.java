package co.edu.uco.rapizzas.crosscuting.helper;

public final class TextHelper {
	
	private static final String EMPTY = "";

	private TextHelper() {
		
	}

	public static String getDefault() {
		return EMPTY;
	}

	public static String getDefault(final String value) {
		return ObjectHelper.getDefault(value, getDefault());
	}

	public static String getDefaultWithTrim(final String value) {
		return getDefault(value).trim();
	}
	
	public static boolean isEmpty(final String value) {
		return EMPTY.equals(getDefault(value));
	}
	
	public static boolean isEmptyWithTrim(final String value) {
		return EMPTY.equals(getDefaultWithTrim(value));
	}
	
	public static boolean isValidPhoneNumber(final String cellPhone) {
        return  cellPhone.matches("^\\+?[0-9]{10,15}$");
    }
	
	public static boolean isValidIdentificationNumber(final String identificationNumber) {
	    String pattern = "^[A-Za-z0-9]{8,12}$";

	    return identificationNumber.matches(pattern);
	}
	
	public static boolean isValidName(final String name) {
        String pattern = "^[A-Za-zÁÉÍÓÚáéíóúÑñ]{3,25}$";
        return name.matches(pattern);
    }

    public static boolean isValidLastName(final String lastName) {
        String pattern = "^[A-Za-zÁÉÍÓÚáéíóúÑñ]{2,25}$";
        return lastName.matches(pattern);
    }
    
    public static boolean isValidPassword(final String password) {
        String pattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%&*?\\-_])[A-Za-z\\d!@#$%&*?\\-_]{8,15}$";
        return password.matches(pattern);
    }

}
