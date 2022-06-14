package com.epam.engx.cleancode.finaltask.task1;


import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.Command;
import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.DataSet;
import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.View;
import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.DatabaseManager;

import java.util.List;


public class PrintTableDataset implements Command {

    private View view;
    private DatabaseManager manager;
    private String tableName;
    private int maxLength;
    private StringBuilder result=new StringBuilder();
    private int maxColumnSize;
    private static final String TOP_LEFT = "╗";
    private static final String TOP_RIGHT = "╔";
    private static final String BOTTOM_LEFT = "╝";
    private static final String BOTTOM_RIGHT = "╚";
    private static final String EQUAL = "═";
    private static final String PARALLEL ="║";
    private static final String LEFT_PIPE = "╠";
    private static final String RIGHT_PIPE ="╣";
    private static final String BOTH_PIPE ="╬";
    private static final String UP_PIPE="╩";
    private static final String BOTTOM_PIPE="╦";

    public PrintTableDataset(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    public boolean canProcess(String command) {
        return command.startsWith("print ");
    }

    public void process(String input) {
        String[] command = input.split(" ");
        invalidParamaters(command);
        tableName = command[1];
        List<DataSet> data = manager.getTableData(command[1]);
        view.write(getTableString(data));
    }

    private void invalidParamaters(String[] command) {
        if (command.length != 2) {
            throw new IllegalArgumentException(
                    "incorrect number of parameters. Expected 1, but is " + (command.length - 1));
        }
    }

    private String getTableString(List<DataSet> data) {
        return getMaxColumnSize(data) == 0 ? getEmptyTable(tableName) : getTableData(data);
    }

    private String  getTableData(List<DataSet> data) {
        return getHeaderOfTheTable(data) + getStringTableData(data);

    }

    private String getEmptyTable(String tableName) {
        String textEmptyTable = PARALLEL+" "+"Table '" + tableName + "' is empty or does not exist "+PARALLEL;
        System.out.println(result);
        result = new StringBuilder(TOP_RIGHT);

        formatResultEmptyTable(textEmptyTable);
        result.append(TOP_LEFT + "\n");
        System.out.println(result);
        result.append(textEmptyTable).append("\n");
        result.append(BOTTOM_RIGHT);
        System.out.println(result);
        formatResultEmptyTable(textEmptyTable);
        result.append(BOTTOM_LEFT + "\n");
        System.out.println(result);
        return result.toString();
    }

    private void formatResultEmptyTable(String textEmptyTable) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < textEmptyTable.length() - 2; i++) {
            temp.append(EQUAL);
        }
        result.append(temp);
        System.out.println(result);
    }

    private int getMaxColumnSize(List<DataSet> dataSets) {
        if (dataSets.size() > 0) {
            List<String> columnNames = dataSets.get(0).getColumnNames();
            for (String columnName : columnNames) {
                if (columnName.length() > maxLength) {
                    maxLength = columnName.length();
                }
            }
            getMaxColumnDataSetLength(dataSets);
        }
        return maxLength;

    }

    private void getMaxColumnDataSetLength(List<DataSet> dataSets) {
        for (DataSet dataSet : dataSets) {
            List<Object> dataSetValues = dataSet.getValues();
            getMaxColumnDataSetValueLength(dataSetValues);
        }
    }

    private void getMaxColumnDataSetValueLength(List<Object> dataSetValues) {
        for (Object dataSetValue : dataSetValues) {
            if (dataSetValue.toString().length() > maxLength) {
                maxLength = String.valueOf(dataSetValue).length();
            }
        }
    }


    private int getColumnCount(List<DataSet> dataSets) {
        int result = 0;
        if (dataSets.size() > 0) {
            return dataSets.get(0).getColumnNames().size();
        }
        return result;
    }




    private void rowsNames(List<DataSet> dataSets, int rowsCount, int columnCount) {
        for (int row = 0; row < rowsCount; row++) {
            List<Object> values = dataSets.get(row).getValues();
            result.append(PARALLEL);
            columnsNames(columnCount,values);
            result.append("\n");
            if (row < rowsCount - 1) {
                result.append(LEFT_PIPE);
                for (int j = 1; j < columnCount; j++) {
                    lastStringMaxColumn();
                    result.append(BOTH_PIPE);
                }
                lastStringMaxColumn();
                result.append(RIGHT_PIPE+"\n");
            }
        }
    }
    private void lastStringMaxColumn() {
        for (int i = 0; i < maxColumnSize; i++) {
            result.append(EQUAL);
        }
    }


    private void columnNameLengthLessThan(int columnNamesLength) {
        int columnLength = (maxColumnSize - columnNamesLength) / 2;
        for (int j = 0; j < columnLength; j++) {
            result.append(" ");
        }
    }
    private void columnNameLengthLessThanEqual(int columnNamesLength) {
        int columnLength = (maxColumnSize - columnNamesLength) / 2;
        for (int j = 0; j <= columnLength; j++) {
            result.append(" ");
        }
    }


    private void maxColumnSizeCalculation() {
        if (maxColumnSize % 2 == 0) {
            maxColumnSize += 2;
        } else {
            maxColumnSize += 3;
        }
    }
    private String  getStringTableData(List<DataSet> dataSets) {
        int rowsCount = dataSets.size();
        maxColumnSize = getMaxColumnSize(dataSets);
        result = new StringBuilder("");
        maxColumnSizeCalculation();
        int columnCount = getColumnCount(dataSets);
        rowsNames( dataSets,  rowsCount, columnCount);
        result.append(BOTTOM_RIGHT);
        for (int j = 1; j < columnCount; j++) {
            lastStringMaxColumn();
            result.append(UP_PIPE);
        }
        lastStringMaxColumn();
        result.append(BOTTOM_LEFT+"\n");
        return result.toString();
    }
    private void columnsNames(int columnCount,List<Object> values) {
        for (int column = 0; column < columnCount; column++) {
            int valuesLength = String.valueOf(values.get(column)).length();
            if (valuesLength % 2 == 0) {
                columnNameLengthLessThan(valuesLength);
                result.append(String.valueOf(values.get(column)));
                columnNameLengthLessThan(valuesLength);
                result.append(PARALLEL);
            } else {
                columnNameLengthLessThan(valuesLength);
                result.append(String.valueOf(values.get(column)));
                columnNameLengthLessThanEqual(valuesLength);
                result.append(PARALLEL);
            }
        }
    }

    private String  getHeaderOfTheTable(List<DataSet> dataSets) {
        maxColumnSize = getMaxColumnSize(dataSets);
          result = new StringBuilder("");
        int columnCount = getColumnCount(dataSets);
        maxColumnSizeCalculation();
        result.append(TOP_RIGHT);
        for (int j = 1; j < columnCount; j++) {
            lastStringMaxColumn();
            result.append(BOTTOM_PIPE);
        }
        lastStringMaxColumn();
        result.append(TOP_LEFT+"\n");
        List<String> columnNames = dataSets.get(0).getColumnNames();
        for (int column = 0; column < columnCount; column++) {
            result.append(PARALLEL);
            int columnNamesLength = columnNames.get(column).length();
            if (columnNamesLength % 2 == 0) {
                columnNameLengthLessThan(columnNamesLength);
                result.append(columnNames.get(column));
                columnNameLengthLessThan(columnNamesLength);
            } else {
                 columnNameLengthLessThan(columnNamesLength);
                result.append(columnNames.get(column));
                columnNameLengthLessThan(columnNamesLength);
            }
        }
        result.append(PARALLEL+"\n");
//        lastHeaderDataSet( dataSets ,columnCount);
        //last string of the header
        if (dataSets.size() > 0) {
            result.append(LEFT_PIPE);
            for (int j = 1; j < columnCount; j++) {
                for (int i = 0; i < maxColumnSize; i++) {
                    result.append(EQUAL);
                }
                result.append(BOTH_PIPE);
            }
            for (int i = 0; i < maxColumnSize; i++) {
                result.append(EQUAL);
            }
            result.append(RIGHT_PIPE+"\n");
        } else {
            result.append(BOTTOM_RIGHT);
            for (int j = 1; j < columnCount; j++) {
                for (int i = 0; i < maxColumnSize; i++) {
                    result.append(EQUAL);
                }
                result.append(UP_PIPE);
            }
            for (int i = 0; i < maxColumnSize; i++) {
                result.append(EQUAL);
            }
            result.append(BOTTOM_LEFT+"\n");
        }
        return result.toString();

    }

    private void columnNames(int columnCount, List<String> columnNames) {
        for (int column = 0; column < columnCount; column++) {
            result.append(PARALLEL);
            int columnNamesLength = columnNames.get(column).length();
            if (columnNamesLength % 2 == 0) {
                includeSymbol(columnNamesLength, column, columnNames);
            } else {
                includeSymbol(columnNamesLength, column, columnNames);
            }
        }
    }

    private void includeSymbol(int columnNamesLength, int column, List<String> columnNames) {
        columnNameLengthLessThan(columnNamesLength);
        result.append(columnNames.get(column));
        columnNameLengthLessThan(columnNamesLength);
    }

    private void lastHeaderDataSet(List<DataSet> dataSets, int columnCount) {
        if (dataSets.size() > 0) {
            result.append(LEFT_PIPE);
            includeHeaderDataLeft(columnCount);
            lastStringMaxColumn();
            result.append(RIGHT_PIPE + "\n");
        } else {
            result.append(BOTTOM_RIGHT);
            includeHeaderDataRight(columnCount);
            lastStringMaxColumn();
            result.append(BOTTOM_LEFT + "\n");
        }
    }

    private void includeHeaderDataLeft(int columnCount) {
        for (int j = 1; j < columnCount; j++) {
            lastStringMaxColumn();
            result.append(BOTH_PIPE);
        }
    }

    private void includeHeaderDataRight(int columnCount) {
        for (int j = 1; j < columnCount; j++) {
            lastStringMaxColumn();
            result.append(UP_PIPE);
        }
    }
}
