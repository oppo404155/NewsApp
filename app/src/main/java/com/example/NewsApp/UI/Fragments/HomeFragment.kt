package com.example.NewsApp.UI.Fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.NewsApp.Data.Article
import com.example.NewsApp.Data.Source
import com.example.NewsApp.R
import com.example.NewsApp.UI.Adaptres.NewsAdapter
import com.example.NewsApp.UI.News_Viewmodel
import com.example.NewsApp.databinding.FragmentHomeBinding
import com.example.NewsApp.utiles.Networkconnectivity.Companion.isOnline
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.newSingleThreadContext

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewmodel: News_Viewmodel by viewModels()
    private var _homefragmentbinding: FragmentHomeBinding? = null
    private val homefragmentbinding get() = _homefragmentbinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _homefragmentbinding = FragmentHomeBinding.bind(view)

        val newsAdapter = NewsAdapter()
        homefragmentbinding.apply {
            newsRecycler.apply {

                layoutManager = LinearLayoutManager(context)
                adapter = newsAdapter

            }

            searImage.apply {
                clearFocus()
                setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                    androidx.appcompat.widget.SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {

                        newsAdapter.filter.filter(newText)
                        return false
                    }

                })
            }
            newsAdapter.onItemClick = {
                val article = bundleOf("article" to it)
                findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, article)

            }


            if (isOnline(requireContext().applicationContext)) {
                viewmodel.getallnews().observe(viewLifecycleOwner) {
                    Log.d("testAPI", it.articles[1].title)
                    newsAdapter.setData(it.articles as MutableList<Article>)

                }


            } else {
                searImage.isVisible = false
                progressBar.isVisible = true
                newsRecycler.isVisible = false
                Handler().postDelayed({
                    progressBar.isVisible = false
                    txvErrorMessage.isVisible = true
                }, 3000)
            }


        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _homefragmentbinding = null
    }


}