package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.CategoryDomain;
import co.edu.uco.rapizzas.dto.CategoryDTO;

public final class CategoryDTOAssembler implements DTOAssembler<CategoryDTO, CategoryDomain>{
	
	private static final DTOAssembler<CategoryDTO, CategoryDomain> instance =
			new CategoryDTOAssembler();
	
	private CategoryDTOAssembler() {
		
	}
	
	public static DTOAssembler<CategoryDTO, CategoryDomain> getCategoryDTOAssembler() {
		return instance;
	}
	
	@Override
	public CategoryDTO toDTO(final CategoryDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new CategoryDomain(UUIDHelper.getUUIDHelper().getDefault()));
		return new CategoryDTO(domainTmp.getId(), domainTmp.getNameCategory());
	}

	@Override
	public CategoryDomain toDomain(final CategoryDTO dto) {
		var dtoTmp = ObjectHelper.getDefault(dto, new CategoryDTO());
		return new CategoryDomain(dtoTmp.getCategoryId(), dtoTmp.getCategoryName());
	}

	@Override
	public List<CategoryDTO> toDTO(final List<CategoryDomain> domainList) {
		
		if (ObjectHelper.isNull(domainList)) {
			return new ArrayList<>();
		}
		
		var categoryDtoList = new ArrayList<CategoryDTO>();
		
		for (var categoryDomain : domainList) {
			
			categoryDtoList.add(toDTO(categoryDomain));
			
		}
		
		return categoryDtoList;
	}

}
