package utils

import model.IExportable
import ui.IEntradaSalida
import java.io.File

class Ficheros(private val ui: IEntradaSalida): IUtilFicheros {

    override fun leerArchivo(ruta: String): List<String> {
        return try {
            File(ruta).readLines()
        }catch (e: IllegalArgumentException){
            ui.mostrarError("Error al leer el mensaje.")
            emptyList()
        }
    }

    override fun agregarLinea(ruta: String, linea: String): Boolean {
        return try {
            File(ruta).appendText("$linea\n")
            true
        }catch (e: IllegalArgumentException){
            ui.mostrarError("Error al intentar escribir.")
            false
        }
    }

    override fun <T : IExportable> escribirArchivo(ruta: String, elementos: List<T>): Boolean {
        return try {
            File(ruta).writeText(elementos.joinToString("\n") {it.serializar()} )
            true
        }catch (e: IllegalArgumentException){
            ui.mostrarError("Error al intentar escribir el archivo.")
            false
        }
    }

    override fun existeFichero(ruta: String): Boolean {
        return File(ruta).isFile
    }

    override fun existeDirectorio(ruta: String): Boolean {
        return File(ruta).isDirectory
    }

}