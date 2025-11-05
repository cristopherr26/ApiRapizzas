package co.edu.uco.rapizzas.data.dao.entity;

import java.util.UUID;

import co.edu.uco.rapizzas.data.dao.CreateDAO;
import co.edu.uco.rapizzas.data.dao.RetrieveDAO;
import co.edu.uco.rapizzas.entity.PaymentEntity;

public interface PaymentDAO extends CreateDAO<PaymentEntity>, 
	RetrieveDAO<PaymentEntity, UUID> {

}
