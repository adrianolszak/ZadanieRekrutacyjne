# ZadanieRekrutacyjne
Komponent do wyliczenia kosztu kredytu hipotecznego w obliczu zmieniających się warunków spłaty.
# Założenia
Bank oblicza stałą ratę odsetkowo-kapitałową: skorzystałem tutaj ze wzoru:

  rata = S * q^n * (q-1)/(q^n-1)

S – kwota zaciągniętego kredytu
n – ilość rat
q – współczynnik równy 1 + (r / m), gdzie
q^n – „q” do potęgi „n”
r – oprocentowanie kredytu
m – ilość rat w okresie dla którego obowiązuje oprocentowanie „r”. 

Założyłem również że poprawność wprowadzonych danych weryfikowana jest w innym miejscu
