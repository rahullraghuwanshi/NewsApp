package com.intern.assignment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.intern.assignment.R
import com.intern.assignment.data.models.Article
import com.intern.assignment.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {

    lateinit var binding: FragmentWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)

        val article = arguments?.getSerializable("article") as Article

        binding.webView.apply {
            webViewClient = WebViewClient()
            settings.builtInZoomControls = true;
            loadUrl(article.url)
        }
        return binding.root
    }

}