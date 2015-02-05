package view.tabs.course;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class CoursesControlPanel extends JPanel {

    private CoursesScrollPane coursesScrollPane;

    private JToggleButton allCoursesButton;
    private JToggleButton standardCoursesButton;
    private JToggleButton advancedCoursesButton;
    private JToggleButton prerequisitesButton;
    private JButton saveTableButton;
    private ButtonGroup buttonGroup;

    public CoursesControlPanel(CoursesScrollPane studentsScrollPane) {
        super();
        this.processParameters(studentsScrollPane);
        this.setupPanel();
        this.createComponents();
        this.setupComponents();
        this.addComponents();
    }

    private void processParameters(CoursesScrollPane studentsScrollPane) {
        this.coursesScrollPane = studentsScrollPane;
    }

    private void setupPanel() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.WHITE);
    }

    private void createComponents() {
        this.allCoursesButton = new JToggleButton("All");
        this.standardCoursesButton = new JToggleButton("Standard");
        this.advancedCoursesButton = new JToggleButton("Advanced");
        this.prerequisitesButton = new JToggleButton("Prerequisites");
        this.saveTableButton = new JButton("Save Table");
        this.buttonGroup = new ButtonGroup();
    }

    private void setupComponents() {
        this.allCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coursesScrollPane.showAllCoursesTable();
            }
        });
        this.standardCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coursesScrollPane.showStandardCoursesTable();
            }
        });
        this.advancedCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coursesScrollPane.showAdvancedCoursesTable();
            }
        });
        this.prerequisitesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coursesScrollPane.showPrerequisitesTable();
            }
        });
        this.saveTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coursesScrollPane.saveCurrentTable();
            }
        });
        this.allCoursesButton.setSelected(true);
    }

    private void addComponents() {
        this.add(allCoursesButton);
        this.add(standardCoursesButton);
        this.add(advancedCoursesButton);
        this.add(prerequisitesButton);
        this.add(saveTableButton);
        this.buttonGroup.add(allCoursesButton);
        this.buttonGroup.add(standardCoursesButton);
        this.buttonGroup.add(advancedCoursesButton);
        this.buttonGroup.add(prerequisitesButton);
    }

}
