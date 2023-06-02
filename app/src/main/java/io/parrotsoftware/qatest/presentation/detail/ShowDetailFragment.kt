package io.parrotsoftware.qatest.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import io.parrotsoftware.qatest.databinding.FragmentShowDetailBinding
import io.parrotsoftware.qatest.presentation.ScreenDetail

class ShowDetailFragment : Fragment() {

    private lateinit var binding: FragmentShowDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowDetailBinding.inflate(inflater)
        binding.composeContainer.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ScreenDetail()
            }
        }
        return binding.root
    }
}
