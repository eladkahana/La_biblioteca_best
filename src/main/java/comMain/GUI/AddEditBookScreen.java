package comMain.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;


public class AddEditBookScreen extends JPanel {

    private final JButton saveButton;
    private final JButton cancelButton;
    private JTextField isbnField;
    private JTextField titleField;
    private JTextField editionField;
    private JTextField shelfmarkField;
    private JTextField pagesField;
    private JTextField yearField;
    private JComboBox<String> languageComboBox;
    private JTextField publisherField;
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> authorComboBox;
    private JComboBox<String> seriesComboBox;
    private JComboBox<String> audienceComboBox;
    private JTextArea noteArea;
    private JButton uploadButton;
    private JLabel imageLabel;

    public AddEditBookScreen() {
        setLayout(new BorderLayout());

        // Create the input fields
        JPanel fieldsPanel = new JPanel(new GridLayout(13, 2, 5, 5));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        fieldsPanel.add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        fieldsPanel.add(isbnField);

        fieldsPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        fieldsPanel.add(titleField);

        fieldsPanel.add(new JLabel("Edition:"));
        editionField = new JTextField();
        fieldsPanel.add(editionField);

        fieldsPanel.add(new JLabel("Shelfmark:"));
        shelfmarkField = new JTextField();
        fieldsPanel.add(shelfmarkField);

        fieldsPanel.add(new JLabel("Number of Pages:"));
        pagesField = new JTextField();
        fieldsPanel.add(pagesField);

        fieldsPanel.add(new JLabel("Publish Year:"));
        yearField = new JTextField();
        fieldsPanel.add(yearField);

        fieldsPanel.add(new JLabel("Language:"));
        String[] languages = {"Hebrew", "English", "Arabic", "French", "Spanish"};
        languageComboBox = new JComboBox<>(languages);
        fieldsPanel.add(languageComboBox);

        fieldsPanel.add(new JLabel("Publisher:"));
        publisherField = new JTextField();
        fieldsPanel.add(publisherField);

        fieldsPanel.add(new JLabel("Category:"));
        String[] categories = {"Fiction", "Non-fiction", "Biography", "History", "Science"};
        categoryComboBox = new JComboBox<>(categories);
        categoryComboBox.setEditable(true);
        fieldsPanel.add(categoryComboBox);

        fieldsPanel.add(new JLabel("Author:"));
        String[] authors = {"Stephen King", "Agatha Christie", "J.K. Rowling", "Ernest Hemingway", "Haruki Murakami"};
        authorComboBox = new JComboBox<>(authors);
        authorComboBox.setEditable(true);
        fieldsPanel.add(authorComboBox);

        fieldsPanel.add(new JLabel("Series:"));
        String[] series = {"Harry Potter", "Lord of the Rings", "Game of Thrones", "Percy Jackson", "The Hunger Games"};
        seriesComboBox = new JComboBox<>(series);
        seriesComboBox.setEditable(true);
        fieldsPanel.add(seriesComboBox);

        fieldsPanel.add(new JLabel("Audience:"));
        String[] audience = {"Children", "Young Adult", "Adult", "Senior"};
        audienceComboBox = new JComboBox<>(audience);
        audienceComboBox.setEditable(true);
        fieldsPanel.add(audienceComboBox);

        fieldsPanel.add(new JLabel("Note:"));
        noteArea = new JTextArea();
        noteArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(noteArea);
        fieldsPanel.add(scrollPane);


        // Create the image panel
        uploadButton = new JButton("Upload Image");
        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    BufferedImage image = ImageIO.read(selectedFile);
                    ImageIcon icon = new ImageIcon(image);
                    imageLabel.setIcon(icon);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.add(uploadButton, BorderLayout.NORTH);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Add the input fields and the image panel to the main panel
        add(fieldsPanel, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.EAST);

        // Create the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);


        saveButton.addActionListener(e -> {
            // Create a new dialog
            JDialog dialog = new JDialog();
            dialog.setTitle("Confirmation Message");

            // Create a panel for the message and the image
            JPanel messagePanel = new JPanel(new BorderLayout(10, 10));
            JLabel messageLabel = new JLabel("Are you sure you want to save this book?");
            messagePanel.add(messageLabel, BorderLayout.NORTH);
            messagePanel.add(imageLabel, BorderLayout.CENTER);

            // Create a panel for the print button
            JPanel printPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            JButton printButton = new JButton("Print");
            printButton.addActionListener(printActionListener());
            printPanel.add(printButton);

            // Add the message panel and the print panel to the dialog
            dialog.add(messagePanel, BorderLayout.CENTER);
            dialog.add(printPanel, BorderLayout.SOUTH);

            // Set the size and show the dialog
            dialog.pack();
            dialog.setVisible(true);
        });





    }


    private ActionListener printActionListener() {
        return e -> {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(new Printable() {
                @Override
                public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                    if (pageIndex == 0) {
                        Graphics2D g2d = (Graphics2D) graphics;
                        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                        imageLabel.paint(g2d);
                        return Printable.PAGE_EXISTS;
                    } else {
                        return Printable.NO_SUCH_PAGE;
                    }
                }
            });
            boolean doPrint = job.printDialog();
            if (doPrint) {
                try {
                    job.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
        };
    }


}

