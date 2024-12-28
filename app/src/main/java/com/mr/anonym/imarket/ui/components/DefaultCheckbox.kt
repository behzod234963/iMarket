package com.mr.anonym.imarket.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultCheckbox(
    isChecked:Boolean,
    onCheckedChange:(Boolean)->Unit
) {
    Checkbox(
        modifier = Modifier
            .size(50.dp),
        checked = isChecked,
        onCheckedChange = { onCheckedChange(it) },
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Red
        )
    )
}