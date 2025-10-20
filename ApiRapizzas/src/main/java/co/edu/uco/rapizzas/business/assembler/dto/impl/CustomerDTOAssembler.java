package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import static co.edu.uco.rapizzas.business.assembler.dto.impl.IdentificationTypeDTOAssembler.getIdentificationTypeDTOAssembler;

import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.CustomerDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.dto.CustomerDTO;

public final class CustomerDTOAssembler implements DTOAssembler<CustomerDTO, CustomerDomain> {
	
	private static final DTOAssembler<CustomerDTO, CustomerDomain> instance =
			new CustomerDTOAssembler();
	
	private CustomerDTOAssembler() {
		
	}
	
	public static DTOAssembler<CustomerDTO, CustomerDomain> getCustomerDTOAssembler() {
		return instance;
	}
	
	@Override
	public CustomerDTO toDTO(final CustomerDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new CustomerDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var identificationTypeTmp = getIdentificationTypeDTOAssembler().toDTO(domainTmp.getIdentificationType());
		return new CustomerDTO(domainTmp.getId(), domainTmp.getName(), domainTmp.getLastName(), domainTmp.isActive(), identificationTypeTmp, domainTmp.getIdentificationNumber());
	}

	@Override
	public CustomerDomain toDomain(final CustomerDTO dto) {
		var dtoTmp = ObjectHelper.getDefault(dto, new CustomerDTO());
		var identificationTypeDomainTmp = getIdentificationTypeDTOAssembler().toDomain(dtoTmp.getIdentificationType());
		return new CustomerDomain(dtoTmp.getCustomerId(), dtoTmp.getName(), dtoTmp.getLastName(), dtoTmp.isActive(), identificationTypeDomainTmp, dtoTmp.getIdentificationNumber());
	}

	@Override
	public List<CustomerDTO> toDTO(final List<CustomerDomain> domainList) {
		var customerDtoList = new ArrayList<CustomerDTO>();
		
		for (var customerDomain : domainList) {
			
			customerDtoList.add(toDTO(customerDomain));
			
		}
		
		return customerDtoList;
	}
}


