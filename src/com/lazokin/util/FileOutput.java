// Author: Nik Ambukovski
// Student Number: s2008618
// Date: 16-Oct-2013

package com.lazokin.util;

import java.io.PrintWriter;

public final class FileOutput {

    // Prints a multiple row table without header
    public static void printTable(String[][] data, PrintWriter pw) {

        int maxColWidths[];
        maxColWidths = new int[data[0].length];
        CalcMaxColWidths(data, maxColWidths);

        PrintTableData(data, maxColWidths, pw);

    }

    // Prints a single row table with a single row header
    public static void printTable(String data[], String header[], PrintWriter pw) {

        int maxColWidths[];
        maxColWidths = new int[data.length];

        String table[][] = new String[2][data.length];
        System.arraycopy(header, 0, table[0], 0, header.length);
        System.arraycopy(data, 0, table[1], 0, data.length);
        CalcMaxColWidths(table, maxColWidths);

        PrintTableHeader(header, maxColWidths, pw);
        PrintRowDivider(maxColWidths, pw);
        PrintTableRow(data, maxColWidths, pw);
        PrintRowDivider(maxColWidths, pw);

    }

    // Prints a single row table with a multiple row header
    public static void printTable(String[] data, String[][] header,
        PrintWriter pw) {

        int maxColWidths[];
        maxColWidths = new int[data.length];

        String table[][] = new String[1 + header.length][data.length];
        for (int i = 0; i < header.length; i++) {
            System.arraycopy(header[i], 0, table[i], 0, data.length);
        }
        System.arraycopy(data, 0, table[header.length], 0, data.length);
        CalcMaxColWidths(table, maxColWidths);

        PrintTableHeader(header, maxColWidths, pw);
        PrintRowDivider(maxColWidths, pw);
        PrintTableRow(data, maxColWidths, pw);
        PrintRowDivider(maxColWidths, pw);
    }

    // Prints a multiple row table with a single row header
    public static void printTable(String[][] data, String[] header,
        PrintWriter pw) {

        int maxColWidths[];
        maxColWidths = new int[data[0].length];

        String table[][] = new String[1 + data.length][data[0].length];
        System.arraycopy(header, 0, table[0], 0, header.length);
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, table[i + 1], 0, data[0].length);
        }
        CalcMaxColWidths(table, maxColWidths);

        PrintTableHeader(header, maxColWidths, pw);
        PrintTableData(data, maxColWidths, pw);
    }

    // Prints a multiple row table with a multiple row header
    public static void printTable(String[][] data, String[][] header,
        PrintWriter pw) {

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

        PrintTableHeader(header, maxColWidths, pw);
        PrintTableData(data, maxColWidths, pw);
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
    private static void PrintTableHeader(String header[], int maxColWidths[],
        PrintWriter pw) {
        PrintRowDivider(maxColWidths, pw);
        PrintTableRow(header, maxColWidths, pw);
    }

    // Prints a multiple row table header
    private static void PrintTableHeader(String header[][], int maxColWidths[],
        PrintWriter pw) {
        PrintRowDivider(maxColWidths, pw);
        for (int i = 0; i < header.length; i++) {
            PrintTableRow(header[i], maxColWidths, pw);
        }
    }

    // Prints a table of data
    private static void PrintTableData(String data[][], int maxColWidths[],
        PrintWriter pw) {
        PrintRowDivider(maxColWidths, pw);
        for (int i = 0; i < data.length; i++) {
            PrintTableRow(data[i], maxColWidths, pw);
            PrintRowDivider(maxColWidths, pw);
        }
    }

    // Prints a row of data
    private static void PrintTableRow(String data[], int maxColWidths[],
        PrintWriter pw) {
        for (int i = 0; i < data.length; i++) {
            pw.print("+");
            pw.print(" ");
            String s = String
                .format("%" + "-" + maxColWidths[i] + "s", data[i]);
            pw.print(s);
            pw.print(" ");
        }
        pw.println("+");
    }

    // Prints a row divider
    private static void PrintRowDivider(int maxColWidths[], PrintWriter pw) {
        for (int i = 0; i < maxColWidths.length; i++) {
            pw.print("+");
            pw.print("-");
            for (int j = 0; j < maxColWidths[i]; j++) {
                pw.print("-");
            }
            pw.print("-");
        }
        pw.println("+");
    }

}
