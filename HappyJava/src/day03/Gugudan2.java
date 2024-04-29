package day03;

public class Gugudan2 {
    public static void main(String[] args){
        for(int k = 1; k <= 9; k++) {

            for (int i = 1; i <= 9; i++) {
                System.out.print(i + " * " + k + " = " + (k * i)+"\t");
            }
            System.out.println();

        }

    }
}