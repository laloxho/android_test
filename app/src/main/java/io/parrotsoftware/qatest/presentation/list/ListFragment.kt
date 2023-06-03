package io.parrotsoftware.qatest.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.parrotsoftware.qatest.R
import io.parrotsoftware.qatest.utils.observe
import io.parrotsoftware.qatest.utils.toast
import io.parrotsoftware.qatest.databinding.FragmentListBinding
import io.parrotsoftware.qatest.utils.menuProvider

@AndroidEntryPoint
class ListFragment :
    Fragment(),
    CategoryListener,
    MenuProvider {

    private val viewModel: ListViewModel by viewModels()
    private lateinit var binding: FragmentListBinding

    private val categoryController by lazy {
        CategoryController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showSupportActionBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        lifecycle.addObserver(viewModel)
        observe(viewModel.getViewState(), ::onViewState)

        binding.recyclerProducts.adapter = categoryController.adapter
        binding.swipeProducts.setOnRefreshListener { viewModel.fetchProducts() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuProvider(this)
        viewModel.initView()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.menu_logout -> {
                viewModel.logout().also {
                    findNavController().navigate(
                        ListFragmentDirections.actionListFragmentToLoginFragment()
                    )
                }
            }
        }
        return false
    }

    private fun showSupportActionBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }

    private fun onViewState(state: ListViewState?) {
        when (state) {
            ListViewState.ErrorLoadingItems -> {
                requireContext().toast("Error al cargar los productos")
            }
            is ListViewState.ItemsLoaded -> {
                categoryController.categories = state.categories
            }
            ListViewState.ErrorUpdatingItem -> {
                requireContext().toast("Error al actualizar el producto")
            }
            else -> {}
        }
    }

    override fun onCategorySelected(category: ExpandableCategory) {
        viewModel.categorySelected(category)
    }

    override fun onProductSelected(product: EnabledProduct) {
        viewModel.productSelected(product)
    }

    override fun onProductSelected(product: String) {
        findNavController().navigate(
            ListFragmentDirections.actionListFragmentToShowDetailFragment(product)
        )
    }
}
