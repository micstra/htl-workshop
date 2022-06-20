Workshop-Environment
====================

Dieses Projekt stellt einen lokalen Postgres-Datenbankserver mittels [docker](https://docs.docker.com/get-started/) 
beziehungsweise [docker-compose](https://docs.docker.com/compose/) zur Verfügung. 
Docker ermöglicht die plattformunabhängige Bereitstellung von Anwendungen. Dazu wird [Containervirtualisierung](https://de.wikipedia.org/wiki/Containervirtualisierung) verwendet. 
Docker-Compose ist ein Hilfswerkzeug, das die Verwaltung mehrerer Container erleichtert. 

Verwendung
-------------------

Um die Datenbank zu starten, kann folgender Befehl ausgeführt werden: 

`docker compose up` beziehungsweise mit dem älteren Docker-Client `docker-compose up`

Um den Container im Hintergrund zu starten, kann die Option `-d` verwendet werden. 

`docker compose up -d`

Mittels `docker ps` werden alle aktuell laufenden Container angezeigt:

```
docker ps
CONTAINER ID   IMAGE           COMMAND                  CREATED        STATUS             PORTS                                            NAMES
d0de6a319135   postgres:14.2   "docker-entrypoint.s…"   2 days ago     Up 10 seconds      0.0.0.0:5432->5432/tcp, :::5432->5432/tcp        workshop-db
```

Um einen Container zu stoppen kann `docker stop` verwendet werden. `docker stop` erhält als Argument entweder den 
Namen oder die ID des Containers.

`docker stop workshop-db` oder `docker stop d0de6a319135`

Compose-Files
------------------

Docker-Compose verwendet zur Automatisierung sogenannte Compose-Files. Das sind Dateien im `.yaml`-Format, die
beschreiben welche Container gestartet werden.

Für das Labor wurde folgendes Compose-File verwendet: 

```
services: (1)
  storage: (2)
    container_name: workshop-db (3)
    image: postgres:14.2 (4)
    environment: (5)
      POSTGRES_USER: root
      POSTGRES_PASSWORD: root
    ports: (6)
      - "5432:5432"
    volumes: (7)
      - postgres-volume:/var/lib/postgresql/
volumes: (8)
  postgres-volume: {}
```

1. `services` Die Services-Sektion beschreibt welche Container gestartet werden. In diesem Fall gibt es nur einen Service
mit dem Namen `storage`.
2. `storage` Hier beginnt die Deklaration des Dienstes. 
3. `container_name` Der Containername des Dienstes wenn er ausgeführt wird. Der Container hat in diesem Fall den Namen
`workshop-db`
4. `image` Spezifiziert das docker-image das ausgeführt werden soll. In diesem Fall handelt es sich um ein `image` einer
Postgresql-Datenbank. Docker lädt automatisch `images` von sogenannten Registries. Die Standard-Registry für docker
ist [Docker-Hub](https://hub.docker.com/_/postgres)
5. `environment` Die Umgebungsvariablen, die im ausgeführten Container verfügbar sein sollen. Die Postgresdatenbank sucht
nach diesen Variablen, wenn noch kein Datenbanknutzer konfiguriert ist. Hier wird der Datenbankzugang konfiguriert.
6. `ports` Beschreibt welche Container-Ports am Host erreichbar sein sollen. Nachdem sich der Postgres-Server auf Port
`5432` startet, muss dieser auch zum Host-System durchgereicht werden.
7. `volume-mounts` Dieser Abschnitt beschreibt die sogenannten Volume-Mounts. Normalerweise sind in einem laufenden
Container alle Daten transient. Das heißt alle Änderungen am Container-Filesystem passieren nur im Arbeitsspeicher.
Bei einem Neustart des Containers befindet er sich wieder in seinem Ursprungszustand. Im Falle 
einer Datenbank ist dieses Verhalten nicht wünschenswert. Zu diesem Zweck gibt es `volumes` beziehungsweise `volume-mounts`.
Ein `volume` definiert einen persistenten Speicher. Ein `volume-mount` deklariert welcher Teil eines Dateisystems
persistent sein soll. 
8. `volumes` Hier befindet sich die Deklaration der Volumes. Es wird ein Volume mit dem Namen `postgres-volume`
angelegt. Das Volume benötigt keine zusätzliche Konfiguration, daher wird hier nur ein leeres Objekt angegeben `{}`.
