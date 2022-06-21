//this is the member abstract class, it is following the Factory Method design

public abstract class Member {
    //all the abstract method
    public abstract String getID();
    public abstract int getAge();
    public abstract int getExpectedLife();
    public abstract boolean getHealth();
    public abstract boolean getC19Positive();
    public abstract int getQuarantineDays();
    public abstract boolean getQuarantine();
    public abstract boolean getC19immune();
    public abstract int getC19InfectedDays();
    public abstract void setC19Positive(boolean c19Positive);
    public abstract void setQuarantineDays(int quarantineDays);
    public abstract void setQuarantine(boolean quarantinePositive);
    public abstract void setC19immune(boolean c19immune);
    public abstract void setC19InfectedDays(int c19InfectedDays);



    //override the toString() to print the member
    @Override
    public String toString(){
        return "ID: "+this.getID()+",Age: "+this.getAge()/365+",Expected Life: "+
                this.getExpectedLife()/365+",Health Condition: "+this.getHealth()+",Covid-19 Positive: "+getC19Positive()
                    +" Quarantined days: "+getQuarantineDays()+" Covid-19 immunity: "+getC19immune();
    }
}
