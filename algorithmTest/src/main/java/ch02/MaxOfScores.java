package ch02;

import java.util.Scanner;

class MaxOfScores {
    // 배열 scores의 최댓값을 구하여 반환하는 메소드
    static int maxOf(int[] scores) {
        int max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("점수의 최댓값을 구합니다.");
        System.out.print("학생수: ");
        int num = stdIn.nextInt(); // 입력받은 학생수로 배열의 크기를 결정
        int[] scores = new int[num]; // 학생수만큼의 점수를 저장할 배열 생성
        for (int i = 0; i < num; i++) {
            System.out.print("scores[" + i + "] : ");
            scores[i] = stdIn.nextInt(); // 각 학생의 점수를 입력받아 배열에 저장
        }
        System.out.println("최댓값은 " + maxOf(scores) + "입니다.");
    }
}

