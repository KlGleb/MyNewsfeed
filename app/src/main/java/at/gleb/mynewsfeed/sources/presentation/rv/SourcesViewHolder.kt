package at.gleb.mynewsfeed.sources.presentation.rv

import androidx.recyclerview.widget.RecyclerView
import at.gleb.mynewsfeed.databinding.SourceItemBinding
import at.gleb.mynewsfeed.domain.entity.SourceVo

class SourcesViewHolder(private val binding: SourceItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindData(sourceVo: SourceVo, clickListener: (SourceVo) -> Unit) {
        binding.root.setOnClickListener {
            clickListener.invoke(sourceVo)
        }

        binding.titleText.text = sourceVo.title
        binding.descriptionText.text = sourceVo.description
        binding.urlText.text = sourceVo.url
    }
}