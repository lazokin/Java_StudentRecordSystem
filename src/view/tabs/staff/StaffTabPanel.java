package view.tabs.staff;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.StudentRecordSystem;
import view.MainWindow;

@SuppressWarnings("serial")
public class StaffTabPanel extends JPanel {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private StaffScrollPane staffScrollPane;
    private StaffControlPanel staffControlPanel;

    public StaffTabPanel(MainWindow mainWindow, StudentRecordSystem srs) {
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
        this.staffScrollPane = new StaffScrollPane(mainWindow, srs);
        this.staffControlPanel = new StaffControlPanel(staffScrollPane);
    }

    private void addComopnentsToPanel() {
        this.add(staffScrollPane, BorderLayout.CENTER);
        this.add(staffControlPanel, BorderLayout.SOUTH);
    }

    public void refresh() {
        this.staffScrollPane.refresh();
    }

}
