package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileManagerGUI extends JFrame {
    private JTextField directoryTextField;
    private JTextArea fileListTextArea;

    public FileManagerGUI() {
        setTitle("File Manager");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JLabel directoryLabel = new JLabel("Directory:");
        directoryTextField = new JTextField(30);
        JButton browseButton = new JButton("Browse");
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                browseButtonClicked();
            }
        });
        topPanel.add(directoryLabel);
        topPanel.add(directoryTextField);
        topPanel.add(browseButton);

        fileListTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(fileListTextArea);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void browseButtonClicked() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            directoryTextField.setText(selectedFile.getAbsolutePath());
            // Call a method to update the file list based on the selected directory
            updateFileList(selectedFile);
        }
    }

    private void updateFileList(File directory) {
        // Retrieve list of files in the selected directory and display them in the fileListTextArea
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            StringBuilder fileList = new StringBuilder();
            if (files != null) {
                for (File file : files) {
                    fileList.append(file.getName()).append("\n");
                }
            }
            fileListTextArea.setText(fileList.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FileManagerGUI().setVisible(true);
            }
        });
    }
}
