package baby02;

import java.util.Scanner;

public class AgeChecker2 {
    public static void checker(int age) {
        if (age < 18) {
            System.out.println("미성년자입니다");
        } else
            System.out.println("성인입니다");
    }

    public static void main(String[] args) {

        int age = Integer.parseInt(args[0]);

//        AgeChecker ageChecker = new AgeChecker();
//        ageChecker.checker(age);

        AgeChecker.checker(age);
    }
}


