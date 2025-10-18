package co.edu.uco.rapizzas.dto;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.IntegerHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class TableDTO {
	
	private UUID tableId;
	private int tableNumber;
	
	public UUID getTableId() {
		return tableId;
	}
	
	public TableDTO() {
		setTableId(UUIDHelper.getUUIDHelper().getDefault());
		setTableNumber(IntegerHelper.getDefault());
	}
	
	public TableDTO(final UUID tableId) {
		setTableId(tableId);
		setTableNumber(IntegerHelper.getDefault());
	}
	
	public TableDTO(final UUID tableId, final int tableNumber) {
		setTableId(tableId);
		setTableNumber(tableNumber);
	}
	
	static TableDTO getDefaultValue() {
		return new TableDTO();
	}
	
	static TableDTO getDefaultValue(final TableDTO table) {
		return ObjectHelper.getDefault(table, getDefaultValue());
	}
	
	public void setTableId(final UUID tableId) {
		this.tableId = ObjectHelper.getDefault(tableId, UUIDHelper.getUUIDHelper().getDefault());
	}
	
	public int getTableNumber() {
		return tableNumber;
	}
	
	public void setTableNumber(final int tableNumber) {
		this.tableNumber = IntegerHelper.getDefault(tableNumber);
	}

}
