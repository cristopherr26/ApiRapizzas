package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import static co.edu.uco.rapizzas.business.assembler.dto.impl.CustomerDTOAssembler.getCustomerDTOAssembler;
import static co.edu.uco.rapizzas.business.assembler.dto.impl.TableDTOAssembler.getTableDTOAssembler;

import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.CustomerTableDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.dto.CustomerTableDTO;

public final class CustomerTableDTOAssembler implements DTOAssembler<CustomerTableDTO, CustomerTableDomain>{
	
	private static final DTOAssembler<CustomerTableDTO, CustomerTableDomain> instance =
			new CustomerTableDTOAssembler();
	
	private CustomerTableDTOAssembler() {
		
	}
	
	public static DTOAssembler<CustomerTableDTO, CustomerTableDomain> getCustomerTableDTOAssembler() {
		return instance;
	}
	
	@Override
	public CustomerTableDTO toDTO(final CustomerTableDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new CustomerTableDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var customerTmp = getCustomerDTOAssembler().toDTO(domainTmp.getCustomer());
		var tableTmp = getTableDTOAssembler().toDTO(domainTmp.getTable());
		return new CustomerTableDTO(domainTmp.getId(), domainTmp.getOrderDate(), tableTmp, customerTmp);
	}

	@Override
	public CustomerTableDomain toDomain(final CustomerTableDTO dto) {
		var dtoTmp = ObjectHelper.getDefault(dto, new CustomerTableDTO());
		var customerDomainTmp = getCustomerDTOAssembler().toDomain(dtoTmp.getCustomer());
		var tableDomainTmp = getTableDTOAssembler().toDomain(dtoTmp.getTable());
		return new CustomerTableDomain(dtoTmp.getCustomerTableId(), dtoTmp.getOrderDate(), tableDomainTmp, customerDomainTmp);
	}

	@Override
	public List<CustomerTableDTO> toDTO(final List<CustomerTableDomain> domainList) {
		
		if (ObjectHelper.isNull(domainList)) {
			return new ArrayList<>();
		}
		
		var customerTableDtoList = new ArrayList<CustomerTableDTO>();
		
		for (var customerTableDomain : domainList) {
			
			customerTableDtoList.add(toDTO(customerTableDomain));
			
		}
		
		return customerTableDtoList;
	}

}
