
//this is the sub class of member which create immuned member
public class ImmunedMember extends Member{
    private String ID;
    private int Age;
    private int expectedLife;
    private boolean health;
    private boolean c19Positive;
    private int quarantineDays;
    private boolean c19immune;
    private int c19InfectedDays;
    private boolean quarantined;

    public ImmunedMember(String id, int age, int el,boolean cdprob){
        this.ID=id;
        this.Age=age;
        this.expectedLife=el;
        this.health=cdprob;
        this.c19Positive=false;
        this.quarantineDays=0;
        this.c19immune=true;
        this.c19InfectedDays=0;
        this.quarantined=false;
    }

    //getter and setter
    @Override
    public String getID(){
        return this.ID;
    }

    @Override
    public int getAge(){
        return this.Age;
    }

    @Override
    public int getExpectedLife(){
        return this.expectedLife;
    }

    @Override
    public boolean getHealth(){
        return this.health;
    }

    @Override
    public boolean getC19Positive(){return this.c19Positive;}

    @Override
    public int getQuarantineDays(){return quarantineDays;}

    @Override
    public boolean getC19immune() { return c19immune;}

    @Override
    public int getC19InfectedDays(){return c19InfectedDays;}

    @Override
    public void setC19Positive(boolean c19Positive){this.c19Positive=c19Positive;}

    @Override
    public void setQuarantineDays(int quarantineDays){this.quarantineDays=quarantineDays;}

    @Override
    public void setQuarantine(boolean quarantine){this.quarantined=quarantine;}

    @Override
    public boolean getQuarantine(){return quarantined;}

    @Override
    public void setC19immune(boolean c19immune){this.c19immune=c19immune;}

    @Override
    public void setC19InfectedDays(int c19InfectedDays){this.c19InfectedDays=c19InfectedDays;}

}

