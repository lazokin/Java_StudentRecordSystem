package controller.courseoffering;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.StudentRecordSystem;
import model.courses.Course;
import model.exceptions.SRSException;
import view.MainWindow;

import com.lazokin.util.SwingInput;

public class AddCourseOfferingActionListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public AddCourseOfferingActionListener(MainWindow mainWindow,
        StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Collection<Course> courses = srs.getAllCourses();

        ArrayList<String[]> comboBoxContents = new ArrayList<String[]>();
        comboBoxContents.add(createComboBoxContents(courses));
        String[] input = SwingInput.showComboTextDialog(mainWindow,
            "Add Course Offering", 300, new String[] { "Course" },
            new String[] { "Run Number", "Year" }, comboBoxContents);

        if (input != null) {
            try {
                String courseId = getIdFromCollection(courses, input[0]);
                String runNumber = input[1];
                String courseOfferingId = courseId + "-RUN-" + runNumber;
                String year = input[2];
                srs.addCourseOffering(courseId, courseOfferingId, year);
                mainWindow.refresh();
            } catch (SRSException e) {
                JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                    "Add Course Offering Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private String[] createComboBoxContents(Collection<Course> courses) {
        String[] result = new String[courses.size()];
        int idx = 0;
        for (Course c : courses) {
            result[idx++] = c.getId() + " (" + c.getName() + ")";
        }
        return result;
    }

    private String getIdFromCollection(Collection<Course> courses,
        String selectedIndex) {
        return ((Course) courses.toArray()[Integer.valueOf(selectedIndex)])
            .getId();
    }

}
