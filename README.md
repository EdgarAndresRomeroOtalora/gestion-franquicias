El proyecto esta implementado en Spring Boot y configurado para realizar persistencia con una base de datos de MySQL.

Para ejecutar el proyecto de forma local es necesario tener instalado la versión de java 17

- Para verificar la versión de java en windows usar el comando 
  java -version

- Es importante adicionar las variables de entorno que tienen los datos de conexión con la base de datos, las cuales son:
  MYSQL_URL
  MYSQL_USERNAME
  MYSQL_PASSWORD
  
- Clonar el repositorio

- Ejecutar el proyecto en Windows

  .\mvnw.cmd clean install -DskipTests
  .\mvnw.cmd spring-boot:run


  
  

