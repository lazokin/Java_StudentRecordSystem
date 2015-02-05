package view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.StudentRecordSystem;
import view.tabs.MainTabbedPane;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private MainTabbedPane mainTabbedPane;

    public MainPanel(MainWindow mainWindow, StudentRecordSystem srs) {
        super();
        this.processParameters(mainWindow, srs);
        this.setupPanel();
        this.createComponents();
        this.setupComponents();
        this.addComponents();
    }

    private void processParameters(MainWindow mainWindow,
        StudentRecordSystem srs) {
        this.mainWindow = mainWindow;
        this.srs = srs;
    }

    private void setupPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void createComponents() {
        this.mainTabbedPane = new MainTabbedPane(mainWindow, srs);
    }

    private void setupComponents() {
        // No setup required
    }

    private void addComponents() {
        this.add(mainTabbedPane);
    }

    public void refresh() {
        this.mainTabbedPane.refresh();
    }

}
