package model

class Usuario(
    val nombre: String,
    private var clave: String,
    val perfil: Perfil): IExportable {

    companion object {
        fun crearUsuario(datos: List<String>): Usuario? {
            return try{
            Usuario(
                datos[0],
                datos[1],
                Perfil.getPerfil(datos[2])
            )
            }catch (e: IllegalArgumentException){
                null
            }
        }
    }

    fun cambiarClave(nuevaCavleEncriptada: String){
        clave = nuevaCavleEncriptada
    }

    override fun hashCode(): Int {
        return nombre.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Usuario) return false
        return nombre == other.nombre
    }

    override fun serializar(separador: String): String{
        return "$nombre; $clave; $perfil"
    }

}