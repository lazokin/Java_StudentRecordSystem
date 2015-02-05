package controller.courseoffering;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.StudentRecordSystem;
import model.courses.CourseOffering;
import model.exceptions.SRSException;
import model.staff.interfaces.Instructor;
import model.staff.interfaces.Staff;
import view.MainWindow;

import com.lazokin.util.SwingInput;

public class AssignInstructorActionListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public AssignInstructorActionListener(MainWindow mainWindow,
        StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Collection<CourseOffering> courseOfferings = srs
            .getAllCourseOfferings();
        Collection<CourseOffering> currentCourseOfferings = new ArrayList<CourseOffering>();
        for (CourseOffering co : courseOfferings) {
            if (!co.isCompleted()) {
                currentCourseOfferings.add(co);
            }
        }
        Collection<Instructor> instructors = srs.getAllInstructors();

        if (currentCourseOfferings.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "There are no course offerings.", "Assign Instructor Error",
                JOptionPane.ERROR_MESSAGE);
        } else if (instructors == null) {
            JOptionPane.showMessageDialog(null, "There are no instructors.",
                "Assign Instructor Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String[]> comboBoxContents = new ArrayList<String[]>();
            comboBoxContents
                .add(createCourseOfferingComboBoxContents(currentCourseOfferings));
            comboBoxContents.add(createInstructorComboBoxContents(instructors));
            String[] input = SwingInput.showComboDialog(mainWindow,
                "Assign Instructor", 300, new String[] { "Course Offering",
                    "Instructor" }, comboBoxContents);
            if (input != null) {
                try {
                    String courseOfferingId = getCourseOfferingIdFromCollection(
                        currentCourseOfferings, input[0]);
                    String staffId = getInstructorIdFromCollection(instructors,
                        input[1]);
                    srs.assignInstructor(staffId, courseOfferingId);
                    mainWindow.refresh();
                } catch (SRSException e) {
                    JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                        "Assign Instructor Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    private String[] createCourseOfferingComboBoxContents(
        Collection<CourseOffering> courseofferings) {
        String[] result = new String[courseofferings.size()];
        int idx = 0;
        for (CourseOffering co : courseofferings) {
            result[idx++] = co.getId() + " (" + co.getCourse().getName() + ")";
        }
        return result;
    }

    private String[] createInstructorComboBoxContents(
        Collection<Instructor> instructors) {
        String[] result = new String[instructors.size()];
        int idx = 0;
        for (Instructor i : instructors) {
            result[idx++] = ((Staff) i).getId() + " (" + ((Staff) i).getName()
                + ")";
        }
        return result;
    }

    private String getCourseOfferingIdFromCollection(
        Collection<CourseOffering> courseofferings, String selectedIndex) {
        return ((CourseOffering) courseofferings.toArray()[Integer
            .valueOf(selectedIndex)]).getId();
    }

    private String getInstructorIdFromCollection(
        Collection<Instructor> instructors, String selectedIndex) {
        return ((Staff) instructors.toArray()[Integer.valueOf(selectedIndex)])
            .getId();
    }

}
