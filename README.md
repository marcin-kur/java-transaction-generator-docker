<h1>Zadanie 4 - Generator transakcji</h1>

Generator "transakcji" - plików JSON, generuje transakcje wg wzoru:

{
  "id": 1,
  "timestamp": "2018-03-08T12:31:13.000-0100",
  "customer_id": 12,
  "items": [
    {
      "name": "bułeczka",
      "quantity": 3,
      "price": 1.2
    },
    {
      "name": "mleko 3% 1l",
      "quantity": 1,
      "price": 2.3
    }
  ],
  "sum": 4.5
}

Każda transakcja jest zapisywana do odzielnego pliku w formacie "transaction_nrTransakcji.json". 

Build projektu(shadow jar build/libs/transaction-generator-all.jar):

- gradle build

Zainstalwanie w lokalnym repozytorium:

- gradle install

Przykładowe wywołanie:

- java -jar transaction-generator-all.jar -customerIds 1:20 -dateRange "2018-03-08T00:00:00.000-0100":"2018-03-08T23:59:59.999-0100" -itemsFile items.csv -itemsCount 5:15 -itemsQuantity 1:30 -eventsCount 1000 -outDir ./output

Parametry:

- customerIds: zakres, z jakiego będą generowane wartości do pola "customer_id". Domyślny 1:20

- dateRange: zakres dat, z jakiego będzie generowane pole "timestamp". Domyślny dzień uruchomienia, cała doba

- itemsFile: nazwa pliku csv zawierającego spis produktów. Przykładowy plik (items.csv) dołączony do zadania.

- itemsCount: zakres ilości elementów generowanych w tablicy "items". Domyślny 1:5

- itemsQuantity: zakres z jakiego będzie generowana ilość kupionych produktów danego typu (pole "quantity"). Domyślnie 1:5

- eventsCount: ilość transakcji (pojedynczych plików) do wygenerowania. Domyślnie 100.

- outDir: katalog, do którego mają być zapisane pliki. Domyślnie aktualny katalog roboczy.

W aplikacji istnieje logowanie z wykorzystaniem Logbacka.

Logowanie na konsolę jest sformatowane JSonem.

Logowanie do pliku to tekst, z datą/godziną, wątkiem, poziomem logowania oraz wiadomością. Logi są zapisywane w folderze logs.

Plik jest "rolowany" co 10MB. Poprzedni plik jest zarchiwizowany z dopisaniem daty/godziny.