package at.gleb.mynewsfeed.articles.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import at.gleb.mynewsfeed.databinding.FragmentArticlesBinding

class ArticlesFragment : Fragment() {
    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }
}