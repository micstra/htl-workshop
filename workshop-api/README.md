# Workshop-API

Dieses Projekt definiert die HTTP-Schnittstelle zwischen Frontend und Backend. 

Die Schnittstelle wird mittels des [OpenApi-3-Standards](https://swagger.io/specification/) dokumentiert.

Hier ein Beispiel für die Beschreibung eines Endpoints: 

```
/employees:
    description: Receive a list of all employees
    get:
      operationId: findAllEmployees
      tags:
        - employee
      responses:
        '200':
          description: An array of all exisiting employees
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: "#/components/schemas/ReadEmployeeDto"
```

Eine solche Dokumentation hat den Vorteil, dass diese sowohl für Menschen lesbar ist, als auch sehr einfach maschinell
verarbeitet werden kann. 

Es werden Code-Generatoren eingesetzt, um Server und Client aus dieser API-Spezifikation automatisch zu
erzeugen. Es wird der [OpenApi-Generator](https://openapi-generator.tech/) verwendet. Hierfür wird 
das [OpenApi-Generator-Gradle-Plugin](https://github.com/OpenAPITools/openapi-generator/tree/master/modules/openapi-generator-gradle-plugin).
benötigt.

Prinzipiell ist es auch möglich, aus einem bereits existierenden Server-Code eine OpenApi-Spezifikation zu erzeugen. 
Es gibt also zwei Varianten der Code/Spezifikation-Erzeugung:

- Wird aus einer Spezifikation der Server-Code erzeugt, so spricht man von `Contract-First`.

- Wird aus Server-Code die Spezifikation erzeugt, so spricht man von `Code-First`.


