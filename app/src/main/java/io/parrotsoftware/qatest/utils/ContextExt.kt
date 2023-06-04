package io.parrotsoftware.qatest.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toast(@StringRes resource: Int, isLengthShort: Boolean = true) =
    Toast.makeText(
        this,
        resource,
        if (isLengthShort) {
            Toast.LENGTH_SHORT
        } else {
            Toast.LENGTH_LONG
        },
    ).show()
