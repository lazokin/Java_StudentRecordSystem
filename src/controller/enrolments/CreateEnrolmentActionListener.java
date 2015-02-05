package controller.enrolments;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.StudentRecordSystem;
import model.courses.CourseOffering;
import model.exceptions.SRSException;
import model.students.Student;
import view.MainWindow;

import com.lazokin.util.SwingInput;

public class CreateEnrolmentActionListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public CreateEnrolmentActionListener(MainWindow mainWindow,
        StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Collection<Student> students = srs.getAllStudents();
        Collection<CourseOffering> courseOfferings = srs
            .getAllCourseOfferings();

        if (students == null) {
            JOptionPane.showMessageDialog(null, "There are no students.",
                "Create Enrolment Error", JOptionPane.ERROR_MESSAGE);
        } else if (courseOfferings == null) {
            JOptionPane.showMessageDialog(null,
                "There are no course offerings.", "Create Enrolment Error",
                JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String[]> comboBoxContents = new ArrayList<String[]>();
            comboBoxContents.add(createStudentComboBoxContents(students));
            comboBoxContents
                .add(createCourseOfferingComboBoxContents(courseOfferings));
            String[] input = SwingInput.showComboDialog(mainWindow,
                "Create Enrolment", 300, new String[] { "Student",
                    "Course Offering" }, comboBoxContents);
            if (input != null) {
                try {
                    String studentId = getStudentIdFromCollection(students,
                        input[0]);
                    String courseOfferingId = getCourseOfferingIdFromCollection(
                        courseOfferings, input[1]);
                    srs.createEnrolment(studentId, courseOfferingId);
                    mainWindow.refresh();
                } catch (SRSException e) {
                    JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                        "Create Enrolment Error", JOptionPane.ERROR_MESSAGE);
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

    private String[] createCourseOfferingComboBoxContents(
        Collection<CourseOffering> courseOfferings) {
        String[] result = new String[courseOfferings.size()];
        int idx = 0;
        for (CourseOffering co : courseOfferings) {
            result[idx++] = co.getId() + " (" + co.getCourse().getName() + ")";
        }
        return result;
    }

    private String getStudentIdFromCollection(Collection<Student> students,
        String selectedIndex) {
        return ((Student) students.toArray()[Integer.valueOf(selectedIndex)])
            .getId();
    }

    private String getCourseOfferingIdFromCollection(
        Collection<CourseOffering> courseOfferings, String selectedIndex) {
        return ((CourseOffering) courseOfferings.toArray()[Integer
            .valueOf(selectedIndex)]).getId();
    }

}
