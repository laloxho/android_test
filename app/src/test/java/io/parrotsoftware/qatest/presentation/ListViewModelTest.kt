package io.parrotsoftware.qatest.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.parrotsoftware.qatest.fake.FakeData.givenCredentials
import io.parrotsoftware.qatest.fake.FakeData.givenListProduct
import io.parrotsoftware.qatest.fake.FakeData.givenStore
import io.parrotsoftware.qatest.domain.models.RepositoryResult
import io.parrotsoftware.qatest.domain.usescases.GetCredentialsUseCase
import io.parrotsoftware.qatest.domain.usescases.GetProductsUseCase
import io.parrotsoftware.qatest.domain.usescases.GetStoreUseCase
import io.parrotsoftware.qatest.domain.usescases.LogoutUseCase
import io.parrotsoftware.qatest.domain.usescases.SetProductsStateUseCase
import io.parrotsoftware.qatest.fake.FakeData.givenEnabledProduct
import io.parrotsoftware.qatest.fake.FakeData.givenExpandableCategory
import io.parrotsoftware.qatest.presentation.list.ListViewModel
import io.parrotsoftware.qatest.presentation.list.ListViewState
import io.parrotsoftware.qatest.rules.CoroutineRule
import io.parrotsoftware.qatest.utils.assertIsInstanceOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class ListViewModelTest {

    @Rule @JvmField val mockitoRule: MockitoRule = MockitoJUnit.rule()
    @Rule @JvmField val testRule: TestRule = InstantTaskExecutorRule()
    @ExperimentalCoroutinesApi @get:Rule var coroutineRule = CoroutineRule()
    @Mock private lateinit var getCredentialsUseCase: GetCredentialsUseCase
    @Mock private lateinit var getStoreUseCase: GetStoreUseCase
    @Mock private lateinit var getProductsUseCase: GetProductsUseCase
    @Mock private lateinit var setProductsStateUseCase: SetProductsStateUseCase
    @Mock private lateinit var logoutUseCase: LogoutUseCase
    private lateinit var listViewModel: ListViewModel

    @Before
    fun setUp() {
        listViewModel = ListViewModel(
            getCredentialsUseCase,
            getStoreUseCase,
            getProductsUseCase,
            setProductsStateUseCase,
            logoutUseCase
        )
    }

    @Test
    fun `Call complete flow when invoke init view`() = runTest {
        val credentials = RepositoryResult(givenCredentials())
        val store = RepositoryResult(givenStore())
        val list = RepositoryResult(givenListProduct())
        val accessToken = credentials.requiredResult.access
        val storeId = store.requiredResult.id

        given(getCredentialsUseCase()).willReturn(credentials)
        given(getStoreUseCase()).willReturn(store)
        given(getProductsUseCase(accessToken, storeId)).willReturn(list)
        listViewModel.initView()
        verify(getCredentialsUseCase).invoke()
        verify(getStoreUseCase).invoke()
        verify(getProductsUseCase).invoke(accessToken, storeId)
    }

    @Test
    fun `Get Items Loaded when invoke init view`() = runTest {
        val credentials = RepositoryResult(givenCredentials())
        val store = RepositoryResult(givenStore())
        val list = RepositoryResult(givenListProduct())
        val accessToken = credentials.requiredResult.access
        val storeId = store.requiredResult.id

        given(getCredentialsUseCase()).willReturn(credentials)
        given(getStoreUseCase()).willReturn(store)
        given(getProductsUseCase(accessToken, storeId)).willReturn(list)
        listViewModel.initView()

        val stateDataSuccess = listViewModel.getViewState().value as ListViewState.ItemsLoaded
        assertThat(stateDataSuccess.categories[0].category.id, equalTo("bbc22898-7bd3-4512-8b09-64c4e19d7a9b"))

        assertIsInstanceOf<ListViewState.ItemsLoaded>(stateDataSuccess)
    }

    @Test
    fun `Get Items Loaded when invoke category selected`() = runTest {
        val expandableCategory = givenExpandableCategory()

        listViewModel.products = givenListProduct().toMutableList()
        listViewModel.categorySelected(expandableCategory)

        val stateDataSuccess = listViewModel.getViewState().value as ListViewState.ItemsLoaded
        assertThat(stateDataSuccess.categories[0].category.name, equalTo("Combos Especiales"))
        assertIsInstanceOf<ListViewState.ItemsLoaded>(stateDataSuccess)
    }

    @Test
    fun `Call set products state use case when invoke product selected`() = runTest {
        val credentials = RepositoryResult(givenCredentials())
        val result = RepositoryResult<Nothing>()
        val accessToken = credentials.requiredResult.access
        val enabledProduct = givenEnabledProduct()

        given(getCredentialsUseCase()).willReturn(credentials)
        given(setProductsStateUseCase(accessToken, enabledProduct.product.id, enabledProduct.product.isAvailable.not())).willReturn(result)
        listViewModel.products = givenListProduct().toMutableList()
        listViewModel.productSelected(enabledProduct)
        verify(getCredentialsUseCase).invoke()
        verify(setProductsStateUseCase).invoke(accessToken, enabledProduct.product.id, enabledProduct.product.isAvailable.not())
    }

    @Test
    fun `Get Item Updated when invoke product selected`() = runTest {
        val credentials = RepositoryResult(givenCredentials())
        val result = RepositoryResult<Nothing>()
        val accessToken = credentials.requiredResult.access
        val enabledProduct = givenEnabledProduct()

        given(getCredentialsUseCase()).willReturn(credentials)
        given(setProductsStateUseCase(accessToken, enabledProduct.product.id, enabledProduct.product.isAvailable.not())).willReturn(result)
        listViewModel.products = givenListProduct().toMutableList()
        listViewModel.productSelected(enabledProduct)
        val stateDataSuccess = listViewModel.getViewState().value
        assertIsInstanceOf<ListViewState.ItemUpdated>(stateDataSuccess)
    }

    @Test
    fun `Call logout use case when invoke logout`() = runTest {
        listViewModel.logout()
        verify(logoutUseCase).invoke()
    }
}
