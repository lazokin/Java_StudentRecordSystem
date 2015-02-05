package view.menu;

import java.awt.Insets;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import model.StudentRecordSystem;
import view.MainWindow;
import controller.student.AddStudentActionListener;
import controller.student.AssignExemptionActionListener;
import controller.student.DeleteStudentActionListener;
import controller.student.ProcessPaymentActionListener;

@SuppressWarnings("serial")
public class StudentMenu extends JMenu {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private JMenuItem addStudentMenuItem;
    private JMenuItem deleteStudentMenuItem;
    private JMenuItem assignExemptionMenuItem;
    private JMenuItem processPaymentMenuItem;

    public StudentMenu(MainWindow mainWindow, StudentRecordSystem srs) {
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
        this.setText("Student");
        this.setMargin(new Insets(4, 4, 4, 4));
    }

    private void createComponents() {
        this.addStudentMenuItem = new JMenuItem("Add Student");
        this.deleteStudentMenuItem = new JMenuItem("Delete Student");
        this.assignExemptionMenuItem = new JMenuItem("Assign Exemption");
        this.processPaymentMenuItem = new JMenuItem("Process Payment");
    }

    private void setupComponents() {
        this.addStudentMenuItem.addActionListener(
                new AddStudentActionListener(
                        mainWindow, srs));
        this.deleteStudentMenuItem.addActionListener(
                new DeleteStudentActionListener(
                        mainWindow, srs));
        this.assignExemptionMenuItem.addActionListener(
                new AssignExemptionActionListener(
                        mainWindow, srs));
        this.processPaymentMenuItem.addActionListener(
                new ProcessPaymentActionListener(
                        mainWindow, srs));
    }

    private void addComponents() {
        this.add(addStudentMenuItem);
        this.add(deleteStudentMenuItem);
        this.addSeparator();
        this.add(assignExemptionMenuItem);
        this.addSeparator();
        this.add(processPaymentMenuItem);
    }

}
