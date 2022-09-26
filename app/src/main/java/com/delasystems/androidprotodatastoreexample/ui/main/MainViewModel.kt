/*
Copyright (C) 2022 Roy Watson

Permission is hereby granted, free of charge, to any person obtaining a copy of this
software and associated documentation files (the "Software"), to deal in the Software
without restriction, including without limitation the rights to use, copy, modify, merge,
publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies
or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
OR OTHER DEALINGS IN THE SOFTWARE.
*/
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