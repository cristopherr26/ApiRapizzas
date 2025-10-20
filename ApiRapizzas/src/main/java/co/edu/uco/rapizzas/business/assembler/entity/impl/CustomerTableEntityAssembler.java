package co.edu.uco.rapizzas.business.assembler.entity.impl;

import static co.edu.uco.rapizzas.business.assembler.entity.impl.TableEntityAssembler.getTableEntityAssembler;
import static co.edu.uco.rapizzas.business.assembler.entity.impl.CustomerEntityAssembler.getCustomerEntityAssembler;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.CustomerTableDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.entity.CustomerTableEntity;

public final class CustomerTableEntityAssembler implements EntityAssembler<CustomerTableEntity, CustomerTableDomain>{
	
private static final EntityAssembler<CustomerTableEntity, CustomerTableDomain> instance = new CustomerTableEntityAssembler();
	
	private CustomerTableEntityAssembler() {
		
	}
	
	public static EntityAssembler<CustomerTableEntity, CustomerTableDomain> getCustomerTableEntityAssembler() {
		return instance;
	}

	@Override
	public CustomerTableEntity toEntity(final CustomerTableDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new CustomerTableDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var tableTmp = getTableEntityAssembler().toEntity(domainTmp.getTable());
		var customerTmp = getCustomerEntityAssembler().toEntity(domainTmp.getCustomer());
		return new CustomerTableEntity(domainTmp.getId(), domainTmp.getOrderDate(), tableTmp, customerTmp);
	}

	@Override
	public CustomerTableDomain toDomain(final CustomerTableEntity entity) {
		var entityTmp = ObjectHelper.getDefault(entity, new CustomerTableEntity());
		var tableDomainTmp = getTableEntityAssembler().toDomain(entityTmp.getTable());
		var customerDomainTmp = getCustomerEntityAssembler().toDomain(entityTmp.getCustomer());
		return new CustomerTableDomain(entityTmp.getCustomerTableId(), entityTmp.getOrderDate(), tableDomainTmp, customerDomainTmp);
	}
	


}
