package view.tabs.courseoffering;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.StudentRecordSystem;
import view.MainWindow;

@SuppressWarnings("serial")
public class CourseOfferingsTabPanel extends JPanel {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private CourseOfferingsScrollPane courseOfferingsScrollPane;
    private CourseOfferingsControlPanel courseOfferingsControlPanel;

    public CourseOfferingsTabPanel(MainWindow mainWindow,
        StudentRecordSystem srs) {
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
        this.courseOfferingsScrollPane = new CourseOfferingsScrollPane(
            mainWindow, srs);
        this.courseOfferingsControlPanel = new CourseOfferingsControlPanel(
            courseOfferingsScrollPane);
    }

    private void addComopnentsToPanel() {
        this.add(courseOfferingsScrollPane, BorderLayout.CENTER);
        this.add(courseOfferingsControlPanel, BorderLayout.SOUTH);
    }

    public void refresh() {
        this.courseOfferingsScrollPane.refresh();
    }

}
