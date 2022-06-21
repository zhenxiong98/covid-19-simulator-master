
//an enum for age, different age group will have different age range
public enum Age {
    SENIOR(60,85),ADULT(40,59),YOUTH(20,39),CHILD(0,19);
    private final int min;
    private final int max;

    Age(int min ,int max) {
        this.min=min;
        this.max=max;
    }

    public int getMin(){
        return this.min;
    }

    public int getMax(){
        return this.max;
    }
}
