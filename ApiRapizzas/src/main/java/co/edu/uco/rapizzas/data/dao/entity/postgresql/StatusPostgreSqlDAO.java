package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.data.dao.entity.StatusDAO;
import co.edu.uco.rapizzas.entity.StatusEntity;

public final class StatusPostgreSqlDAO extends SqlConnection implements StatusDAO {

	public StatusPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public List<StatusEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatusEntity> findByFilter(StatusEntity filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(StatusEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
