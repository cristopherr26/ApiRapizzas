package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.rapizzas.data.dao.entity.CustomerDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.CustomerEntity;

public final class CustomerPostgreSqlDAO extends SqlConnection implements CustomerDAO {

	public CustomerPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(CustomerEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CustomerEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerEntity> findByFilter(CustomerEntity filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(CustomerEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
