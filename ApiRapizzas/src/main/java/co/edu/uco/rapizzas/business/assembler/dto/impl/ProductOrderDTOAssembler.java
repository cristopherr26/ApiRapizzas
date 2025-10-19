package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.List;

import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.ProductOrderDomain;
import co.edu.uco.rapizzas.dto.ProductOrderDTO;

public final class ProductOrderDTOAssembler implements DTOAssembler<ProductOrderDTO, ProductOrderDomain>{
	
	private static final DTOAssembler<ProductOrderDTO, ProductOrderDomain> instance =
			new ProductOrderDTOAssembler();
	
	private ProductOrderDTOAssembler() {
		
	}
	
	public static DTOAssembler<ProductOrderDTO, ProductOrderDomain> getProductOrderDTOAssembler() {
		return instance;
	}
	
	@Override
	public ProductOrderDTO toDTO(final ProductOrderDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductOrderDomain toDomain(final ProductOrderDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductOrderDTO> toDTO(List<ProductOrderDomain> domainList) {
		// TODO Auto-generated method stub
		return null;
	}

}
