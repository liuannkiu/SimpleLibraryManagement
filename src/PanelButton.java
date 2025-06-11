import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelButton extends JPanel implements ActionListener {

    public JButton addButton = new JButton("追加");
    public JButton updateButton = new JButton("更新");
    public JButton deleteButton = new JButton("削除");
    public JButton clearButton = new JButton("クリア");

    private Services services = new Services();

    public PanelButton() {
        setLayout(new FlowLayout());

        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);

        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(clearButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            services.addBook(this);
        } else if (e.getSource() == updateButton) {
            services.updateBook(this);
        } else if (e.getSource() == deleteButton) {
            services.deleteBook(this);
        } else if (e.getSource() == clearButton) {
            services.clearFields();
        }

    }
}
