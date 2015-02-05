package controller.student;

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

public class DeleteStudentActionListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public DeleteStudentActionListener(MainWindow mainWindow,
            StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Collection<Student> students = srs.getAllStudents();

        if (students == null) {
            JOptionPane.showMessageDialog(null, "There are no students.",
                    "Delete Student Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String[]> comboBoxContents = new ArrayList<String[]>();
            comboBoxContents.add(createComboBoxContents(students));
            String[] input = SwingInput.showComboDialog(mainWindow,
                    "Delete Student", 300, new String[] { "Student" },
                    comboBoxContents);
            if (input != null) {
                try {
                    String id = getIdFromCollection(students, input[0]);
                    srs.deleteStudent(id);
                    mainWindow.refresh();
                } catch (SRSException e) {
                    JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                            "Delete Student Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    private String[] createComboBoxContents(Collection<Student> students) {
        String[] result = new String[students.size()];
        int idx = 0;
        for (Student s : students) {
            result[idx++] = s.getId() + " (" + s.getName() + ")";
        }
        return result;
    }

    private String getIdFromCollection(Collection<Student> students,
            String selectedIndex) {
        return ((Student) students.toArray()[Integer.valueOf(selectedIndex)])
                .getId();
    }

}
