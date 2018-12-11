package com.shurik.homeaccountant

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.shurik.homeaccountant.adapters.CountsAdapter
import com.shurik.homeaccountant.database.AppDatabase
import com.shurik.homeaccountant.database.Counts
import java.lang.Exception

class CountListFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mRecycler : RecyclerView
    private lateinit var mEmptyTextView :TextView

    companion object {
        fun newInstance(): CountListFragment {
            return CountListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
            val mView =  inflater?.inflate(R.layout.fragment_count_list,container,false)
            mRecycler = mView.findViewById(R.id.counts_list)
            mEmptyTextView = mView.findViewById(R.id.empty_view)
            linearLayoutManager = LinearLayoutManager(context)
            mRecycler.layoutManager = linearLayoutManager

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
                mLastCounts  = db.countsDao().getCounts()
            }
            catch (ex: Exception) {
            }
            return mLastCounts
        }

        override fun onPostExecute(result: List<Counts>?) = if (result!!.isNotEmpty()) {
            val adapter = CountsAdapter(result)
            mRecycler.adapter = adapter
        }
        else {
            mRecycler.visibility = View.GONE
            mEmptyTextView.visibility = View.VISIBLE
        }
    }

}


