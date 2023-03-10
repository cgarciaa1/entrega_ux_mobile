package co.edu.uniandes.app.movil202311.viewmodels

import android.app.Application
import androidx.lifecycle.*
import co.edu.uniandes.app.movil202311.models.Alarm
import co.edu.uniandes.app.movil202311.repositories.AlarmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlarmViewModel(application: Application) :  AndroidViewModel(application) {

    private val alarmsRepository = AlarmRepository(application)

    private val _alarms = MutableLiveData<List<Alarm>>()

    val alarms: LiveData<List<Alarm>>
        get() = _alarms

    private var _eventNetworkError = MutableLiveData(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch  (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    _alarms.postValue(alarmsRepository.refreshData())
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlarmViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlarmViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
