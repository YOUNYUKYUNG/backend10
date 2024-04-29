package day0418;

class MyRunnable implements Runnable { // MyRunnable 은 Thread는 아니다.

    @Override
    public void run() {
        System.out.println("MyRunnable 출발");
        //쓰레드가 해야하는 일
        for (int i = 0; i < 100; i++) {
            System.out.println("MyRunnable 안녕!!!");
        }
        System.out.println("MyRunnable 종료!!!");
    }
}

public class RunnableExam {
    public static void main(String[] args) {
        System.out.println("main출발!!");

        //쓰레드 생성.
        Thread myThread = new Thread(new MyRunnable());
        myThread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("main 안녕!!!");
        }

        System.out.println("main 종료!!");
    }

}