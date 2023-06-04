package io.parrotsoftware.qatest.domain.usescases

import io.parrotsoftware.qatest.domain.repositories.ProductRepository
import javax.inject.Inject

class SetProductsStateUseCase @Inject constructor(private val productRepository: ProductRepository) {

    suspend operator fun invoke(
        accessToken: String,
        productId: String,
        isAvailable: Boolean,
    ) = productRepository.setProductState(accessToken, productId, isAvailable)
}
