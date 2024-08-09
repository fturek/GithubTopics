package com.example.design.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Light mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = 400,
    heightDp = 720,
    showBackground = true
)
@Preview(
    name = "Dark mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    widthDp = 400,
    heightDp = 720,
    showBackground = true
)
@Preview(
    name = "Small screen",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = 320,
    heightDp = 520,
    showBackground = true
)
annotation class ScreenPreview