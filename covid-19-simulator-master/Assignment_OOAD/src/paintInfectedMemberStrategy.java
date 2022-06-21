import javax.swing.*;
import java.awt.*;

//a class that implement paintLabelStrategy interface, paint blue when the label has infected member on it
public class paintInfectedMemberStrategy implements paintLabelStrategy{
    @Override
    public void paint(JLabel targetLabel){
        targetLabel.setOpaque(true);
        targetLabel.setBackground(Color.BLUE);

    }
}
