package com.FT.test.system.selenium.testcase;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.FT.test.system.selenium.driver.*;
import com.FT.test.system.selenium.testlink.*;

import com.FT.test.system.selenium.page.*;
import com.FT.test.system.selenium.datamanager.Excel;
import com.FT.test.system.selenium.util.Utilitario;

public class EliminarFoodTruck {

	private IniciarSesionPage iniciarSesionPage;
	private FoodTrucksPage foodTruckPage;
	private String rutaCarpetaError = "C:\\Users\\User\\Downloads";
	private Integer idNavegadorTestlink;
	private String nombreNavegadorTestlink;
	
	
	
	@BeforeTest
	@Parameters({"navegador", "remoto", "testlinkIdNavegador", "testlinkNombreNavegador"})
	public void antesDelTest(String navegador, int remoto, int testlinkIdNavegador, String testlinkNombreNavegador) {
		this.iniciarSesionPage = new IniciarSesionPage(navegador, remoto == 1);
		this.foodTruckPage = new FoodTrucksPage(this.iniciarSesionPage.getWebDriver());
		this.idNavegadorTestlink = testlinkIdNavegador;
		this.nombreNavegadorTestlink = testlinkNombreNavegador;
	}
	
	@DataProvider(name = "DatosDeEntrada")
	public static Object[][] datosDeEntrada(ITestContext contexto){
		String rutaArchivo = contexto.getCurrentXmlTest().getParameter("rutaArchivoExcel");
		return Excel.leerExcel(rutaArchivo);
	}
	
	@Test(dataProvider = "DatosDeEntrada")
	public void insertarfoodTruck(String casoPrueba, String urlInicial, String usuario, String clave,
								String valorEsperado) {
								
//								, String urlTestlink, String keyTestlink,  String idTestCaseInternoTestlink,
//								String idTestCaseExternoTestlink,  String idTestPlanTestlink, String idBuildTestlink, 
//								String nombreBuildTestlink){
		
		try{
			this.iniciarSesionPage.ingresarPaginaIniciarSesion(urlInicial);
			this.iniciarSesionPage.iniciarSesion(usuario, clave);
			String valorObtenido = this.foodTruckPage.hacerClickBotonEliminar();
			
			Assert.assertEquals(valorObtenido, valorEsperado);
//			FoodTruckTestlink.reportarCasoDePrueba(urlTestlink, keyTestlink, Integer.parseInt(idTestCaseInternoTestlink), 
//					 Integer.parseInt(idTestCaseExternoTestlink),  Integer.parseInt(idTestPlanTestlink), true, 
//					 Integer.parseInt(idBuildTssestlink), nombreBuildTestlink, "Se ejecuto correctamente", 
//					 this.idNavegadorTestlink, this.nombreNavegadorTestlink);
			
		}catch (AssertionError e) {
			Utilitario.caputarPantallarError(this.rutaCarpetaError, "Error: " + e.getMessage(), 
					foodTruckPage.getWebDriver());
//			FoodTruckTestlink.reportarCasoDePrueba(urlTestlink, keyTestlink, Integer.parseInt(idTestCaseInternoTestlink), 
//					 Integer.parseInt(idTestCaseExternoTestlink),  Integer.parseInt(idTestPlanTestlink), false, 
//					 Integer.parseInt(idBuildTestlink), nombreBuildTestlink, "Error:" + e.getMessage(), 
//					 this.idNavegadorTestlink, this.nombreNavegadorTestlink);
			Assert.fail(e.getMessage());
			
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error: " + e.getMessage());
		}
		
	}
	
	@AfterTest
	public void despuesDelTest() {
		try {
			Thread.sleep(2000);
			this.foodTruckPage.hacerClickBotonLogout();
			this.foodTruckPage.cerrarPagina();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
