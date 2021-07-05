package com.yc.everylib.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.yc.everylib.init.YcEveryInit
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 *  PreferencesDataStore    先用
 *  ProtoDataStore          以后在换
 */
object YcDataStore {

    @JvmStatic
    val Context.ycDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    @JvmStatic
    suspend inline fun <reified T : Any> save(key: Preferences.Key<T>, value: T) {
        YcEveryInit.mApplication.ycDataStore.edit {
            it[key] = value
        }
    }

    @JvmStatic
    suspend inline fun <reified T : Any> saveMore(crossinline transform: suspend (MutablePreferences) -> Unit) {
        YcEveryInit.mApplication.ycDataStore.edit {
            transform(it)
        }
    }

    @JvmStatic
    suspend inline fun <reified T : Any> get(key: Preferences.Key<T>, crossinline block: (T?) -> Unit) {
        YcEveryInit.mApplication.ycDataStore.data.map {
            block(it[key])
        }.first()
    }

    @JvmStatic
    inline fun <reified T : Any> getMore(crossinline block: (Preferences) -> Unit) {
        YcEveryInit.mApplication.ycDataStore.data.map {
            block(it)
        }
    }

}

