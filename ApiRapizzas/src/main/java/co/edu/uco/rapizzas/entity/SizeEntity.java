package co.edu.uco.rapizzas.entity;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class SizeEntity{
	
	private UUID sizeId;
	private String sizeName;
	
	public SizeEntity() {
		setSizeId(UUIDHelper.getUUIDHelper().getDefault());
		setNameSize(TextHelper.getDefault());	
	}
	
	public SizeEntity(final UUID id) {
		setSizeId(id);
		setNameSize(TextHelper.getDefault());	
	}
	
	public SizeEntity(final UUID id, final String nameSize) {
		setSizeId(id);
		setNameSize(nameSize);
	}
	
	static SizeEntity getDefaultValue() {
		return new SizeEntity();
	}
	
	static SizeEntity getDefaultValue(final SizeEntity size) {
		return ObjectHelper.getDefault(size, getDefaultValue());
	}

	public String getNameSize() {
		return sizeName;
	}

	public void setNameSize(final String nameSize) {
		this.sizeName = TextHelper.getDefaultWithTrim(nameSize);
	}

	public UUID getSizeId() {
		return sizeId;
	}

	public void setSizeId(final UUID sizeId) {
		this.sizeId = UUIDHelper.getUUIDHelper().getDefault(sizeId);
	}
	
}
