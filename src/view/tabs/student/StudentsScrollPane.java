package view.tabs.student;

import java.awt.Color;
import java.util.Collection;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.StudentRecordSystem;
import model.courses.Course;
import model.enrolments.Enrolment;
import model.students.Student;
import view.MainWindow;
import view.io.SRS_IO;

@SuppressWarnings("serial")
public class StudentsScrollPane extends JScrollPane {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private JTable studentsTable;
    private DefaultTableModel personalTableModel;
    private DefaultTableModel academicTableModel;
    private DefaultTableModel financialTableModel;
    private DefaultTableModel exemptionsTableModel;
    private DefaultTableModel currentEnrolmentsTableModel;
    private DefaultTableModel completedEnrolmentsTableModel;
    private DefaultTableModel currentTableModel;

    public StudentsScrollPane(MainWindow mainWindow, StudentRecordSystem srs) {
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

    }

    private void createComponents() {
        this.studentsTable = new JTable();
        this.personalTableModel = new DefaultTableModel();
        this.academicTableModel = new DefaultTableModel();
        this.financialTableModel = new DefaultTableModel();
        this.exemptionsTableModel = new DefaultTableModel();
        this.currentEnrolmentsTableModel = new DefaultTableModel();
        this.completedEnrolmentsTableModel = new DefaultTableModel();
        this.currentTableModel = this.personalTableModel;
    }

    private void setupComponents() {
        this.studentsTable.setEnabled(false);
        this.studentsTable
            .setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.studentsTable
            .setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    }

    private void addComponents() {
        this.setViewportView(studentsTable);
    }

    public void showPersonalTable() {
        this.updatePersonalTable();
        this.studentsTable.setModel(this.personalTableModel);
        this.currentTableModel = this.personalTableModel;
    }

    public void showAcademicTable() {
        this.updateAcademicTable();
        this.studentsTable.setModel(this.academicTableModel);
        this.currentTableModel = this.academicTableModel;
    }

    public void showFinanicalTable() {
        this.updateFinancialTable();
        this.studentsTable.setModel(this.financialTableModel);
        this.currentTableModel = this.financialTableModel;
    }

    public void showExemptionsTable() {
        this.updateExemptionsTable();
        this.studentsTable.setModel(this.exemptionsTableModel);
        this.currentTableModel = this.exemptionsTableModel;
    }

    public void showCurrentEnrolmentsTable() {
        this.updateCurrentEnrolmentsTable();
        this.studentsTable.setModel(this.currentEnrolmentsTableModel);
        this.currentTableModel = this.currentEnrolmentsTableModel;
    }

    public void showCompletedEnrolmentsTable() {
        this.updateCompletedEnrolmentsTable();
        this.studentsTable.setModel(this.completedEnrolmentsTableModel);
        this.currentTableModel = this.completedEnrolmentsTableModel;
    }

    public void updatePersonalTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("ID");
        columnIdentifiers.add("Name");
        columnIdentifiers.add("Year");
        columnIdentifiers.add("Age");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Student> students = this.srs.getAllStudents();
        if (students != null) {
            for (Student s : students) {
                Vector<String> row = new Vector<String>();
                row.add(s.getId());
                row.add(s.getName());
                row.add(s.getYearOfBirth());
                row.add(String.valueOf(s.getAge()));
                dataVector.add(row);
            }
        }

        this.personalTableModel.setDataVector(dataVector, columnIdentifiers);
    }

    public void updateAcademicTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("ID");
        columnIdentifiers.add("Name");
        columnIdentifiers.add("Current Enrolments");
        columnIdentifiers.add("Completed Enrolments");
        columnIdentifiers.add("Standard Certificate");
        columnIdentifiers.add("Advanced Certificate");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Student> students = this.srs.getAllStudents();
        if (students != null) {
            for (Student s : students) {
                Vector<String> row = new Vector<String>();
                row.add(s.getId());
                row.add(s.getName());
                row.add(parseBoolean(s.hasCurrentEnrolments()));
                row.add(parseBoolean(s.hasCompletedEnrolments()));
                row.add(parseBoolean(s.hasStandardCertificate()));
                row.add(parseBoolean(s.hasAdvancedCertificate()));
                dataVector.add(row);
            }
        }
        this.academicTableModel.setDataVector(dataVector, columnIdentifiers);
    }

    public void updateFinancialTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("ID");
        columnIdentifiers.add("Name");
        columnIdentifiers.add("Tuition Total");
        columnIdentifiers.add("Tuition Balance");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Student> students = this.srs.getAllStudents();
        if (students != null) {
            for (Student s : students) {
                Vector<String> row = new Vector<String>();
                row.add(s.getId());
                row.add(s.getName());
                row.add(parseBalance(s.getTuitionFeeTotal()));
                row.add(parseBalance(s.getTuitionFeeBalance()));
                dataVector.add(row);
            }
        }
        this.financialTableModel.setDataVector(dataVector, columnIdentifiers);
    }

    public void updateExemptionsTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("ID");
        columnIdentifiers.add("Name");
        columnIdentifiers.add("Exemptions");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Student> students = this.srs.getAllStudents();
        if (students != null) {
            for (Student s : students) {
                Vector<String> row = new Vector<String>();
                row.add(s.getId());
                row.add(s.getName());
                row.add(parseCourseIds(s.getExemptions().values()));
                dataVector.add(row);
            }
        }
        this.exemptionsTableModel.setDataVector(dataVector, columnIdentifiers);
    }

    public void updateCurrentEnrolmentsTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("ID");
        columnIdentifiers.add("Name");
        columnIdentifiers.add("Current Enrolments");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Student> students = this.srs.getAllStudents();
        if (students != null) {
            for (Student s : students) {
                Vector<String> row = new Vector<String>();
                row.add(s.getId());
                row.add(s.getName());
                row.add(parseEnrolments(s.getCurrentEnrolments().values()));
                dataVector.add(row);
            }
        }
        this.currentEnrolmentsTableModel.setDataVector(dataVector,
            columnIdentifiers);
    }

    public void updateCompletedEnrolmentsTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("ID");
        columnIdentifiers.add("Name");
        columnIdentifiers.add("Current Enrolments");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Student> students = this.srs.getAllStudents();
        if (students != null) {
            for (Student s : students) {
                Vector<String> row = new Vector<String>();
                row.add(s.getId());
                row.add(s.getName());
                row.add(parseEnrolmentsWithGrade(s.getCompletedEnrolments()
                    .values()));
                dataVector.add(row);
            }
        }
        this.completedEnrolmentsTableModel.setDataVector(dataVector,
            columnIdentifiers);
    }

    private String parseBoolean(boolean bool) {
        return bool ? "Yes" : "No";
    }

    private String parseBalance(int balance) {
        if (balance < 0) {
            return "($" + Math.abs(balance) + ")";
        } else {
            return "$" + balance + "";
        }
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

    private String parseEnrolments(Collection<Enrolment> enrolments) {
        String result = "";
        if (enrolments.isEmpty()) {
            result = "None";
        } else {
            for (Enrolment e : enrolments) {
                result += e.getCourseOffering().getId() + ", ";
            }
            result = result.substring(0, result.length() - 2);
        }
        return result;
    }

    private String parseEnrolmentsWithGrade(Collection<Enrolment> enrolments) {
        String result = "";
        if (enrolments.isEmpty()) {
            result = "None";
        } else {
            for (Enrolment e : enrolments) {
                result += e.getCourseOffering().getId() + "[" + e.getGrade()
                    + "], ";
            }
            result = result.substring(0, result.length() - 2);
        }
        return result;
    }

    public void refresh() {
        if (currentTableModel == personalTableModel) {
            this.showPersonalTable();
        }
        if (currentTableModel == academicTableModel) {
            this.showAcademicTable();
        }
        if (currentTableModel == financialTableModel) {
            this.showFinanicalTable();
        }
        if (currentTableModel == exemptionsTableModel) {
            this.showExemptionsTable();
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
        if (currentTableModel == personalTableModel) {
            title = "Student Personal Details";
        }
        if (currentTableModel == academicTableModel) {
            title = "Student Academic Details";
        }
        if (currentTableModel == financialTableModel) {
            title = "Student Financial Details";
        }
        if (currentTableModel == exemptionsTableModel) {
            title = "Student Exemption Details";
        }
        if (currentTableModel == currentEnrolmentsTableModel) {
            title = "Student Current Enrolment Details";
        }
        if (currentTableModel == completedEnrolmentsTableModel) {
            title = "Student Completed Enrolment Details";
        }
        SRS_IO.saveTableToFile(currentTableModel, title,
            mainWindow);
    }

}
