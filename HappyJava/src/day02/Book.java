package day02;

public class Book {
    /*
title
price 를 필드로 선언하였습니다.
*/
    private String title;
    private int price; // field price
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
