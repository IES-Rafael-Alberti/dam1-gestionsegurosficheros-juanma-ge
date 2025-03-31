package data

import model.Seguro
import model.Usuario

class RepoUsuariosMem: IRepoUsuarios {

    private val seguros = mutableListOf<Seguro>()

    override fun agregar(usuario: Usuario): Boolean {
        return seguros.add(seguro)
    }

    override fun buscar(nombreUsuario: String): Usuario? {
        return seguros.find{
            it.
        }
    }
}