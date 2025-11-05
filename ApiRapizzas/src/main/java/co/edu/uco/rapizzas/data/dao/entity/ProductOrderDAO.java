package co.edu.uco.rapizzas.data.dao.entity;

import java.util.UUID;

import co.edu.uco.rapizzas.data.dao.CreateDAO;
import co.edu.uco.rapizzas.data.dao.DeleteDAO;
import co.edu.uco.rapizzas.data.dao.RetrieveDAO;
import co.edu.uco.rapizzas.data.dao.UpdateDAO;
import co.edu.uco.rapizzas.entity.ProductOrderEntity;

public interface ProductOrderDAO extends CreateDAO<ProductOrderEntity>, 
	RetrieveDAO<ProductOrderEntity, UUID>,UpdateDAO<ProductOrderEntity>, DeleteDAO<UUID> {

}
