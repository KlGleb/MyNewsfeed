package at.gleb.mynewsfeed.articles.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.gleb.mynewsfeed.articles.presentation.rv.ArticlesRecyclerViewAdapter
import at.gleb.mynewsfeed.databinding.FragmentArticlesBinding
import kotlinx.android.synthetic.main.fragment_sources.*
import org.koin.androidx.viewmodel.ext.android.viewModel

const val SCROLL_POSITION = "SCROLL_POSITION"

class ArticlesFragment : Fragment() {
    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!
    private val articlesViewModel: ArticlesViewModel by viewModel()

    private val args: ArticlesFragmentArgs by navArgs()

    private val adapter by lazy {
        ArticlesRecyclerViewAdapter {
            /*  findNavController().navigate(
                  SourcesFragmentDirections.actionSourcesFragmentToArticlesFragment(it.id)
              )*/
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        articlesViewModel.onGetSourceId(args.sourceId!!)//todo: default source id

        articlesViewModel.articles.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            if (swipeRefreshLayout.isRefreshing) {
                swipeRefreshLayout.isRefreshing = false
            }
        })

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ArticlesFragment.adapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            articlesViewModel.onRefresh()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun showError() {

    }

    private fun toggleProgress(visible: Boolean) {

    }

    private fun showSources(state: ArticlesState.ShowArticles) {
        toggleProgress(false)
//        toggleErrorLayout(false)
        adapter.submitList(state.list)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(
            SCROLL_POSITION,
            (binding.recyclerView.layoutManager as LinearLayoutManager?)?.findFirstVisibleItemPosition()
                ?: 0
        )
        super.onSaveInstanceState(outState)
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        savedInstanceState?.getInt(SCROLL_POSITION)?.let {
            binding.recyclerView.post { binding.recyclerView.scrollToPosition(it) }
        }
        super.onViewStateRestored(savedInstanceState)
    }
}