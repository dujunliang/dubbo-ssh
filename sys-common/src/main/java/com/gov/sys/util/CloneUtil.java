package com.gov.sys.util;


import com.gov.sys.entity.AbstractEntity;

import java.io.*;

public class CloneUtil {

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T clone(T obj) throws Exception {
		// serialize
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream objOut = new ObjectOutputStream(bout);
		objOut.writeObject(obj);

		// de-serialize
		ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
		ObjectInputStream objIn = new ObjectInputStream(bin);
		return (T) objIn.readObject();
	}
	
	@SuppressWarnings("unchecked")
    public static <T extends AbstractEntity> T cloneNew(T entity) throws Exception {
        T newEntity = clone(entity);
        newEntity.setCreateUser(null);
        newEntity.setCreateDate(null);
        return (T)newEntity;
    }
}
