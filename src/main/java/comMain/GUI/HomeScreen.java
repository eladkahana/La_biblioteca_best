package comMain.GUI;

import javax.swing.*;
import java.awt.*;


public class HomeScreen{

    public static JTabbedPane tabbedPane = new JTabbedPane();
    public static void main(String[] args) {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);



        // Create the screens and add them to the tabbed pane
        BooksManagementScreen bookManagementScreen = new BooksManagementScreen();
        tabbedPane.addTab("Books Management", bookManagementScreen);

        // Create an instance of the AddEditUserScreen and add it to the tabbed pane
        ReadersManagementScreen readersManagementScreen = new ReadersManagementScreen();
        tabbedPane.addTab("Readers Management", readersManagementScreen);


        SettingsScreen settingsScreen = new SettingsScreen();
        tabbedPane.addTab("Settings", settingsScreen);

        ReservationManagementScreen reservationManagementScreen = new ReservationManagementScreen();
        tabbedPane.addTab("Reservation Management", reservationManagementScreen);

        ReservesActionsScreen reservesActionsScreen = new ReservesActionsScreen();
        tabbedPane.addTab("Reservation Actions", reservesActionsScreen);

        AnalyticsScreen analyticsScreen = new AnalyticsScreen();
        tabbedPane.addTab("Analytics", analyticsScreen);

        MailBoxScreen mailbox = new MailBoxScreen();
        tabbedPane.addTab("Mail Box", mailbox);

        // Add the tabbed pane to the frame
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);
    }


}


