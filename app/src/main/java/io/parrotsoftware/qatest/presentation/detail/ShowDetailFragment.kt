package io.parrotsoftware.qatest.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import io.parrotsoftware.qatest.databinding.FragmentShowDetailBinding

@AndroidEntryPoint
class ShowDetailFragment : Fragment() {

    private lateinit var binding: FragmentShowDetailBinding

    private val args : ShowDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowDetailBinding.inflate(inflater)
        binding.composeContainer.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ScreenDetail(productId = args.productId)
            }
        }
        return binding.root
    }
}
