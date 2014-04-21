package wula.thread;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

	private int threadId = 0;
	private List<Runnable> taskQueue;
	private boolean isAlive;
	
	public ThreadPool(int count) {
		isAlive = true;
		
		taskQueue = new LinkedList<Runnable>();
		
		for (int i = 0; i < count; i++) {
			new PooledThread().start();
		}
	}

	public synchronized void runTask(Runnable task) {
		if (!isAlive) {
			throw new IllegalStateException();
		}
		
		if (task != null) {
			taskQueue.add(task);
			notify();
		}
	}
	
	protected synchronized Runnable getTask() throws InterruptedException {
		while (taskQueue.size() == 0) {
			if (!isAlive) {
				return null;
			}
			wait();
		}
		return (Runnable)taskQueue.remove(0);
	}
	
	private class PooledThread extends Thread {
		public PooledThread() {
			super("PooledThread-"+(threadId++));
			
		}
		
		@Override
		public void run() {
			while(!isInterrupted()) {
				Runnable task = null;
				
				try {
					task = getTask();
				} catch (InterruptedException e) {
				}
				
				if (task == null) {
					return;
				}

				task.run();
			}
		}
	}
}