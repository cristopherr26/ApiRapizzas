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
import co.edu.uco.rapizzas.data.dao.entity.PaymentDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.CustomerEntity;
import co.edu.uco.rapizzas.entity.CustomerTableEntity;
import co.edu.uco.rapizzas.entity.EmployeeEntity;
import co.edu.uco.rapizzas.entity.IdentificationTypeEntity;
import co.edu.uco.rapizzas.entity.OrderEntity;
import co.edu.uco.rapizzas.entity.PaymentEntity;
import co.edu.uco.rapizzas.entity.StatusEntity;
import co.edu.uco.rapizzas.entity.TableEntity;

public final class PaymentPostgreSqlDAO extends SqlConnection implements PaymentDAO {

	public PaymentPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(final PaymentEntity entity) {
		
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		final var sql = new StringBuilder();
		sql.append("INSERT INTO \"Pago\" (\"idPago\", \"idPedido\", \"fechaCobro\", \"idEmpleado\") ");
		sql.append("VALUES (?, ?, ?, ?);");
		
		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
			
			preparedStatement.setObject(1, entity.getPaymentId());
			preparedStatement.setObject(2, entity.getOrder().getOrderId());
			preparedStatement.setObject(3, entity.getCollectionDate());
			preparedStatement.setObject(4, entity.getEmployee().getEmployeeId());
			
		}  catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_CREATING_PAYMENT_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_CREATING_PAYMENT_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_CREATING_PAYMENT_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_CREATING_PAYMENT_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}

	@Override
	public List<PaymentEntity> findAll() {
		return findByFilter(new PaymentEntity());
	}

	@Override
	public List<PaymentEntity> findByFilter(PaymentEntity filterEntity) {
		var parametersList = new ArrayList<Object>();
		var sql = createSentenceFindByFilter(filterEntity, parametersList);

		try (var preparedStatement = this.getConnection().prepareStatement(sql)) {
			for (int index = 0; index < parametersList.size(); index++) {
				preparedStatement.setObject(index + 1, parametersList.get(index));
			}
			return executeSentenceFindByFilter(preparedStatement);

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_PAYMENT_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_PAYMENT_WHILE_PREPARATION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_PAYMENT_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_PAYMENT_WHILE_PREPARATION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}
	
	private String createSentenceFindByFilter(final PaymentEntity filterEntity, final List<Object> parametersList) {

		final var sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append("  PA.\"idPago\" AS \"idPA\", ");
		sql.append("  PA.\"fechaCobro\" AS \"fechaCobroPA\", ");

		sql.append("  P.\"idPedido\" AS \"idP\", ");
		sql.append("  P.\"total\" AS \"totalP\", ");
		sql.append("  P.\"comentario\" AS \"comentarioP\", ");

		sql.append("  ES.\"idEstado\" AS \"idES\", ");
		sql.append("  ES.\"nombreEstado\" AS \"nombreES\", ");

		sql.append("  E_T.\"idEmpleado\" AS \"idET\", ");
		sql.append("  E_T.\"nombre\" AS \"nombreET\", ");
		sql.append("  E_T.\"apellido\" AS \"apellidoET\", ");
		sql.append("  E_T.\"numeroTelefonoMovil\" AS \"numeroTelefonoMovilET\", ");
		sql.append("  E_T.\"numeroTelefonoMovilConfirmado\" AS \"numeroTelefonoMovilConfirmadoET\", ");
		sql.append("  E_T.\"activo\" AS \"activoET\", ");
		sql.append("  E_T.\"esAdministrador\" AS \"esAdministradorET\", ");
		sql.append("  E_T.\"numeroDocumento\" AS \"numeroIdentificacionET\", ");
		sql.append("  TI_ET.\"idTipoDocumento\" AS \"idTIET\", ");
		sql.append("  TI_ET.\"nombreTipoDocumento\" AS \"nombreTIET\", ");

		sql.append("  E_C.\"idEmpleado\" AS \"idEC\", ");
		sql.append("  E_C.\"nombre\" AS \"nombreEC\", ");
		sql.append("  E_C.\"apellido\" AS \"apellidoEC\", ");
		sql.append("  E_C.\"numeroTelefonoMovil\" AS \"numeroTelefonoMovilEC\", ");
		sql.append("  E_C.\"numeroTelefonoMovilConfirmado\" AS \"numeroTelefonoMovilConfirmadoEC\", ");
		sql.append("  E_C.\"activo\" AS \"activoEC\", ");
		sql.append("  E_C.\"esAdministrador\" AS \"esAdministradorEC\", ");
		sql.append("  E_C.\"numeroDocumento\" AS \"numeroIdentificacionEC\", ");
		sql.append("  TI_EC.\"idTipoDocumento\" AS \"idTIEC\", ");
		sql.append("  TI_EC.\"nombreTipoDocumento\" AS \"nombreTIEC\", ");

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
		sql.append("INNER JOIN \"Empleado\" AS E_T ON P.\"idEmpleado\" = E_T.\"idEmpleado\" ");
		sql.append("INNER JOIN \"ClienteMesa\" AS CT ON P.\"idClienteMesa\" = CT.\"idClienteMesa\" ");
		sql.append("INNER JOIN \"Cliente\" AS C ON CT.\"idCliente\" = C.\"idCliente\" ");
		sql.append("INNER JOIN \"Mesa\" AS M ON CT.\"idMesa\" = M.\"idMesa\" ");
		sql.append("INNER JOIN \"TipoDocumento\" AS TI_ET ON E_T.\"idTipoDocumento\" = TI_ET.\"idTipoDocumento\" ");
		sql.append("INNER JOIN \"TipoDocumento\" AS TI_C ON C.\"idTipoDocumento\" = TI_C.\"idTipoDocumento\" ");
		sql.append("INNER JOIN \"Pago\" AS PA ON P.\"idPedido\" = PA.\"idPedido\" ");
		sql.append("INNER JOIN \"Empleado\" AS E_C ON PA.\"idEmpleado\" = E_C.\"idEmpleado\" "); 
		sql.append("INNER JOIN \"TipoDocumento\" AS TI_EC ON E_C.\"idTipoDocumento\" = TI_EC.\"idTipoDocumento\";");



		createWhereClauseFindByFilter(sql, parametersList, filterEntity);

		return sql.toString();
	}

	private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parametersList, final PaymentEntity filterEntity) {

		var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new PaymentEntity());
		final var conditions = new ArrayList<String>();

		addCondition(conditions, parametersList, !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getEmployee().getEmployeeId()),
				"E_C.\"idEmpleado\" = ", filterEntityValidated.getEmployee().getEmployeeId());
		
		addCondition(conditions, parametersList, !DateHelper.isDefaultDate(filterEntityValidated.getCollectionDate()),
				"PA.\"fechaCobro\" = ", filterEntityValidated.getCollectionDate());
	

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

	private List<PaymentEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {

		var payments = new ArrayList<PaymentEntity>();

		try (var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {

				var orderEmployeeIdentificationType = new IdentificationTypeEntity();
				orderEmployeeIdentificationType.setIdentificationTypeId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idTIET")));
				orderEmployeeIdentificationType.setIdentificationTypeName(resultSet.getString("nombreTIET"));

				var orderEmployee = new EmployeeEntity();
				orderEmployee.setEmployeeId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idET")));
				orderEmployee.setName(resultSet.getString("nombreET"));
				orderEmployee.setLastName(resultSet.getString("apellidoET"));
				orderEmployee.setCellPhoneNumber(resultSet.getString("numeroTelefonoMovilET"));
				orderEmployee.setCellPhoneNumberConfirmed(resultSet.getBoolean("numeroTelefonoMovilConfirmadoET"));
				orderEmployee.setActive(resultSet.getBoolean("activoET"));
				orderEmployee.setAdministrator(resultSet.getBoolean("esAdministradorET"));
				orderEmployee.setIdentificationType(orderEmployeeIdentificationType);
				orderEmployee.setIdentificationNumber(resultSet.getString("numeroIdentificacionET"));

				var paymentEmployeeIdentificationType = new IdentificationTypeEntity();
				paymentEmployeeIdentificationType.setIdentificationTypeId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idTIEC")));
				paymentEmployeeIdentificationType.setIdentificationTypeName(resultSet.getString("nombreTIEC"));

				var paymentEmployee = new EmployeeEntity();
				paymentEmployee.setEmployeeId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idEC")));
				paymentEmployee.setName(resultSet.getString("nombreEC"));
				paymentEmployee.setLastName(resultSet.getString("apellidoEC"));
				paymentEmployee.setCellPhoneNumber(resultSet.getString("numeroTelefonoMovilEC"));
				paymentEmployee.setCellPhoneNumberConfirmed(resultSet.getBoolean("numeroTelefonoMovilConfirmadoEC"));
				paymentEmployee.setActive(resultSet.getBoolean("activoEC"));
				paymentEmployee.setAdministrator(resultSet.getBoolean("esAdministradorEC"));
				paymentEmployee.setIdentificationType(paymentEmployeeIdentificationType);
				paymentEmployee.setIdentificationNumber(resultSet.getString("numeroIdentificacionEC"));

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
				order.setEmployee(orderEmployee); 
				order.setCustomerTable(customerTable);

				var payment = new PaymentEntity();
				payment.setPaymentId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPA")));
				payment.setOrder(order);
				payment.setCollectionDate(resultSet.getDate("fechaCobroPA").toLocalDate());
				payment.setEmployee(paymentEmployee); 


				payments.add(payment);
			}

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_PAYMENT_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_PAYMENT_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_PAYMENT_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_PAYMENT_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}

		return payments;
	}

}
