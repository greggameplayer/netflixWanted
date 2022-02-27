package com.greg.netflixwanted.controllers

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.greg.netflixwanted.R
import com.greg.netflixwanted.Search


class SearchListAdapter(
    activity: Activity,
    private val data: ArrayList<Search>
): RecyclerView.Adapter<SearchListAdapter.VH>() {

    var ctx = activity
    var retrofitController : RetrofitController = RetrofitController()

    inner class VH(itemView: View):RecyclerView.ViewHolder(itemView){
        var img: ImageView
        var card: CardView

        init {
            img= itemView.findViewById(R.id.imgView)
            card = itemView.findViewById(R.id.cardview)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
    private val inflater : LayoutInflater

    init {
        this.inflater = activity.layoutInflater
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = inflater.inflate(R.layout.list_item_search, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(vh: VH, position: Int) {
        //To set data in the item view
//        vh.img.imageAlpha = data.get(position).body

        vh.card.setCardBackgroundColor(Color.WHITE);

//        vh.card.setOnClickListener{
//            //To redirect to EmailDetails (activity)
//            val intent = Intent(ctx, EmailDetails::class.java)
//            intent.putExtra("from", data.get(position).from)
//            intent.putExtra("content", data.get(position).body)
//            intent.putExtra("subject", data.get(position).subject)
//            ctx.startActivity(intent)
//        }
    }


}