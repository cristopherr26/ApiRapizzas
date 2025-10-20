package co.edu.uco.rapizzas.business.assembler.entity.impl;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.TableDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.entity.TableEntity;

public final class TableEntityAssembler implements EntityAssembler<TableEntity, TableDomain>{
	
	private static final EntityAssembler<TableEntity, TableDomain> instance = new TableEntityAssembler();
	
	private TableEntityAssembler() {
		
	}
	
	public static EntityAssembler<TableEntity, TableDomain> getTableEntityAssembler() {
		return instance;
	}

	@Override
	public TableEntity toEntity(final TableDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new TableDomain(UUIDHelper.getUUIDHelper().getDefault()));
		return new TableEntity(domainTmp.getId(), domainTmp.getTableNumber());
	}

	@Override
	public TableDomain toDomain(final TableEntity entity) {
		var entityTmp = ObjectHelper.getDefault(entity, new TableEntity());
		return new TableDomain(entityTmp.getTableId(), entityTmp.getTableNumber());
	}
	


}
