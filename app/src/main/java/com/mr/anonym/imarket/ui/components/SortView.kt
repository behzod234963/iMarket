package com.mr.anonym.imarket.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.imarket.ui.theme.darkerGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortView(
    value:String,
    isExpanded:Boolean,
    onValueChange:(String)->Unit,
    onExpandedChange:(Boolean)->Unit,
    onDismissRequest:()->Unit,
    content: @Composable () ->Unit
) {

    val anchorType = MenuAnchorType.PrimaryNotEditable

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {
            onExpandedChange(it)
        },
        modifier = Modifier
            .width(150.dp)
            .height(50.dp)
    ) {
        OutlinedTextField(
            value =value,
            onValueChange = {
                onValueChange(it)
            },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = if(isSystemInDarkTheme()) darkerGray else Color.White,
                focusedContainerColor = if(isSystemInDarkTheme()) darkerGray else Color.White,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent
            ),
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black
            ),
            modifier = Modifier
                .menuAnchor(type = anchorType)
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                onDismissRequest()
            }
        ) {
            content()
        }
    }
}