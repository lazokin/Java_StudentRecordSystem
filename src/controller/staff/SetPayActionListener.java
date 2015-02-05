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

public class SetPayActionListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public SetPayActionListener(MainWindow mainWindow, StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Collection<Staff> staff = srs.getAllStaff();

        ArrayList<String[]> comboBoxContents = new ArrayList<String[]>();
        comboBoxContents.add(createComboBoxContents(staff));
        String[] input = SwingInput.showComboTextDialog(mainWindow, "Set Pay",
            300, new String[] { "Staff" }, new String[] { "Pay" },
            comboBoxContents);

        if (input != null) {
            try {
                String staffId = getIdFromCollection(staff, input[0]);
                String pay = input[1];
                srs.setPay(staffId, pay);
                mainWindow.refresh();
            } catch (SRSException e) {
                JOptionPane.showMessageDialog(mainWindow, e.getMessage(),
                    "Set Pay Error", JOptionPane.ERROR_MESSAGE);
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
