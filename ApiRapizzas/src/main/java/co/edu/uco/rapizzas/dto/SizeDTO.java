package co.edu.uco.rapizzas.dto;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class SizeDTO {
	
	private UUID sizeId;
	private String sizeName;
	
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
		return sizeId;
	}
	
	public void setSizeId(final UUID sizeId) {
		this.sizeId = UUIDHelper.getUUIDHelper().getDefault(sizeId);
	}
	
	public String getSizeName() {
		return sizeName;
	}
	
	public void setSizeName(final String sizeName) {
		this.sizeName = TextHelper.getDefaultWithTrim(sizeName);
	}
	
}
