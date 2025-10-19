package co.edu.uco.rapizzas.business.assembler.entity.impl;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.TableDomain;
import co.edu.uco.rapizzas.entity.TableEntity;

public final class TableEntityAssembler implements EntityAssembler<TableEntity, TableDomain>{
	
	private static final EntityAssembler<TableEntity, TableDomain> instance = new TableEntityAssembler();
	
	private TableEntityAssembler() {
		
	}
	
	public static EntityAssembler<TableEntity, TableDomain> getTableEntityAssembler() {
		return instance;
	}
	
	@Override
	public TableEntity toDomain(final TableDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableDomain toEntity(final TableEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
