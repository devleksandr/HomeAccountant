package com.shurik.homeaccountant


import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

class StartFragment : Fragment() {

    companion object {
        fun newInstance(): StartFragment {
            return StartFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val myView = inflater?.inflate(R.layout.fragment_start,container,false)
        val webView = myView.findViewById<WebView>(R.id.web_view)
        if (isNetworkConnected()) {
            webView!!.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    view?.loadUrl(url)
                    return true
                }
            }
            webView!!.loadUrl(getString(R.string.url_taxes))
        }
        else  Toast.makeText(
                context, // Parent view
                getString(R.string.inet_check), // Message to show
                Toast.LENGTH_LONG // How long to display the message.
        )::show
        return myView
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager  =
                context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}



