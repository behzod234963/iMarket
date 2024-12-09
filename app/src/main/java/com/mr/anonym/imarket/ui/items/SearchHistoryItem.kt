package com.mr.anonym.imarket.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.SearchHistoryModel
import com.mr.anonym.imarket.ui.theme.darkerGray

@Composable
fun SearchHistoryItem(
    historyModel: SearchHistoryModel,
    onHistoryClick:()->Unit
) {

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(if (isSystemInDarkTheme()) darkerGray else Color.White)
            .padding(horizontal = 15.dp)
            .clickable { onHistoryClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = historyModel.title,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            fontSize = 16.sp
        )
    }
}