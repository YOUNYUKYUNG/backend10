package ch02;

public class DuplicateArray {
    public static void main(String[] args) {
        int[] original = {10,20,30,40,50};
        int [] copied = original.clone();

        copied[2] = 0;

        System.out.println(original[2]);
        System.out.println(copied[2]);
    }
}
