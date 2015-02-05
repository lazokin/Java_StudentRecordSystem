package controller.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.StudentRecordSystem;
import model.exceptions.SRSException;
import model.staff.interfaces.Staff;
import view.MainWindow;

import com.lazokin.util.SwingInput;

public class DeleteStaffActionListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public DeleteStaffActionListener(MainWindow mainWindow,
        StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Collection<Staff> staff = srs.getAllStaff();

        if (staff == null) {
            JOptionPane.showMessageDialog(null, "There are no staff members.",
                "Delete Staff Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String[]> comboBoxContents = new ArrayList<String[]>();
            comboBoxContents.add(createComboBoxContents(staff));
            String[] input = SwingInput
                .showComboDialog(mainWindow, "Delete Staff", 300,
                    new String[] { "Staff" }, comboBoxContents);
            if (input != null) {
                try {
                    String staffId = getIdFromCollection(staff, input[0]);
                    srs.deleteStaff(staffId);
                    mainWindow.refresh();
                } catch (SRSException e) {
                    JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                        "Delete Staff Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    private String[] createComboBoxContents(Collection<Staff> staff) {
        String[] result = new String[staff.size()];
        int idx = 0;
        for (Staff s : staff) {
            result[idx++] = s.getId() + " (" + s.getName() + ")";
        }
        return result;
    }

    private String getIdFromCollection(Collection<Staff> staff,
        String selectedIndex) {
        return ((Staff) staff.toArray()[Integer.valueOf(selectedIndex)])
            .getId();
    }

}
