package com.FT.test.system.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.FT.test.system.selenium.driver.FoodTruckDriver;


public class IniciarSesionPage {

	static By username = By.id("username");
	static By password = By.id("password");
	static By iniciarSesion = By.id("loginButton");
	//static By message = By.id("message");
	private WebDriver webDriver = null;
	
	public IniciarSesionPage(String navegador, boolean remoto) {
		this.webDriver = FoodTruckDriver.inicializarDriver(navegador, remoto);
	}
	
	public void ingresarPaginaIniciarSesion(String url) throws Exception{
		System.out.println(url);
		webDriver.get(url);
		
	}
	
	public void iniciarSesion(String usuario, String clave) throws Exception{
		this.webDriver.findElement(username).clear();
		this.webDriver.findElement(username).sendKeys(usuario);
		this.webDriver.findElement(password).clear();
		this.webDriver.findElement(password).sendKeys(clave);
		this.webDriver.findElement(iniciarSesion).click();
		Thread.sleep(2000);
	}
	
	public void cerrarPagina() throws Exception{
		FoodTruckDriver.cerrarPagina(this.webDriver);
	}
	
	public WebDriver getWebDriver() {
		return this.webDriver;
	}
	
}
