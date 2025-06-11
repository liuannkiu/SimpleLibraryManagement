import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelSearch extends JPanel implements ActionListener {

	public static JTextField searchField = new JTextField(20);
	public static JButton searchButton = new JButton("検査");

	private Services services = new Services();

	public PanelSearch() {
		setLayout(new FlowLayout(FlowLayout.LEFT));

		searchButton.addActionListener(this);
		add(new JLabel("検査:"));
		add(searchField);
		add(searchButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) {
			String searchTerm = searchField.getText().toLowerCase().trim();

			if (searchTerm.isEmpty()) {
				services.displayBooks();
				return;
			}

			services.displayBooksWithSearch(searchTerm);
		}
	}

}
