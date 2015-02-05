package view.tabs.student;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class StudentsControlPanel extends JPanel {

    private StudentsScrollPane studentsScrollPane;

    private JToggleButton personalButton;
    private JToggleButton academicButton;
    private JToggleButton finanicalButton;
    private JToggleButton exemptionsButton;
    private JToggleButton currentEnrolmentsButton;
    private JToggleButton completedEnrolmentsButton;
    private JButton saveTableButton;
    private ButtonGroup buttonGroup;

    public StudentsControlPanel(StudentsScrollPane studentsScrollPane) {
        super();
        this.processParameters(studentsScrollPane);
        this.setupPanel();
        this.createComponents();
        this.setupComponents();
        this.addComponents();
    }

    private void processParameters(StudentsScrollPane studentsScrollPane) {
        this.studentsScrollPane = studentsScrollPane;
    }

    private void setupPanel() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.WHITE);
    }

    private void createComponents() {
        this.personalButton = new JToggleButton("Personal");
        this.academicButton = new JToggleButton("Academic");
        this.finanicalButton = new JToggleButton("Financial");
        this.exemptionsButton = new JToggleButton("Exemptions");
        this.currentEnrolmentsButton = new JToggleButton("Current Enrolments");
        this.completedEnrolmentsButton = new JToggleButton(
            "Completed Enrolments");
        this.saveTableButton = new JButton("Save Table");
        this.buttonGroup = new ButtonGroup();
    }

    private void setupComponents() {
        this.personalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentsScrollPane.showPersonalTable();
            }
        });
        this.academicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentsScrollPane.showAcademicTable();
            }
        });
        this.finanicalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentsScrollPane.showFinanicalTable();
            }
        });
        this.exemptionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentsScrollPane.showExemptionsTable();
            }
        });
        this.currentEnrolmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentsScrollPane.showCurrentEnrolmentsTable();
            }
        });
        this.completedEnrolmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentsScrollPane.showCompletedEnrolmentsTable();
            }
        });
        this.saveTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentsScrollPane.saveCurrentTable();
            }
        });
        this.personalButton.setSelected(true);
    }

    private void addComponents() {
        this.add(personalButton);
        this.add(academicButton);
        this.add(finanicalButton);
        this.add(exemptionsButton);
        this.add(currentEnrolmentsButton);
        this.add(completedEnrolmentsButton);
        this.add(saveTableButton);
        this.buttonGroup.add(personalButton);
        this.buttonGroup.add(academicButton);
        this.buttonGroup.add(finanicalButton);
        this.buttonGroup.add(exemptionsButton);
        this.buttonGroup.add(currentEnrolmentsButton);
        this.buttonGroup.add(completedEnrolmentsButton);
    }

}
