import java.util.concurrent.Semaphore;

public class WriterFirst {

    public static void main(String[] args) {
        Thread w1 = new Writing("写者一号");
        Thread w2 = new Writing("写者二号");
        Thread r1 = new Reading("读者一号");
        Thread r2 = new Reading("读者二号");
        w1.start();
        w2.start();
        r1.start();
        r2.start();
    }
}

class My{

    public static Semaphore s1 = new Semaphore(1);
    public static Semaphore s2 = new Semaphore(1);
    public static Semaphore sR = new Semaphore(1);
    public static Semaphore sW = new Semaphore(1);
    public static Semaphore mutex = new Semaphore(1);
    public static int Writer_Cnt = 0;
    public static int Reader_Cnt = 0;

}

class Writing extends Thread{

    public Writing(String name){
        super(name);
    }
    public void run(){
        while(true){
            try {
                Thread.sleep(2000);
                My.sW.acquire();
                System.out.println(Thread.currentThread().getName()+"进入等待！");
                My.Writer_Cnt++;
                if(My.Writer_Cnt == 1){
                    My.s2.acquire();
                    System.out.println(Thread.currentThread().getName()+" 成功进入,挡住了后面的读者");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            My.sW.release();
            try {
                My.mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" 进入数据区了  "+ My.Writer_Cnt);
            My.mutex.release();
            try {
                My.sW.acquire();
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"进入等待！");
            My.Writer_Cnt--;
            if(My.Writer_Cnt==0){
                System.out.println(Thread.currentThread().getName()+" 成功出去,放出了后面的读者");
                My.s2.release();
            }
            System.out.println(Thread.currentThread().getName()+"出来了，里面还剩:"+"当前写者数："+My.Writer_Cnt);
            My.sW.release();
        }
    }
}
class Reading extends Thread{

    public Reading(String name){
        super(name);
    }
    public void run(){
        while(true){
            try {
                My.s1.acquire();
                My.s2.acquire();
                My.sR.acquire();
                System.out.println(Thread.currentThread().getName()+"进入等待！");
            } catch (Exception e) {
                e.printStackTrace();
            }
            My.Reader_Cnt++;
            if(My.Reader_Cnt == 1){
                try {
                    My.mutex.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" 成功进入,挡住后面写者");
            }
            My.sR.release();
            My.s1.release();
            My.s2.release();
            try {
                Thread.sleep(2000);
                My.sR.acquire();
                System.out.println(Thread.currentThread().getName()+" 进入了数据区 "+"当前读者数："+My.Reader_Cnt);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"进入等待！");
            My.Reader_Cnt--;
            if(My.Reader_Cnt==0){
                System.out.println(Thread.currentThread().getName()+" 出来了,后面的写者可以进入");
                My.mutex.release();
            }
            My.sR.release();
        }
    }
}