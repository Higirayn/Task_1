# Task_1 — юнит-тесты Stellar Burgers

Java 11, Maven, JUnit 5, Mockito, JaCoCo.

Исходные классы взяты из https://github.com/yandex-praktikum/QA-java-diplom-1 без изменений.
Проверены `Bun`, `Burger`, `Ingredient`, `IngredientType`. Есть параметризация и моки зависимостей бургера.

Запуск:
```shell
mvn clean verify
```

Отчёт: `target/site/jacoco/index.html`. Сохранённый результат прогона: `reports/jacoco/index.html`.
Порог покрытия строк — 70%. Область отчёта соответствует заданию: четыре перечисленных класса.
Демонстрационные `Praktikum` и `Database` исключены из отчёта и проверки порога.
