package co.edu.uco.rapizzas.dto;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.IntegerHelper;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class OrderDTO {
	
	private UUID orderId;
	private int total;
	private String comment;
	private EmployeeDTO employee;
	private StatusDTO status;
	private CustomerTableDTO customerTable;
	
	public OrderDTO() {
		setOrderId(UUIDHelper.getUUIDHelper().getDefault());
		setTotal(IntegerHelper.getDefault());
		setComment(TextHelper.getDefault());
		setEmployee(EmployeeDTO.getDefaultValue());
		setStatus(StatusDTO.getDefaultValue());
		setCustomerTable(CustomerTableDTO.getDefaultValue());
	}
	
	public OrderDTO(final UUID orderId) {
		setOrderId(orderId);
		setTotal(IntegerHelper.getDefault());
		setComment(TextHelper.getDefault());
		setEmployee(EmployeeDTO.getDefaultValue());
		setStatus(StatusDTO.getDefaultValue());
		setCustomerTable(CustomerTableDTO.getDefaultValue());
	}
	
	public OrderDTO(final UUID orderId, final int total, final String comment, 
			final EmployeeDTO employee, final StatusDTO status, 
			final CustomerTableDTO customerTable) {
		setOrderId(orderId);
		setTotal(total);
		setComment(comment);
		setEmployee(employee);
		setStatus(status);
		setCustomerTable(customerTable);
	}
	
	static OrderDTO getDefaultValue() {
		return new OrderDTO();
	}
	
	static OrderDTO getDefaultValue(final OrderDTO order) {
		return ObjectHelper.getDefault(order, getDefaultValue());
	}
	
	public UUID getOrderId() {
		return orderId;
	}
	
	public void setOrderId(final UUID orderId) {
		this.orderId = UUIDHelper.getUUIDHelper().getDefault(orderId);
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
	
	public EmployeeDTO getEmployee() {
		return employee;
	}
	
	public void setEmployee(final EmployeeDTO employee) {
		this.employee = EmployeeDTO.getDefaultValue(employee);
	}
	
	public StatusDTO getStatus() {
		return status;
	}
	
	public void setStatus(final StatusDTO status) {
		this.status = StatusDTO.getDefaultValue(status);
	}
	
	public CustomerTableDTO getCustomerTable() {
		return customerTable;
	}
	
	public void setCustomerTable(final CustomerTableDTO customerTable) {
		this.customerTable = CustomerTableDTO.getDefaultValue(customerTable);
	}
	
}
