package com.example.NewsApp.UI.Adaptres

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.NewsApp.Data.Article
import com.example.NewsApp.R
import com.example.NewsApp.databinding.NewscardItemBinding
import java.util.*

class NewsAdapter :
    androidx.recyclerview.widget.
    ListAdapter<Article, NewsAdapter.viewholder>(itemCallback),Filterable {

    var onItemClick: ((Article) -> Unit)? = null
    private var list = mutableListOf<Article>()





    inner class viewholder(private val binding: NewscardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }

        fun bind(article: Article) {
            binding.apply {
                Glide.with(itemView).load(article.urlToImage).centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.erorr_24)
                    .into(newsImage)
                sourcenameTxv.text = article.source.name
                puplishDateTxv.text = article.publishedAt
                TitleTxv.text = article.title

            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view = NewscardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewholder(view)

    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }


    }

    companion object {
        private val itemCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }

        }
    }

    override fun getFilter(): Filter {
        return object :Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<Article>()
                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(list)
                } else {
                    for (item in list) {
                        if (item.title.toLowerCase(Locale.ROOT).contains(constraint.toString().toLowerCase(
                                Locale.ROOT
                            )
                            ) ||
                            item.title.toLowerCase(Locale.ROOT).contains(constraint.toString().toLowerCase(
                                        Locale.ROOT
                                    )
                                    )
                        ) {
                            filteredList.add(item)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                list.clear()
                list.addAll(results?.values as MutableList<Article>)
                submitList(list)

            }

        }
    }
    fun setData(list: MutableList<Article>?){
        this.list = list!!
        submitList(list)
    }

}






