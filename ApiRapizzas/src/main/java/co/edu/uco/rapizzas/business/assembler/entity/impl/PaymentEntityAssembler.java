package co.edu.uco.rapizzas.business.assembler.entity.impl;

import co.edu.uco.rapizzas.business.assembler.entity.EntityAssembler;
import co.edu.uco.rapizzas.business.domain.PaymentDomain;
import co.edu.uco.rapizzas.entity.PaymentEntity;

public final class PaymentEntityAssembler implements EntityAssembler<PaymentEntity, PaymentDomain>{
	
	private static final EntityAssembler<PaymentEntity, PaymentDomain> instance = new PaymentEntityAssembler();
	
	private PaymentEntityAssembler() {
		
	}
	
	public static EntityAssembler<PaymentEntity, PaymentDomain> getPaymentEntityAssembler() {
		return instance;
	}
	
	@Override
	public PaymentEntity toDomain(final PaymentDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentDomain toEntity(final PaymentEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
