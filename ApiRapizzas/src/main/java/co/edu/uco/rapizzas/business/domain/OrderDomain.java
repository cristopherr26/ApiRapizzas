package co.edu.uco.rapizzas.business.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.IntegerHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class OrderDomain extends Domain {
	
	private int total;
	private String comment;
	private EmployeeDomain employee;
	private StatusDomain status;
	private CustomerTableDomain customerTable;
	
	public OrderDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setTotal(IntegerHelper.getDefault());
		setComment(TextHelper.getDefault());
		setEmployee(EmployeeDomain.getDefaultValue());
		setStatus(StatusDomain.getDefaultValue());
		setCustomerTable(CustomerTableDomain.getDefaultValue());
	}
	
	public OrderDomain(final UUID id) {
		super(id);
		setTotal(IntegerHelper.getDefault());
		setComment(TextHelper.getDefault());
		setEmployee(EmployeeDomain.getDefaultValue());
		setStatus(StatusDomain.getDefaultValue());
		setCustomerTable(CustomerTableDomain.getDefaultValue());
	}
	
	public OrderDomain(final UUID id, final int total, final String comment, 
			final EmployeeDomain employee, final StatusDomain status, 
			final CustomerTableDomain customerTable) {
		super(id);
		setTotal(total);
		setComment(comment);
		setEmployee(employee);
		setStatus(status);
		setCustomerTable(customerTable);
	}
	
	static OrderDomain getDefaultValue() {
		return new OrderDomain();
	}
	
	static OrderDomain getDefaultValue(final OrderDomain order) {
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
	
	public EmployeeDomain getEmployee() {
		return employee;
	}
	
	public void setEmployee(final EmployeeDomain employee) {
		this.employee = EmployeeDomain.getDefaultValue(employee);
	}
	
	public StatusDomain getStatus() {
		return status;
	}
	
	public void setStatus(final StatusDomain status) {
		this.status = StatusDomain.getDefaultValue(status);
	}
	
	public CustomerTableDomain getCustomerTable() {
		return customerTable;
	}
	
	public void setCustomerTable(final CustomerTableDomain customerTable) {
		this.customerTable = CustomerTableDomain.getDefaultValue(customerTable);
	}
	
}
