<p align="center">
 <img src="https://raw.githubusercontent.com/luksari/kanban-board/development/kanban.png" width="235">
</p>

# Kanban Board

Kanban Board jest projektem zespołowym wykonywanym w ramach przedmiotu `Aplikacje Mobline`.

Projekt ten został stworzony przy współpracy 5 osób:

- Justyna Kozik
- Łukasz Tyszkiewicz
- Marcin Josiński
- Artur Góra
- Michał Kempski

Aby pomyślnie ukończyć projekt używaliśmy wielu narzędzi i technologi, które pomogły przyśpieszyć i zrównoleglić naszą pracę.

## Założenia

Projekt ma na celu dostarczenie funkcjonalności podobnej do tej, którą dostarcza popularna aplikacja `Trello`. Ma on za zadanie ułatwienie organizacji pracy w zespole. Zgodnie z założeniami użytkownik powinien móc tworzyć wiele tablic na których będzie zarządzać zadaniami, które uprzednio stworzył, a także członkami, którzy są przypisani do danej tablicy zadań. Właściciel tablicy może zapraszać kolejnych użytkowników, którzy razem będą tworzyć zespół, pracujący nad zadaniami z danej tablicy. Projekt wykorzystuje swoje wewnętrzne API do zapisywania i pobierania danych. API wykorzystuje bazę danych do zapisywania informacji o tablicach, i zadaniach.   

## Technologie

W ramach przedmiotu `Aplikacje Mobilne` byliśmy zoobowiązani do napisania projektu przy użyciu języka Kotlin.

Oprócz Kotlina, korzystaliśmy także z różnych wzorców i technologii, takich jak:

- MVVM - Model - View - ViewModel
- LiveData - MutableLiveData
- Data binding
- Dependency injection
- Room - Local Database communication
- Koin - Dependency injection
- MVVM android
- Data binding
- GSON/Jackson - parsing json
- Retrofit - communication with API
- Android KTX
- Python Django - API
- Material Design

Do stworzenia API użyliśmy technologi Python Django. API wykorzystuje bazę danych MySQL do przechowywania informacji o tablicach, zadaniach i użytkownikach.

## Zarządanie projektem

Kod źródłowy aplikacji był rozwijany na GitHubie. Każda osoba robiąca kolejne zadanie tworzyła swój osobny branch, na którym implementowała dane zadanie i po skończeniu, mergowała swój kod do brancha `development`.

Projekt był organizowany za pomocą `Trello`, podzieliliśmy cały projekt na wiele zadań które można było implementować równolegle.

Do komunikacji używaliśmy `Slacka`, na którym mieliśmy kanał dla każdej możliwej domeny implementacji np. `UI`, `API` albo `Tasks`.
