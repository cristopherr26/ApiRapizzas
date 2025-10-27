package co.edu.uco.rapizzas.business.domain;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class CategoryDomain extends Domain {
	
	private String categoryName;
	
	CategoryDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setCategoryName(TextHelper.getDefault());	
	}
	
	public CategoryDomain(final UUID id) {
		super(id);
		setCategoryName(TextHelper.getDefault());
	}
	
	public CategoryDomain(final UUID id, final String nameCategory) {
		super(id);
		setCategoryName(nameCategory);
	}
	
	protected static CategoryDomain getDefaultValue() {
		return new CategoryDomain();
	}
	
	static CategoryDomain getDefaultValue(final CategoryDomain category) {
		return ObjectHelper.getDefault(category, getDefaultValue());
	}

	public String getNameCategory() {
		return categoryName;
	}

	public void setCategoryName(final String nameCategory) {
		this.categoryName = TextHelper.getDefaultWithTrim(nameCategory);
	}

}
