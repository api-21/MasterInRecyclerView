package com.xpresscure.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import com.google.gson.Gson
import com.xpresscure.recyclerview.databinding.ActivityMainBinding
import com.xpresscure.recyclerview.databinding.ItemUpdateLytBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var itemUpdateLytBinding: ItemUpdateLytBinding
    val rcvData = mutableListOf<DataFactor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_main)


        for (i in 0..5){
            rcvData.add(
                DataFactor.ItemData(title = "XpressCure", subTitle = "this is testing subtitle")
            )
        }
        initRecyclerView(rcvData)
    }

    fun initRecyclerView(rcvData: MutableList<DataFactor>) {

        val adapter = RecyclerAdapter()
        binding.mainRcv.adapter = adapter
        adapter.itemList = rcvData
        binding.mainRcv.layoutManager = LinearLayoutManager(this)
        val click = object  : OnRcvItemClick{
            override fun onClick(position: Int) {

                itemUpdateLytBinding = ItemUpdateLytBinding.inflate(LayoutInflater.from(this@MainActivity))
                val alertDialog = MaterialAlertDialogBuilder(this@MainActivity).create()
                alertDialog.setView(itemUpdateLytBinding.root)
                alertDialog.show()


                itemUpdateLytBinding.btnUpdateItem.setOnClickListener {
                    val updateData = DataFactor.ItemData(isActive = itemUpdateLytBinding.btnVisibleValue.text.toString()?.toInt(), title = "${itemUpdateLytBinding.updateTitle.text}", subTitle = "${itemUpdateLytBinding.updateSubTitle.text}")
                    rcvData.set(position,updateData)
                    adapter.notifyItemChanged(position)
                    alertDialog.dismiss()
                }

                itemUpdateLytBinding.btnDeleteItem.setOnClickListener {
                    rcvData.removeAt(position)
                    adapter.notifyItemChanged(position)
                    alertDialog.dismiss()
                }

                itemUpdateLytBinding.btnShareItem.setOnClickListener {

                    val gson = Gson()
                    val data = gson.toJson(rcvData[position])
                    val getData = gson.fromJson("$data",DataFactor.ItemData::class.java)

                    val intent = Intent()
                    intent.action = Intent.ACTION_SEND
                    intent.putExtra(Intent.EXTRA_STREAM,"Worknig")
                    intent.type ="text/plain"
                    val intentData = Intent.createChooser(intent,getString(R.string.app_name))
                    startActivity(Intent.createChooser(intent,getString(R.string.app_name)))
                   // Toast.makeText(this@MainActivity, "${getData.title}", Toast.LENGTH_SHORT).show()
                }

            }

        }
        adapter.onItemClick = click
    }
}