package com.example.prigui.gamesapp.ui.games_detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import com.example.prigui.gamesapp.R
import com.example.prigui.gamesapp.model.GamesModel
import com.example.prigui.gamesapp.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_games_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class GamesDetailActivity : AppCompatActivity(), GamesDetailContract.View {

    private lateinit var mGamesModel : GamesModel

    private val mPresenter: GamesDetailContract.Presenter by lazy {
        val presenter = GamesDetailPresenter()
        presenter.attachView(this)
        presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_detail)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        setup()
    }

    private fun setup() {
        getIntentExtra()
        setupLayout()
        mPresenter.loadGenres(this, mGamesModel)
        mPresenter.loadPlatform(this, mGamesModel)

    }

    private fun setupLayout() {
        if (!TextUtils.isEmpty(mGamesModel.image)) {
            Picasso.with(this).load(mGamesModel.image)
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(gamesImageIv)
        }

        gamesDetailName.text = mGamesModel.name

        gamesDetailSummary.text = mGamesModel.summary
    }

    private fun getIntentExtra() {
        mGamesModel = intent.getSerializableExtra(Constants.EXTRA_GAMES) as GamesModel
    }

    override fun showPlatformDetail(platforms: String?) {
        gamesDetailPlatforms.text = getString(R.string.platform) + platforms.toString()
    }

    override fun showGenresDetail(genres: String?) {
        gamesDetailGenres.text = getString(R.string.genres) + genres.toString()
    }

    override fun displayError(message: String?) {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}


