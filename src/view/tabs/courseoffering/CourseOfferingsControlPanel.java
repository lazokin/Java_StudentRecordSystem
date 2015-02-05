package view.tabs.courseoffering;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class CourseOfferingsControlPanel extends JPanel {

    private CourseOfferingsScrollPane courseOfferingsScrollPane;

    private JToggleButton allCourseOfferingsButton;
    private JToggleButton currentCourseOfferingsButton;
    private JToggleButton completedCourseOfferingsButton;
    private JButton saveTableButton;
    private ButtonGroup buttonGroup;

    public CourseOfferingsControlPanel(
        CourseOfferingsScrollPane courseOfferingsScrollPane) {
        super();
        this.processParameters(courseOfferingsScrollPane);
        this.setupPanel();
        this.createComponents();
        this.setupComponents();
        this.addComponents();
    }

    private void processParameters(
        CourseOfferingsScrollPane courseOfferingsScrollPane) {
        this.courseOfferingsScrollPane = courseOfferingsScrollPane;
    }

    private void setupPanel() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.WHITE);
    }

    private void createComponents() {
        this.allCourseOfferingsButton = new JToggleButton("All");
        this.currentCourseOfferingsButton = new JToggleButton("Current");
        this.completedCourseOfferingsButton = new JToggleButton("Completed");
        this.saveTableButton = new JButton("Save Table");
        this.buttonGroup = new ButtonGroup();
    }

    private void setupComponents() {
        this.allCourseOfferingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseOfferingsScrollPane.showAllCourseOfferingsTable();
            }
        });
        this.currentCourseOfferingsButton
            .addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    courseOfferingsScrollPane.showCurrentCourseOfferingsTable();
                }
            });
        this.completedCourseOfferingsButton
            .addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    courseOfferingsScrollPane
                        .showCompletedCourseOfferingsTable();
                }
            });
        this.saveTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseOfferingsScrollPane.saveCurrentTable();
            }
        });
        this.allCourseOfferingsButton.setSelected(true);
    }

    private void addComponents() {
        this.add(allCourseOfferingsButton);
        this.add(currentCourseOfferingsButton);
        this.add(completedCourseOfferingsButton);
        this.add(saveTableButton);
        this.buttonGroup.add(allCourseOfferingsButton);
        this.buttonGroup.add(currentCourseOfferingsButton);
        this.buttonGroup.add(completedCourseOfferingsButton);
    }

}
