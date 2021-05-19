package com.example.fitnessapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R


class HelpFragment : Fragment(R.layout.fragment_help) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_help, container, false)
        val url = "https://www.lifehack.org/articles/technology/20-tips-use-google-search-efficiently.html"
        val webView = rootView.findViewById<WebView>(R.id.webView)

        webView.loadUrl(url)
        webView.webViewClient = MyWebClient()

        return rootView
    }

    private class MyWebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }

}