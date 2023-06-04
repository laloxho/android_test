package io.parrotsoftware.qatest.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T : Any, L : LiveData<T>?> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) {
    removeObservers(liveData)
    liveData?.observe(this, Observer(body))
}

fun <T : Any, L : LiveData<T>?> LifecycleOwner.removeObservers(liveData: L) {
    liveData?.removeObservers(this)
}

fun Fragment.menuProvider(menuProvider: MenuProvider) =
    (requireActivity() as MenuHost).addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)

fun Fragment.supportActionBar() = (activity as AppCompatActivity).supportActionBar
