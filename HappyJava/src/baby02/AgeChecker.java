package baby02;

import java.util.Scanner;

public class AgeChecker {
    public static void checker(int age) {
        if (age < 18) {
            System.out.println("미성년자입니다");
        } else
            System.out.println("성인입니다");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("나이를 입력하세요: ");
        int age = scanner.nextInt();


        //int age = 12;

//        AgeChecker ageChecker = new AgeChecker();
//        ageChecker.checker(age);

        AgeChecker.checker(age);
    }
}


