package baby02;

import java.util.Scanner;

public class MonthChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("월 입력");
        int month = scanner.nextInt();

        switch (month) {
            case 1:
                System.out.println("1월");
                break;
            case 2:
                System.out.println("2월");
                break;
            case 3:
                System.out.println("3월");
                break;
            case 4:
                System.out.println("4월");
                break;
            case 5:
                System.out.println("5월");
                break;

            default:
                System.out.println("없음");

        }

    }
}
