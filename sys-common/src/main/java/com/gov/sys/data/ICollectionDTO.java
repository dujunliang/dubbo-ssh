package com.gov.sys.data;

import java.util.Collection;

public interface ICollectionDTO extends DTO {
	public Collection<? extends DTO> getDtos();
}
