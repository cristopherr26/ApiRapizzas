package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.List;

import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.PaymentDomain;
import co.edu.uco.rapizzas.dto.PaymentDTO;

public final class PaymentDTOAssembler implements DTOAssembler<PaymentDTO, PaymentDomain>{
	
	private static final DTOAssembler<PaymentDTO, PaymentDomain> instance =
			new PaymentDTOAssembler();
	
	private PaymentDTOAssembler() {
		
	}
	
	public static DTOAssembler<PaymentDTO, PaymentDomain> getPaymentDTOAssembler() {
		return instance;
	}
	
	@Override
	public PaymentDTO toDTO(final PaymentDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentDomain toDomain(final PaymentDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentDTO> toDTO(List<PaymentDomain> domainList) {
		// TODO Auto-generated method stub
		return null;
	}

}
