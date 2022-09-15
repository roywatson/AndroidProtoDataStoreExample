package com.delasystems.androidprotodatastoreexample.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.delasystems.androidprotodatastoreexample.data.MyPrefsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val repository = MyPrefsRepository(getApplication<Application>().applicationContext)

    private val _iAmLearning = MutableLiveData<Boolean>(false)
    val iAmLearning: LiveData<Boolean> = _iAmLearning

    private val _Color = MutableLiveData<String>("")
    val Color: LiveData<String> = _Color

    private val _Number = MutableLiveData<Int>(-1)
    val Number: LiveData<Int> = _Number

    fun observeMyPreferensesWithFlow() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.myPreferences.collect() { prefs ->
                _iAmLearning.postValue(prefs.iAmLearning)
                _Color.postValue(prefs.favoriteColor)
                _Number.postValue(prefs.favoriteNumber)
            }
        }
    }

    fun setIAmLearning(learning: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setIAmLearning(learning)
        }
    }

    fun setColor(color: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setFavoriteColor(color)
        }
    }

    fun setNumber(number: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setFavoriteNumber(number)
        }
    }

    fun resetDefaults() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setDefaults()
        }
    }
}