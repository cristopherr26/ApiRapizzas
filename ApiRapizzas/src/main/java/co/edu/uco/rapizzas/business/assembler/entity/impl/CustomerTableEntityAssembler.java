package co.edu.uco.rapizzas.business.assembler.entity.impl;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.CustomerTableDomain;
import co.edu.uco.rapizzas.entity.CustomerTableEntity;

public final class CustomerTableEntityAssembler implements EntityAssembler<CustomerTableEntity, CustomerTableDomain>{
	
private static final EntityAssembler<CustomerTableEntity, CustomerTableDomain> instance = new CustomerTableEntityAssembler();
	
	private CustomerTableEntityAssembler() {
		
	}
	
	public static EntityAssembler<CustomerTableEntity, CustomerTableDomain> getCustomerTableEntityAssembler() {
		return instance;
	}
	
	@Override
	public CustomerTableEntity toDomain(final CustomerTableDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerTableDomain toEntity(final CustomerTableEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
