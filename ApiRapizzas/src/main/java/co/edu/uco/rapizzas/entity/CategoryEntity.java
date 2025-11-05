package co.edu.uco.rapizzas.entity;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class CategoryEntity {
	
	private UUID id;
	private String name;
	
	public CategoryEntity() {
		setCategoryId(UUIDHelper.getUUIDHelper().getDefault());
		setNameCategory(TextHelper.getDefault());	
	}
	
	public CategoryEntity(final UUID id) {
		setCategoryId(id);
		setNameCategory(TextHelper.getDefault());
	}
	
	public CategoryEntity(final UUID id, final String nameCategory) {
		setCategoryId(id);
		setNameCategory(nameCategory);
	}
	
	static CategoryEntity getDefaultValue() {
		return new CategoryEntity();
	}
	
	static CategoryEntity getDefaultValue(final CategoryEntity category) {
		return ObjectHelper.getDefault(category, getDefaultValue());
	}
	
	public UUID getIdCategory() {
		return id;
	}

	public void setCategoryId(final UUID categoryId) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(categoryId);
	}

	public String getNameCategory() {
		return name;
	}

	public void setNameCategory(final String nameCategory) {
		this.name = TextHelper.getDefaultWithTrim(nameCategory);
	}

}
