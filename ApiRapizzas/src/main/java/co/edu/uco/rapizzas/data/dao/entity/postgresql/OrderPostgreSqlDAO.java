package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.data.dao.entity.OrderDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.CustomerEntity;
import co.edu.uco.rapizzas.entity.CustomerTableEntity;
import co.edu.uco.rapizzas.entity.EmployeeEntity;
import co.edu.uco.rapizzas.entity.IdentificationTypeEntity;
import co.edu.uco.rapizzas.entity.OrderEntity;
import co.edu.uco.rapizzas.entity.StatusEntity;
import co.edu.uco.rapizzas.entity.TableEntity;

public final class OrderPostgreSqlDAO extends SqlConnection implements OrderDAO {

	public OrderPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(final OrderEntity entity) {
		
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		final var sql = new StringBuilder();
		sql.append("INSERT INTO \"Pedido\" (\"idPedido\", total, comentario, \"idEstado\", \"idEmpleado\", \"idClienteMesa\") ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?); ");

		
		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
			
			preparedStatement.setObject(1, entity.getOrderId());
			preparedStatement.setInt(2, entity.getTotal());
			preparedStatement.setString(3, entity.getComment());
			preparedStatement.setObject(4, entity.getStatus().getStatusId());
			preparedStatement.setObject(5, entity.getEmployee().getEmployeeId());
			preparedStatement.setObject(6, entity.getCustomerTable().getCustomerTableId());
			
		}  catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_CREATING_ORDER_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_CREATING_ORDER_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_CREATING_ORDER_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_CREATING_ORDER_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}

	@Override
	public List<OrderEntity> findAll() {
		return findByFilter(new OrderEntity());
	}

	@Override
	public List<OrderEntity> findByFilter(final OrderEntity filterEntity) {
		var parametersList = new ArrayList<Object>();
		var sql = createSentenceFindByFilter(filterEntity, parametersList);

		try (var preparedStatement = this.getConnection().prepareStatement(sql)) {
			for (int index = 0; index < parametersList.size(); index++) {
				preparedStatement.setObject(index + 1, parametersList.get(index));
			}
			return executeSentenceFindByFilter(preparedStatement);

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_ORDER_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_ORDER_WHILE_PREPARATION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_ORDER_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_ORDER_WHILE_PREPARATION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}
	
	private String createSentenceFindByFilter(final OrderEntity filterEntity, final List<Object> parametersList) {

		final var sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append("  P.\"idPedido\" AS \"idP\", ");
		sql.append("  P.\"total\" AS \"totalP\", ");
		sql.append("  P.\"comentario\" AS \"comentarioP\", ");
		sql.append("  ES.\"idEstado\" AS \"idES\", ");
		sql.append("  ES.\"nombreEstado\" AS \"nombreES\", ");

		sql.append("  E.\"idEmpleado\" AS \"idE\", ");
		sql.append("  E.\"nombre\" AS \"nombreE\", ");
		sql.append("  E.\"apellido\" AS \"apellidoE\", ");
		sql.append("  E.\"numeroTelefonoMovil\" AS \"numeroTelefonoMovilE\", ");
		sql.append("  E.\"numeroTelefonoMovilConfirmado\" AS \"numeroTelefonoMovilConfirmadoE\", ");
		sql.append("  E.\"activo\" AS \"activoE\", ");
		sql.append("  E.\"esAdministrador\" AS \"esAdministradorE\", ");
		sql.append("  E.\"numeroDocumento\" AS \"numeroIdentificacionE\", ");
		sql.append("  TI_E.\"idTipoDocumento\" AS \"idTIE\", ");
		sql.append("  TI_E.\"nombreTipoDocumento\" AS \"nombreTIE\", ");

		sql.append("  CT.\"idClienteMesa\" AS \"idCT\", ");
		sql.append("  CT.\"fechaPedido\" AS \"fechaPedidoCT\", ");

		sql.append("  C.\"idCliente\" AS \"idC\", ");
		sql.append("  C.\"nombre\" AS \"nombreC\", ");
		sql.append("  C.\"apellido\" AS \"apellidoC\", ");
		sql.append("  C.\"activo\" AS \"activoC\", ");
		sql.append("  C.\"numeroDocumento\" AS \"numeroIdentificacionC\", ");
		sql.append("  TI_C.\"idTipoDocumento\" AS \"idTIC\", ");
		sql.append("  TI_C.\"nombreTipoDocumento\" AS \"nombreTIC\", ");

		sql.append("  M.\"idMesa\" AS \"idM\", ");
		sql.append("  M.\"numeroMesa\" AS \"numeroM\" ");

		sql.append("FROM \"Pedido\" AS P ");
		sql.append("INNER JOIN \"Estado\" AS ES ON P.\"idEstado\" = ES.\"idEstado\" ");
		sql.append("INNER JOIN \"Empleado\" AS E ON P.\"idEmpleado\" = E.\"idEmpleado\" ");
		sql.append("INNER JOIN \"ClienteMesa\" AS CT ON P.\"idClienteMesa\" = CT.\"idClienteMesa\" ");
		sql.append("INNER JOIN \"Cliente\" AS C ON CT.\"idCliente\" = C.\"idCliente\" ");
		sql.append("INNER JOIN \"Mesa\" AS M ON CT.\"idMesa\" = M.\"idMesa\" ");
		sql.append("INNER JOIN \"TipoDocumento\" AS TI_E ON E.\"idTipoDocumento\" = TI_E.\"idTipoDocumento\" ");
		sql.append("INNER JOIN \"TipoDocumento\" AS TI_C ON C.\"idTipoDocumento\" = TI_C.\"idTipoDocumento\"; ");


		createWhereClauseFindByFilter(sql, parametersList, filterEntity);

		return sql.toString();
	}

	private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parametersList, final OrderEntity filterEntity) {

		var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new OrderEntity());
		final var conditions = new ArrayList<String>();

		addCondition(conditions, parametersList, !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getCustomerTable().getCustomerTableId()),
				"CT.\"idClienteMesa\" = ", filterEntityValidated.getCustomerTable().getCustomerTableId());
		
		addCondition(conditions, parametersList, !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getEmployee().getEmployeeId()),
				"E.\"idEmpleado\" = ", filterEntityValidated.getEmployee().getEmployeeId());

		addCondition(conditions, parametersList, !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getStatus().getStatusId()),
				"ES.\"idEstado\" = ", filterEntityValidated.getStatus().getStatusId());

	

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

	private List<OrderEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {

		var orders = new ArrayList<OrderEntity>();

		try (var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {

				var employeeIdentificationType = new IdentificationTypeEntity();
				employeeIdentificationType.setIdentificationTypeId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idTIE")));
				employeeIdentificationType.setIdentificationTypeName(resultSet.getString("nombreTIE"));

				var employee = new EmployeeEntity();
				employee.setEmployeeId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idE")));
				employee.setName(resultSet.getString("nombreE"));
				employee.setLastName(resultSet.getString("apellidoE"));
				employee.setCellPhoneNumber(resultSet.getString("numeroTelefonoMovilE"));
				employee.setCellPhoneNumberConfirmed(resultSet.getBoolean("numeroTelefonoMovilConfirmadoE"));
				employee.setActive(resultSet.getBoolean("activoE"));
				employee.setAdministrator(resultSet.getBoolean("esAdministradorE"));
				employee.setIdentificationType(employeeIdentificationType);
				employee.setIdentificationNumber(resultSet.getString("numeroIdentificacionE"));

				var customerIdentificationType = new IdentificationTypeEntity();
				customerIdentificationType.setIdentificationTypeId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idTIC")));
				customerIdentificationType.setIdentificationTypeName(resultSet.getString("nombreTIC"));

				var customer = new CustomerEntity();
				customer.setCustomerId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idC")));
				customer.setName(resultSet.getString("nombreC"));
				customer.setLastName(resultSet.getString("apellidoC"));
				customer.setActive(resultSet.getBoolean("activoC"));
				customer.setIdentificationType(customerIdentificationType);
				customer.setIdentificationNumber(resultSet.getString("numeroIdentificacionC"));

				var table = new TableEntity();
				table.setTableId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idM")));
				table.setTableNumber(resultSet.getInt("numeroM"));

				var customerTable = new CustomerTableEntity();
				customerTable.setCustomerTableId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idCT")));
				customerTable.setCustomer(customer);
				customerTable.setTable(table);
				customerTable.setOrderDate(resultSet.getDate("fechaPedidoCT").toLocalDate());

				var state = new StatusEntity();
				state.setStatusId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idES")));
				state.setStatusName(resultSet.getString("nombreES"));

				var order = new OrderEntity();
				order.setOrderId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idP")));
				order.setTotal(resultSet.getInt("totalP"));
				order.setComment(resultSet.getString("comentarioP"));
				order.setStatus(state);
				order.setEmployee(employee);
				order.setCustomerTable(customerTable);

				orders.add(order);
			}

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_ORDER_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_ORDER_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_ORDER_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_ORDER_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}

		return orders;
	}

	@Override
	public void update(final OrderEntity entity) {
		
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		final var sql = new StringBuilder();
		sql.append("UPDATE \"Pedido\" ");
		sql.append("SET \"idClienteMesa\" = ?, \"idEstado\" = ?, \"comentario\" = ? ");
		sql.append("WHERE \"idPedido\" = ?");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setObject(1, entity.getCustomerTable().getCustomerTableId());
			preparedStatement.setObject(2, entity.getStatus().getStatusId());
			preparedStatement.setObject(3, entity.getComment());
			preparedStatement.setObject(4, entity.getOrderId());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_UPDATING_ORDER_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_UPDATING_ORDER_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_UPDATING_ORDER_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_UPDATING_ORDER_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}

}
