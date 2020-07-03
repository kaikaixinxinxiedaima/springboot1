package com.test.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

//zookeeper 实现分布式锁
public class ZkLock implements Lock {
    //zk客户端
    private ZooKeeper zk;
    //zk地址
    private String zkQurom = "localhost:2181";
    //目录
    private String lockNameSpace = "/mylock";
    //锁名称
    private String lockName;
    //当前线程创建的序列node
    private ThreadLocal<String> nodeId = new ThreadLocal<>();
    //用于同步等待
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private final static int sessionTimeOut = 3000;
    private final static byte[]  data = new byte[0];



    //构造器
    public ZkLock(String lockName){
        this.lockName = lockName;

        try {
            zk = new ZooKeeper(zkQurom, sessionTimeOut, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    //建立连接
                    if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
                        countDownLatch.countDown();
                    }
                }
            });

            countDownLatch.await();

            Stat stat = zk.exists(lockNameSpace, false);

            if(stat == null){
                //创建根节点
                zk.create(lockNameSpace, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //添加watch监听临时顺序节点的删除
    class LockWatcher implements Watcher{
        private CountDownLatch latch = null;
        public LockWatcher(CountDownLatch downLatch){
            this.latch = downLatch;
        }

        @Override
        public void process(WatchedEvent watchedEvent) {
            if(watchedEvent.getType() == Event.EventType.NodeDeleted){
                latch.countDown();
            }
        }
    }



    @Override
    public void lock() {
        try {
            //创建临时子节点
            String myNode = zk.create(lockNameSpace + '/' + lockName, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName() + myNode + "created");

            //取出所有子节点
            List<String> children = zk.getChildren(lockNameSpace, false);
            TreeSet<String> sortNodes = new TreeSet<>();

            for (String child : children) {
                sortNodes.add(lockNameSpace + "/" + child);
            }

            //第一个结点
            String firstNode = sortNodes.first();

            //如果是最小节点，获得锁
            if(myNode.equals(firstNode)){
                System.out.println(Thread.currentThread().getName() + myNode + "get lock");

                this.nodeId.set(myNode);
                return;
            }

            //前一个节点
            String preNode = sortNodes.lower(myNode);

            CountDownLatch latch = new CountDownLatch(1);
            Stat stat = zk.exists(preNode, new LockWatcher(latch));//注册监听

            //判断比自己小一个数的节点是否存在，如果不存在则无需等待，同时注册监听
            if(stat != null){
                System.out.println(Thread.currentThread().getName() + myNode + " waiting for " + lockNameSpace + "/" + preNode + "released lock");
                latch.await();
                nodeId.set(myNode);
                latch = null;
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        try {
            System.out.println(Thread.currentThread().getName() + " unlock");

            if(nodeId != null){
                zk.delete(nodeId.get(), -1);
            }

            nodeId.remove();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
