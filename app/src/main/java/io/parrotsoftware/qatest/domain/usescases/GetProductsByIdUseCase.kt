package io.parrotsoftware.qatest.domain.usescases

import io.parrotsoftware.qatest.domain.repositories.ProductRepository
import javax.inject.Inject

class GetProductsByIdUseCase @Inject constructor(private val productRepository: ProductRepository) {

    suspend operator fun invoke(id: String) = productRepository.getProductById(id)
}
