package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.data.dao.entity.CustomerDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.CustomerEntity;
import co.edu.uco.rapizzas.entity.IdentificationTypeEntity;

public final class CustomerPostgreSqlDAO extends SqlConnection implements CustomerDAO {

	public CustomerPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(final CustomerEntity entity) {
		
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		final var sql = new StringBuilder();
		sql.append("INSERT INTO \"Cliente\" (\"idCliente\", \"nombre\", \"apellido\", ");
		sql.append("\"activo\", \"idTipoDocumento\", \"numeroDocumento\") ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?)");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setObject(1, entity.getCustomerId());
			preparedStatement.setString(2, entity.getName());
			preparedStatement.setString(3, entity.getLastName());
			preparedStatement.setBoolean(4, entity.isActive());
			preparedStatement.setObject(5, entity.getIdentificationType().getIdentificationTypeId());
			preparedStatement.setString(6, entity.getIdentificationNumber());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_CREATING_CUSTOMER_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_CREATING_CUSTOMER_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_CREATING_CUSTOMER_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_CREATING_CUSTOMER_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}

	@Override
	public List<CustomerEntity> findAll() {
		return findByFilter(new CustomerEntity());
	}

	@Override
	public List<CustomerEntity> findByFilter(final CustomerEntity filterEntity) {
		
		var parametersList = new ArrayList<Object>();
		var sql = createSentenceFindByFilter(filterEntity, parametersList);

		try (var preparedStatement = this.getConnection().prepareStatement(sql)) {
			for (int index = 0; index < parametersList.size(); index++) {
				preparedStatement.setObject(index + 1, parametersList.get(index));
			}
			return executeSentenceFindByFilter(preparedStatement);

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_CUSTOMER_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_CUSTOMER_WHILE_PREPARATION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_CUSTOMER_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_CUSTOMER_WHILE_PREPARATION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}
	
	private String createSentenceFindByFilter(final CustomerEntity filterEntity, final List<Object> parametersList) {

		final var sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append("  C.\"idCliente\" AS \"idC\", ");
		sql.append("  C.\"nombre\" AS \"nombreC\", ");
		sql.append("  C.\"apellido\" AS \"apellidoC\", ");
		sql.append("  C.\"activo\" AS \"activoC\", ");
		sql.append("  TI.\"idTipoDocumento\" AS \"idTI\", ");
		sql.append("  TI.\"nombreTipoDocumento\" AS \"nombreTI\", ");
		sql.append("  C.\"numeroDocumento\" AS \"numeroIdentificacionC\" ");
		sql.append("FROM \"Cliente\" AS C ");
		sql.append("INNER JOIN \"TipoDocumento\" AS TI ON C.\"idTipoDocumento\" = TI.\"idTipoDocumento\" ");

		createWhereClauseFindByFilter(sql, parametersList, filterEntity);

		return sql.toString();
	}

	private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parametersList, final CustomerEntity filterEntity) {

		var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new CustomerEntity());
		final var conditions = new ArrayList<String>();

		addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getName()),
				"C.\"nombre\" = ", filterEntityValidated.getName());

		addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getLastName()),
				"C.\"apellido\" = ", filterEntityValidated.getLastName());

		addCondition(conditions, parametersList, !ObjectHelper.isNull(filterEntityValidated.getIdentificationType()) &&
				!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getIdentificationType().getIdentificationTypeId()),
				"C.\"idTipoDocumento\" = ", filterEntityValidated.getIdentificationType().getIdentificationTypeId());

		addCondition(conditions, parametersList, !filterEntityValidated.isActiveDefaultValue(),
				"C.\"activo\" = ", filterEntityValidated.isActive());

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

	private List<CustomerEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {

		var customers = new ArrayList<CustomerEntity>();

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

				customers.add(customer);
			}

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_CUSTOMER_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_CUSTOMER_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_CUSTOMER_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_CUSTOMER_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}

		return customers;
	}

	@Override
	public void update(final CustomerEntity entity) {
		
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		final var sql = new StringBuilder();
		sql.append("UPDATE \"Cliente\" ");
		sql.append("SET \"nombre\" = ?, \"apellido\" = ?, \"activo\" = ?, ");
		sql.append("\"idTipoDocumento\" = ?, ");
		sql.append("WHERE \"idCliente\" = ?");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setString(1, entity.getName());
			preparedStatement.setString(2, entity.getLastName());
			preparedStatement.setBoolean(3, entity.isActive());
			preparedStatement.setObject(4, entity.getIdentificationType().getIdentificationTypeId());
			preparedStatement.setObject(5, entity.getCustomerId());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_UPDATING_CUSTOMER_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_UPDATING_CUSTOMER_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_UPDATING_CUSTOMER_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_UPDATING_CUSTOMER_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}

	@Override
	public CustomerEntity findById(final UUID id) {
		return findByFilter(new CustomerEntity(id)).stream().findFirst().orElse(new CustomerEntity());
	}

}
