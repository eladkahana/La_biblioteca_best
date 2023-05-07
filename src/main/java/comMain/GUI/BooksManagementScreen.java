package comMain.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.List;

import comMain.client.Information;
import comMain.entities.BookEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


public class BooksManagementScreen extends JPanel {
    private JLabel titleLabel;
    private JList<BookEntity> bookList;
    private JButton editBookButton;
    private JButton deleteBookButton;
    private JButton addBookButton;
    private JTextField searchField;
    private JButton searchButton;

    public BooksManagementScreen() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize components
        titleLabel = new JLabel("Books Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        bookList = new JList<BookEntity>();
        editBookButton = new JButton("Edit");
        deleteBookButton = new JButton("Delete");
        addBookButton = new JButton("Add");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        // Set book list model
        DefaultListModel<BookEntity> bookListModel = Information.getAllBooks();
        bookList.setModel(bookListModel);


        // Add components to panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.SOUTH);

        JScrollPane bookListScrollPane = new JScrollPane(bookList);
        add(bookListScrollPane, BorderLayout.CENTER);

        JPanel bookListButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bookListButtonPanel.add(addBookButton);
        bookListButtonPanel.add(editBookButton);
        bookListButtonPanel.add(deleteBookButton);
        bookListButtonPanel.add(searchPanel);
        add(bookListButtonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AddEditBookScreen when the Add button is clicked
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(BooksManagementScreen.this);
                AddEditBookScreen addEditBookScreen = new AddEditBookScreen();
                frame.setContentPane(addEditBookScreen);
                frame.revalidate();
                frame.repaint();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                DefaultListModel<BookEntity> filteredListModel = new DefaultListModel<BookEntity>();
                for (int i = 0; i < bookListModel.getSize(); i++) {
                    BookEntity book = bookListModel.getElementAt(i);
                    if (book.getTitle().contains(query)) {
                        filteredListModel.addElement(book);
                    }
                }
                bookList.setModel(filteredListModel);
            }
        });


        bookList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                BookEntity book = (BookEntity) value;
                renderer.setText(book.getTitle());
                renderer.setHorizontalAlignment(SwingConstants.RIGHT);
                return renderer;
            }
        });

    }
}
