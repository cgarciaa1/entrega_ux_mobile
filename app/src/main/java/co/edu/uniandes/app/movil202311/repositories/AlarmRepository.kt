package co.edu.uniandes.app.movil202311.repositories

import android.app.Application
import co.edu.uniandes.app.movil202311.models.Alarm

class AlarmRepository (val application: Application){
    private var list :  List<Alarm> = listOf();
    suspend fun refreshData() : List<Alarm>{

        var a = Alarm(name = "Cita", extra ="...", date ="2023-10-15",hour ="09:30 AM")
        var list_2 :  List<Alarm> = listOf(a);

        var  l = list;

        if(list.isEmpty()){
            l=list_2;
        }

        return l;
     }

}
