import javax.swing.*;
import java.awt.*;

//this is the map that create the statistic panel on the right side of the simulator
public class statisticArea extends JPanel {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    JLabel populationlabel1;
    JLabel populationlabel2;
    JLabel infectedLabel1;
    JLabel infectedLabel2;
    JLabel iterationLabel1;
    JLabel iterationLabel2;
    JLabel healthyCount1;
    JLabel healthyCount2;
    JLabel immunedCount1;
    JLabel immunedCount2;
    JLabel quarantinedCount1;
    JLabel quarantinedCount2;
    JLabel deadCount1;
    JLabel deadCount2;
    JLabel redLabel1;
    JLabel redLabel2;
    JLabel blueLabel1;
    JLabel blueLabel2;
    JLabel greenLabel1;
    JLabel greenLabel2;
    JLabel yellowLabel1;
    JLabel yellowLabel2;
    JLabel cyanLabel1;
    JLabel cyanLabel2;

    public statisticArea(){
        if (RIGHT_TO_LEFT) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }


        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3,3,3,3);

        if (shouldFill)
            c.fill = GridBagConstraints.HORIZONTAL;

        greenLabel1 = new JLabel();
        greenLabel1.setOpaque(true);
        greenLabel1.setBackground(Color.green);
        greenLabel1.setPreferredSize(new Dimension(50,30));
        if (shouldWeightX)
            c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(greenLabel1, c);

        greenLabel2 = new JLabel("Green indicate healthy member");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        add(greenLabel2, c);

        blueLabel1 = new JLabel();
        blueLabel1.setOpaque(true);
        blueLabel1.setBackground(Color.blue);
        blueLabel1.setPreferredSize(new Dimension(50,30));
        if (shouldWeightX)
            c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        add(blueLabel1, c);

        blueLabel2 = new JLabel("Blue indicate infected member");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        add(blueLabel2, c);

        yellowLabel1 = new JLabel();
        yellowLabel1.setOpaque(true);
        yellowLabel1.setBackground(Color.yellow);
        yellowLabel1.setPreferredSize(new Dimension(50,30));
        if (shouldWeightX)
            c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        add(yellowLabel1, c);

        yellowLabel2 = new JLabel("Yellow indicate immuned member");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        add(yellowLabel2, c);

        redLabel1 = new JLabel();
        redLabel1.setOpaque(true);
        redLabel1.setBackground(Color.RED);
        redLabel1.setPreferredSize(new Dimension(50,30));
        if (shouldWeightX)
            c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        add(redLabel1, c);

        redLabel2 = new JLabel("Red indicate quarantined member");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        add(redLabel2, c);

        cyanLabel1 = new JLabel();
        cyanLabel1.setOpaque(true);
        cyanLabel1.setBackground(Color.cyan);
        cyanLabel1.setPreferredSize(new Dimension(50,30));
        if (shouldWeightX)
            c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        add(cyanLabel1, c);

        cyanLabel2 = new JLabel("Cyan indicate quarantine wall");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        add(cyanLabel2, c);

        populationlabel1 = new JLabel("Current population: ");
        if (shouldWeightX)
            c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        add(populationlabel1, c);

        populationlabel2 = new JLabel();
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        add(populationlabel2, c);

        healthyCount1 = new JLabel("Healthy member: ");
        if (shouldWeightX)
            c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        add(healthyCount1, c);

        healthyCount2 = new JLabel();
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 6;
        add(healthyCount2, c);

        infectedLabel1 = new JLabel("Current infected member: ");
        if (shouldWeightX)
            c.weightx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 7;
        add(infectedLabel1, c);

        infectedLabel2 = new JLabel();
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 7;
        add(infectedLabel2, c);

        immunedCount1 = new JLabel("Current immuned member: ");
        if (shouldWeightX)
            c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 8;
        add(immunedCount1, c);

        immunedCount2 = new JLabel();
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 8;
        add(immunedCount2, c);


        quarantinedCount1 = new JLabel("Current quarantined member: ");
        if (shouldWeightX)
            c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 9;
        add(quarantinedCount1, c);

        quarantinedCount2 = new JLabel();
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 9;
        add(quarantinedCount2, c);

        deadCount1 = new JLabel("Current dead member: ");
        if (shouldWeightX)
            c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 10;
        add(deadCount1, c);

        deadCount2 = new JLabel();
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 10;
        add(deadCount2, c);

        iterationLabel1 = new JLabel("Current iteration: ");
        if (shouldWeightX)
            c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 11;
        add(iterationLabel1, c);

        iterationLabel2 = new JLabel();
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 11;
        add(iterationLabel2, c);


    }
}
