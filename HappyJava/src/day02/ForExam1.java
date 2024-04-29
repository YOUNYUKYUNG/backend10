package day02;

public class ForExam1 {
    public static void main(String[] args){
        int sum = 0;
        
        for(int i = 1; i <= 100; i++){
           // System.out.println(i);//1~100 출력

            if(i%2==0){
                System.out.println(i);//짝수 출력
                sum+=i;
            }
        }
        System.out.println(sum); //짝수 합

    }
}
