# Guía de Estudio — Práctica Biblioteca (Java y Spring Boot)

Esta guía documenta el desarrollo completo de la actividad: qué se hizo en cada etapa, cómo funciona el código y por qué se tomó cada decisión en relación con los parámetros solicitados.

El proyecto es una aplicación Spring Boot (Java 21) creada con Spring Initializr. Los archivos agregados son:

- `src/main/java/com/example/biblioteca/Practica.java`
- `src/main/java/com/example/biblioteca/Libro.java`
- `src/main/java/com/example/biblioteca/BibliotecaController.java`

> Nota: la consigna menciona el paquete `com.ejemplo.biblioteca`, pero el proyecto generado usa `com.example.biblioteca`. Se mantuvo el paquete existente para no romper la estructura del proyecto.

---

## Etapa 1: Java puro (`Practica.java`)

La clase `Practica` contiene el método `main`, que es el punto de entrada de cualquier programa en Java. Al ejecutarla sin levantar Spring, se practican los fundamentos de la lengua.

### 1.1 Variables

```java
String titulo = "Cien años de soledad";
int añoPublicacion = 1967;
boolean disponible = true;
```

Se declaran los tres tipos pedidos:

- `String` guarda texto (secuencia de caracteres entre comillas).
- `int` guarda números enteros, adecuado para un año porque no necesita decimales.
- `boolean` solo admite `true` o `false`, ideal para un estado binario como "disponible / no disponible".

Se imprimen con `System.out.println`, que muestra el valor en consola y agrega un salto de línea. La concatenación con `+` une el texto fijo con el valor de cada variable.

### 1.2 Control de flujo con if/else

```java
if (añoPublicacion < 2000) {
    System.out.println("Clásico");
} else {
    System.out.println("Contemporáneo");
}
```

La condición evalúa una expresión booleana. Si el año de publicación es menor a 2000, el libro se clasifica como "Clásico"; si no, se ejecuta la rama `else` y se imprime "Contemporáneo". Se probó con dos años distintos (1967 y 2015) para observar ambas ramas en acción: la primera impresión fue "Clásico" y la segunda "Contemporáneo".

### 1.3 Ciclo `for`

```java
for (int i = 1; i <= 5; i++) {
    System.out.println("Ejemplar " + i);
}
```

El `for` tiene tres partes: inicialización (`int i = 1`), condición de continuidad (`i <= 5`) e incremento (`i++`). En cada vuelta se imprime "Ejemplar" seguido del número actual, obteniendo de la Ejemplar 1 a la Ejemplar 5.

### 1.4 Método estático

```java
public static int calcularMulta(int diasRetraso) {
    return diasRetraso * 500;
}
```

- `public`: puede invocarse desde cualquier parte del programa.
- `static`: pertenece a la clase, no a una instancia; se llama sin crear un objeto (`Practica.calcularMulta(...)` o simplemente `calcularMulta(...)` dentro de la misma clase).
- `int` (antes del nombre): es el tipo de dato que el método devuelve.
- El parámetro `diasRetraso` recibe el valor de entrada y `return` entrega el resultado calculado.

**Registro de las 3 llamadas:**

| Días de retraso | Llamada | Resultado |
| :--- | :--- | :--- |
| 3 | `calcularMulta(3)` | **1500** |
| 7 | `calcularMulta(7)` | **3500** |
| 30 | `calcularMulta(30)` | **15000** |

Salida en consola:

```
Multa 3 días: 1500
Multa 7 días: 3500
Multa 30 días: 15000
```

La multa es simplemente los días multiplicados por 500, el mismo criterio que después se reutiliza en el endpoint `/prestamo/{dias}`.

---

## Etapa 2: La clase `Libro.java`

La clase `Libro` modela un libro de la biblioteca siguiendo los principios de programación orientada a objetos.

### Atributos privados

```java
private String titulo;
private String autor;
private int año;
private boolean disponible;
```

La palabra `private` significa que estos atributos solo son accesibles desde dentro de la propia clase `Libro`. Ninguna otra clase puede leerlos ni modificarlos directamente. Esto se llama **encapsulamiento** y es el motivo de que existan los getters (ver abajo). El atributo `disponible` corresponde al reto opcional 2; se incluye desde el principio con su getter `estaDsiponible()`.

### Constructor

```java
public Libro(String titulo, String autor, int año) {
    this.titulo = titulo;
    this.autor = autor;
    this.año = año;
    this.disponible = true;
}
```

El constructor es un método especial con el mismo nombre de la clase y sin tipo de retorno. Se ejecuta automáticamente al usar la palabra `new`. La palabra `this` distingue el atributo del parámetro cuando comparten nombre: `this.titulo` es el atributo de la instancia y `titulo` (sin `this`) es el valor que llega por parámetro. `disponible` se inicializa en `true` por defecto.

### Getters

```java
public String getTitulo() { return titulo; }
public String getAutor() { return autor; }
public int getaño() { return año; }
public boolean estaDsiponible() { return disponible; }
```

Los getters son métodos `public` que devuelven el valor de un atributo `private`. Son la única puerta de salida legítima del encapsulado. Para booleanos, la convención de Java usa `isXxx()` en lugar de `getXxx()`.

### Método `esAntiguo()`

```java
public boolean esAntiguo() {
    return año < 2000;
}
```

Devuelve directamente la expresión booleana: si el año es anterior a 2000 devuelve `true`, si no `false`. Reutiliza el mismo criterio del if/else de la Etapa 1, pero encapsulado en un método reutilizable.

### Método `resumen()`

```java
public String resumen() {
    return titulo + " de " + autor + " (" + año + ")";
}
```

Concatena los atributos con texto fijo para producir un resumen legible, por ejemplo: `El señor de los anillos de J.R.R. Tolkien (1954)`.

---

## Etapa 3: Crear y usar objetos (en el `main` de `Practica`)

```java
Libro libro1 = new Libro("El señor de los anillos", "J.R.R. Tolkien", 1954);
Libro libro2 = new Libro("Harry Potter y la piedra filosofal", "J.K. Rowling", 1997);
Libro libro3 = new Libro("El código da Vinci", "Dan Brown", 2003);
```

Se instanciaron 3 objetos con datos reales: dos anteriores al 2000 (1954 y 1997) y uno posterior (2003), tal como pide la consigna. Luego se imprimieron `resumen()` y `esAntiguo()` de cada uno:

```
El señor de los anillos de J.R.R. Tolkien (1954)
Harry Potter y la piedra filosofal de J.K. Rowling (1997)
El código da Vinci de Dan Brown (2003)
¿Antiguo? true
¿Antiguo? true
¿Antiguo? false
```

### Intento de acceso directo al atributo

La consigna pide intentar `libro1.titulo = "Otro";` desde el `main`. **Esta línea no puede incluirse en el código compilable porque el programa dejaría de compilar** (el archivo no pasaría la etapa de compilación y no se podría ejecutar nada).

**Mensaje de error del IDE:**

```
titulo tiene acceso privado en Libro
```

(En inglés: `titulo has private access in Libro`)

**Por qué ocurre:** el atributo `titulo` está declarado con `private` en la clase `Libro`, y el intento se realiza desde otra clase (`Practica`). El compilador de Java aplica las reglas de acceso en tiempo de compilación, no en tiempo de ejecución: detecta la violación y detiene la construcción del `.class`. La única forma correcta de modificar un atributo privado desde fuera es usar un setter (que en esta práctica no se pidió) o bien leerlo con `getTitulo()`.

---

## Etapa 4: Endpoints REST (`BibliotecaController.java`)

La clase usa la anotación `@RestController`, que combina `@Controller` (acepta peticiones web) y `@ResponseBody` (devuelve datos, no vistas HTML). Cada método con `@GetMapping` responde a una petición HTTP GET en la ruta indicada. Spring se encarga de convertir el valor de retorno a JSON (cuando es un objeto) o a texto plano (cuando es un `String`).

Los 3 libros se crean una sola vez como atributo de la clase y se reutilizan en todos los endpoints.

### `GET /libro` — devuelve un objeto `Libro`

```java
@GetMapping("/libro")
public Libro libro() {
    return libros.get(0);
}
```

Spring serializa el objeto con la librería Jackson y produce JSON:

```json
{"titulo":"El señor de los anillos","autor":"J.R.R. Tolkien","año":1954,"disponible":true}
```

### `GET /libros` — devuelve una lista

```java
@GetMapping("/libros")
public List<Libro> libros() {
    return libros;
}
```

Una `List<Libro>` se serializa como arreglo JSON, un objeto por cada elemento.

### `GET /libro/resumen` — devuelve un String

```java
@GetMapping("/libro/resumen")
public String resumen() {
    return libros.get(0).resumen();
}
```

Como el tipo de retorno es `String`, Spring lo devuelve como texto plano (no JSON entre comillas). Aquí se conecta la Etapa 2 con la Etapa 4: el método `resumen()` de la clase `Libro` es el que produce el texto.

### `GET /prestamo/{dias}` — lógica de negocio con parámetro de ruta

```java
@GetMapping("/prestamo/{dias}")
public String prestamo(@PathVariable int dias) {
    if (dias <= 14) {
        return "Préstamo vigente";
    }
    int multa = (dias - 14) * 500;
    return "Préstamo vencido. Multa: $" + multa;
}
```

- `{dias}` es un **placeholder** en la ruta; `@PathVariable int dias` le dice a Spring que extraiga ese segmento de la URL y lo convierta a `int`.
- Préstamo base: 14 días. Si `dias <= 14` el préstamo sigue vigente.
- Si `dias > 14`, la multa es $500 por cada día **adicional** (por eso se resta 14 primero). Ejemplo: 20 días → 6 días de excedente → $3000. La cuenta de $500 por día es la misma de `calcularMulta` de la Etapa 1.

### `GET /contar/{n}` — reto opcional 1

```java
@GetMapping("/contar/{n}")
public String contar(@PathVariable int n) {
    StringBuilder cadena = new StringBuilder();
    for (int i = 1; i <= n; i++) {
        cadena.append(i).append(" ");
    }
    return cadena.toString();
}
```

Se usa un `for` para recorrer de 1 a `n` y un `StringBuilder` para acumular los números separados por espacio. Se eligió `StringBuilder` sobre concatenar con `+` dentro del loop porque es la forma eficiente de construir strings en Java (la concatenación repetida con `+` crea un objeto nuevo en cada vuelta).

---

## Etapa 5: Pruebas y Registro

La aplicación se ejecutó con `./mvnw spring-boot:run` y se realizaron las peticiones con `curl`. **Observación importante:** en la máquina de prueba el puerto 8080 ya estaba ocupado por otro servicio, por lo que la aplicación se levantó en el puerto 8081 (`--server.port=8081`). Adiconal se cambio el puerto en application.properties  a  server.port=8081 ya que tengo el puerto 8080 ocupado con codeserver.

| Petición (GET) | Código HTTP | Respuesta Recibida |
| :--- | :--- | :--- |
| `/libro` | 200 | `{"titulo":"El señor de los anillos","autor":"J.R.R. Tolkien","año":1954,"disponible":true}` |
| `/libros` | 200 | `[{"titulo":"El señor de los anillos","autor":"J.R.R. Tolkien","año":1954,"disponible":true},{"titulo":"Harry Potter y la piedra filosofal","autor":"J.K. Rowling","año":1997,"disponible":true},{"titulo":"El código da Vinci","autor":"Dan Brown","año":2003,"disponible":true}]` |
| `/libro/resumen` | 200 | `El señor de los anillos de J.R.R. Tolkien (1954)` |
| `/prestamo/10` | 200 | `Préstamo vigente` |
| `/prestamo/14` | 200 | `Préstamo vigente` |
| `/prestamo/20` | 200 | `Préstamo vencido. Multa: $3000` |
| `/prestamo/abc` | 400 | `{"status":400,"error":"Bad Request","message":"Method parameter 'dias': Failed to convert value of type 'java.lang.String' to required type 'int'; For input string: \"abc\""}` |

### Respuesta al /prestamo/abc

El código HTTP recibido fue **400 Bad Request**. Ocurre porque `@PathVariable int dias` le pide a Spring que convierta el texto `"abc"` de la URL a un número entero. Spring usa internamente `Integer.parseInt("abc")`, que lanza `NumberFormatException`; Spring captura esa excepción y responde con 400 Bad Request, el código HTTP que indica que la petición está mal formada (el cliente envió datos inválidos). El endpoint nunca llegó a ejecutarse, porque la falla ocurrió antes, al resolver el argumento del método.

---

## Etapa 6: Cuestionario de Comprensión

**1. ¿Cuál es la diferencia entre una clase y un objeto?**

Respuesta: **b)** La clase es el molde y el objeto es una unidad creada a partir de ese molde.

La clase `Libro` define qué datos y comportamientos tiene un libro; cada `new Libro(...)` crea un objeto concreto con sus propios valores de título, autor y año.

**2. ¿Qué hace la palabra `new` al escribir `new Libro(...)`?**

Respuesta: **b)** Crea un objeto y ejecuta el constructor.

`new` reserva memoria para la instancia y invoca el constructor para inicializar sus atributos.

**3. ¿Cuál es el tipo de dato más adecuado para guardar un precio con decimales?**

Respuesta: **c)** `double`.

`int` solo guarda enteros, `boolean` valores verdadero/falso y `char` un solo carácter. `double` (o `float`) soporta números con decimales.

**4. ¿Cómo se comparan correctamente dos textos (`String`) en Java?**

Respuesta: **c)** `texto1.equals(texto2)`.

El operador `==` en objetos compara referencias de memoria (si apuntan al mismo objeto), no el contenido del texto. `equals()` compara el contenido real de los caracteres.

**5. ¿Qué condición debe cumplir un método para que Jackson lo incluya automáticamente como propiedad en el JSON?**

Debe ser un método getter con la convención de Java: público, sin parámetros, cuyo nombre empiece con `get` seguido del nombre de la propiedad en mayúscula (`getTitulo()` → `titulo`) o, si devuelve `boolean`, puede empezar con `is` (`estaDsiponible()` → `disponible`). Por eso `esAntiguo()` no aparece en el JSON: el nombre no sigue la convención `getXxx`/`isXxx` (empieza con `es`, que es español), entonces Jackson no lo reconoce como getter de una propiedad.

**6. ¿Por qué los atributos de la clase `Libro` se declaran como `private` y se acceden mediante getters?**

Porque el encapsulamiento protege el estado del objeto. Si los atributos fueran públicos, cualquier clase podría ponerles cualquier valor (por ejemplo, un año negativo o un título vacío) y sería imposible controlar o auditar quién modifica los datos. Al hacerlos `private`, el único punto de acceso es a través de métodos públicos (getters, y setters si existieran), lo que permite centralizar la validación, ocultar el detalle interno y cambiar la implementación de la clase sin romper a quienes la usan. La consigna misma lo demuestra: al intentar asignar `libro1.titulo` directamente, el compilador lo rechaza.

---

## Retos Opcionales

**1. Ciclo en endpoint** — Implementado como `GET /contar/{n}` (ver Etapa 4). Ejemplo real:

```
GET /contar/5 → 200 → "1 2 3 4 5 "
```

**2. Atributo adicional `disponible`** — Agregado a `Libro` con su getter `estaDsiponible()` y valor por defecto `true` en el constructor. Como `/libro` y `/libros` devuelven los objetos completos, el JSON resultante ahora incluye `"disponible":true` en cada libro (visible en la tabla de la Etapa 5).

---

## Cómo ejecutar el proyecto

```bash
./mvnw spring-boot:run
```

La aplicación queda disponible en `http://localhost:8080` (o en el puerto indicado con `--server.port`). Para ejecutar solo la práctica de Java sin Spring:

```bash
./mvnw -q compile
java -cp target/classes com.example.biblioteca.Practica
```
