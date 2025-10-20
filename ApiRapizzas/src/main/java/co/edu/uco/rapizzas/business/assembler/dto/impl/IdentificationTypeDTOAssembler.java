package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.IdentificationTypeDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.dto.IdentificationTypeDTO;

public final class IdentificationTypeDTOAssembler implements DTOAssembler<IdentificationTypeDTO, IdentificationTypeDomain>{
	
	private static final DTOAssembler<IdentificationTypeDTO, IdentificationTypeDomain> instance =
			new IdentificationTypeDTOAssembler();
	
	private IdentificationTypeDTOAssembler() {
		
	}
	
	public static DTOAssembler<IdentificationTypeDTO, IdentificationTypeDomain> getIdentificationTypeDTOAssembler() {
		return instance;
	}
	
	@Override
	public IdentificationTypeDTO toDTO(final IdentificationTypeDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new IdentificationTypeDomain(UUIDHelper.getUUIDHelper().getDefault()));
		return new IdentificationTypeDTO(domainTmp.getId(), domainTmp.getIdentificationTypeName());
	}

	@Override
	public IdentificationTypeDomain toDomain(final IdentificationTypeDTO dto) {
		var dtoTmp = ObjectHelper.getDefault(dto, new IdentificationTypeDTO());
		return new IdentificationTypeDomain(dtoTmp.getIdentificationTypeId(), dtoTmp.getIdentificationTypeName());
	}

	@Override
	public List<IdentificationTypeDTO> toDTO(final List<IdentificationTypeDomain> domainList) {
		var identificationTypeDtoList = new ArrayList<IdentificationTypeDTO>();
		
		for (var identificationTypeDomain : domainList) {
			
			identificationTypeDtoList.add(toDTO(identificationTypeDomain));
		}
		
		return identificationTypeDtoList;
	}

}
