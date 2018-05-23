<h1>Generator transakcji w Dockerze</h1>

Utworzony obraz po uruchomieniu odczytuje parametry z pliku: /storage/generator.properties i generuje pliki z transakcjami do katalogu: /storage.

Przykład użycia:
```bash
gradle build
docker build --tag generator-transakcji .
docker run -v (katalog):/storage generator-transakcji
```