package day03;

public class Rectangle  {
    public static void main(String[] args) {
        int n = 5;
        int i = 1;

        // 삼각형 모양의 별찍기
        while (i <= n) {
            int j = 1; // 해당 행의 별 개수
            while (j <= i) {
                System.out.print("*");
                j++;
            }
            System.out.println(); // 다음 행으로 이동
            i++; // 행 번호 증가
        }
    }
}
