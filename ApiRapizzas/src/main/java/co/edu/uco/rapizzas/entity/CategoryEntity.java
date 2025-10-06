package co.edu.uco.rapizzas.entity;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class CategoryEntity {
	
	private UUID categoryId;
	private String categoryName;
	
	public CategoryEntity() {
		setcategoryId(UUIDHelper.getUUIDHelper().getDefault());
		setNameCategory(TextHelper.getDefault());	
	}
	
	public CategoryEntity(final UUID id) {
		setcategoryId(id);
		setNameCategory(TextHelper.getDefault());
	}
	
	public CategoryEntity(final UUID id, final String nameCategory) {
		setcategoryId(id);
		setNameCategory(nameCategory);
	}
	
	static CategoryEntity getDefaultValue() {
		return new CategoryEntity();
	}
	
	static CategoryEntity getDefaultValue(final CategoryEntity category) {
		return ObjectHelper.getDefault(category, getDefaultValue());
	}
	
	

	public UUID getIdCategory() {
		return categoryId;
	}

	public void setcategoryId(final UUID categoryId) {
		this.categoryId = UUIDHelper.getUUIDHelper().getDefault(categoryId);
	}

	public String getNameCategory() {
		return categoryName;
	}

	public void setNameCategory(final String nameCategory) {
		this.categoryName = TextHelper.getDefaultWithTrim(nameCategory);
	}

}
