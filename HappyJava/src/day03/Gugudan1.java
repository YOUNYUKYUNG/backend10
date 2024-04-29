package day03;

import java.util.Scanner;

public class Gugudan1 {
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("숫자 입력");
        int count = keyboard.nextInt();
        int sum=0;


    for(int i = 1; i <= count; i++){
        if(i%2==0){
            sum = sum + i;
        }
        System.out.println( "1 부터" +count+" 짝수의 합= " + sum);

    }
}
}
