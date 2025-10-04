package co.edu.uco.rapizzas.business.domain;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class CategoryDomain extends Domain {
	
	private String nameCategory;
	
	public CategoryDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNameCategory(TextHelper.getDefault());	
	}
	
	public CategoryDomain(final UUID id) {
		super(id);
		setNameCategory(TextHelper.getDefault());
	}
	
	public CategoryDomain(final UUID id, final String nameCategory) {
		super(id);
		setNameCategory(nameCategory);
	}
	
	static CategoryDomain getDefaultValue() {
		return new CategoryDomain();
	}
	
	static CategoryDomain getDefaultValue(final CategoryDomain category) {
		return ObjectHelper.getDefault(category, getDefaultValue());
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(final String nameCategory) {
		this.nameCategory = TextHelper.getDefaultWithTrim(nameCategory);
	}

}
