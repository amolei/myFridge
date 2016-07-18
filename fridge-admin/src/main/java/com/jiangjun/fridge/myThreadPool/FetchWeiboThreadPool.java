package com.jiangjun.fridge.myThreadPool;

import EDU.oswego.cs.dl.util.concurrent.LinkedQueue;
import EDU.oswego.cs.dl.util.concurrent.PooledExecutor;

/**
 * Created by jiangjun on 2016/7/17.
 */
public class FetchWeiboThreadPool {

    private static PooledExecutor pooledExecutor;

    static {
        pooledExecutor = new PooledExecutor(new LinkedQueue());
        pooledExecutor.setKeepAliveTime(180000);
        pooledExecutor.createThreads(4);
        pooledExecutor.setMinimumPoolSize(4);
        pooledExecutor.setMaximumPoolSize(4);
        pooledExecutor.waitWhenBlocked();
    }
    public static void execute(Runnable r) throws InterruptedException {
        pooledExecutor.execute(r);
    }

    public static int getThreadSize() {
        return pooledExecutor.getPoolSize();
    }

    public static void shutdown() {
        pooledExecutor.shutdownAfterProcessingCurrentlyQueuedTasks();
        pooledExecutor = null;
    }

}
