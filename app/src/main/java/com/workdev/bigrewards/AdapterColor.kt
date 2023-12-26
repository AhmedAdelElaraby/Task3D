package com.workdev.bigrewards
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.workdev.bigrewards.databinding.ItemColorBinding

import com.workdev.bigrewards.databinding.ItemSizeBinding

internal class AdapterColor(context:Context,onClickCategory: OnClickCategory) : RecyclerView.Adapter<AdapterColor.ViewHolder>() {

    private lateinit var binding: ItemColorBinding
    private var selectedItemPosition = 0

    private val context: Context
    private var onClickCategory: OnClickCategory? =null

    init {
        this.context = context
        this.onClickCategory=onClickCategory
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): AdapterColor.ViewHolder
    { binding = ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        binding.name.text = differ.currentList[position]
        binding.image.setImageResource(R.color.purple_700)

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
        binding.image.background = null
        binding.name.setTextColor(context.getColor(R.color.ss))
    }
    fun selectedBg() {
        binding.image.background = context.getDrawable(R.drawable.item_selected)
        binding.name.setTextColor(Color.BLACK)
        binding.name.setTypeface(Typeface.DEFAULT_BOLD)

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