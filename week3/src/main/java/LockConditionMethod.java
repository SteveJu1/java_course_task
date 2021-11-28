import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 管程Lock Condition方式
 */
public class LockConditionMethod {
    private volatile Integer value = null;
    private Lock lock = new ReentrantLock();
    private Condition calComplete = lock.newCondition();

    public void sum(int num) {
        lock.lock();
        try {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            value = 1;
            calComplete.signal();
        } finally {
            lock.unlock();
        }
    }

    public int getValue() throws InterruptedException {
        lock.lock();
        try {
            while (value == null) {
                calComplete.await();
            }
        } finally {
            lock.unlock();
        }
        return value;
    }

    public static void main(String[] args) throws InterruptedException {
        long start=System.currentTimeMillis();
        final LockConditionMethod method = new LockConditionMethod();
        Thread thread = new Thread(() -> {
            method.sum(45);
        });
        thread.start();

        int result = method.getValue(); //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

}
