package io.parrotsoftware.qatest.data.repositories

import io.parrotsoftware.qa_network.data.NetworkError
import io.parrotsoftware.qa_network.data.NetworkResult
import io.parrotsoftware.qa_network.data.requests.ApiUpdateProductRequest
import io.parrotsoftware.qa_network.data.responses.ApiListResponse
import io.parrotsoftware.qa_network.data.responses.ApiProduct
import io.parrotsoftware.qa_network.data.responses.ApiProductAvailability
import io.parrotsoftware.qa_network.data.responses.ApiSingleResponse
import io.parrotsoftware.qatest.data.datasource.local.LocalDataSource
import io.parrotsoftware.qatest.data.datasource.remote.RemoteDataSource
import io.parrotsoftware.qatest.data.toProduct
import io.parrotsoftware.qatest.data.toProductEntity
import io.parrotsoftware.qatest.domain.repositories.ProductRepository
import io.parrotsoftware.qatest.fake.FakeData.givenAccessToken
import io.parrotsoftware.qatest.fake.FakeData.givenListProductEntity
import io.parrotsoftware.qatest.fake.FakeData.givenStoreId
import io.parrotsoftware.qatest.utils.mock
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class ProductRepositoryTest {

    @Rule @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock private lateinit var remoteDataSource: RemoteDataSource

    @Mock private lateinit var localDataSource: LocalDataSource

    private lateinit var productRepository: ProductRepository

    @Before
    fun setUp() {
        productRepository = ProductRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `Get products flow when invoke get products method`() = runTest {
        val result = mock<ApiListResponse<ApiProduct>>()
        val products = result.results.map { it.toProduct() }.map { it.toProductEntity() }
        val storeId = givenStoreId()
        val accessToken = givenAccessToken()

        given(remoteDataSource.getProducts(accessToken, storeId)).willReturn(NetworkResult(result))
        productRepository.getProducts(accessToken, storeId)
        verify(remoteDataSource).getProducts(accessToken, storeId)
        verify(localDataSource).insertAll(products)
    }

    @Test
    fun `Get products from database when get products service throws an error`() = runTest {
        val error = mock<NetworkError>()
        val products = givenListProductEntity()
        val storeId = givenStoreId()
        val accessToken = givenAccessToken()

        given(remoteDataSource.getProducts(accessToken, storeId)).willReturn(NetworkResult(networkError = error))
        given(localDataSource.getProducts()).willReturn(products)
        productRepository.getProducts(accessToken, storeId)
        verify(remoteDataSource).getProducts(accessToken, storeId)
        verify(localDataSource).getProducts()
    }

    @Test
    fun `Set product state flow when invoke update product method`() = runTest {
        val accessToken = "access_token"
        val productId = "product_id"
        val body = ApiUpdateProductRequest(ApiProductAvailability.AVAILABLE)
        val result = mock<NetworkResult<ApiSingleResponse<ApiProduct>>>()

        given(remoteDataSource.updateProduct(accessToken, productId, body)).willReturn(result)
        productRepository.setProductState(accessToken, productId, true)
        verify(remoteDataSource).updateProduct(accessToken, productId, body)
        verify(localDataSource).updateProduct(1, productId)
    }

    @Test
    fun `Get product by id from database when invoke get product by id`() = runTest {
        val productId = "some_product_id"
        val products = givenListProductEntity().first()

        given(localDataSource.getProductById(productId)).willReturn(products)
        productRepository.getProductById(productId).first()
        verify(localDataSource).getProductById(productId)
    }
}
