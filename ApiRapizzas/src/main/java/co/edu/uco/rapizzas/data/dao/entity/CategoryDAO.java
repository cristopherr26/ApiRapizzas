package co.edu.uco.rapizzas.data.dao.entity;

import co.edu.uco.rapizzas.data.dao.CreateDAO;
import co.edu.uco.rapizzas.data.dao.RetrieveDAO;
import co.edu.uco.rapizzas.data.dao.UpdateDAO;
import co.edu.uco.rapizzas.entity.CategoryEntity;

public interface CategoryDAO extends CreateDAO<CategoryEntity>, RetrieveDAO<CategoryEntity>,
	UpdateDAO<CategoryEntity> {

}
