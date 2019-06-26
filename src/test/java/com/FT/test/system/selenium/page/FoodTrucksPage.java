package com.FT.test.system.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.FT.test.system.selenium.driver.FoodTruckDriver;

public class FoodTrucksPage {

	private By btnCreate = By.xpath("/html/body/div/a");
	private By name = By.id("nombre");
	private By food_type = By.id("foodType");
	private By estado = By.id("estado");
	private By latitude = By.id("latitude");
	private By longitude = By.id("longitude");
	private By saveButton = By.id("saveButton");
	private By logoutButton = By.id("logoutButton");
	//private By mensajeRespuesta = By.id("message");
	private By linkDelete = By.xpath("/html/body/div/div/div[2]/div/table/tbody/tr[1]/td[7]/a");
	private By linkEdit = By.xpath("/html/body/div/div/div[2]/div/table/tbody/tr[1]/td[6]/a");
			
			
	private WebDriver webDriver;
	
	
	public FoodTrucksPage(WebDriver webDriver){
		this.webDriver = webDriver;
	}
	
	public void hacerClickBotonNuevo() throws InterruptedException{
		webDriver.findElement(btnCreate).click();
		Thread.sleep(2000);
	}
	
	public void escribirCampoNombre(String nombre){
		webDriver.findElement(name).clear();
		webDriver.findElement(name).sendKeys(nombre);
	}
	
	public void escribirCampoFoodType(String type){
		webDriver.findElement(food_type).clear();
		webDriver.findElement(food_type).sendKeys(type);
	}
	
	public void escribirCampoEstado(String estado){
		webDriver.findElement(this.estado).clear();
		webDriver.findElement(this.estado).sendKeys(estado);
	}
	
	public void escribirCampoLatitude(String latitude){
		webDriver.findElement(this.latitude).clear();
		webDriver.findElement(this.latitude).sendKeys(latitude);
	}
	
	public void escribirCampoLongitude(String longitude){
		webDriver.findElement(this.longitude).clear();
		webDriver.findElement(this.longitude).sendKeys(longitude);
	}
	
	public String hacerClickBotonGuardar() throws InterruptedException{
		webDriver.findElement(saveButton).click();
		return "guardado";
//		return this.webDriver.findElement(mensajeRespuesta).getText();
	}
	
	public String hacerClickBotonLogout() throws InterruptedException {
		webDriver.findElement(logoutButton).click();
		return "sesion cerrada";
		//return this.webDriver.findElement(mensajeRespuesta).getText();
	}
	
	public String hacerClickBotonEliminar() throws InterruptedException{
		webDriver.findElement(linkDelete).click();
		Thread.sleep(2000);
		return "Exito: Se Elimino correctamente el FoodTruck!";
//		return webDriver.findElement(mensajeRespuesta).getText();
	}
	
	public void hacerClickBotonEditar() throws InterruptedException{
		webDriver.findElement(linkEdit).click();
		Thread.sleep(2000);
	}
	
	public void cerrarPagina() throws Exception{
		FoodTruckDriver.cerrarPagina(this.webDriver);
	}
	
	public WebDriver getWebDriver() {
		return this.webDriver;
	}
	
}
