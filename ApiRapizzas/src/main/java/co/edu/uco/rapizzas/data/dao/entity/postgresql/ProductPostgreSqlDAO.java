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
import co.edu.uco.rapizzas.data.dao.entity.ProductDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.CategoryEntity;
import co.edu.uco.rapizzas.entity.ProductEntity;
import co.edu.uco.rapizzas.entity.SizeEntity;


public final class ProductPostgreSqlDAO extends SqlConnection implements ProductDAO {

	public ProductPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(final ProductEntity entity) {
		
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		final var sql = new StringBuilder();
		sql.append("INSERT INTO \"Producto\" (\"idProducto\", \"nombreProducto\", \"precioProducto\", \"categoria\", \"tamano\") ");
		sql.append("VALUES (?, ?, ?, ?, ?)");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setObject(1, entity.getProductId());
			preparedStatement.setString(2, entity.getProductName());
			preparedStatement.setInt(3, entity.getPrice());
			preparedStatement.setObject(4, entity.getCategory().getIdCategory());
			preparedStatement.setObject(5, entity.getSize().getSizeId());


			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_CREATING_PRODUCT_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_CREATING_PRODUCT_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_CREATING_PRODUCT_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_CREATING_PRODUCT_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}

	@Override
	public List<ProductEntity> findAll() {
		return findByFilter(new ProductEntity());
	}

	@Override
	public List<ProductEntity> findByFilter(final ProductEntity filterEntity) {
		
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
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_PRODUCT_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_PRODUCT_WHILE_PREPARATION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_PRODUCT_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_PRODUCT_WHILE_PREPARATION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}
	
	private String createSentenceFindByFilter(final ProductEntity filterEntity, final List<Object> parametersList) {

		final var sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append("  P.\"idProducto\" AS \"idP\", ");
		sql.append("  P.\"nombreProducto\" AS \"nombreP\", ");
		sql.append("  P.\"precio\" AS \"precioP\", ");
		sql.append("  C.\"idCategoria\" AS \"idC\", ");
		sql.append("  C.\"nombreCategoria\" AS \"nombreC\", ");
		sql.append("  T.\"idTamano\" AS \"idT\", ");
		sql.append("  T.\"nombreTamano\" AS \"nombreT\" ");
		sql.append("FROM \"Producto\" AS P ");
		sql.append("INNER JOIN \"Categoria\" AS C ON P.\"categoria\" = C.\"idCategoria\" ");
		sql.append("INNER JOIN \"Tamano\" AS T ON P.\"tamano\" = T.\"idTamano\" ");

		createWhereClauseFindByFilter(sql, parametersList, filterEntity);

		return sql.toString();
	}

	private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parametersList,
			final ProductEntity filterEntity) {

		var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new ProductEntity());
		final var conditions = new ArrayList<String>();

		addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getProductName()),
				"P.\"nombreProducto\" = ", filterEntityValidated.getProductName());
				
		addCondition(conditions, parametersList, filterEntityValidated.getPrice() > 0,
				"P.\"precio\" = ", filterEntityValidated.getPrice());
		
		addCondition(conditions, parametersList, !ObjectHelper.isNull(filterEntityValidated.getCategory()) && 
				!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getCategory().getIdCategory()),
				"P.\"categoria\" = ", filterEntityValidated.getCategory().getIdCategory());
		
		addCondition(conditions, parametersList, !ObjectHelper.isNull(filterEntityValidated.getSize()) && 
				!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getSize().getSizeId()),
				"P.\"tamano\" = ", filterEntityValidated.getSize().getSizeId());
		
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

	private List<ProductEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {

		var products = new ArrayList<ProductEntity>();

		try (var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {

				var category = new CategoryEntity();
				category.setCategoryId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idC")));
				category.setNameCategory(resultSet.getString("nombreC"));

				var size = new SizeEntity();
				size.setSizeId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idT")));
				size.setNameSize(resultSet.getString("nombreT"));

				var product = new ProductEntity();
				product.setProductId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idP")));
				product.setProductName(resultSet.getString("nombreP"));
				product.setPrice(resultSet.getInt("precioP"));
				product.setCategory(category);
				product.setSize(size);

				products.add(product);
			}

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_PRODUCT_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_PRODUCT_WHILE_EXECUTION.getContent()
					+ exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_PRODUCT_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_PRODUCT_WHILE_EXECUTION.getContent()
					+ exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}

		return products;
		
	}

	@Override
	public void update(final ProductEntity entity) {
		
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		final var sql = new StringBuilder();
		
		sql.append("UPDATE \"Producto\" ");
		sql.append("SET \"nombreProducto\" = ?, \"precio\" = ?, \"categoria\" = ?, \"tamano\" = ? ");
		sql.append("WHERE \"idProducto\" = ?");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setString(1, entity.getProductName());
			preparedStatement.setInt(2, entity.getPrice());
			preparedStatement.setObject(3, entity.getCategory().getIdCategory());
			preparedStatement.setObject(4, entity.getSize().getSizeId());
			preparedStatement.setObject(5, entity.getProductId());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_UPDATING_PRODUCT_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_UPDATING_PRODUCT_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_UPDATING_PRODUCT_WHILE_EXECUTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_UPDATING_PRODUCT_WHILE_EXECUTION.getContent() + exception.getMessage();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}

	@Override
	public ProductEntity findById(final UUID id) {
		return findByFilter(new ProductEntity(id)).stream().findFirst().orElse(new ProductEntity());
	}

}
