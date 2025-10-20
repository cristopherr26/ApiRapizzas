package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import static co.edu.uco.rapizzas.business.assembler.dto.impl.OrderDTOAssembler.getOrderDTOAssembler;
import static co.edu.uco.rapizzas.business.assembler.dto.impl.ProductDTOAssembler.getProductDTOAssembler;

import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.ProductOrderDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
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
		var domainTmp = ObjectHelper.getDefault(domain, new ProductOrderDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var orderTmp = getOrderDTOAssembler().toDTO(domainTmp.getOrder());
		var productTmp = getProductDTOAssembler().toDTO(domainTmp.getProduct());
		return new ProductOrderDTO(domainTmp.getId(), domainTmp.getAmount(), productTmp, orderTmp);
	}

	@Override
	public ProductOrderDomain toDomain(final ProductOrderDTO dto) {
		var dtoTmp = ObjectHelper.getDefault(dto, new ProductOrderDTO());
		var orderDomainTmp = getOrderDTOAssembler().toDomain(dtoTmp.getOrder());
		var productDomainTmp = getProductDTOAssembler().toDomain(dtoTmp.getProduct());
		return new ProductOrderDomain(dtoTmp.getProductOrderId(), dtoTmp.getAmount(), productDomainTmp, orderDomainTmp);
	}

	@Override
	public List<ProductOrderDTO> toDTO(final List<ProductOrderDomain> domainList) {
		var productOrderDtoList = new ArrayList<ProductOrderDTO>();
		
		for (var productOrderDomain : domainList) {
			
			productOrderDtoList.add(toDTO(productOrderDomain));
			
		}
		
		return productOrderDtoList;
	}

}
