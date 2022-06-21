
//this is the member factory to decide which type of member will be created
public class MemberFactory {
    public static Member getMember(String type, String id, int age, int el,boolean cdprob){
        if("Healthy".equalsIgnoreCase(type)){
            return new HealthyMember(id, age, el,cdprob);
        }
        else if("Infected".equalsIgnoreCase(type)){
            return new InfectedMember(id,age,el,cdprob);
        }
        else if("Immuned".equalsIgnoreCase(type)){
            return new ImmunedMember(id,age,el,cdprob);
        }

        return null;
    }
}
