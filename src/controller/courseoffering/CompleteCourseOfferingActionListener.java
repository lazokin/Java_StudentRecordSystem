package controller.courseoffering;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.StudentRecordSystem;
import model.courses.CourseOffering;
import model.exceptions.SRSException;
import view.MainWindow;

import com.lazokin.util.SwingInput;

public class CompleteCourseOfferingActionListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public CompleteCourseOfferingActionListener(MainWindow mainWindow,
        StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Collection<CourseOffering> courseOfferings = srs
            .getAllCourseOfferings();

        if (courseOfferings == null) {
            JOptionPane.showMessageDialog(null,
                "There are no course offerings.",
                "Complete Course Offering Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String[]> comboBoxContents = new ArrayList<String[]>();
            comboBoxContents.add(createComboBoxContents(courseOfferings));
            String[] input = SwingInput.showComboDialog(mainWindow,
                "Complete Course Offering", 300,
                new String[] { "Course Offering" }, comboBoxContents);
            if (input != null) {
                try {
                    String id = getIdFromCollection(courseOfferings, input[0]);
                    srs.completeCourseOffering(id);
                    mainWindow.refresh();
                } catch (SRSException e) {
                    JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                        "Complete Course Offering Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    private String[] createComboBoxContents(
        Collection<CourseOffering> courseOfferings) {
        String[] result = new String[courseOfferings.size()];
        int idx = 0;
        for (CourseOffering co : courseOfferings) {
            result[idx++] = co.getId() + " (" + co.getCourse().getName() + ")";
        }
        return result;
    }

    private String getIdFromCollection(
        Collection<CourseOffering> courseOfferings, String selectedIndex) {
        return ((CourseOffering) courseOfferings.toArray()[Integer
            .valueOf(selectedIndex)]).getId();
    }

}
