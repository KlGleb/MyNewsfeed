package at.gleb.mynewsfeed.sources.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import at.gleb.mynewsfeed.App
import at.gleb.mynewsfeed.R
import at.gleb.mynewsfeed.databinding.FragmentSourcesBinding
import at.gleb.mynewsfeed.sources.presentation.rv.SourcesRecyclerViewAdapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class SourcesFragment : Fragment() {
    private var _binding: FragmentSourcesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: SourcesViewModel

    private val adapter by lazy {
        SourcesRecyclerViewAdapter {
            viewModel.onItemClick(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSourcesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        App.dagger.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[SourcesViewModel::class.java]

        viewModel.sources.observe(viewLifecycleOwner, Observer {
            when (it) {
                is SourcesState.ShowSources -> showSources(it)
                SourcesState.Loading -> toggleProgress(true)
                SourcesState.Error -> showError()
            }
        })

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@SourcesFragment.adapter
            addItemDecoration(DividerItemDecoration(context, VERTICAL))
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }

        binding.refreshButton.setOnClickListener {
            viewModel.onRefresh()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun showSources(state: SourcesState.ShowSources) {
        toggleProgress(false)
        toggleErrorLayout(false)
        adapter.submitList(state.states)
    }

    private fun showError() {
        toggleProgress(false)
        if (adapter.itemCount == 0) {
            toggleErrorLayout(true)
        } else {
            view?.let {
                Snackbar.make(it, R.string.refresh_label, Snackbar.LENGTH_LONG)
                    .setAction(R.string.refresh_button) {
                        viewModel.onRefresh()
                    }
                    .run {
                        show()
                    }
            }

        }
    }

    private fun toggleProgress(isProgress: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = isProgress
        toggleErrorLayout(false)
    }

    private fun toggleErrorLayout(show: Boolean) {
        if (!show) {
            binding.errorLayout.visibility = View.GONE
            binding.swipeRefreshLayout.visibility = View.VISIBLE
        } else {
            binding.errorLayout.visibility = View.VISIBLE
            binding.swipeRefreshLayout.visibility = View.GONE
        }
    }

}