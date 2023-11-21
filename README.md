
# APP de Estudiante 

## Descripcion 
Para crear este sistema me conecte a una Basede Datos con Mysql para realizar las tareas de Selección, Inserción, Modificación y Eliminación de registros del Sistema de Estudiantes.


## 


=======
## Badges

![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

![GitHub](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)






=======
## Paquete y Nombre de Clase:

- dev.mjtorrest.dominio
  - Estudiante

- dev.mjtorrest.conexion
  - conexion

- dev.mjtorrest.datos
  - estudianteDao

- dev.mjtorrest.presentacion
  - sistemaEstudianteApp


## La clase Estudiante

tiene cinco atributos privados: idEstudiante, nombre, apellido, telefono, y email. Estos atributos representan las características de un estudiante, como su identificación, nombre, apellido, número de teléfono y correo electrónico.

### Constructores:

La clase Estudiante define varios constructores:
Un constructor sin argumentos.
Un constructor que toma un argumento idEstudiante.
Un constructor que toma los atributos nombre, apellido, telefono y email.
Un constructor que toma todos los atributos, incluyendo idEstudiante.

### Métodos de Acceso:

Para cada atributo, se proporcionan métodos get y set para permitir el acceso y la modificación de los valores de los atributos.
Método toString():

## La clase Conexion

proporciona un método estático llamado getConexion(), que tiene la responsabilidad de establecer una conexión a una base de datos MySQL.
Variables de Configuración:

Se declaran varias variables que almacenan información de configuración de la base de datos, como el nombre de la base de datos (baseDatos), la URL de conexión (url), el nombre de usuario (usuario) y la contraseña (password).
Carga del Controlador JDBC:

El código intenta cargar el controlador JDBC de MySQL en memoria mediante Class.forName("com.mysql.cj.jdbc.Driver"). Esto es necesario para establecer una conexión con la base de datos.
Establecimiento de la Conexión:

Luego, se intenta establecer una conexión a la base de datos utilizando la información de configuración proporcionada. La conexión se crea mediante DriverManager.getConnection(url, usuario, password).
Manejo de Excepciones:

Se manejan las excepciones de ClassNotFoundException y SQLException en caso de que ocurra un error durante la carga del controlador o la conexión a la base de datos. En caso de error, se imprime un mensaje de error en la consola.
Retorno de la Conexión:

## La clase EstudianteDAO

que probablemente significa "Data Access Object" y se utiliza para interactuar con la base de datos en relación a los objetos Estudiante.
Método listarEstudiantes():

Este método devuelve una lista de objetos Estudiante que representa a todos los estudiantes en la base de datos. Realiza lo siguiente:
Crea una lista vacía de estudiantes.
Establece una conexión a la base de datos utilizando el método getConexion() de la clase Conexion.
Ejecuta una consulta SQL para seleccionar todos los estudiantes en orden ascendente por su identificación.
Recorre el resultado de la consulta y crea objetos Estudiante con los datos de la base de datos, que se agregan a la lista.
Finalmente, cierra la conexión y retorna la lista de estudiantes.
Método buscarEstudiantePorId():

### método busca estudiante

por su identificación y devuelve un valor booleano. Realiza lo siguiente:
Establece una conexión a la base de datos.
Ejecuta una consulta SQL para seleccionar un estudiante por su identificación.
Si se encuentra un estudiante con la identificación especificada, se actualizan los datos del objeto Estudiante pasado como argumento y se devuelve true. Si no se encuentra, se devuelve false.
Método agregarEstudiante():

### método agrega estudiante

a la base de datos y devuelve un valor booleano para indicar el éxito de la operación.
Establece una conexión a la base de datos.
Ejecuta una consulta SQL para insertar un nuevo estudiante con los datos proporcionados en el objeto Estudiante.
Si la inserción es exitosa, devuelve true. De lo contrario, devuelve false.
Método modificarEstudiante():

### método modifica estudiante

los datos de un estudiante existente en la base de datos y devuelve un valor booleano para indicar el éxito de la operación.
Establece una conexión a la base de datos.
Ejecuta una consulta SQL para actualizar los datos de un estudiante basado en su identificación.
Si la actualización es exitosa, devuelve true. De lo contrario, devuelve false.
Método eliminarEstudiante():

### método elimina estudiante

de la base de datos y devuelve un valor booleano para indicar el éxito de la operación.
Establece una conexión a la base de datos.
Ejecuta una consulta SQL para eliminar un estudiante basado en su identificación.
Si la eliminación es exitosa, devuelve true. De lo contrario, devuelve false.
Manejo de Excepciones:


## La clase SistemaEstudianteApp

y se considera la aplicación principal para gestionar estudiantes en un sistema.
Método main(String[] args):

Este método es el punto de entrada de la aplicación. Comienza declarando una variable salir para controlar la ejecución del programa y crea una instancia de la clase Scanner para recibir la entrada del usuario.
Luego, crea una instancia de EstudianteDAO que se utilizará para interactuar con la base de datos.

En un bucle while, se muestra un menú de opciones con un método mostrarMenu() y se ejecutan las opciones seleccionadas a través del método ejecutarOpciones(). Si ocurre alguna excepción durante la ejecución, se captura y se muestra un mensaje de error en la consola.
El bucle continúa hasta que la opción de salida sea seleccionada.
Método mostrarMenu():

### método muestrar menú

da opciones en la consola para que el usuario seleccione una operación. Las opciones incluyen agregar estudiantes, listar estudiantes, buscar estudiantes, modificar estudiantes, eliminar estudiantes y salir.
Método ejecutarOpciones(Scanner consola, EstudianteDAO estudianteDAO):

Este método toma una instancia de Scanner y EstudianteDAO como argumentos y ejecuta las operaciones correspondientes según la opción seleccionada por el usuario.

Se lee la opción ingresada por el usuario y se utiliza una estructura switch para determinar la operación a realizar.
Para cada caso, se realizan las operaciones correspondientes, como agregar, listar, buscar, modificar o eliminar estudiantes en la base de datos, y se muestra la información adecuada en la consola.
Si el usuario selecciona la opción "Salir", se establece la variable salir en true para salir del bucle principal.
