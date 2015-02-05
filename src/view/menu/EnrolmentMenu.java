package view.menu;

import java.awt.Insets;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import model.StudentRecordSystem;
import view.MainWindow;
import controller.enrolments.CompleteAllEnrolmentsActionListener;
import controller.enrolments.CompleteEnrolmentActionListener;
import controller.enrolments.CreateEnrolmentActionListener;
import controller.enrolments.DeleteEnrolmentActionListener;

@SuppressWarnings("serial")
public class EnrolmentMenu extends JMenu {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private JMenuItem createEnrolmentMenuItem;
    private JMenuItem deleteEnrolmentMenuItem;
    private JMenuItem completeEnrolmentMenuItem;
    private JMenuItem completeAllEnrolmentsMenuItem;

    public EnrolmentMenu(MainWindow mainWindow, StudentRecordSystem srs) {
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
        this.setText("Enrolment");
        this.setMargin(new Insets(4, 4, 4, 4));
    }

    private void createComponents() {
        this.createEnrolmentMenuItem = new JMenuItem(
                "Create Enrolment");
        this.deleteEnrolmentMenuItem = new JMenuItem(
                "Delete Enrolment");
        this.completeEnrolmentMenuItem = new JMenuItem(
                "Complete Enrolment");
        this.completeAllEnrolmentsMenuItem = new JMenuItem(
                "Complete All Enrolments");
    }

    private void setupComponents() {
        this.createEnrolmentMenuItem
                .addActionListener(new CreateEnrolmentActionListener(
                        mainWindow, srs));
        this.deleteEnrolmentMenuItem
                .addActionListener(new DeleteEnrolmentActionListener(
                        mainWindow, srs));
        this.completeEnrolmentMenuItem
                .addActionListener(new CompleteEnrolmentActionListener(
                        mainWindow, srs));
        this.completeAllEnrolmentsMenuItem
                .addActionListener(new CompleteAllEnrolmentsActionListener(
                        mainWindow, srs));
    }

    private void addComponents() {
        this.add(createEnrolmentMenuItem);
        this.add(deleteEnrolmentMenuItem);
        this.addSeparator();
        this.add(completeEnrolmentMenuItem);
        this.add(completeAllEnrolmentsMenuItem);
    }

}
