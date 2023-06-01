package io.parrotsoftware.qatest.domain

import io.parrotsoftware.qatest.fake.FakeUserRepository
import io.parrotsoftware.qatest.domain.usescases.GetStoreUseCase
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class GetStoreUseCaseTest {

    @Rule @JvmField val mockitoRule: MockitoRule = MockitoJUnit.rule()
    private lateinit var repository: FakeUserRepository
    private lateinit var getStoreUseCase: GetStoreUseCase

    @Before
    fun setup() {
        repository = FakeUserRepository()
        getStoreUseCase = GetStoreUseCase(repository)
    }

    @Test
    fun `Get Store, correct store return`() = runTest {
        val store = getStoreUseCase.invoke()
        assertThat(store.result?.id, equalTo("e7f46731-1654-4ba3-be83-408ac5255838"))
        assertThat(store.result?.name, equalTo("Store Android Challenge"))
    }

    @Test
    fun `Get Store, incorrect store return`() = runTest {
        val store = getStoreUseCase.invoke()
        assertThat(store.result?.id, not("e7f46731-1654-4ba3-be83-408ac525444"))
        assertThat(store.result?.name, not("Store Android Challenge 1"))
    }
}
