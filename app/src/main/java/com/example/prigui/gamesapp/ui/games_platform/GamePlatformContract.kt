package com.example.prigui.gamesapp.ui.games_platform

import android.content.Context
import com.example.prigui.gamesapp.model.GamesModel
import com.example.prigui.gamesapp.model.PlatformsModel

/**
 * Created by prigui on 27/08/2018.
 */


interface GamesPlatformContract {

    interface View  {
        fun displayGames(games : List<GamesModel>)
        fun displayPlatforms(games : List<PlatformsModel>)
        fun displayLoading(isFavorite : Boolean)
        fun displayError(message: String?)

    }

    interface Presenter {
        fun attachView(mvpView: View)
        fun detachView()
        fun loadItems(context: Context)
    }
}