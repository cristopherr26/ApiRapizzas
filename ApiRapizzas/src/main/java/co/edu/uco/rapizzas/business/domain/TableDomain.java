package co.edu.uco.rapizzas.business.domain;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.IntegerHelper;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class TableDomain extends Domain{
	
	private int tableNumber;
	
	public TableDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setTableNumber(IntegerHelper.getDefault());
	}
	
	public TableDomain(final UUID id) {
		super(id);
		setTableNumber(IntegerHelper.getDefault());
	}
	
	public TableDomain(final UUID id, final int tableNumber) {
		super(id);
		setTableNumber(tableNumber);
	}
	
	static TableDomain getDefaultValue() {
		return new TableDomain();
	}
	
	static TableDomain getDefaultValue(final TableDomain table) {
		return ObjectHelper.getDefault(table, getDefaultValue());
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(final int tableNumber) {
		this.tableNumber = IntegerHelper.getDefault(tableNumber);
	}

}
