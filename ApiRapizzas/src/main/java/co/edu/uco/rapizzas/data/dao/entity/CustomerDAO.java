package co.edu.uco.rapizzas.data.dao.entity;

import java.util.UUID;

import co.edu.uco.rapizzas.data.dao.CreateDAO;
import co.edu.uco.rapizzas.data.dao.RetrieveDAO;
import co.edu.uco.rapizzas.data.dao.UpdateDAO;
import co.edu.uco.rapizzas.entity.CustomerEntity;

public interface CustomerDAO extends CreateDAO<CustomerEntity>,
	RetrieveDAO<CustomerEntity, UUID>, UpdateDAO<CustomerEntity>{

}
