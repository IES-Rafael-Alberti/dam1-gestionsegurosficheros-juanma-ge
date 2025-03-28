package model

import java.time.LocalDate

class SeguroVida: Seguro {

    private val fechaNac: LocalDate
    private val nivelRiesgo: String
    private val indemnizacion: String

    constructor(
        dniTitular: String,
        importe: Double,
        fechaNac: LocalDate,
        nivelRiesgo: String,
        indemnizacion: String,
    ): super(++numPolizasVida, dniTitular, importe){
        this.fechaNac = fechaNac
        this.nivelRiesgo = nivelRiesgo
        this.indemnizacion = indemnizacion
    }

    constructor(numPoliza: Int,
                dniTitular: String,
                importe: Double,
                fechaNac: LocalDate,
                nivelRiesgo: String,
                indemnizacion: String,
    ): super(numPoliza, dniTitular, importe){
        this.fechaNac = fechaNac
        this.nivelRiesgo = nivelRiesgo
        this.indemnizacion = indemnizacion
    }

    companion object{
        private var numPolizasVida: Int = 800000

        fun crearSeguro(datos: List<String>): SeguroVida {
            return SeguroVida(
                numPoliza = datos[0].toInt(),
                dniTitular = datos[1],
                importe = datos[2].toDouble(),
                fechaNac = LocalDate.parse(datos[3]),
                nivelRiesgo = datos[4],
                indemnizacion = datos[5],
            )
        }
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        return importe
    }

    override fun serializar(separador: String): String {
        return "$numPoliza;${super.serializar(separador)};$fechaNac;$nivelRiesgo;$indemnizacion"
    }

    override fun toString(): String {
        return "SeguroHogar(${super.toString().removePrefix("Seguro").removeSuffix(")")}, " +
                "fecha de nacimiento=$fechaNac, nivel de riesgo=$nivelRiesgo, " +
                "indemnizacion=$indemnizacion"
    }

}