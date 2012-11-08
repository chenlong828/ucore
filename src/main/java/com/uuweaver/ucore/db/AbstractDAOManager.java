package com.uuweaver.ucore.db;

/**
 * Description：
 * Author: ChenLong
 * Date: 12-9-20
 * Time: 下午4:13
 */
public abstract class AbstractDAOManager<E extends DAOBase, P> {

    public abstract E CreateNewEntity();

    public abstract boolean SaveEntity(E entity);

    public abstract E LoadByPriamaryKey(P primary_key);

    public abstract boolean Delete(E entity);

    public abstract boolean Delete(P primary_key);

    public abstract boolean Update(E entity);
}
