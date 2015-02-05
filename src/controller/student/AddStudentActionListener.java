package controller.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.StudentRecordSystem;
import model.exceptions.SRSException;
import view.MainWindow;

import com.lazokin.util.SwingInput;

public class AddStudentActionListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public AddStudentActionListener(MainWindow mainWindow,
        StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        String[] input = SwingInput
            .showTextDialog(mainWindow, "Add Student", 200, new String[] {
                "Student Id", "Student Name", "Year of Birth" });

        if (input != null) {
            try {
                String id = input[0];
                String name = input[1];
                String birthYear = input[2];
                srs.addStudent(id, name, birthYear);
                mainWindow.refresh();
            } catch (SRSException e) {
                JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                    "Add Student Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
