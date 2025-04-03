package ui

import jdk.internal.org.jline.reader.EndOfFileException
import jdk.internal.org.jline.reader.LineReaderBuilder
import jdk.internal.org.jline.reader.UserInterruptException
import jdk.internal.org.jline.terminal.TerminalBuilder

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
        if(msj.startsWith("ERROR - ")){
            println(msj)
            if(pausa){
                pausar()
            }
        }else {
            println("ERROR - $msj")
            if(pausa){
                pausar()
            }
        }
    }

    override fun pedirInfo(msj: String): String {
        if(msj.isNotEmpty()){
            mostrar(msj)
        }
        return readLine()?.trim() ?: ""
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
        require(debeCumplir(valor)){error}
        return valor
    }

    override fun pedirEntero(prompt: String, error: String, errorConv: String, debeCumplir: (Int) -> Boolean): Int {
        val valor = pedirInfo(prompt).replace(',', '.').toIntOrNull()
        require(valor != null){errorConv}
        require(debeCumplir(valor)){error}
        return valor
    }

    override fun pedirInfoOculta(prompt: String): String {
        return try {
            val terminal = TerminalBuilder.builder()
                .dumb(true) // Para entornos no interactivos como IDEs
                .build()

            val reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build()

            reader.readLine(prompt, '*') // Oculta la contraseña con '*'
        } catch (e: UserInterruptException) {
            mostrarError("Entrada cancelada por el usuario (Ctrl + C).", pausa = false)
            ""
        } catch (e: EndOfFileException) {
            mostrarError("Se alcanzó el final del archivo (EOF ó Ctrl+D).", pausa = false)
            ""
        } catch (e: Exception) {
            mostrarError("Problema al leer la contraseña: ${e.message}", pausa = false)
            ""
        }
    }

    override fun pausar(msj: String) {
        pedirInfo(msj)
    }

    override fun limpiarPantalla(numSaltos: Int) {
        if (System.console() != null) {
            mostrar("\u001b[H\u001b[2J", false)
            System.out.flush()
        } else {
            repeat(numSaltos) {
                mostrar("")
            }
        }
    }

    override fun preguntar(mensaje: String): Boolean {
        val respuesta = pedirInfo("$mensaje (s/n): ").lowercase()
        if (respuesta == "s" || respuesta == "n"){
            return true
        }else{
            mostrarError("Error al leer el mensaje. ")
            return false
        }
    }

}