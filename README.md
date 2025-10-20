##  Descripción de la prueba técnica

En la base de datos de comercio electrónico de la compañía existe una tabla `PRICES` que refleja el precio final (PVP) y la tarifa aplicable a un producto de una cadena entre unas fechas determinadas.


###  Requerimiento principal

Se pide construir un **servicio REST** que cumpla con los siguientes requisitos:

#### Parámetros de entrada
- Fecha de aplicación  
- Identificador de producto  
- Identificador de cadena  

#### Datos de salida
- Identificador de producto  
- Identificador de cadena  
- Tarifa a aplicar  
- Fechas de aplicación  
- Precio final a aplicar  


###  Base de datos

Se solicita utilizar una **base de datos en memoria (tipo H2)** inicializada con los datos del ejemplo.  
A continuación se describen los campos de la tabla `PRICES`:

| Campo | Descripción |
|--------|-------------|
| **BRAND_ID** | Foreign key de la cadena del grupo (1 = ZARA). |
| **START_DATE**, **END_DATE** | Rango de fechas en el que aplica la tarifa indicada. |
| **PRICE_LIST** | Identificador de la tarifa de precios aplicable. |
| **PRODUCT_ID** | Identificador o código de producto. |
| **PRIORITY** | Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rango de fechas, se aplica la de mayor prioridad (valor numérico más alto). |
| **PRICE** | Precio final de venta. |
| **CURR** | Código ISO de la moneda. |


###  Tests solicitados

Se deben desarrollar tests que validen las siguientes peticiones al endpoint REST, utilizando los datos del ejemplo:

| Test | Fecha y hora de aplicación | Producto | Brand (cadena) | Descripción |
|------|-----------------------------|-----------|----------------|--------------|
| 1 | 2020-06-14 10:00:00 | 35455 | 1 (ZARA) | Petición a las 10:00 del día 14 |
| 2 | 2020-06-14 16:00:00 | 35455 | 1 (ZARA) | Petición a las 16:00 del día 14 |
| 3 | 2020-06-14 21:00:00 | 35455 | 1 (ZARA) | Petición a las 21:00 del día 14 |
| 4 | 2020-06-15 10:00:00 | 35455 | 1 (ZARA) | Petición a las 10:00 del día 15 |
| 5 | 2020-06-16 21:00:00 | 35455 | 1 (ZARA) | Petición a las 21:00 del día 16 |


###  Criterios de evaluación

Se valorará especialmente:

- Diseño y construcción del servicio  
- Calidad del código  
- Resultados correctos en los tests  
