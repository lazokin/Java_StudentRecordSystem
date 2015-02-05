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

public class DeleteCourseActionListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public DeleteCourseActionListener(MainWindow mainWindow,
            StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Collection<Course> courses = srs.getAllCourses();

        if (courses == null) {
            JOptionPane.showMessageDialog(null,
                    "There are no courses to delete.", "Delete Course Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String[]> comboBoxContents = new ArrayList<String[]>();
            comboBoxContents.add(createComboBoxContents(courses));
            String[] input = SwingInput.showComboDialog(mainWindow,
                    "Delete Course", 300, new String[] { "Course" },
                    comboBoxContents);
            if (input != null) {
                try {
                    String id = getIdFromCollection(courses, input[0]);
                    srs.deleteCourse(id);
                    mainWindow.refresh();
                } catch (SRSException e) {
                    JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                            "Delete Course Error", JOptionPane.ERROR_MESSAGE);
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
