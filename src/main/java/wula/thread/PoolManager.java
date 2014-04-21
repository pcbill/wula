package wula.thread;

import java.util.ArrayList;
import java.util.List;

public class PoolManager {

	private static class PoolItem {
		boolean inUse = false;
		Object item;
		PoolItem(Object item) {
			this.item = item;
		}
	}
	
	private List<Object> items = new ArrayList<Object>();
	
	public void add(Object o) {
		items.add(new PoolItem(o));
	}
	
	public Object get() throws EmptyPoolException {
		for (Object o: items) {
			PoolItem pitem = (PoolItem)o;
			if (!pitem.inUse) {
				pitem.inUse = true;
				return pitem.item;
			}
		}
		throw new EmptyPoolException();
	}
	
	public void release(Object item) {
		for (Object o: items) {
			PoolItem pitem = (PoolItem)o;
			if (item == pitem.item) {
				pitem.inUse = false;
				return;
			}
		}
		throw new RuntimeException(item+"not found!");
	}
}