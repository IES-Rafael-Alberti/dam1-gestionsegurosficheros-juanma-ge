package service

import data.IRepoUsuarios
import model.Perfil
import model.Usuario
import utils.IUtilSeguridad

class GestorUsuarios(
    private val repo: IRepoUsuarios,
    private val seguridad: IUtilSeguridad
): IServUsuarios {

    override fun iniciarSesion(nombre: String, clave: String): Perfil? {
        val usuario = buscarUsuario(nombre)
        if (usuario != null && seguridad.verificarClave(nombre, clave)) {
            return usuario.perfil
        }
        return null
    }

    override fun agregarUsuario(nombre: String, clave: String, perfil: Perfil): Boolean {
        val claveEncriptada = seguridad.encriptarClave(clave)
        return repo.agregar(Usuario(nombre, claveEncriptada, perfil))
    }

    override fun eliminarUsuario(nombre: String): Boolean {
        val usuario = buscarUsuario(nombre)
        return if (usuario != null) {
            repo.eliminar(usuario)
        } else {
            return false
        }
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        val claveEncriptada = seguridad.encriptarClave(nuevaClave)
        return repo.cambiarClave(usuario, claveEncriptada)
    }

    override fun buscarUsuario(nombre: String): Usuario? {
        return repo.buscar(nombre)
    }

    override fun consultarTodos(): List<Usuario> {
        return repo.obtenerTodos()
    }

    override fun consultarPorPerfil(perfil: Perfil): List<Usuario> {
        return repo.obtener(perfil)
    }

}