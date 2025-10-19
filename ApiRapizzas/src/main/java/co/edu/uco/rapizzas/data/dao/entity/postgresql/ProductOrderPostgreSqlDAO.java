package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import co.edu.uco.rapizzas.data.dao.entity.ProductOrderDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.ProductOrderEntity;

public final class ProductOrderPostgreSqlDAO extends SqlConnection implements ProductOrderDAO {

	public ProductOrderPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(ProductOrderEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductOrderEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductOrderEntity> findByFilter(ProductOrderEntity filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ProductOrderEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
