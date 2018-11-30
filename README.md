# ALQUILER DE BICICLETAS

<img src="https://github.com/jonathanlezaeta/ejercicio/blob/master/bicycleRental/UML.jpg">

Para resolver el calculo del precio de los alquileres se hiso una herencia con un composite para modelar las promosiones.

1. Rental by hour, charging $5 per hour
2. Rental by day, charging $20 a day
3. Rental by week, changing $60 a week

Se abstrajo el calculo del precio de alquileres en la AbstractRentailPrice con un metodo abstracto getRentalBicycle, para que cada hijo defina su logica. Y si se desean agregar nuevas formas de calcular precios solo se agrega un hijo a la herencia y se implementa el metodo de una forma diferente.

Para los casos: 
1. Rental by hour, charging $5 per hour
2. Rental by day, charging $20 a day
3. Rental by week, changing $60 a week

Los calculos son analohos unidad de tiempo por precio, y se modelan con la clase SimplePrice, las distintas instancias de esta clase modelan los diferentes tipos de precios. Por ejemplo:
    SimplePrice hours = new SimplePrice();
		hours.setDescripcion("PRECIOS POR HORA ");
		hours.setPrice(5);
		SimplePrice day = new SimplePrice();
		day.setDescripcion("PRECIO POR DIA ");
		day.setPrice(20);
		SimplePrice weely = new SimplePrice();
		weely.setDescripcion("PRECIO POR SEMANA ");
		weely.setPrice(60);
    
La clase que modela las promosiones (PUNTO 4 del ejercicio) es CompositePrice, tambien reimplementa getRentailCosts para aplicar el descuento y calcular el precio de todos los precios que la componen, inclusive esta implementacion es tan flexible que puede anidarse promosiones de promosiones en caso de ser necesario y no habria que tocar codigo. Tambien se puede hacer promosiones con nuevos tipos de precios si se desean agregar.

Luego para validar promosiones se hiso otra jerarquia a partir de una interfaz, de la cual todas las clases que la utilizan van a reimplementar su metodo de validacion. 
Para el ejercicio se crearon el validador de >=, <= y un validador AND que esta compuesto de dos validadores mas, esta implementacion permitiria agregar mas validadores si es necesario solo reimplementando la interfaz (OR, >, <, ects) y generar combinaciones entre ellos.

NOTA: se realizo un JunitTest que prueba calculos basicos y una promosion asignandole un tiempo de alquiler y validando con assertTrue los resultados que deberian dar. Adicionalmente se hiso u algoritmo recursivo para probar todas las posibles promosiones que se ueden generar con los tres tipos de presios que hay actualmente (si se agregan nuevos tipos no es necesario tocar el algoritmo), cuando se calculan todas las combinaciones posibles vamos a notar que aparecen resultados repetidos, esto se debe a que el orden de los precios en la promosion no afecta el resultado, si se deseara eliminar repetidos implementamos un compareTo en los hijos de AbstractRentailPrice y evitar repediso. 

NOTA 2: Si esto fuera un sistema real usaria logg4j para loguear e implementaria try catch en varios lugares para evitar errores, decidi omitir esto en el ejercicio para no cargar de mas. 
