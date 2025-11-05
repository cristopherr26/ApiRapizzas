package co.edu.uco.rapizzas.entity;

import java.time.LocalDate;
import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.DateHelper;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class CustomerTableEntity {
	
	private UUID id;
	private LocalDate orderDate;
	private TableEntity table;
	private CustomerEntity customer;
	
	public CustomerTableEntity() {
		setCustomerTableId(UUIDHelper.getUUIDHelper().getDefault());
		setOrderDate(DateHelper.getDefault());
		setTable(TableEntity.getDefaultValue());
		setCustomer(CustomerEntity.getDefaultValue());
	}
	
	public CustomerTableEntity(final UUID id) {
		setCustomerTableId(id);
		setOrderDate(DateHelper.getDefault());
		setTable(TableEntity.getDefaultValue());
		setCustomer(CustomerEntity.getDefaultValue());
	}
	
	public CustomerTableEntity(final UUID id, final LocalDate orderDate, final TableEntity table, final CustomerEntity customer) {
		setCustomerTableId(id);
		setOrderDate(orderDate);
		setTable(table);
		setCustomer(customer);
	}
	
	static CustomerTableEntity getDefaultValue() {
		return new CustomerTableEntity();
	}
	
	static CustomerTableEntity getDefaultValue(final CustomerTableEntity customerTable) {
		return ObjectHelper.getDefault(customerTable, getDefaultValue());
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(final LocalDate orderDate) {
		this.orderDate = DateHelper.getDefault(orderDate);
	}

	public UUID getCustomerTableId() {
		return id;
	}

	public void setCustomerTableId(final UUID customerTableId) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(customerTableId);
	}

	public TableEntity getTable() {
		return table;
	}

	public void setTable(final TableEntity table) {
		this.table = TableEntity.getDefaultValue(table);
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = CustomerEntity.getDefaultValue(customer);
	}
	
}
