package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.data.dao.entity.TableDAO;
import co.edu.uco.rapizzas.entity.TableEntity;

public final class TablePostgreSqlDAO extends SqlConnection implements TableDAO {

	public TablePostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(TableEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TableEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TableEntity> findByFilter(TableEntity filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
