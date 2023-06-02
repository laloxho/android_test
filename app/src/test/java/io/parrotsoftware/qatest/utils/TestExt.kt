package io.parrotsoftware.qatest.utils

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.mockito.Mockito

inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)

inline fun <reified T> classOf(): Class<T> = T::class.java

inline fun <reified T> assertIsInstanceOf(actual: Any?) =
    assertThat(actual, CoreMatchers.instanceOf(classOf<T>()))
