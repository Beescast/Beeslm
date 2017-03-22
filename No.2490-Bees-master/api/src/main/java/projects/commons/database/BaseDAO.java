package projects.commons.database;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import projects.commons.database.Query;
import projects.commons.exception.DaoException;

public abstract class BaseDAO<T, K, M, E>{
	
	private final static Log LOG = LogFactory.getLog(BaseDAO.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T getByKey(K key) throws DaoException{
		try {
			M mapper = getMapper();
			Class clazz = mapper.getClass();
			Method method = clazz.getDeclaredMethod("selectByPrimaryKey", key.getClass());
			Object o = method.invoke(mapper, key);
			return o!=null ? (T)o:null;
		} catch (NoSuchMethodException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public T getByExample(E example) throws DaoException{
		List<T> list = list(example);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> list(Query query, Class<E> exampleClass) throws DaoException{
		try {
			E example = query.createExample(query, exampleClass);
			M mapper = getMapper();
			Class clazz = mapper.getClass();
			Method selectMethod = clazz.getDeclaredMethod("selectByExample", exampleClass);

			//检查SqlTools是否包含了totalCounts,可以避免每次都执行一次,在大数据集的时候可以加快速度
	        boolean needCount = (query.getTotalCounts() <= 0);
	        // 如果是pageSize=0 的话，则是所有数据，没有必要做一次查询
	        if (query.getPageSize() == 0)
	            needCount = false;

	        if (needCount) {
	        	Method countMethod = clazz.getDeclaredMethod("countByExample", exampleClass);
	        	int totalCounts = (Integer) countMethod.invoke(mapper, example);
	            LOG.debug("Total Counts=" + totalCounts);
	            query.setTotalCounts(totalCounts);//设置总数
	        }

	        if (query.getPageNo() * query.getPageSize() > query.getTotalCounts()) {
	            LOG.debug("exceed all page data.pageNo="
	                            + query.getPageNo() + " pageSize="
	                            + query.getPageSize() + " reset pageNo=0");
	            query.setPageNo(0);
	        }

	        if (query.isNeedPage()) {
	            LOG.debug("set page info. PageNo=" + query.getPageNo()
	                    + " pageSize=" + query.getPageSize());

	            Method limitStartMethod = exampleClass.getDeclaredMethod("setLimitStart", int.class);
	            limitStartMethod.invoke(example, query.getPageNo() * query.getPageSize());
	            Method limitEndMethod = exampleClass.getDeclaredMethod("setLimitEnd", int.class);
	            limitEndMethod.invoke(example, query.getPageSize());
	        }

			Object o = selectMethod.invoke(mapper, example);

			List<T> result = o!=null ? (List<T>)o:null;

			//如果是pageSize=0 的话，则是所有数据，没有必要做一次查询
	        if (query.getPageSize() == 0 && result!=null) {
	        	query.setTotalCounts(result.size());
	        }

			return result;
		} catch (NoSuchMethodException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> list(E example) throws DaoException{
		try {
			M mapper = getMapper();
			Class clazz = mapper.getClass();
			Method method = clazz.getDeclaredMethod("selectByExample", example.getClass());
			Object o = method.invoke(mapper, example);
			return o!=null ? (List<T>)o:null;
		} catch (NoSuchMethodException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> listWithBLOBs(E example) throws DaoException{
		try {
			M mapper = getMapper();
			Class clazz = mapper.getClass();
			Method method = clazz.getDeclaredMethod("selectByExampleWithBLOBs", example.getClass());
			Object o = method.invoke(mapper, example);
			return o!=null ? (List<T>)o:null;
		} catch (NoSuchMethodException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int count(E example) throws DaoException{
		try {
			M mapper = getMapper();
			Class clazz = mapper.getClass();
			Method method = clazz.getDeclaredMethod("countByExample", example.getClass());
			Object o = method.invoke(mapper, example);
			return o!=null ? (int)o:0;
		} catch (NoSuchMethodException e) {
			return 0;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int insert(T entity) throws DaoException{
		try {
			M mapper = getMapper();
			Class clazz = mapper.getClass();
			Method method = clazz.getDeclaredMethod("insertSelective", entity.getClass());
			Object o = method.invoke(mapper, entity);
			return o!=null ? (int)o:0;
		} catch (NoSuchMethodException e) {

		} catch (Exception e) {
			throw new DaoException(e);
		}
        return 0;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int deleteByKey(K key) throws DaoException{
		try {
			M mapper = getMapper();
			Class clazz = mapper.getClass();
			Method method = clazz.getDeclaredMethod("deleteByPrimaryKey", key.getClass());
			Object o = method.invoke(mapper, key);
			return o!=null ? (int)o:0;
		} catch (NoSuchMethodException e) {

		} catch (Exception e) {
			throw new DaoException(e);
		}
        return 0;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int delete(E example) throws DaoException{
		try {
			M mapper = getMapper();
			Class clazz = mapper.getClass();
			Method method = clazz.getDeclaredMethod("deleteByExample", example.getClass());
			Object o = method.invoke(mapper, example);
            return o!=null ? (int)o:0;
		} catch (NoSuchMethodException e) {

		} catch (Exception e) {
			throw new DaoException(e);
		}
        return 0;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int update(T entity) throws DaoException{
		try {
			M mapper = getMapper();
			Class clazz = mapper.getClass();
			Method method = clazz.getDeclaredMethod("updateByPrimaryKeySelective", entity.getClass());
			Object o = method.invoke(mapper, entity);
			return o!=null ? (int)o:0;
		} catch (NoSuchMethodException e) {

		} catch (Exception e) {
			throw new DaoException(e);
		}
		
		return 0;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int update(T entity, E example) throws DaoException{
		try {
			M mapper = getMapper();
			Class clazz = mapper.getClass();
			Method method = clazz.getDeclaredMethod("updateByExampleSelective", entity.getClass(), example.getClass());
			Object o = method.invoke(mapper, entity, example);
			return o!=null ? (int)o:0;
		} catch (NoSuchMethodException e) {

		} catch (Exception e) {
			throw new DaoException(e);
		}
		
		return 0;
	}

	public abstract M getMapper();
}