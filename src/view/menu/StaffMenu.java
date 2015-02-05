package view.menu;

import java.awt.Insets;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import model.StudentRecordSystem;
import view.MainWindow;
import controller.staff.AddStaffActionListener;
import controller.staff.AddStudentInstructorActionListener;
import controller.staff.DeleteStaffActionListener;
import controller.staff.SetPayActionListener;

@SuppressWarnings("serial")
public class StaffMenu extends JMenu {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private JMenuItem addStaffMenuItem;
    private JMenuItem addStudentInstructorMenuItem;
    private JMenuItem deleteStaffMenuItem;
    private JMenuItem setPayMenuItem;

    public StaffMenu(MainWindow mainWindow, StudentRecordSystem srs) {
        super();
        this.processParameters(mainWindow, srs);
        this.setupMenu();
        this.createComponents();
        this.setupComponents();
        this.addComponents();
    }

    private void processParameters(MainWindow mainWindow,
        StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    private void setupMenu() {
        this.setText("Staff");
        this.setMargin(new Insets(4, 4, 4, 4));
    }

    private void createComponents() {
        this.addStaffMenuItem = new JMenuItem("Add Staff");
        this.addStudentInstructorMenuItem = new JMenuItem(
            "Add Student Instructor");
        this.deleteStaffMenuItem = new JMenuItem("Delete Staff");
        this.setPayMenuItem = new JMenuItem("Set Pay");
    }

    private void setupComponents() {
        this.addStaffMenuItem.addActionListener(new AddStaffActionListener(
            mainWindow, srs));
        this.addStudentInstructorMenuItem
            .addActionListener(new AddStudentInstructorActionListener(
                mainWindow, srs));
        this.deleteStaffMenuItem
            .addActionListener(new DeleteStaffActionListener(mainWindow, srs));
        this.setPayMenuItem.addActionListener(new SetPayActionListener(
            mainWindow, srs));
    }

    private void addComponents() {
        this.add(addStaffMenuItem);
        this.add(addStudentInstructorMenuItem);
        this.add(deleteStaffMenuItem);
        this.addSeparator();
        this.add(setPayMenuItem);
    }

}
