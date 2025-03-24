package model

class SeguroAuto(numPoliza: Int,
                 dniTitular: String,
                 importe: Double,
                 val descripcion: String,
                 val combustible: String,
                 val tipoAuto: Auto,
                 val tipoCobertura: String,
                 val asistenciaCarretera: Boolean,
                 val numPartes: Int,
): Seguro(numPoliza, dniTitular, importe) {

    override fun serializar(): String {
        return "$numPoliza;$dniTitular;$importe;$descripcion;$combustible$tipoAuto;$tipoCobertura$asistenciaCarretera;$numPartes;{${tipoSeguro()}}"
    }

    override fun tipoSeguro(): String {
        return "Seguro de coche."
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        return
    }

}