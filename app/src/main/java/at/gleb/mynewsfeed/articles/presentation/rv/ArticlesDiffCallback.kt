package at.gleb.mynewsfeed.articles.presentation.rv

import androidx.recyclerview.widget.DiffUtil
import at.gleb.mynewsfeed.domain.entity.ArticleVo

class ArticlesDiffCallback : DiffUtil.ItemCallback<ArticleVo>() {
    override fun areItemsTheSame(oldItem: ArticleVo, newItem: ArticleVo): Boolean {
        return oldItem.publishedDate == newItem.publishedDate
                && oldItem.title == newItem.title
                && oldItem.author == newItem.author
    }

    override fun areContentsTheSame(oldItem: ArticleVo, newItem: ArticleVo): Boolean {
        return oldItem == newItem
    }
}