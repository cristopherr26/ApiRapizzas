package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.rapizzas.data.dao.entity.SizeDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.SizeEntity;

public final class SizePostgreSqlDAO extends SqlConnection implements SizeDAO {

	protected SizePostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(SizeEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SizeEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SizeEntity> findByFilter(SizeEntity filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SizeEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
