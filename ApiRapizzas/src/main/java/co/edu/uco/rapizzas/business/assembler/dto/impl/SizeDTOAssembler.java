package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.SizeDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.dto.SizeDTO;

public final class SizeDTOAssembler implements DTOAssembler<SizeDTO, SizeDomain>{
	
	private static final DTOAssembler<SizeDTO, SizeDomain> instance =
			new SizeDTOAssembler();
	
	private SizeDTOAssembler() {
		
	}
	
	public static DTOAssembler<SizeDTO, SizeDomain> getSizeDTOAssembler() {
		return instance;
	}
	
	@Override
	public SizeDTO toDTO(final SizeDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new SizeDomain(UUIDHelper.getUUIDHelper().getDefault()));
		return new SizeDTO(domainTmp.getId(), domainTmp.getNameSize());
	}

	@Override
	public SizeDomain toDomain(final SizeDTO dto) {
		var dtoTmp = ObjectHelper.getDefault(dto, new SizeDTO());
		return new SizeDomain(dtoTmp.getSizeId(), dtoTmp.getSizeName());
	}

	@Override
	public List<SizeDTO> toDTO(final List<SizeDomain> domainList) {
		var sizeDtoList = new ArrayList<SizeDTO>();
		
		for (var sizeDomain : domainList) {
			
			sizeDtoList.add(toDTO(sizeDomain));
		}
		
		return sizeDtoList;
	}

}
