import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this is the controller class of MVC design pattern which retireve the value from view and send to model
public class Controller implements ActionListener {
    private Model model;

    JTextField population;
    JTextField size;
    JTextField iteration;
    JTextField cdprob;
    JTextField initialInfected;
    
    public Controller(JTextField population, JTextField size, JTextField iteration, JTextField cdprob, JTextField initialInfected, Model m) {
        this.model=m;
        this.population=population;
        this.size=size;
        this.iteration=iteration;
        this.cdprob=cdprob;
        this.initialInfected=initialInfected;

    }

    //button to parse the information of user input at view and send to model
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if(Integer.parseInt(population.getText())>(Integer.parseInt(size.getText())*Integer.parseInt(size.getText()))){
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Population can't be greater than size!","Error!",JOptionPane.WARNING_MESSAGE);
        }
        else if(Integer.parseInt(initialInfected.getText())>Integer.parseInt(population.getText())){
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Initial infected person can't be greater than total population!","Error!",JOptionPane.WARNING_MESSAGE);
        }
        else if(Integer.parseInt(initialInfected.getText())>Integer.parseInt(size.getText())*Integer.parseInt(size.getText())){
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Initial infected person can't be greater than the size!","Error!",JOptionPane.WARNING_MESSAGE);
        }
        else {
            model.setPopulation(Integer.parseInt(population.getText()));
            model.setSize(Integer.parseInt(size.getText()));
            model.setIteration(Integer.parseInt(iteration.getText()));
            model.setChronicProb(Integer.parseInt(cdprob.getText()));
            model.setInitialInfected(Integer.parseInt(initialInfected.getText()));

            source.getRootPane().getParent().setVisible(false);
        }

        //initiate the world with the user input and run the simulation
        WorldView w = new WorldView();
        w.mapInitialize();
        w.run();
    }

}
