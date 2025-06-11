import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SimpleLibraryManagement extends JFrame {
	public static ArrayList<Book> books = new ArrayList<>();

	public static JTextArea displayArea = new JTextArea();

	public Services services = new Services();

	public SimpleLibraryManagement() {
		setTitle("簡単な図書館の管理リスト");
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(10, 10));

		// Display Area
		displayArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(displayArea);

		add(new PanelSearch(), BorderLayout.PAGE_START);
		add(new PanelFill(), BorderLayout.SOUTH);
		add(scrollPane, BorderLayout.CENTER);

		services.loadBooksFromFile(this);
		services.displayBooks();

		setVisible(true);
	}

}
