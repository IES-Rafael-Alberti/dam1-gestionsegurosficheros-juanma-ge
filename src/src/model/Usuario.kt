package model

class Usuario(
    val nombre: String,
    private val clave: String,
    val perfil: Perfil): IExportable {

    companion object {
        fun crearUsuario(datos: List<String>): Usuario {
            return try{
                datos[0],
                datos[1],
                Perfil.
            }
        }
    }

    fun cambiarClave(nuevaCavleEncriptada: String){

    }

    override fun serializar(separador: String): String{
        TODO("Not yet implemented")
    }

}