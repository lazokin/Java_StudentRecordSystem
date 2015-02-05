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

public class SetTextbookActionListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public SetTextbookActionListener(MainWindow mainWindow,
        StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Collection<CourseOffering> courseOfferings = srs
            .getAllCourseOfferings();

        if (courseOfferings == null) {
            JOptionPane
                .showMessageDialog(null, "There are no course offerings.",
                    "Set Course Offering Textbook Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String[]> comboBoxContents = new ArrayList<String[]>();
            comboBoxContents.add(createComboBoxContents(courseOfferings));
            String[] input = SwingInput.showComboTextDialog(mainWindow,
                "Set Course Offering Texbook", 300,
                new String[] { "Course Offering" },
                new String[] { "Textbook" }, comboBoxContents);

            if (input != null) {
                try {
                    String id = getIdFromCollection(courseOfferings, input[0]);
                    String textbook = input[1];
                    srs.setTextbookForCourseOffering(id, textbook);
                    mainWindow.refresh();
                } catch (SRSException e) {
                    JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                        "Set Course Offering Texbook Error",
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
