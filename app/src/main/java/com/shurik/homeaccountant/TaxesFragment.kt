package com.shurik.homeaccountant

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat

class TaxesFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    companion object {
        fun newInstance(): TaxesFragment {
            return TaxesFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_taxes)
        val mSharedPref = preferenceScreen.sharedPreferences
        onStartSetSummary(mSharedPref)

    }

    override fun onCreatePreferences(p0: Bundle?, p1: String?) {

    }

    override fun onSharedPreferenceChanged(pref: SharedPreferences?, key: String?) {

        if (pref != null) {
            when (key) {
                getString(R.string.key_light1) -> {
                    val light1 = findPreference(key)
                    light1.summary = pref.getString(key, "")
                }
                getString(R.string.key_light2) -> {
                    val light2 = findPreference(key)
                    light2.summary = pref.getString(key,"")
                }
                getString(R.string.key_water) -> {
                    val water = findPreference(key)
                    water.summary = pref.getString(key,"")
                }
                getString(R.string.key_gas) -> {
                    val gas = findPreference(key)
                    gas.summary = pref.getString(key,"")
                }
                getString(R.string.key_apt) -> {
                    val apt = findPreference(key)
                    apt.summary = pref.getString(key,"")
                }
            }
        }
    }

    private fun onStartSetSummary (pref :SharedPreferences) {
        val light1 =findPreference(getString(R.string.key_light1))
        light1.summary = pref.getString(getString(R.string.key_light1),"")
        val light2 =findPreference(getString(R.string.key_light2))
        light2.summary = pref.getString(getString(R.string.key_light2),"")
        val water =findPreference(getString(R.string.key_water))
        water.summary = pref.getString(getString(R.string.key_water),"")
        val gas =findPreference(getString(R.string.key_gas))
        gas.summary = pref.getString(getString(R.string.key_gas),"")
        val apt =findPreference(getString(R.string.key_apt))
        apt.summary = pref.getString(getString(R.string.key_apt),"")

    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(this)

    }

    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)

    }
}

