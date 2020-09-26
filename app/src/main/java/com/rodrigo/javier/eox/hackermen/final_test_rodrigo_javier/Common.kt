package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.utilities.external

class CommonFunctions {
    companion object {
        /*RETROFIT*/
        const val baseUrl: String = "https://mindicador.cl/"
        const val apiEndPoint: String = "api"

        /*MONGODB CONECTION*/
        const val stringConexionMongoServer: String =
            "mongodb+srv://<username>:<password>@<cluster-address>/test?w=majority"
        const val stringConexionMongoLocal: String = "mongodb://localhost:27017"

        const val fileNameShPref: String = "com.rodrigo.javier.eox.persistencia"
    }
}