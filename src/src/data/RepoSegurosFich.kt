package data

import model.Seguro
import model.SeguroAuto
import model.SeguroHogar
import model.SeguroVida
import ui.IEntradaSalida
import utils.IUtilFicheros

class RepoSegurosFich(
    private val rutaArchivo: String,
    private val fich: IUtilFicheros,
    private val ui: IEntradaSalida
) : RepoSegurosMem(), ICargarSegurosIniciales {

    override fun agregar(seguro: Seguro): Boolean {
        return if (fich.agregarLinea(rutaArchivo, seguro.serializar())) {
            super.agregar(seguro)
        } else {
            ui.mostrarError("Error al guardar el seguro en el archivo")
            false
        }
    }

    override fun eliminar(seguro: Seguro): Boolean {
        val segurosActualizados = seguros.filter { it != seguro }
        return if (fich.escribirArchivo(rutaArchivo, segurosActualizados)) {
            super.eliminar(seguro)
        } else {
            ui.mostrarError("Error al actualizar el archivo de seguros")
            false
        }
    }

    override fun cargarSeguros(mapa: Map<String, (List<String>) -> Seguro>): Boolean {
        val lineas = fich.leerArchivo(rutaArchivo)
        if (lineas.isEmpty()) return false

        val segurosCargados = mutableListOf<Seguro>()

        lineas.forEach { linea ->
            val datos = linea.split(";")
            val tipoSeguro = datos.last()
            val constructor = mapa[tipoSeguro]

            if (constructor != null) {
                constructor(datos.dropLast(1))?.let { seguro ->
                    segurosCargados.add(seguro)
                }
            }
        }

        segurosCargados.forEach { seguro -> super.agregar(seguro) }
        actualizarContadores(segurosCargados)
        return true
    }

    private fun actualizarContadores(seguros: List<Seguro>) {
        val maxHogar = seguros.filter { it.tipoSeguro() == "SeguroHogar" }.maxOfOrNull { it.numPoliza }
        val maxAuto = seguros.filter { it.tipoSeguro() == "SeguroAuto" }.maxOfOrNull { it.numPoliza }
        val maxVida = seguros.filter { it.tipoSeguro() == "SeguroVida" }.maxOfOrNull { it.numPoliza }

        if (maxHogar != null) SeguroHogar.numPolizasHogar = maxHogar
        if (maxAuto != null) SeguroAuto.numPolizasAuto = maxAuto
        if (maxVida != null) SeguroVida.numPolizasVida = maxVida
    }
}