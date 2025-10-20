package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.TableDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.dto.TableDTO;

public final class TableDTOAssembler implements DTOAssembler<TableDTO, TableDomain>{
	
	private static final DTOAssembler<TableDTO, TableDomain> instance =
			new TableDTOAssembler();
	
	private TableDTOAssembler() {
		
	}
	
	public static DTOAssembler<TableDTO, TableDomain> getTableDTOAssembler() {
		return instance;
	}
	
	@Override
	public TableDTO toDTO(final TableDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new TableDomain(UUIDHelper.getUUIDHelper().getDefault()));
		return new TableDTO(domainTmp.getId(), domainTmp.getTableNumber());
	}

	@Override
	public TableDomain toDomain(final TableDTO dto) {
		var dtoTmp = ObjectHelper.getDefault(dto, new TableDTO());
		return new TableDomain(dtoTmp.getTableId(), dtoTmp.getTableNumber());
	}

	@Override
	public List<TableDTO> toDTO(List<TableDomain> domainList) {
		
		if (ObjectHelper.isNull(domainList)) {
			return new ArrayList<>();
		}
		
		var tableDtoList = new ArrayList<TableDTO>();
		
		for (var tableDomain : domainList) {
			
			tableDtoList.add(toDTO(tableDomain));
		}
		
		return tableDtoList;
	}

}
