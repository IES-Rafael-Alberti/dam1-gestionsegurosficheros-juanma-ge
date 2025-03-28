package model

enum class Perfil(val id: String) {

    ADMIN("admin"),
    GESTION("gestion"),
    CONSULTA("consulta");

    companion object{
        fun getPerfil(valor: String): Perfil{
            return try {
                valueOf(valor)
            } catch (e: IllegalArgumentException){
                CONSULTA
            }
        }
    }

}