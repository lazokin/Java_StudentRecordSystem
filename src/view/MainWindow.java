package view;

import javax.swing.JFrame;

import model.StudentRecordSystem;
import view.menu.MainMenuBar;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

    private StudentRecordSystem srs;

    private MainMenuBar mainMenuBar;
    private MainPanel mainPanel;

    public final static int WIDTH = 1024;
    public final static int HEIGHT = 768;

    public MainWindow(StudentRecordSystem srs) {
        super();
        this.processParameters(srs);
        this.setupFrame();
        this.createComponents();
        this.setupComponents();
        this.addComponents();
        this.refresh();
    }

    private void processParameters(StudentRecordSystem srs) {
        this.srs = srs;
    }

    private void setupFrame() {
        this.setSize(MainWindow.WIDTH, MainWindow.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void createComponents() {
        this.mainMenuBar = new MainMenuBar(this, srs);
        this.mainPanel = new MainPanel(this, srs);
    }

    private void setupComponents() {
        // No setup required
    }

    private void addComponents() {
        this.setJMenuBar(mainMenuBar);
        this.setContentPane(mainPanel);
    }

    public void refresh() {
        this.mainPanel.refresh();
    }

}
