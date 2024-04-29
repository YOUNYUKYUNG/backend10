package day0409;

public abstract class BeverageRecipe {

    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    // 추상 메서드들
    public abstract void brew();
    public abstract void addCondiments();

    // 구현된 메서드들
    public void boilWater() {
        System.out.println("물을 끓입니다.");
    }

    public void pourInCup() {
        System.out.println("컵에 따릅니다.");
    }

}