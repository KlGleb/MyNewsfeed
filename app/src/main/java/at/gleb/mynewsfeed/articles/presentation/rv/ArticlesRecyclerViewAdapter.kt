package at.gleb.mynewsfeed.articles.presentation.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import at.gleb.mynewsfeed.databinding.ArticleItemBinding
import at.gleb.mynewsfeed.domain.entity.ArticleVo

class ArticlesRecyclerViewAdapter(private val callback: (ArticleVo) -> Unit) :
    PagedListAdapter<ArticleVo, ArticlesViewHolder>(ArticlesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ArticleItemBinding.inflate(inflater)
        return ArticlesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item, callback)
    }

}