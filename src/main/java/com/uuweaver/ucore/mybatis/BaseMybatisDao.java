package com.uuweaver.ucore.mybatis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.util.List;


public abstract class BaseMybatisDao<E,PK extends Serializable> extends MyBatisDao  implements EntityDao<E,PK> {
    protected final Log log = LogFactory.getLog(getClass());
	//protected Logger logger = Logger.getLogger(BaseMybatisDao.class);
   
    public E getById(PK primaryKey) {
        return (E)getSqlSession().selectOne(getFindByPrimaryKeyStatement(), primaryKey);
    }
    
	public void deleteById(PK id) {
		int affectCount = getSqlSession().delete(getDeleteStatement(), id);
	}
	
    public void save(E entity) {
		prepareObjectForSaveOrUpdate(entity);
		int affectCount = getSqlSession().insert(getInsertStatement(), entity);    	
    }
    
	public void update(E entity) {
		prepareObjectForSaveOrUpdate(entity);
		int affectCount = getSqlSession().update(getUpdateStatement(), entity);
	}
	
	
	
	/**
	 * 用于子类覆盖,在insert,update之前调用
	 * @param o
	 */
    protected void prepareObjectForSaveOrUpdate(E o) {
    }

    /*public String getMybatisMapperNamesapce() {
        throw new RuntimeException("not yet implement");
    }*/
    
    public abstract String getMybatisMapperNamesapce();
    
    public String getFindByPrimaryKeyStatement() {
        return getMybatisMapperNamesapce()+".getById";
    }

    public String getInsertStatement() {
        return getMybatisMapperNamesapce()+".insert";
    }

    public String getUpdateStatement() {
    	return getMybatisMapperNamesapce()+".update";
    }

    public String getDeleteStatement() {
    	return getMybatisMapperNamesapce()+".delete";
    }
    
	public List<E> findAll() {
		throw new UnsupportedOperationException();
	}

	public boolean isUnique(E entity, String uniquePropertyNames) {
		throw new UnsupportedOperationException();
	}
	
	public void flush() {
		//ignore
	}


}
