package io.parrotsoftware.qatest.domain

import io.parrotsoftware.qatest.domain.models.Product
import io.parrotsoftware.qatest.domain.usescases.GetProductsByIdUseCase
import io.parrotsoftware.qatest.fake.FakeProductRepository
import io.parrotsoftware.qatest.utils.assertIsInstanceOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class GetProductsByIdUseCaseTest {

    @Rule @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()
    private lateinit var repository: FakeProductRepository
    private lateinit var getProductsByIdUseCase: GetProductsByIdUseCase

    @Before
    fun setup() {
        repository = FakeProductRepository()
        getProductsByIdUseCase = GetProductsByIdUseCase(repository)
    }

    @Test
    fun `Get Product instance when invoke use case`() = runTest {
        val productId = "some_product_id"
        val products = getProductsByIdUseCase.invoke(productId).first()
        assertIsInstanceOf<Product>(products)
    }

    @Test
    fun `Get Product when invoke use case`() = runTest {
        val productId = "some_product_id"
        val products = getProductsByIdUseCase.invoke(productId).first()

        assertThat(products.id, equalTo("2618ec65-f996-4b12-898b-b6cf1cc32384"))
        assertThat(products.name, equalTo("Combo Amigos"))
        assertThat(products.price, equalTo(189.00f))
        assertThat(products.isAvailable, equalTo(false))
    }
}
