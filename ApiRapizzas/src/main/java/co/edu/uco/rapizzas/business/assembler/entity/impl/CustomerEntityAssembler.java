package co.edu.uco.rapizzas.business.assembler.entity.impl;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.CustomerDomain;
import co.edu.uco.rapizzas.entity.CustomerEntity;

public final class CustomerEntityAssembler implements EntityAssembler<CustomerEntity, CustomerDomain>{
	
	private static final EntityAssembler<CustomerEntity, CustomerDomain> instance = new CustomerEntityAssembler();
	
	private CustomerEntityAssembler() {
		
	}
	
	public static EntityAssembler<CustomerEntity, CustomerDomain> getCustomerEntityAssembler() {
		return instance;
	}
	
	@Override
	public CustomerEntity toDomain(final CustomerDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDomain toEntity(final CustomerEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
