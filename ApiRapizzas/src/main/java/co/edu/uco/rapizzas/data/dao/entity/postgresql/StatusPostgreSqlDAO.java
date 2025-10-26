package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.TextHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.data.dao.entity.StatusDAO;
import co.edu.uco.rapizzas.entity.StatusEntity;

public final class StatusPostgreSqlDAO extends SqlConnection implements StatusDAO {

	public StatusPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public List<StatusEntity> findAll() {
		return findByFilter(new StatusEntity());
	}

	@Override
	public List<StatusEntity> findByFilter(final StatusEntity filterEntity) {
		
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
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_STATUS_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_STATUS_WHILE_PREPARATION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_STATUS_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_STATUS_WHILE_PREPARATION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}
	
	private String createSentenceFindByFilter(final StatusEntity filterEntity, final List<Object> parametersList) {

		final var sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append("  S.\"idEstado\" AS \"idS\", ");
		sql.append("  S.\"nombreEstado\" AS \"nombreS\" ");
		sql.append("  FROM \"Estado\" AS S ");

		createWhereClauseFindByFilter(sql, parametersList, filterEntity);

		return sql.toString();
	}

	private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parametersList,
			final StatusEntity filterEntity) {

		var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new StatusEntity());
		final var conditions = new ArrayList<String>();

		addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getStatusName()),
				"S.\"nombreEstado\" = ", filterEntityValidated.getStatusName());

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

	private List<StatusEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {

		var statuses = new ArrayList<StatusEntity>();

		try (var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {

				var status = new StatusEntity();
				status.setStatusId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idS")));
				status.setStatusName(resultSet.getString("nombreS"));

				statuses.add(status);
			}

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_STATUS_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_STATUS_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_STATUS_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_STATUS_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}

		return statuses;
	}

	@Override
	public void update(final StatusEntity entity) {
		
		final var sql = new StringBuilder();
		sql.append("UPDATE \"Estado\" ");
		sql.append("SET \"nombreEstado\" = ? ");
		sql.append("WHERE \"idEstado\" = ?");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setString(1, TextHelper.getDefaultWithTrim(entity.getStatusName()));
			preparedStatement.setObject(2, entity.getStatusId());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_UPDATING_STATUS_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_UPDATING_STATUS_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_UPDATING_STATUS_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_UPDATING_STATUS_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}

}
