package co.edu.uco.rapizzas.business.domain;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class SizeDomain extends Domain{
	
	private String name;
	
	SizeDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setSizeName(TextHelper.getDefault());	
	}
	
	public SizeDomain(final UUID id) {
		super(id);
		setSizeName(TextHelper.getDefault());	
	}
	
	public SizeDomain(final UUID id, final String nameSize) {
		super(id);
		setSizeName(nameSize);
	}
	
	protected static SizeDomain getDefaultValue() {
		return new SizeDomain();
	}
	
	static SizeDomain getDefaultValue(final SizeDomain size) {
		return ObjectHelper.getDefault(size, getDefaultValue());
	}

	public String getNameSize() {
		return name;
	}

	public void setSizeName(final String nameSize) {
		this.name = TextHelper.getDefaultWithTrim(nameSize);
	}
	
}
