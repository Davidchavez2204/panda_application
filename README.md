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
<img src="https://github.com/Davidchavez2204/VerificacionyValidcionDeSW/blob/main/JMETER.PNG?raw=true" width="600"/>
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



   







