package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import static co.edu.uco.rapizzas.business.assembler.dto.impl.SizeDTOAssembler.getSizeDTOAssembler;
import static co.edu.uco.rapizzas.business.assembler.dto.impl.CategoryDTOAssembler.getCategoryDTOAssembler;

import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.ProductDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.dto.ProductDTO;

public final class ProductDTOAssembler implements DTOAssembler<ProductDTO, ProductDomain>{
	
	private static final DTOAssembler<ProductDTO, ProductDomain> instance =
			new ProductDTOAssembler();
	
	private ProductDTOAssembler() {
		
	}
	
	public static DTOAssembler<ProductDTO, ProductDomain> getProductDTOAssembler() {
		return instance;
	}
	
	@Override
	public ProductDTO toDTO(final ProductDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new ProductDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var sizeTmp = getSizeDTOAssembler().toDTO(domainTmp.getSize());
		var categoryTmp = getCategoryDTOAssembler().toDTO(domainTmp.getCategory());
		return new ProductDTO(domainTmp.getId(), domainTmp.getProductName(), domainTmp.getPrice(), categoryTmp, sizeTmp);
	}

	@Override
	public ProductDomain toDomain(final ProductDTO dto) {
		var dtoTmp = ObjectHelper.getDefault(dto, new ProductDTO());
		var sizeDomainTmp = getSizeDTOAssembler().toDomain(dtoTmp.getSize());
		var categoryDomainTmp = getCategoryDTOAssembler().toDomain(dtoTmp.getCategory());
		return new ProductDomain(dtoTmp.getProductId(), dtoTmp.getProductName(), dtoTmp.getPrice(), categoryDomainTmp, sizeDomainTmp);
	}

	@Override
	public List<ProductDTO> toDTO(final List<ProductDomain> domainList) {
		
		if (ObjectHelper.isNull(domainList)) {
			return new ArrayList<>();
		}
		
		var productDtoList = new ArrayList<ProductDTO>();
		
		for (var productDomain : domainList) {
			
			productDtoList.add(toDTO(productDomain));
			
		}
		
		return productDtoList;
	}

}
