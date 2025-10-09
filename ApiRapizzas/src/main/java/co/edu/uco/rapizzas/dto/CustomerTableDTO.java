package co.edu.uco.rapizzas.dto;

import java.time.LocalDate;
import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.DateHelper;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;


public class CustomerTableDTO {
	
	private UUID customerTableId;
	private LocalDate orderDate;
	private TableDTO table;
	private CustomerDTO customer;
	
	public CustomerTableDTO() {
		setCustomerTableId(UUIDHelper.getUUIDHelper().getDefault());
		setOrderDate(DateHelper.getDefault());
		setTable(TableDTO.getDefaultValue());
		setCustomer(CustomerDTO.getDefaultValue());
	}
	
	public CustomerTableDTO(final UUID customerTableId) {
		setCustomerTableId(customerTableId);
		setOrderDate(DateHelper.getDefault());
		setTable(TableDTO.getDefaultValue());
		setCustomer(CustomerDTO.getDefaultValue());
	}
	
	public CustomerTableDTO(final UUID customerTableId, final LocalDate orderDate, 
			final TableDTO table, final CustomerDTO customer) {
		setCustomerTableId(customerTableId);
		setOrderDate(orderDate);
		setTable(table);
		setCustomer(customer);
	}
	
	static CustomerTableDTO getDefaultValue() {
		return new CustomerTableDTO();
	}
	
	static CustomerTableDTO getDefaultValue(final CustomerTableDTO customerTable) {
		return ObjectHelper.getDefault(customerTable, getDefaultValue());
	}
	
	public UUID getCustomerTableId() {
		return customerTableId;
	}
	
	public void setCustomerTableId(final UUID customerTableId) {
		this.customerTableId = ObjectHelper.getDefault(customerTableId, UUIDHelper.getUUIDHelper().getDefault());
	}
	
	public LocalDate getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(final LocalDate orderDate) {
		this.orderDate = DateHelper.getDefault(orderDate);
	}
	
	public TableDTO getTable() {
		return table;
	}
	
	public void setTable(final TableDTO table) {
		this.table = ObjectHelper.getDefault(table, TableDTO.getDefaultValue());
	}
	
	public CustomerDTO getCustomer() {
		return customer;
	}
	
	public void setCustomer(final CustomerDTO customer) {
		this.customer = ObjectHelper.getDefault(customer, CustomerDTO.getDefaultValue());
	}

}
