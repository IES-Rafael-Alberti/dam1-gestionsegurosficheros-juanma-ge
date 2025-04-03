package data

import model.Usuario
import ui.IEntradaSalida
import utils.IUtilFicheros

class RepoUsuariosFich(
    private val rutaArchivo: String,
    private val fich: IUtilFicheros,
    private val ui: IEntradaSalida
): RepoUsuariosMem(), ICargarUsuariosIniciales {

    override fun agregar(usuario: Usuario): Boolean {
        return if (fich.agregarLinea(rutaArchivo, usuario.serializar())) {
            super.agregar(usuario)
        } else {
            ui.mostrarError("Error al guardar el usuario en el archivo")
            false
        }
    }

    override fun eliminar(usuario: Usuario): Boolean {
        val usuariosActualizados = usuarios.filter { it != usuario }
        return if (fich.escribirArchivo(rutaArchivo, usuariosActualizados)) {
            super.eliminar(usuario)
        } else {
            ui.mostrarError("Error al actualizar el archivo de usuarios")
            false
        }
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        usuario.cambiarClave(nuevaClave)
        return fich.escribirArchivo(rutaArchivo, usuarios)
    }

    override fun cargarUsuarios(): Boolean {
        val lineas = fich.leerArchivo(rutaArchivo)
        if (lineas.isEmpty()) return false

        lineas.forEach { linea ->
            val datos = linea.split(";")
            Usuario.crearUsuario(datos)?.let { usuario ->
                super.agregar(usuario)
            }
        }
        return true
    }
}
