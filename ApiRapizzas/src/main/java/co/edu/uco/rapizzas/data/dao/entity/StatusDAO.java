package co.edu.uco.rapizzas.data.dao.entity;

import java.util.UUID;

import co.edu.uco.rapizzas.data.dao.RetrieveDAO;
import co.edu.uco.rapizzas.data.dao.UpdateDAO;
import co.edu.uco.rapizzas.entity.StatusEntity;

public interface StatusDAO extends RetrieveDAO<StatusEntity, UUID>, 
	UpdateDAO<StatusEntity> {

}
