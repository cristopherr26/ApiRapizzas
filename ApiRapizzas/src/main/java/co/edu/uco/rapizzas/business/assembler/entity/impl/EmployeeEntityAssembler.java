package co.edu.uco.rapizzas.business.assembler.entity.impl;

import static co.edu.uco.rapizzas.business.assembler.entity.impl.IdentificationTypeEntityAssembler.getIdentificationTypeEntityAssembler;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.EmployeeDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.entity.EmployeeEntity;

public final class EmployeeEntityAssembler implements EntityAssembler<EmployeeEntity, EmployeeDomain>{
	
private static final EntityAssembler<EmployeeEntity, EmployeeDomain> instance = new EmployeeEntityAssembler();
	
	private EmployeeEntityAssembler() {
		
	}
	
	public static EntityAssembler<EmployeeEntity, EmployeeDomain> getEmployeeEntityAssembler() {
		return instance;
	}

	@Override
	public EmployeeEntity toEntity(final EmployeeDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new EmployeeDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var identificationTypeTmp = getIdentificationTypeEntityAssembler().toEntity(domainTmp.getIdentificationType());
		return new EmployeeEntity(domainTmp.getId(), domainTmp.getName(), domainTmp.getLastName(), domainTmp.isActive(), identificationTypeTmp, domainTmp.getIdentificationNumber(), domainTmp.getCellPhoneNumber(), domainTmp.isCellPhoneNumberConfirmed(), domainTmp.isAdministrator(), domainTmp.getEmployeePassword());
	}

	@Override
	public EmployeeDomain toDomain(final EmployeeEntity entity) {
		var entityTmp = ObjectHelper.getDefault(entity, new EmployeeEntity());
		var identificationTypeDomainTmp = getIdentificationTypeEntityAssembler().toDomain(entityTmp.getIdentificationType());
		return new EmployeeDomain(entityTmp.getEmployeeId(), entityTmp.getName(), entityTmp.getLastName(), entityTmp.isActive(), identificationTypeDomainTmp, entityTmp.getIdentificationNumber(), entityTmp.getCellPhoneNumber(), entityTmp.isCellPhoneNumberConfirmed(), entityTmp.isAdministrator(), entityTmp.getEmployeePassword());
	}
	
	

}
