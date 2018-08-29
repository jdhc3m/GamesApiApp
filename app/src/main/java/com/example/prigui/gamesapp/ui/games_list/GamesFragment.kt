package com.example.prigui.gamesapp.ui.games_list

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.prigui.gamesapp.R
import com.example.prigui.gamesapp.model.GamesModel
import com.example.prigui.gamesapp.ui.games_detail.GamesDetailActivity
import com.example.prigui.gamesapp.utils.Constants
import kotlinx.android.synthetic.main.fragment_recycler_view.view.*

/**
 * Created by prigui on 27/08/2018.
 */

@SuppressLint("ValidFragment")
class GamesFragment(context: Context, private val mGamesIds: List<Int>) : Fragment(), GamesContract.View {

    private val TAG = "BookingTypeFragment"
    private lateinit var mView: View
    private var mContext = context
    private val mPresenter: GamesContract.Presenter by lazy {
        val presenter = GamesPresenter()
        presenter.attachView(this)
        presenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(R.layout.fragment_recycler_view, container, false)
        mPresenter.loadGames(mContext, mGamesIds)
        mView?.swiperefresh.setOnRefreshListener {
            mPresenter.loadGames(mContext, mGamesIds)
        }
        return mView
    }

    override fun displayGames(items: List<GamesModel>) {
        setupRecyclerView(items)
    }

    private fun setupRecyclerView(items: List<GamesModel>) {
        mView.recyclerview.layoutManager = LinearLayoutManager(mContext)

        mView.recyclerview.layoutManager = GridLayoutManager(mContext, 2)

        mView.recyclerview.adapter = GamesAdapter(mContext, items, object : GamesAdapter.OnItemClickListener {
            override fun onGameClicked(item: GamesModel) {
                val intent = Intent(context!!, GamesDetailActivity::class.java)
                intent.putExtra(Constants.EXTRA_GAMES, item)
                startActivity(intent)
            }

        })
    }


    override fun displayError(msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }


    override fun displayLoading(loading: Boolean) {
        mView.swiperefresh.isRefreshing = loading

    }

}
