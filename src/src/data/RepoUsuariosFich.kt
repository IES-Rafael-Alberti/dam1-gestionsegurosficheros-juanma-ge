package data

import utils.IUtilFicheros

class RepoUsuariosFich(
    private val rutaArchivo: String,
    private val fich: IUtilFicheros
): RepoUsuariosMem(), ICargarUsuariosIniciales {



}