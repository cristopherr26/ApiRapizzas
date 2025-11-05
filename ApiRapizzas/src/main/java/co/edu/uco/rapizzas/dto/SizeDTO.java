package co.edu.uco.rapizzas.dto;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class SizeDTO {
	
	private UUID id;
	private String name;
	
	public SizeDTO() {
		setSizeId(UUIDHelper.getUUIDHelper().getDefault());
		setSizeName(TextHelper.getDefault());
	}
	
	public SizeDTO(final UUID sizeId) {
		setSizeId(sizeId);
		setSizeName(TextHelper.getDefault());
	}
	
	public SizeDTO(final UUID sizeId, final String sizeName) {
		setSizeId(sizeId);
		setSizeName(sizeName);
	}
	
	static SizeDTO getDefaultValue() {
		return new SizeDTO();
	}
	
	static SizeDTO getDefaultValue(final SizeDTO size) {
		return ObjectHelper.getDefault(size, getDefaultValue());
	}
	
	public UUID getSizeId() {
		return id;
	}
	
	public void setSizeId(final UUID sizeId) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(sizeId);
	}
	
	public String getSizeName() {
		return name;
	}
	
	public void setSizeName(final String sizeName) {
		this.name = TextHelper.getDefaultWithTrim(sizeName);
	}
	
}
