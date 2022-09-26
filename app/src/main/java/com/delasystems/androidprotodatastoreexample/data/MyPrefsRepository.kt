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
package com.delasystems.androidprotodatastoreexample.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.delasystems.androidprotodatastoreexample.MyPrefs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

class MyPrefsRepository(private val context: Context) {

    private val Context.settingsDataStore: DataStore<MyPrefs> by dataStore(
        fileName = "my_prefences.pb",
        serializer = MyPrefsSerializer
    )

    val myPreferences: Flow<MyPrefs> = context.settingsDataStore.data
        .catch {
            emit(MyPrefs.getDefaultInstance())
        }

    suspend fun setIAmLearning(learning: Boolean) {
        context.settingsDataStore.updateData { currentValues ->
            currentValues.toBuilder().setIAmLearning(learning).build()
        }
    }

    suspend fun setFavoriteColor(color: String) {
        context.settingsDataStore.updateData { currentValues ->
            currentValues.toBuilder().setFavoriteColor(color).build()
        }
    }

    suspend fun setFavoriteNumber(number: Int) {
        context.settingsDataStore.updateData { currentValues ->
            currentValues.toBuilder().setFavoriteNumber(number).build()
        }
    }

    suspend fun setDefaults() {
        context.settingsDataStore.updateData { currentValues ->
            currentValues.toBuilder().clear().build()
        }
    }

}