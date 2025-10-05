package co.edu.uco.rapizzas.business.domain;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.IntegerHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public class TableDomain extends Domain{
	
	private int numberTable;
	
	public TableDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setTableNumber(IntegerHelper.getDefault());
	}
	
	public TableDomain(final UUID id) {
		super(id);
		setTableNumber(IntegerHelper.getDefault());
	}
	
	public TableDomain(final UUID id, final String nameCategory) {
		super(id);
		setTableNumber(numberTable);
	}
	
	static TableDomain getDefaultValue() {
		return new TableDomain();
	}

	public int getTableNumber() {
		return numberTable;
	}

	public void setTableNumber(final int tableNumber) {
		this.numberTable = IntegerHelper.getDefault(tableNumber);
	}
	
	

}
