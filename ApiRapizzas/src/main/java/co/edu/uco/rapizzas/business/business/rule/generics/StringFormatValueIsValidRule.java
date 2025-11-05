package co.edu.uco.rapizzas.business.business.rule.generics;

import co.edu.uco.rapizzas.business.business.rule.Rule;
import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;

public final class StringFormatValueIsValidRule implements Rule {
	
	private static final Rule instance = new StringFormatValueIsValidRule();
	
	private StringFormatValueIsValidRule() {
		
	}
	
	public static void executeRule(final Object... data) {
		instance.execute(data);
	}

	@Override
	public void execute( final Object... data) {
		
		if(ObjectHelper.isNull(data)) {
			var userMessage = "";
			var technicalMessage ="";
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		if(data.length < 4) {
			var userMessage = "";
			var technicalMessage = "";
			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
		
		var stringData = (String) data[0];
		var dataName = (String) data[1];
		var pattern = (String) data[2];
		boolean mustApplyTrim = (Boolean) data[3];
		
		if(!TextHelper.formatIsValid(stringData, pattern, mustApplyTrim)) {
			var userMessage= "No cumple con los requisitos" + dataName;
			var technicalMessage= "No cumple con los siguientes requeisitos:" + dataName + " " + pattern;

			throw RapizzasException.create(userMessage, technicalMessage);
		}
		
	}

}
