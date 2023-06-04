package io.parrotsoftware.qatest.domain.usescases

import io.parrotsoftware.qatest.domain.repositories.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val productRepository: ProductRepository) {

    suspend operator fun invoke(accessToken: String, storeId: String) = productRepository.getProducts(accessToken, storeId)
}
