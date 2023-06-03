package io.parrotsoftware.qatest.presentation.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.parrotsoftware.qatest.domain.models.Product
import io.parrotsoftware.qatest.domain.models.ResponseState
import io.parrotsoftware.qatest.domain.usescases.GetProductsByIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailViewModel @Inject constructor(
    private val getProductsByIdUseCase: GetProductsByIdUseCase
) : ViewModel() {

    private var _product = mutableStateOf<ResponseState>(ResponseState.Loading)
    val product: MutableState<ResponseState> = _product

    fun getProductById(id: String) {
        viewModelScope.launch {
            getProductsByIdUseCase(id)
                .collect {
                    _product.value = ResponseState.Success(it)
                }
        }
    }
}
