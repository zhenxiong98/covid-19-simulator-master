import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

//this is the class that create the map of the world
public class playArea extends JPanel {

    ArrayList<JLabel> label ;
    public playArea(int size) {
        label = new ArrayList<>(size);
        int num=size*size;

        setLayout(new GridLayout(size,size));
        Border border = BorderFactory.createLineBorder(Color.BLACK,1);

        for(int i=0; i<num; i++){
            label.add(new JLabel());

            label.get(i).setBorder(border);
            label.get(i).setPreferredSize(new Dimension(25, 25));

            add(label.get(i));
        }
    }

    public ArrayList<JLabel> getLabelList(){
        return label;
    }

    @Override
        public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
    }
}
