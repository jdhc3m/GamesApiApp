package com.example.prigui.gamesapp.ui.games_list

import android.content.Context
import com.example.prigui.gamesapp.model.GamesModel
import com.example.prigui.gamesapp.model.PlatformsModel

/**
 * Created by prigui on 28/08/2018.
 */
interface GamesContract {

    interface View {
        fun displayGames(games: List<GamesModel>)
        fun displayLoading(isFavorite: Boolean)
        fun displayError(message: String?)

    }

    interface Presenter {
        fun attachView(mvpView: View)
        fun detachView()
        fun loadGames(context: Context, gamesIds : List<Int>)
    }


}