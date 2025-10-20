package co.edu.uco.rapizzas.business.domain;

import java.time.LocalDate;
import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.DateHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class PaymentDomain extends Domain {
	
	private LocalDate collectionDate;
	private EmployeeDomain employee;
	private OrderDomain order;
	
	PaymentDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setCollectionDate(DateHelper.getDefault());
		setEmployee(EmployeeDomain.getDefaultValue());
		setOrder(OrderDomain.getDefaultValue());
	}
	
	public PaymentDomain(final UUID id) {
		super(id);
		setCollectionDate(DateHelper.getDefault());
		setEmployee(EmployeeDomain.getDefaultValue());
		setOrder(OrderDomain.getDefaultValue());
	}
	
	public PaymentDomain(final UUID id, final LocalDate collectionDate, 
			final EmployeeDomain employee, final OrderDomain order) {
		super(id);
		setCollectionDate(collectionDate);
		setEmployee(employee);
		setOrder(order);
	}
	
	static PaymentDomain getDefaultValue() {
		return new PaymentDomain();
	}
	
	static PaymentDomain getDefaultValue(final PaymentDomain payment) {
		return ObjectHelper.getDefault(payment, getDefaultValue());
	}
	
	public LocalDate getCollectionDate() {
		return collectionDate;
	}
	
	public void setCollectionDate(final LocalDate collectionDate) {
		this.collectionDate = DateHelper.getDefault(collectionDate);
	}
	
	public EmployeeDomain getEmployee() {
		return employee;
	}
	
	public void setEmployee(final EmployeeDomain employee) {
		this.employee = EmployeeDomain.getDefaultValue(employee);
	}
	
	public OrderDomain getOrder() {
		return order;
	}
	
	public void setOrder(final OrderDomain order) {
		this.order = OrderDomain.getDefaultValue(order);
	}
	
}
