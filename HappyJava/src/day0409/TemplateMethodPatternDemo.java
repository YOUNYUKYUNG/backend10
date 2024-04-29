package day0409;

public class TemplateMethodPatternDemo {
    public static void main(String[] args) {
        BeverageRecipe tea = new Tea();
        tea.prepareRecipe();

        BeverageRecipe coffee = new Coffee();
        coffee.prepareRecipe();
    }
}
