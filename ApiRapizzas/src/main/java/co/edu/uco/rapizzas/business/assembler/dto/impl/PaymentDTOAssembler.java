package co.edu.uco.rapizzas.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import static co.edu.uco.rapizzas.business.assembler.dto.impl.EmployeeDTOAssembler.getEmployeeDTOAssembler;
import static co.edu.uco.rapizzas.business.assembler.dto.impl.OrderDTOAssembler.getOrderDTOAssembler;

import co.edu.uco.rapizzas.business.assembler.dto.DTOAssembler;
import co.edu.uco.rapizzas.business.domain.PaymentDomain;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
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
		var domainTmp = ObjectHelper.getDefault(domain, new PaymentDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var orderTmp = getOrderDTOAssembler().toDTO(domainTmp.getOrder());
		var employeeTmp = getEmployeeDTOAssembler().toDTO(domainTmp.getEmployee());
		return new PaymentDTO(domainTmp.getId(), domainTmp.getCollectionDate(), employeeTmp, orderTmp);
	}

	@Override
	public PaymentDomain toDomain(final PaymentDTO dto) {
		var dtoTmp = ObjectHelper.getDefault(dto, new PaymentDTO());
		var employeeDomainTmp = getEmployeeDTOAssembler().toDomain(dtoTmp.getEmployee());
		var orderDomainTmp = getOrderDTOAssembler().toDomain(dtoTmp.getOrder());
		return new PaymentDomain(dtoTmp.getPaymentId(), dtoTmp.getCollectionDate(), employeeDomainTmp, orderDomainTmp);
		
	}

	@Override
	public List<PaymentDTO> toDTO(final List<PaymentDomain> domainList) {
		
		if (ObjectHelper.isNull(domainList)) {
		    return new ArrayList<>();
		}

		var paymentDtoList = new ArrayList<PaymentDTO>();
		
		for (var paymentDomain : domainList) {
			
			paymentDtoList.add(toDTO(paymentDomain));
			
		}
		
		return paymentDtoList;
	}

}
