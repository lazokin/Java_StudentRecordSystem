package view.tabs;

import javax.swing.JTabbedPane;

import model.StudentRecordSystem;
import view.MainWindow;
import view.tabs.course.CoursesTabPanel;
import view.tabs.courseoffering.CourseOfferingsTabPanel;
import view.tabs.enrolment.EnrolmentsTabPanel;
import view.tabs.staff.StaffTabPanel;
import view.tabs.student.StudentsTabPanel;

@SuppressWarnings("serial")
public class MainTabbedPane extends JTabbedPane {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private StudentsTabPanel studentsTabPanel;
    private CoursesTabPanel coursesTabPanel;
    private CourseOfferingsTabPanel courseOfferingsTabPanel;
    private EnrolmentsTabPanel enrolmentsTabPanel;
    private StaffTabPanel staffTabPanel;

    public MainTabbedPane(MainWindow mainWindow, StudentRecordSystem srs) {
        super();
        this.processParameters(mainWindow, srs);
        this.setupTabbedPane();
        this.createComponents();
        this.addComponents();
    }

    private void processParameters(MainWindow mainWindow,
        StudentRecordSystem srs) {
        this.mainWindow = mainWindow;
        this.srs = srs;
    }

    private void setupTabbedPane() {
        // Nothing to set up
    }

    private void createComponents() {
        this.studentsTabPanel = new StudentsTabPanel(mainWindow, srs);
        this.coursesTabPanel = new CoursesTabPanel(mainWindow, srs);
        this.courseOfferingsTabPanel = new CourseOfferingsTabPanel(mainWindow,
            srs);
        this.enrolmentsTabPanel = new EnrolmentsTabPanel(mainWindow, srs);
        this.staffTabPanel = new StaffTabPanel(mainWindow, srs);
    }

    private void addComponents() {
        this.addTab("Students", studentsTabPanel);
        this.addTab("Courses", coursesTabPanel);
        this.addTab("Course Offerings", courseOfferingsTabPanel);
        this.addTab("Enrolments", enrolmentsTabPanel);
        this.addTab("Staff", staffTabPanel);
    }

    public void refresh() {
        this.studentsTabPanel.refresh();
        this.coursesTabPanel.refresh();
        this.courseOfferingsTabPanel.refresh();
        this.enrolmentsTabPanel.refresh();
        this.staffTabPanel.refresh();
    }

}
