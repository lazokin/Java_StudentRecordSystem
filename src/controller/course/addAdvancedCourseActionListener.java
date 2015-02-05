package controller.course;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.StudentRecordSystem;
import model.exceptions.SRSException;
import view.MainWindow;

import com.lazokin.util.SwingInput;

public class addAdvancedCourseActionListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public addAdvancedCourseActionListener(MainWindow mainWindow,
            StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        String[] input = SwingInput.showTextDialog(mainWindow,
                "Add Standard Course", 200, new String[] { "Course Id",
                        "Course Name", "Course Fee" });

        if (input != null) {

            try {
                String id = input[0];
                String name = input[1];
                String fee = input[2];
                srs.addAdvancedCourse(id, name, fee);
                mainWindow.refresh();
            } catch (SRSException e) {
                JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                        "Add Advanced Course Error", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

}
