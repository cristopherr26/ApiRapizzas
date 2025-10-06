package co.edu.uco.rapizzas.entity;

import java.time.LocalDate;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.DateHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class PaymentEntity {
	
	private UUID paymentId;
	private LocalDate collectionDate;
	private EmployeeEntity employee;
	private OrderEntity order;
	
	public PaymentEntity() {
		setPaymentId(UUIDHelper.getUUIDHelper().getDefault());
		setCollectionDate(DateHelper.getDefault());
		setEmployee(EmployeeEntity.getDefaultValue());
		setOrder(OrderEntity.getDefaultValue());
	}
	
	public PaymentEntity(final UUID id) {
		setPaymentId(id);
		setCollectionDate(DateHelper.getDefault());
		setEmployee(EmployeeEntity.getDefaultValue());
		setOrder(OrderEntity.getDefaultValue());
	}
	
	public PaymentEntity(final UUID id, final LocalDate collectionDate, 
			final EmployeeEntity employee, final OrderEntity order) {
		setPaymentId(id);
		setCollectionDate(collectionDate);
		setEmployee(employee);
		setOrder(order);
	}
	
	static PaymentEntity getDefaultValue() {
		return new PaymentEntity();
	}
	
	static PaymentEntity getDefaultValue(final PaymentEntity payment) {
		return ObjectHelper.getDefault(payment, getDefaultValue());
	}
	
	public LocalDate getCollectionDate() {
		return collectionDate;
	}
	
	public void setCollectionDate(final LocalDate collectionDate) {
		this.collectionDate = DateHelper.getDefault(collectionDate);
	}

	public UUID getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(final UUID paymentId) {
		this.paymentId = UUIDHelper.getUUIDHelper().getDefault(paymentId);
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(final EmployeeEntity employee) {
		this.employee = EmployeeEntity.getDefaultValue(employee);
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(final OrderEntity order) {
		this.order = OrderEntity.getDefaultValue(order);
	}
	
	
	
}
