package projects.commons.service.cache;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.cache.decorators.SerializedCache.CustomObjectInputStream;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import projects.commons.exception.ServiceException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.SortingParams;

@Service
public class CacheService {
	private final transient Logger LOG = Logger.getLogger(CacheService.class);

	private JedisPool connectionPool;
	private int cacheDatabase;
	private boolean stringKeySerializer = true;

	public void initService(String cacheHost, int cachePort, int cacheTimeout, int cacheDatabase, 
			int cacheMaxActive, int cacheMaxIdle, int cacheMaxWait, boolean cacheTestOnBorrow, boolean stringKeySerializer) {
		try {
			try {
				JedisPoolConfig config = new JedisPoolConfig();
				config.setMaxActive(cacheMaxActive);
				config.setMaxIdle(cacheMaxIdle);
				config.setMaxWait(cacheMaxWait);
				config.setTestOnBorrow(cacheTestOnBorrow);
				this.connectionPool = new JedisPool(new JedisPoolConfig(), 
						cacheHost, 
						cachePort, 
						cacheTimeout);

				this.stringKeySerializer = stringKeySerializer;
				connectionPool.getResource();
				this.cacheDatabase = cacheDatabase;
				
			} catch (Exception e) {
				throw new ServiceException("Error Connecting to Redis", e);
			}
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}
	
	public void initService(String cacheHost, int cachePort, int cacheTimeout, int cacheDatabase, 
			int cacheMaxActive, int cacheMaxIdle, int cacheMaxWait, boolean cacheTestOnBorrow, boolean stringKeySerializer, String cachePass) {
		try {
			try {
				JedisPoolConfig config = new JedisPoolConfig();
				config.setMaxActive(cacheMaxActive);
				config.setMaxIdle(cacheMaxIdle);
				config.setMaxWait(cacheMaxWait);
				config.setTestOnBorrow(cacheTestOnBorrow);
				this.connectionPool = new JedisPool(new JedisPoolConfig(), 
						cacheHost, 
						cachePort, 
						cacheTimeout, cachePass);

				this.stringKeySerializer = stringKeySerializer;
				connectionPool.getResource();
				this.cacheDatabase = cacheDatabase;
				
			} catch (Exception e) {
				throw new ServiceException("Error Connecting to Redis", e);
			}
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@PreDestroy
	public void destory() {
		try {
			this.connectionPool.destroy();
		} catch (Exception e) {

		}
	}

	public String getAttribute(String key) {
		Boolean error = Boolean.valueOf(true);
		Jedis jedis = null;
		String v = null;
		try {
			jedis = acquireConnection();
			v = jedis.get(key);
			error = Boolean.valueOf(false);
		} finally {
			if (jedis != null) {
				returnConnection(jedis, error);
			}
		}
		return v;
	}
	public byte[] getAttribute(byte[] key) {
		Boolean error = Boolean.valueOf(true);
		Jedis jedis = null;
		byte[] v = null;
		try {
			jedis = acquireConnection();
			v = jedis.get(key);
			error = Boolean.valueOf(false);
		} finally {
			if (jedis != null) {
				returnConnection(jedis, error);
			}
		}
		return v;
	}

	public Object getAttribute(Object key) {
		byte[] serializeKey = stringKeySerializer ? key.toString().getBytes() : SerializeUtil.serialize(key);
    	byte[] serializyObj = getAttribute(serializeKey);
        return SerializeUtil.unserialize(serializyObj);
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(Object key, Class<T> clazz) {
		byte[] serializeKey = stringKeySerializer ? key.toString().getBytes() : SerializeUtil.serialize(key);
    	byte[] serializyObj = getAttribute(serializeKey);
        Object o = SerializeUtil.unserialize(serializyObj);
        if(o != null){
        	try{
        		return (T)o;
        	}catch(Exception e){
        	}
        }
        return null;
	}

	public void setStrAttribute(String key, String obj) {
		Boolean error = Boolean.valueOf(true);
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			jedis.set(key, obj);
			error = Boolean.valueOf(false);
		} finally {
			if (jedis != null) {
				returnConnection(jedis, error);
			}
		}
	}

	private void setAttribute(byte[] key, byte[] obj) {
		Boolean error = Boolean.valueOf(true);
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			jedis.set(key, obj);
			error = Boolean.valueOf(false);
		} finally {
			if (jedis != null) {
				returnConnection(jedis, error);
			}
		}
	}

	public void setAttribute(Object key, Object value) {
		setAttribute(stringKeySerializer ? key.toString().getBytes() : SerializeUtil.serialize(key), SerializeUtil.serialize(value));
	}
	
	/** 
     * 向集合中增加一条记录,如果这个值已存在，这个值对应的权重将被置为新的权重 
     * @param String key 
     * @param double score 权重 
     * @param String member 要加入的值， 
     * @return 状态码 1成功，0已存在member的值 
     * */  
    public long zadd(String key,double score,String member){  
    	 Boolean error = Boolean.valueOf(true);
    	 Jedis jedis = null;
    	 long s;
    	 try {
    		jedis=acquireConnection();  
	        s =jedis.zadd(key, score, member);  
	       
			error = Boolean.valueOf(false);
		} finally {
			if (jedis != null) {
				returnConnection(jedis, error);
			}
		}
    	return s;  
       
    }  
    
	 /** 
     * 获取指定值在集合中的位置，集合排序从低到高 
     * @see zrank 
     * @param String key 
     * @param String member 
     * @return long 位置 
     * */  
    public long zrevrank(String key,String member){           
	     Boolean error = Boolean.valueOf(true);
	   	 Jedis jedis = null;
	   	 long index;
	   	 try {
	   		jedis=acquireConnection();  
	   		index= jedis.zrevrank(key, member); 
		       
				error = Boolean.valueOf(false);
			} finally {
				if (jedis != null) {
					returnConnection(jedis, error);
				}
			}
	   	return index;  
    }  

    public boolean exists(String key) {
    	 Boolean error = Boolean.valueOf(true);
    	 Jedis jedis = null;
    	 boolean s = true;
    	 try {
    		 jedis=acquireConnection(); 
    		 if (!jedis.exists(key)) {
    	        s = false; 	 
    	      }	       
			error = Boolean.valueOf(false);
		} finally {
			if (jedis != null) {
				returnConnection(jedis, error);
			}
		}
    	return s;  
     
    }
    
	public long setExpire(String key, int expire) {
		Boolean error = Boolean.valueOf(true);
		Jedis jedis = null;
		long reply = 0;
		try {
			jedis = acquireConnection();
			reply = jedis.expire(key, expire);//设置秒
			error = Boolean.valueOf(false);
		} finally {
			if (jedis != null) {
				returnConnection(jedis, error);
			}
		}
		return reply;
	}
	
	public long setExpire(byte[] key, int expire) {
		Boolean error = Boolean.valueOf(true);
		Jedis jedis = null;
		long reply = 0;
		try {
			jedis = acquireConnection();
			reply = jedis.expire(key, expire);//设置秒
			error = Boolean.valueOf(false);
		} finally {
			if (jedis != null) {
				returnConnection(jedis, error);
			}
		}
		return reply;
	}

	public void removeAttribute(String key) {
		Boolean error = Boolean.valueOf(true);
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			jedis.del(key);
			error = Boolean.valueOf(false);
		} finally {
			if (jedis != null) {
				returnConnection(jedis, error);
			}
		}
	}
	
	public void removeAttribute(byte[] key) {
		Boolean error = Boolean.valueOf(true);
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			jedis.del(key);
			error = Boolean.valueOf(false);
		} finally {
			if (jedis != null) {
				returnConnection(jedis, error);
			}
		}
	}
	
	/**
	 * 获取两个集合的差集
	 * @param dstkey  差集数据的存储key
	 * @param key1    第一个数据
	 * @param key2   第二个数据
	 * @return
	 */
	public Long sdiffstore(String dstkey, String key1, String key2) {
        Boolean error = Boolean.valueOf(true);
        Jedis jedis = null;
        Long v = null;
        try {
            jedis = acquireConnection();
            v = jedis.sdiffstore(dstkey, key1, key2);
            error = Boolean.valueOf(false);
        } finally {
            if (jedis != null) {
                returnConnection(jedis, error);
            }
        }
        return v;
    }
	/**
	 * 
	 *  Created on 2015年3月4日 
	 * <p>Description:算二度人脉</p>
	 * @author:刘宝仲
	 * @param dstkey
	 * @param key1
	 * @param expire
	 * @param keys
	 * @return
	 */
	public Long sunion(String dstkey,String key1,int expire,String...keys ){
	    Boolean error = Boolean.valueOf(true);
        Jedis jedis = null;
        Long v = null;
        try {
            jedis = acquireConnection();
            //集合合并
            jedis.sunionstore(dstkey, keys);
            //差集
            v=jedis.sdiffstore(dstkey, dstkey, key1);
            setExpire(dstkey, expire);
            error = Boolean.valueOf(false);
        } finally {
            if (jedis != null) {
                returnConnection(jedis, error);
            }
        }
	    return v;
	}
	   public Long sunion(String dstkey,String...keys ){
	        Boolean error = Boolean.valueOf(true);
	        Jedis jedis = null;
	        Long v = null;
	        try {
	            jedis = acquireConnection();
	            //集合合并
	            jedis.sunionstore(dstkey, keys);
	            error = Boolean.valueOf(false);
	        } finally {
	            if (jedis != null) {
	                returnConnection(jedis, error);
	            }
	        }
	        return v;
	    }
	   /**
     * 获取两个集合的交集
     * @param dstkey  交集数据的存储key
     * @param key1    第一个数据
     * @param key2   第二个数据
     * @return
     */
    public Long sinterstore(String dstkey, String key1, String key2) {
        Boolean error = Boolean.valueOf(true);
        Jedis jedis = null;
        Long v = null;
        try {
            jedis = acquireConnection();
            v = jedis.sinterstore(dstkey, key1, key2);
            error = Boolean.valueOf(false);
        } finally {
            if (jedis != null) {
                returnConnection(jedis, error);
            }
        }
        return v;
    }
    /**
     * 
     *  Created on 2015年3月5日 
     * <p>Description:向名称为key的hash中添加元素field</p>
     * @author:刘宝仲
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hset(String key,String field,String value){
        Boolean error = Boolean.valueOf(true);
        Jedis jedis = null;
        Long v = null;
        try {
            jedis = acquireConnection();
            v = jedis.hset(key, field, value);
            error = Boolean.valueOf(false);
        } finally {
            if (jedis != null) {
                returnConnection(jedis, error);
            }
        }
        return v;
    }
    /**
     * 
     *  Created on 2015年3月5日 
     * <p>Description:向名称为key的hash中添加元素field</p>
     * @author:刘宝仲
     * @param key
     * @param fields
     * @return
     */
    public String hmset(String key, Map<String, String> fields){
        Boolean error = Boolean.valueOf(true);
        Jedis jedis = null;
        String  v = null;
        try {
            jedis = acquireConnection();
            v=jedis.hmset(key, fields);
            error = Boolean.valueOf(false);
        } finally {
            if (jedis != null) {
                returnConnection(jedis, error);
            }
        }
        return v;
    }
    /**
     * 
     *  Created on 2015年3月5日 
     * <p>Description:返回名称为key的hash中field对应的value</p>
     * @author:刘宝仲
     * @param key
     * @param field
     * @return
     */
    public String hget(String key,String field){
        Boolean error = Boolean.valueOf(true);
        Jedis jedis = null;
        String  v = null;
        try {
            jedis = acquireConnection();
            v = jedis.hget(key, field);
            error = Boolean.valueOf(false);
        } finally {
            if (jedis != null) {
                returnConnection(jedis, error);
            }
        } 
        return v;
    }
    /**
     * 
     *  Created on 2015年3月5日 
     * <p>Description:返回名称为key的hash中所有的键（field）及其对应的value</p>
     * @author:刘宝仲
     * @param key
     * @return
     */
    public Map<String, String> hgetAll(String key){
        Boolean error = Boolean.valueOf(true);
        Jedis jedis = null;
        Map<String, String>  v = null;
        try {
            jedis = acquireConnection();
            v = jedis.hgetAll(key);
            error = Boolean.valueOf(false);
        } finally {
            if (jedis != null) {
                returnConnection(jedis, error);
            }
        } 
        return v;
    }
    /**
     * 
     *  Created on 2015年3月5日 
     * <p>Description:返回名称为key的hash中field i对应的value</p>
     * @author:刘宝仲
     * @param key
     * @param field
     * @return
     */
    public List<String> hmget(String key,String...fields){
        Boolean error = Boolean.valueOf(true);
        Jedis jedis = null;
        List<String>  v = null;
        try {
            jedis = acquireConnection();
            v = jedis.hmget(key, fields);
            error = Boolean.valueOf(false);
        } finally {
            if (jedis != null) {
                returnConnection(jedis, error);
            }
        } 
        return v;
    }
	/**
	 * 获取前几条数据
	 * @param key
	 * @param begin
	 * @param size
	 * @return
	 */
	public List<String> sortKeyLimit(String key, int begin, int size) {
        Boolean error = Boolean.valueOf(true);
        Jedis jedis = null;
        List<String> v = null;
        try {
            jedis = acquireConnection();
            
            SortingParams sortingParameters = new SortingParams();  
            sortingParameters.desc();  
            // sortingParameters.alpha();//当数据集中保存的是字符串值时，你可以用 ALPHA  
            // 修饰符(modifier)进行排序。  
            sortingParameters.limit(begin, size);// 可用于分页查询  
            
            v = jedis.sort(key, sortingParameters);
            error = Boolean.valueOf(false);
        } finally {
            if (jedis != null) {
                returnConnection(jedis, error);
            }
        }
        return v;
    }
	public Set<String> smembers(String key){
	    Boolean error = Boolean.valueOf(true);
        Jedis jedis = null;
        Set<String> set = null;
        try {
            jedis = acquireConnection();
            set=jedis.smembers(key);
            error = Boolean.valueOf(false);
        } finally {
            if (jedis != null) {
                returnConnection(jedis, error);
            }
        }
        return set;
    }
	/**
	 * Set结构中增加数据
	 * @param key
	 * @param value
	 * @return
	 */
	public Long sadd(String key, String value) {
        Boolean error = Boolean.valueOf(true);
        Jedis jedis = null;
        Long v = null;
        try {
            jedis = acquireConnection();
            v = jedis.sadd(key, value);
            error = Boolean.valueOf(false);
        } finally {
            if (jedis != null) {
                returnConnection(jedis, error);
            }
        }
        return v;
    }
	
	public long del(String key) {
        Boolean error = Boolean.valueOf(true);
        Jedis jedis = null;
        Long v = null;
        try {
            jedis = acquireConnection();
            v = jedis.del(key);
            error = Boolean.valueOf(false);
        } finally {
            if (jedis != null) {
                returnConnection(jedis, error);
            }
        }
        return v;
    }
	
	public long srem(String key, String value) {
        Boolean error = Boolean.valueOf(true);
        Jedis jedis = null;
        Long v = null;
        try {
            jedis = acquireConnection();
            v = jedis.srem(key,value);
            error = Boolean.valueOf(false);
        } finally {
            if (jedis != null) {
                returnConnection(jedis, error);
            }
        }
        return v;
    }
	
	public void flushDB() {
		Boolean error = Boolean.valueOf(true);
		Jedis jedis = null;
		try {
			jedis = acquireConnection();
			jedis.flushDB();
			error = Boolean.valueOf(false);
		} finally {
			if (jedis != null) {
				returnConnection(jedis, error);
			}
		}
	}

	protected Jedis acquireConnection() {
		Jedis jedis = (Jedis) this.connectionPool.getResource();

		if (cacheDatabase != 0) {
			jedis.select(cacheDatabase);
		}

		return jedis;
	}

	protected void returnConnection(Jedis jedis, Boolean error) {
		if (error.booleanValue())
			this.connectionPool.returnBrokenResource(jedis);
		else
			this.connectionPool.returnResource(jedis);
	}

	protected void returnConnection(Jedis jedis) {
		returnConnection(jedis, Boolean.valueOf(false));
	}
	
	public Long dbSize() {
		Boolean error = Boolean.valueOf(true);
		Jedis jedis = null;
		Long dbSize = 0l;
		try {
			jedis = acquireConnection();
			dbSize = jedis.dbSize();
			error = Boolean.valueOf(false);
		} finally {
			if (jedis != null) {
				returnConnection(jedis, error);
			}
		}
		return dbSize;
	}

	public String getRedisSession(final String sessionId) {
		try {
			byte[] data = getAttribute(sessionId.getBytes());
			
			if(data!=null){
				BufferedInputStream bis = new BufferedInputStream(
						new ByteArrayInputStream(data));
				ObjectInputStream ois = new CustomObjectInputStream(bis);
				RedisSession redisSession = new RedisSession();
				redisSession.setCreationTime(ois.readLong());
				redisSession.readObjectData(ois);
				return redisSession.toString();
			}else{
				LOG.info("RedisSession: "+sessionId+" is null");
				return null;	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public String getRedisSession(HttpSession session) {
		try {
			StringBuilder attributesStr = new StringBuilder();
			Enumeration attributes = session.getAttributeNames();
			while (attributes.hasMoreElements()) {
			    String attributeName = (String)attributes.nextElement();
			    attributesStr.append("\n"+attributeName +" : "+ session.getAttribute(attributeName));
			}

			return "QuerySession [creationTime="
					+ session.getCreationTime() + ", id=" + session.getId() + ", lastAccessedTime="
					+ session.getLastAccessedTime() + ", maxInactiveInterval="
					+ session.getMaxInactiveInterval() + ", isNew=" + session.isNew() + ", attributes=" + attributesStr + "]";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	class RedisSession {
		protected static final String NOT_SERIALIZED = "___NOT_SERIALIZABLE_EXCEPTION___";
		@SuppressWarnings({ "rawtypes", "unchecked" })
		protected Map<String, Object> attributes = new ConcurrentHashMap();
		protected transient String authType = null;
		protected long creationTime = 0L;
		protected volatile transient boolean expiring = false;
		protected String id = null;
		protected static final String info = "StandardSession/1.0";
		protected volatile long lastAccessedTime = this.creationTime;
		protected int maxInactiveInterval = -1;
		protected boolean isNew = false;
		protected volatile boolean isValid = false;
		protected volatile long thisAccessedTime = this.creationTime;

		
		public void setCreationTime(long creationTime) {
			this.creationTime = creationTime;
		}

		public void readObjectData(ObjectInputStream stream)throws ClassNotFoundException, IOException {
			readObject(stream);
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		protected void readObject(ObjectInputStream stream)
				throws ClassNotFoundException, IOException {
			this.authType = null;
			this.creationTime = ((Long) stream.readObject()).longValue();
			this.lastAccessedTime = ((Long) stream.readObject()).longValue();
			this.maxInactiveInterval = ((Integer) stream.readObject())
					.intValue();
			this.isNew = ((Boolean) stream.readObject()).booleanValue();
			this.isValid = ((Boolean) stream.readObject()).booleanValue();
			this.thisAccessedTime = ((Long) stream.readObject()).longValue();


			this.id = ((String) stream.readObject());

			if (this.attributes == null)
				this.attributes = new ConcurrentHashMap();
			int n = ((Integer) stream.readObject()).intValue();
			boolean isValidSave = this.isValid;
			this.isValid = true;
			for (int i = 0; i < n; i++) {
				String name = (String) stream.readObject();
				Object value = stream.readObject();
				if ((!(value instanceof String))
						|| (!value.equals("___NOT_SERIALIZABLE_EXCEPTION___"))) {
					this.attributes.put(name, value);
				}
			}
			this.isValid = isValidSave;

		}

		@Override
		public String toString() {
			StringBuilder attributesStr = new StringBuilder();
			for(Entry<String, Object> entry : attributes.entrySet()){
				attributesStr.append("\n"+entry.getKey() +" : "+ entry.getValue());
			}
			return "QuerySession [creationTime="
					+ creationTime + ", id=" + id + ", lastAccessedTime="
					+ lastAccessedTime + ", maxInactiveInterval="
					+ maxInactiveInterval + ", isNew=" + isNew + ", isValid="
					+ isValid + ", thisAccessedTime=" + thisAccessedTime + ", attributes=" + attributesStr + "]";
		}
	}

}