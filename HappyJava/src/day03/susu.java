package day03;

import java.util.Scanner;

public class susu {
    /*
     * 4.5 이하의 평점을 입력받아 그 값에 따라 다음과 같이 출력하는 프로그램을 작성하시오.(switch ~ case문 사용)
    단 입력는 0이상 4.5 이하이다.
    4.0 이상 : “장학금을 받아가세요.”
    3.0 이상 : “다음 학기를 준비하세요.”
    2.0 이상 : “계절 학기에 등록하세요.”
    2.0 미만 : “다음 학기에 재수강하세요.”
    입력예:3.5
    출력예:다음 학기를 준비하세요.
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Scanner keyboard = new Scanner(System.in);

        System.out.println("양의 정수 입력하세요");

        int sum =0;
        int input;

        do {
            input = keyboard.nextInt();
            sum += input;}

        while (input != 0);
        System.out.println("입력한 양의 정수들의 총합 값: "+ sum );
    }


}