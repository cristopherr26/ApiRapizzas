package co.edu.uco.rapizzas.business.domain;

import java.util.UUID;

class Domain {
	
	private UUID id;
	
	protected Domain(final UUID id) {
		
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	

}
