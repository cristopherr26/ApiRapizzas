package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.rapizzas.data.dao.entity.ProductDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.ProductEntity;

public final class ProductPostgreSqlDAO extends SqlConnection implements ProductDAO {

	protected ProductPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(ProductEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductEntity> findByFilter(ProductEntity filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ProductEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
