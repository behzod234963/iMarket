package com.mr.anonym.imarket.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

@Composable
fun DefaultOutlinedTextFiled(
    modifier: Modifier,
    value: String,
    singleLine: Boolean = false,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    textStyle: TextStyle? = null,
    colors: TextFieldColors,
    shape: Shape? = null,
    keyboardOptions: KeyboardOptions? = null,
    keyboardActions: () -> Unit,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit,
    placeholder: @Composable () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        singleLine = singleLine,
        colors = colors,
        textStyle = textStyle!!,
        shape = shape!!,
        readOnly = readOnly,
        enabled = enabled,
        leadingIcon = { leadingIcon() },
        trailingIcon = { trailingIcon() },
        placeholder = { placeholder() },
        keyboardOptions = keyboardOptions!!,
        keyboardActions = KeyboardActions(
            onDone = { keyboardActions() },
            onGo = { keyboardActions() },
            onNext = { keyboardActions() },
            onPrevious = { keyboardActions() },
            onSend = { keyboardActions() },
            onSearch = { keyboardActions() }
        ),
    )
}