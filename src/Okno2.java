import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Okno2 extends JFrame {
    JButton b1;
    public static JTextField otrzymane;
    public static String binar;
    public static String[] litery;

    Okno2() {
        JFrame f1 = new JFrame("Okno2");
        f1.setLocation(900,0);
        ImageIcon img = new ImageIcon("bg2.jpg");
        JLabel background = new JLabel("", img, 0);
        background.setBounds(0, 0, 1544, 766);
        f1.add(background);
        f1.setSize(900, 700);
        f1.setLayout((LayoutManager)null);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(3);
        otrzymane = new JTextField();
        background.add(otrzymane);
        otrzymane.setBounds(300, 400, 200, 70);
    }
}
