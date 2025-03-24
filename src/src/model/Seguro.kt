package model

abstract class Seguro(val numPoliza: Int,
                      val dniTitular: String,
                      protected var importe: Double) {

    abstract fun calcularImporteAnioSiguiente(interes: Double): Double

    abstract fun tipoSeguro(): String

    abstract fun serializar(): String

}