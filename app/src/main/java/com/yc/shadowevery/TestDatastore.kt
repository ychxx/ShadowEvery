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
        findViewById<Button>(R.id.btnTestDatastoreAdd).setOnClickListener {
            changeData += "-1-"
        }
    }
}