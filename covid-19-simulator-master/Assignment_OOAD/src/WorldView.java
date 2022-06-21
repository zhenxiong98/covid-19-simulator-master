import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class WorldView extends JFrame {
    //This is where the world gui be created
    //this is also the view class of the MVC design pattern

    int timeout = Model.getIteration() * 1000; //get the timeout by multiply the delay with interation number
    int counter; //initiate counter to keep track how many iteration has run
    int currentInfected=Model.getInitialInfected(); //get current infected
    final static int initialPopulation = Model.getPopulation(); //get the population input by user

    private static final playArea pa = new playArea(Model.getSize()); // create the play area as variable to call the class later
    private final statisticArea sa = new statisticArea();   // create the statistic area as variable to call the class later

    Random random = new Random();   //Random class initialization
    int[] deathProbabilityArray={10,20,30,40,50,60,70,80,90,100}; //initiate the death probability


    public WorldView() {
        //creating the World gui
        setTitle("Covid 19 simulator");

        setLayout(new BorderLayout());

        Container c = getContentPane();

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        sa.setBorder(border);
        pa.setBorder(border);

        sa.setPreferredSize(new Dimension(420, 80));
        pa.setPreferredSize(new Dimension(1500, 1000));


        c.add(sa, BorderLayout.EAST);
        c.add(pa, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(1500, 950);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

    }

    //initialize the world
    public static void mapInitialize() {
        Model.createMemberList(Model.getPopulation(), Model.getInitialInfected(), Model.getChronicProb());
        Model.printArrayList();
        Model.placeMember(pa.getLabelList());
    }

    //run() which will run the timer to start the map iteration
    public void run() {
        counter = 0;
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeout > 0) {
                    timeout -= 1000;

                    //every iteration death probability is generate again
                    int deathProbability = deathProbabilityArray[random.nextInt(deathProbabilityArray.length)];


                    //if the neighbour have covid-19 and the member dont have immunity, it will get infected
                    for (int i = 0; i < Model.getMemberList().size(); i++) {
                        if(!Model.getMemberList().get(i).getC19immune()) {
                            if (Model.checkIfNeighbourC19Positive(Model.getMemberList().get(i))) {
                                if (!Model.getMemberList().get(i).getC19Positive()) {
                                    Model.gainInfected(Model.getMemberList().get(i),pa.getLabelList());
                                    currentInfected++;
                                }
                            }
                        }
                    }

                    //check if the infected member want to be quarantine or not at that interation, start quarantine at second iteration
                    if(counter>=2) {
                        for (int i = 0; i < Model.getMemberList().size(); i++) {
                            if (Model.getMemberList().get(i).getC19Positive() && !Model.getMemberList().get(i).getQuarantine()) {
                                if (Model.selfQuarantine(Model.getMemberList().get(i), pa.getLabelList())) {
                                    Model.getMemberList().get(i).setQuarantine(true);
                                }
                            }
                        }
                    }

                    //check if any member recover from the covid-19, he will be declared to discharge from quarantine and gain immunity
                    for (int i = 0; i < Model.getMemberList().size(); i++) {
                        if(Model.getMemberList().get(i).getQuarantine()) {
                            if(Model.checkRecovery(Model.getMemberList().get(i))){
                                Model.gainImmunity(Model.getMemberList().get(i),pa.getLabelList());
                                currentInfected--;
                            }
                        }
                    }

                    //add 1 quarantine day to add quarantined member to keep track on how long they have been quarantined
                    for (int i = 0; i < Model.getMemberList().size(); i++) {
                        if(Model.getMemberList().get(i).getC19Positive() && Model.getMemberList().get(i).getQuarantine()) {
                            Model.getMemberList().get(i).setQuarantineDays(Model.getMemberList().get(i).getQuarantineDays()+1);
                        }
                    }

                    //Check if any member meet death condition, if yes the will be killed by the system
                    for (int i = 0; i < Model.getMemberList().size(); i++) {
                        if(Model.checkDieCondition(Model.getMemberList().get(i),deathProbability)){
                            Model.killMember(Model.getMemberList().get(i),pa.getLabelList());
                            currentInfected--;
                        }
                    }

                    //add 1 infected day to infected member to keep track on how long they have been quarantined
                    for (int i = 0; i < Model.getMemberList().size(); i++) {
                        if(Model.getMemberList().get(i).getC19Positive()) {
                            Model.getMemberList().get(i).setC19InfectedDays(Model.getMemberList().get(i).getC19InfectedDays()+1);
                        }
                    }


                    //move the member that are not in quarantine
                    for (int i = 0; i < Model.getMemberList().size(); i++) {
                        if(!Model.getMemberList().get(i).getQuarantine()) {
                            Model.move(Model.getMemberList().get(i), pa.getLabelList());
                        }
                    }

                    //getiing information to show on the statistic pane
                    sa.populationlabel2.setText(Model.getPopulation() + "");
                    sa.infectedLabel2.setText(currentInfected + "");
                    sa.iterationLabel2.setText(counter + "");
                    sa.healthyCount2.setText(Model.getHealthyMemberCount()+"");
                    sa.immunedCount2.setText(Model.getImmunedMemberCount()+"");
                    sa.deadCount2.setText(Model.getDead()+"");
                    sa.quarantinedCount2.setText(Model.getQuarantinedMember()+"");

                    counter++;
                    setVisible(true);

                    //if everyone is dead, simulator end
                    if(Model.getPopulation()==0) {
                        JOptionPane.showConfirmDialog(null,"All the member is dead!","Exit",JOptionPane.WARNING_MESSAGE);
                        ((Timer) e.getSource()).stop();
                        System.exit(0);
                    }

                    //if everyone is cured, simulator end
                    if(Model.getHealthyMemberCount()+Model.getDead()==initialPopulation) {
                        JOptionPane.showConfirmDialog(null,"Everyone is cured!","Exit",JOptionPane.WARNING_MESSAGE);
                        ((Timer) e.getSource()).stop();
                        System.exit(0);
                    }

                } else {
                    JOptionPane.showConfirmDialog(null,"Iteration run for "+ counter + "times!","Exit",JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                }
            }
        });
        timer.start();
    }

}
