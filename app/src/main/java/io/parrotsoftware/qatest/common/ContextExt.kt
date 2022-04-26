package io.parrotsoftware.qatest.common

import android.content.Context
import android.widget.Toast

fun Context.toast(message: CharSequence, isLengthShort: Boolean = true) =
    Toast.makeText(
        this, message, if (isLengthShort) {
            Toast.LENGTH_SHORT
        } else {
            Toast.LENGTH_LONG
        }
    ).show()