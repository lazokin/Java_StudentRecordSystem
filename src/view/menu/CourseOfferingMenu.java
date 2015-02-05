package view.menu;

import java.awt.Insets;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import model.StudentRecordSystem;
import view.MainWindow;
import controller.courseoffering.AddCourseOfferingActionListener;
import controller.courseoffering.AssignInstructorActionListener;
import controller.courseoffering.CompleteCourseOfferingActionListener;
import controller.courseoffering.DeleteCourseOfferingActionListener;
import controller.courseoffering.SetFeeActionListener;
import controller.courseoffering.SetTextbookActionListener;

@SuppressWarnings("serial")
public class CourseOfferingMenu extends JMenu {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private JMenuItem addCourseOfferingMenuItem;
    private JMenuItem deleteCourseOfferingMenuItem;
    private JMenuItem assignInstructorMenuItem;
    private JMenuItem setFeeMenuItem;
    private JMenuItem setTextbookMenuItem;
    private JMenuItem completeCourseOfferingMenuItem;

    public CourseOfferingMenu(MainWindow mainWindow, StudentRecordSystem srs) {
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
        this.setText("Course Offering");
        this.setMargin(new Insets(4, 4, 4, 4));
    }

    private void createComponents() {
        this.addCourseOfferingMenuItem = new JMenuItem(
                "Add Course Offering");
        this.deleteCourseOfferingMenuItem = new JMenuItem(
                "Delete Course Offering");
        this.assignInstructorMenuItem = new JMenuItem(
                "Assign Instructor");
        this.setFeeMenuItem = new JMenuItem(
                "Set Fee");
        this.setTextbookMenuItem = new JMenuItem(
                "Set Textbook");
        this.completeCourseOfferingMenuItem = new JMenuItem(
                "Complete Course Offering");
    }

    private void setupComponents() {
        this.addCourseOfferingMenuItem
                .addActionListener(new AddCourseOfferingActionListener(
                        mainWindow, srs));
        this.deleteCourseOfferingMenuItem
                .addActionListener(new DeleteCourseOfferingActionListener(
                        mainWindow, srs));
        this.assignInstructorMenuItem
                .addActionListener(new AssignInstructorActionListener(
                        mainWindow, srs));
        this.setFeeMenuItem.addActionListener(
                new SetFeeActionListener(
                        mainWindow, srs));
        this.setTextbookMenuItem
                .addActionListener(new SetTextbookActionListener(
                        mainWindow, srs));
        this.completeCourseOfferingMenuItem
                .addActionListener(new CompleteCourseOfferingActionListener(
                        mainWindow, srs));
    }

    private void addComponents() {
        this.add(addCourseOfferingMenuItem);
        this.add(deleteCourseOfferingMenuItem);
        this.addSeparator();
        this.add(assignInstructorMenuItem);
        this.addSeparator();
        this.add(setFeeMenuItem);
        this.add(setTextbookMenuItem);
        this.addSeparator();
        this.add(completeCourseOfferingMenuItem);
    }

}
