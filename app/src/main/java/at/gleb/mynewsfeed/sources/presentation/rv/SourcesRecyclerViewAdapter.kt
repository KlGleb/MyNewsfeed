package at.gleb.mynewsfeed.sources.presentation.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import at.gleb.mynewsfeed.databinding.SourceItemBinding
import at.gleb.mynewsfeed.domain.entity.SourceVo

class SourcesRecyclerViewAdapter(private val callback: (SourceVo) -> Unit) :
    ListAdapter<SourceVo, SourcesViewHolder>(SourcesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SourceItemBinding.inflate(inflater)
        return SourcesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SourcesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item, callback)
    }

}