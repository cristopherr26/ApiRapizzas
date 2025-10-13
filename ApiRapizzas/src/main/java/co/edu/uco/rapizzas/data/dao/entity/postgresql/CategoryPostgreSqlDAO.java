package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.rapizzas.data.dao.entity.CategoryDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.CategoryEntity;

public final class CategoryPostgreSqlDAO extends SqlConnection implements CategoryDAO {

	protected CategoryPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(CategoryEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CategoryEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryEntity> findByFilter(CategoryEntity filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(CategoryEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
