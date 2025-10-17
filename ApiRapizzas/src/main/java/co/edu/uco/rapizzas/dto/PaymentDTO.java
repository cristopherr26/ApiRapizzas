package co.edu.uco.rapizzas.dto;

import java.time.LocalDate;
import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.DateHelper;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public class PaymentDTO {
	
	private UUID paymentId;
	private LocalDate collectionDate;
	private EmployeeDTO employee;
	private OrderDTO order;
	
	public PaymentDTO() {
		setPaymentId(UUIDHelper.getUUIDHelper().getDefault());
		setCollectionDate(DateHelper.getDefault());
		setEmployee(EmployeeDTO.getDefaultValue());
		setOrder(OrderDTO.getDefaultValue());
	}
	
	public PaymentDTO(final UUID paymentId) {
		setPaymentId(paymentId);
		setCollectionDate(DateHelper.getDefault());
		setEmployee(EmployeeDTO.getDefaultValue());
		setOrder(OrderDTO.getDefaultValue());
	}
	
	public PaymentDTO(final UUID paymentId, final LocalDate collectionDate, 
			final EmployeeDTO employee, final OrderDTO order) {
		setPaymentId(paymentId);
		setCollectionDate(collectionDate);
		setEmployee(employee);
		setOrder(order);
	}
	
	static PaymentDTO getDefaultValue() {
		return new PaymentDTO();
	}
	
	static PaymentDTO getDefaultValue(final PaymentDTO payment) {
		return ObjectHelper.getDefault(payment, getDefaultValue());
	}
	
	public UUID getPaymentId() {
		return paymentId;
	}
	
	public void setPaymentId(final UUID paymentId) {
		this.paymentId = ObjectHelper.getDefault(paymentId, UUIDHelper.getUUIDHelper().getDefault());
	}
	
	public LocalDate getCollectionDate() {
		return collectionDate;
	}
	
	public void setCollectionDate(final LocalDate collectionDate) {
		this.collectionDate = DateHelper.getDefault(collectionDate);
	}
	
	public EmployeeDTO getEmployee() {
		return employee;
	}
	
	public void setEmployee(final EmployeeDTO employee) {
		this.employee = EmployeeDTO.getDefaultValue(employee);
	}
	
	public OrderDTO getOrder() {
		return order;
	}
	
	public void setOrder(final OrderDTO order) {
		this.order = OrderDTO.getDefaultValue(order);
	}
	
}
