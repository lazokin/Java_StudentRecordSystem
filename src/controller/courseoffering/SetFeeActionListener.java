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

public class SetFeeActionListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public SetFeeActionListener(MainWindow mainWindow, StudentRecordSystem srs) {
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
                "Set Course Offering Fee Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String[]> comboBoxContents = new ArrayList<String[]>();
            comboBoxContents.add(createComboBoxContents(courseOfferings));
            String[] input = SwingInput.showComboTextDialog(mainWindow,
                "Set Course Offering Fee", 300,
                new String[] { "Course Offering" }, new String[] { "Fee" },
                comboBoxContents);

            if (input != null) {
                try {
                    String id = getIdFromCollection(courseOfferings, input[0]);
                    String fee = input[1];
                    srs.setFeeForCourseOffering(id, fee);
                    mainWindow.refresh();
                } catch (SRSException e) {
                    JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                        "Set Course Offering Fee Error",
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
