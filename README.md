# CS-320: Software Test Automation and Quality Assurance
## Portafolio Profesional de Ciencias de la Computación — Southern New Hampshire University (SNHU)
### Autor: David de la Cruz Contreras

---

## 📌 Panorama General y Contexto del Proyecto

Este repositorio constituye un artefacto formal para mi portafolio profesional en el programa de Ciencias de la Computación de **Southern New Hampshire University (SNHU)**. En este proyecto se demuestra el dominio técnico en pruebas de software, automatización y aseguramiento de la calidad (QA), integrando los entregables de:
* **Proyecto Final I**: Módulo de servicio de contactos en memoria (`ContactService`), modelo de dominio (`Contact`) y suite de pruebas unitarias exhaustivas en JUnit 5.
* **Proyecto Final II**: Informe de síntesis y reflexiones sobre metodologías de prueba, análisis de valores límite, cobertura de código y mentalidad profesional de aseguramiento de calidad.

### Escenario Corporativo: Grand Strand Systems
Como ingeniero de software en **Grand Strand Systems**, una firma especializada en servicios *back-end*, desarrollé y verifiqué los componentes de lógica de negocio para la aplicación móvil de un cliente corporativo. El desarrollo se implementó en Java bajo una arquitectura modular sin dependencias de bases de datos externas ni interfaces gráficas, priorizando el encapsulamiento defensivo, la inmutabilidad de identificadores únicos y el cumplimiento estricto de los contratos de servicio definidos en las especificaciones Javadoc.

---

## 🗂️ Estructura del Repositorio y Artefactos

```text
CS-320-Software-Test-Automation/
├── Contact.java                                           # Entidad de dominio Contacto con validación defensiva
├── ContactService.java                                    # Servicio de gestión en memoria con estructura HashMap O(1)
├── ContactTest.java                                       # Pruebas unitarias de modelo, límites y excepciones (JUnit 5)
├── ContactServiceTest.java                                # Pruebas unitarias del servicio y protección de estado (JUnit 5)
├── Informe_de_Resumen_y_Reflexiones_David_de_la_Cruz.docx # Informe formal Proyecto Final II (APA 7.ª edición)
├── 7-2 Proyecto final II.docx                             # Documento complementario de resumen y reflexiones
├── junit-platform-console-standalone.jar                  # Ejecutable de consola para ejecutar pruebas JUnit 5
├── .gitignore                                             # Exclusión de binarios y temporales
└── README.md                                              # Documentación y reflexión profesional del portafolio
```

---

## 🧠 Reflexión Profesional del Portafolio

A continuación se presentan las respuestas desarrolladas a profundidad sobre las tres preguntas fundamentales de la práctica de la ingeniería de software y el aseguramiento de la calidad:

### 1. ¿Cómo me aseguro de que mi código, programa o software sea funcional y seguro?

Para asegurar que el software sea **funcional**, adopto un enfoque disciplinado de verificación y validación (V&V) basado en requisitos medibles:
* **Partición de equivalencia y análisis de valores límite (BVA)**: Descompongo el dominio de entrada de cada parámetro en clases válidas y clases inválidas, probando no solo los casos representativos sino los extremos críticos ($n-1$, $n$ y $n+1$). En `ContactTest.java`, por ejemplo, el identificador `contactId` se prueba en su frontera permitida de 10 caracteres (`testContactIdBoundaryLengthSuccess`) y en su primera violación de 11 caracteres (`testContactIdTooLongThrowsException`). Para el teléfono, se valida la clase válida de 10 dígitos numéricos frente a casos de 9 dígitos, 11 dígitos y cadenas alfanuméricas (`12345abcde`).
* **Cobertura estructural exhaustiva**: Más allá de ejecutar líneas de código, audito la **cobertura de ramas (*branch coverage*)** con JaCoCo/EclEmma. Un 100 % de cobertura de ramas asegura que cada condición condicional compuesta (por ejemplo, evaluaciones booleanas con `||` o `&&`) sea recorrida en todas sus combinaciones de verdad y falsedad, eliminando caminos ciegos en el *bytecode*.
* **Aserciones semánticas precisas**: Utilizo `assertAll()` para evaluaciones holísticas de estado y `assertThrows()` para verificar que las precondiciones violadas detengan la ejecución de manera controlada.

Para garantizar que el software sea **seguro**, implemento el principio de defensa en profundidad (*defense-in-depth*) a nivel de código:
* **Programación defensiva y principio *Fail-Fast***: El sistema valida rigurosamente los datos en el punto de entrada (constructores y mutadores), eyectando excepciones tipificadas (`IllegalArgumentException`) antes de que un estado corrupto o nulo contamine la memoria del servidor.
* **Inmutabilidad y protección de identificadores**: Atributos críticos como `contactId` se definen como `final` a nivel de clase y carecen deliberadamente de métodos *setter*, impidiendo que un atacante o un error lógico altere la clave primaria de una entidad tras su instanciación.
* **Encapsulamiento del estado interno**: Para evitar que clientes externos modifiquen inadvertidamente las colecciones en memoria, los métodos de consulta como `getContacts()` retornan vistas inmodificables mediante `Collections.unmodifiableMap(contacts)`. En `ContactServiceTest.java`, se verifica formalmente con `assertThrows(UnsupportedOperationException.class, ...)` que cualquier intento de añadir o eliminar elementos eludiendo la API del servicio sea bloqueado inmediatamente por la JVM.
* **Validación sintáctica estricta**: Uso de expresiones regulares (`\d{10}`) y límites estrictos de longitud de caracteres para neutralizar inyecciones de datos maliciosos, desbordamientos de búfer o inconsistencias de codificación.

---

### 2. ¿Cómo interpreto las necesidades del usuario y las incorporo en un programa?

Interpretar las necesidades del usuario requiere transformar requerimientos comerciales y narrativos en **especificaciones técnicas no ambiguas y verificables**:
* **Descomposición de contratos y reglas de negocio**: Analizo las historias de usuario y requerimientos funcionales traduciéndolos en precondiciones (*requires*), invariantes de clase (*invariants*) y postcondiciones (*ensures*) documentadas en el Javadoc de la API. Si el usuario necesita gestionar contactos telefónicos de forma fiable, esa necesidad se traduce técnicamente en requerir exactamente 10 dígitos numéricos, campos obligatorios de nombre y apellido acotados para interfaces móviles, y claves únicas para evitar colisiones de registros.
* **Trazabilidad 1 a 1 entre requisitos y pruebas automatizadas**: Cada necesidad del usuario se modela en al menos dos pruebas automatizadas: una que confirma que el sistema cumple el objetivo esperado cuando se suministran datos correctos (*happy path*), y otra que valida que el sistema protege al usuario rechazando entradas inconsistentes con mensajes de error claros y predecibles.
* **Diseño centrado en la experiencia de integración (*Developer & User Experience*)**: Anticipando la integración con la interfaz de usuario móvil del cliente, proveo métodos alias intuitivos (como `updateNumber` y `getNumber` sincronizados con `setPhone` y `getPhone`) para facilitar el consumo de la API por parte de los desarrolladores frontend sin sacrificar la coherencia interna de los datos.

---

### 3. ¿Cómo abordo el diseño de software?

Abordo el diseño de software desde una perspectiva arquitectónica modular, mantenible y orientada a la calidad:
* **Separación de responsabilidades y principios SOLID**: Desacoplo estrictamente el modelo de datos (`Contact.java`) de la lógica de gestión y almacenamiento (`ContactService.java`), respetando el Principio de Responsabilidad Única (*Single Responsibility Principle*). La entidad `Contact` es responsable exclusiva de encapsular y validar los datos del contacto, mientras que `ContactService` se encarga de la gestión de la colección, la garantía de unicidad y el ciclo de vida de los registros en memoria.
* **Selección fundamentada de estructuras de datos y eficiencia algorítmica**: En lugar de recurrir a colecciones secuenciales lineales (`ArrayList` o `LinkedList`) que degradarían el rendimiento a una complejidad temporal de $O(n)$ en operaciones de búsqueda y borrado, seleccioné un `HashMap<String, Contact>` indexado por el ID único. Esto garantiza operaciones de tiempo constante promedio $O(1)$ para las funciones críticas de inserción (`addContact`), consulta (`getContact`), actualización (`update*`) y eliminación (`deleteContact`), asegurando un servicio altamente escalable y eficiente.
* **Reutilización y principio DRY (*Don't Repeat Yourself*)**: Centralizo la lógica de validación delegando desde el constructor directamente a los métodos mutadores (`setters`). Asimismo, factoricé la comprobación de existencia y nulidad en el método auxiliar privado `getContactOrThrow(contactId)`, eliminando la duplicación de código en todos los métodos de mutación.
* **Diseño para la testabilidad (*Design for Testability*)**: Concibo el código para que sea fácilmente aislable y verificable. En las pruebas unitarias aplico los principios **FIRST** (*Fast, Independent, Repeatable, Self-validating, Timely*), utilizando la anotación `@BeforeEach` en `setUp()` para garantizar que cada prueba se ejecute sobre un estado limpio y reproducible, sin dependencias cruzadas ni efectos secundarios entre pruebas.

---

## 📊 Métricas de Cobertura y Verificación de Calidad

La suite de pruebas fue evaluada con **JaCoCo** y ejecutada mediante la consola autónoma de **JUnit 5**, superando con creces el umbral mínimo del 80 % exigido por la rúbrica de SNHU:

| Clase de Producción | Métodos | Líneas Totales / Cubiertas | Cobertura de Líneas | Ramas Lógicas / Cubiertas | Cobertura de Ramas |
|:-------------------|:-------:|:--------------------------:|:-------------------:|:-------------------------:|:------------------:|
| `Contact.java` | 12 / 12 | 33 / 33 | **100 %** | 22 / 22 | **100 %** |
| `ContactService.java` | 11 / 11 | 33 / 33 | **100 %** | 12 / 12 | **100 %** |
| **Total Módulo** | **23 / 23** | **66 / 66** | **100 %** | **34 / 34** | **100 %** |

* **Pruebas ejecutadas**: 53 pruebas unitarias en `ContactTest` y `ContactServiceTest` (y 131 pruebas en la suite global de servicios).
* **Pruebas exitosas**: 53 / 53 (100 % éxito).
* **Fallos o errores**: 0.
* **Tiempo de ejecución**: ~56 milisegundos.

---

## 🚀 Instrucciones para Compilar y Ejecutar las Pruebas

Para compilar y verificar el proyecto desde cualquier terminal con Java (JDK 11+):

```bash
# 1. Clonar el repositorio
git clone https://github.com/dobock13/CS-320-Software-Test-Automation.git
cd CS-320-Software-Test-Automation

# 2. Compilar el código fuente y las clases de prueba en la carpeta 'bin'
mkdir -p bin
javac -cp junit-platform-console-standalone.jar *.java -d bin

# 3. Ejecutar la suite completa de pruebas JUnit 5
java -jar junit-platform-console-standalone.jar -cp bin --scan-class-path
```

---

## 📄 Documentos de Entrega Académica

El informe formal presentado para la supervisión y evaluación del curso se encuentra disponible en formato Word:
* **[`Informe_de_Resumen_y_Reflexiones_David_de_la_Cruz.docx`](Informe_de_Resumen_y_Reflexiones_David_de_la_Cruz.docx)**: Documento de 3 a 4 páginas estructurado conforme a las pautas de estilo APA 7.ª edición, interlineado doble, tipografía Times New Roman 12 pt y márgenes de 2.54 cm.
* **[`7-2 Proyecto final II.docx`](7-2%20Proyecto%20final%20II.docx)**: Documento complementario de entrega de Proyecto Final II.
