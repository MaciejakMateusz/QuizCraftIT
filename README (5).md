# Zadanie rekrutacyjne - CodersLab - kurs JEE

## Wstęp

Poniższe zadanie jest okazją, by zaprezentować Twoje dotychczasowe doświadczenie, Twój styl oraz sposób pracy.
Zaprezentuj swoje umiejętności, wiedzę oraz znajomość narzędzi i technologii zdobytą w trakcie kursu.

W ramach zadania będziesz miał/miała okazję zaprezentować swoje umiejętności w następujących obszarach:

* Spring Boot,
* Java w wersji 8 lub wyższej,
* REST API,
* Spring Data JPA,
* JUnit,
* Maven,
* najlepsze praktyki.

Rozwiązanie umieść w serwisie GitHub.

W repozytorium umieść skrypt tworzący schemat bazy danych dla aplikacji.

## Wprowadzenie

Twoim celem jest stworzenie aplikacji opartej o **Spring Boot**, umożliwiającej przetestowanie wiedzy z różnych technologii (MySQL, JavaScript, Linux).
Wyświetlanie pytań oraz udzielanie odpowiedzi będzie dostępne za pomocą API, format przesyłanych danych to **json**.

W celu zasilenia bazy danych konkretnymi pytaniami i odpowiedziami do nich połączysz się z **https://quizapi.io/**,
które udostępnia pytania wraz z poprawnymi odpowiedziami. 

Dane z **quizapi** musisz przekształcić w taki sposób,
by zapisać je w strukturze bazy danych określonej w dalszej części zadania. Możesz w tym celu wykorzystać obiekty transferowe **DTO**.

Na podstawie danych pobranych z **quizapi** udostępnisz własne endpointy API, umożliwiające wyświetlanie losowego pytania i udzielania odpowiedzi.

Aplikacja ma posiadać następujące główne funkcjonalności:
- pobieranie z zewnętrznego API pytań,
- udostępnianie w postaci API losowego pytania,
- możliwość udzielenia przez API odpowiedzi na pytanie.


## Zasoby API

Twoim zadaniem testowym będzie utworzenie w aplikacji dwóch następujących endpointów:

1. dostępny metodą GET  adres - **/api/questions** (pobranie pytania wraz z możliwymi odpowiedziami)
2. dostępny metodą POST adres - **/api/answers**. (odpowiedź na zadane pytanie)

### /api/questions
Endpoint ma zwracać jedno losowe pytanie z bazy danych oraz możliwe odpowiedzi.

Przykład:

```json
{
  "id": 4,
  "question": "Which of the following is correct about NULL?",
  "answers": [
    {
      "id": 14,
      "answer": "NULL is a special type that only has one value: NULL."
    },
    {
      "id": 15,
      "answer": "The special constant NULL is capitalized by convention, but actually it is case insensitive."
    },
    {
      "id": 16,
      "answer": "NULL is a special type that only has two values : NULL and NOT NULL"
    },
    {
      "id": 17,
      "answer": "The special constant NULL is capitalized by convention and it should be defined as such as it's case sensitive. Meaning null is different than NULL"
    }
  ]
}
```

Podczas losowania pytania możemy przyjąć założenie, że dane w bazie mają narastające identyfikatory i nie jest możliwe
usuwanie danych.

Napisz w komentarzu, w jaki sposób proponujesz pobrać losowe pytanie dane z bazy w przypadku, gdy nie możemy polegać na
wcześniejszym założeniu.

### /api/questions

Endpoint ma umożliwiać udzielenie odpowiedzi dla pytania. Zwrotnie mamy uzyskać informacje czy udzielona odpowiedź jest poprawna.

Przykład:

```json
{
  "answers": [
    1,2
  ],
  "questionId": 1
}
```

Przykładowy response dla poprawnej odpowiedzi:

```json
{
  "correct": true
}
```

Przykładowy response dla niepoprawnej odpowiedzi:

```json
{
  "correct": false
}
```
Odpowiedź uznajemy za prawidłową tylko w przypadku udzielenia wszystkich poprawnych odpowiedzi (w przypadku gdy występuje więcej niż jedna).

**Walidacja danych**

Jeżeli dane przesyłane w odpowiedzi nie są poprawne:
- brakuje wartości atrybutu **questionId**,
- brakuje wartości atrybutu **answers**.

wyświetlaj stosowny komunikat w formacie json.

Jeżeli dla podanego **questionId** nie znajdziesz odpowiedzi, zwracaj status 404.


### Pobieranie danych dla aplikacji

#### Struktura danych
Dane powinny zostać zapisane w poniższej strukturze bazy danych:

![image info](./diagram.png)


#### Źródło pytań

Do pozyskania pytań dla projektu wykorzystaj **https://quizapi.io/**. Api wymaga podania **API key**, który możemy
wygenerować pod adresem:
**https://quizapi.io/register**.

Aplikacja podczas startu łączy się z api, pobiera pytania, które następnie zapisuje do bazy danych.
Wykorzystaj **CommandLineRunner** w celu wywołania pobierania danych podczas startu aplikacji.

Adres **quizapi** udostępniający pytania to: **https://quizapi.io/api/v1/questions**

Wykonaj połączenie do API 10 razy, odpowiedź zapisuj do bazy danych.

Dane dostarczane przez **quizapi** są postaci:

```
[
    {
        "id": 550,
        "question": "Which sign is used to access a variable of  a variable in PHP?",
        "description": null,
        "answers": {
            "answer_a": "$$",
            "answer_b": "$|",
            "answer_c": "#@",
            "answer_d": "$",
            "answer_e": null,
            "answer_f": null
        },
        "multiple_correct_answers": "false",
        "correct_answers": {
            "answer_a_correct": "true",
            "answer_b_correct": "false",
            "answer_c_correct": "false",
            "answer_d_correct": "false",
            "answer_e_correct": "false",
            "answer_f_correct": "false"
        },
        "correct_answer": "answer_a",
        "explanation": null,
        "tip": null,
        "tags": [
            {
                "name": "PHP"
            }
        ],
        "category": "Code",
        "difficulty": "Medium"
    }
]
```


- zapisujemy dostępne odpowiedzi z właściwości `answers`,
- informację o poprawnej odpowiedzi z klucza `correct_answers`, api zawiera również `correct_answer` - w niektórych
  przypadkach wartość ta jest **null**, więc nie możemy na niej polegać.

Ignoruj odpowiedzi z wartością `null` np. (`"answer_f": null`),

**Quizapi** może również zwracać więcej niż jedną poprawną odpowiedź:

```json
{
  "id": 396,
  "question": "What kind of replication is supported by the MySQL Server?",
  "description": null,
  "answers": {
    "answer_a": "Multiple-master Replication",
    "answer_b": "MySQL Doesn't Support Replication",
    "answer_c": "Master To Slave Replication",
    "answer_d": "Single File Based Clustering",
    "answer_e": "Master to Master Replication",
    "answer_f": null
  },
  "multiple_correct_answers": "true",
  "correct_answers": {
    "answer_a_correct": "false",
    "answer_b_correct": "false",
    "answer_c_correct": "true",
    "answer_d_correct": "false",
    "answer_e_correct": "true",
    "answer_f_correct": "false"
  },
  "correct_answer": "answer_a",
  "explanation": null,
  "tip": null,
  "tags": [
    {
      "name": "MySQL"
    }
  ],
  "category": "SQL",
  "difficulty": "Medium"
},
```

Dokumentacja: https://quizapi.io/docs/1.0/overview


## Oceniane elementy

1. Kompletność rozwiązania
2. Jakość kodu: logiczny podział na klasy/interfejsy poszczególnych elementów aplikacji, testy, komentarze, zastosowane wzorce projektowe.

Dodatkowo punktowane będzie:
- utworzenie kontenera dokerowego z bazą danych oraz aplikacją,
- wykorzystanie biblioteki swagger jako narzędzia do testowania metod API.

## Check-lista

### Zadania obowiązkowe

- [ ] Aplikacja oparta o `Spring Boot`.
    * Uzupełnione zależności,
    * Pobieranie pytań z **quizapi** podczas startu aplikacji,
    * Dodawanie pobranych z **quizapi** pytań do bazy danych,
    * Oznaczenie poprawnych odpowiedzi,
    * Utworzenie encji,
    * Utworzenie repozytoriów,
    * Utworzenie kontrolerów.
  
- [ ] Aplikacja udostępnia API dostępne pod adresem: (`/api/questions`).
    * Pytanie jest losowane spośród dostępnych w bazie danych,
    * Format danych jest zgodny z określonym w treści zadania.

- [ ] Aplikacja udostępnia API dostępne pod adresem: (`/api/answers`).
    * Poprawne odebranie przesłanych informacji,
    * Wyświetlenie komunikatu w przypadku poprawnej odpowiedzi,
    * Wyświetlenie komunikatu w przypadku niepoprawnej odpowiedzi,
    * Sprawdzanie, czy określone w formacie odpowiedzi znajduje się **questionId**,
    * Sprawdzanie, czy określone w formacie odpowiedzi znajduje się **answers**,
    * W przypadku błędnego **questionId** aplikacja zwraca kod HTTP 404,
    * Format danych jest zgodny z określonym w treści zadania.

### Zadania dodatkowe

- [ ] Konfiguracja biblioteki Swagger,
- [ ] Testy aplikacji,
- [ ] Konfiguracja docker-compose,
- [ ] Poprawne formatowanie kodu,
- [ ] Odpowiednia przejrzystość i jakość kodu,
- [ ] Przygotowanie pliku README z opisem projektu oraz informacjami niezbędnymi do uruchomienia aplikacji.

## Uwagi końcowe

- Możesz użyć pliku README do zapisania swoich uwag i spostrzeżeń dotyczących wykonywanego zadania. Możesz również
  wykorzystać ten plik do zaprezentowania swojego punktu widzenia dotyczącego procesu realizacji zadania.

- Upewnij się, że w Twoim kodzie nie ma błędów (generujących ostrzeżenia lub błędy w konsoli). Lepiej gdy, któryś z
  punktów nie zostanie dostarczony, aniżeli dostarczysz go zaimplementowanego nieprawidłowo.

## Materiały pomocnicze

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

* [Run method on Spring Boot startup](https://stacktraceguru.com/springboot/run-method-on-startup)
* [Map Serialization and Deserialization with Jackson](https://www.baeldung.com/jackson-map)

* [Wzorce projektowe](https://www.patterns.dev/)
* [Data Transfer Object](https://martinfowler.com/eaaCatalog/dataTransferObject.html)
* [Testing the Web Layer](https://spring.io/guides/gs/testing-web/)
* [Guide to Unit Testing Spring Boot REST APIs](https://stackabuse.com/guide-to-unit-testing-spring-boot-rest-apis/)
