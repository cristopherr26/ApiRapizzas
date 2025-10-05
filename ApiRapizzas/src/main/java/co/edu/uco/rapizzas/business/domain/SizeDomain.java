package co.edu.uco.rapizzas.business.domain;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class SizeDomain extends Domain{
	
	private String sizeName;
	
	public SizeDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNameSize(TextHelper.getDefault());	
	}
	
	public SizeDomain(final UUID id) {
		super(id);
		setNameSize(TextHelper.getDefault());	
	}
	
	public SizeDomain(final UUID id, final String nameSize) {
		super(id);
		setNameSize(nameSize);
	}
	
	static SizeDomain getDefaultValue() {
		return new SizeDomain();
	}
	
	static SizeDomain getDefaultValue(final SizeDomain size) {
		return ObjectHelper.getDefault(size, getDefaultValue());
	}

	public String getNameSize() {
		return sizeName;
	}

	public void setNameSize(final String nameSize) {
		this.sizeName = TextHelper.getDefaultWithTrim(nameSize);
	}
	
}
