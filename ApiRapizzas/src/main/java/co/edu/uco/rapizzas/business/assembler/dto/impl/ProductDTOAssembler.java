package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.List;

import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.ProductDomain;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDomain toDomain(final ProductDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDTO> toDTO(List<ProductDomain> domainList) {
		// TODO Auto-generated method stub
		return null;
	}

}
