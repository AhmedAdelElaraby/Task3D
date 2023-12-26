package com.workdev.bigrewards
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.workdev.bigrewards.databinding.ItemSizeBinding

internal class AdapterAllCatogires(context:Context, onClickCategory: OnClickCategory) : RecyclerView.Adapter<AdapterAllCatogires.ViewHolder>() {

    private lateinit var binding: ItemSizeBinding
    private var selectedItemPosition = 0

    private val context: Context
    private var onClickCategory: OnClickCategory? =null

    init {
        this.context = context
        this.onClickCategory=onClickCategory
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): AdapterAllCatogires.ViewHolder
    { binding = ItemSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        binding.size.text = differ.currentList[position].toString()

        holder.setIsRecyclable(false)
        holder.itemView.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
        }
        if(selectedItemPosition == position) {
            onClickCategory!!.getname(differ.currentList[position].toString())
            selectedBg()
            Toast.makeText(context, ""+differ.currentList[position].toString(), Toast.LENGTH_LONG).show()
        }
        else {
            defaultBg()
           // Toast.makeText(context, ""+differ.currentList[position].name, Toast.LENGTH_LONG).show()

        }



    }

    fun defaultBg() {
        binding.size.background = null
    }
    fun selectedBg() {
        binding.size.background = context.getDrawable(R.drawable.item_selected)
    }

    override fun getItemCount(): Int {
       return differ.currentList.size

    }
    inner class ViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return  oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallback)
}