package io.parrotsoftware.qatest.data.datasource

import io.parrotsoftware.qatest.fake.FakePrefsStorage
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class PrefsStorageTest {

    @Rule @JvmField val mockitoRule: MockitoRule = MockitoJUnit.rule()
    private lateinit var prefsStorage: FakePrefsStorage

    @Before
    fun setUp() {
        prefsStorage = FakePrefsStorage()
    }

    @Test
    fun `Check that the string value is being saved correctly`() {
        prefsStorage.setString("some_key", "some_string_value")
        val value = prefsStorage.getString("some_key")
        assertThat(value, equalTo("some_string_value"))
    }

    @Test
    fun `Check that the int value is being saved correctly`() {
        prefsStorage.setInt("some_key", 35)
        val value = prefsStorage.getInt("some_key")
        assertThat(value, equalTo(35))
    }

    @Test
    fun `Check that the boolean value is being saved correctly`() {
        prefsStorage.setBoolean("some_key", true)
        val value = prefsStorage.getBoolean("some_key")
        assertThat(value, equalTo(true))
    }

    @Test
    fun `Check that the values are removed correctly`() {
        prefsStorage.setString("some_key", "some_string_value")
        prefsStorage.clear()
        val value = prefsStorage.getString("some_key")
        assert(value.isEmpty())
    }
}
