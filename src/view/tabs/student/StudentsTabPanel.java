package view.tabs.student;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.StudentRecordSystem;
import view.MainWindow;

@SuppressWarnings("serial")
public class StudentsTabPanel extends JPanel {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private StudentsScrollPane studentsScrollPane;
    private StudentsControlPanel studentsControlPanel;

    public StudentsTabPanel(MainWindow mainWindow, StudentRecordSystem srs) {
        super();
        this.processParameters(mainWindow, srs);
        this.setupPanel();
        this.createComponents();
        this.addComopnentsToPanel();
    }

    private void processParameters(MainWindow mainWindow,
        StudentRecordSystem srs) {
        this.mainWindow = mainWindow;
        this.srs = srs;
    }

    private void setupPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
    }

    private void createComponents() {
        this.studentsScrollPane = new StudentsScrollPane(mainWindow, srs);
        this.studentsControlPanel = new StudentsControlPanel(studentsScrollPane);
    }

    private void addComopnentsToPanel() {
        this.add(studentsScrollPane, BorderLayout.CENTER);
        this.add(studentsControlPanel, BorderLayout.SOUTH);
    }

    public void refresh() {
        this.studentsScrollPane.refresh();
    }

}
