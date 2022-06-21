import javax.swing.*;
import java.awt.*;

//a class that implement paintLabelStrategy interface, paint green when the label has healthy member on it
public class paintHealthyMemberStrategy implements paintLabelStrategy{
    @Override
    public void paint(JLabel targetLabel){
        targetLabel.setOpaque(true);
        targetLabel.setBackground(Color.GREEN);

    }
}
