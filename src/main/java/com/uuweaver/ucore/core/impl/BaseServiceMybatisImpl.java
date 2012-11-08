package com.uuweaver.ucore.core.impl;

import com.uuweaver.ucore.core.BaseServiceMybatis;
import com.uuweaver.ucore.mybatis.EntityDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class BaseServiceMybatisImpl <E,PK extends Serializable> implements BaseServiceMybatis<E, PK> {
	
		protected Log log = LogFactory.getLog(getClass());

		protected abstract EntityDao getEntityDao();


		@Transactional(readOnly=true)
		public E getById(PK id) throws DataAccessException{
			return (E)getEntityDao().getById(id);
		}
		
		@Transactional(readOnly=true)
		public List<E> findAll() throws DataAccessException{
			return getEntityDao().findAll();
		}
		
		/*public void saveOrUpdate(E entity) throws DataAccessException{
			getEntityDao().saveOrUpdate(entity);
		}*/
		
		public void save(E entity) throws DataAccessException{
			getEntityDao().save(entity);
		}
		
		public void removeById(PK id) throws DataAccessException{
			getEntityDao().deleteById(id);
		}
		
		public void update(E entity) throws DataAccessException{
			getEntityDao().update(entity);
		}
		
		@Transactional(readOnly=true)
		public boolean isUnique(E entity, String uniquePropertyNames) throws DataAccessException {
			return getEntityDao().isUnique(entity, uniquePropertyNames);
		}
		
	}