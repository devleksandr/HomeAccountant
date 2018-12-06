package com.shurik.homeaccountant


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class AccrualsListFragment : Fragment() {


        companion object {
            fun newInstance(): AccrualsListFragment {
                return AccrualsListFragment()
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_accruals_list,container,false)
    }
}
