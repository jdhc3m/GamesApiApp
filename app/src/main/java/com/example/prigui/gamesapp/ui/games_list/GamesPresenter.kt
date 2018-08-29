package com.example.prigui.gamesapp.ui.games_list

import android.content.Context
import com.android.volley.VolleyError
import com.example.prigui.gamesapp.model.GamesModel
import com.example.prigui.gamesapp.model.PlatformsModel
import com.example.prigui.gamesapp.utils.Constants
import com.example.prigui.gamesapp.wrapper.APIWrapper
import com.igdb.api_android_java.callback.OnSuccessCallback
import com.igdb.api_android_java.wrapper.Parameters
import com.igdb.api_android_java.wrapper.Version
import junit.framework.Assert
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by prigui on 28/08/2018.
 */

class GamesPresenter : GamesContract.Presenter{
    private var mView: GamesContract.View? = null
    private lateinit var mContext : Context
    private lateinit var mWrapper: APIWrapper
    private var mGamesResult: List<GamesModel> = ArrayList()



    override fun attachView(mvpView: GamesContract.View) {
        mView = mvpView
    }

    override fun detachView() {
        mView = null
    }


    override fun loadGames(context : Context, gamesIds : List<Int>) {
        mView?.displayLoading(true)
        mGamesResult = ArrayList()
        var arrayIds = gamesIds.toString().toString()
        arrayIds = arrayIds.replace("[", "")
        arrayIds = arrayIds.replace("]", "")
        val params = Parameters()
                .addIds(arrayIds)
                .addLimit(Constants.QUERY_LIMMIT_30)
                .addFields(Constants.QUERY_GAMES)
        mWrapper = APIWrapper(context, Constants.API_KEY, Version.STANDARD, true)
        mWrapper.games(params, object : OnSuccessCallback {
            override fun onSuccess(result: JSONArray) {
                try {
                    mView?.displayLoading(false)
                    mView!!.displayGames(getResultsFromJsonArray(result))
                } catch (e: JSONException) {
                    mView?.displayLoading(false)
                    mView?.displayError(e.message)
                }
            }
            override fun onError(error: VolleyError) {
                mView?.displayLoading(false)
                mView?.displayError(error.toString())
            }
        })

    }

    private fun getResultsFromJsonArray(result: JSONArray): List<GamesModel> {
        mGamesResult = ArrayList()
        for (i in 0..(result.length() - 1)) {
            var item = result.getJSONObject(i)


            //Get Genres from Json

            var genresObject: JSONArray?
            var genresArrayList: ArrayList<Int> = ArrayList()

            if (item.has(Constants.FIELD_GENRES)) {
                genresObject = item.getJSONArray(Constants.FIELD_GENRES)
                (0..(genresObject!!.length() - 1)).mapTo(genresArrayList) { genresObject!!.getInt(it) }
            }

            // get Platforms from Json
            var platformsObject: JSONArray?
            var platformsArrayList: ArrayList<Int> = ArrayList()

            if (item.has(Constants.FIELD_PLATFORM)) {
                platformsObject = item.getJSONArray(Constants.FIELD_PLATFORM)

                (0..(platformsObject.length() - 1)).mapTo(platformsArrayList) { platformsObject!!.getInt(it) }
            }

            // Get Name Image
            var screenshotsObject : JSONObject?
            var urlImageName : String? = null

            if (item.has(Constants.FIELD_COVER)) {
                screenshotsObject = item.getJSONObject(Constants.FIELD_COVER)
                urlImageName = screenshotsObject.getString(Constants.FIELD_CLOUDINARY_ID)
            }

            var summary : String? = null
            if (item.has(Constants.FIELD_SUMMARY)) {
                summary = item.getString(Constants.FIELD_SUMMARY)
            }

            var name : String? = null
            if (item.has(Constants.FIELD_NAME)) {
                name = item.getString(Constants.FIELD_NAME)
            }


            mGamesResult += GamesModel(Constants.URL_IMAGE + urlImageName + Constants.FIELD_IMAGE_EXTENTION,
                    name,
                    genresArrayList,
                    platformsArrayList,
                    summary)
        }
        return mGamesResult
    }

}
