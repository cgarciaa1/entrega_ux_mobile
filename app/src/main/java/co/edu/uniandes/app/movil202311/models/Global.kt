package co.edu.uniandes.app.movil202311.models

import android.app.Application

public class Global : Application() {
    companion object {
        @JvmField
        var login: Boolean = false
    }
}