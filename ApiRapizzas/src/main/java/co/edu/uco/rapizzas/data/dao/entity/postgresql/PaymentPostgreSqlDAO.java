package co.edu.uco.rapizzas.data.dao.entity.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.rapizzas.data.dao.entity.PaymentDAO;
import co.edu.uco.rapizzas.data.dao.entity.SqlConnection;
import co.edu.uco.rapizzas.entity.PaymentEntity;

public final class PaymentPostgreSqlDAO extends SqlConnection implements PaymentDAO {

	public PaymentPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(PaymentEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PaymentEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentEntity> findByFilter(PaymentEntity filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
