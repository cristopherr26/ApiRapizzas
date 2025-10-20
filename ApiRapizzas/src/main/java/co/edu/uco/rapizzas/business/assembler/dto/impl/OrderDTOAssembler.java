package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import static co.edu.uco.rapizzas.business.assembler.dto.impl.EmployeeDTOAssembler.getEmployeeDTOAssembler;
import static co.edu.uco.rapizzas.business.assembler.dto.impl.CustomerTableDTOAssembler.getCustomerTableDTOAssembler;
import static co.edu.uco.rapizzas.business.assembler.dto.impl.StatusDTOAssembler.getStatusDTOAssembler;


import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.OrderDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
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
		var domainTmp = ObjectHelper.getDefault(domain, new OrderDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var customerTableTmp = getCustomerTableDTOAssembler().toDTO(domainTmp.getCustomerTable());
		var employeeTmp = getEmployeeDTOAssembler().toDTO(domainTmp.getEmployee());
		var statusTmp = getStatusDTOAssembler().toDTO(domainTmp.getStatus());
		return new OrderDTO(domainTmp.getId(), domainTmp.getTotal(), domainTmp.getComment(), employeeTmp, statusTmp, customerTableTmp);
	}

	@Override
	public OrderDomain toDomain(final OrderDTO dto) {
		var dtoTmp = ObjectHelper.getDefault(dto, new OrderDTO());
		var customerTableDomainTmp = getCustomerTableDTOAssembler().toDomain(dtoTmp.getCustomerTable());
		var employeeTmp = getEmployeeDTOAssembler().toDomain(dtoTmp.getEmployee());
		var statusTmp = getStatusDTOAssembler().toDomain(dtoTmp.getStatus());
		return new OrderDomain(dtoTmp.getOrderId(), dtoTmp.getTotal(), dtoTmp.getComment(), employeeTmp, statusTmp, customerTableDomainTmp);
	}

	@Override
	public List<OrderDTO> toDTO(final List<OrderDomain> domainList) {
		var orderDtoList = new ArrayList<OrderDTO>();
		
		for (var orderDomain : domainList) {
			
			orderDtoList.add(toDTO(orderDomain));
			
		}
		
		return orderDtoList;
	}

}
