// Author: Nik Ambukovski
// Student Number: s2008618
// Date: 16-Oct-2013

package com.lazokin.util;

public final class ConsoleOutput {

    // Prints a multiple row table without header
    public static void printTable(String[][] data) {

        int maxColWidths[];
        maxColWidths = new int[data[0].length];
        CalcMaxColWidths(data, maxColWidths);

        PrintTableData(data, maxColWidths);

    }

    // Prints a single row table with a single row header
    public static void printTable(String data[], String header[]) {

        int maxColWidths[];
        maxColWidths = new int[data.length];

        String table[][] = new String[2][data.length];
        System.arraycopy(header, 0, table[0], 0, header.length);
        System.arraycopy(data, 0, table[1], 0, data.length);
        CalcMaxColWidths(table, maxColWidths);

        PrintTableHeader(header, maxColWidths);
        PrintRowDivider(maxColWidths);
        PrintTableRow(data, maxColWidths);
        PrintRowDivider(maxColWidths);

    }

    // Prints a single row table with a multiple row header
    public static void printTable(String[] data, String[][] header) {

        int maxColWidths[];
        maxColWidths = new int[data.length];

        String table[][] = new String[1 + header.length][data.length];
        for (int i = 0; i < header.length; i++) {
            System.arraycopy(header[i], 0, table[i], 0, data.length);
        }
        System.arraycopy(data, 0, table[header.length], 0, data.length);
        CalcMaxColWidths(table, maxColWidths);

        PrintTableHeader(header, maxColWidths);
        PrintRowDivider(maxColWidths);
        PrintTableRow(data, maxColWidths);
        PrintRowDivider(maxColWidths);
    }

    // Prints a multiple row table with a single row header
    public static void printTable(String[][] data, String[] header) {

        int maxColWidths[];
        maxColWidths = new int[data[0].length];

        String table[][] = new String[1 + data.length][data[0].length];
        System.arraycopy(header, 0, table[0], 0, header.length);
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, table[i + 1], 0, data[0].length);
        }
        CalcMaxColWidths(table, maxColWidths);

        PrintTableHeader(header, maxColWidths);
        PrintTableData(data, maxColWidths);
    }

    // Prints a multiple row table with a multiple row header
    public static void printTable(String[][] data, String[][] header) {

        int maxColWidths[];
        maxColWidths = new int[data[0].length];

        String table[][] = new String[header.length + data.length][data[0].length];
        for (int i = 0; i < header.length; i++) {
            System.arraycopy(header[i], 0, table[i], 0, header[0].length);
        }
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, table[i + header.length], 0,
                    data[0].length);
        }
        CalcMaxColWidths(table, maxColWidths);

        PrintTableHeader(header, maxColWidths);
        PrintTableData(data, maxColWidths);
    }

    // Calculates the maximum width of each table column
    private static void CalcMaxColWidths(String data[][], int maxColWidths[]) {
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[0].length; col++) {
                if (data[row][col].length() > maxColWidths[col]) {
                    maxColWidths[col] = data[row][col].length();
                }
            }
        }
    }

    // Prints a single row table header
    private static void PrintTableHeader(String header[], int maxColWidths[]) {
        PrintRowDivider(maxColWidths);
        PrintTableRow(header, maxColWidths);
    }

    // Prints a multiple row table header
    private static void PrintTableHeader(String header[][], int maxColWidths[]) {
        PrintRowDivider(maxColWidths);
        for (int i = 0; i < header.length; i++) {
            PrintTableRow(header[i], maxColWidths);
        }
    }

    // Prints a table of data
    private static void PrintTableData(String data[][], int maxColWidths[]) {
        PrintRowDivider(maxColWidths);
        for (int i = 0; i < data.length; i++) {
            PrintTableRow(data[i], maxColWidths);
            PrintRowDivider(maxColWidths);
        }
    }

    // Prints a row of data
    private static void PrintTableRow(String data[], int maxColWidths[]) {
        for (int i = 0; i < data.length; i++) {
            System.out.print("+");
            System.out.print(" ");
            String s = String
                    .format("%" + "-" + maxColWidths[i] + "s", data[i]);
            System.out.print(s);
            System.out.print(" ");
        }
        System.out.println("+");
    }

    // Prints a row divider
    private static void PrintRowDivider(int maxColWidths[]) {
        for (int i = 0; i < maxColWidths.length; i++) {
            System.out.print("+");
            System.out.print("-");
            for (int j = 0; j < maxColWidths[i]; j++) {
                System.out.print("-");
            }
            System.out.print("-");
        }
        System.out.println("+");
    }

    // Displays a menu with title
    public static void displayMenu(String title, String[] options, String[] ids) {
        System.out.println(title);
        for (int i = 0; i < title.length(); i++)
            System.out.print("-");
        System.out.println();
        for (int i = 0; i < options.length; i++) {
            System.out.print(ids[i]);
            System.out.println(options[i]);
        }
    }

    // Displays a menu without title
    public static void displayMenu(String[] options, String[] ids) {
        for (int i = 0; i < options.length; i++) {
            System.out.print(ids[i]);
            System.out.println(options[i]);
        }
    }

}
