package com.ebanma.cloud.threadPool;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author yuqintao
 * @version $ Id: ThreadPoolTest, v 0.1 2023/02/22 10:21 banma-0018 Exp $
 */
public class ThreadPoolTest {

    @Test
    public void oldHandle() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println("文档处理开始！");

                try {
                    Thread.sleep(1000l * 30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("文档处理结束！");
            }).start();
        }

        Thread.sleep(1000l * 1000);
    }

    @Test
    public void newHandle() throws InterruptedException {
        // 开启了一个线程池：线程个数是10个
        ExecutorService threadPool =
                Executors.newFixedThreadPool(10);

        for (int i = 1; i <= 100; i++) {
            threadPool.execute(() -> {
                System.out.println("文档处理开始！");

                try {
                    // 将Word转换为PDF格式：处理时长很长的耗时过程
                    Thread.sleep(1000L * 3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("文档处理结束！");
            });
        }

        Thread.sleep(1000L * 1000);
    }

    @Test
    public void arrayBlockingQueue() throws InterruptedException {
        // 基于数组的有界阻塞队列，队列容量为10
        ArrayBlockingQueue queue =
                new ArrayBlockingQueue<Integer>(10);

        // 循环向队列添加元素
        for (int i = 0; i < 20; i++) {
            queue.put(i);
            System.out.println("向队列中添加值：" + i);
        }
    }

    @Test
    public void linkedBlockingQueue() throws InterruptedException {
        // 基于链表的有界/无界阻塞队列(有参数即为有界队列，无参数即为无界队列)
        LinkedBlockingQueue queue =
                new LinkedBlockingQueue<Integer>();

        // 循环向队列添加元素
        for (int i = 0; i < 20; i++) {
            queue.put(i);
            System.out.println("向队列中添加值：" + i);
        }
    }

    @Test
    public void synchronousQueue() throws InterruptedException {
        // 同步移交阻塞队列
        SynchronousQueue queue = new SynchronousQueue<Integer>();

        // 插入值
        new Thread(() -> {
            try {
                queue.put(1);
                System.out.println("插入成功！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 删除值
        new Thread(() -> {
            try {
                queue.take();
                System.out.println("删除成功！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000L * 60);
    }

}
