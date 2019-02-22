Herzlich Willkommen bei der ersten Pizza Session zum Thema Test Driven Development im Jahr 2019!

Wir wollen hierbei zusammen mit euch Code-Katas lösen und dabei die Regeln von TDD zusammen versuchen anzuwenden. Am Anfang dieser Pizza-Session
schauen wir zunächst ein Video zum Thema Mocking und wollen im Anschluss eine praktische Aufgabe zu diesem Thema in einem Code-Kata lösen.

# Aufgabe 1: Umsetzung eines Login-Usecases

Das Code-Kata behandelt thematisch den Login-Usecase. Man stelle sich zum Beispiel vor, dass man beauftragt wird einen Bereich
einer Anwendung mit einem wie beispielsweise die Klasse Kombination aus Mail-Adresse + Passwort zu schützen. Die [Entity-Klassen](https://github.com/TheAomx/TDD-Pizza-Session-01-19/tree/master/src/de/ov/software/kata/tdd/y2019/v01/entities) in diesem Usecase sollen in einem persistenten Datenspeicher wie beispielsweise einer Datenbank abgespeichert werden.

Als Eingabe-Werte für den Login-Usecase sollen die drei Attribute "Mail-Adresse", "Passwort" und "IP-Adresse" für das Code-Kata ausreichen.
Hierbei soll der Login-Usecase die Fälle abdecken, dass ein User 

a. eine nicht in der Datenbank gespeicherte Mail-Adresse eingibt.

b. ein ungültiges Passwort zu einer in der Datenbank gespeicherten Mail-Adresse angibt.

c. eine gültige Kombination aus Mail-Adresse und Passwort eingibt.

d. Null-Eingaben bzgl. des Requests, bzw. der Attribute Mail-Adresse, Passwort und IP-Adresse vornimmt.

Falls der User einen gültigen Login-Versuch vorgenommen hat, soll vom Login-Usecase eine LoginSession erzeugt werden, bei welcher der die Mail-Adresse, ein zufälliger Access-Token, die beim Login verwendete IP-Adresse, der Login-Zeitpunkt sowie ein Ablaufdatum des Login-Session hinterlegt wird.

Da die Entwicklung im TDD-Style erfolgen soll, müsst ihr anfangs gut überlegen, wie die Tests von externen Datenquellen wie in diesem Beispiel
die Datenbank unabhängig gemacht werden können. Hierzu können Test Doubles wie Mocks, Stubs, Spys und Fakes verwendet werden. Im Login-Usecase
muss man sich zudem mittels Dependency Inversion von einer konkreten Datenquelle entkoppeln und die Zugriffsoperationen hinter einem Interface 
kapseln.

Hierzu können als Anregung beispielsweise die Gateway-Interfaces im Unterordner [gateways](https://github.com/TheAomx/TDD-Pizza-Session-01-19/tree/master/src/de/ov/software/kata/tdd/y2019/v01/gateways) dienen. Wer zudem Schwierigkeiten beim "Start" in dieses Code-Kata hat, kann auch gerne einen
Blick in den [LoginUseCaseTest](https://github.com/TheAomx/TDD-Pizza-Session-01-19/blob/master/src/de/ov/software/kata/tdd/y2019/v01/usecases/login/test/LoginUseCaseTest.java) werfen. 

Falls dennoch noch weitergehende Fragen nach dieser längeren Erkärung offen sind, könnt ihr gerne mich oder Isabel persönlich ansprechen! Ihr könnt natürlich bei der Lösung dieses Code-Katas jede Programmiersprache verwenden. Falls jemand schon Erfahrung mit Mocking-Frameworks gesammelt und diese einsetzen will, kann dies auch gerne machen!

Wir sind in jedem Falle gespannt auf eure Lösungen und wünschen euch viel Spaß beim Lösen dieses Code-Katas zum Thema Mocking!
