import javax.swing.*;

// this is a interface of Paint strategy, it is using Strategy design method. It is call when deciding the label will coloured with what colour
public interface paintLabelStrategy {
    void paint(JLabel targetLabel); //paint method
}
