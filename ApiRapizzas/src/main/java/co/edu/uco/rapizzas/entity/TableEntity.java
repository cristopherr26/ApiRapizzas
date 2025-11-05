package co.edu.uco.rapizzas.entity;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.IntegerHelper;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class TableEntity{
	
	private UUID id;
	private int number;
	
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
		return number;
	}

	public void setTableNumber(final int tableNumber) {
		this.number = IntegerHelper.getDefault(tableNumber);
	}

	public UUID getTableId() {
		return id;
	}

	public void setTableId(final UUID tableId) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(tableId);
	}
	
}
