package com.shurik.homeaccountant

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.shurik.homeaccountant.database.AppDatabase
import com.shurik.homeaccountant.database.Counts
import java.lang.Exception

class AddCountersFragment : Fragment() {

    var mLightNow : Int = 0
    var mWaterNow : Int = 0
    var mGasNow : Int = 0
    var mLight1Tax : Float = 0.0f
    var mLight2Tax : Float= 0.0f
    var mWaterTax : Float = 0.0f
    var mGasTax : Float = 0.0f
    var mAptTax : Float = 0.0f
    private var bundle = Bundle()

    companion object {
        fun newInstance(): AddCountersFragment {
            return AddCountersFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val mView =  inflater.inflate(R.layout.fragment_add_counters,container,false)

        AsyncQuery().execute(context)

        return mView
    }

    @SuppressLint("StaticFieldLeak")
    inner class AsyncQuery : AsyncTask<Context, Void, List<Counts>>() {

        private lateinit var db: AppDatabase
        @SuppressLint("StaticFieldLeak")

        override fun onPreExecute() {
            super.onPreExecute()
            db = AppDatabase.getInstance(context)!!
        }

        override fun doInBackground(vararg context: Context?): List<Counts> {

            var mLastCounts : List<Counts> = arrayListOf()

            try {
                mLastCounts  = db.countsDao().getLastData()
            }
            catch (ex: Exception) {
            }
            return mLastCounts
        }

        override fun onPostExecute(result: List<Counts>?) {
            if (result!!.isNotEmpty()) {
                    mLightNow = result.component1().lightNow
                    mWaterNow = result.component2().waterNow
                    mGasNow = result.component3().gasNow
            }
            else {
                Toast.makeText(context,getString(R.string.toast_wtf),Toast.LENGTH_LONG).show()
            }
        }
    }
}
