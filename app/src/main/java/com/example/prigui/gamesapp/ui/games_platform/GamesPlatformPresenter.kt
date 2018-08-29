package com.example.prigui.gamesapp.ui.games_platform

import android.content.Context
import com.android.volley.VolleyError
import com.example.prigui.gamesapp.model.PlatformsModel
import com.example.prigui.gamesapp.utils.Constants
import com.example.prigui.gamesapp.wrapper.APIWrapper
import com.igdb.api_android_java.callback.OnSuccessCallback
import com.igdb.api_android_java.wrapper.Parameters
import com.igdb.api_android_java.wrapper.Version
import junit.framework.Assert
import org.json.JSONArray
import org.json.JSONException

/**
 * Created by prigui on 27/08/2018.
 */

class GamesPlatformPresenter : GamesPlatformContract.Presenter{
    private var mView: GamesPlatformContract.View? = null
    private lateinit var mContext : Context
    private lateinit var mWrapper: APIWrapper
    private var mPlatformResult: List<PlatformsModel> = ArrayList()



    override fun attachView(mvpView: GamesPlatformContract.View) {
        mView = mvpView
    }

    override fun detachView() {
        mView = null
    }


    override fun loadItems(context : Context) {
        mContext = context
        mView?.displayLoading(true)

        mWrapper = APIWrapper(context, Constants.API_KEY, Version.STANDARD, true)
        val params = Parameters()
                .addFields(Constants.QUERY_GENERAL_PLARFORMS)
        mWrapper.platforms(params, object : OnSuccessCallback {
            override fun onSuccess(result: JSONArray) {
                mView!!.displayPlatforms(getResultsFromJsonArray(result))
            }
            override fun onError(error: VolleyError) {
                mView?.displayError(error.message)
                mView?.displayLoading(false)
            }
        })

        mView?.displayLoading(false)

    }


    private fun getResultsFromJsonArray(result: JSONArray): List<PlatformsModel> {
        mPlatformResult = ArrayList()
        try {
            for (i in 0..(result.length() - 1)) {
                var item = result.getJSONObject(i)
                //item.getJSONArray("games")

                var gamesObject = item.getJSONArray(Constants.FIELD_GAMES)
                var gamesArrayList : ArrayList<Int> = ArrayList()

                (0..(30)).mapTo(gamesArrayList) { gamesObject.getInt(it) }
                mPlatformResult += PlatformsModel(item.getInt(Constants.FIELD_ID), item.getString(Constants.FIELD_NAME), gamesArrayList)
            }
            mView!!.displayPlatforms(mPlatformResult)

        } catch (e: JSONException) {
            mView?.displayLoading(false)
            e.printStackTrace()
            mView?.displayError(e.message)
        }
        return mPlatformResult
    }

}