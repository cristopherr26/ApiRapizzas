package co.edu.uco.rapizzas.data.dao.entity;

import java.util.UUID;

import co.edu.uco.rapizzas.data.dao.CreateDAO;
import co.edu.uco.rapizzas.data.dao.RetrieveDAO;
import co.edu.uco.rapizzas.data.dao.UpdateDAO;
import co.edu.uco.rapizzas.entity.CustomerTableEntity;

public interface CustomerTableDAO extends CreateDAO<CustomerTableEntity>, 
	RetrieveDAO<CustomerTableEntity, UUID>,UpdateDAO<CustomerTableEntity> {

}
