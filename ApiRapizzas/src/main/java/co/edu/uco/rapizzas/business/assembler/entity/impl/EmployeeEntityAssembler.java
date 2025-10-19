package co.edu.uco.rapizzas.business.assembler.entity.impl;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.EmployeeDomain;
import co.edu.uco.rapizzas.entity.EmployeeEntity;

public final class EmployeeEntityAssembler implements EntityAssembler<EmployeeEntity, EmployeeDomain>{
	
private static final EntityAssembler<EmployeeEntity, EmployeeDomain> instance = new EmployeeEntityAssembler();
	
	private EmployeeEntityAssembler() {
		
	}
	
	public static EntityAssembler<EmployeeEntity, EmployeeDomain> getEmployeeEntityAssembler() {
		return instance;
	}
	
	@Override
	public EmployeeEntity toDomain(final EmployeeDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeDomain toEntity(final EmployeeEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
