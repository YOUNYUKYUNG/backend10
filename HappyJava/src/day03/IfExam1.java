package day03;

public class IfExam1 {
    public static void main(String[] args){
        char ch= 0;
        try {
            ch = 'a';
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (ch=='a' || ch=='A'){
            System.out.println("a입니다");
        }else if(ch=='b' || ch=='B') {
            System.out.println("B입니다");
        }else if(ch=='c' || ch=='C') {
            System.out.println("C입니다");
        }else{
            System.out.println("A,B,C가 아닙니다.");
        }
    }
}