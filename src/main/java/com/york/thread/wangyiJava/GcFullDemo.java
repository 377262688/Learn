package com.york.thread.wangyiJava;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

/**
 * @author york
 * @create 2019-10-18 21:08
 **/
public class GcFullDemo {

    public static void main(String[] args) throws IOException, BiffException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            WorkbookSettings settings = new WorkbookSettings();
            settings.setGCDisabled(true);
            Workbook workbook = Workbook.getWorkbook(
                    new File(GcFullDemo.class.getClassLoader()
                            .getResource("FullGcDemo.xlt").getFile()),settings);
            Sheet sheet = workbook.getSheet(0);
            Cell cell = sheet.getCell(0,0);
            String content = cell.getContents();
            System.out.println(content);
            Thread.sleep(2000L);
        }
    }
}
