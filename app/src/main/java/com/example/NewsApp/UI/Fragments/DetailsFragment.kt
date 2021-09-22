package com.example.NewsApp.UI.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.NewsApp.Data.Article
import com.example.NewsApp.R
import com.example.NewsApp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    private var _detailsfragmentbinding: FragmentDetailsBinding? = null
    private val detailsfragmentbinding get() = _detailsfragmentbinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _detailsfragmentbinding = FragmentDetailsBinding.bind(view)
        val article = arguments?.get("article") as Article
        detailsfragmentbinding.apply {
            Glide.with(this@DetailsFragment).load(article.urlToImage).centerCrop()
                .error(R.drawable.erorr_24)
                .transition(DrawableTransitionOptions.withCrossFade()).into(detailsImg)


            detailsTitleTxv.apply {
                text = article.title
                val uri = Uri.parse(article.url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                paint.isUnderlineText = true
                setOnClickListener {
                    context.startActivity(intent)
                }

            }
            detailsDescriptionTxv.text = article.description

            backBtn.setOnClickListener {
                findNavController().navigate(R.id.action_detailsFragment_to_homeFragment)
            }

            detailsSourceTxv.text=article.source.name



        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _detailsfragmentbinding = null
    }

}