package view.tabs.courseoffering;

import java.awt.Color;
import java.util.Collection;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.StudentRecordSystem;
import model.courses.CourseOffering;
import model.staff.interfaces.Instructor;
import model.staff.interfaces.Staff;
import view.MainWindow;
import view.io.SRS_IO;

@SuppressWarnings("serial")
public class CourseOfferingsScrollPane extends JScrollPane {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private JTable courseOfferingsTable;
    private DefaultTableModel allCourseOfferingsTableModel;
    private DefaultTableModel currentCourseOfferingsTableModel;
    private DefaultTableModel completedCourseOfferingsTableModel;
    private DefaultTableModel currentTableModel;

    public CourseOfferingsScrollPane(MainWindow mainWindow,
        StudentRecordSystem srs) {
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
        this.courseOfferingsTable = new JTable();
        this.allCourseOfferingsTableModel = new DefaultTableModel();
        this.currentCourseOfferingsTableModel = new DefaultTableModel();
        this.completedCourseOfferingsTableModel = new DefaultTableModel();
        this.currentTableModel = this.allCourseOfferingsTableModel;
    }

    private void setupComponents() {
        this.courseOfferingsTable.setEnabled(false);
        this.courseOfferingsTable.setBorder(BorderFactory
            .createLineBorder(Color.GRAY));
        this.courseOfferingsTable
            .setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    }

    private void addComponents() {
        this.setViewportView(courseOfferingsTable);
    }

    public void showAllCourseOfferingsTable() {
        this.updateAllCourseOfferingsTable();
        this.courseOfferingsTable.setModel(this.allCourseOfferingsTableModel);
        this.currentTableModel = this.allCourseOfferingsTableModel;
    }

    public void showCurrentCourseOfferingsTable() {
        this.updateCurrentCourseOfferingsTable();
        this.courseOfferingsTable
            .setModel(this.currentCourseOfferingsTableModel);
        this.currentTableModel = this.currentCourseOfferingsTableModel;
    }

    public void showCompletedCourseOfferingsTable() {
        this.updateCompletedCourseOfferingsTable();
        this.courseOfferingsTable
            .setModel(this.completedCourseOfferingsTableModel);
        this.currentTableModel = this.completedCourseOfferingsTableModel;
    }

    public void updateAllCourseOfferingsTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("ID");
        columnIdentifiers.add("Course");
        columnIdentifiers.add("Year");
        columnIdentifiers.add("Textbook");
        columnIdentifiers.add("Fee");
        columnIdentifiers.add("Instructor");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<CourseOffering> courseOfferings = this.srs
            .getAllCourseOfferings();
        if (courseOfferings != null) {
            for (CourseOffering co : courseOfferings) {
                Vector<String> row = new Vector<String>();
                row.add(co.getId());
                row.add(co.getCourse().getName());
                row.add(co.getYear());
                row.add(co.getTextbook());
                row.add(String.valueOf(co.getFee()));
                row.add(parseInstructor(co.getInstructor()));
                dataVector.add(row);
            }
        }
        this.allCourseOfferingsTableModel.setDataVector(dataVector,
            columnIdentifiers);
    }

    public void updateCurrentCourseOfferingsTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("ID");
        columnIdentifiers.add("Course");
        columnIdentifiers.add("Year");
        columnIdentifiers.add("Textbook");
        columnIdentifiers.add("Fee");
        columnIdentifiers.add("Instructor");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<CourseOffering> courseOfferings = this.srs
            .getAllCourseOfferings();
        if (courseOfferings != null) {
            for (CourseOffering co : courseOfferings) {
                if (!co.isCompleted()) {
                    Vector<String> row = new Vector<String>();
                    row.add(co.getId());
                    row.add(co.getCourse().getName());
                    row.add(co.getYear());
                    row.add(co.getTextbook());
                    row.add(String.valueOf(co.getFee()));
                    row.add(parseInstructor(co.getInstructor()));
                    dataVector.add(row);
                }
            }
        }
        this.currentCourseOfferingsTableModel.setDataVector(dataVector,
            columnIdentifiers);
    }

    public void updateCompletedCourseOfferingsTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("ID");
        columnIdentifiers.add("Course");
        columnIdentifiers.add("Year");
        columnIdentifiers.add("Textbook");
        columnIdentifiers.add("Fee");
        columnIdentifiers.add("Instructor");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<CourseOffering> courseOfferings = this.srs
            .getAllCourseOfferings();
        if (courseOfferings != null) {
            for (CourseOffering co : courseOfferings) {
                if (co.isCompleted()) {
                    Vector<String> row = new Vector<String>();
                    row.add(co.getId());
                    row.add(co.getCourse().getName());
                    row.add(co.getYear());
                    row.add(co.getTextbook());
                    row.add(String.valueOf(co.getFee()));
                    row.add(parseInstructor(co.getInstructor()));
                    dataVector.add(row);
                }
            }
        }
        this.completedCourseOfferingsTableModel.setDataVector(dataVector,
            columnIdentifiers);
    }

    public void refresh() {
        if (currentTableModel == allCourseOfferingsTableModel) {
            this.showAllCourseOfferingsTable();
        }
        if (currentTableModel == currentCourseOfferingsTableModel) {
            this.showCurrentCourseOfferingsTable();
        }
        if (currentTableModel == completedCourseOfferingsTableModel) {
            this.showCompletedCourseOfferingsTable();
        }
    }

    private String parseInstructor(Instructor instructor) {
        return (instructor == null) ? "None" : ((Staff) instructor).getName();
    }

    public void saveCurrentTable() {
        String title = null;
        if (currentTableModel == allCourseOfferingsTableModel) {
            title = "All Course Offering Details";
        }
        if (currentTableModel == currentCourseOfferingsTableModel) {
            title = "Current Course Offering Details";
        }
        if (currentTableModel == completedCourseOfferingsTableModel) {
            title = "Completed Course Offering Details";
        }
        SRS_IO.saveTableToFile(currentTableModel, title,
            mainWindow);
    }

}
