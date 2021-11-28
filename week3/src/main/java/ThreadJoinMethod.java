import java.util.concurrent.atomic.AtomicInteger;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * Thread join 方式
 */
public class ThreadJoinMethod {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        AtomicInteger value = new AtomicInteger();
        Thread thread = new Thread(() ->
                value.set(getValue()));

        System.out.println("start thread");
        thread.start();
        System.out.println("after start thread");
        thread.join();
        System.out.println("after join thread");
        int result = value.get(); //这是得到的返回值

        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

    }

    private static int getValue() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }


}
