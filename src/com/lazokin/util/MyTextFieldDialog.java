// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 7-May-2014

package com.lazokin.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class MyTextFieldDialog extends JDialog implements ActionListener,
        DocumentListener {

    private String[] input;
    private JPanel[] textFieldLabelPanels;
    private JPanel[] textFieldPanels;
    private JLabel[] textFieldLabels;
    private JTextField[] textFields;
    private JPanel inputPanel;
    private JPanel buttonPanel;
    private JButton cancelButton;
    private JButton okButton;

    private int width;
    private String[] textFieldLabelStrings;
    private int numberOfTextFields;

    private boolean closedWihtOK;

    public MyTextFieldDialog(Window window, String title, int width,
            String[] testFieldLabels) {
        super(window, title, Dialog.ModalityType.APPLICATION_MODAL);
        this.processParameters(title, width, testFieldLabels);
        this.setupDialog(window);
        this.createComponents();
        this.setupComponents();
        this.addComponents();
    }
    
    public MyTextFieldDialog(Window window, String title, int width,
        String[] testFieldLabels, String[] defaultValues) {
    super(window, title, Dialog.ModalityType.APPLICATION_MODAL);
    this.processParameters(title, width, testFieldLabels);
    this.setupDialog(window);
    this.createComponents();
    this.setupComponents();
    this.setupComponents(defaultValues);
    this.addComponents();
    }

    private void processParameters(String title, int width,
        String[] textFieldLabels) {
        this.width = width;
        this.textFieldLabelStrings = textFieldLabels;
        this.numberOfTextFields = textFieldLabels.length;
    }

    private void setupDialog(Window window) {
        this.setSize(width, 100 + 75 * numberOfTextFields);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(window);
    }

    private void createComponents() {
        this.input = new String[numberOfTextFields];
        this.textFieldLabelPanels = new JPanel[numberOfTextFields];
        this.textFieldPanels = new JPanel[numberOfTextFields];
        this.textFieldLabels = new JLabel[numberOfTextFields];
        this.textFields = new JTextField[numberOfTextFields];
        for (int i = 0; i < numberOfTextFields; i++) {
            this.textFieldLabelPanels[i] = new JPanel();
            this.textFieldPanels[i] = new JPanel();
            this.textFieldLabels[i] = new JLabel();
            this.textFields[i] = new JTextField();
        }
        this.inputPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.cancelButton = new JButton();
        this.okButton = new JButton();
    }

    private void setupComponents() {
        for (int i = 0; i < numberOfTextFields; i++) {
            textFieldLabelPanels[i].setLayout(new GridBagLayout());
            textFieldPanels[i].setLayout(new GridBagLayout());
            textFieldLabels[i].setText(textFieldLabelStrings[i]);
            textFields[i].setPreferredSize(new Dimension(width - 50, 30));
            textFields[i].setBackground(Color.WHITE);
            textFields[i].getDocument().addDocumentListener(this);
        }
        this.inputPanel.setLayout(new GridLayout(0, 1));
        this.buttonPanel.setLayout(new GridBagLayout());
        this.buttonPanel.setBorder(BorderFactory
                .createEmptyBorder(10, 0, 10, 0));
        this.cancelButton.setText("Cancel");
        this.cancelButton.addActionListener(this);
        this.okButton.setText("OK");
        this.okButton.addActionListener(this);
        this.okButton.setEnabled(false);
    }
    
    private void setupComponents(String[] defaultValues) {
        for (int i = 0; i < numberOfTextFields; i++) {
            textFields[i].setText(defaultValues[i]);
        }
    }

    private void addComponents() {
        for (int i = 0; i < numberOfTextFields; i++) {
            this.textFieldLabelPanels[i].add(textFieldLabels[i]);
            this.textFieldPanels[i].add(textFields[i]);
            this.inputPanel.add(textFieldLabelPanels[i]);
            this.inputPanel.add(textFieldPanels[i]);
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
            boolean emptyFields = false;
            for (JTextField tf : textFields) {
                if (tf.getText().isEmpty()) {
                    emptyFields = true;
                }
            }
            if (emptyFields) {
                JOptionPane.showMessageDialog(null,
                        "Please enter a value for every field.",
                        "Empty Fields", JOptionPane.ERROR_MESSAGE);
            } else {
                for (int i = 0; i < numberOfTextFields; i++) {
                    input[i] = textFields[i].getText();
                }
                this.closeDialogWithOK();
            }
        }
    }

    @Override
    public void changedUpdate(DocumentEvent event) {
        this.checkFieldsToEnableOK();
    }

    @Override
    public void insertUpdate(DocumentEvent event) {
        this.checkFieldsToEnableOK();
    }

    @Override
    public void removeUpdate(DocumentEvent event) {
        this.checkFieldsToEnableOK();
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

    private void checkFieldsToEnableOK() {
        boolean fieldEmpty = false;
        for (JTextField tf : textFields) {
            if (tf.getText().isEmpty()) {
                fieldEmpty = true;
            }
        }
        if (fieldEmpty) {
            this.okButton.setEnabled(false);
        } else {
            this.okButton.setEnabled(true);
        }
    }

}
