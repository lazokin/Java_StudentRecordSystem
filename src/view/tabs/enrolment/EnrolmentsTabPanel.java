package view.tabs.enrolment;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.StudentRecordSystem;
import view.MainWindow;

@SuppressWarnings("serial")
public class EnrolmentsTabPanel extends JPanel {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private EnrolmentsScrollPane enrolmentsScrollPane;
    private EnrolmentsControlPanel enrolmentsControlPanel;

    public EnrolmentsTabPanel(MainWindow mainWindow, StudentRecordSystem srs) {
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
        this.enrolmentsScrollPane = new EnrolmentsScrollPane(mainWindow, srs);
        this.enrolmentsControlPanel = new EnrolmentsControlPanel(
            enrolmentsScrollPane);
    }

    private void addComopnentsToPanel() {
        this.add(enrolmentsScrollPane, BorderLayout.CENTER);
        this.add(enrolmentsControlPanel, BorderLayout.SOUTH);
    }

    public void refresh() {
        this.enrolmentsScrollPane.refresh();
    }

}
