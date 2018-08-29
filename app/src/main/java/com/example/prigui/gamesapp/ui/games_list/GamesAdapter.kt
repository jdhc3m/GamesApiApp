package com.example.prigui.gamesapp.ui.games_list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prigui.gamesapp.R
import com.example.prigui.gamesapp.model.GamesModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_game.view.*

/**
 * Created by prigui on 27/08/2018.
 */
class GamesAdapter(private val mContext: Context, val mGamesList: List<GamesModel>, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return mGamesList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_game, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        //Render image using Picasso library
        if (!TextUtils.isEmpty(mGamesList[position].image)) {
            Picasso.with(mContext).load(mGamesList[position].image)
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(holder?.gamesImageIv)
        }

        //Setting text view title
        holder?.gamesNameTv!!.text = mGamesList[position].name
        holder?.gamesItemListLl!!.setOnClickListener {
            onItemClickListener.onGameClicked(mGamesList[position])
        }

    }


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val gamesNameTv = view.gamesNameTv!!
        val gamesImageIv = view.gamesImageIv!!
        val gamesItemListLl = view.gamesItemListLl!!
    }

    interface OnItemClickListener {
        fun onGameClicked(item: GamesModel)
    }
}