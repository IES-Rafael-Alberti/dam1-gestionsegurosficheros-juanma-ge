package model

enum class Auto(val vehiculo: String) {

    COCHE("Coche"),
    MOTO("Moto"),
    CAMION("Camion");

    companion object {
        fun getAuto(valor: String): Auto {
            return try {
                valueOf(valor)
            }catch (e: IllegalArgumentException){
                COCHE
            }
        }
    }

}