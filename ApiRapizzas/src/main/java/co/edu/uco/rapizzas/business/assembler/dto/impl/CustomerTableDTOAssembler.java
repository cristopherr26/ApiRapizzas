package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.List;

import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.CustomerTableDomain;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerTableDomain toDomain(final CustomerTableDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerTableDTO> toDTO(List<CustomerTableDomain> domainList) {
		// TODO Auto-generated method stub
		return null;
	}

}
