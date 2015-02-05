package view.menu;

import javax.swing.JMenuBar;

import model.StudentRecordSystem;
import view.MainWindow;

@SuppressWarnings("serial")
public class MainMenuBar extends JMenuBar {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private FileMenu fileMenu;
    private StudentMenu studentMenu;
    private CourseMenu courseMenu;
    private CourseOfferingMenu courseOfferingMenu;
    private EnrolmentMenu enrolmentMenu;
    private StaffMenu staffMenu;

    public MainMenuBar(MainWindow mainWindow, StudentRecordSystem srs) {
        super();
        this.processParameters(mainWindow, srs);
        this.setupMenuBar();
        this.createComponents();
        this.setupComponents();
        this.addComponents();
    }

    private void processParameters(MainWindow mainWindow,
            StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    private void setupMenuBar() {
        // No setup required
    }

    private void createComponents() {
        this.fileMenu = new FileMenu(mainWindow, srs);
        this.studentMenu = new StudentMenu(mainWindow, srs);
        this.courseMenu = new CourseMenu(mainWindow, srs);
        this.courseOfferingMenu = new CourseOfferingMenu(mainWindow, srs);
        this.enrolmentMenu = new EnrolmentMenu(mainWindow, srs);
        this.staffMenu = new StaffMenu(mainWindow, srs);
    }

    private void setupComponents() {
        // No setup required
    }

    private void addComponents() {
        this.add(fileMenu);
        this.add(studentMenu);
        this.add(courseMenu);
        this.add(courseOfferingMenu);
        this.add(enrolmentMenu);
        this.add(staffMenu);
    }

}
