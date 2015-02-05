package controller.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.StudentRecordSystem;
import model.exceptions.SRSException;
import model.staff.EmploymentType;
import model.staff.PositionType;
import view.MainWindow;

import com.lazokin.util.SwingInput;

public class AddStaffActionListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public AddStaffActionListener(MainWindow mainWindow, StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        ArrayList<String[]> comboBoxContents = new ArrayList<String[]>();
        comboBoxContents.add(createEmploymentComboBoxContents());
        comboBoxContents.add(createPositionComboBoxContents());
        String[] input = SwingInput.showComboTextDialog(mainWindow,
            "Add Staff", 300, new String[] { "Employment", "Position" },
            new String[] { "Staff Id", "Name", "Pay" }, comboBoxContents);

        if (input != null) {
            try {
                EmploymentType employmentType = getEmploymentType(input[0]);
                PositionType positionType = getPositionType(input[1]);
                String staffId = input[2];
                String name = input[3];
                String pay = input[4];
                srs.addStaff(staffId, name, employmentType, positionType, pay);
                mainWindow.refresh();
            } catch (SRSException e) {
                JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                    "Add Course Offering Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private String[] createEmploymentComboBoxContents() {
        String[] result = new String[EmploymentType.values().length];
        int idx = 0;
        for (EmploymentType et : EmploymentType.values()) {
            result[idx++] = et.toString();
        }
        return result;
    }

    private String[] createPositionComboBoxContents() {
        String[] result = new String[PositionType.values().length];
        int idx = 0;
        for (PositionType pt : PositionType.values()) {
            result[idx++] = pt.toString();
        }
        return result;
    }

    private EmploymentType getEmploymentType(String selectedIndex) {
        return EmploymentType.values()[Integer.parseInt(selectedIndex)];
    }

    private PositionType getPositionType(String selectedIndex) {
        return PositionType.values()[Integer.parseInt(selectedIndex)];
    }

}
