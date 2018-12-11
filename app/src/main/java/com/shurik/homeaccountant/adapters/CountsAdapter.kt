package com.shurik.homeaccountant.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.shurik.homeaccountant.R
import com.shurik.homeaccountant.database.Counts
import com.shurik.homeaccountant.utils.inflate
import kotlinx.android.synthetic.main.recycler_element.view.*

class CountsAdapter(private val counts: List<Counts>) : RecyclerView.Adapter<CountsAdapter.CountsHolder>() {

    class CountsHolder (v : View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view : View = v
        private var count : Counts? =  null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.d("RECYCLER","CLICK")
        }

        val tvCounts = v.tv_element!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountsAdapter.CountsHolder {
        val inflatedView = parent.inflate(R.layout.recycler_element,false)
        return CountsHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: CountsHolder, position: Int) {
        val light = onBuildLight()
        val water = onBuildWater()
        val gas = onBuildGas()
        val apt = onBuildApt()
        val total = onBuildTotal()
        val item = light + water + gas + apt + total
        holder.tvCounts.text = item
    }

    override fun getItemCount(): Int {
        return counts.size
    }

    private fun onBuildLight() : String {
        val lightDiff = counts.component1().lightDiff.toString()
        val lightPrice = counts.component2().lightPrice.toString()
        return "Свет:" + lightDiff + "кВт," + lightPrice + "грн.\n"
    }

    private fun onBuildWater() : String {
        val waterDiff = counts.component1().waterDiff.toString()
        val waterPrice = counts.component2().waterPrice.toString()
        return "Вода:" + waterDiff + "м.куб.," + waterPrice + "грн.\n"
    }

    private fun onBuildGas() : String {
        val gasDiff = counts.component1().gasDiff.toString()
        val gasPrice = counts.component2().gasPrice.toString()
        return "Газ:" + gasDiff + "м.куб.," + gasPrice + "грн.\n"
    }

    private fun onBuildApt() : String {
        val aptPrice = counts.component1().aptPrice.toString()
        return "Квартплата:" + aptPrice + "грн.\n"
    }

    private fun onBuildTotal() : String {
        val totalPrice = counts.component1().total.toString()
        return "Общая сумма:" + totalPrice + "грн.\n"
    }

}