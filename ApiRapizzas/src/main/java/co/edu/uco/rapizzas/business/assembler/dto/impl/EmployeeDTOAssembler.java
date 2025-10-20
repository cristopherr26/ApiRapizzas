package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import static co.edu.uco.rapizzas.business.assembler.dto.impl.IdentificationTypeDTOAssembler.getIdentificationTypeDTOAssembler;

import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.EmployeeDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
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
		var domainTmp = ObjectHelper.getDefault(domain, new EmployeeDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var identificationTypeTmp = getIdentificationTypeDTOAssembler().toDTO(domainTmp.getIdentificationType());
		return new EmployeeDTO(domainTmp.getId(), domainTmp.getName(), domainTmp.getLastName(), domainTmp.isActive(), identificationTypeTmp, domainTmp.getIdentificationNumber(), domainTmp.getCellPhoneNumber(), domainTmp.isCellPhoneNumberConfirmed(), domainTmp.isAdministrator(), domainTmp.getEmployeePassword());
	}

	@Override
	public EmployeeDomain toDomain(final EmployeeDTO dto) {
		var dtoTmp = ObjectHelper.getDefault(dto, new EmployeeDTO());
		var identificationTypeDomainTmp = getIdentificationTypeDTOAssembler().toDomain(dtoTmp.getIdentificationType());
		return new EmployeeDomain(dtoTmp.getEmployeeId(), dtoTmp.getName(), dtoTmp.getLastName(), dtoTmp.isActive(), identificationTypeDomainTmp, dtoTmp.getIdentificationNumber(), dtoTmp.getCellPhoneNumber(), dtoTmp.isCellPhoneNumberConfirmed(), dtoTmp.isAdministrator(), dtoTmp.getEmployeePassword());
	}

	@Override
	public List<EmployeeDTO> toDTO(final List<EmployeeDomain> domainList) {
		
		if (ObjectHelper.isNull(domainList)) {
			return new ArrayList<>();
		}

		var employeeDtoList = new ArrayList<EmployeeDTO>();
		
		for (var employeeDomain : domainList) {
			
			employeeDtoList.add(toDTO(employeeDomain));
			
		}
		
		return employeeDtoList;
	}

}
