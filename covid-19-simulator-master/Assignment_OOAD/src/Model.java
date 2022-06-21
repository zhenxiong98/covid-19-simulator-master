import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

//this is the model class of MVC design pattern
public class Model {

    //This is the Model which is simulator class
    private static ArrayList<Member> memberList;//create array list to store all the member
    private static paintLabelStrategy paintStrategy; //this is the paint strategy interface, which using Strategy design method to decide what colour should be painted on the particular label
    private static final Map<Member, Integer> memberPosition = new HashMap<Member, Integer>(); //Mapping postion to the member, using map because it can search faster
    private static final ArrayList<Integer> quarantineWall = new ArrayList<>(); //Keeping track of which postion has wall so member won't move to it
    private static int iteration; //number of iteration
    private static int population; //number of population
    private static int size; //size of the world
    private static int initialInfected; //initial infected member
    private static int chronicProb; // to store the chronic disease probability
    private static int dead; //dead member counter



    //a method is to create the member list
    public static void createMemberList(int p, int i, int cdprb){
        memberList = generatePopulation(p, i, cdprb);
    }

    //a method that create member
    public static Member createMember(String hc,boolean cdprob) {
        String uniqueID = UUID.randomUUID().toString(); //using UUID to get unique ID
        Age ageGroup = generateAgeGroup(); //generate age group base on age enum
        int age = generateAge(ageGroup); //generate age
        int el = generateExpectedLife(age, ageGroup); //generate expected life
        age*=365; //convert age to years for easy calculation
        el*=365;    //convert age to years for easy calculation

        Member member = MemberFactory.getMember(hc, uniqueID, age, el,cdprob); //create member using Member Factory

        return member;
    }

    //method to generate age randomly using age enum
    public static int generateAge(Age age) {
        return age.getMin() + (int) Math.round(Math.random() * (age.getMax() - age.getMin()));
    }

    //random generate age group of member
    public static Age generateAgeGroup() {
        int temp = 1 + (int) Math.round(Math.random() * (4 - 1));
        switch (temp) {
            case 1:
                return Age.SENIOR;
            case 2:
                return Age.ADULT;
            case 3:
                return Age.YOUTH;
            case 4:
                return Age.CHILD;
        }
        return null;
    }

    //randomly add number to age base on age group to generate expected life
    public static int generateExpectedLife(int age, Age ageGroup) {
        switch (ageGroup) {
            case SENIOR:
                return age + (0 + (int) Math.round(Math.random() * (20 - 0)));
            case ADULT:
                return age + (5 + (int) Math.round(Math.random() * (40 - 5)));
            case YOUTH:
                return age + (20 + (int) Math.round(Math.random() * (60 - 20)));
            case CHILD:
                return age + (35 + (int) Math.round(Math.random() * (75 - 35)));
        }

        return age;
    }

    //generate chronic disease probability and then return will the user get chronic disease base on probability
    public static boolean chronicDiseaseProb(int num) {
        ArrayList<String> temp = new ArrayList<>();
        num/=10;
        for (int i = 0; i < num; i++) {
            temp.add("chronic");
        }

        num = 10 - num;

        for (int i = 0; i < num; i++) {
            temp.add("healthy");
        }

        Random generator = new Random();
        int randomIndex = generator.nextInt(temp.size());
        return !(temp.get(randomIndex) == "chronic");

    }

    //generate the initial population
    public static ArrayList<Member> generatePopulation(int population, int infected, int cdProb) {
        ArrayList<Member> temp = new ArrayList<>(population);

        //generate the infected member first
        for (int i = 0; i < infected; i++) {
            temp.add(createMember("Infected",chronicDiseaseProb(cdProb)));
        }

        //rest of the member is generated healthy
        for (int i = 0; i < population-infected; i++) {

            temp.add(createMember("Healthy",chronicDiseaseProb(cdProb)));
        }

        return temp;
    }

    //place the member randomly at random position
    public static void placeMember(ArrayList<JLabel> pa) {
        Random generator = new Random();
        List<Integer> randomPosition = generator.ints(0, size*size).distinct().limit(memberList.size()).boxed().collect(Collectors.toList());
        for (int i = 0; i < memberList.size(); i++) {
            memberPosition.put(memberList.get(i), randomPosition.get(i));

            //paint the label using paint strategy
            if (memberList.get(i).getC19Positive()) {
                paintStrategy = new paintInfectedMemberStrategy();
            } else {
                paintStrategy = new paintHealthyMemberStrategy();
            }

            paintStrategy.paint(pa.get(memberPosition.get(memberList.get(i))));
        }

    }

    //move the member
    public static void move(Member member, ArrayList<JLabel> pa) {

        boolean flag = true;
        int counter = 0;
        int origin;
        int destination;

        //if the member is not quarantined
        if(member.getQuarantine()==false) {
            do {
                Random r = new Random();
                int temp =r.nextInt((4 - 1) + 1) + 1; // 1 = move upwards , 2 = move right, 3 = move downward, 4 move left, Randomly choose the direction to move to
                switch (temp) {
                    case 1:
                        counter++;
                        origin = memberPosition.get(member);
                        destination = origin - size;

                        if (checkOutOfTopBorder(destination)) {
                            break;
                        }
                        if (memberPosition.containsValue(destination)) {
                            break;
                        }
                        if (quarantineWall.contains(destination)) {
                            break;
                        }
                            successfullyMove(member, destination, pa);
                            flag = false;

                        break;

                    case 2:
                        counter++;
                        origin = memberPosition.get(member);
                        destination = origin + 1;

                        if (checkOutOfRightBorder(destination)) {
                            break;
                        }
                        if (memberPosition.containsValue(destination)) {
                            break;
                        }
                        if (quarantineWall.contains(destination)) {
                            break;
                        }

                        successfullyMove(member, destination, pa);
                        flag = false;

                        break;

                    case 3:
                        counter++;
                        origin = memberPosition.get(member);
                        destination = origin + size;

                        if (checkOutOfBottomBorder(destination)) {
                            break;
                        }
                        if (memberPosition.containsValue(destination)) {
                            break;
                        }
                        if (quarantineWall.contains(destination)) {
                            break;
                        }

                        successfullyMove(member, destination, pa);
                        flag = false;

                        break;

                    case 4:
                        counter++;
                        origin = memberPosition.get(member);
                        destination = origin - 1;

                        if (checkOutOfLeftBorder(destination)) {
                            break;
                        }
                        if (memberPosition.containsValue(destination)) {
                            break;
                        }
                        if (quarantineWall.contains(destination)) {
                            break;
                        }

                        successfullyMove(member, destination, pa);
                        flag = false;

                        break;
                }
            } while (flag && (counter <=5)); //the method will only run for 4 times because if there is no place to move to it will run forever
        }
        else
            System.out.println("You are quarantined, can't move");
    }

    //create new infected member when it get infected by other
    public static void gainInfected(Member member,ArrayList<JLabel> pa){
        Member newMember = MemberFactory.getMember("Infected",member.getID(), member.getAge(), member.getExpectedLife(),member.getHealth());
        int temp=memberPosition.get(member);
        memberPosition.put(newMember,temp);
        memberPosition.remove(member);
        memberList.add(newMember);
        memberList.remove(member);


        paintLabelStrategy paintStrategy = new paintInfectedMemberStrategy();
        paintStrategy.paint(pa.get(memberPosition.get(newMember)));
    }

    //to let member decide to quarantine or not base on 50% probability
    public static boolean selfQuarantine(Member m, ArrayList<JLabel> pa) {
        int counter=0;
        int temp = (int) Math.round(Math.random() * (1)); //0 indicate no, 1 indicate yes

        if (temp == 1) {
            do {
                int position = memberPosition.get(m);

                //initiate the position that going to be quarantine wall
                    int upPosition = position - size;
                    int downPosition = position + size;
                    int leftPosition = position - 1;
                    int rightPosition = position + 1;

                    int upLeft = upPosition -1;
                    int upRight = upPosition +1;

                    int downLeft = downPosition -1;
                    int downRight = downPosition +1;

                    counter++;


                    if (!memberPosition.containsValue(upPosition) && !memberPosition.containsValue(upRight) && !memberPosition.containsValue(upLeft) && !memberPosition.containsValue(leftPosition)
                            && !memberPosition.containsValue(rightPosition) && !memberPosition.containsValue(downPosition) && !memberPosition.containsValue(downRight) && !memberPosition.containsValue(downLeft)) {


                        paintStrategy = new paintQuarantineMemberStrategy();
                        paintStrategy.paint(pa.get(memberPosition.get(m)));


                        paintStrategy = new paintQuarantineWallStrategy();
                        if(!checkOutOfTopBorder(upPosition)){
                            paintStrategy.paint(pa.get(upPosition));
                            quarantineWall.add(upPosition);

                            if(!checkOutOfRightBorder(upRight)){
                                paintStrategy.paint(pa.get(upRight));
                                quarantineWall.add(upLeft);
                            }

                            if(!checkOutOfLeftBorder(upLeft)) {
                                paintStrategy.paint(pa.get(upLeft));
                                quarantineWall.add(upLeft);
                            }

                        }

                        if(!checkOutOfLeftBorder(leftPosition)) {
                            paintStrategy.paint(pa.get(leftPosition));
                            quarantineWall.add(leftPosition);
                        }

                        if(!checkOutOfRightBorder(rightPosition)) {
                            paintStrategy.paint(pa.get(rightPosition));
                            quarantineWall.add(upRight);
                        }

                        if(!checkOutOfBottomBorder(downPosition)) {
                            paintStrategy.paint(pa.get(downPosition));
                            quarantineWall.add(downPosition);

                            if(!checkOutOfRightBorder(downRight)) {
                                paintStrategy.paint(pa.get(downRight));
                                quarantineWall.add(downRight);
                            }

                            if(!checkOutOfLeftBorder(downLeft)) {
                                paintStrategy.paint(pa.get(downLeft));
                                quarantineWall.add(downLeft);
                            }
                        }
                        return true;
                    }
            } while (counter<=5); //the member will find if there is enough space to quarantine for 5times, if there is no space it will stop finding for this iteration.

        }
        return false;
    }

    //declare immunity for member that quarantined for 14 days and not dead
    public static void gainImmunity(Member member, ArrayList<JLabel> pa){
        Member newMember = MemberFactory.getMember("Immuned",member.getID(), member.getAge(), member.getExpectedLife(),member.getHealth());
        int temp=memberPosition.get(member);
        memberPosition.put(newMember,temp);
        memberPosition.remove(member);
        memberList.add(newMember);
        memberList.remove(member);

        paintLabelStrategy paintStrategy = new paintImmunedMemberStrategy();
        paintStrategy.paint(pa.get(memberPosition.get(newMember)));

        discharge(newMember,pa);
    }

    //discharge a member whether he is recover or dead, free out the quarantine wall to blank space
    public static void discharge(Member member, ArrayList<JLabel> pa){
        int position = memberPosition.get(member);

        int upPosition = position - size;
        int upLeft = upPosition -1;
        int upRight = upPosition +1;
        int downPosition = position + size;
        int downLeft = downPosition -1;
        int downRight = downPosition +1;
        int leftPosition = position - 1;
        int rightPosition = position + 1;



            paintStrategy = new paintBlankStrategy();
            if(!checkOutOfTopBorder(upPosition)){
                paintStrategy.paint(pa.get(upPosition));
                quarantineWall.remove(Integer.valueOf(upPosition));

                if(!checkOutOfRightBorder(upRight)){
                    paintStrategy.paint(pa.get(upRight));
                    quarantineWall.remove(Integer.valueOf(upLeft));
                }

                if(!checkOutOfLeftBorder(upLeft)) {
                    paintStrategy.paint(pa.get(upLeft));
                    quarantineWall.remove(Integer.valueOf(upLeft));
                }

            }

            if(!checkOutOfLeftBorder(leftPosition)) {
                paintStrategy.paint(pa.get(leftPosition));
                quarantineWall.remove(Integer.valueOf(leftPosition));
            }

            if(!checkOutOfRightBorder(rightPosition)) {
                paintStrategy.paint(pa.get(rightPosition));
                quarantineWall.remove(Integer.valueOf(upRight));
            }

            if(!checkOutOfBottomBorder(downPosition)) {
                paintStrategy.paint(pa.get(downPosition));
                quarantineWall.remove(Integer.valueOf(downPosition));

                if(!checkOutOfRightBorder(downRight)) {
                    paintStrategy.paint(pa.get(downRight));
                    quarantineWall.remove(Integer.valueOf(downRight));
                }

                if(!checkOutOfLeftBorder(downLeft)) {
                    paintStrategy.paint(pa.get(downLeft));
                    quarantineWall.remove(Integer.valueOf(downLeft));
                }
            }
        }

        //kill the member if he meet dead requirement
    public static void killMember(Member member,ArrayList<JLabel> pa){
        System.out.println(member.getID()+" just passed away...");
        if(member.getQuarantine())
            discharge(member,pa);

        paintStrategy = new paintBlankStrategy();
        paintStrategy.paint(pa.get(memberPosition.get(member)));


        memberPosition.remove(member);
        memberList.remove(member);
        population--;
        dead++;
    }

    //in move() method when the member allow to move, it call this method to move the member
    public static void successfullyMove(Member member, int destination, ArrayList<JLabel> pa) {
        int temp=memberPosition.get(member);
        paintStrategy = new paintBlankStrategy();
        paintStrategy.paint(pa.get(temp));

        memberPosition.replace(member, destination);
        if (member.getC19Positive()) {
            paintStrategy = new paintInfectedMemberStrategy();
        }
        else if(member.getC19immune()){
            paintStrategy = new paintImmunedMemberStrategy();
        }
        else{
            paintStrategy = new paintHealthyMemberStrategy();
        }
        paintStrategy.paint(pa.get(memberPosition.get(member)));


    }

    //check if meet top border by checking if is negative
    public static boolean checkOutOfTopBorder(int temp) {
        return temp < 0;

    }

    //check if meet bottom border by checking if is exceed the dimension of the world
    public static boolean checkOutOfBottomBorder(int temp) {
        return temp >= size*size;
    }

    //check if meet left border by modulus the size of the world
    public static boolean checkOutOfLeftBorder(int temp) {
        return ((temp + 1) % size) == 0;
    }

    //check if meet right border by checking if target label is equal to 0 when modulus by world size
    public static boolean checkOutOfRightBorder(int temp) {
        return temp % size == 0;
    }

    //check if there is any infected member around, if it is yes the member will get infected else vice versa
    public static boolean checkIfNeighbourC19Positive(Member member) {
        // 1 = check upwards , 2 = check right, 3 = check downward, 4 check left
        int origin = memberPosition.get(member);
        int destination;
        Member temp = null;

        for (int i = 1; i <= 4; i++) {
            switch (i) {
                case 1:
                    destination = origin - size;
                    if (checkOutOfTopBorder(destination))
                        break;
                    else {
                        if (memberPosition.containsValue(destination)) {
                            Set entry = getKeysByValue(memberPosition, destination);
                            for (Object o : entry) {
                                temp = (Member) o;
                            }

                            if (temp.getC19Positive()) {
                                return true;
                            } else
                                break;

                        } else
                            break;
                    }

                case 2:
                    destination = origin + 1;
                    if (checkOutOfRightBorder(destination))
                        break;
                    else {
                        if (memberPosition.containsValue(destination)) {
                            Set entry = getKeysByValue(memberPosition, destination);
                            for (Object o : entry) {
                                temp = (Member) o;
                            }

                            if (temp.getC19Positive()) {
                                return true;
                            } else
                                break;

                        } else
                            break;
                    }

                case 3:
                    destination = origin + size;
                    if (checkOutOfBottomBorder(destination))
                        break;
                    else {
                        if (memberPosition.containsValue(destination)) {
                            Set entry = getKeysByValue(memberPosition, destination);
                            for (Object o : entry) {
                                temp = (Member) o;
                            }

                            if (temp.getC19Positive()) {
                                return true;
                            } else
                                break;

                        } else
                            break;
                    }

                case 4:
                    destination = origin - 1;
                    if (checkOutOfLeftBorder(destination))
                        break;
                    else {
                        if (memberPosition.containsValue(destination)) {
                            Set entry = getKeysByValue(memberPosition, destination);
                            for (Object o : entry) {
                                temp = (Member) o;
                            }

                            if (temp.getC19Positive()) {
                                return true;
                            } else
                                break;

                        } else
                            break;
                    }
            }
        }
        return false;
    }

    //check if the member meet discharge requirement
    public static boolean checkRecovery(Member member){
        return member.getQuarantineDays()>14;
    }

    //check if the member meet dead condition
    public static boolean checkDieCondition(Member member,int deathProb){

        if(member.getC19InfectedDays()>=10 && deathProb>=80 && !member.getHealth())
            return true;
        if(member.getAge()>member.getExpectedLife())
            return true;
        return false;
    }

    //method that use the retrieve the member key by value, since the relationship between member and position is one to one, this method is enought to use
    public static <Member, Integer> Set<Member> getKeysByValue(Map<Member, Integer> map, Integer value) {
        return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    //print the member list for debug purpose
    public static void printArrayList() {
        for (Member member : memberList) {
            System.out.println(member);
        }
    }

    //set population
    public static void setPopulation(int p){
        population=p;
    }

    //set chronic probability
    public static void setChronicProb(int c) {
        chronicProb = c;
    }

    //set iteration count
    public static void setIteration(int i){
        iteration=i;
    }

    //set the world size
    public static void setSize(int s){
        size=s;
    }

    //set initial infected member
    public static void setInitialInfected(int i){
        initialInfected=i;
    }

    //return size
    public static int getSize(){
        return size;
    }

    //retrun interation
    public static int getIteration(){
        return iteration;
    }

    //return population
    public static int getPopulation(){
        return population;
    }

    //return initial infected member
    public static int getInitialInfected(){
        return initialInfected;
    }

    //return chronic probability
    public static int getChronicProb(){
        return chronicProb;
    }

    //return the member array list
    public static ArrayList<Member> getMemberList(){
        return memberList;
    }

    //return the healthy member count
    public static int getHealthyMemberCount(){
        int temp=0;
        for(int i=0;i<memberList.size();i++){
            if(!memberList.get(i).getC19Positive())
                temp++;
        }
        return temp;
    }

    //return immuned member count
    public static int getImmunedMemberCount(){
        int temp=0;
        for(int i=0;i<memberList.size();i++){
            if(memberList.get(i).getC19immune())
                temp++;
        }
        return temp;
    }

    //return dead count
    public static int getDead(){
        return dead;
    }

    //return quarintined member count
    public static int getQuarantinedMember(){
        int temp=0;
        for(int i=0;i<memberList.size();i++){
            if(memberList.get(i).getQuarantine())
                temp++;
        }
        return temp;
    }
}
