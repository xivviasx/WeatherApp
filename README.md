Projekt WheatherApp to aplikacja konsolowa napisana w języku Java, umożliwiająca użytkownikowi sprawdzanie aktualnej pogody w wybranych miastach za pomocą danych pobranych z API OpenWeatherMap. Program pozwala użytkownikowi na wprowadzanie nazwy miasta, a następnie prezentuje informacje o temperaturze, ciśnieniu i wilgotności powietrza dla danego miejsca. Dodatkowo użytkownik ma możliwość zapisania wyników pomiarów w formacie PDF, JSON lub XML.

Instrukcja obsługi
Program rozpoczyna się od wczytania listy miast i ich współrzędnych geograficznych w formacie JSON.
Po uruchomieniu programu, użytkownik wprowadza nazwę miasta, dla którego chce sprawdzić pogodę.
Program zwraca aktualne dane meteorologiczne dla podanego miasta.
Użytkownik może kontynuować wprowadzanie kolejnych miast lub zakończyć działanie programu.
Po zakończeniu, użytkownik ma możliwość zapisania wyników pomiarów w formacie PDF, JSON lub XML.

Technologie
Język programowania: Java
Biblioteki: Apache HttpClient (do wykonywania zapytań HTTP), Biblioteka PDF (do generowania plików PDF)

Testowanie
Testy jednostkowe zostały napisane korzystając z biblioteki JUnit oraz Mockito.
