package com.mr.anonym.imarket.ui.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.ProductsItem
import com.mr.anonym.imarket.ui.components.DefaultCheckbox

@Composable
fun FilterViewBrandItem(
    color:Color,
    isChecked:Boolean = false,
    onValueChange:(Boolean)->Unit,
    model:ProductsItem
) {

    Row (
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        DefaultCheckbox(
            isChecked = isChecked,
            onCheckedChange = { onValueChange(it) }
        )
        Spacer(Modifier.width(10.dp))
        model.brand?.let {
            Text(
                text = it,
                color = if (color == Color.Black) Color.White else Color.Black,
                fontSize = 18.sp
            )
        }
    }
}