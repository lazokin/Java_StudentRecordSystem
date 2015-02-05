package controller.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.StudentRecordSystem;
import model.exceptions.SRSException;
import model.students.Student;
import view.MainWindow;

import com.lazokin.util.SwingInput;

public class AddStudentInstructorActionListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public AddStudentInstructorActionListener(MainWindow mainWindow,
        StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Collection<Student> students = srs.getAllStudents();

        if (students == null) {
            JOptionPane.showMessageDialog(null, "There are no students.",
                "Add Student Instructor Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String[]> comboBoxContents = new ArrayList<String[]>();
            comboBoxContents.add(createStudentComboBoxContents(students));
            String[] input = SwingInput.showComboTextDialog(mainWindow,
                "Add Student Instructor", 300, new String[] { "Student" },
                new String[] { "Pay" }, comboBoxContents);

            if (input != null) {
                try {
                    String studentId = getStudentIdFromCollection(students,
                        input[0]);
                    String staffId = studentId.replace("S", "E");
                    String pay = input[1];
                    srs.addStudentInstructor(studentId, staffId, pay);
                    mainWindow.refresh();
                } catch (SRSException e) {
                    JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                        "Add Student Instructor Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    private String[] createStudentComboBoxContents(Collection<Student> students) {
        String[] result = new String[students.size()];
        int idx = 0;
        for (Student s : students) {
            result[idx++] = s.getId() + " (" + s.getName() + ")";
        }
        return result;
    }

    private String getStudentIdFromCollection(Collection<Student> students,
        String selectedIndex) {
        return ((Student) students.toArray()[Integer.valueOf(selectedIndex)])
            .getId();
    }

}
