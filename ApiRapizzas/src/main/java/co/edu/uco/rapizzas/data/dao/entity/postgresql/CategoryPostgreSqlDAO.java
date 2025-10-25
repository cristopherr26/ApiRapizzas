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
import co.edu.uco.rapizzas.entity.CategoryEntity;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.data.dao.entity.CategoryDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;

public final class CategoryPostgreSqlDAO extends SqlConnection implements CategoryDAO {

	public CategoryPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(CategoryEntity entity) {
		
		final var sql = new StringBuilder();
	    sql.append("INSERT INTO \"Categoria\" (\"idCategoria\", \"nombreCategoria\") ");
	    sql.append("VALUES (?, ?);");

	    try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {

	        preparedStatement.setObject(1, ObjectHelper.getDefault(entity.getIdCategory(), UUIDHelper.getUUIDHelper().generateNewUUID()));
	        preparedStatement.setString(2, TextHelper.getDefaultWithTrim(entity.getNameCategory()));

	        preparedStatement.executeUpdate();

	    } catch (final SQLException exception) {
	        var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_CREATING_CATEGORY_WHILE_EXECUTION.getContent();
	        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_CREATING_CATEGORY_WHILE_EXECUTION.getContent()
	                + exception.getMessage();
	        throw RapizzasException.create(exception, userMessage, technicalMessage);
	    } catch (final Exception exception) {
	        var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_CREATING_CATEGORY_WHILE_EXECUTION.getContent();
	        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_CREATING_CATEGORY_WHILE_EXECUTION.getContent()
	                + exception.getMessage();
	        throw RapizzasException.create(exception, userMessage, technicalMessage);
	    }
		
	}

	@Override
	public List<CategoryEntity> findAll() {
		
		return findByFilter(new CategoryEntity());
	}

	@Override
	public List<CategoryEntity> findByFilter(CategoryEntity filterEntity) {
		
		var parametersList = new ArrayList<Object>();
		var sql = createSentenceFindByFilter(filterEntity, parametersList);
		
		try (var preparedStatement = this.getConnection().prepareStatement(sql)){
			for (int index = 0; index < parametersList.size(); index++) {
				preparedStatement.setObject(index+1, parametersList.get(index));
			}
			return executeSentenceFindByFilter(preparedStatement);	
		} catch (final RapizzasException exception) {
			throw exception;
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_CATEGORY_WHILE_EXECUTION.getContent();
	        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_CATEGORY_WHILE_PREPARATION.getContent() + exception.getMessage();
	        throw RapizzasException.create(exception, userMessage, technicalMessage);
	    } catch (final Exception exception) {
	        var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_CATEGORY_WHILE_EXECUTION.getContent();
	        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_CATEGORY_WHILE_PREPARATION.getContent() + exception.getMessage();
	        throw RapizzasException.create(exception, userMessage, technicalMessage);
	    }
	}
	
	private String createSentenceFindByFilter(final CategoryEntity filterEntity, final List<Object> parametersList) {

		final var sql = new StringBuilder();
		
		sql.append("SELECT ");
		sql.append("  C.\"idCategoria\" as \"idC\", ");
		sql.append("  C.\"nombreCategoria\" as \"nombreC\" ");
		sql.append("  FROM \"Categoria\" as C ");
		
		createWhereClauseFindByFilter(sql, parametersList, filterEntity);
	    
	    return sql.toString();
	}
	
	private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parametersList,
			final CategoryEntity filterEntity) {
		
		var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new CategoryEntity());
		final var conditions = new ArrayList<String>();
		
		addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getNameCategory()),
		"C.\"nombreCategoria\" = ", filterEntityValidated.getNameCategory());
		
		if(!conditions.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(String.join(" AND ", conditions));
		}
	}
	
	private void addCondition(final List<String> conditions, final List<Object> parametersList, final boolean condition,
			final String clause, final Object value) {
		
		if(condition) {
			conditions.add(clause + " ?");
			parametersList.add(value);
		}
		
	}
	
	private List<CategoryEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {
		
		var categories = new ArrayList<CategoryEntity>();
		
		try (var resultSet = preparedStatement.executeQuery()) {

	        while (resultSet.next()) {

		            var category = new CategoryEntity();
		            category.setCategoryId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idC")));
		            category.setNameCategory(resultSet.getString("nombreC"));
		           
		            categories.add(category);
		        }

		    } catch (final SQLException exception) {
		        var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_CATEGORY_WHILE_EXECUTION.getContent();
		        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_CATEGORY_WHILE_EXECUTION.getContent() + exception.getMessage();
		        throw RapizzasException.create(exception, userMessage, technicalMessage);
		    } catch (final Exception exception) {
		        var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_CATEGORY_WHILE_EXECUTION.getContent();
		        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_CATEGORY_WHILE_EXECUTION.getContent() + exception.getMessage();
		        throw RapizzasException.create(exception, userMessage, technicalMessage);
		    }

		    return categories;
		}

	@Override
	public void update(CategoryEntity entity) {
		
		final var sql = new StringBuilder();
	    sql.append("UPDATE \"Categoria\" ");
	    sql.append("SET \"nombreCategoria\" = ? ");
	    sql.append("WHERE \"idCategoria\" = ?;");

	    try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {

	        preparedStatement.setString(1, TextHelper.getDefaultWithTrim(entity.getNameCategory()));
	        preparedStatement.setObject(2, entity.getIdCategory());

	        preparedStatement.executeUpdate();

	    } catch (final SQLException exception) {
	        var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_UPDATING_CATEGORY_WHILE_EXECUTION.getContent();
	        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_UPDATING_CATEGORY_WHILE_EXECUTION.getContent()
	                + exception.getMessage();
	        throw RapizzasException.create(exception, userMessage, technicalMessage);
	    } catch (final Exception exception) {
	        var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_UPDATING_CATEGORY_WHILE_EXECUTION.getContent();
	        var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_UPDATING_CATEGORY_WHILE_EXECUTION.getContent()
	                + exception.getMessage();
	        throw RapizzasException.create(exception, userMessage, technicalMessage);
	    }
		
	}

}
