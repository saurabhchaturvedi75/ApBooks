package com.example.apbooks

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(private val context: Context, private val myData: MyData) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.eachitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = myData[position]

        holder.titleTextView.text = book.title
        holder.subtitleTextView.text = book.subtitle
        if (book.amount == 0 && book.originalAmount == 0) {
            holder.amountStatusTextView.text = "Available for Free"
        } else {
            holder.amountStatusTextView.text = "Suggested donation: "
        }
        if (book.amount != 0) {
            holder.amountTextView.text = "₹" + book.amount.toString()
        }
        if (book.originalAmount != 0) {
            holder.originalAmountTextView.text = "₹" + book.originalAmount.toString()

            // for Strikethrough Text
            holder.originalAmountTextView.setPaintFlags(holder.originalAmountTextView.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
        }


        // Load the cover image using Glide library
        Glide.with(context)
            .load(book.coverImage)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_report_image)
            .into(holder.bookImageView)
    }


    override fun getItemCount(): Int {
        return myData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookImageView: ImageView = itemView.findViewById(R.id.productImage)
        val titleTextView: TextView = itemView.findViewById(R.id.productTitle)
        val subtitleTextView: TextView = itemView.findViewById(R.id.productSubitle)

        val amountTextView: TextView = itemView.findViewById(R.id.amount)
        val originalAmountTextView: TextView = itemView.findViewById(R.id.originalAmount)
        val amountStatusTextView: TextView = itemView.findViewById(R.id.amountStatus)
    }
}