package com.gov.sys.data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;


@XmlRootElement
public class CollectionDTO implements ICollectionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 130626L;

	public CollectionDTO() {}
	
	public CollectionDTO(Collection<? extends DTO> dtos) {
		this.dtos = dtos;
	}
	
	private Collection<? extends DTO> dtos;

	public Collection<? extends DTO> getDtos() {
		return dtos;
	}

	public void setDtos(Collection<? extends DTO> dtos) {
		this.dtos = dtos;
	}	
	


}
