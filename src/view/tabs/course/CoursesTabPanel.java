package view.tabs.course;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.StudentRecordSystem;
import view.MainWindow;

@SuppressWarnings("serial")
public class CoursesTabPanel extends JPanel {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private CoursesScrollPane coursesScrollPane;
    private CoursesControlPanel coursesControlPanel;

    public CoursesTabPanel(MainWindow mainWindow, StudentRecordSystem srs) {
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
        this.coursesScrollPane = new CoursesScrollPane(mainWindow, srs);
        this.coursesControlPanel = new CoursesControlPanel(coursesScrollPane);

    }

    private void addComopnentsToPanel() {
        this.add(coursesScrollPane, BorderLayout.CENTER);
        this.add(coursesControlPanel, BorderLayout.SOUTH);
    }

    public void refresh() {
        this.coursesScrollPane.refresh();
    }

}
