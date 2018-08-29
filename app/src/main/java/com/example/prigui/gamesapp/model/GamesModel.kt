package com.example.prigui.gamesapp.model

import java.io.Serializable

/**
 * Created by prigui on 27/08/2018.
 */

data class GamesModel(
        var image: String?,
        var name :String?,
        var genres : List<Int>?,
        var platforms : List<Int>?,
        var summary : String?
) : Serializable