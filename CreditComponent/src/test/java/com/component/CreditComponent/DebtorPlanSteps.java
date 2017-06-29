package com.component.CreditComponent;

import static org.junit.Assert.assertEquals;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DebtorPlanSteps {
	CreditManager manager;
	Credit kredytobiorca;
	double creditCost;
	@Given("^Kredytobiorca zaciaga kredyt hipoteczny w wysokosci (\\d+) PLN na (\\d+) miesiecy w banku z marza (.+)% kredytobiorca chce co miesiac nadplacac kredyt stala kwota (\\d+) PLN, WIBOR wynosi (\\d+)%$")
	public void Kredytobiorca_zaciaga_kredyt_hipoteczny_w_wysokosci_PLN_na_miesiecy_w_banku_z_marza_kredytobiorca_chce_co_miesiac_nadplacac_kredyt_stala_kwota_PLN_WIBOR_wynosi(int wysokośćKredytu, int czasTrwania, double marżaBanku, double kwotaNadpłacania, double wibor) throws Throwable {
	kredytobiorca = new Credit(wysokośćKredytu, czasTrwania, marżaBanku, wibor, kwotaNadpłacania, 0, 0);
	}
	
	@Given("^Zmienialy sie rozne parametry$")
	public void zmienialy_sie_rozne_parametry(final List<Operations> operacje) throws Throwable{	
	manager = new CreditManager(kredytobiorca, operacje);
	}
	
	@When("^Policzył koszt kredytu$")
	public void policzył_koszt_kredytu() throws Throwable{
		
		creditCost = manager.mainCaluclate();
	}
	@Then ("^Koszt kredytu powinien wyniesc (.+) PLN$")
	public void Kozst_kredytu_powinien_wyniesc(final double kosztKredytu) throws Throwable{	
	assertEquals(kosztKredytu, creditCost, 0.01);
	}
}
