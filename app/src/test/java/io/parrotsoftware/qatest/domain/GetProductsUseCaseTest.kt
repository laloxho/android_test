package io.parrotsoftware.qatest.domain

import io.parrotsoftware.qatest.domain.models.Product
import io.parrotsoftware.qatest.domain.usescases.GetProductsUseCase
import io.parrotsoftware.qatest.fake.FakeProductRepository
import io.parrotsoftware.qatest.utils.assertIsInstanceOf
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class GetProductsUseCaseTest {

    @Rule @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()
    private lateinit var repository: FakeProductRepository
    private lateinit var getProductsUseCase: GetProductsUseCase

    @Before
    fun setup() {
        repository = FakeProductRepository()
        getProductsUseCase = GetProductsUseCase(repository)
    }

    @Test
    fun `Get Product List instance when invoke use case`() = runTest {
        val accessToken = "some_access_token"
        val storeId = "some_store_id"
        val products = getProductsUseCase.invoke(accessToken, storeId)
        assertIsInstanceOf<List<Product>>(products.result)
    }

    @Test
    fun `Get Product List when invoke use case`() = runTest {
        val accessToken = "some_access_token"
        val storeId = "some_store_id"
        val products = getProductsUseCase.invoke(accessToken, storeId)
        assertThat(products.result?.size, equalTo(2))
        assertThat(products.result?.get(0)?.id, equalTo("2618ec65-f996-4b12-898b-b6cf1cc32384"))
        assertThat(products.result?.get(0)?.name, equalTo("Combo Amigos"))
        assertThat(products.result?.get(1)?.id, equalTo("9d1e3446-f536-4842-8adf-8a06e96ab0a9"))
        assertThat(products.result?.get(1)?.name, equalTo("Combo Amigos - COPIA"))
    }
}
