package wula.dao;

import org.springframework.beans.factory.annotation.Autowired;

import wula.cache.CacheWrapper;

/**
 * For Reference
 * @author Bill1Wu
 *
 * @param <T>
 */
public class SampleDao<T> /*implements BuserMapper*/{

	// uid, user
	@Autowired
	protected CacheWrapper<String, T> tCache;
		
	/**
	 * sample code
	 */
//	@Autowired
//	private BuserMapper userMapper;
	
	/**
	 * sample code
	 */
//	@Override
//	public Buser findByUid(String id) {
//		Buser value = null;
//		if ((value = userCache.get(id)) == null) {
//	        value = userMapper.findByUid(id);
//	        if (value != null) {
//	        	userCache.put(id, value);
//	        }
//	    }
//		return value;
//	}
	
}
