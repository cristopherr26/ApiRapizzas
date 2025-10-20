package co.edu.uco.rapizzas.data.dao.entity;

import java.sql.Connection;

import co.edu.uco.rapizzas.crosscuting.helper.SqlConnectionHelper;


public abstract class SqlConnection {

	private Connection connection;
	
	protected SqlConnection (final Connection connection) {
		setConnection(connection);
	}

	public Connection getConnection() {
		return connection;
	}

	private void setConnection(final Connection connection) {
		SqlConnectionHelper.ensureConnectionIsOpen(connection);
		this.connection = connection;
	}
	
}
