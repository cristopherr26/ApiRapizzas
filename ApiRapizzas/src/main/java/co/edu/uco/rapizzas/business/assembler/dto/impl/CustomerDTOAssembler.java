package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.List;

import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.CustomerDomain;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDomain toDomain(final CustomerDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerDTO> toDTO(List<CustomerDomain> domainList) {
		// TODO Auto-generated method stub
		return null;
	}

}
