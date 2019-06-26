package com.FT.test.system.selenium.datamanager;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Excel {

	public static String[][] leerExcel(String rutaArchivo) {
		String[][] lista = null;
		int i = 0;
		String valor = "";
		try {
			FileInputStream archivo = new FileInputStream(new File(rutaArchivo));
			XSSFWorkbook archivoExcel = new XSSFWorkbook (archivo);
			XSSFSheet  hojaExcel = archivoExcel.getSheetAt(0);
			Iterator<Row> filas = hojaExcel.iterator();
			filas.next();
			lista = new String[hojaExcel.getLastRowNum()][];
			while (filas.hasNext()) {
				Row filaActual = filas.next();
				Iterator<Cell> celdas = filaActual.cellIterator();
				lista[i] = new String[filaActual.getLastCellNum()];
				int j = 0;
				while (celdas.hasNext()) {
					Cell celda = celdas.next();
					switch(celda.getCellTypeEnum()) {
						case STRING:
							valor = celda.getStringCellValue();
							break;
						case NUMERIC:
							if(DateUtil.isCellDateFormatted(celda)){
								SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
								valor = sdf.format(celda.getDateCellValue());
								
							}else{
								valor = String.valueOf(celda.getNumericCellValue());
							}
							break;
						default:
							valor = "";
							break;
					}
					lista[i][j] = valor;
					j++;
				}
				i++;
			}
			archivoExcel.close();
			archivo.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return lista;
	}
}