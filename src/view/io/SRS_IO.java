package view.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.StudentRecordSystem;
import view.MainWindow;

import com.lazokin.util.FileOutput;

public class SRS_IO {

    public final static String STUDENT_FILE_NAME = "studentList.txt";
    public final static String STAFF_FILE_NAME = "staffList.txt";
    public final static String COURSE_FILE_NAME = "courseInfo.txt";

    public final static int ENTRIES_ON_LINE_FOR_STUDENT_FILE = 10;
    public final static int ENTRIES_ON_LINE_FOR_STAFF_FILE = 4;
    public final static int ENTRIES_ON_LINE_FOR_COURSE_FILE = 7;

    public final static String CURRENT_YEAR = "2014";

    public SRS_IO() {
        super();
    }

    public static void saveTableToFile(DefaultTableModel dtm, String title,
        MainWindow mainWindow) {

        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(mainWindow);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            FileWriter fw = null;
            try {
                fw = new FileWriter(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PrintWriter pw = new PrintWriter(fw);

            String[] header = createTableHeaderArray(dtm);
            String[][] data = createTableDataArray(dtm);
            pw.println(title);
            FileOutput.printTable(data, header, pw);

            pw.close();
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private static String[] createTableHeaderArray(DefaultTableModel dtm) {

        int numberOfColumns = dtm.getColumnCount();
        String[] headerArray = new String[numberOfColumns];
        for (int i = 0; i < numberOfColumns; i++) {
            headerArray[i] = dtm.getColumnName(i);
        }
        return headerArray;

    }

    private static String[][] createTableDataArray(DefaultTableModel dtm) {

        int numberOfRows = dtm.getRowCount();
        int numberOfColumns = dtm.getColumnCount();
        String[][] dataArray = new String[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                dataArray[i][j] = (String) dtm.getValueAt(i, j);
            }
        }
        return dataArray;

    }

    // Select location of files to load, then load files
    public static void loadFromFiles(MainWindow mainWindow,
        StudentRecordSystem srs) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setDialogTitle("Select Location of Files");
        int returnVal = fc.showOpenDialog(mainWindow);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File dir = fc.getSelectedFile();
            String studentFilePath = dir.getAbsolutePath() + File.separator
                + STUDENT_FILE_NAME;
            String staffFilePath = dir.getAbsolutePath() + File.separator
                + STAFF_FILE_NAME;
            String courseFilePath = dir.getAbsolutePath() + File.separator
                + COURSE_FILE_NAME;
            try {
                SRS_Load.load(mainWindow, srs, studentFilePath, staffFilePath,
                    courseFilePath);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                    "Load From Files Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                    "Load From Files Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Select location of files to save, then save files
    public static void saveToFiles(MainWindow mainWindow,
        StudentRecordSystem srs) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setDialogTitle("Select Location of Files");
        int returnVal = fc.showSaveDialog(mainWindow);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File dir = fc.getSelectedFile();
            String studentFilePath = dir.getAbsolutePath() + File.separator
                + STUDENT_FILE_NAME;
            String staffFilePath = dir.getAbsolutePath() + File.separator
                + STAFF_FILE_NAME;
            String courseFilePath = dir.getAbsolutePath() + File.separator
                + COURSE_FILE_NAME;
            SRS_Save.save(mainWindow, srs, studentFilePath, staffFilePath,
                courseFilePath);
        }
    }

}