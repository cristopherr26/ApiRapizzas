package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.rapizzas.data.dao.entity.CustomerTableDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.CustomerTableEntity;

public final class CustomerTablePostgreSqlDAO extends SqlConnection implements CustomerTableDAO{

	public CustomerTablePostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(CustomerTableEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CustomerTableEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerTableEntity> findByFilter(CustomerTableEntity filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(CustomerTableEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
