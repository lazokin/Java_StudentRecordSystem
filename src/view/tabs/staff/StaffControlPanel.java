package view.tabs.staff;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class StaffControlPanel extends JPanel {

    private StaffScrollPane staffScrollPane;

    private JToggleButton allStaffButton;
    private JToggleButton permanentStaffButton;
    private JToggleButton casualStaffButton;
    private JToggleButton studentInstructorsButton;
    private JToggleButton allInstructorsButton;
    private JToggleButton nonStudentInstructorsButton;
    private JButton saveTableButton;
    private ButtonGroup buttonGroup;

    public StaffControlPanel(StaffScrollPane staffScrollPane) {
        super();
        this.processParameters(staffScrollPane);
        this.setupPanel();
        this.createComponents();
        this.setupComponents();
        this.addComponents();
    }

    private void processParameters(StaffScrollPane staffScrollPane) {
        this.staffScrollPane = staffScrollPane;
    }

    private void setupPanel() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.WHITE);
    }

    private void createComponents() {
        this.allStaffButton = new JToggleButton("All Staff");
        this.permanentStaffButton = new JToggleButton("Permanent Staff");
        this.casualStaffButton = new JToggleButton("Casual Staff");
        this.studentInstructorsButton = new JToggleButton("Student Instructors");
        this.allInstructorsButton = new JToggleButton("Instructor Assignments");
        this.nonStudentInstructorsButton = new JToggleButton(
            "Instructor Permissions");
        this.saveTableButton = new JButton("Save Table");
        this.buttonGroup = new ButtonGroup();
    }

    private void setupComponents() {
        this.allStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staffScrollPane.showAllStaffTable();
            }
        });
        this.permanentStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staffScrollPane.showPermanentStaffTable();
            }
        });
        this.casualStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staffScrollPane.showCasualStaffTable();
            }
        });
        this.studentInstructorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staffScrollPane.showStudentInstructorsTable();
            }
        });
        this.allInstructorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staffScrollPane.showInstructorAssignmentsTable();
            }
        });
        this.nonStudentInstructorsButton
            .addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    staffScrollPane.showInstructorPermissionsTable();
                }
            });
        this.saveTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staffScrollPane.saveCurrentTable();
            }
        });
        this.allStaffButton.setSelected(true);
    }

    private void addComponents() {
        this.add(allStaffButton);
        this.add(permanentStaffButton);
        this.add(casualStaffButton);
        this.add(studentInstructorsButton);
        this.add(allInstructorsButton);
        this.add(nonStudentInstructorsButton);
        this.add(saveTableButton);
        this.buttonGroup.add(allStaffButton);
        this.buttonGroup.add(permanentStaffButton);
        this.buttonGroup.add(casualStaffButton);
        this.buttonGroup.add(studentInstructorsButton);
        this.buttonGroup.add(allInstructorsButton);
        this.buttonGroup.add(nonStudentInstructorsButton);
    }

}
