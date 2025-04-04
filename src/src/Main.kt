import ui.Consola

fun main(){

    val con = Consola()

    fun validarNombreNoVacioYMayorQue5(nombre: String): Boolean{
        return nombre.isNotEmpty() && nombre.length >= 5
    }

    val nombre = con.pedirInfo("Introduzca un nombre> ")
    if (!validarNombreNoVacioYMayorQue5(nombre)) {
        con.mostrarError("Nombre incorrecto!")
    }

    val nombre1 = con.pedirInfo("Introduzca un nombre 1", "Nombre incorrecto!",
        ::validarNombreNoVacioYMayorQue5)

}