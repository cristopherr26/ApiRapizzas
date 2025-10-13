package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.rapizzas.data.dao.entity.IdentificationTypeDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.IdentificationTypeEntity;

public final class IdentificationTypePostgreSqlDAO extends SqlConnection implements IdentificationTypeDAO {

	protected IdentificationTypePostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(IdentificationTypeEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IdentificationTypeEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IdentificationTypeEntity> findByFilter(IdentificationTypeEntity filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(IdentificationTypeEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
