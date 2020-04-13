package at.gleb.mynewsfeed.sources.presentation.rv

import androidx.recyclerview.widget.DiffUtil
import at.gleb.mynewsfeed.domain.entity.SourceVo

class SourcesDiffCallback : DiffUtil.ItemCallback<SourceVo>() {
    override fun areItemsTheSame(oldItem: SourceVo, newItem: SourceVo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SourceVo, newItem: SourceVo): Boolean {
        return oldItem == newItem
    }
}