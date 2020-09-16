package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.DataEjemplo

import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.Productos_DataView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.Productos_Entity


/*Ejemplo con 5 productos para pruebas*/
/*los ítems no debes repetirse ----> evitar la duplicación de nombre de producto (relacionando por
ejemplo id -> nombre del producto)*/
var producto_1 = Productos_Entity(
11111,
/*el txt debe ser tratado para quedar en minúsculas al entrar a la db*/
"calcetines",
5000
)

var producto_2 = Productos_DataView(
    1,
    /*el txt debe ser tratado para quedar en minúsculas al entrar a la db*/
    "poleras",
    5000
)

var producto_3 = Productos_DataView(
    2,
    /*el txt debe ser tratado para quedar en minúsculas al entrar a la db*/
    "zapatos",
    5000
)

var producto_4 = Productos_DataView(
    3,
    /*el txt debe ser tratado para quedar en minúsculas al entrar a la db*/
    "zapatillas",
    5000
)

var producto_5 = Productos_DataView(
    4,
    /*el txt debe ser tratado para quedar en minúsculas al entrar a la db*/
    "sombreros",
    5000
)


