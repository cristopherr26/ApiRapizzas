package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.rapizzas.data.dao.entity.EmployeeDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.EmployeeEntity;

public final class EmployeePostgreSqlDAO extends SqlConnection implements EmployeeDAO {

	protected EmployeePostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(EmployeeEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EmployeeEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeEntity> findByFilter(EmployeeEntity filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(EmployeeEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
