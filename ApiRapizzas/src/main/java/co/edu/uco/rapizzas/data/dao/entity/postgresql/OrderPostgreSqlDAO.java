package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.rapizzas.data.dao.entity.OrderDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.OrderEntity;

public final class OrderPostgreSqlDAO extends SqlConnection implements OrderDAO {

	public OrderPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(OrderEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrderEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderEntity> findByFilter(OrderEntity filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(OrderEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
