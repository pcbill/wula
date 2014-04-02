package wula.cache;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class EhCacheWrapper<K, V> implements CacheWrapper<K, V> {

	private final String cacheName;
	private final CacheManager cacheManager;
	
	private EhCacheWrapper(String cacheName, CacheManager cacheManager) {
		this.cacheName = cacheName;
		this.cacheManager = cacheManager;
	}

	@Override
	public void put(K key, V value) {
		getCache().put(new Element(key, value));
	}

	private Ehcache getCache() {
		return cacheManager.getEhcache(cacheName);
	}

	@Override
	public V get(K key) {
		Element element = getCache().get(key);
	    return (element == null)? null: (V) element.getValue();
	}

}
