package io.parrotsoftware.qatest.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.parrotsoftware.qatest.R
import io.parrotsoftware.qatest.databinding.FragmentLoginBinding
import io.parrotsoftware.qatest.utils.observe
import io.parrotsoftware.qatest.utils.toast

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        lifecycle.addObserver(viewModel)
        observe(viewModel.getViewState(), ::onViewState)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initView()
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    private fun onViewState(state: LoginViewState?) {
        when (state) {
            LoginViewState.LoginError -> {
                requireContext().toast(R.string.error_login)
            }
            LoginViewState.LoginSuccess -> {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToListFragment()
                )
                viewModel.navigated()
            }
            else -> {}
        }
    }
}
