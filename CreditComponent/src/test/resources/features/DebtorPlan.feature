Feature: Plan Kredytobiorcy
 
  Scenario: Sprawdz jaki bedzie koszt kredytu gdy nie bedzie sie nadplacac i nie b edzie zadnych zmian
    Given Kredytobiorca zaciaga kredyt hipoteczny w wysokosci 300000 PLN na 60 miesiecy w banku z marza 2.0% kredytobiorca chce co miesiac nadplacac kredyt stala kwota 0 PLN, WIBOR wynosi 2%
    And  Zmienialy sie rozne parametry 
    	| operation  		  													| date      	| value  |
    When 	Policzył koszt kredytu
    Then Koszt kredytu powinien wyniesc 137725.02 PLN

  Scenario: Sprawdz jak zmiany wplyna na Twoj kredyty
    Given Kredytobiorca zaciaga kredyt hipoteczny w wysokosci 300000 PLN na 60 miesiecy w banku z marza 2.0% kredytobiorca chce co miesiac nadplacac kredyt stala kwota 0 PLN, WIBOR wynosi 2%
    And  Zmienialy sie rozne parametry 
    	| operation  		  													| date      	| value   |
    	| Od teraz będę nadpłacać kredyt w kwocie   | 09.07.2017  | 2000 PLN|
    	| Zmiana WIBOR   														| 09.07.2017  | 4%		  |
    	| Zmiana WIBOR   														| 10.01.2018  | 1%		  |
    When 	Policzył koszt kredytu
    Then Koszt kredytu powinien wyniesc 103489.58 PLN   
    
     Scenario: Splata po miesiacu
    Given Kredytobiorca zaciaga kredyt hipoteczny w wysokosci 300000 PLN na 1 miesiecy w banku z marza 2.0% kredytobiorca chce co miesiac nadplacac kredyt stala kwota 0 PLN, WIBOR wynosi 2%
    And  Zmienialy sie rozne parametry 
    	| operation  		  													| date      	| value   |
    	| Od teraz będę nadpłacać kredyt w kwocie   | 09.07.2017  | 295000 PLN|
    When 	Policzył koszt kredytu
    Then Koszt kredytu powinien wyniesc 4000 PLN
    
     Scenario: Sprawdz jak zmiany w nadplacaniu wplyna na Twoj kredyty
    Given Kredytobiorca zaciaga kredyt hipoteczny w wysokosci 300000 PLN na 60 miesiecy w banku z marza 2.0% kredytobiorca chce co miesiac nadplacac kredyt stala kwota 0 PLN, WIBOR wynosi 2%
    And  Zmienialy sie rozne parametry 
    	| operation  		  													| date      	| value   |
    	| Od teraz będę nadpłacać kredyt w kwocie   | 09.07.2017  | 2000 PLN|
    	| Zmiana WIBOR   														| 10.12.2017  | 4%		  |
    	| Od teraz będę nadpłacać kredyt w kwocie   | 09.07.2017  | 2000 PLN|
    	| Zmiana WIBOR   														| 10.01.2018  | 1%		  |
    	| Od teraz będę nadpłacać kredyt w kwocie   | 09.07.2018  | 8000 PLN|
    When 	Policzył koszt kredytu
    Then Koszt kredytu powinien wyniesc 76564.03 PLN  