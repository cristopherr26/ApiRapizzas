package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.List;

import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.EmployeeDomain;
import co.edu.uco.rapizzas.dto.EmployeeDTO;

public final class EmployeeDTOAssembler implements DTOAssembler<EmployeeDTO, EmployeeDomain>{
	
	private static final DTOAssembler<EmployeeDTO, EmployeeDomain> instance =
			new EmployeeDTOAssembler();
	
	private EmployeeDTOAssembler() {
		
	}
	
	public static DTOAssembler<EmployeeDTO, EmployeeDomain> getEmployeeDTOAssembler() {
		return instance;
	}
	
	@Override
	public EmployeeDTO toDTO(final EmployeeDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeDomain toDomain(final EmployeeDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeDTO> toDTO(List<EmployeeDomain> domainList) {
		// TODO Auto-generated method stub
		return null;
	}

}
