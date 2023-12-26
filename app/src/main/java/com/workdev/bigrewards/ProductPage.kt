package com.workdev.bigrewards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.workdev.bigrewards.databinding.ActivityProductPageBinding

class ProductPage : AppCompatActivity(),OnClickCategory {
    lateinit var binding:ActivityProductPageBinding
    var arraysize=ArrayList<String>()
    var arrayColor=ArrayList<String>()
    val imageList = ArrayList<SlideModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=DataBindingUtil.setContentView(this,R.layout.activity_product_page)

        imageList.add(SlideModel(R.drawable.taskimage,ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.taskimage,ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.taskimage,ScaleTypes.FIT))

        binding.imageSlider.setImageList(imageList, ScaleTypes.FIT)
        arraysize.add("L")
        arraysize.add("XL")
        arraysize.add("XXL")

        arrayColor.add("Red")
        arrayColor.add("Green")
        arrayColor.add("Blue")

binding.TD.setOnClickListener {

        val intent=Intent(this@ProductPage,MainActivity::class.java)
        startActivity(intent)
}




        val adapterSize = AdapterAllCatogires(this,this)
        val  linearLayoutManager = LinearLayoutManager(this@ProductPage, LinearLayoutManager.HORIZONTAL,false)
        binding.recSize.setLayoutManager(linearLayoutManager)
        binding.recSize.adapter=adapterSize
        adapterSize.differ.submitList(arraysize)


        val adapterColor = AdapterColor(this,this)
        val  linearColor = LinearLayoutManager(this@ProductPage, LinearLayoutManager.HORIZONTAL,false)
        binding.recColor.setLayoutManager(linearColor)
        binding.recColor.adapter=adapterColor
        adapterColor.differ.submitList(arrayColor)

    }

    override fun getname(name: String) {
       makeText(this@ProductPage,""+name,Toast.LENGTH_LONG).show()
    }
}