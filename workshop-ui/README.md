# workshop-ui

In diesem Projekt befindet sich das Frontend. Es wird [vue-js](https://vuejs.org/) als 
Web-Framework verwendet. Zum Bauen und Testen der Applikation 
wird das Build-Tool [vite](https://vitejs.dev/) eingesetzt.

## Projekt bauen und ausführen

Es wird das [gradle-node-plugin](https://github.com/node-gradle/gradle-node-plugin) verwendet. Das Plugin
installiert automatisch eine konfigurierte Node-JS-Version im Projektverzeichnis (`.gradle/nodejs`). Weiters
werden Gradle-Tasks bereitgestellt, die die Ausführung von npm-Scripts ermöglichen. 

### Abhängigkeiten installieren


`npm install` oder `gradlew.bat :workshop-ui:npmInstall`


### Development-Server mit Hot-Reload starten

`npm run dev` oder `gradlew.bat :workshop-ui:npmDev`

### Bundle für Produktivbetrieb bauen

`npm run build` oder `gradlew.bat :workshop-ui:npmBuild`

### Linten mit [ESLint](https://eslint.org/)

`npm run lint` oder `gradlew.bat :workshop-ui:npmLint`


## Über das Projekt

`vue` ist ein Javascript-Framework, dass eine komponenten-orientierte Strukturierung von Web-Anwendungen
ermöglicht. `vue` definiert dabei eine eigene Template-Struktur (`.vue`-Dateien). Diese `.vue`-Templates werden 
dann in herkömmliches Javascript, HTML und CSS übersetzt. Das heißt nach dem Bauen einer Vue-Applikation, kann diese 
über einen gewöhnlichen Webserver ausgeliefert werden (Nginx, Apache etc...).

Zum Übersetzen der `.vue`-Templates wird `vite` verwendet. `vite` ist ein Build-Tool zum Bauen und Testen von Webanwendungen.
Bei `vite` steht vor allem Performance im Vordergrund. `vite` selbst ist auch in Javascript implementiert, daher
wird für dieses Projekt auch eine `nodejs`-Installation benötigt. Abhängigkeiten werden über den Node-Package-Manager
[npm](https://www.npmjs.com/) installiert. Die Abhängigkeiten werden in der Datei `package.json` deklariert und in dem
Ordner `node_modules` gespeichert. 

`vite` stellt auch einen Live-Reload-Server für die lokale Entwicklung zur Verfügung. Mittels `npm run dev` kann dieser
gestartet werden. Ein Live-Reload-Server beobachtet das Dateisystem des Projekts. Wenn es Änderungen gibt, wird
das Projekt neu gebaut und automatisch über den Webserver wieder ausgeliefert. In anderen Worten wird ermöglicht, 
dass Änderungen unmittelbar getestet werden können. Es ist nicht notwendig jedes Mal manuell einen `build` zu starten. 
Der Live-Reload-Server ist unter Port `3000` erreichbar. Dieser Server ist lediglich für die lokale
Entwicklung gedacht. In einem Produktbetrieb sollte die Applikation von einem stabilen Backend ausgeliefert werden; 
zum Beispiel (Nginx, Apache oder Spring-Boot). 

### Backend-API

Um das Backend (Task- und Employee-Endpoints) aufzurufen müssen wir noch einen API-Client generieren. Hierfür wird der 
Gradle-Task `openApiGenerate` verwendet. 

```
gradlew.bat :workshop-ui:openApiGenerate
```

### Proxy-Settings und CORS

Aktuelle Browser verhindern, dass Javascript-Code API-Calls zu fremden Webseiten macht. 
Wird ein Webserver unter `localhost:3000` betrieben kann dieser keine API-Aufrufe auf 
`localhost:8080` durchführen. Dieses Verhalten hat einen sicherheitstechnischen Hintergrund und hängt 
mit den Browser-Cookies zusammen. 

Hierzu ein Beispiel: 

Wir sind in unserem Browser auf `bank.at` angemeldet, um einige Überweisungen durchzuführen. Gleichzeitig 
sehen wir uns fragwürdige Inhalte auf `illegal.to` in einem anderen Tab an. Nun macht der Javascript-Code
von `illegal.to` folgenden API-Aufruf `POST https://bank.at/ueberweisung`. Der API-Aufruf würde funktionieren, 
da wir in einem anderen Tab auf `bank.at` angemeldet sind und darum der Browser mit dem API-Aufruf auch den
Session-Cookie von `bank.at` mitschickt. Deshalb verhindern moderne Browser solche Cross-Origin-Requests im Normalfall.

Es gibt jedoch mehrere Fälle wo es möglich sein sollte, Cross-Origin-Requests durchzuführen. Zum Beispiel wenn 
man mehrere Applikationen entwickelt, die miteinander kommunizieren sollen. In diesem Fall lässt sich 
[Cross-Origin Resource Sharing](https://developer.mozilla.org/de/docs/Web/HTTP/CORS)
verwenden. Hierbei wird am Webserver hinterlegt, von welchen Origins (Domains) dieser aufgerufen werden darf. Hier 
zB die Anleitung wie CORS unter Spring konfiguriert wird: https://spring.io/guides/gs/rest-service-cors/.
Der Browser fragt dann, bevor der eigentliche Request geschickt wird, welche Origins diese Seite aufrufen dürfen. 
Ist der Aufruf vom fremden Origin gestattet, schickt der Browser den ursprünglichen Request, ansonsten wird
der Aufruf blockiert.

Auch in diesem Projekt existieren Cross-Origin-Requests. Das Frontend wird von einem Hot-Reload-Server unter Port 
``3000`` ausgeliefert und das Spring-Boot-Backend läuft auf Port `8080`. Das heißt ohne 
zusätzliche Konfiguration werden die API-Aufrufe zum Backend vom Browser blockiert, weil eben Javascript das von
`localhost:3000` ausgeliefert wurde API-Aufrufe nach `localhost:8080` durchführen will.

Eine Möglichkeit dieses Problem zu lösen, ist ein CORS-Setting im Backend. Ein anderer etwas eleganterer Weg ist
eine Proxy-Einstellung im Hot-Reload-Server, der von `vite` gestartet wird. 

Hierzu gibt es folgenden Eintrag in der Datei `vite.config.ts`:

```
export default defineConfig({
...
server: {
    proxy: {
        "/api": "http://localhost:8080/",
    },
},
...
});
```

Wir generieren unseren API-Client so, dass alle Requests gegen `localhost:3000` geschickt werden. Gleichzeitig
weißen wir `localhost:3000` dazu an, dass alle Requests unter dem Pfad `/api/*` zum Server `http://localhost:8080` 
weitergeleitet werden. Damit erspart man sich das CORS-Mapping im Backend, da es für den Browser so aussieht als
gäbe es nur ein Backend (Origin). Natürlich muss sichergestellt werden, dass alle Backend-Aufrufe sich einen gemeinsamen
Kontext-Pfad teilen, so wie hier `/api`.  

## Verwendete Frameworks / Technologien

### Vue i18n
Um irgendwann die Sprache des Frontends zu ändern, schreiben wir die Übersetzungen nicht direkt in die Vue-Templates,
sondern in ein JSON File `de.json`. Danach referenzieren wir diese Übersetzungen mittels dem JSON Key und lassen
uns dadurch die Möglichkeit offen, die Sprache zur Laufzeit zu wechseln.

Die Konfiguration dafür liegt in dem File `workshop-ui/src/i18n.js`, dort werden die unterstützen
Sprachen festgelegt, die JSONs für die jeweiligen Sprachen liegen in `workshop-ui/src/locales`.

### PrimeFaces / PrimeVue
Da wir ein gewöhnliches Admin-Frontend bauen, nutzen wir eine OpenSource Kompenentenbibliothek.
Eine genaue Setup-Anleitung gibts es unter
https://www.primefaces.org/primevue/setup

Die Konfiguration dafür liegt in `workshop-ui/src/primeVue.ts`.

### Lodash
Fürs filtern der Angestellten wollen wir nicht nach jedem Buchstaben einen Request ins Backend abschicken.
Wir verwenden hier ein sogenanntes **Debouncing**, um nur nach X Sekunden wo sich der Input nicht mehr ändert einen Request abzuschicken.

Das schreiben wir nicht selber, sondern verwenden von Lodash, einer Utility Library die eine Menge 
nützlicher Funktionen mitliefert.

Hierfür gibt es keine Konfiguration, einfach per npm installieren, in die `tsconfig.app.json` als `type` definieren.

### Vue Router
Um zwischen den verschiedenen Seiten zu navigieren verwenden wir Vue Router, die Konfigration hierfür liegt in `workshop-ui/src/router`

## Projekt-Skelett selbst aufsetzen
Für ein ähnliches Setup gibt es die nachfolgende Anleitung:

### Allgemeines Setup
+ Um das Projekt zu bauen, muss eine aktuelle [nodejs-](https://nodejs.org/en/) installiert sein.
+ führe `npm init vue@latest` 

### PrimeFaces
Es wird PrimeFaces als Komponentenbibliothek verwendet. Eine genaue Setup-Anleitung gibts es unter
https://www.primefaces.org/primevue/setup

```
npm install primevue@^3.13.1 --save
npm install primeicons --save
```

