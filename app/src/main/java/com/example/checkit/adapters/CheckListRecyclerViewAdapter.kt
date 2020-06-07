package com.example.checkit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.checkit.R
import com.example.checkit.model.Checklist

class CheckListRecyclerViewAdapter(
    private val mListener: CheckListAdapterInterface
) :
    RecyclerView.Adapter<CheckListRecyclerViewAdapter.ViewMovieHolder>() {

    private var checkList = ArrayList<Checklist>()
    private var content: String = ""

    class ViewMovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var description: TextView = itemView.findViewById(R.id.content)
        var date: TextView = itemView.findViewById(R.id.date)
        var deleteButton: ImageView = itemView.findViewById(R.id.delete)
        var card: CardView = itemView.findViewById(R.id.card_id)
    }

    interface CheckListAdapterInterface {
        fun onDeleteClick(position: Int)
        fun onClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewMovieHolder =
        ViewMovieHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_recyclerview, parent, false)
        )

    override fun getItemCount(): Int {
        return checkList.size
    }

    override fun onBindViewHolder(holder: ViewMovieHolder, position: Int) {
        holder.deleteButton.setOnClickListener {
            mListener.onDeleteClick(position)
        }

        holder.card.setOnClickListener {
            mListener.onClick(position)
        }

        holder.title.text = checkList[position].title
        for (i in checkList[position].checkBoxes) {
            content = "$content\n${i}"
        }
        holder.description.text = content
        holder.date.text = checkList[position].date
    }

    fun getList(item: ArrayList<Checklist>) {
        checkList.clear()
        checkList.addAll(item)
    }
}