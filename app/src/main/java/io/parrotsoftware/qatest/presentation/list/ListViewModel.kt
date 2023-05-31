package io.parrotsoftware.qatest.presentation.list

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.parrotsoftware.qatest.domain.models.Product
import io.parrotsoftware.qatest.domain.usescases.GetCredentialsUseCase
import io.parrotsoftware.qatest.domain.usescases.GetProductsUseCase
import io.parrotsoftware.qatest.domain.usescases.GetStoreUseCase
import io.parrotsoftware.qatest.domain.usescases.LogoutUseCase
import io.parrotsoftware.qatest.domain.usescases.SetProductsStateUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getCredentialsUseCase: GetCredentialsUseCase,
    private val getStoreUseCase: GetStoreUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val setProductsStateUseCase: SetProductsStateUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel(), LifecycleObserver {

    private val _viewState = MutableLiveData<ListViewState>()
    fun getViewState() = _viewState

    val isLoading: LiveData<Boolean> = _viewState.map {
        it is ListViewState.Loading
    }

    private var products = mutableListOf<Product>()
    private val categoriesExpanded = mutableMapOf<String, Boolean>()

    fun initView() {
        fetchProducts()
    }

    fun fetchProducts() {
        _viewState.value = ListViewState.Loading

        viewModelScope.launch {
            val credentials = getCredentialsUseCase()
            val store = getStoreUseCase()

            if (credentials.isError || store.isError) {
                _viewState.value = ListViewState.ErrorLoadingItems
                return@launch
            }

            val response = getProductsUseCase.invoke(
                credentials.requiredResult.access,
                store.requiredResult.id
            )

            if (response.isError) {
                _viewState.value = ListViewState.ErrorLoadingItems
                return@launch
            }

            products = response.requiredResult.toMutableList()
            val expandedCategories = createCategoriesList()
            _viewState.value = ListViewState.ItemsLoaded(expandedCategories)
        }
    }

    private fun updateProduct(productId: String, isAvailable: Boolean) {
        viewModelScope.launch {
            val credentials = getCredentialsUseCase()

            if (credentials.isError) {
                _viewState.value = ListViewState.ErrorUpdatingItem
                return@launch
            }

            val response = setProductsStateUseCase.invoke(
                credentials.requiredResult.access,
                productId,
                isAvailable
            )

            if (response.isError) {
                _viewState.value = ListViewState.ErrorUpdatingItem
                return@launch
            }

            _viewState.value = ListViewState.ItemUpdated
        }
    }

    fun categorySelected(category: ExpandableCategory) {
        val currentState = categoriesExpanded[category.category.id] ?: false
        categoriesExpanded[category.category.id] = !currentState
        val expandedCategories = createCategoriesList()
        _viewState.value = ListViewState.ItemsLoaded(expandedCategories)
    }

    fun productSelected(product: EnabledProduct) {
        val nextState = product.enabled.not()
        val index = products.indexOfFirst { it.id == product.product.id }
        products[index] = product.product.copy(isAvailable = nextState)
        val expandedCategories = createCategoriesList()
        _viewState.value = ListViewState.ItemsLoaded(expandedCategories)
        updateProduct(product.product.id, nextState)
    }

    private fun createCategoriesList(): List<ExpandableCategory> {
        // Get Categories from products
        val categories = products
            .map { it.category }
            .distinctBy { it.id }
            .sortedBy { it.position }
        val groupedProducts = products.groupBy { it.category.id }

        return categories.map { category ->
            val productGroup = groupedProducts[category.id]?.map { product ->
                EnabledProduct(product, product.isAvailable)
            } ?: emptyList()

            ExpandableCategory(
                category,
                categoriesExpanded[category.id] ?: false,
                productGroup
            )
        }
    }

    fun logout() {
        logoutUseCase()
    }
}