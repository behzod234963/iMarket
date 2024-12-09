package com.mr.anonym.imarket.ui.items

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.ProductsItem
import com.mr.anonym.imarket.R
import com.mr.anonym.imarket.ui.theme.darkerGray

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchFieldItem(
    productsItem: ProductsItem,
    onIconClick:()->Unit,
    onCardClick:()->Unit,
    categoryText: @Composable ()->Unit
) {
    Card(
        modifier = Modifier
            .height(60.dp)
            .padding(vertical = 3.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White
        ),
        onClick = {
            onCardClick()
        },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                (if (productsItem.title == null) productsItem.category else productsItem.title)?.let {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.35f)
                            .padding(vertical = 2.dp),
                        text = it,
                        color = if (isSystemInDarkTheme())Color.White else Color.Black,
                        fontSize = 18.sp
                    )
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth(0.95f),
                    horizontalArrangement = Arrangement.Start
                ){ categoryText() }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(0.2f)
            ){
                IconButton(
                    onClick = {
                        onIconClick()
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_left_up),
                        contentDescription = "icon move to field",
                        tint = if (isSystemInDarkTheme())Color.White else Color.Black
                    )
                }
            }
        }
    }
}