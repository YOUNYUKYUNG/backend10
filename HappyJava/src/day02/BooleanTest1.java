package day02;

public class BooleanTest1 {
    public static void main(String[] args){
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        boolean flag5 = false;
        flag1 = 10 > 5;
        flag2 = 10 < 5;
        flag3 = 10 >= 10;
        flag4 = 10 <= 10;
        flag5 = 10 == 10;
        System.out.println(flag1);
        System.out.println(flag2);
        System.out.println(flag3);
        System.out.println(flag4);
        System.out.println(flag5);
        System.out.println();

        boolean flag11 = false;
        boolean flag22 = false;
        boolean flag33 = false;
        boolean flag44 = false;
        boolean flag55 = false;
        boolean flag66 = false;
        flag11 = 10 > 5 && 5 < 20;
        flag22 = 10 > 5 & 5 < 20;
        flag33 = 10 >= 10 || 5 > 6;
        flag44 = 10 >= 10 | 5 > 6;
        flag55 = 10 == 10 ^ 5 == 4;
        flag66 = !flag55;
        System.out.println(flag11);
        System.out.println(flag22);
        System.out.println(flag33);
        System.out.println(flag44);
        System.out.println(flag55);
        System.out.println(flag66);
    }
}
