package com.ebanma.cloud.threadPool;
  
import org.junit.After;  
import org.junit.Test;

import java.util.concurrent.*;

public class
PolicyTest {
   
    private static ThreadPoolExecutor executor =
            new ThreadPoolExecutor(
                    // 核心线程数和最大线程数
                    2, 3,
                    // 线程空闲后的存活时间
                    60L, TimeUnit.SECONDS,
                    // 有界阻塞队列
                    new LinkedBlockingQueue<Runnable>(5));

    private static ExecutorService executorService=Executors.newCachedThreadPool();
   
    class Task implements Runnable {  
        // 任务名称  
        private String taskName;  
  
        public Task(String taskName) {  
            this.taskName = taskName;  
        }  
  
        @Override  
        public void run() {  
            System.out.println("线程[ " + Thread.currentThread().getName()  
                    + " ]正在执行[ " + this.taskName + " ]任务...");  
  
            try {  
                Thread.sleep(1000L * 5);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
  
            System.out.println("线程[ " + Thread.currentThread().getName()  
                    + " ]已执行完[ " + this.taskName + " ]任务！！！");  
        }  
    }  
  
    /**
     *
     * 中止策略：无特殊场景。
     * 丢弃策略：无关紧要的任务（博客阅读量）。
     * 弃老策略：发布消息。
     * 调用者运行策略：不允许失败场景（对性能要求不高、并发量较小）。
     *
     * 线程池的执行过程
     *  
     * 2个核心线程  
     * 5个任务的队列  
     * 3个最大线程：1个线程可用  
     *  
     * 前2个任务，会占用2个核心线程  
     * 第3个到第7个任务，会暂存到任务队列中  
     * 第8个任务，会启动最大线程，去执行  
     * 第9个任务，没有线程可以去执行~~~  
     */   
    @Test  
    public void abortPolicyTest() {  
        // 设置饱和策略为 终止策略  
        executor.setRejectedExecutionHandler(  
                new ThreadPoolExecutor.AbortPolicy());  
  
        for (int i = 1; i <= 10; i++) {  
            try {  
                // 提交10个线程任务  
                executor.execute(new Task("线程任务" + i));
            } catch (Exception e) {  
                System.err.println(e);  
            }  
        }  
  
        // 关闭线程池  
        executor.shutdown();  
    }

    @Test
    public void discardPolicyTest() {
        // 设置饱和策略为 抛弃策略
        executor.setRejectedExecutionHandler(
                new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 1; i <= 10; i++) {
            try {
                // 提交10个线程任务
                executor.execute(new Task("线程任务" + i));
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        // 关闭线程池
        executor.shutdown();
    }

    @Test
    public void discardOldestPolicyTest() {
        // 设置饱和策略为 抛弃旧任务策略
        executor.setRejectedExecutionHandler(
                new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 1; i <= 10; i++) {
            try {
                // 提交10个线程任务
                executor.execute(new Task("线程任务" + i));
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        // 关闭线程池
        executor.shutdown();
    }

    @Test
    public void callerRunsPolicyTest() {
        // 设置饱和策略为 调用者运行策略
        executor.setRejectedExecutionHandler(
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 1; i <= 10; i++) {
            try {
                // 提交10个线程任务
                executor.execute(new Task("线程任务" + i));
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        // 关闭线程池
        executor.shutdown();
    }
  
    /**  
     * 单元测试执行完，主线程等待100秒。防止主线程退出，看不到线程的执行结果  
     * @throws InterruptedException  
     */  
    @After  
    public void after() throws InterruptedException {  
        Thread.sleep(1000L * 100);
    }

    //线程数量无线线程池
    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

    //线程数量固定线程池
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    //单一线程线程池
    public static ExecutorService newSingleThreadExecutor() {
        return  Executors.newSingleThreadExecutor();
    }

    @Test
    //向线程池提交任务，使用submit()方法
    public void submitTest()
            throws ExecutionException, InterruptedException {

        // 创建线程池
        ExecutorService threadPool =
                Executors.newCachedThreadPool();

        // 利用submit方法提交任务，接收任务的返回结果
        Future<Integer> future = threadPool.submit(() -> {
            Thread.sleep(1000L * 10);
            return 2 * 5;
        });

        // 阻塞方法，直到任务有返回值后，才向下执行
        Integer num = future.get();
        System.out.println("执行结果：" + num);
    }

    @Test
    //向线程池提交任务，使用execute()方法
    public void executeTest() throws InterruptedException {
        // 创建线程池
        ExecutorService threadPool =
                Executors.newCachedThreadPool();

        // 利用execute方法提交任务，没有返回结果
        threadPool.execute(() -> {
            try {
                Thread.sleep(1000L * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer num = 2 * 5;
            System.out.println("执行结果：" + num);
        });

        Thread.sleep(1000L * 1000);
    }
  
}