package app

import data.ICargarSegurosIniciales
import data.ICargarUsuariosIniciales
import model.Seguro
import ui.IEntradaSalida

/**
 * Clase encargada de cargar los datos iniciales de usuarios y seguros desde ficheros,
 * necesarios para el funcionamiento del sistema en modo persistente.
 *
 * @param ui Interfaz de entrada/salida para mostrar mensajes de error.
 * @param repoUsuarios Repositorio que permite cargar usuarios desde un fichero.
 * @param repoSeguros Repositorio que permite cargar seguros desde un fichero.
 */
class CargadorInicial(
    private val ui: IEntradaSalida,
    private val repoUsuarios: ICargarUsuariosIniciales,
    private val repoSeguros: ICargarSegurosIniciales,
    private val mapa: Map<String, (List<String>) -> Seguro>

){

    /**
     * Carga los usuarios desde el archivo configurado en el repositorio.
     * Muestra errores si ocurre un problema en la lectura o conversión de datos.
     */
    fun cargarUsuarios() {
        if (!repoUsuarios.cargarUsuarios()){
            ui.mostrarError("No se han podido cargar los usuarios.")
        }
    }

    /**
     * Carga los seguros desde el archivo configurado en el repositorio.
     * Utiliza el mapa de funciones de creación definido en la configuración de la aplicación
     * (ConfiguracionesApp.mapaCrearSeguros).
     * Muestra errores si ocurre un problema en la lectura o conversión de datos.
     */
    fun cargarSeguros() {
        if (!repoSeguros.cargarSeguros(mapa)){
            ui.mostrarError("No se han podido cargar los seguros.")
        }
    }

}