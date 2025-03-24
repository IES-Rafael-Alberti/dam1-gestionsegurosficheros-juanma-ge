package model

class SeguroHogar(numPoliza: Int,
                  dniTitular: String,
                  importe: Double,
                  val metrosCuadrados: Double,
                  val valorContenido: Double,
                  val direccion: String
): Seguro(numPoliza, dniTitular, importe) {

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        return importe * (1 + interes / 100)
    }

    override fun serializar(): String {
        return "$numPoliza;$dniTitular;$importe;$metrosCuadrados;$valorContenido;$direccion;${tipoSeguro()}"
    }

    override fun tipoSeguro(): String {
        return "Seguro de hogar."
    }

}