package view.menu;

import java.awt.Insets;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import model.StudentRecordSystem;
import view.MainWindow;
import controller.file.ExitActionListener;
import controller.file.LoadDataListener;
import controller.file.SaveDataListener;

@SuppressWarnings("serial")
public class FileMenu extends JMenu {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private JMenuItem loadDataMenuItem = new JMenuItem("Load Data");
    private JMenuItem saveDataMenuItem = new JMenuItem("Save Data");
    private JMenuItem exitMenuItem = new JMenuItem("Exit");

    public FileMenu(MainWindow mainWindow, StudentRecordSystem srs) {
        super();
        processParameters(mainWindow, srs);
        setupMenu();
        createComponents();
        setupComponents();
        addComponents();
    }

    private void processParameters(MainWindow mainWindow,
        StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    private void setupMenu() {
        this.setText("File");
        this.setMargin(new Insets(4, 4, 4, 4));
    }

    private void createComponents() {
        this.loadDataMenuItem = new JMenuItem("Load From Files");
        this.saveDataMenuItem = new JMenuItem("Save to Files");
        this.exitMenuItem = new JMenuItem("Exit");
    }

    private void setupComponents() {
        this.loadDataMenuItem.addActionListener(new LoadDataListener(
            mainWindow, srs));
        this.saveDataMenuItem.addActionListener(new SaveDataListener(
            mainWindow, srs));
        this.exitMenuItem.addActionListener(new ExitActionListener());
    }

    private void addComponents() {
        this.add(loadDataMenuItem);
        this.add(saveDataMenuItem);
        this.addSeparator();
        this.add(exitMenuItem);
    }

}
