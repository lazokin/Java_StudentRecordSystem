// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 7-May-2014

package com.lazokin.util;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyComboBoxDialog extends JDialog implements ActionListener {

    private String[] input;
    private JPanel[] comboBoxLabelPanels;
    private JPanel[] comboBoxPanels;
    private JLabel[] comboBoxLabels;
    private JComboBox<String>[] comboBoxes;
    private JPanel inputPanel;
    private JPanel buttonPanel;
    private JButton cancelButton;
    private JButton okButton;

    private int width;
    private String[] comboBoxLabelStrings;
    private ArrayList<String[]> comboBoxContents;
    private int numberOfComboBoxes;

    private boolean closedWihtOK;

    public MyComboBoxDialog(Window window, String title, int width,
            String[] comboBoxLabels, ArrayList<String[]> comboBoxContents) {
        super(window, title, Dialog.ModalityType.APPLICATION_MODAL);
        this.processParameters(title, width, comboBoxLabels,
                comboBoxContents);
        this.setupDialog(window);
        this.createComponents();
        this.setupComponents();
        this.addComponents();
    }

    private void processParameters(String title, int width,
            String[] comboBoxLabels, ArrayList<String[]> comboBoxContents) {
        this.width = width;
        this.comboBoxLabelStrings = comboBoxLabels;
        this.comboBoxContents = comboBoxContents;
        this.numberOfComboBoxes = comboBoxLabels.length;
    }

    private void setupDialog(Window window) {
        this.setSize(width, 100 + 75 * numberOfComboBoxes);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(window);
    }

    private void createComponents() {
        this.input = new String[numberOfComboBoxes];
        this.comboBoxLabelPanels = new JPanel[numberOfComboBoxes];
        this.comboBoxPanels = new JPanel[numberOfComboBoxes];
        this.comboBoxLabels = new JLabel[numberOfComboBoxes];
        this.comboBoxes = new JComboBox[numberOfComboBoxes];
        for (int i = 0; i < numberOfComboBoxes; i++) {
            this.comboBoxLabelPanels[i] = new JPanel();
            this.comboBoxPanels[i] = new JPanel();
            this.comboBoxLabels[i] = new JLabel();
            this.comboBoxes[i] = new JComboBox<String>();
        }
        this.inputPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.cancelButton = new JButton();
        this.okButton = new JButton();
    }

    private void setupComponents() {
        for (int i = 0; i < numberOfComboBoxes; i++) {
            comboBoxLabelPanels[i].setLayout(new GridBagLayout());
            comboBoxPanels[i].setLayout(new GridBagLayout());
            comboBoxLabels[i].setText(comboBoxLabelStrings[i]);
            comboBoxes[i].setPreferredSize(new Dimension(width - 50, 30));
            comboBoxes[i].setModel(new DefaultComboBoxModel<String>(
                    comboBoxContents.get(i)));
        }
        this.inputPanel.setLayout(new GridLayout(0, 1));
        this.buttonPanel.setLayout(new GridBagLayout());
        this.buttonPanel.setBorder(BorderFactory
                .createEmptyBorder(10, 0, 10, 0));
        this.cancelButton.setText("Cancel");
        this.cancelButton.addActionListener(this);
        this.okButton.setText("OK");
        this.okButton.addActionListener(this);
    }

    private void addComponents() {
        for (int i = 0; i < numberOfComboBoxes; i++) {
            this.comboBoxLabelPanels[i].add(comboBoxLabels[i]);
            this.comboBoxPanels[i].add(comboBoxes[i]);
            this.inputPanel.add(comboBoxLabelPanels[i]);
            this.inputPanel.add(comboBoxPanels[i]);
        }
        this.buttonPanel.add(cancelButton);
        this.buttonPanel.add(okButton);
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if (actionCommand.equals("Cancel")) {
            this.closeDialogWithCancel();
        }
        if (actionCommand.equals("OK")) {
            for (int i = 0; i < numberOfComboBoxes; i++) {
                input[i] = Integer.toString(comboBoxes[i].getSelectedIndex());
            }
            this.closeDialogWithOK();
        }
    }

    public String[] getInput() {
        return input;
    }

    public boolean closedWihtOK() {
        return closedWihtOK;
    }

    private void closeDialogWithCancel() {
        this.setVisible(false);
    }

    private void closeDialogWithOK() {
        this.setVisible(false);
        this.closedWihtOK = true;
    }

}
