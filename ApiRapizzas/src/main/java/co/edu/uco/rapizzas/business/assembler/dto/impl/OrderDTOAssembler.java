package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.List;

import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.OrderDomain;
import co.edu.uco.rapizzas.dto.OrderDTO;

public final class OrderDTOAssembler implements DTOAssembler<OrderDTO, OrderDomain>{
	
	private static final DTOAssembler<OrderDTO, OrderDomain> instance =
			new OrderDTOAssembler();
	
	private OrderDTOAssembler() {
		
	}
	
	public static DTOAssembler<OrderDTO, OrderDomain> getOrderDTOAssembler() {
		return instance;
	}
	
	@Override
	public OrderDTO toDTO(final OrderDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDomain toDomain(final OrderDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDTO> toDTO(List<OrderDomain> domainList) {
		// TODO Auto-generated method stub
		return null;
	}

}
