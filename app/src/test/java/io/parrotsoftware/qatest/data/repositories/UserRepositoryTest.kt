package io.parrotsoftware.qatest.data.repositories

import io.parrotsoftware.qanetwork.data.NetworkResult
import io.parrotsoftware.qanetwork.data.responses.ApiSingleResponse
import io.parrotsoftware.qatest.data.datasource.local.LocalDataSource
import io.parrotsoftware.qatest.data.datasource.remote.RemoteDataSource
import io.parrotsoftware.qatest.domain.repositories.UserRepository
import io.parrotsoftware.qatest.fake.FakeData.givenApiCredentials
import io.parrotsoftware.qatest.fake.FakeData.givenApiStore
import io.parrotsoftware.qatest.fake.FakeData.givenApiUserWithStores
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

class UserRepositoryTest {

    @Rule @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock private lateinit var remoteDataSource: RemoteDataSource

    @Mock private lateinit var localDataSource: LocalDataSource

    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        userRepository = UserRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `Get Auth flow when invoke login`() = runTest {
        val result = givenApiCredentials()
        val getMeResult = givenApiUserWithStores()
        val email = "android-challenge@parrotsoftware.io"
        val password = "8mngDhoPcB3ckV7X"

        given(remoteDataSource.auth(email, password)).willReturn(NetworkResult(result))
        given(remoteDataSource.getMe(result.accessToken)).willReturn(NetworkResult(ApiSingleResponse("ok", getMeResult)))
        userRepository.login(email, password)
        verify(remoteDataSource).auth(email, password)
        verify(remoteDataSource).getMe(result.accessToken)
    }

    @Test
    fun `Get user exists flow when invoke user exists method`() = runTest {
        given(localDataSource.isAuth()).willReturn(true)
        userRepository.userExists()
        verify(localDataSource).isAuth()
    }

    @Test
    fun `Validate if user exists when invoke user exists method`() = runTest {
        given(localDataSource.isAuth()).willReturn(true)
        val isUserExists = userRepository.userExists().result
        assertThat(isUserExists, equalTo(true))
    }

    @Test
    fun `Get Credentials flow when invoke get credentials method`() = runTest {
        given(localDataSource.getAccess()).willReturn("some_access")
        given(localDataSource.getRefresh()).willReturn("some_refresh")
        userRepository.getCredentials()
        verify(localDataSource).getAccess()
        verify(localDataSource).getRefresh()
    }

    @Test
    fun `Get Api Credentials when invoke get credentials method`() = runTest {
        val credential = givenApiCredentials()
        given(localDataSource.getAccess()).willReturn(credential.accessToken)
        given(localDataSource.getRefresh()).willReturn(credential.refreshToken)

        val credentials = userRepository.getCredentials()

        assertThat(credentials.result?.access, equalTo("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6..."))
        assertThat(credentials.result?.refresh, equalTo("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjo..."))
    }

    @Test
    fun `Get Store flow when invoke get store method`() = runTest {
        given(localDataSource.getStoreUuid()).willReturn("some_uuid")
        given(localDataSource.getStoreName()).willReturn("some_name")
        userRepository.getStore()
        verify(localDataSource).getStoreUuid()
        verify(localDataSource).getStoreName()
    }

    @Test
    fun `Get Api Store when invoke get store method`() = runTest {
        val apiStore = givenApiStore()
        given(localDataSource.getStoreUuid()).willReturn(apiStore.uuid)
        given(localDataSource.getStoreName()).willReturn(apiStore.name)

        val store = userRepository.getStore()

        assertThat(store.result?.id, equalTo("e7f46731-1654-4ba3-be83-408ac5255838"))
        assertThat(store.result?.name, equalTo("Store Android Challenge"))
    }

    @Test
    fun `Get logout flow when invoke logout method`() = runTest {
        userRepository.logout()
        verify(localDataSource).clearData()
    }
}
