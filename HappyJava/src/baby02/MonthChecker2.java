package baby02;

import java.util.Scanner;
public class MonthChecker2 {

public static String checker(int month) {
    switch (month) {
        case 1:
            return "1월";
        case 2:
            return "2월";
        case 3:
            return "3월";
        case 4:
            return "4월";
        case 5:
            return "5월";

        default:
            return "없음";
    }
}


    public static void main(String[] args) {
        int month = 5;

        String msg = MonthChecker2.checker(month);
        System.out.println(msg);
    }
}
