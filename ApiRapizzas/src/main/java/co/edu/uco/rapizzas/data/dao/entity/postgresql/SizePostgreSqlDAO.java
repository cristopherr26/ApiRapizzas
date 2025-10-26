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
import co.edu.uco.rapizzas.data.dao.entity.SizeDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.SizeEntity;

public final class SizePostgreSqlDAO extends SqlConnection implements SizeDAO {

	public SizePostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(final SizeEntity entity) {
		
		final var sql = new StringBuilder();
		sql.append("INSERT INTO \"Tamano\" (\"idTamano\", \"nombreTamano\") ");
		sql.append("VALUES (?, ?)");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setObject(1, ObjectHelper.getDefault(entity.getSizeId(), UUIDHelper.getUUIDHelper().generateNewUUID()));
			preparedStatement.setString(2, TextHelper.getDefaultWithTrim(entity.getNameSize()));

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_CREATING_SIZE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_CREATING_SIZE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_CREATING_SIZE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_CREATING_SIZE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}

	@Override
	public List<SizeEntity> findAll() {
		return findByFilter(new SizeEntity());
	}

	@Override
	public List<SizeEntity> findByFilter(final SizeEntity filterEntity) {
		
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
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_SIZE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_SIZE_WHILE_PREPARATION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_SIZE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_SIZE_WHILE_PREPARATION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}
	
	private String createSentenceFindByFilter(final SizeEntity filterEntity, final List<Object> parametersList) {

		final var sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append("  T.\"idTamano\" AS \"idT\", ");
		sql.append("  T.\"nombreTamano\" AS \"nombreT\" ");
		sql.append("  FROM \"Tamano\" AS T ");

		createWhereClauseFindByFilter(sql, parametersList, filterEntity);

		return sql.toString();
	}

	private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parametersList,
			final SizeEntity filterEntity) {

		var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new SizeEntity());
		final var conditions = new ArrayList<String>();

		addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getNameSize()),
				"T.\"nombreTamano\" = ", filterEntityValidated.getNameSize());

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

	private List<SizeEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {

		var sizes = new ArrayList<SizeEntity>();

		try (var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {

				var size = new SizeEntity();
				size.setSizeId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idT")));
				size.setNameSize(resultSet.getString("nombreT"));

				sizes.add(size);
			}

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_SIZE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_SIZE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_SIZE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_SIZE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}

		return sizes;
	}

	@Override
	public void update(final SizeEntity entity) {
		
		final var sql = new StringBuilder();
		sql.append("UPDATE \"Tamano\" ");
		sql.append("SET \"nombreTamano\" = ? ");
		sql.append("WHERE \"idTamano\" = ?");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setString(1, TextHelper.getDefaultWithTrim(entity.getNameSize()));
			preparedStatement.setObject(2, entity.getSizeId());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_UPDATING_SIZE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_UPDATING_SIZE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_UPDATING_SIZE_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_UPDATING_SIZE_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
			}

		}
	
	}
