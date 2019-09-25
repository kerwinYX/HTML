import java.util.concurrent.Semaphore;

public class WriterFirst {

    public static void main(String[] args) {
        Thread w1 = new Writing("д��һ��");
        Thread w2 = new Writing("д�߶���");
        Thread r1 = new Reading("����һ��");
        Thread r2 = new Reading("���߶���");
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
                System.out.println(Thread.currentThread().getName()+"����ȴ���");
                My.Writer_Cnt++;
                if(My.Writer_Cnt == 1){
                    My.s2.acquire();
                    System.out.println(Thread.currentThread().getName()+" �ɹ�����,��ס�˺���Ķ���");
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
            System.out.println(Thread.currentThread().getName()+" ������������  "+ My.Writer_Cnt);
            My.mutex.release();
            try {
                My.sW.acquire();
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"����ȴ���");
            My.Writer_Cnt--;
            if(My.Writer_Cnt==0){
                System.out.println(Thread.currentThread().getName()+" �ɹ���ȥ,�ų��˺���Ķ���");
                My.s2.release();
            }
            System.out.println(Thread.currentThread().getName()+"�����ˣ����滹ʣ:"+"��ǰд������"+My.Writer_Cnt);
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
                System.out.println(Thread.currentThread().getName()+"����ȴ���");
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
                System.out.println(Thread.currentThread().getName()+" �ɹ�����,��ס����д��");
            }
            My.sR.release();
            My.s1.release();
            My.s2.release();
            try {
                Thread.sleep(2000);
                My.sR.acquire();
                System.out.println(Thread.currentThread().getName()+" ������������ "+"��ǰ��������"+My.Reader_Cnt);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"����ȴ���");
            My.Reader_Cnt--;
            if(My.Reader_Cnt==0){
                System.out.println(Thread.currentThread().getName()+" ������,�����д�߿��Խ���");
                My.mutex.release();
            }
            My.sR.release();
        }
    }
}