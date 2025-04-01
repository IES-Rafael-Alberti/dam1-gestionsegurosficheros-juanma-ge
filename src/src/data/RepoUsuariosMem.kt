package data

import model.Perfil
import model.Usuario

class RepoUsuariosMem: IRepoUsuarios {

    private val usuarios = mutableListOf<Usuario>()

    override fun agregar(usuario: Usuario): Boolean {
        if (buscar(usuario.nombre) != null) return false
        return usuarios.add(usuario)
    }

    override fun buscar(nombreUsuario: String): Usuario? {
        return usuarios.find { usuario -> usuario.nombre == nombreUsuario }
    }

    override fun obtenerTodos(): List<Usuario> {
        return usuarios
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        usuario.cambiarClave(nuevaClave)
        return true
    }

    override fun eliminar(nombreUsuario: String): Boolean {
        val usuario = buscar(nombreUsuario)
        return usuarios.remove(usuario)
    }

    override fun eliminar(usuario: Usuario): Boolean {
        return usuarios.remove(usuario)
    }

    override fun obtener(perfil: Perfil): List<Usuario> {
        return usuarios.toList()
    }

}