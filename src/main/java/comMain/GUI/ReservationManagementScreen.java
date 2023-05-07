package comMain.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import comMain.entities.ReserveEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


public class ReservationManagementScreen extends JPanel {
    private JLabel titleLabel;
    private JList<ReserveEntity> reserveList;
    private JButton editReserveButton;
    private JButton deleteReserveButton;
    private JButton addReserveButton;
    private JTextField searchField;
    private JButton searchButton;

    public ReservationManagementScreen() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize components
        titleLabel = new JLabel("Reserves Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        reserveList = new JList<ReserveEntity>();
        editReserveButton = new JButton("Edit");
        deleteReserveButton = new JButton("Delete");
        addReserveButton = new JButton("Add");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        // Set reserve list model
        DefaultListModel<ReserveEntity> reserveListModel = new DefaultListModel<ReserveEntity>();
        reserveList.setModel(reserveListModel);


        // Add components to panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.SOUTH);

        JScrollPane reserveListScrollPane = new JScrollPane(reserveList);
        add(reserveListScrollPane, BorderLayout.CENTER);

        JPanel reserveListButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        reserveListButtonPanel.add(addReserveButton);
        reserveListButtonPanel.add(editReserveButton);
        reserveListButtonPanel.add(deleteReserveButton);
        reserveListButtonPanel.add(searchPanel);
        add(reserveListButtonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addReserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // In the ActionListener for the button that opens the new window
                JFrame frame = new JFrame();
                frame.setContentPane(new ReservationManagementScreen());
                frame.pack();
                frame.setLocationRelativeTo(null); // Center the window
                frame.setVisible(true);

// In the ActionListener for the button that goes back to the tabbed window
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(ReservationManagementScreen.this); // 'this' is the button
                currentFrame.dispose(); // Close the current window

// Get the JTabbedPane that contains the current tabbed window
                JTabbedPane tabbedPane = HomeScreen.tabbedPane;

// Switch to the tabbed window
                tabbedPane.setSelectedIndex(0); // Set the index of the tab you want to switch to
            }
        });


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                DefaultListModel<ReserveEntity> filteredListModel = new DefaultListModel<ReserveEntity>();
                for (int i = 0; i < reserveListModel.getSize(); i++) {
                    ReserveEntity reserve = reserveListModel.getElementAt(i);
                    if (reserve.toString().contains(query)) {
                        filteredListModel.addElement(reserve);
                    }
                }
                reserveList.setModel(filteredListModel);
            }
        });


        reserveList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                ReserveEntity reserve = (ReserveEntity) value;
                renderer.setText(reserve.toString());
                renderer.setHorizontalAlignment(SwingConstants.RIGHT);
                return renderer;
            }
        });
    }
}
