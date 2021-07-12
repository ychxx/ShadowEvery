package com.yc.shadowevery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yc.everylib.recycleView.YcRecyclerViewAdapter
import com.yc.everylib.recycleView.YcViewHolder
import com.yc.everylib.widget.dialog.YcLoadingDialog
import com.yc.shadowevery.databinding.YcCommonItemBinding

class MainActivity : AppCompatActivity() {
    private val loadingDialog: YcLoadingDialog by lazy {
        YcLoadingDialog(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.rvMain)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = object : YcRecyclerViewAdapter<String, YcCommonItemBinding>(YcCommonItemBinding::inflate) {
            override fun onUpdate(holder: YcViewHolder<YcCommonItemBinding>, position: Int, data: String) {
                holder.viewBinding.tvCommonItem.text = data
                holder.viewBinding.tvCommonItem.setOnClickListener {
                    next(position)
                }
            }
        }.apply {
            addData("测试progressBar")
            addData("测试Datastore")
        }

    }

    fun next(position: Int) {
        when (position) {
            0 -> {
                loadingDialog.show()
            }
            1 -> {
                startActivity(Intent(this, TestDatastore::class.java))
            }
        }
    }
}