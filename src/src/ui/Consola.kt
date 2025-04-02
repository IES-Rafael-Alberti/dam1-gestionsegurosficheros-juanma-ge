package ui

class Consola(): IEntradaSalida {

    override fun mostrar(msj: String, salto: Boolean, pausa: Boolean) {
        if (salto){
            println()
        }
        if (pausa){
            pausar()
        }
    }

    override fun mostrarError(msj: String, pausa: Boolean) {
        TODO("Not yet implemented")
    }

    override fun pedirInfo(msj: String): String {
        if(msj.isNotEmpty()){
            mostrar(msj, false)
        }
        return readLine()
    }

    override fun pedirInfo(msj: String, error: String, debeCumplir: (String) -> Boolean): String {
        val valor = pedirInfo(msj)
        require(debeCumplir(valor)) {error}
        return valor
    }

    override fun pedirDouble(
        prompt: String,
        error: String,
        errorConv: String,
        debeCumplir: (Double) -> Boolean
    ): Double {
        val valor = pedirInfo(prompt).replace(',', '.').toDoubleOrNull()
        require(valor != null){errorConv}
    }

}