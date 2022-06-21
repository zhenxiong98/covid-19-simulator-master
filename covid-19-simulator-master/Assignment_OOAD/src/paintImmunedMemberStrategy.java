import javax.swing.*;
import java.awt.*;

//a class that implement paintLabelStrategy interface, paint yellow when the label has immuned member on it
public class paintImmunedMemberStrategy implements paintLabelStrategy{
    @Override
    public void paint(JLabel targetLabel){
        targetLabel.setOpaque(true);
        targetLabel.setBackground(Color.yellow);

    }
}