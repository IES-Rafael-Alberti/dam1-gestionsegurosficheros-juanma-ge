package data

import model.Seguro
import model.Usuario

class RepoSegurosMem: IRepoSeguros{
    protected val seguros = mutableListOf<Seguro>()

    override fun agregar(seguro: Seguro): Boolean {
        if (buscar(seguro.numPoliza) != null) return false
        return seguros.add(seguro)
    }

    override fun buscar(numPoliza: Int): Seguro? {
        return seguros.find { usuario -> usuario.numPoliza == numPoliza }
    }

    override fun eliminar(seguro: Seguro): Boolean {
        return seguros.remove(seguro)
    }

    override fun eliminar(numPoliza: Int): Boolean {
        val seguro = buscar(numPoliza)
        return seguros.remove(seguro)
    }

    override fun obtenerTodos(): List<Seguro> {
        TODO("Not yet implemented")
    }

    override fun obtener(tipoSeguro: String): List<Seguro> {
        TODO("Not yet implemented")
    }

}