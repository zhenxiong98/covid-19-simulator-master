import javax.swing.*;
import java.awt.*;

//this is the class that create the user input gui, it is another view class of the MVC
public class FirstView {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;


    public FirstView() {
        JFrame frame = new JFrame();
        frame.setTitle("User input");

        if (RIGHT_TO_LEFT) {
            frame.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        JLabel label;
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3,3,3,3);

        if (shouldFill)
            c.fill = GridBagConstraints.HORIZONTAL;

        label = new JLabel("size of population (n)");
        if (shouldWeightX)
            c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        frame.add(label, c);

        JTextField tPopulation = new JTextField(10);
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        frame.add(tPopulation, c);

        label = new JLabel("size of the world (z x z), input z");
        if (shouldWeightX)
            c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        frame.add(label, c);

        JTextField tWorldSize = new JTextField(10);
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        frame. add(tWorldSize, c);


        label = new JLabel("number of iterations");
        if (shouldWeightX)
            c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        frame. add(label, c);

        JTextField tNumIteration = new JTextField(10);
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        frame.add(tNumIteration, c);

        label = new JLabel("Probability of getting disease(%)");
        if (shouldWeightX)
            c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        frame.add(label, c);

        JTextField tCdProb = new JTextField(10);
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        frame.add(tCdProb, c);

        label = new JLabel("Initial Number of Infected Individuals (I)");
        if (shouldWeightX)
            c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        frame.add(label, c);

        JTextField tInitInfectedIndv = new JTextField(10);
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        frame.add(tInitInfectedIndv, c);

        JButton button = new JButton("Generate");
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 7;

        frame. add(button, c);

        Model m = new Model();
        Controller controller= new Controller(tPopulation,tWorldSize,tNumIteration,tCdProb,tInitInfectedIndv,m);
        button.addActionListener(controller);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400,250);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);
    }
}
