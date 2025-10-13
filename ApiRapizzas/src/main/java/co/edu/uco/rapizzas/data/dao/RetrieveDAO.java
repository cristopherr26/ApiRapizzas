package co.edu.uco.rapizzas.data.dao;

import java.util.List;

public interface RetrieveDAO <E>{
	
	List<E> findAll();
	
	List<E> findByFilter(E filterEntity);

}
