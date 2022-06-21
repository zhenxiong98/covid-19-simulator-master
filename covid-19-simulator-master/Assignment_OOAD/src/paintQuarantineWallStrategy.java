import javax.swing.*;
import java.awt.*;

//a class that implement paintLabelStrategy interface, paint cyan when the label is has quarantine wall on it
public class paintQuarantineWallStrategy implements paintLabelStrategy{
    @Override
    public void paint(JLabel targetLabel){
        targetLabel.setBackground(Color.CYAN);
        targetLabel.setOpaque(true);
    }
}
