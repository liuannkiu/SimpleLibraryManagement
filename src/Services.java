import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Services {
    private ArrayList<Book> books = SimpleLibraryManagement.books;

    private final String FILE_PATH = "library_data.txt";

    public void displayBooks() {
        JTextArea displayArea = SimpleLibraryManagement.displayArea;

        displayArea.setText("BookLibraryList:\n\n");
        for (Book book : books) {
            displayArea.append(book.toString() + "\n");
        }
    }

    public void displayBooksWithSearch(String searchTerm) {
        JTextArea displayArea = SimpleLibraryManagement.displayArea;

        displayArea.setText("SearchResult:\n\n");
        for (Book book : books) {
            if (book.getTitle().matches(searchTerm)) {
                displayArea.append(book.toString() + "\n");
            }
        }
    }

    public void addBook(Component messagePlaceComponent) {

        String title = PanelFill.titleField.getText().trim();
        String author = PanelFill.authorField.getText().trim();
        String isbn = PanelFill.isbnField.getText().trim();

        if (!validateInput(title, author, isbn, messagePlaceComponent)) {
            return;
        }

        for (Book item : books) {
            if (item.getIsbn().equals(isbn)) {
                JOptionPane.showMessageDialog(messagePlaceComponent,
                        "ID 重複!",
                        "NG～～",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        books.add(new Book(title, author, isbn));

        saveBooksToFile(messagePlaceComponent);
        clearFields();
        displayBooks();
        JOptionPane.showMessageDialog(messagePlaceComponent, "追加成功!");
    }

    private void saveBooksToFile(Component messagePlaceComponentWhenError) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Book book : books) {
                writer.println(book.toString());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(messagePlaceComponentWhenError,
                    "保存エラーが発生: " + e.getMessage(),
                    "NG～～",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clearFields() {
        PanelFill.titleField.setText("");
        PanelFill.authorField.setText("");
        PanelFill.isbnField.setText("");
        PanelSearch.searchField.setText("");
    }

    public void updateBook(Component messagePlaceComponent) {

        String title = PanelFill.titleField.getText().trim();
        String author = PanelFill.authorField.getText().trim();
        String isbn = PanelFill.isbnField.getText().trim();

        Book book;

        if (!validateInput(title, author, isbn, messagePlaceComponent)) {
            return;
        }

        for (Book item : books) {
            if (item.getIsbn().equals(isbn)) {
                book = item;

                book.setAuthor(author);
                book.setTitle(title);
                book.setIsbn(isbn);

                saveBooksToFile(messagePlaceComponent);
                clearFields();
                displayBooks();
                JOptionPane.showMessageDialog(messagePlaceComponent, "更新成功!");
                return;

            }
        }
    }

    private boolean validateInput(String title, String author, String isbn, Component messagePlaceComponent) {
        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
            JOptionPane.showMessageDialog(messagePlaceComponent,
                    "情報を入力してください!",
                    "NG～～",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Check ISBN
        if (isbn.length() < 10 || isbn.length() > 13) {
            JOptionPane.showMessageDialog(messagePlaceComponent,
                    "ISBN 10-13数が表示しなければならない!",
                    "NG～～",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public void deleteBook(Component messagePlaceComponent) {
        String isbn = PanelFill.isbnField.getText().trim();

        Book book;

        // check if ISBN field not empty
        if (isbn.isEmpty()) {
            JOptionPane.showMessageDialog(messagePlaceComponent,
                    "ISBNを入力してください!",
                    "NG～～",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check ISBN
        if (isbn.length() < 10 || isbn.length() > 13) {
            JOptionPane.showMessageDialog(messagePlaceComponent,
                    "ISBN 10-13数が表示しなければならない!",
                    "NG～～",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(messagePlaceComponent,
                "この本を削除しますか?",
                "確定",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            for (Book item : books) {
                if (item.getIsbn().equals(isbn)) {
                    book = item;
                    books.remove(book);

                    saveBooksToFile(messagePlaceComponent);
                    clearFields();
                    displayBooks();
                    JOptionPane.showMessageDialog(messagePlaceComponent, "更新成功!");
                    return;

                }
            }

        }
    }

    public void loadBooksFromFile(Component messagePlaceComponent) {
        books.clear();

        List<String> data = new ArrayList<>();
        try {
            data.addAll(Files.readAllLines(Path.of(FILE_PATH)));

            Book book = new Book();

            for (String line : data) {
                String[] parts = line.split(": ");
                if (parts[0].equals("本")) {
                    book.setTitle(parts[1]);
                } else if (parts[0].equals("著作")) {
                    book.setAuthor(parts[1]);
                } else if (parts[0].equals("ISBN")) {
                    book.setIsbn(parts[1]);
                    books.add(book);

                    book = new Book();
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(messagePlaceComponent,
                    "fileにエラーが発生: " + e.getMessage(),
                    "NG～～",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
