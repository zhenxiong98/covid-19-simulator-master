import javax.swing.*;
import java.awt.*;

//a class that implement paintLabelStrategy interface, paint white when the label is blank
public class paintBlankStrategy implements paintLabelStrategy{
    @Override
    public void paint(JLabel targetLabel){
        targetLabel.setOpaque(true);
        targetLabel.setBackground(Color.white);
    }
}
