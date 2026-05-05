# 🛒 Liverpool Selenium Test

Framework de automatización de pruebas funcionales para el sitio web de
[Liverpool](https://www.liverpool.com.mx), construido con Selenium WebDriver y Java.

---

## 📋 Tabla de Contenidos

- [Descripción](#descripción)
- [Tecnologías](#tecnologías)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Ejecución de Pruebas](#ejecución-de-pruebas)
- [Caso de Prueba](#caso-de-prueba)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Autor](#autor)

---

## 📖 Descripción

Este proyecto implementa un framework de automatización de pruebas funcionales
sobre la página web de Liverpool. Utiliza Selenium WebDriver para interactuar
con el navegador y simular el comportamiento del usuario final, siguiendo el
patrón Page Object Model (POM).

---

## 🛠️ Tecnologías

| Herramienta        | Versión |
|--------------------|---------|
| Java               | 17      |
| Selenium WebDriver | 4.15.0  |
| TestNG             | 7.7.1   |
| WebDriverManager   | 5.6.3   |
| Gson               | 2.10.1  |
| Log4j 2            | 2.20.0  |
| Maven              | 3.x     |

---

## ✅ Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:

- [Java JDK 17+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Apache Maven 3+](https://maven.apache.org/download.cgi)
- Un navegador compatible (Chrome, Firefox, Edge, etc.)

> **Nota:** No es necesario descargar ni configurar manualmente el WebDriver.
> La clase `DriverManager` utiliza WebDriverManager para gestionar
> automáticamente la descarga y configuración del driver correspondiente.

---

## 📦 Instalación

1. Clona el repositorio:

```bash
git clone https://github.com/Kamed/liverpool-selenium-test.git
cd liverpool-selenium-test
```

2. Compila el proyecto y descarga las dependencias:

```bash
mvn compile
```

---

## ▶️ Ejecución de Pruebas

Para ejecutar la suite de pruebas definida en `testng.xml`:

```bash
mvn clean test
```

> El plugin Surefire ejecutará automáticamente la suite configurada en
> `src/test/resources/testng.xml`.

Para ejecutar una clase de prueba específica:

```bash
mvn clean test -Dtest=NombreDeLaClase
```

---

## 🧪 Caso de Prueba

### Flujo Completo de Checkout

Simula el recorrido completo de un usuario realizando una compra en Liverpool:

1. **Navegación por categorías** — Accede al sitio y navega a través de las
   categorías y subcategorías de productos disponibles.
2. **Selección de producto** — Selecciona un producto de la subcategoría elegida.
3. **Agregar al carrito** — Añade el producto seleccionado al carrito de compras.
4. **Validaciones** — Verifica que el carrito refleje correctamente los productos
   agregados (nombre, cantidad, precio, etc.).

---

## 📁 Estructura del Proyecto
liverpool-selenium-test/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/liverpool/automation/
│   │           ├── config/       # DriverManager: configuración del WebDriver
│   │           ├── pages/        # Page Objects
│   │           └── utils/        # Utilidades generales
│   └── test/
│       ├── java/
│       │   └── com/liverpool/automation/
│       │       └── tests/        # Clases de prueba (TestNG)
│       └── resources/
│           └── testng.xml        # Suite de pruebas
├── pom.xml
└── README.md

---

## 👤 Autor

**Kamed**  
GitHub: [@Kamed](https://github.com/Kamed)

---

## 📄 Licencia

Este proyecto es de uso personal/educativo. Consulta al autor para más detalles.
