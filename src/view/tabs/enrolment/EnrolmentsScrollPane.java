package view.tabs.enrolment;

import java.awt.Color;
import java.util.Collection;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.StudentRecordSystem;
import model.enrolments.Enrolment;
import view.MainWindow;
import view.io.SRS_IO;

@SuppressWarnings("serial")
public class EnrolmentsScrollPane extends JScrollPane {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private JTable enrolmentsTable;
    private DefaultTableModel allEnrolmentsTableModel;
    private DefaultTableModel currentEnrolmentsTableModel;
    private DefaultTableModel completedEnrolmentsTableModel;
    private DefaultTableModel currentTableModel;

    public EnrolmentsScrollPane(MainWindow mainWindow, StudentRecordSystem srs) {
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
        this.enrolmentsTable = new JTable();
        this.allEnrolmentsTableModel = new DefaultTableModel();
        this.currentEnrolmentsTableModel = new DefaultTableModel();
        this.completedEnrolmentsTableModel = new DefaultTableModel();
        this.currentTableModel = this.allEnrolmentsTableModel;
    }

    private void setupComponents() {
        this.enrolmentsTable.setEnabled(false);
        this.enrolmentsTable.setBorder(BorderFactory
            .createLineBorder(Color.GRAY));
        this.enrolmentsTable
            .setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    }

    private void addComponents() {
        this.setViewportView(enrolmentsTable);
    }

    public void showAllEnrolmentsTable() {
        this.updateAllEnrolmentsTable();
        this.enrolmentsTable.setModel(this.allEnrolmentsTableModel);
        this.currentTableModel = this.allEnrolmentsTableModel;
    }

    public void showCurrentEnrolmentsTable() {
        this.updateCurrentEnrolmentsTable();
        this.enrolmentsTable.setModel(this.currentEnrolmentsTableModel);
        this.currentTableModel = this.currentEnrolmentsTableModel;
    }

    public void showCompletedEnrolmentsTable() {
        this.updateCompletedEnrolmentsTable();
        this.enrolmentsTable.setModel(this.completedEnrolmentsTableModel);
        this.currentTableModel = this.completedEnrolmentsTableModel;
    }

    public void updateAllEnrolmentsTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("Student Id");
        columnIdentifiers.add("Student Name");
        columnIdentifiers.add("Course");
        columnIdentifiers.add("Course Offering");
        columnIdentifiers.add("Fee");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Enrolment> enrolments = this.srs.getAllEnrolments();
        if (enrolments != null) {
            for (Enrolment e : enrolments) {
                Vector<String> row = new Vector<String>();
                row.add(e.getStudent().getId());
                row.add(e.getStudent().getName());
                row.add(e.getCourse().getName());
                row.add(e.getCourseOffering().getId());
                row.add(String.valueOf(e.getFee()));
                dataVector.add(row);
            }
        }
        this.allEnrolmentsTableModel.setDataVector(dataVector,
            columnIdentifiers);
    }

    public void updateCurrentEnrolmentsTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("Student Id");
        columnIdentifiers.add("Student Name");
        columnIdentifiers.add("Course");
        columnIdentifiers.add("Course Offering");
        columnIdentifiers.add("Fee");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Enrolment> enrolments = this.srs.getAllEnrolments();
        if (enrolments != null) {
            for (Enrolment e : enrolments) {
                if (!e.isCompleted()) {
                    Vector<String> row = new Vector<String>();
                    row.add(e.getStudent().getId());
                    row.add(e.getStudent().getName());
                    row.add(e.getCourse().getName());
                    row.add(e.getCourseOffering().getId());
                    row.add(String.valueOf(e.getFee()));
                    dataVector.add(row);
                }
            }
        }
        this.currentEnrolmentsTableModel.setDataVector(dataVector,
            columnIdentifiers);
    }

    public void updateCompletedEnrolmentsTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("Student Id");
        columnIdentifiers.add("Student Name");
        columnIdentifiers.add("Course");
        columnIdentifiers.add("Course Offering");
        columnIdentifiers.add("Grade");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Enrolment> enrolments = this.srs.getAllEnrolments();
        if (enrolments != null) {
            for (Enrolment e : enrolments) {
                if (e.isCompleted()) {
                    Vector<String> row = new Vector<String>();
                    row.add(e.getStudent().getId());
                    row.add(e.getStudent().getName());
                    row.add(e.getCourse().getName());
                    row.add(e.getCourseOffering().getId());
                    row.add(String.valueOf(e.getGrade()));
                    dataVector.add(row);
                }
            }
        }
        this.completedEnrolmentsTableModel.setDataVector(dataVector,
            columnIdentifiers);
    }

    public void refresh() {
        if (currentTableModel == allEnrolmentsTableModel) {
            this.showAllEnrolmentsTable();
        }
        if (currentTableModel == currentEnrolmentsTableModel) {
            this.showCurrentEnrolmentsTable();
        }
        if (currentTableModel == completedEnrolmentsTableModel) {
            this.showCompletedEnrolmentsTable();
        }
    }

    public void saveCurrentTable() {
        String title = null;
        if (currentTableModel == allEnrolmentsTableModel) {
            title = "All Enrolment Details";
        }
        if (currentTableModel == currentEnrolmentsTableModel) {
            title = "Current Enrolment Details";
        }
        if (currentTableModel == completedEnrolmentsTableModel) {
            title = "Completed Enrolment Details";
        }
        SRS_IO.saveTableToFile(currentTableModel, title,
            mainWindow);
    }

}
