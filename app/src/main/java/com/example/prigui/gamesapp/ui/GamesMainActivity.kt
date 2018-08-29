package com.example.prigui.gamesapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.prigui.gamesapp.R
import com.example.prigui.gamesapp.model.GamesModel
import com.example.prigui.gamesapp.model.PlatformsModel
import com.example.prigui.gamesapp.helpers.ViewPagerAdapter
import com.example.prigui.gamesapp.ui.games_platform.GamesPlatformContract
import com.example.prigui.gamesapp.ui.games_platform.GamesPlatformPresenter
import com.example.prigui.gamesapp.ui.games_list.GamesFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class GamesMainActivity : AppCompatActivity(), GamesPlatformContract.View {
   

    private val mPresenter: GamesPlatformContract.Presenter by lazy {
        val presenter = GamesPlatformPresenter()
        presenter.attachView(this)
        presenter
    }

    private lateinit var mAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title = getString(R.string.toolbar_title)
        mPresenter.loadItems(this)
    }

    override fun displayPlatforms(platforms: List<PlatformsModel>) {
        setupViewPager(platforms)
        
    }

    private fun setupViewPager(platformsModel: List<PlatformsModel>) {
        mAdapter = ViewPagerAdapter(supportFragmentManager)

        platformsModel.forEach {
            mAdapter.addFrag(GamesFragment(this, it.games), it.name.toString())
        }
        gamesVp.adapter = mAdapter
        gamesTl.setupWithViewPager(gamesVp)
        mAdapter.notifyDataSetChanged()
    }


    override fun displayGames(movies: List<GamesModel>) {
    }

    override fun displayLoading(isFavorite: Boolean) {
    }

    override fun displayError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}