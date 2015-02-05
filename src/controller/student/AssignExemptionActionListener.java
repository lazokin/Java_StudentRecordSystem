package controller.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.StudentRecordSystem;
import model.courses.Course;
import model.exceptions.SRSException;
import model.students.Student;
import view.MainWindow;

import com.lazokin.util.SwingInput;

public class AssignExemptionActionListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public AssignExemptionActionListener(MainWindow mainWindow,
            StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Collection<Student> students = srs.getAllStudents();
        Collection<Course> courses = srs.getAllCourses();

        if (students == null) {
            JOptionPane.showMessageDialog(null, "There are no students.",
                    "Assign Exemption Error", JOptionPane.ERROR_MESSAGE);
        } else if (courses == null) {
            JOptionPane.showMessageDialog(null, "There are no courses.",
                    "Assign Exemption Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String[]> comboBoxContents = new ArrayList<String[]>();
            comboBoxContents.add(createStudentComboBoxContents(students));
            comboBoxContents.add(createCourseComboBoxContents(courses));
            String[] input = SwingInput.showComboDialog(mainWindow,
                    "Assign Exemption", 300, new String[] { "Student",
                            "Exemption" }, comboBoxContents);
            if (input != null) {
                try {
                    String studentId = getStudentIdFromCollection(students,
                            input[0]);
                    String courseId = getCourseIdFromCollection(courses,
                            input[1]);
                    srs.assignExemption(studentId, courseId);
                    mainWindow.refresh();
                } catch (SRSException e) {
                    JOptionPane
                            .showMessageDialog(mainWindow, e.getMessage(),
                                    "Assign Exemption Error",
                                    JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    private String[] createStudentComboBoxContents(Collection<Student> students) {
        String[] result = new String[students.size()];
        int idx = 0;
        for (Student s : students) {
            result[idx++] = s.getId() + " (" + s.getName() + ")";
        }
        return result;
    }

    private String[] createCourseComboBoxContents(Collection<Course> courses) {
        String[] result = new String[courses.size()];
        int idx = 0;
        for (Course c : courses) {
            result[idx++] = c.getId() + " (" + c.getName() + ")";
        }
        return result;
    }

    private String getStudentIdFromCollection(Collection<Student> students,
            String selectedIndex) {
        return ((Student) students.toArray()[Integer.valueOf(selectedIndex)])
                .getId();
    }

    private String getCourseIdFromCollection(Collection<Course> courses,
            String selectedIndex) {
        return ((Course) courses.toArray()[Integer.valueOf(selectedIndex)])
                .getId();
    }

}
