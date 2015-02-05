package view.tabs.course;

import java.awt.Color;
import java.util.Collection;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.StudentRecordSystem;
import model.courses.AdvancedCourse;
import model.courses.Course;
import model.courses.StandardCourse;
import view.MainWindow;
import view.io.SRS_IO;

@SuppressWarnings("serial")
public class CoursesScrollPane extends JScrollPane {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private JTable coursesTable;
    private DefaultTableModel allCoursesTableModel;
    private DefaultTableModel standardCoursesTableModel;
    private DefaultTableModel advancedCoursesTableModel;
    private DefaultTableModel prerequisitesTableModel;
    private DefaultTableModel currentTableModel;

    public CoursesScrollPane(MainWindow mainWindow, StudentRecordSystem srs) {
        super();
        this.processParameters(mainWindow, srs);
        this.setupScrollPane();
        this.createComponents();
        this.setupComponents();
        this.addComponents();
    }

    private void processParameters(MainWindow mainWindow,
        StudentRecordSystem srs) {
        this.mainWindow = mainWindow;
        this.srs = srs;
    }

    private void setupScrollPane() {
        // No setup required
    }

    private void createComponents() {
        this.coursesTable = new JTable();
        this.allCoursesTableModel = new DefaultTableModel();
        this.standardCoursesTableModel = new DefaultTableModel();
        this.advancedCoursesTableModel = new DefaultTableModel();
        this.prerequisitesTableModel = new DefaultTableModel();
        this.currentTableModel = this.allCoursesTableModel;
    }

    private void setupComponents() {
        this.coursesTable.setEnabled(false);
        this.coursesTable.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.coursesTable
            .setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    }

    private void addComponents() {
        this.setViewportView(coursesTable);
    }

    public void showAllCoursesTable() {
        this.updateAllCoursesTable();
        this.coursesTable.setModel(this.allCoursesTableModel);
        this.currentTableModel = this.allCoursesTableModel;
    }

    public void showStandardCoursesTable() {
        this.updateStandardCoursesTable();
        this.coursesTable.setModel(this.standardCoursesTableModel);
        this.currentTableModel = this.standardCoursesTableModel;
    }

    public void showAdvancedCoursesTable() {
        this.updateAdvancedCoursesTable();
        this.coursesTable.setModel(this.advancedCoursesTableModel);
        this.currentTableModel = this.advancedCoursesTableModel;
    }

    public void showPrerequisitesTable() {
        this.updatePrerequisitesTable();
        this.coursesTable.setModel(this.prerequisitesTableModel);
        this.currentTableModel = this.prerequisitesTableModel;
    }

    public void updateAllCoursesTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("ID");
        columnIdentifiers.add("Name");
        columnIdentifiers.add("Has Offerings");
        columnIdentifiers.add("Has Prerequisites");
        columnIdentifiers.add("Fee");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Course> courses = this.srs.getAllCourses();
        if (courses != null) {
            for (Course c : courses) {
                Vector<String> row = new Vector<String>();
                row.add(c.getId());
                row.add(c.getName());
                row.add(parseBoolean(c.hasCourseOfferings()));
                row.add(parseBoolean(c.hasPrerequisites()));
                row.add(String.valueOf(c.getFee()));
                dataVector.add(row);
            }
        }
        this.allCoursesTableModel.setDataVector(dataVector, columnIdentifiers);
    }

    public void updateStandardCoursesTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("ID");
        columnIdentifiers.add("Name");
        columnIdentifiers.add("Has Offerings");
        columnIdentifiers.add("Has Prerequisites");
        columnIdentifiers.add("Fee");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Course> courses = this.srs.getAllCourses();
        if (courses != null) {
            for (Course c : courses) {
                if (c instanceof StandardCourse) {
                    Vector<String> row = new Vector<String>();
                    row.add(c.getId());
                    row.add(c.getName());
                    row.add(parseBoolean(c.hasCourseOfferings()));
                    row.add(parseBoolean(c.hasPrerequisites()));
                    row.add(String.valueOf(c.getFee()));
                    dataVector.add(row);
                }
            }
        }
        this.standardCoursesTableModel.setDataVector(dataVector,
            columnIdentifiers);
    }

    public void updateAdvancedCoursesTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("ID");
        columnIdentifiers.add("Name");
        columnIdentifiers.add("Has Offerings");
        columnIdentifiers.add("Has Prerequisites");
        columnIdentifiers.add("Fee");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Course> courses = this.srs.getAllCourses();
        if (courses != null) {
            for (Course c : courses) {
                if (c instanceof AdvancedCourse) {
                    Vector<String> row = new Vector<String>();
                    row.add(c.getId());
                    row.add(c.getName());
                    row.add(parseBoolean(c.hasCourseOfferings()));
                    row.add(parseBoolean(c.hasPrerequisites()));
                    row.add(String.valueOf(c.getFee()));
                    dataVector.add(row);
                }
            }
        }
        this.advancedCoursesTableModel.setDataVector(dataVector,
            columnIdentifiers);
    }

    public void updatePrerequisitesTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("ID");
        columnIdentifiers.add("Name");
        columnIdentifiers.add("Prerequisites");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Course> courses = this.srs.getAllCourses();
        if (courses != null) {
            for (Course c : courses) {
                Vector<String> row = new Vector<String>();
                row.add(c.getId());
                row.add(c.getName());
                row.add(parseCourseIds(c.getPrerequisites().values()));
                dataVector.add(row);
            }
        }
        this.prerequisitesTableModel.setDataVector(dataVector,
            columnIdentifiers);
    }

    private String parseBoolean(boolean bool) {
        return bool ? "Yes" : "No";
    }

    private String parseCourseIds(Collection<Course> courses) {
        String result = "";
        if (courses.isEmpty()) {
            result = "None";
        } else {
            for (Course c : courses) {
                result += c.getId() + ", ";
            }
            result = result.substring(0, result.length() - 2);
        }
        return result;
    }

    public void refresh() {
        if (currentTableModel == allCoursesTableModel) {
            this.showAllCoursesTable();
        }
        if (currentTableModel == standardCoursesTableModel) {
            this.showStandardCoursesTable();
        }
        if (currentTableModel == advancedCoursesTableModel) {
            this.showAdvancedCoursesTable();
        }
        if (currentTableModel == prerequisitesTableModel) {
            this.showPrerequisitesTable();
        }
    }

    public void saveCurrentTable() {
        String title = null;
        if (currentTableModel == allCoursesTableModel) {
            title = "All Course Details";
        }
        if (currentTableModel == standardCoursesTableModel) {
            title = "All Standard Course Details";
        }
        if (currentTableModel == advancedCoursesTableModel) {
            title = "All Advanced Course Details";
        }
        if (currentTableModel == prerequisitesTableModel) {
            title = "All Course Details";
        }
        SRS_IO.saveTableToFile(currentTableModel, title,
            mainWindow);
    }

}
