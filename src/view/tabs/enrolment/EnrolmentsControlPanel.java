package view.tabs.enrolment;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class EnrolmentsControlPanel extends JPanel {

    private EnrolmentsScrollPane enrolmentsScrollPane;

    private JToggleButton allEnrolmentsButton;
    private JToggleButton currentEnrolmentsButton;
    private JToggleButton completedEnrolmentsButton;
    private JButton saveTableButton;
    private ButtonGroup buttonGroup;

    public EnrolmentsControlPanel(EnrolmentsScrollPane enrolmentsScrollPane) {
        super();
        this.processParameters(enrolmentsScrollPane);
        this.setupPanel();
        this.createComponents();
        this.setupComponents();
        this.addComponents();
    }

    private void processParameters(EnrolmentsScrollPane enrolmentsScrollPane) {
        this.enrolmentsScrollPane = enrolmentsScrollPane;
    }

    private void setupPanel() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.WHITE);
    }

    private void createComponents() {
        this.allEnrolmentsButton = new JToggleButton("All");
        this.currentEnrolmentsButton = new JToggleButton("Current");
        this.completedEnrolmentsButton = new JToggleButton("Completed");
        this.saveTableButton = new JButton("Save Table");
        this.buttonGroup = new ButtonGroup();
    }

    private void setupComponents() {
        this.allEnrolmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enrolmentsScrollPane.showAllEnrolmentsTable();
            }
        });
        this.currentEnrolmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enrolmentsScrollPane.showCurrentEnrolmentsTable();
            }
        });
        this.completedEnrolmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enrolmentsScrollPane.showCompletedEnrolmentsTable();
            }
        });
        this.saveTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enrolmentsScrollPane.saveCurrentTable();
            }
        });
        this.allEnrolmentsButton.setSelected(true);
    }

    private void addComponents() {
        this.add(allEnrolmentsButton);
        this.add(currentEnrolmentsButton);
        this.add(completedEnrolmentsButton);
        this.add(saveTableButton);
        this.buttonGroup.add(allEnrolmentsButton);
        this.buttonGroup.add(currentEnrolmentsButton);
        this.buttonGroup.add(completedEnrolmentsButton);
    }

}
