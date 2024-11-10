# PRÁCTICA 1 PROGRAMACIÓN ORIENTADA A OBJETOS


- [x] **Clase Puerto**
- [ ] **Clase PuertoTest**
- [ ] **Hacer JavaDoc Puerto**
- [x] **Clase Muelle**
- [ ] **Clase MuelleTest**
- [ ] **Hacer JavaDoc Muelle**
- [ ] **Clase Contenedor**
- [ ] **Clase ContenedorTest**
- [ ] **Hacer JavaDoc Contenedor**
- [x] **Clase Trayecto**
- [ ] **Clase TrayectoTest**
- [ ] **Hacer JavaDoc Trayecto**


## Clase puerto
Gestiona el puerto de una localidad. Se identifica con dos letras mayúsculas con el nombre del país, un guión y finalmente tres letras mayúsculas con el código de la localidad.
En cada puerto hay una serie de muelles a los que llegan barcos llenos de contenedores.

- [x] Hacer que puerto está operativo si alguno de sus muelles lo está.
- [x] Poder añadir un Muelle a un puerto.
- [x] Poder eliminar un Muelle de un puerto a partir de su ID.
- [x] Obtener si el puerto está completo o no; entendiendo por completo que no hay posibilidad de almacenar ningún contenedor más.
- [x] Obtener una lista de los Muelles que estén operativos.
- [x] Obtener una lista de los Muelles que tengan espacio.
- [x] Obtener una lista de los Muelles que se encuentran a una distancia inferior dada de cierto punto GPS.

## Clase muelle
La clase Muelle está caracterizada por un identificador que es un número de dos dígitos (9 < x < 100), un punto GPS que lo localiza, un estado (operativo o fuera de servicio).
Cada muelle tiene un número variable de plazas (positivo) y una cantidad máxima de contenedores que se pueden apilar (x > 0) que se fija en el momento de crearlo.

- [x] Conocer el número de plazas que tiene el muelle.
- [x] Conocer el número de plazas vacías, semi-llenas y completas.
- [x] Dado un código de contenedor, indicar la plaza en la que está.
- [x] Dado un código de contenedor, indicar en qué nivel de una plaza está apilado.
- [x] Asignar un contenedor a una plaza y apilarlo encima de otro si es posible.
- [x] Sacar un contenedor de una plaza y desapilarlo.

## Clase contenedor
La clase `Contenedor` está caracterizada 1 por el código del dueño (3 letras mayúsculas A-Z), una letra mayúscula (U, J o Z) que indica el equipamiento, un número de serie de 6 dígitos, un dígito de control obtenido de un algoritmo (), el peso de la tara (es decir el peso del contenedor) (x > 0), la máxima carga útil permitida y el volumen (x > 0). Las unidades de medidas para el peso estarán dadas en Kilogramos y/o Libras y las de volumen en metros cúbicos y/o pies cúbicos. Cada Contenedor necesita un estado que indique si se encuentra en tránsito o en recogida, asimismo hay que indicar si el contenedor tiene techo o no (si no tiene techo, entonces no se puede disponer otro contenedor encima
de él).

- [x] Cambiar el estado de un contenedor para reflejar que está en recogida
- [x] Cambiar el estado de un contenedor para reflejar que está en tránsito
- [x] Cambiar a contenedor tiene techo o no
- [x] Obtener el volumen del contenedor en metros cúbicos
- [x] Obtener el volumen del contenedor en pies cúbicos
- [x] Obtener el peso del contenedor en Kilogramos
- [x] Obtener el peso del contenedor en Libras
- [x] Obtener el precio del transporte total de un contenedor a partir de sus trayectos.


## Clase trayecto
Cada Contenedor puede realizar uno o varios viajes (de puerto en puerto) hasta llegar a destino. Esta información se va a almacenar gracias a la clase Trayecto. Las instancias de la clase Trayecto deben almacenar un muelle de origen, puerto de origen, una fecha de inicio del trayecto, muelle de destino, puerto de destino y la fecha del fin de trayecto.

- [x] Conocer si la fecha de fin de trayecto es superior a una dada.
- [x] A partir del coste por día de trayecto dado y el coste por milla marina, obtener el precio de un trayecto en euros.
- [x] Obtener la distancia en millas marinas de un trayecto.
- [x] Obtener información completa del trayecto: indicando localidad del puerto de origen, país y fecha de inicio del trayecto y localidad del puerto de destino, país y fecha de fin de trayecto
