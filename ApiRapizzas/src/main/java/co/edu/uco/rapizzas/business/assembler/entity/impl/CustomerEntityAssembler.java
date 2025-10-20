package co.edu.uco.rapizzas.business.assembler.entity.impl;

import static co.edu.uco.rapizzas.business.assembler.entity.impl.IdentificationTypeEntityAssembler.getIdentificationTypeEntityAssembler;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.CustomerDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.entity.CustomerEntity;

public final class CustomerEntityAssembler implements EntityAssembler<CustomerEntity, CustomerDomain>{
	
	private static final EntityAssembler<CustomerEntity, CustomerDomain> instance = new CustomerEntityAssembler();
	
	private CustomerEntityAssembler() {
		
	}
	
	public static EntityAssembler<CustomerEntity, CustomerDomain> getCustomerEntityAssembler() {
		return instance;
	}

	@Override
	public CustomerEntity toEntity(final CustomerDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new CustomerDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var identificationTypeTmp = getIdentificationTypeEntityAssembler().toEntity(domainTmp.getIdentificationType());
		return new CustomerEntity(domainTmp.getId(), domainTmp.getName(), domainTmp.getLastName(), domainTmp.isActive(), identificationTypeTmp, domainTmp.getIdentificationNumber());
	}

	@Override
	public CustomerDomain toDomain(final CustomerEntity entity) {
		var entityTmp = ObjectHelper.getDefault(entity, new CustomerEntity());
		var identificationTypeDomainTmp = getIdentificationTypeEntityAssembler().toDomain(entityTmp.getIdentificationType());
		return new CustomerDomain(entityTmp.getCustomerId(), entityTmp.getName(), entityTmp.getLastName(), entityTmp.isActive(), identificationTypeDomainTmp, entityTmp.getIdentificationNumber());
	}
	
	

}
