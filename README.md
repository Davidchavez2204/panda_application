# panda_application
Sample Spring Boot application using Thymeleaf and Spring Web.

## 1. Propósito del Proyecto
### Objetivo
Este proyecto tiene como objetivo demostrar la creación de una aplicación web utilizando Spring Boot y Thymeleaf, que permite realizar operaciones básicas sobre mensajes.

### Funcionalidades principales
- Crear, obtener, actualizar y eliminar mensajes mediante peticiones HTTP.
- Integración con Apache JMeter para realizar pruebas de performance.

## 2. Pipeline: Para todas, herramienta/Framework + Evidencia(fragmento de código)+ Integración con Jenkins
### A. Construcción Automática
La construcción automática de la aplicación se realiza mediante Jenkins y Maven, asegurando que el código siempre esté en una versión construible y lista para ser desplegada.

### B. Análisis Estático
Se utiliza SonarQube para realizar análisis estáticos del código y asegurar la calidad del software.

### C. Pruebas Unitarias
Se ejecutan pruebas unitarias utilizando JUnit para garantizar que cada componente del sistema funcione como se espera.

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

## 4. Contacto
Si tienes alguna pregunta o comentario sobre el proyecto, no dudes en ponerte en contacto con el equipo a través de [GitHub Issues](https://github.com/user/repository/issues).



   







