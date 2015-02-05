package controller.course;

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

public class removePrerequisiteActionListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public removePrerequisiteActionListener(MainWindow mainWindow,
            StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Collection<Course> courses = srs.getAllCourses();

        if (courses == null) {
            JOptionPane.showMessageDialog(null, "There are no courses.",
                    "Remove Prerequisite Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String[]> comboBoxContents = new ArrayList<String[]>();
            comboBoxContents.add(createComboBoxContents(courses));
            comboBoxContents.add(createComboBoxContents(courses));
            String[] input = SwingInput.showComboDialog(mainWindow,
                    "Remove Prerequisite", 300, new String[] { "Course",
                            "Prerequisite" }, comboBoxContents);
            if (input != null) {
                try {
                    String courseId = getIdFromCollection(courses, input[0]);
                    String prerequisiteCourseId = getIdFromCollection(courses,
                            input[1]);
                    srs.removePrerequisite(courseId, prerequisiteCourseId);
                    mainWindow.refresh();
                } catch (SRSException e) {
                    JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                            "Remove Prerequisite Error",
                            JOptionPane.ERROR_MESSAGE);
                }
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
