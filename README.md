# VetTurno 🐾
---

## 🐶 Historia de Veterinaria Huellitas
Doña Marta abrió **Veterinaria Huellitas** hace seis años en un barrio donde conoce por su nombre a muchas familias y a sus 
mascotas. 
Ella atiende la clínica con el **doctor Andrés** y con **Paula**, quien recibe llamadas, responde mensajes y organiza las citas.
La agenda todavía vive entre un cuaderno y conversaciones de WhatsApp. Cuando el día está ocupado, Paula puede reservar dos 
consultas para el mismo veterinario a la misma hora, escribir mal el nombre de una mascota o perder el teléfono de su 
responsable. 
El problema no es falta de cuidado: **es que la información está dispersa**.
Para solucionar esta situación nace **VetTurno**, una API que permita registrar responsables, mascotas y veterinarios, agendar citas sin cruces 
de horario y consultar la agenda con información clara y persistente.

---
## 📈 Alcance
Gestión de propietarios: registrar y consultar propietarios de las mascotas.
Gestión de mascotas: almacenar cada mascota.
Gestión de veterinarios: administrar veterinarios.
Gestión de citas: registrar citas con  mascota y veterinario asignado.
Autenticación y autorización: controlar el acceso mediante usuarios y roles.
Persistencia de datos: almacenar la información en una base de datos MySQL.
API REST: permitir la comunicación entre el cliente y el backend mediante solicitudes HTTP.

## 💻 Tecnologías

- ☕ **Java 17** — Lenguaje principal.
- 🌱 **Spring Boot** — Framework para backend.
- 📦 **Maven** — Gestión de dependencias.
- 🗄️ **MySQL** — Sistema gestor de base de datos.
- 🔗 **JPA** — Persistencia y comunicación con la base de datos.
- 🔐 **JWT (JSON Web Token)** — Autenticación y autorización de usuarios.
- 📖 **Swagger / OpenAPI** — Documentación y prueba de endpoints.

---

# 🚀 Instalación y ejecución del proyecto

## Paso 1. Descargar el proyecto

Primero, obtén una copia del repositorio en tu máquina local. Puedes hacerlo de dos formas:

- **Clonar el repositorio:** ejecuta el siguiente comando en una terminal, reemplazando la URL por la del repositorio:

  ```bash
  git clone <URL_DEL_REPOSITORIO>
- **Descargar el ZIP:** descarga el proyecto desde GitHub, descomprime el archivo y guarda la carpeta en una ubicación de tu preferencia y que no tengas que navegar por muchos directorios.

## Paso 2. Abrir el proyecto en un IDE
Abre la carpeta raíz del proyecto con un IDE. Se recomienda utilizar IntelliJ IDEA, aunque puedes elegir el entorno de desarrollo que tu prefieras.

Asegúrate de abrir la carpeta correcta: debe ser aquella en la que se encuentran archivos como pom.xml, mvnw y la carpeta src/, no una carpeta externa que contenga otra carpeta con el mismo nombre.

La estructura debería verse similar a la siguiente:

```
vetturno/
├── src/
├── .mvn/
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```
**Importante:** espera a que Maven descargue y configure todas las dependencias del proyecto antes de intentar ejecutarlo. Si las dependencias no se cargan correctamente, podrías encontrar errores de compilación o ejecución.

## Paso 3. Crear la base de datos en MySQL
Asegúrate de tener MySQL instalado y de que el servidor esté en ejecución.

Abre MySQL Workbench o una terminal de MySQL y ejecuta el siguiente comando para crear la base de datos:
```
sql
CREATE DATABASE vetturno;
```
La base de datos debe llamarse vetturno para la conexión.

## Paso 4. Configurar las variables de conexión
Abre el archivo application.properties y verifica que tenga la siguiente configuración:
```
spring.application.name=vetturno

spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/vetturno}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

# Clave secreta para la autenticación mediante JWT
jwt.secretKey=${JWT_SECRET_KEY}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
Configura las variables de entorno según el properties.
En este caso se estableció:
- DB_USER Usuario de MySQL.
- DB_PASSWORD Contraseña usuario de MySQL.
- JWT_SECRET_KEY

Importante:

Reemplaza los valores de ejemplo por los correspondientes a tu entorno.
Establece una clave secreta segura para JWT_SECRET_KEY.
No publiques contraseñas ni claves secretas en el repositorio de Git.

## Paso 5. Ejecutar el proyecto
Una vez que hayas configurado la base de datos, las variables de entorno y las dependencias de Maven, abre una terminal en la carpeta raíz del proyecto.

En Windows:
```
.\mvnw.cmd spring-boot:run
```
También puedes ejecutar la clase principal de Spring Boot directamente desde tu IDE.

Si la configuración es correcta, Maven iniciará la aplicación y podrás consultar los mensajes de inicio en la consola.

⚠️ Recomendaciones
Verifica que MySQL esté activo.
Comprueba que el nombre de la base de datos, el usuario y la contraseña sean correctos.
Asegúrate de que Maven haya descargado todas las dependencias.
Si la aplicación no inicia, revisa los mensajes de error que aparecen en la consola.
---
---
## 📊Modelo
De esta forma queda nuestra bd:
<img width="536" height="365" alt="Modelo relacional BD" src="https://github.com/user-attachments/assets/b86965ed-05d9-40c2-b465-4a14668ef83c" />

## Descripción de las tablas

### Tabla `propietarios`

| Campo | Tipo de dato | Restricción |
|-------|--------------|-------------|
| `id` | BIGINT | PK, autoincremental |
| `nombre` | VARCHAR(255) | Admite NULL |
| `telefono` | VARCHAR(255) | Admite NULL |
| `email` | VARCHAR(255) | Admite NULL |


### Tabla `mascotas`

| Campo | Tipo de dato | Restricción |
|-------|--------------|-------------|
| `id` | BIGINT | PK, autoincremental |
| `nombre` | VARCHAR(255) | Admite NULL |
| `especie` | VARCHAR(255) | Admite NULL |
| `raza` | VARCHAR(255) | Admite NULL |
| `propietario_id` | BIGINT | FK → `propietarios.id` |


### Tabla `veterinarios`

| Campo | Tipo de dato | Restricción |
|-------|--------------|-------------|
| `id` | BIGINT | PK, autoincremental |
| `nombre` | VARCHAR(255) | Admite NULL |
| `especilidad` | VARCHAR(255) | Admite NULL |


### Tabla `citas`

| Campo | Tipo de dato | Restricción |
|-------|--------------|-------------|
| `id` | BIGINT | PK, autoincremental |
| `fecha_hora` | DATETIME(6) | Admite NULL |
| `motivo` | VARCHAR(255) | Admite NULL |
| `mascota_id` | BIGINT | FK → `mascotas.id` |
| `veterinario_id` | BIGINT | FK → `veterinarios.id` |


### Tabla `usuarios`

| Campo | Tipo de dato | Restricción |
|-------|--------------|-------------|
| `id` | BIGINT | PK, autoincremental |
| `email` | VARCHAR(255) | UNIQUE |
| `password` | VARCHAR(255) | Admite NULL |
| `rol` | ENUM | Valores: `ADMIN`, `USER` |
## 📡 Endpoints
### Veterinarios

| Método | Ruta | Descripción | Body requerido |
|--------|------|-------------|----------------|
| GET | `/api/veterinarios` | Listar todos los veterinarios | — |
| POST | `/api/veterinarios` | Crear un veterinario | `nombre` (string), `especilidad` (string) |

### Propietarios

| Método | Ruta | Descripción | Body requerido |
|--------|------|-------------|----------------|
| GET | `/api/propietarios` | Listar todos los propietarios | — |
| POST | `/api/propietarios` | Crear un propietario | `nombre` (string), `telefono` (string), `email` (string)|


### Mascotas

| Método | Ruta | Descripción | Body requerido |
|--------|------|-------------|----------------|
| GET | `/api/mascotas` | Listar todas las mascotas | — |
| POST | `/api/mascotas` | Crear una mascota | `nombre` (string), `especie` (string), `raza` (string), `propietario_id` (long) |
| GET | `/api/mascotas/propietario/{idPropietario}` | Listar mascotas de un propietario | — |

### Citas

| Método | Ruta | Descripción | Body requerido |
|--------|------|-------------|----------------|
| GET | `/api/citas` | Listar todas las citas | — |
| POST | `/api/citas` | Crear una cita veterinaria | `fecha_hora` (datetime), `motivo` (string), `mascota_id` (long), `veterinario_id` (long) |
| GET | `/api/citas/veterinario/{idVeterinario}` | Listar citas de un veterinario | — |

### Autenticación

| Método | Ruta | Descripción | Body requerido |
|--------|------|-------------|----------------|
| POST | `/api/autenticacion/registrar` | Registrar un usuario | `email` (string), `password` (string), `rol` (enum) |
| POST | `/api/autenticacion/login` | Iniciar sesión | `email` (string), `password` (string) |

**Roles permitidos:** `ADMIN`, `USER`.
## Permisos de acceso a la API

### Acceso público

| Método | Ruta | Acceso |
|--------|------|--------|
| GET | `/swagger-ui/**` | Público |
| POST | `/api/autenticacion/registrar` | Público |
| POST | `/api/autenticacion/login` | Público |

### Acceso para usuarios y administradores

| Método | Ruta | Acceso |
|--------|------|--------|
| GET | `/api/propietarios` | USER, ADMIN |
| POST | `/api/propietarios` | USER, ADMIN |
| GET | `/api/mascotas` | USER, ADMIN |
| POST | `/api/mascotas` | USER, ADMIN |
| GET | `/api/veterinarios` | USER, ADMIN |
| POST | `/api/veterinarios` | ADMIN |
| GET | `/api/citas` | USER, ADMIN |
| POST | `/api/citas` | USER, ADMIN |
| GET | `/api/citas/veterinario/{idVeterinario}` | USER, ADMIN |



---
# 🔄 Flujo sistema

## 1. Usuario normal

### Paso 1. Registrar un usuario

Realizar una petición al endpoint de registro para crear un usuario nuevo.

- Utilizar un correo electrónico válido.
- La contraseña debe tener mínimo 8 caracteres.

Una vez registrado, copia y guarda el token JWT devuelto por la API, ya que se utilizará para las siguientes peticiones que requieran autenticación.


### Paso 2. Crear un propietario POST

Da clic en el botón authorize y saldrá una ventana emergente, donde deberás colocar el token retornado.
Enviar una petición para crear un propietario, asegurándose de que ningún campo esté vacío.

**Campos requeridos:**
```
{
  "nombre": "string",
  "telefono": "string",
  "email": "user@example.com"
}
```

**Verificación:**

1. Comprobar que la API responda correctamente y devuelva los datos del propietario creado.
2. Consultar directamente la base de datos para verificar que el propietario se haya almacenado correctamente.
3. Realizar una petición `GET` al endpoint de propietarios y confirmar que el nuevo registro aparezca en la respuesta.
4. Guardar `id` del propietario creado, este se necesitará para crear una mascota.

### Paso 3. Crear una mascota POST

Enviar una petición para crear una mascota.

**Campos requeridos:**
```
{
  "nombre": "string",
  "especie": "string",
  "raza": "string",
  "propietarioId": 0
}
```

Utilizar el `id` del propietario obtenido en el paso anterior.

**Verificación:**

1. Comprobar que la API retorne la mascota.
2. Consultar la base de datos para verificar que la mascota se haya guardado correctamente y que `propietario_id` corresponda al propietario registrado.
3. Realizar una petición `GET` al endpoint de mascotas y confirmar que el registro aparezca en la respuesta.
4. Guardar el `id` de la mascota creada,  este se necesitará para crear una cita.

### Paso 4. Buscar mascotas por propietario

Realizar una petición al endpoint que permite consultar las mascotas asociadas a un propietario.

- Utilizar el `id` del propietario creado anteriormente.
- Verificar que la mascota registrada aparezca.

### Paso 5. Consultar los veterinarios disponibles

Realizar una petición `GET` al endpoint de veterinarios para consultar los registros disponibles.

- Verificar que la API devuelva los veterinarios registrados.
- Seleccionar un veterinario y guardar su `id`, ya que se necesitará para crear una cita.

Si no existen veterinarios, primero será necesario registrar uno mediante el flujo de administrador.

### Paso 6. Crear una cita

Enviar una petición para agendar una cita utilizando los identificadores válidos de la mascota y del veterinario seleccionados.
```
**Campos requeridos:**
{
  "motivo": "string",
  "idMascota": 0,
  "idVeterinario": 0,
  "fechaHora": "2026-10-10T08:22:31.803Z"
}
```
(datetime): fecha y hora futuras.


**Verificación:**

1. Comprobar que la API confirme la creación de la cita.
2. Consultar directamente la base de datos para verificar que la cita se haya almacenado correctamente y que los identificadores de la mascota y del veterinario correspondan a los registros existentes.
3. Realizar una petición `GET` al endpoint de citas y confirmar que la cita aparezca en los resultados.

### Paso 7. Buscar citas por veterinario

Realizar una petición al endpoint que permite consultar las citas que tiene un veterinario.

- Utilizar el `id` del veterinario seleccionado anteriormente.

---

## 2. Flujo de administrador

### Paso 1. Registrar un usuario nuevo

- Utilizar un correo electrónico válido.
- La contraseña debe tener mínimo 8 caracteres.

Copia y guarda el token JWT retornado. Este token inicial se utilizará para realizar las peticiones.
Da clic en el botón authorize y saldrá una ventana emergente, donde deberás colocar el token retornado.

### Paso 2. Asignar el rol de administrador

Para probar, modificar directamente el rol del usuario en la base de datos.

Ejecutar la siguiente consulta SQL, reemplazando el correo por el del usuario registrado:

```sql
UPDATE usuario
SET rol = 'ADMIN'
WHERE email = 'correo_del_usuario';
```

**Verificación:**

Consultar el usuario para asegurarse que tenga asignado el rol `ADMIN`.

```sql
SELECT email, rol
FROM usuario
WHERE email = 'correo_del_usuario';
```


### Paso 3. Iniciar sesión nuevamente

Después de actualizar el usuario, haz el login otra vez utilizando sus credenciales.

- Copia el nuevo token JWT retornado.
- Da clic en el botón authorize y saldrá una ventana emergente, donde deberás colocar el token retornado.


### Paso 4. Crear un veterinario POST

Enviar una petición para crear un nuevo veterinario.

**Campos requeridos:**
```
{
  "nombre": "string",
  "especialidad": "string"
}
```
**Verificación:**

1. Comprobar que la API retorne el veterinario.
2. Consultar la base de datos para verificar que el registro se haya guardado correctamente.
3. Realizar una petición `GET` al endpoint de veterinarios y confirmar que el nuevo veterinario aparezca.

---

## 3. Consideraciones generales

- Utilizar `ids` que existan realmente en la base de datos.
- Verificar creaciones en las respuestas de la API o mediante consultas directas a MySQL.
- Utilizar el token JWT correspondiente en cada petición que requiera autenticación.
- Utilizar fechas futuras.


---
# 📐 Matriz mínima de pruebas manuales
| # | Escenario | Resultado Esperado | img |
|---|------|--------|-----------------|
|1| La aplicación inicia con MySQL disponible.| Servidor activo y esquema accesible. | <img width="579" height="173" alt="image" src="https://github.com/user-attachments/assets/a3503f84-eebc-4037-b5b5-c83bc15ea6e0" />|
|2|Registro válido de Paula|200 y token; contraseña hasheada|<img width="780" height="470" alt="image" src="https://github.com/user-attachments/assets/6975181b-f0db-4da7-94f5-26ae98f4988b" /> <img width="501" height="65" alt="image" src="https://github.com/user-attachments/assets/824e1464-e3f6-4287-8f84-8238859de766" />|
|3|Registro con email inválido y clave corta|400 con errores por campo|<img width="1334" height="758" alt="image" src="https://github.com/user-attachments/assets/de879e71-64f6-4a25-8602-3b9bf7fdbb0e" />|
|4|Login con credenciales válidas|200 y JWT vigente|<img width="1332" height="758" alt="image" src="https://github.com/user-attachments/assets/e15aa2d3-4b2f-4ff9-8b88-c080c0b856b3" />|
|5|GET /api/citas sin token|acceso rechazado|<img width="1333" height="760" alt="image" src="https://github.com/user-attachments/assets/d16b05b0-b89a-4e23-bd60-58c67e1f4f79" />|
|6|POST /api/veterinarios con USER|403 Forbidden|<img width="1333" height="761" alt="image" src="https://github.com/user-attachments/assets/60137a8c-b98f-4acd-93a7-9c1a97c504b2" />|
|7|POST /api/veterinarios con ADMIN|201 y veterinario persistido|<img width="1333" height="763" alt="image" src="https://github.com/user-attachments/assets/af1ab8eb-1a7a-4f08-8764-59700ddfb712" /><img width="325" height="17" alt="image" src="https://github.com/user-attachments/assets/bb083e69-6ed2-43bb-9d99-7531155ef702" />|
|8|Creación válida de propietario|201 y DTO sin colecciones anidadas|<img width="1330" height="761" alt="image" src="https://github.com/user-attachments/assets/2f3d37a8-095f-4fdc-ad3f-65b9eeebc127" />|
|9|Creación de mascota con propietario existente|201 y relación correcta|<img width="1334" height="756" alt="image" src="https://github.com/user-attachments/assets/aae5abaa-7eeb-4873-be31-6cb5f98e31b6" />|
|10|Mascota con propietario inexistente|400 controlado; no se inserta fila|<img width="1331" height="761" alt="image" src="https://github.com/user-attachments/assets/7c4d2677-b5ca-4eb2-bee5-91468f1264c9" />|
|11|Cita futura con referencias validas|201 y cita persistida|<img width="1337" height="764" alt="image" src="https://github.com/user-attachments/assets/07356ce7-e70a-48ef-ac00-1594f1850d4f" />|
|12|Cita con fecha pasada|400 con mensaje claro|<img width="1334" height="756" alt="image" src="https://github.com/user-attachments/assets/50bca5c6-f254-4188-88d1-ccc68f863a76" />|
|13|Segundo intento con mismo veterinario y horario|400; se conserva una sola cita|<img width="1337" height="765" alt="image" src="https://github.com/user-attachments/assets/34e7e71c-2f33-4942-92df-1b320cfb940f" />|
|14|Filtro de citas por veterinario|200 y solo coincidencias|<img width="1336" height="765" alt="image" src="https://github.com/user-attachments/assets/7ea9dfc9-6d08-46ef-a33e-a08ba436c7d9" /><img width="479" height="57" alt="image" src="https://github.com/user-attachments/assets/843fe0bb-a991-4644-8ee1-37a8bb742bca" />|
|15|Reinicio y prueba desde Swagger con Authorize |Datos persisten y flujo protegido funciona|<img width="933" height="948" alt="image" src="https://github.com/user-attachments/assets/ff457a6f-852f-44e6-99ba-bb47619b92e8" /><img width="1794" height="7945" alt="670307842-8c822edit" src="https://github.com/user-attachments/assets/09c9061d-b303-4773-9f64-1c51bdfce87c" />
|

---
# 🔄 Prueba con flujo sistema planteado en README.md

### Paso 1. Registrar un usuario

<img width="1062" height="942" alt="image" src="https://github.com/user-attachments/assets/388fae80-ede8-4f09-9f33-719cf6eb61b5" />
<img width="579" height="572" alt="image" src="https://github.com/user-attachments/assets/6dc11b91-9fea-4d7c-aa83-5a1669ce07fc" />



### Paso 2. Crear un propietario POST
<img width="681" height="306" alt="image" src="https://github.com/user-attachments/assets/db907fe2-a8c2-49c4-aad9-c12d230f76bc" />
<img width="1054" height="942" alt="image" src="https://github.com/user-attachments/assets/284bc4d2-7d73-407d-8844-413d50b76ec1" />
<img width="367" height="93" alt="image" src="https://github.com/user-attachments/assets/080b9be2-64f8-4d7b-8d70-e96a0b694e9c" />
<img width="1071" height="899" alt="image" src="https://github.com/user-attachments/assets/ced8971f-7c1a-45bf-8ccd-09e22e688a51" />




### Paso 3. Crear una mascota POST
<img width="1067" height="921" alt="image" src="https://github.com/user-attachments/assets/6b66a23f-92ed-4f11-9859-5cec86139feb" />
<img width="363" height="19" alt="image" src="https://github.com/user-attachments/assets/c17cb25d-bdf7-481a-8d6c-974f961b654f" />
<img width="1064" height="897" alt="image" src="https://github.com/user-attachments/assets/b3ad7a22-a9d5-4c1a-9399-9628f94441b2" />


### Paso 4. Buscar mascotas por propietario
<img width="1070" height="806" alt="image" src="https://github.com/user-attachments/assets/9d306170-eb41-46a0-afa2-9768e7cc4a63" />


### Paso 5. Consultar los veterinarios disponibles
<img width="1072" height="830" alt="image" src="https://github.com/user-attachments/assets/7f8deec0-f561-433a-b922-4b3ded2240bf" />



### Paso 6. Crear una cita
<img width="943" height="930" alt="image" src="https://github.com/user-attachments/assets/b604fbaf-081f-48d2-ac6c-962d9fcd6133" />
<img width="457" height="72" alt="image" src="https://github.com/user-attachments/assets/85f3e8db-8154-4768-8911-20238f1c1758" />
<img width="945" height="734" alt="image" src="https://github.com/user-attachments/assets/adb6e095-a646-4375-9734-0dcda7fc52a3" />





### Paso 7. Buscar citas por veterinario
<img width="950" height="676" alt="image" src="https://github.com/user-attachments/assets/9e56af14-8e35-4daf-b8b4-05e77bd47b0c" />




---
# 🤖 Uso IA
Para el proyecto se usó IA para recordar y comprender algunos conceptos y organizar partes del README.


