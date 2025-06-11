import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelFill extends JPanel {
    public static JTextField titleField = new JTextField();
    public static JTextField authorField = new JTextField();
    public static JTextField isbnField = new JTextField();

    public PanelFill() {
        setLayout(new GridLayout(4, 2, 5, 5));

		setBorder(BorderFactory.createTitledBorder("内容"));

		add(new JLabel("本の名前:"));
		add(titleField);

		add(new JLabel("著作:"));
		add(authorField);

		add(new JLabel("ID:"));
		add(isbnField);
		
		add(new PanelButton());
    }
}
