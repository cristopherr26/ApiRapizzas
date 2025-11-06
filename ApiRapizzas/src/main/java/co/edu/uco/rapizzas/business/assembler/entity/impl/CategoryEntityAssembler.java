package co.edu.uco.rapizzas.business.assembler.entity.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.CategoryDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.entity.CategoryEntity;

public final class CategoryEntityAssembler implements EntityAssembler<CategoryEntity, CategoryDomain>{
	
	private static final EntityAssembler<CategoryEntity, CategoryDomain> instance = new CategoryEntityAssembler();
	
	private CategoryEntityAssembler() {
		
	}
	
	public static EntityAssembler<CategoryEntity, CategoryDomain> getCategoryEntityAssembler() {
		return instance;
	}

	@Override
	public CategoryEntity toEntity(final CategoryDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new CategoryDomain(UUIDHelper.getUUIDHelper().getDefault()));
		return new CategoryEntity(domainTmp.getId(), domainTmp.getNameCategory());
	}

	@Override
	public CategoryDomain toDomain(final CategoryEntity entity) {
		var entityTmp = ObjectHelper.getDefault(entity, new CategoryEntity());
		return new CategoryDomain(entityTmp.getIdCategory(), entityTmp.getNameCategory());
	}

	@Override
	public List<CategoryDomain> toDomain(List<CategoryEntity> entityList) {
		var categoryDomainList = new ArrayList<CategoryDomain>();
		for (var categoryEntity : entityList) {
			categoryDomainList.add(toDomain(categoryEntity));
		}
		return categoryDomainList;
	}
	
}
