package service

import data.IRepoSeguros
import model.*
import java.time.LocalDate

class GestorSeguros(
    private val repo: IRepoSeguros
): IServSeguros {

    override fun contratarSeguroHogar(
        dniTitular: String,
        importe: Double,
        metrosCuadrados: Int,
        valorContenido: Double,
        direccion: String,
        anioConstruccion: Int
    ): Boolean {
        val seguro = SeguroHogar(
            dniTitular.toInt(),
            importe.toString(),
            metrosCuadrados.toDouble(),
            valorContenido.toInt(),
            direccion.toInt(),
            anioConstruccion.toString()
        )
        return repo.agregar(seguro)
    }

    override fun contratarSeguroAuto(
        dniTitular: String,
        importe: Double,
        descripcion: String,
        combustible: String,
        tipoAuto: Auto,
        cobertura: Cobertura,
        asistenciaCarretera: Boolean,
        numPartes: Int
    ): Boolean {
        val seguro = SeguroAuto(
            dniTitular,
            importe,
            descripcion,
            combustible,
            tipoAuto,
            cobertura,
            asistenciaCarretera,
            numPartes
        )
        return repo.agregar(seguro)
    }

    override fun contratarSeguroVida(
        dniTitular: String,
        importe: Double,
        fechaNacimiento: LocalDate,
        nivelRiesgo: Riesgo,
        indemnizacion: Double
    ): Boolean {
        val seguro = SeguroVida(
            dniTitular,
            importe,
            fechaNacimiento,
            nivelRiesgo.toString(),
            indemnizacion.toString()
        )
        return repo.agregar(seguro)
    }

    override fun eliminarSeguro(numPoliza: Int): Boolean {
        return repo.eliminar(numPoliza)
    }

    override fun consultarTodos(): List<Seguro> {
        return repo.obtenerTodos()
    }

    override fun consultarPorTipo(tipoSeguro: String): List<Seguro> {
        return repo.obtener(tipoSeguro)
    }

}
