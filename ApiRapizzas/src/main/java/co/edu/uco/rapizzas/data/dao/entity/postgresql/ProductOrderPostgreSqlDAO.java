package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.ObjectHelper;
import co.edu.uco.rapizzas.crosscuting.helper.UUIDHelper;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.data.dao.entity.ProductOrderDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.OrderEntity;
import co.edu.uco.rapizzas.entity.ProductEntity;
import co.edu.uco.rapizzas.entity.ProductOrderEntity;

public final class ProductOrderPostgreSqlDAO extends SqlConnection implements ProductOrderDAO {

	public ProductOrderPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(final ProductOrderEntity entity) {
		
		final var sql = new StringBuilder();
		sql.append("INSERT INTO \"ProductoPedido\" (\"idProductoPedido\", \"idProducto\", \"idPedido\", \"cantidad\") ");
		sql.append("VALUES (?, ?, ?, ?)");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setObject(1, entity.getProductOrderId());
			preparedStatement.setObject(2, entity.getProduct().getProductId());
			preparedStatement.setObject(3, entity.getOrder().getOrderId());
			preparedStatement.setInt(4, entity.getAmount());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}

	@Override
	public List<ProductOrderEntity> findAll() {
		return findByFilter(new ProductOrderEntity());
	}

	@Override
	public List<ProductOrderEntity> findByFilter(final ProductOrderEntity filterEntity) {
		
		var parametersList = new ArrayList<Object>();
		var sql = createSentenceFindByFilter(filterEntity, parametersList);

		try (var preparedStatement = this.getConnection().prepareStatement(sql)) {

			for (int index = 0; index < parametersList.size(); index++) {
				preparedStatement.setObject(index + 1, parametersList.get(index));
			}

			return executeSentenceFindByFilter(preparedStatement);

		} catch (final SQLException exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}
	
	private String createSentenceFindByFilter(final ProductOrderEntity filterEntity, final List<Object> parametersList) {

		final var sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append("  PP.\"idProductoPedido\" AS \"idPP\", ");
		sql.append("  PP.\"cantidad\" AS \"cantidadPP\", ");
		sql.append("  P.\"idProducto\" AS \"idP\", ");
		sql.append("  P.\"nombreProducto\" AS \"nombreP\", ");
		sql.append("  P.\"precioProducto\" AS \"precioP\", ");
		sql.append("  PE.\"idPedido\" AS \"idPe\", ");
		sql.append("  PE.\"total\" AS \"totalPe\" ");
		sql.append("FROM \"ProductoPedido\" AS PP ");
		sql.append("INNER JOIN \"Producto\" AS P ON PP.\"idProducto\" = P.\"idProducto\" ");
		sql.append("INNER JOIN \"Pedido\" AS PE ON PP.\"idPedido\" = PE.\"idPedido\" ");

		createWhereClauseFindByFilter(sql, parametersList, filterEntity);

		return sql.toString();
	}

	private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parametersList, final ProductOrderEntity filterEntity) {

		var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new ProductOrderEntity());
		final var conditions = new ArrayList<String>();

		addCondition(conditions, parametersList,
				!ObjectHelper.isNull(filterEntityValidated.getProduct()) &&
				!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getProduct().getProductId()),
				"PP.\"idProducto\" = ", filterEntityValidated.getProduct().getProductId());

		addCondition(conditions, parametersList,
				!ObjectHelper.isNull(filterEntityValidated.getOrder()) &&
				!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getOrder().getOrderId()),
				"PP.\"idPedido\" = ", filterEntityValidated.getOrder().getOrderId());

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

	private List<ProductOrderEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {

		var productOrders = new ArrayList<ProductOrderEntity>();

		try (var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {

				var product = new ProductEntity();
				product.setProductId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idP")));
				product.setProductName(resultSet.getString("nombreP"));
				product.setPrice(resultSet.getInt("precioP"));

				var order = new OrderEntity();
				order.setOrderId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPe")));
				order.setTotal(resultSet.getInt("totalPe"));

				var productOrder = new ProductOrderEntity();
				productOrder.setProductOrderId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPP")));
				productOrder.setAmount(resultSet.getInt("cantidadPP"));
				productOrder.setProduct(product);
				productOrder.setOrder(order);

				productOrders.add(productOrder);
			}

		} catch (final SQLException exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}

		return productOrders;
	}

	@Override
	public void update(final ProductOrderEntity entity) {
		
		final var sql = new StringBuilder();
		sql.append("UPDATE \"ProductoPedido\" ");
		sql.append("SET \"idProducto\" = ?, \"idPedido\" = ?, \"cantidad\" = ? ");
		sql.append("WHERE \"idProductoPedido\" = ?");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setObject(1, entity.getProduct().getProductId());
			preparedStatement.setObject(2, entity.getOrder().getOrderId());
			preparedStatement.setInt(3, entity.getAmount());
			preparedStatement.setObject(4, entity.getProductOrderId());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}

	@Override
	public void delete(final UUID id) {
		
		final var sql = new StringBuilder();
		sql.append("DELETE FROM \"ProductoPedido\" WHERE \"idProductoPedido\" = ?");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setObject(1, id);
			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}

}
