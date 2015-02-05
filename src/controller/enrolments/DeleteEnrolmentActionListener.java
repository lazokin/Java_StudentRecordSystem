package controller.enrolments;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.StudentRecordSystem;
import model.enrolments.Enrolment;
import model.exceptions.SRSException;
import view.MainWindow;

import com.lazokin.util.SwingInput;

public class DeleteEnrolmentActionListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public DeleteEnrolmentActionListener(MainWindow mainWindow,
        StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Collection<Enrolment> enrolments = srs.getAllEnrolments();
        Collection<Enrolment> currentEnrolments = new ArrayList<Enrolment>();
        for (Enrolment e : enrolments) {
            if (!e.isCompleted()) {
                currentEnrolments.add(e);
            }
        }

        if (currentEnrolments.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "There are no current enrolments to delete.",
                "Delete Enrolment Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String[]> comboBoxContents = new ArrayList<String[]>();
            comboBoxContents
                .add(createCurrentEnrolmentsComboBoxContents(currentEnrolments));
            String[] input = SwingInput.showComboDialog(mainWindow,
                "Delete Enrolment", 300, new String[] { "Current Enrolment" },
                comboBoxContents);
            if (input != null) {
                try {
                    String studentId = getStudentIdFromCollection(
                        currentEnrolments, input[0]);
                    String courseOfferingId = getCourseOfferingIdFromCollection(
                        currentEnrolments, input[0]);
                    srs.deleteEnrolment(studentId, courseOfferingId);
                    mainWindow.refresh();
                } catch (SRSException e) {
                    JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                        "Delete Course Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    private String[] createCurrentEnrolmentsComboBoxContents(
        Collection<Enrolment> currentEnrolments) {
        String[] result = new String[currentEnrolments.size()];
        int idx = 0;
        for (Enrolment e : currentEnrolments) {
            result[idx++] = e.getStudent().getId() + " ("
                + e.getCourseOffering().getId() + ")";
        }
        return result;
    }

    private String getStudentIdFromCollection(
        Collection<Enrolment> currentEnrolments, String selectedIndex) {
        return ((Enrolment) currentEnrolments.toArray()[Integer
            .valueOf(selectedIndex)]).getStudent().getId();
    }

    private String getCourseOfferingIdFromCollection(
        Collection<Enrolment> currentEnrolments, String selectedIndex) {
        return ((Enrolment) currentEnrolments.toArray()[Integer
            .valueOf(selectedIndex)]).getCourseOffering().getId();
    }

}
