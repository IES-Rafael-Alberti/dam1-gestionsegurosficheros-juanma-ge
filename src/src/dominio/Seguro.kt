package dominio

abstract class Seguro(val numPoliza: Int,
                      val dniTitular: String,
                      private val importe: Double) {

    abstract fun calcularImporteAnioSiguiente(interes: Double): Double

    abstract fun tipoSeguro(): String

    abstract fun serializar(): String

}