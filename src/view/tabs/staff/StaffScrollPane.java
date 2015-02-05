package view.tabs.staff;

import java.awt.Color;
import java.util.Collection;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.StudentRecordSystem;
import model.courses.Course;
import model.courses.CourseOffering;
import model.staff.EmploymentType;
import model.staff.interfaces.Instructor;
import model.staff.interfaces.Staff;
import view.MainWindow;
import view.io.SRS_IO;

@SuppressWarnings("serial")
public class StaffScrollPane extends JScrollPane {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    private JTable staffTable;
    private DefaultTableModel allStaffTableModel;
    private DefaultTableModel permanentStaffTableModel;
    private DefaultTableModel casualStaffTableModel;
    private DefaultTableModel studentInstructorsTableModel;
    private DefaultTableModel instructorAssignmentsTableModel;
    private DefaultTableModel instructorPermissionsTableModel;
    private DefaultTableModel currentTableModel;

    public StaffScrollPane(MainWindow mainWindow, StudentRecordSystem srs) {
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
        this.staffTable = new JTable();
        this.allStaffTableModel = new DefaultTableModel();
        this.permanentStaffTableModel = new DefaultTableModel();
        this.casualStaffTableModel = new DefaultTableModel();
        this.studentInstructorsTableModel = new DefaultTableModel();
        this.instructorAssignmentsTableModel = new DefaultTableModel();
        this.instructorPermissionsTableModel = new DefaultTableModel();
        this.currentTableModel = this.allStaffTableModel;
    }

    private void setupComponents() {
        this.staffTable.setEnabled(false);
        this.staffTable.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.staffTable
            .setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    }

    private void addComponents() {
        this.setViewportView(staffTable);
    }

    public void showAllStaffTable() {
        this.updateAllStaffTable();
        this.staffTable.setModel(this.allStaffTableModel);
        this.currentTableModel = this.allStaffTableModel;
    }

    public void showPermanentStaffTable() {
        this.updatePermanentStaffTable();
        this.staffTable.setModel(this.permanentStaffTableModel);
        this.currentTableModel = this.permanentStaffTableModel;
    }

    public void showCasualStaffTable() {
        this.updateCasualStaffTable();
        this.staffTable.setModel(this.casualStaffTableModel);
        this.currentTableModel = this.casualStaffTableModel;
    }

    public void showStudentInstructorsTable() {
        this.updateStudentInstructorsTable();
        this.staffTable.setModel(this.studentInstructorsTableModel);
        this.currentTableModel = this.studentInstructorsTableModel;
    }

    public void showInstructorAssignmentsTable() {
        this.updateInstructorAssignmentsTable();
        this.staffTable.setModel(this.instructorAssignmentsTableModel);
        this.currentTableModel = this.instructorAssignmentsTableModel;
    }

    public void showInstructorPermissionsTable() {
        this.updateInstructorPermissionsTable();
        this.staffTable.setModel(this.instructorPermissionsTableModel);
        this.currentTableModel = this.instructorPermissionsTableModel;
    }

    public void updateAllStaffTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("ID");
        columnIdentifiers.add("Name");
        columnIdentifiers.add("Position");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Staff> staff = this.srs.getAllStaff();
        if (staff != null) {
            for (Staff s : staff) {
                Vector<String> row = new Vector<String>();
                row.add(s.getId());
                row.add(s.getName());
                row.add(s.getPositionType().toString());
                dataVector.add(row);
            }
        }
        this.allStaffTableModel.setDataVector(dataVector, columnIdentifiers);
    }

    public void updatePermanentStaffTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("ID");
        columnIdentifiers.add("Name");
        columnIdentifiers.add("Position");
        columnIdentifiers.add("Salary");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Staff> staff = this.srs.getAllStaff();
        if (staff != null) {
            for (Staff s : staff) {
                if (s.getEmploymentType() == EmploymentType.Permanent) {
                    Vector<String> row = new Vector<String>();
                    row.add(s.getId());
                    row.add(s.getName());
                    row.add(s.getPositionType().toString());
                    row.add(String.valueOf(s.getPay()));
                    dataVector.add(row);
                }
            }
        }
        this.permanentStaffTableModel.setDataVector(dataVector,
            columnIdentifiers);
    }

    public void updateCasualStaffTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("ID");
        columnIdentifiers.add("Name");
        columnIdentifiers.add("Position");
        columnIdentifiers.add("Hourly Rate");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Staff> staff = this.srs.getAllStaff();
        if (staff != null) {
            for (Staff s : staff) {
                if (s.getEmploymentType() == EmploymentType.Casual) {
                    Vector<String> row = new Vector<String>();
                    row.add(s.getId());
                    row.add(s.getName());
                    row.add(s.getPositionType().toString());
                    row.add(String.valueOf(s.getPay()));
                    dataVector.add(row);
                }
            }
        }
        this.casualStaffTableModel.setDataVector(dataVector, columnIdentifiers);
    }

    public void updateStudentInstructorsTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("Staff ID");
        columnIdentifiers.add("Name");
        columnIdentifiers.add("Student ID");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Instructor> instructors = this.srs.getAllInstructors();
        if (instructors != null) {
            for (Instructor i : instructors) {
                if (i.isStudent()) {
                    Vector<String> row = new Vector<String>();
                    row.add(((Staff) i).getId());
                    row.add(((Staff) i).getName());
                    row.add(i.getStudent().getId());
                    dataVector.add(row);
                }
            }
        }
        this.studentInstructorsTableModel.setDataVector(dataVector,
            columnIdentifiers);
    }

    public void updateInstructorAssignmentsTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("ID");
        columnIdentifiers.add("Name");
        columnIdentifiers.add("Current Assignments");
        columnIdentifiers.add("Previous Assignments");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Instructor> instructors = this.srs.getAllInstructors();
        if (instructors != null) {
            for (Instructor i : instructors) {
                Vector<String> row = new Vector<String>();
                row.add(((Staff) i).getId());
                row.add(((Staff) i).getName());
                row.add(parseCourseOfferingIds(i.getCurrentlyInstructing()
                    .values()));
                row.add(parseCourseOfferingIds(i.getPreviouslyInstructed()
                    .values()));
                dataVector.add(row);
            }
        }
        this.instructorAssignmentsTableModel.setDataVector(dataVector,
            columnIdentifiers);
    }

    public void updateInstructorPermissionsTable() {
        Vector<String> columnIdentifiers = new Vector<String>();
        columnIdentifiers.add("ID");
        columnIdentifiers.add("Name");
        columnIdentifiers.add("Can Instruct");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Collection<Instructor> instructors = this.srs.getAllInstructors();
        if (instructors != null) {
            for (Instructor i : instructors) {
                Vector<String> row = new Vector<String>();
                row.add(((Staff) i).getId());
                row.add(((Staff) i).getName());
                row.add(parseCourseIds(i.getCanInstruct().values()));
                dataVector.add(row);
            }
        }
        this.instructorPermissionsTableModel.setDataVector(dataVector,
            columnIdentifiers);
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

    private String parseCourseOfferingIds(
        Collection<CourseOffering> courseOfferings) {
        String result = "";
        if (courseOfferings.isEmpty()) {
            result = "None";
        } else {
            for (CourseOffering co : courseOfferings) {
                result += co.getId() + ", ";
            }
            result = result.substring(0, result.length() - 2);
        }
        return result;
    }

    public void refresh() {
        if (currentTableModel == allStaffTableModel) {
            this.showAllStaffTable();
        }
        if (currentTableModel == permanentStaffTableModel) {
            this.showPermanentStaffTable();
        }
        if (currentTableModel == casualStaffTableModel) {
            this.showCasualStaffTable();
        }
        if (currentTableModel == studentInstructorsTableModel) {
            this.showStudentInstructorsTable();
        }
        if (currentTableModel == instructorAssignmentsTableModel) {
            this.showInstructorAssignmentsTable();
        }
        if (currentTableModel == instructorPermissionsTableModel) {
            this.showInstructorPermissionsTable();
        }
    }

    public void saveCurrentTable() {
        String title = null;
        if (currentTableModel == allStaffTableModel) {
            title = "All Staff Details";
        }
        if (currentTableModel == permanentStaffTableModel) {
            title = "Permanent Staff Details";
        }
        if (currentTableModel == casualStaffTableModel) {
            title = "Casual Staff Details";
        }
        if (currentTableModel == studentInstructorsTableModel) {
            title = "Student Instructor Details";
        }
        if (currentTableModel == instructorAssignmentsTableModel) {
            title = "Instructor Assignment Details";
        }
        if (currentTableModel == instructorPermissionsTableModel) {
            title = "Instructor Permission Details";
        }
        SRS_IO.saveTableToFile(currentTableModel, title,
            mainWindow);
    }

}
