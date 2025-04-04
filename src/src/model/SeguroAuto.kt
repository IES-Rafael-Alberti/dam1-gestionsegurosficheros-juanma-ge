package model

class SeguroAuto: Seguro{

    private val descripcion: String
    private val combustible: String
    private val tipoAuto: Auto
    private val tipoCobertura: String
    private val asistenciaCarretera: Boolean
    private val numPartes: Int

    constructor(
                dniTitular: String,
                importe: Double,
                descripcion: String,
                combustible: String,
                tipoAuto: Auto,
                tipoCobertura: Cobertura,
                asistenciaCarretera: Boolean,
                numPartes: Int,
    ): super(++numPolizasAuto, dniTitular, importe){
        this.descripcion = descripcion
        this.combustible = combustible
        this.tipoAuto = tipoAuto
        this.tipoCobertura = tipoCobertura.toString()
        this.asistenciaCarretera = asistenciaCarretera
        this.numPartes = numPartes
    }

    private constructor(numPoliza: Int,
                dniTitular: String,
                importe: Double,
                descripcion: String,
                combustible: String,
                tipoAuto: Auto,
                tipoCobertura: Cobertura,
                asistenciaCarretera: Boolean,
                numPartes: Int,
    ): super(numPoliza, dniTitular, importe){
        this.descripcion = descripcion
        this.combustible = combustible
        this.tipoAuto = tipoAuto
        this.tipoCobertura = tipoCobertura.toString()
        this.asistenciaCarretera = asistenciaCarretera
        this.numPartes = numPartes
    }

    companion object {
        var numPolizasAuto: Int = 400000
        private val PORCENTAJE_INCREMENTO_PARTES = 2

        fun crearSeguro(datos: List<String>): SeguroAuto{
            return SeguroAuto(
                numPoliza = datos[0].toInt(),
                dniTitular = datos[1],
                importe = datos[2].toDouble(),
                descripcion = datos[3],
                combustible = datos[4],
                tipoAuto = Auto.valueOf(datos[5]),
                tipoCobertura = Cobertura.valueOf(datos[6]),
                asistenciaCarretera = datos[7].toBoolean(),
                numPartes = datos[8].toInt()
            )
        }
    }

    override fun serializar(separador: String): String {
        return "$numPoliza;${super.serializar(separador)};$descripcion;$combustible$tipoAuto;$tipoCobertura$asistenciaCarretera;$numPartes"
    }

    override fun tipoSeguro(): String {
        return "SeguroHogar(${super.toString().removePrefix("Seguro").removeSuffix(")")}, " +
                "descripcion=$descripcion, combustible=$combustible, " +
                "Tipo de auto=$tipoAuto, Tipo de cobertura=$tipoCobertura)" +
                "asistencia de carretera=$asistenciaCarretera, numero de partes=$numPartes"
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        return importe
    }

}