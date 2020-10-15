"# ProyectoFinal New"  
IMPORTANTE!!
para el acceso a la app
/*********************************************/
user = admin
password = 9999

LA RAMA MASTER para revisar es la nunero 2
/*********************************************/

DETALLES:
La app se entiende en el contexto de un negocio familiar que tiene que controlar su inventario
, clientes y sus provedores.
Eventualmente realiza rutas de entregas a domicilio.

La app que he desarrollado puede expandirse (mejorarse) en varios aspectos.
Se encuentra incompleta, pero posee las funcionalidades a modo de muestra para entender su 
usabilidad en este contexto.
Adicionalmente permite mostrar los conocimientos adquiridos en el curso como por ejemplo:
-La app consiste en solamente dos activities, la primera es login de acceso, la 
segunda es la main activity, en la cual se desenvuelven todas las demas funcionalidades.

En la primera creación de la base de datos en el celular se crea el usuario Admin, el cual posee password 9999.
Asimismo no es posible alterarlo.
Se valida el password del user creado usando la misma base de datos en el login.

En Main se usa un navigation drawer, gracias al cual pude usar funcinalidades que se extienden
un poco mas allá del curso, especialmente en el manejo de layouts.

Para el tema de la arquitectura e software intenté incorporar viewmodel para el agregado de items en
las listas que se obtienen desde la base de datos (la que se encuentra implementada en Room).

Dejé puntos pendientes que creo que son útiles para el trabajo del negocio en el que se encuentra pensada
la app, sólo por tema de tiempo: Son el caso del ítem de Gastos y los botones de acceso a base de datos en la 
nube (mi intención es usar mongoDB, mendiante JAVA, porque creo que los conectores no se encuentran disponibles para Kotlin).

Visión global de la app.

Home:
->en la cabecera posee datos de divisas y algunos indicadores (util en caso de comprar en dólares)
->abajo tiene tres botones, solamente recibe eventos el permite gestionar (crear, actualizar y elimnar users).
Este btn solamente se expande si el user es Admin.

Ventas/Gastos.
-> En ests ítem prentendo ingresar a la base de datos las ventaas que se van registrando y listando.
-> Algo simila en el tab de Gastos (el que no implemento en esta presentación).

Ruta.
Para el caso de salida a entregas a domicilio se permita listar nombres, domicilio y teléfono (principalmente)

Admin.
->permite mostrar valores relevantes que permitan informar de lo que posee la base de datos.

Clientes.
Este listad se llena tanto con Ruta como con este tab, directamente.

Proveedores.
Listado de provedores y datos.



EN LA RAMA MASTER_3 EXPLORO EL USO DE VIEW MODEL JUNTO CON ROOM
POR LO QUE ALTERO LAS CLASES INVOLUCRADAS