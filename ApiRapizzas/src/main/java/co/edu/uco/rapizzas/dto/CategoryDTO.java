package co.edu.uco.rapizzas.dto;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public class CategoryDTO {
	
	private UUID categoryId;
	private String categoryName;
	
	public CategoryDTO() {
		setCategoryId(UUIDHelper.getUUIDHelper().getDefault());
		setCategoryName(TextHelper.getDefault());
	}
	
	public CategoryDTO(final UUID categoryId) {
		setCategoryId(categoryId);
		setCategoryName(TextHelper.getDefault());
	}
	
	public CategoryDTO(final UUID categoryId, final String categoryName) {
		setCategoryId(categoryId);
		setCategoryName(categoryName);
	}
	
	static CategoryDTO getDefaultValue() {
		return new CategoryDTO();
	}
	
	static CategoryDTO getDefaultValue(final CategoryDTO category) {
		return ObjectHelper.getDefault(category, getDefaultValue());
	}

	public UUID getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(final UUID categoryId) {
		this.categoryId = ObjectHelper.getDefault(categoryId, UUIDHelper.getUUIDHelper().getDefault());
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(final String categoryName) {
		this.categoryName = TextHelper.getDefaultWithTrim(categoryName);
	}
	
}
