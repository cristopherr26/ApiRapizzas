package co.edu.uco.rapizzas.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.IntegerHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class OrderEntity {
	
	private UUID orderId;
	private int total;
	private String comment;
	private EmployeeEntity employee;
	private StatusEntity status;
	private CustomerTableEntity customerTable;
	
	public OrderEntity() {
		setOrderId(UUIDHelper.getUUIDHelper().getDefault());
		setTotal(IntegerHelper.getDefault());
		setComment(TextHelper.getDefault());
		setEmployee(EmployeeEntity.getDefaultValue());
		setStatus(StatusEntity.getDefaultValue());
		setCustomerTable(CustomerTableEntity.getDefaultValue());
	}
	
	public OrderEntity(final UUID id) {
		setOrderId(id);
		setTotal(IntegerHelper.getDefault());
		setComment(TextHelper.getDefault());
		setEmployee(EmployeeEntity.getDefaultValue());
		setStatus(StatusEntity.getDefaultValue());
		setCustomerTable(CustomerTableEntity.getDefaultValue());
	}
	
	public OrderEntity(final UUID id, final int total, final String comment, 
			final EmployeeEntity employee, final StatusEntity status, 
			final CustomerTableEntity customerTable) {
		setOrderId(id);
		setTotal(total);
		setComment(comment);
		setEmployee(employee);
		setStatus(status);
		setCustomerTable(customerTable);
	}
	
	static OrderEntity getDefaultValue() {
		return new OrderEntity();
	}
	
	static OrderEntity getDefaultValue(final OrderEntity order) {
		return ObjectHelper.getDefault(order, getDefaultValue());
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(final int total) {
		this.total = IntegerHelper.getDefault(total);
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(final String comment) {
		this.comment = TextHelper.getDefaultWithTrim(comment);
	}

	public UUID getOrderId() {
		return orderId;
	}

	public void setOrderId(final UUID orderId) {
		this.orderId = UUIDHelper.getUUIDHelper().getDefault(orderId);
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(final EmployeeEntity employee) {
		this.employee = EmployeeEntity.getDefaultValue(employee);
	}

	public StatusEntity getStatus() {
		return status;
	}

	public void setStatus(final StatusEntity status) {
		this.status = StatusEntity.getDefaultValue(status);
	}

	public CustomerTableEntity getCustomerTable() {
		return customerTable;
	}

	public void setCustomerTable(final CustomerTableEntity customerTable) {
		this.customerTable = CustomerTableEntity.getDefaultValue(customerTable);
	}

}
