/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DAL.ReportDAL;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 *
 * @author Yue
 */
public class ExcelExporter {

    public ExcelExporter() {

    }

    public void exportToExcel(JTable table, String path) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(2);
        for (int j = 0; j < table.getColumnCount(); j++) {
            Cell cell = headerRow.createCell(j);
            cell.setCellValue(table.getColumnName(j));
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 3;
        for (int i = 0; i < table.getRowCount(); i++) {
            Row row = sheet.createRow(rowNum++);
            for (int j = 0; j < table.getColumnCount(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(table.getValueAt(i, j) == null ? "" : table.getValueAt(i, j).toString());
            }
        }

        for (int j = 0; j < table.getColumnCount(); j++) {
            sheet.autoSizeColumn(j);
        }

        try {
            FileOutputStream outputExcel = new FileOutputStream(path);
            workbook.write(outputExcel);
            outputExcel.close();
            workbook.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelExporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelExporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
