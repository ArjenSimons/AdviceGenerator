package com.example.random_advice_generator.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.random_advice_generator.R
import com.example.random_advice_generator.model.Advice
import kotlinx.android.synthetic.main.item.view.*

class AdviceAdapter(val advices: MutableList<Advice>, private val removeClickListener: (Advice) -> Unit):
    RecyclerView.Adapter<AdviceAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return advices.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(advices[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(advice: Advice){
            itemView.tvAdvice.text = advice.advice
            itemView.tvRating.text = advice.rating.toString()
            itemView.btnRemove.setOnClickListener{ removeClickListener(advice)}
        }
    }
}