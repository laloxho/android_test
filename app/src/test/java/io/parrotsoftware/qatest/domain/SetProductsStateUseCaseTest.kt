package io.parrotsoftware.qatest.domain

import io.parrotsoftware.qatest.domain.models.RepositoryResult
import io.parrotsoftware.qatest.domain.repositories.ProductRepository
import io.parrotsoftware.qatest.domain.usescases.SetProductsStateUseCase
import io.parrotsoftware.qatest.utils.assertIsInstanceOf
import io.parrotsoftware.qatest.utils.mock
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class SetProductsStateUseCaseTest {

    @Rule @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock private lateinit var repository: ProductRepository
    private lateinit var setProductsStateUseCase: SetProductsStateUseCase

    @Before
    fun setup() {
        setProductsStateUseCase = SetProductsStateUseCase(repository)
    }

    @Test
    fun `Call set product state method when invoke use case`() = runTest {
        val result = mock<RepositoryResult<Nothing>>()
        val accessToken = "access_token"
        val productId = "product_id"

        given(repository.setProductState(accessToken, productId, true)).willReturn(result)
        setProductsStateUseCase.invoke(accessToken, productId, true)
        verify(repository).setProductState(accessToken, productId, true)
    }

    @Test
    fun `Receive Repository Result when invoke use case`() = runTest {
        val result = mock<RepositoryResult<Nothing>>()
        val accessToken = "access_token"
        val productId = "product_id"

        given(repository.setProductState(accessToken, productId, true)).willReturn(result)
        val response = setProductsStateUseCase.invoke(accessToken, productId, true)
        assertThat(result, equalTo(response))
        assertIsInstanceOf<RepositoryResult<Nothing>>(response)
    }
}
