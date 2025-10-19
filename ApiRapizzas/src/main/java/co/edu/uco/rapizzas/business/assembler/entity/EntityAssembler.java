package co.edu.uco.rapizzas.business.assembler.entity;

public interface EntityAssembler<E, D> {
	
	E toDomain(D domain);
	
	D toEntity(E entity);

}
