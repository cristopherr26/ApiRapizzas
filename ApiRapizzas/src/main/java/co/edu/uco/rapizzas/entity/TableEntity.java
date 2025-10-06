package co.edu.uco.rapizzas.entity;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.IntegerHelper;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class TableEntity{
	
	private UUID tableId;
	private int tableNumber;
	
	public TableEntity() {
		setTableId(UUIDHelper.getUUIDHelper().getDefault());
		setTableNumber(IntegerHelper.getDefault());
	}
	
	public TableEntity(final UUID id) {
		setTableId(id);
		setTableNumber(IntegerHelper.getDefault());
	}
	
	public TableEntity(final UUID id, final int tableNumber) {
		setTableId(id);
		setTableNumber(tableNumber);
	}
	
	static TableEntity getDefaultValue() {
		return new TableEntity();
	}
	
	static TableEntity getDefaultValue(final TableEntity table) {
		return ObjectHelper.getDefault(table, getDefaultValue());
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(final int tableNumber) {
		this.tableNumber = IntegerHelper.getDefault(tableNumber);
	}

	public UUID getTableId() {
		return tableId;
	}

	public void setTableId(final UUID tableId) {
		this.tableId = UUIDHelper.getUUIDHelper().getDefault(tableId);
	}
	
}
