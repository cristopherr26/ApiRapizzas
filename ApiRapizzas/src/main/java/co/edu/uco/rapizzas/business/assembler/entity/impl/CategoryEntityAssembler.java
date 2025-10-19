package co.edu.uco.rapizzas.business.assembler.entity.impl;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.CategoryDomain;
import co.edu.uco.rapizzas.entity.CategoryEntity;

public final class CategoryEntityAssembler implements EntityAssembler<CategoryEntity, CategoryDomain>{
	
	private static final EntityAssembler<CategoryEntity, CategoryDomain> instance = new CategoryEntityAssembler();
	
	private CategoryEntityAssembler() {
		
	}
	
	public static EntityAssembler<CategoryEntity, CategoryDomain> getCategoryEntityAssembler() {
		return instance;
	}
	
	@Override
	public CategoryEntity toDomain(final CategoryDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDomain toEntity(final CategoryEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
