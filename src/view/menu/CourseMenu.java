package view.menu;

import java.awt.Insets;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import model.StudentRecordSystem;
import view.MainWindow;
import controller.course.DeleteCourseActionListener;
import controller.course.addAdvancedCourseActionListener;
import controller.course.addPrerequisiteActionListener;
import controller.course.addStandardCourseActionListener;
import controller.course.removePrerequisiteActionListener;

@SuppressWarnings("serial")
public class CourseMenu extends JMenu {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private JMenuItem addStandardCourseMenuItem;
    private JMenuItem addAdvancedCourseMenuItem;
    private JMenuItem deleteCourseMenuItem;
    private JMenuItem addPrerequisiteMenuItem;
    private JMenuItem removePrerequisiteMenuItem;

    public CourseMenu(MainWindow mainWindow, StudentRecordSystem srs) {
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
        this.setText("Course");
        this.setMargin(new Insets(4, 4, 4, 4));
    }

    private void createComponents() {
        this.addStandardCourseMenuItem = new JMenuItem("Add Standard Course");
        this.addAdvancedCourseMenuItem = new JMenuItem("Add Advanced Course");
        this.deleteCourseMenuItem = new JMenuItem("Delete Course");
        this.addPrerequisiteMenuItem = new JMenuItem("Add Prerequisite");
        this.removePrerequisiteMenuItem = new JMenuItem("Remove Prerequisite");
    }

    private void setupComponents() {
        this.addStandardCourseMenuItem
                .addActionListener(new addStandardCourseActionListener(
                        mainWindow, srs));
        this.addAdvancedCourseMenuItem
                .addActionListener(new addAdvancedCourseActionListener(
                        mainWindow, srs));
        this.deleteCourseMenuItem
                .addActionListener(new DeleteCourseActionListener(
                        mainWindow, srs));
        this.addPrerequisiteMenuItem
                .addActionListener(new addPrerequisiteActionListener(
                        mainWindow, srs));
        this.removePrerequisiteMenuItem
                .addActionListener(new removePrerequisiteActionListener(
                        mainWindow, srs));
    }

    private void addComponents() {
        this.add(addStandardCourseMenuItem);
        this.add(addAdvancedCourseMenuItem);
        this.add(deleteCourseMenuItem);
        this.addSeparator();
        this.add(addPrerequisiteMenuItem);
        this.add(removePrerequisiteMenuItem);
    }

}
