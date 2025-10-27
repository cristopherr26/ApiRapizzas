package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.data.dao.entity.EmployeeDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.EmployeeEntity;
import co.edu.uco.rapizzas.entity.IdentificationTypeEntity;

public final class EmployeePostgreSqlDAO extends SqlConnection implements EmployeeDAO {

	public EmployeePostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(final EmployeeEntity entity) {
		
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		final var sql = new StringBuilder();
		sql.append("INSERT INTO \"Empleado\" (\"idEmpleado\", \"nombre\", \"apellido\", \"contrasenaEmpleado\", \"esAdministrador\", \"activo\", \"idTipoDocumento\", \"numeroDocumento\", \"numeroTelefonoMovil\", \"numeroTelefonoMovilConfirmado\") ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
		
		try (var preparedStatement = getConnection().prepareStatement(sql.toString())){
			
			preparedStatement.setObject(1, entity.getEmployeeId());
			preparedStatement.setString(2, entity.getName());
			preparedStatement.setString(3, entity.getLastName());
			preparedStatement.setString(4, entity.getEmployeePassword());
			preparedStatement.setBoolean(5, entity.isAdministrator());
			preparedStatement.setBoolean(6, entity.isActive());
			preparedStatement.setObject(7, entity.getIdentificationType().getIdentificationTypeId());
			preparedStatement.setString(8, entity.getIdentificationNumber());
			preparedStatement.setString(9, entity.getCellPhoneNumber());
			preparedStatement.setBoolean(10, entity.isCellPhoneNumberConfirmed());

			preparedStatement.executeUpdate();
			
			
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_CREATING_EMPLOYEE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_CREATING_EMPLOYEE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_CREATING_EMPLOYEE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_CREATING_EMPLOYEE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}

		
	}

	@Override
	public List<EmployeeEntity> findAll() {
		return findByFilter(new EmployeeEntity());
	}

	@Override
	public List<EmployeeEntity> findByFilter(final EmployeeEntity filterEntity) {
		var parametersList = new ArrayList<Object>();
		var sql = createSentenceFindByFilter(filterEntity, parametersList);

		try (var preparedStatement = this.getConnection().prepareStatement(sql)) {
			for (int index = 0; index < parametersList.size(); index++) {
				preparedStatement.setObject(index + 1, parametersList.get(index));
			}
			return executeSentenceFindByFilter(preparedStatement);

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_EMPLOYEE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_EMPLOYEE_WHILE_PREPARATION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_EMPLOYEE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_EMPLOYEE_WHILE_PREPARATION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}
	
	private String createSentenceFindByFilter(final EmployeeEntity filterEntity, final List<Object> parametersList) {

		final var sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append("  E.\"idEmpleado\" AS \"idE\", ");
		sql.append("  E.\"nombre\" AS \"nombreE\", ");
		sql.append("  E.\"apellido\" AS \"apellidoE\", ");
		sql.append("  E.\"numeroTelefonoMovil\" AS \"numeroTelefonoMovilE\", ");
		sql.append("  E.\"numeroTelefonoMovilConfirmado\" AS \"numeroTelefonoMovilConfirmadoE\", ");
		sql.append("  E.\"activo\" AS \"activoE\", ");
		sql.append("  E.\"esAdministrador\" AS \"esAdministradorE\", ");
		sql.append("  TI.\"idTipoDocumento\" AS \"idTI\", ");
		sql.append("  TI.\"nombreTipoDocumento\" AS \"nombreTI\", ");
		sql.append("  E.\"numeroDocumento\" AS \"numeroIdentificacionE\" ");
		sql.append("FROM \"Empleado\" AS E ");
		sql.append("INNER JOIN \"TipoDocumento\" AS TI ON E.\"idTipoDocumento\" = TI.\"idTipoDocumento\" ");

		createWhereClauseFindByFilter(sql, parametersList, filterEntity);

		return sql.toString();
	}

	private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parametersList, final EmployeeEntity filterEntity) {

		var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new EmployeeEntity());
		final var conditions = new ArrayList<String>();
		
		addCondition(conditions, parametersList,
		!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getEmployeeId()),
				"E.\"idEmpleado\" = ", filterEntityValidated.getEmployeeId());


		addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getName()),
				"E.\"nombre\" = ", filterEntityValidated.getName());

		addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getLastName()),
				"E.\"apellido\" = ", filterEntityValidated.getLastName());

		addCondition(conditions, parametersList,
				!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getIdentificationType().getIdentificationTypeId()),
				"E.\"idTipoDocumento\" = ", filterEntityValidated.getIdentificationType().getIdentificationTypeId());

		addCondition(conditions, parametersList, !filterEntityValidated.isActiveDefaultValue(),
				"E.\"activo\" = ", filterEntityValidated.isActive());
		
		addCondition(conditions, parametersList, !filterEntityValidated.isAdministratorDefaultValue(),
				"E.\"esAdministrador\" = ", filterEntityValidated.isAdministrator());
		
		addCondition(conditions, parametersList, !filterEntityValidated.isCellPhoneNumberConfirmedDefaultValue(),
				"E.\"numeroTelefonoMovilConfirmado\" = ", filterEntityValidated.isCellPhoneNumberConfirmed());

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

	private List<EmployeeEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {

		var employees = new ArrayList<EmployeeEntity>();

		try (var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {

				var identificationType = new IdentificationTypeEntity();
				identificationType.setIdentificationTypeId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idTI")));
				identificationType.setIdentificationTypeName(resultSet.getString("nombreTI"));

				var employee = new EmployeeEntity();
				employee.setEmployeeId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idE")));
				employee.setName(resultSet.getString("nombreE"));
				employee.setLastName(resultSet.getString("apellidoE"));
				employee.setActive(resultSet.getBoolean("activoE"));
				employee.setAdministrator(resultSet.getBoolean("esAdministradorE"));
				employee.setCellPhoneNumber(resultSet.getString("numeroTelefonoMovilE"));
				employee.setCellPhoneNumberConfirmed(resultSet.getBoolean("numeroTelefonoMovilConfirmadoE"));
				employee.setIdentificationType(identificationType);
				employee.setIdentificationNumber(resultSet.getString("numeroIdentificacionE"));

				employees.add(employee);
			}

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_EMPLOYEE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_EMPLOYEE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_EMPLOYEE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_EMPLOYEE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}

		return employees;
	}

	@Override
	public void update(final EmployeeEntity entity) {
		
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		final var sql = new StringBuilder();
		sql.append("UPDATE \"Empleado\" ");
		sql.append("SET \"nombre\" = ?, \"apellido\" = ?, \"activo\" = ?, ");
		sql.append("\"idTipoDocumento\" = ?, \"numeroTelefonoMovil\" = ?, \"numeroTelefonoMovilConfirmado\" = ? ");
		sql.append("\"esAdministrador\" = ?, \"contrasenaEmpleado\" = ? ");
		sql.append("WHERE \"idEmpleado\" = ?");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setString(1, entity.getName());
			preparedStatement.setString(2, entity.getLastName());
			preparedStatement.setBoolean(3, entity.isActive());
			preparedStatement.setObject(4, entity.getIdentificationType().getIdentificationTypeId());
			preparedStatement.setString(5, entity.getCellPhoneNumber());
			preparedStatement.setBoolean(6, entity.isCellPhoneNumberConfirmed());
			preparedStatement.setBoolean(7, entity.isAdministrator());
			preparedStatement.setString(8, entity.getEmployeePassword());
			preparedStatement.setObject(9, entity.getEmployeeId());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_UPDATING_EMPLOYEE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_UPDATING_EMPLOYEE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_UPDATING_EMPLOYEE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_UPDATING_EMPLOYEE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}

}
