package com.yc.shadowevery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.datastore.preferences.core.stringPreferencesKey
import com.yc.everylib.data.datastore.YcDataStore
import kotlinx.coroutines.runBlocking

class TestDatastore : AppCompatActivity() {
    private val data = stringPreferencesKey("key_test_data")
    private val data2 = stringPreferencesKey("key_test_data_2")
    private val data3 = stringPreferencesKey("key_test_data_3")
    private var changeData = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_datastore_activity)
        findViewById<Button>(R.id.btnTestDatastoreRead).setOnClickListener {
            runBlocking {
                YcDataStore.get(data) {
                    changeData = it ?: ""
                    Log.e("tag", "read-$it")
                }
            }
        }
        findViewById<Button>(R.id.btnTestDatastoreWrite).setOnClickListener {
            runBlocking {
                YcDataStore.save(data, changeData)
            }
        }
        findViewById<Button>(R.id.btnTestDatastoreRead2).setOnClickListener {
            runBlocking {
                YcDataStore.getMore {
                    Log.e("tag", "read-data:${it[data]}-data2:${it[data2]}-data3:${it[data3]}")
                }
            }
        }
        findViewById<Button>(R.id.btnTestDatastoreWrite2).setOnClickListener {
            runBlocking {
                YcDataStore.saveMore {
                    it[data] = "1:$changeData"
                    it[data2] = "2:$changeData"
                    it[data3] = "3:$changeData"
                }
            }
        }
        findViewById<Button>(R.id.btnTestDatastoreAdd).setOnClickListener {
            changeData += "-1-"
        }
    }
}