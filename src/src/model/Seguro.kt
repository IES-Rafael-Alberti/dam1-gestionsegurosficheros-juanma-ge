package model

abstract class Seguro(val numPoliza: Int,
                      private val dniTitular: String,
                      protected var importe: Double): IExportable {

    abstract fun calcularImporteAnioSiguiente(interes: Double): Double

    abstract fun tipoSeguro(): String

    override fun serializar(separador: String): String {
        return "$numPoliza; $dniTitular; $importe;"
    }

    override fun toString(): String {
        return "Seguro(numPoliza=$numPoliza, dniTitular=$dniTitular, importe=$importe)"
    }

    override fun hashCode(): Int {
        return numPoliza.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Seguro) return false
        return numPoliza == other.numPoliza
    }

}