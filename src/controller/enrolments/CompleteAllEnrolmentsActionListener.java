package controller.enrolments;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.swing.JOptionPane;

import model.StudentRecordSystem;
import model.enrolments.Enrolment;
import model.exceptions.SRSException;
import view.MainWindow;

public class CompleteAllEnrolmentsActionListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public CompleteAllEnrolmentsActionListener(MainWindow mainWindow,
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
                "There are no current enrolments to complete.",
                "Complete Enrolment Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String message = "Complete all enrolments with random grades?";
            String title = "Complete All Enrolments";
            int result = JOptionPane.showConfirmDialog(mainWindow, message,
                title, JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    for (Enrolment e : currentEnrolments) {
                        String studentId = e.getStudent().getId();
                        String courseOfferingId = e.getCourseOffering().getId();
                        Random r = new Random();
                        int grade = r.nextInt(100);
                        srs.completeEnrolment(studentId, courseOfferingId,
                            String.valueOf(grade));
                        mainWindow.refresh();
                    }
                } catch (SRSException e) {
                    JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                        "Complete Enrolment Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

}
