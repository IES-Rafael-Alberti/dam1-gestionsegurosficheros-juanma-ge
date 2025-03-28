package model

class SeguroHogar: Seguro {

    private val metrosCuadrados: Int
    private val valorContenido: Int
    private val direccion: String

    constructor(
        dniTitular: String,
        importe: Double,
        metrosCuadrados: Int,
        valorContenido: Int,
        direccion: String
    ) : super(++numPolizasHogar, dniTitular, importe) {
        this.metrosCuadrados = metrosCuadrados
        this.valorContenido = valorContenido
        this.direccion = direccion
    }

    constructor(
        numPoliza: Int,
        dniTitular: String,
        importe: Double,
        metrosCuadrados: Int,
        valorContenido: Int,
        direccion: String
    ) : super(numPoliza, dniTitular, importe) {
        this.metrosCuadrados = metrosCuadrados
        this.valorContenido = valorContenido
        this.direccion = direccion
    }

    companion object{
        private var numPolizasHogar: Int =  10000

        fun crearSeguro(datos: List<String>): SeguroHogar{
            return SeguroHogar(
                numPoliza = datos[0].toInt(),
                dniTitular = datos[1],
                importe = datos[2].toDouble(),
                metrosCuadrados = datos[3].toInt(),
                valorContenido = datos[4].toInt(),
                direccion = datos[5]
            )
        }
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        return importe
    }

    override fun serializar(separador: String): String {
        return "${super.serializar(separador)};$metrosCuadrados;$valorContenido;$direccion;${tipoSeguro()}"
    }

    override fun tipoSeguro(): String {
        return "SeguroHogar(${super.toString().removePrefix("Seguro").removeSuffix(")")}, " +
                "metrosCuadrados=$metrosCuadrados, valorContenido=$valorContenido, " +
                "direccion='$direccion)"
    }

}