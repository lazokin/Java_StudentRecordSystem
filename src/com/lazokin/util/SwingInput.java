// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 7-May-2014

package com.lazokin.util;

import java.awt.Window;
import java.util.ArrayList;

public final class SwingInput {

    // Shows a dialog with multiple text fields
    // Returns a String array of text field input
    public static String[] showTextDialog(Window window, String title,
            int width, String[] textFieldInputLabels) {
        String[] result = null;
        MyTextFieldDialog dialog = new MyTextFieldDialog(window, title, width,
                textFieldInputLabels);
        dialog.setVisible(true);
        if (dialog.closedWihtOK()) {
            result = dialog.getInput();
        }
        return result;
    }
    
    // Shows a dialog with multiple text fields and default values
    // Returns a String array of text field input
    public static String[] showTextDialog(Window window, String title,
            int width, String[] textFieldInputLabels, String[] defaultValues) {
        String[] result = null;
        MyTextFieldDialog dialog = new MyTextFieldDialog(window, title, width,
                textFieldInputLabels, defaultValues);
        dialog.setVisible(true);
        if (dialog.closedWihtOK()) {
            result = dialog.getInput();
        }
        return result;
    }

    // Shows a dialog with multiple combo boxes
    // Returns a String array of combo box selections
    public static String[] showComboDialog(Window window, String title,
            int width, String[] comboBoxInputLabels,
            ArrayList<String[]> comboBoxContents) {
        String[] result = null;
        MyComboBoxDialog dialog = new MyComboBoxDialog(window, title, width,
                comboBoxInputLabels, comboBoxContents);
        dialog.setVisible(true);
        if (dialog.closedWihtOK()) {
            result = dialog.getInput();
        }
        return result;
    }

    // Shows a dialog with multiple combo boxes and multiple text fields
    // Returns String array of combo box selections and text field inputs
    public static String[] showComboTextDialog(Window window, String title,
            int width, String[] comboBoxInputLabels,
            String[] textFieldInputLabels, ArrayList<String[]> comboBoxContents) {
        String[] result = null;
        MyComboBoxTextFieldDialog dialog = new MyComboBoxTextFieldDialog(
                window, title, width, comboBoxInputLabels,
                textFieldInputLabels, comboBoxContents);
        dialog.setVisible(true);
        if (dialog.closedWihtOK()) {
            result = dialog.getInput();
        }
        return result;
    }

}
