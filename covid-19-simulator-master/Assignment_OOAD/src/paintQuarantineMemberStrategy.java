import javax.swing.*;
import java.awt.*;

//a class that implement paintLabelStrategy interface, paint red when the label has member quarantined at it
public class paintQuarantineMemberStrategy implements paintLabelStrategy{
    @Override
    public void paint(JLabel targetLabel){
        targetLabel.setBackground(Color.RED);
        targetLabel.setOpaque(true);
    }
}
