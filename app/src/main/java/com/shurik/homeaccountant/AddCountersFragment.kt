package com.shurik.homeaccountant

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.shurik.homeaccountant.database.AppDatabase
import com.shurik.homeaccountant.database.Counts
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AddCountersFragment : Fragment() {

    var mLightNow : Int = 0
    var mWaterNow : Int = 0
    var mGasNow : Int = 0

    var mLight1Tax : Double = 0.0
    var mLight2Tax : Double= 0.0
    var mWaterTax : Double = 0.0
    var mGasTax : Double = 0.0
    var mAptTax : Double = 0.0

    private lateinit var mLightPrev : TextView
    private lateinit var mWaterPrev : TextView
    private lateinit var mGasPrev : TextView
    private var mTotal : Double = 0.0

    companion object {
        fun newInstance(): AddCountersFragment {
            return AddCountersFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val mView =  inflater.inflate(R.layout.fragment_add_counters,container,false)

        AsyncQuery().execute(context)

        mLightPrev = mView.findViewById(R.id.prev_light)
        mWaterPrev = mView.findViewById(R.id.prev_water)
        mGasPrev = mView.findViewById(R.id.prev_gas)

        val mETlight = mView.findViewById<EditText>(R.id.now_light)
        val mETwater = mView.findViewById<EditText>(R.id.now_water)
        val mETgas = mView.findViewById<EditText>(R.id.now_gas)

        val taxes : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        mLight1Tax = taxes.getString(getString(R.string.key_light1),"0").toDouble()
        mLight2Tax = taxes.getString(getString(R.string.key_light2),"0").toDouble()
        mWaterTax = taxes.getString(getString(R.string.key_water),"0").toDouble()
        mGasTax = taxes.getString(getString(R.string.key_gas),"0").toDouble()
        mAptTax = taxes.getString(getString(R.string.key_apt),"0").toDouble()

        val mFAB = mView.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        mFAB.setOnClickListener {
            val countLightPrev : Int = mLightNow
            val mStringLightNow = mETlight.text.toString()

            val countWaterPrev : Int = mWaterNow
            val mStringWaterNow = mETwater.text.toString()

            val countGasPrev : Int = mGasNow
            val mStringGasNow = mETgas.text.toString()

            if (mStringLightNow != "" && mStringWaterNow != "" && mStringGasNow != "") {
                val countLightNext = mStringLightNow.toInt()
                val countWaterNext = mStringWaterNow.toInt()
                val countGasNext = mStringGasNow.toInt()

                val mRow: Counts = onCountAll(countLightPrev, countLightNext, mLight1Tax, mLight2Tax,
                        countWaterPrev, countWaterNext, mWaterTax,
                        countGasPrev, countGasNext, mGasTax, mAptTax)

                AsyncTask.execute {
                    val db = AppDatabase.getInstance(context)
                    db!!.countsDao().addData(mRow)
                }
                Toast.makeText(context,getString(R.string.saved),Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(context,getString(R.string.enter_all_pls),Toast.LENGTH_SHORT).show()
            }
        }

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
                mWaterNow = result.component1().waterNow
                mGasNow = result.component1().gasNow
                mLightPrev.text = mLightNow.toString()
                mWaterPrev.text = mWaterNow.toString()
                mGasPrev.text = mGasNow.toString()
                Log.d("LIGHT",mLightNow.toString())
                Log.d("WATER",mWaterNow.toString())
                Log.d("GAS",mGasNow.toString())
            }
            else {
                mLightPrev.text = mLightNow.toString()
                mWaterPrev.text = mWaterNow.toString()
                mGasPrev.text = mGasNow.toString()
                Toast.makeText(context,getString(R.string.toast_wtf),Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onCountAll (prevL : Int, nextL : Int, tax1L : Double, tax2L: Double,
                            prevW : Int, nextW : Int, taxW: Double,
                            prevG : Int, nextG : Int, taxG : Double, taxA : Double) : Counts {
        val diffL = nextL - prevL
        val priceL = if (diffL > 100) {
            val priceL2 = (diffL - 100) * tax2L
            val priceL1 = tax1L * 100
            priceL1 + priceL2
        }
        else {
            diffL * tax1L
        }

        val diffW = nextW - prevW
        val priceW = diffW * taxW

        val diffG = nextG - prevG
        val priceG = diffG * taxG

        mTotal = priceL + priceW + priceG + taxA

        val current : Date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("MM-yyyy")
        val mDate : String = formatter.format(current)

        return Counts(id,nextL,prevL,diffL,priceL,nextW,prevW,diffW,priceW,
                nextG,prevG,diffG,priceG,taxA, mTotal,mDate)
    }


}
