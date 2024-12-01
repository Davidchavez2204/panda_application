## 1. Propósito del Proyecto
### Descripción
Nuestra aplicación web se basa en Spring Boot con Thymeleaf como motor de plantillas para la interfaz. 
<div align="center">
  <img src="https://github.com/Davidchavez2204/panda_application/blob/master/Imagenes/Aplicacion1.png" alt="Image Alt" width="600">
</div>
   <div align="center">
  <img src="https://github.com/Davidchavez2204/panda_application/blob/master/Imagenes/Aplicacion2.png" alt="Image Alt" width="600">
</div>
Ofrece operaciones CRUD sobre mensajes, utilizando endpoints HTTP para realizar las siguientes acciones:

### Funcionalidades principales
- Crear mensajes: Permite registrar un nuevo mensaje en el sistema.
- Obtener mensajes: Recupera un mensaje específico mediante su ID.
- Actualizar mensajes: Modifica el contenido de un mensaje existente.
- Eliminar mensajes: Borra un mensaje almacenado en el sistema.
- Integración con Apache JMeter para realizar pruebas de performance.

### Objetivo
El objetivo de la web es brindar una experiencia sencilla pero funcional para interactuar con datos de mensajes, incluyendo soporte para pruebas automatizadas.

### Ramas
El repositorio de GitHub tiene las siguientes ramas:
- master: Contiene el código en su versión estable y listo para producción.
- develop: Usada para consolidar características en desarrollo antes de ser integradas en master.
- pruebas-unitarias: Incluye las pruebas unitarias desarrolladas con JUnit para validar componentes individuales del sistema.
- pruebas-funcionales: Rama dedicada a pruebas funcionales usando Selenium WebDriver para automatizar la interacción con la interfaz gráfica.

### Guardado de Datos
El sistema utiliza una base de datos integrada (como H2) para almacenar los datos. Durante la ejecución:
- Los datos se gestionan a través de Spring Data JPA, permitiendo la abstracción en el manejo de repositorios.
- La configuración de persistencia se encuentra en el archivo application.properties, ubicado en el proyecto dentro del directorio src/main/resources.
- Los mensajes se almacenan en tablas de base de datos, con soporte para operaciones transaccionales.

## 2. Pipeline: Para todas, herramienta/Framework + Evidencia(fragmento de código)+ Integración con Jenkins
### A. Construcción Automática
La construcción automática de la aplicación se realiza mediante Jenkins y Maven, asegurando que el código siempre esté en una versión construible y lista para ser desplegada.
<div align="center">
  <img src="https://github.com/Davidchavez2204/panda_application/blob/master/Imagenes/contrucci%C3%B3n%20automatica%202.png" alt="Image Alt">
</div>

#### Integración con Jenkins

<div align="center">
  <img src="https://github.com/Davidchavez2204/panda_application/blob/master/Imagenes/contrucci%C3%B3n%20automatica%201.png" alt="Image Alt">
</div>

### B. Análisis Estático
El análisis estático es una técnica utilizada para examinar el código fuente de un programa sin ejecutarlo con el objetivo identificar defectos, vulnerabilidades, o violaciones de estándares de codificación que puedan afectar la calidad, seguridad, o mantenibilidad del software.

En el proyecto, se utilizaron las siguientes herramientas para implementar análisis estático:

         1. **SonarQube**: Genera informes detallados y métricas que ayudan a mejorar la calidad del código.
         
         2. **Jenkins**: Integrado con SonarQube para automatizar este proceso como parte de un pipeline de CI/CD.

#### Informe de SonarQube

<div align="center">
  <img src="https://github.com/Davidchavez2204/panda_application/blob/master/Imagenes/SonarQube.jpg">
</div>

1. **Complejidad Ciclomática y Coginitva**  
   Ayuda a evaluar la mantenibilidad y simplicidad del código.

<div align="center">
  <img src="https://github.com/Davidchavez2204/panda_application/blob/master/Imagenes/Complejidad.png">
</div>

2. **Bugs**  
   Se identificaron 2 bugs de tipo:

   **Add "lang" and/or "xml:lang" attributes to this "<html>" element**

<div align="center">
  <img src="https://github.com/Davidchavez2204/panda_application/blob/master/Imagenes/Bugs.jpg">
</div>

3. **Code Smells**  
   Se identificaron 5 code smells de tipo:

**Duplication**

<div align="center">
  <img src="https://github.com/Davidchavez2204/panda_application/blob/master/Imagenes/CodeSmells.jpg">
</div>

#### Integración de las pruebas de performance con Jenkins:

<div align="center">
  <img src="https://github.com/Davidchavez2204/panda_application/blob/master/Imagenes/SonarQube_code.png">
</div>

### C. Pruebas Unitarias
Las pruebas unitarias son un método de verificación que se utiliza para validar que cada componente individual de un sistema funcione correctamente. Estas pruebas están diseñadas para aislar y probar pequeñas unidades de código

En este proyecto, utilizamos JUnit, un framework ampliamente reconocido en Java para la creación y ejecución de pruebas unitarias. por su facilidad de integración, eficiencia y estandarización

<div align="center">
  <img src="https://github.com/Davidchavez2204/panda_application/blob/c45c5d23b88506a50bfa7c4d6d716f43101502ee/Imagenes/Junit.jpg" alt="Image Alt">
</div>


  #### Pruebas:
1. **testCreateMessage:**

   . Objetivo: Asegurar que la funcionalidad de creación de mensajes está operativa y genera una respuesta válida.
   
         1. Crear una cadena con el mensaje "Hello, World!".
   
         2. Enviar una solicitud POST al endpoint /create, incluyendo el mensaje.
   
         3. Verificar que la respuesta contiene el texto "Message created with ID", asegurando que el mensaje fue creado exitosamente.

<div align="center">
  <img src="https://github.com/Davidchavez2204/panda_application/blob/8e69a0da10acc85ce6fa8fad423a932719ab892b/Imagenes/CreateMessage.jpg">
</div>

2. **testGetMessage:**

   . Objetivo: Garantizar que los mensajes creados puedan ser recuperados correctamente.
   
         1. Crear un mensaje ("Test Message") mediante una solicitud POST.
   
         2. Extraer el ID del mensaje de la respuesta de creación.
   
         3. Realizar una solicitud GET al endpoint con el ID del mensaje.
   
         4. Verificar que el contenido del mensaje recuperado es "Test Message".

 <div align="center">
  <img src="https://github.com/Davidchavez2204/panda_application/blob/217438fc712d6ec033e98f7be24b600baf08d700/Imagenes/GetMessage.jpg">
</div>  
   
3. **testUpdateMessage:**

   . Objetivo: Asegurar que los mensajes se puedan modificar correctamente.
   
         1. Crear un mensaje inicial ("Old Message") con una solicitud POST.
   
         2. Extraer el ID del mensaje creado.
   
         3. Enviar una solicitud PUT al endpoint correspondiente, con un nuevo contenido ("Updated Message").
   
         4. Verificar que la respuesta indique que el mensaje fue actualizado para el ID correcto.
   
         5. Realizar una solicitud GET al mismo ID para confirmar que el contenido actualizado es "Updated Message". 
 <div align="center">
  <img src="https://github.com/Davidchavez2204/panda_application/blob/217438fc712d6ec033e98f7be24b600baf08d700/Imagenes/UpdateMessagejpg.jpg">
</div>  

4. **testDeleteMessage:**

   . Objetivo: Comprobar que los mensajes se puedan eliminar y que el sistema indique claramente cuando un mensaje ya no existe.
   
         1. Crear un mensaje ("Message to be deleted") con una solicitud POST.
   
         2. Extraer el ID del mensaje de la respuesta de creación.

         3. Enviar una solicitud DELETE al endpoint con el ID del mensaje.
   
         4. Verificar que la respuesta confirme la eliminación del mensaje.
   
         5. Intentar recuperar el mensaje eliminado con una solicitud GET y confirmar que la respuesta indique "Message not found".

 <div align="center">
  <img src="https://github.com/Davidchavez2204/panda_application/blob/217438fc712d6ec033e98f7be24b600baf08d700/Imagenes/DeleteMessage.jpg">
</div> 

### Evidencia de Pruebas Unitarias
La imagen muestra que los cuatro tests se ejecutaron correctamente, sin errores ni fallos, en un tiempo total de 7.759 segundos. Esto confirma que todas las funcionalidades clave de la aplicación están funcionando como se esperaba.

<div align="center">
  <img src="https://github.com/Davidchavez2204/panda_application/blob/8aa2bb384c24b5c9efc97ca44c10c5c2e688e16a/Imagenes/exito.jpeg">
</div> 

### Integración con Jenkins
Las pruebas unitarias se ejecutan automáticamente en el pipeline de Jenkins usando Maven, y los resultados se publican en formato XUnit con el plugin JUnit, asegurando la calidad y automatización del proceso.

<div align="center">
  <img src="https://github.com/Davidchavez2204/panda_application/blob/8aa2bb384c24b5c9efc97ca44c10c5c2e688e16a/Imagenes/pandaaplicationtest.jpg">
</div> 

   
### D. Pruebas Funcionales
Las pruebas funcionales se ejecutan para verificar que la aplicación cumple con los requisitos especificados.
Las pruebas funcionales se implementaron utilizando JUnit 5 como framework de pruebas y Selenium WebDriver como herramienta para la automatización de pruebas de interfaces de usuario (UI).

<p align="center">
   <img src="https://github.com/user-attachments/assets/d6d15df2-d38f-4447-8227-01f0aa672113" width="400"/>
</p>
Se utilizo el web driver de Firefox:

<p align="center">
   <img src="https://github.com/user-attachments/assets/1a43d6c4-4316-4d61-a018-e4c9d5bb4476" width="400"/>
</p>

#### Pruebas:
1. **testCreateMessage:**

   . Objetivo: Validar que se pueda crear un mensaje mediante la interfaz web.
   
   . Flujo:
   
         1. Introducir texto en el campo de entrada createMessage.
   
         2. Hacer clic en el botón "Crear mensaje" .
   
         3. Verifique que el mensaje de respuesta contenga el texto: "Mensaje creado con ID" .
   
   . Resultado esperado: El mensaje se crea correctamente y se genera un ID.
   
   <p align="center">
   <img src="https://github.com/user-attachments/assets/326b0319-81e1-49a4-b260-591ac86d30e0" width="400"/>
   </p>
   
2. **testGetMessage:**
   
   . Objetivo: Verificar que se puede recuperar un mensaje previamente creado.
   
   . Flujo:
   
         1. Crear un mensaje con el flujo de creación de mensajes.
            
         3. Capturar el ID generado.
            
         5. Introducir el ID en el campo getMessageIdy hacer clic en el botón "Get Message".
            
         7. Validar que el mensaje recuperado coincida con el mensaje original.
   
   . Resultado esperado: El mensaje asociado al ID se recuperó correctamente.
   
   <p align="center">
   <img src="https://github.com/user-attachments/assets/191d74af-fd9f-4d2a-ace7-9e33bbc32db9" width="400"/>
   </p>
   
3. **testGetMessage:**

   . Objetivo: Asegurar que un mensaje existente puede ser actualizado.
   
   . Flujo:
   
         1. Crea un mensaje y captura el ID generado.
            
         3. Introducir el ID en el campo updateMessageId.
            
         5. Introduzca el texto actualizado en el campo updateMessagey haga clic en "Actualizar mensaje" .
            
         7. Validar que el mensaje de respuesta contiene el texto: "Mensaje actualizado para ID" .
   
   . Resultado esperado: El mensaje está actualizado correctamente.
   
   <p align="center">
   <img src="https://github.com/user-attachments/assets/a9f87b3d-9b2c-4dae-aced-2d2c7a99a33e" width="400"/>
   </p>

4. **tesDeleteMessage:**
   
   . Objetivo: Comprobar que un mensaje existente puede ser eliminado.
   
   . Flujo:
   
         1. Crea un mensaje y captura el ID generado.
            
         3. Introducir el ID en el campo deleteMessageId.
            
         5. Hacer clic en el botón "Eliminar mensaje" .
            
         7. Validar que el mensaje de respuesta contiene el texto: "Mensaje eliminado por ID" .
   
   . Resultado esperado: El mensaje se eliminó correctamente.
   
   <p align="center">
   <img src="https://github.com/user-attachments/assets/b374ce49-ef4a-4a63-a0d7-d76ca765df10" width="400"/>
   </p>
   
#### Integración de las pruebas de performance con Jenkins:

   <p align="center">
   <img src="https://github.com/user-attachments/assets/d23396f8-856c-4cc2-8722-fa237aeba387" width="400"/>
   </p>

### E. Pruebas de Performance
En este apartado se trabajó utilizando **Apache JMeter** con los siguientes parámetros:
- Número de threads: 50
- Ramp-up period: 10 segundos
- Loop count: 10

#### Casos evaluados:
1. **Crear Mensaje:**  
   Utiliza un HTTP Request con un método `POST` para insertar el mensaje "Texto simple del mensaje".  
   <p align="center">
   <img src="https://github.com/user-attachments/assets/c09e0e94-357a-42c1-b56d-ec075e92e08c" width="400"/>
   </p>

2. **Obtener Mensaje:**  
   Utiliza un HTTP Request con un método `GET` para recuperar el mensaje desde la página web.  
   <p align="center">
   <img src="https://github.com/user-attachments/assets/4553a0e5-7c43-49bc-8bfe-2e86fd2086ea" width="400"/>
   </p>

3. **Actualizar Mensaje:**  
   Utiliza un HTTP Request con un método `PUT` para actualizar el mensaje a "Este es el mensaje actualizado desde JMeter".  
   <p align="center">
   <img src="https://github.com/user-attachments/assets/6e08d9b5-38e3-42f5-a4a1-c61c8586ec4c" width="400"/>
   </p>

4. **Eliminar Mensaje:**  
   Utiliza un HTTP Request con un método `DELETE` para eliminar el mensaje de la página.  
   <p align="center">
   <img src="https://github.com/user-attachments/assets/0bacbcad-bf56-4e2c-b82c-719b6a571358" width="400"/>
   </p>

#### Resultados de la ejecución de las pruebas de performance:
<p align="center">
<img src="https://github.com/user-attachments/assets/618cedcf-0cf0-431d-978e-811f5394efca" width="600"/>
</p>

<p align="center">
<img src="https://github.com/user-attachments/assets/5439b0f7-aa12-42a6-8510-d96ece894cac" width="600"/>
</p>

#### Integración de las pruebas de performance con Jenkins:
   Este pipeline de Jenkins ejecuta pruebas de rendimiento con Apache JMeter en modo headless (sin interfaz gráfica). La etapa "Pruebas de Rendimiento - JMeter" contiene un bloque de pasos que imprime un mensaje informativo en la consola indicando el inicio de las pruebas. Luego, utiliza el comando bat para ejecutar JMeter desde la terminal de Windows, especificando el archivo de pruebas .jmx ubicado en D:/JMeter/Summary Report.jmx. Las opciones del comando incluyen -n para el modo no gráfico, -l para guardar los resultados en un archivo .jtl, -e para generar un informe HTML, y -o para definir el directorio de salida D:/Jenkins/reporte_rendimiento. Este proceso automatiza la ejecución de las pruebas y la generación de reportes, permitiendo la integración continua y el monitoreo del rendimiento en proyectos de software.
   
<p align="center">
<img src="https://github.com/user-attachments/assets/af9a701c-537b-40ee-84e3-5f1d96fd599d" width="600"/>
</p>

### F. Pruebas de Seguridad
Se realizan pruebas de seguridad para garantizar que la aplicación esté protegida contra vulnerabilidades comunes, como inyecciones SQL, XSS y CSRF.

### G. Gestión de Issues
En **GitHub Projects**, se registran los defectos más importantes encontrados por las pruebas en **SonarQube**. Además, se gestiona la división de esfuerzos del equipo, asignando tareas de la siguiente manera:

- **Nombre de la tarea:** Descripción detallada de la tarea.
- **Responsable:** Nombre del miembro del equipo asignado.
- **Etiqueta:** Prioridad o tipo de tarea (e.g., bug, enhancement).
- **Duración estimada:** Tiempo estimado para completar la tarea.

---

## 3. Conclusiones
El proyecto se desarrolló utilizando tecnologías modernas como Spring Boot y Thymeleaf, con un enfoque en pruebas automatizadas y seguridad. Las pruebas de performance realizadas con Apache JMeter demuestran que la aplicación puede manejar un número adecuado de solicitudes bajo carga.

---



   







