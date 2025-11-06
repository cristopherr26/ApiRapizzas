package co.edu.uco.rapizzas.business.assembler.entity.impl;

import static co.edu.uco.rapizzas.business.assembler.entity.impl.OrderEntityAssembler.getOrderEntityAssembler;

import java.util.ArrayList;
import java.util.List;

import static co.edu.uco.rapizzas.business.assembler.entity.impl.EmployeeEntityAssembler.getEmployeeEntityAssembler;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.PaymentDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.entity.PaymentEntity;

public final class PaymentEntityAssembler implements EntityAssembler<PaymentEntity, PaymentDomain>{
	
	private static final EntityAssembler<PaymentEntity, PaymentDomain> instance = new PaymentEntityAssembler();
	
	private PaymentEntityAssembler() {
		
	}
	
	public static EntityAssembler<PaymentEntity, PaymentDomain> getPaymentEntityAssembler() {
		return instance;
	}

	@Override
	public PaymentEntity toEntity(final PaymentDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new PaymentDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var employeeTmp = getEmployeeEntityAssembler().toEntity(domainTmp.getEmployee());
		var orderTmp = getOrderEntityAssembler().toEntity(domainTmp.getOrder());
		return new PaymentEntity(domainTmp.getId(), domainTmp.getCollectionDate(), 
				employeeTmp, orderTmp);
	}

	@Override
	public PaymentDomain toDomain(final PaymentEntity entity) {
		var entityTmp = ObjectHelper.getDefault(entity, new PaymentEntity());
		var employeeDomainTmp = getEmployeeEntityAssembler().toDomain(entityTmp.getEmployee());
		var orderDomainTmp = getOrderEntityAssembler().toDomain(entityTmp.getOrder());
		return new PaymentDomain(entityTmp.getPaymentId(), entityTmp.getCollectionDate(), 
				employeeDomainTmp, orderDomainTmp);
	}

	@Override
	public List<PaymentDomain> toDomain(List<PaymentEntity> entityList) {
		var paymentDomainList = new ArrayList<PaymentDomain>();
		for (PaymentEntity entity : entityList) {
			paymentDomainList.add(toDomain(entity));
		}
		return paymentDomainList;
	}
	
}
