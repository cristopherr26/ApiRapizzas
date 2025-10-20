package co.edu.uco.rapizzas.business.domain;

import java.time.LocalDate;
import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.DateHelper;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class CustomerTableDomain extends Domain {
	
	private LocalDate orderDate;
	private TableDomain table;
	private CustomerDomain customer;
	
	CustomerTableDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setOrderDate(DateHelper.getDefault());
		setTable(TableDomain.getDefaultValue());
		setCustomer(CustomerDomain.getDefaultValue());
	}
	
	public CustomerTableDomain(final UUID id) {
		super(id);
		setOrderDate(DateHelper.getDefault());
		setTable(TableDomain.getDefaultValue());
		setCustomer(CustomerDomain.getDefaultValue());
	}
	
	public CustomerTableDomain(final UUID id, final LocalDate orderDate, 
			final TableDomain table, final CustomerDomain customer) {
		super(id);
		setOrderDate(orderDate);
		setTable(table);
		setCustomer(customer);
	}
	
	static CustomerTableDomain getDefaultValue() {
		return new CustomerTableDomain();
	}
	
	static CustomerTableDomain getDefaultValue(final CustomerTableDomain customerTable) {
		return ObjectHelper.getDefault(customerTable, getDefaultValue());
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(final LocalDate orderDate) {
		this.orderDate = DateHelper.getDefault(orderDate);
	}

	public TableDomain getTable() {
		return table;
	}

	public void setTable(final TableDomain table) {
		this.table = TableDomain.getDefaultValue(table);
	}

	public CustomerDomain getCustomer() {
		return customer;
	}

	public void setCustomer(final CustomerDomain customer) {
		this.customer = CustomerDomain.getDefaultValue(customer);
	}
	
}
