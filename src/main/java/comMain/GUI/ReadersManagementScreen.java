package comMain.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import comMain.client.Information;
import comMain.entities.BookEntity;
import comMain.entities.ReadersEntity;
public class ReadersManagementScreen extends JPanel {
    private final JTextField searchField;
    private final JButton searchButton;
    private JLabel titleLabel;
    private JList<ReadersEntity> bookList;
    private JButton editBookButton;
    private JButton deleteBookButton;
    private JButton addBookButton;

    public ReadersManagementScreen() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize components
        titleLabel = new JLabel("Readers Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        bookList = new JList<ReadersEntity>();
        editBookButton = new JButton("Edit");
        deleteBookButton = new JButton("Delete");
        addBookButton = new JButton("Add");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        // Set book list model
        DefaultListModel<ReadersEntity> readerListModel = Information.getAllReaders();
        bookList.setModel(readerListModel);

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
        bookListButtonPanel.add(editBookButton);
        bookListButtonPanel.add(deleteBookButton);
        bookListButtonPanel.add(addBookButton);
        add(bookListButtonPanel, BorderLayout.SOUTH);

        // Add action listeners

        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AddEditUserScreen when the Add button is clicked
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ReadersManagementScreen.this);
                AddEditUserScreen addEditUserScreen = new AddEditUserScreen();
                frame.setContentPane(addEditUserScreen);
                frame.revalidate();
                frame.repaint();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                DefaultListModel<ReadersEntity> filteredListModel = new DefaultListModel<ReadersEntity>();
                for (int i = 0; i < readerListModel.getSize(); i++) {
                    ReadersEntity reader = readerListModel.getElementAt(i);
                    if ((reader.getFirstName() + " " + reader.getLastName()).contains(query)) {
                        filteredListModel.addElement(reader);
                    }
                }
                bookList.setModel(filteredListModel);
            }
        });
    }
}
