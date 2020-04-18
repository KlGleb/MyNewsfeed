package at.gleb.mynewsfeed.articles.presentation.rv

import androidx.recyclerview.widget.RecyclerView
import at.gleb.mynewsfeed.databinding.ArticleItemBinding
import at.gleb.mynewsfeed.domain.entity.ArticleVo

class ArticlesViewHolder(private val binding: ArticleItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindData(articleVo: ArticleVo, clickListener: (ArticleVo) -> Unit) {
        binding.root.setOnClickListener {
            clickListener.invoke(articleVo)
        }

        binding.titleText.text = articleVo.title
        binding.descriptionText.text = articleVo.description
        binding.authorText.text = articleVo.author
    }
}