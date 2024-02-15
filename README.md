# Mercadolibre - Android APP

Esta es una aplicación en Android que muestra la lista de los productos con el detalle del item 
seleccionado. Esta desarrollada en lenguaje Kotlin y Jetpack compose 

- Buscador de productos
- Muestra lista de los items encontrados
- Muestra el detalle del item seleccionado
- Paginación para cargar mas resultados
- Test unitarios 

```bash
git clone https://github.com/chrisk93/admmeli.git
```

## Screenshots

![screenshot1](https://github.com/chrisk93/admmeli/assets/35810477/e3265dab-512a-4e06-a511-ab992d69d80a)


## Librerias usadas

- AndroidX
- Retrofit
- Coil
- Navigation
- Jetpack compose
- Hilt

## Arquitectura
Esta app fue construida usando Clean arquitecture siguiendo las siguientes capas

![image](https://media.geeksforgeeks.org/wp-content/cdn-uploads/20220219214201/Clean-Architecture-in-Android.png)

La organización de la aplicación es basada en el siguiente diagrama 

:app/
:core/
├── :network/
├── :ui/
└── :utils/
:data/
:domain/
└──  :items_search
:dto/
:feature/
├── :search
└── :detail
:navigation/

- app: Es el activity de la aplicacion llamado MainActivity
- core: Contiene el código comun de la aplicación
    - network: contiene la capa de red de la app, la configuración de retrofit y utilidades para el manejo de las respuestas
    - ui: contiene componentes visuales que se reutilizan en toda al app
    - utils: contiene funcionalidades para convertir textos o formatos
- data: Contiene la capa de datos de la aplicacion
- domain: Contiene los casos de uso de la aplicación
- dto: Contiene los objetos de transferencia de la app
- feature: Contiene las caracteristicas de la aplicación, cada subcarpeta representa una vista
    - search: Contiene la caracteristica de busqueda, la UI y el viewmodel
    - detail: Contiene el detalle del item, la UI y el viewmodel
- navigation: Contiene la navigacion de las vistas de la app 













