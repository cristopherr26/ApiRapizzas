package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.List;

import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.StatusDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.dto.StatusDTO;

public final class StatusDTOAssembler implements DTOAssembler<StatusDTO, StatusDomain>{
	
	private static final DTOAssembler<StatusDTO, StatusDomain> instance =
			new StatusDTOAssembler();
	
	private StatusDTOAssembler() {
		
	}
	
	public static DTOAssembler<StatusDTO, StatusDomain> getStatusDTOAssembler() {
		return instance;
	}
	
	@Override
	public StatusDTO toDTO(final StatusDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new StatusDomain(UUIDHelper.getUUIDHelper().getDefault()));
		return new StatusDTO(domainTmp.getId(), domainTmp.getStatusName());
	}

	@Override
	public StatusDomain toDomain(final StatusDTO dto) {
		var dtoTmp = ObjectHelper.getDefault(dto, new StatusDTO());
		return new StatusDomain(dtoTmp.getStatusId(), dtoTmp.getStatusName());
	}

	@Override
	public List<StatusDTO> toDTO(List<StatusDomain> domainList) {
		// TODO Auto-generated method stub
		return null;
	}

}
