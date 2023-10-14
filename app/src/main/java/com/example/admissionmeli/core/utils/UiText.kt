package com.example.admissionmeli.core.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any,
    ) : UiText()

}