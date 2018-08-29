package com.example.prigui.gamesapp.ui.games_detail

import android.content.Context
import com.android.volley.VolleyError
import com.example.prigui.gamesapp.model.GamesModel
import com.example.prigui.gamesapp.utils.Constants
import com.example.prigui.gamesapp.wrapper.APIWrapper
import com.igdb.api_android_java.callback.OnSuccessCallback
import com.igdb.api_android_java.wrapper.Parameters
import com.igdb.api_android_java.wrapper.Version
import junit.framework.Assert
import org.json.JSONArray
import org.json.JSONException

/**
 * Created by prigui on 29/08/2018.
 */

class GamesDetailPresenter : GamesDetailContract.Presenter{
    private var mView: GamesDetailContract.View? = null
    private lateinit var mWrapper: APIWrapper

    override fun attachView(mvpView: GamesDetailContract.View) {
        mView = mvpView
    }

    override fun detachView() {
        mView = null
    }


    override fun loadGenres(context : Context, games : GamesModel) {
        var arrayGenres = games.genres.toString()

        arrayGenres = arrayGenres.replace("[", "")
        arrayGenres = arrayGenres.replace("]", "")

        val params = Parameters()
                .addIds(arrayGenres)
                .addFields(Constants.FIELD_NAME)
        mWrapper = APIWrapper(context, Constants.API_KEY, Version.STANDARD, true)
        mWrapper.genres(params, object : OnSuccessCallback {
            override fun onSuccess(result: JSONArray) {
                try {
                    mView?.showGenresDetail(getResultsFromJsonArray(result))
                } catch (e: JSONException) {
                    e.printStackTrace()
                    mView?.displayError(e.toString())
                }
            }
            override fun onError(error: VolleyError) {
                mView?.displayError(error.toString())
            }
        })

    }

    override fun loadPlatform(context : Context, games : GamesModel) {
        var arrayPlatforms = games.platforms.toString()

        arrayPlatforms = arrayPlatforms.replace("[", "")
        arrayPlatforms = arrayPlatforms.replace("]", "")
        val params = Parameters()
                .addIds(arrayPlatforms)
                .addFields(Constants.FIELD_NAME)
        mWrapper = APIWrapper(context, Constants.API_KEY, Version.STANDARD, true)
        mWrapper.platforms(params, object : OnSuccessCallback {
            override fun onSuccess(result: JSONArray) {
                try {
                    mView?.showPlatformDetail(getResultsFromJsonArray(result))
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Assert.fail("JSONException!!")
                }
            }
            override fun onError(error: VolleyError) {
                Assert.fail("Volley Error!!")
            }
        })

    }

    private fun getResultsFromJsonArray(result: JSONArray): String? {
        var nameArrayList : ArrayList<String> = ArrayList()
        for (i in 0..(result.length() - 1)) {
            var item = result.getJSONObject(i)

            var names: String? = null
            if (item.has(Constants.FIELD_NAME)) {
                names = item.getString(Constants.FIELD_NAME)
            }
            nameArrayList.add(names!!)
        }
        var namesString = nameArrayList.toString()
        namesString = namesString.replace("[", "")
        namesString = namesString.replace("]", "")
        return namesString
    }
}
