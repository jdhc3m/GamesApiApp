package com.example.prigui.gamesapp.ui.games_detail

import android.content.Context
import com.example.prigui.gamesapp.model.GamesModel

/**
 * Created by prigui on 29/08/2018.
 */
interface GamesDetailContract {
    interface View {
        fun showPlatformDetail(platforms: String?)
        fun showGenresDetail(genres: String?)
        fun displayError(message: String?)

    }

    interface Presenter {
        fun attachView(mvpView: View)
        fun detachView()
        fun loadGenres(context : Context, games : GamesModel)
        fun loadPlatform(context : Context, games : GamesModel)
    }
}