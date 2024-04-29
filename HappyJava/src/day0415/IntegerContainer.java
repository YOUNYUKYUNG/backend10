package day0415;

public class IntegerContainer implements Container<Integer> {
    private Integer value;

    @Override
    public void set(Integer value) {
        this.value = value;
    }

    @Override
    public Integer get() {
        return value;
    }
}