package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.DateHelper;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.data.dao.entity.CustomerTableDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.CustomerEntity;
import co.edu.uco.rapizzas.entity.CustomerTableEntity;
import co.edu.uco.rapizzas.entity.IdentificationTypeEntity;
import co.edu.uco.rapizzas.entity.TableEntity;

public final class CustomerTablePostgreSqlDAO extends SqlConnection implements CustomerTableDAO{

	public CustomerTablePostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(final CustomerTableEntity entity) {
		
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		final var sql = new StringBuilder();
		sql.append("INSERT INTO \"ClienteMesa\" (\"idClienteMesa\", \"idCliente\", \"idMesa\", \"fechaPedido\") ");
		sql.append("VALUES (?, ?, ?, ?) ");
		
		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
			
			preparedStatement.setObject(1, entity.getCustomerTableId());
			preparedStatement.setObject(2, entity.getCustomer().getCustomerId());
			preparedStatement.setObject(3, entity.getTable().getTableId());
			preparedStatement.setObject(4, entity.getOrderDate());
			
		}  catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_CREATING_CUSTOMERTABLE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_CREATING_CUSTOMERTABLE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_CREATING_CUSTOMERTABLE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_CREATING_CUSTOMERTABLE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}

		
	}

	@Override
	public List<CustomerTableEntity> findAll() {
		return findByFilter(new CustomerTableEntity());
	}

	@Override
	public List<CustomerTableEntity> findByFilter(final CustomerTableEntity filterEntity) {
		
		
		var parametersList = new ArrayList<Object>();
		var sql = createSentenceFindByFilter(filterEntity, parametersList);

		try (var preparedStatement = this.getConnection().prepareStatement(sql)) {
			for (int index = 0; index < parametersList.size(); index++) {
				preparedStatement.setObject(index + 1, parametersList.get(index));
			}
			return executeSentenceFindByFilter(preparedStatement);

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_CUSTOMERTABLE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_CUSTOMERTABLE_WHILE_PREPARATION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_CUSTOMERTABLE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_CUSTOMERTABLE_WHILE_PREPARATION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}
	
	private String createSentenceFindByFilter(final CustomerTableEntity filterEntity, final List<Object> parametersList) {

		final var sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append("  CT.\"idClienteMesa\" AS \"idCT\", ");
		sql.append("  CT.\"fechaPedido\" AS \"fechaPedidoCT\", ");
		sql.append("  TI.\"idTipoDocumento\" AS \"idTI\", ");
		sql.append("  TI.\"nombreTipoDocumento\" AS \"nombreTI\", ");
		sql.append("  C.\"idCliente\" AS \"idC\", ");
		sql.append("  C.\"nombre\" AS \"nombreC\", ");
		sql.append("  C.\"apellido\" AS \"apellidoC\", ");
		sql.append("  C.\"activo\" AS \"activoC\", ");
		sql.append("  TI.\"idTipoDocumento\" AS \"idTI\", ");
		sql.append("  TI.\"nombreTipoDocumento\" AS \"nombreTI\", ");
		sql.append("  C.\"numeroDocumento\" AS \"numeroIdentificacionC\" ");
		sql.append("  M.\"idMesa\" AS \"idM\" ");
		sql.append("  M.\"numeroMesa\" AS \"numeroM\" ");
		sql.append("FROM \"ClienteMesa\" AS CT ");
		sql.append("INNER JOIN \"Cliente\" AS C ON CT.\"idCliente\" = C.\"idCliente\" ");
		sql.append("INNER JOIN \"Mesa\" AS M ON CT.\"idMesa\" = M.\"idMesa\" ");
		sql.append("INNER JOIN \"TipoDocumento\" AS TI ON C.\"idTipoDocumento\" = TI.\"idTipoDocumento\" ");

		createWhereClauseFindByFilter(sql, parametersList, filterEntity);

		return sql.toString();
	}

	private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parametersList, final CustomerTableEntity filterEntity) {

		var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new CustomerTableEntity());
		final var conditions = new ArrayList<String>();

		addCondition(conditions, parametersList, !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getCustomer().getCustomerId()),
				"CT.\"idCliente\" = ", filterEntityValidated.getCustomer().getCustomerId());
		
		addCondition(conditions, parametersList, !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getTable().getTableId()),
				"CT.\"idMesa\" = ", filterEntityValidated.getTable().getTableId());

		addCondition(conditions, parametersList, !DateHelper.isDefaultDate(filterEntityValidated.getOrderDate()),
				"CT.\"fechaPedido\" = ", filterEntityValidated.getOrderDate());

	

		if (!conditions.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(String.join(" AND ", conditions));
		}
	}

	private void addCondition(final List<String> conditions, final List<Object> parametersList, final boolean condition,
			final String clause, final Object value) {
		if (condition) {
			conditions.add(clause + " ?");
			parametersList.add(value);
		}
	}

	private List<CustomerTableEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {

		var customerTables = new ArrayList<CustomerTableEntity>();

		try (var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {

				var identificationType = new IdentificationTypeEntity();
				identificationType.setIdentificationTypeId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idTI")));
				identificationType.setIdentificationTypeName(resultSet.getString("nombreTI"));
				
				var customer = new CustomerEntity();
				customer.setCustomerId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idC")));
				customer.setName(resultSet.getString("nombreC"));
				customer.setLastName(resultSet.getString("apellidoC"));
				customer.setActive(resultSet.getBoolean("activoC"));
				customer.setIdentificationType(identificationType);
				customer.setIdentificationNumber(resultSet.getString("numeroIdentificacionC"));
				
				var table = new TableEntity();
				table.setTableId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idM")));
				table.setTableNumber(resultSet.getInt("numeroM"));

				var customerTable = new CustomerTableEntity();
				customerTable.setCustomerTableId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idCT")));
				customerTable.setCustomer(customer);
				customerTable.setTable(table);
				customerTable.setOrderDate(resultSet.getDate("fechaPedidoCT").toLocalDate());


				customerTables.add(customerTable);
			}

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_CUSTOMERTABLE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_CUSTOMERTABLE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_CUSTOMERTABLE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_CUSTOMERTABLE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}

		return customerTables;
	}

	@Override
	public void update(final CustomerTableEntity entity) {
		
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		final var sql = new StringBuilder();
		sql.append("UPDATE \"ClienteMesa\" ");
		sql.append("SET \"idCliente\" = ?, \"idMesa\" = ? ");
		sql.append("WHERE \"idClienteMesa\" = ?");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setObject(1, entity.getCustomer().getCustomerId());
			preparedStatement.setObject(2, entity.getTable().getTableId());
			preparedStatement.setObject(3, entity.getCustomerTableId());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_UPDATING_CUSTOMERTABLE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_UPDATING_CUSTOMERTABLE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_UPDATING_CUSTOMERTABLE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_UPDATING_CUSTOMERTABLE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}

}
