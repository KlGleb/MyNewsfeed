package at.gleb.mynewsfeed.articles.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.gleb.mynewsfeed.App
import at.gleb.mynewsfeed.articles.presentation.rv.ArticlesRecyclerViewAdapter
import at.gleb.mynewsfeed.databinding.FragmentArticlesBinding
import javax.inject.Inject

class ArticlesFragment : Fragment() {
    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: ArticlesViewModel

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
        App.dagger.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[ArticlesViewModel::class.java]

        viewModel.articles.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ArticlesState.ShowArticles -> showSources(it)
                ArticlesState.Loading -> toggleProgress(true)
                ArticlesState.Error -> showError()
            }
        })

        viewModel.onGetSourceId(args.sourceId!!)//todo: default source id

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ArticlesFragment.adapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }
/*
        binding.refreshButton.setOnClickListener {
            viewModel.onRefresh()
        }*/

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
}