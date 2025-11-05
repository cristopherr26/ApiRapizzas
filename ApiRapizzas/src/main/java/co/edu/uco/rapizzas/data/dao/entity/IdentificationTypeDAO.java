package co.edu.uco.rapizzas.data.dao.entity;

import java.util.UUID;

import co.edu.uco.rapizzas.data.dao.CreateDAO;
import co.edu.uco.rapizzas.data.dao.RetrieveDAO;
import co.edu.uco.rapizzas.data.dao.UpdateDAO;
import co.edu.uco.rapizzas.entity.IdentificationTypeEntity;

public interface IdentificationTypeDAO extends CreateDAO<IdentificationTypeEntity>,
	RetrieveDAO<IdentificationTypeEntity, UUID>, UpdateDAO<IdentificationTypeEntity> {

}
