package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.data.dao.entity.IdentificationTypeDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.IdentificationTypeEntity;

public final class IdentificationTypePostgreSqlDAO extends SqlConnection implements IdentificationTypeDAO {

	public IdentificationTypePostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(final IdentificationTypeEntity entity) {
		
		final var sql = new StringBuilder();
		sql.append("INSERT INTO \"TipoDocumento\" (\"idTipoDocumento\", \"nombreTipoDocumento\") ");
		sql.append("VALUES (?, ?)");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setObject(1, ObjectHelper.getDefault(entity.getIdentificationTypeId(), UUIDHelper.getUUIDHelper().generateNewUUID()));
			preparedStatement.setString(2, TextHelper.getDefaultWithTrim(entity.getName()));

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_CREATING_IDENTIFICATIONTYPE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_CREATING_IDENTIFICATIONTYPE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_CREATING_IDENTIFICATIONTYPE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_CREATING_IDENTIFICATIONTYPE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}

	@Override
	public List<IdentificationTypeEntity> findAll() {
		return findByFilter(new IdentificationTypeEntity());
	}

	@Override
	public List<IdentificationTypeEntity> findByFilter(final IdentificationTypeEntity filterEntity) {
		
		var parametersList = new ArrayList<Object>();
		var sql = createSentenceFindByFilter(filterEntity, parametersList);

		try (var preparedStatement = this.getConnection().prepareStatement(sql)) {

			for (int index = 0; index < parametersList.size(); index++) {
				preparedStatement.setObject(index + 1, parametersList.get(index));
			}
			return executeSentenceFindByFilter(preparedStatement);

		} catch (final RapizzasException exception) {
			throw exception;
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_IDENTIFICATIONTYPE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_IDENTIFICATIONTYPE_WHILE_PREPARATION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_IDENTIFICATIONTYPE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_IDENTIFICATIONTYPE_WHILE_PREPARATION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}
	
	private String createSentenceFindByFilter(final IdentificationTypeEntity filterEntity, final List<Object> parametersList) {

		final var sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append("  TI.\"idTipoDocumento\" AS \"idTI\", ");
		sql.append("  TI.\"nombreTipoDocumento\" AS \"nombreTI\" ");
		sql.append("FROM \"TipoDocumento\" AS TI ");

		createWhereClauseFindByFilter(sql, parametersList, filterEntity);

		return sql.toString();
	}

	private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parametersList,
			final IdentificationTypeEntity filterEntity) {

		var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new IdentificationTypeEntity());
		final var conditions = new ArrayList<String>();
		
		addCondition(conditions, parametersList, !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getIdentificationTypeId()),
				"TI.\"idTipoDocumento\" = ", filterEntityValidated.getIdentificationTypeId());

		addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getName()),
				"TI.\"nombreTipoDocumento\" = ", filterEntityValidated.getName());

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

	private List<IdentificationTypeEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {

		var identificationTypes = new ArrayList<IdentificationTypeEntity>();

		try (var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {

				var identificationType = new IdentificationTypeEntity();
				identificationType.setIdentificationTypeId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idTI")));
				identificationType.setIdentificationTypeName(resultSet.getString("nombreTI"));

				identificationTypes.add(identificationType);
			}

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_IDENTIFICATIONTYPE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_IDENTIFICATIONTYPE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_IDENTIFICATIONTYPE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_IDENTIFICATIONTYPE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}

		return identificationTypes;
	}

	@Override
	public void update(final IdentificationTypeEntity entity) {
		
		final var sql = new StringBuilder();
		sql.append("UPDATE \"TipoDocumento\" ");
		sql.append("SET \"nombreTipoDocumento\" = ? ");
		sql.append("WHERE \"idTipoDocumento\" = ?");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
																					
			preparedStatement.setString(1, TextHelper.getDefaultWithTrim(entity.getName()));
			preparedStatement.setObject(2, entity.getIdentificationTypeId());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_UPDATING_IDENTIFICATIONTYPE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_UPDATING_IDENTIFICATIONTYPE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_UPDATING_IDENTIFICATIONTYPE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_UPDATING_IDENTIFICATIONTYPE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		
		}
	}

	@Override
	public IdentificationTypeEntity findById(final UUID id) {
		return findByFilter(new IdentificationTypeEntity(id)).stream().findFirst().orElse(new IdentificationTypeEntity());
	}
}

