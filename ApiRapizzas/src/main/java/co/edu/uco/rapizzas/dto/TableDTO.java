package co.edu.uco.rapizzas.dto;

import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.IntegerHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;

public final class TableDTO {
	
	private UUID id;
	private int number;
	
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
	
	public UUID getTableId() {
		return id;
	}
	
	public void setTableId(final UUID tableId) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(tableId);
	}
	
	public int getTableNumber() {
		return number;
	}
	
	public void setTableNumber(final int tableNumber) {
		this.number = IntegerHelper.getDefault(tableNumber);
	}

}
