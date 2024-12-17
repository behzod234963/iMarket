package com.mr.anonym.imarket.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mr.anonym.imarket.R
import com.mr.anonym.imarket.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchFieldTopAppBar(
    text:String,
    navController:NavHostController,
    onValueChange:(String)->Unit,
    onSend:()->Unit,
    onClear:()->Unit
) {

    val keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
    val keyBoardController = LocalSoftwareKeyboardController.current
    DefaultTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Red
        ),
        title = {
            DefaultOutlinedTextFiled(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(Screens.SearchFieldScreen.route)
                    },
                value = text,
                onValueChange = { onValueChange(it) },
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 17.sp
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    disabledContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledBorderColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black
                ),
                keyboardOptions = keyboardOptions,
                keyboardActions = {
                    onSend()
                    keyBoardController?.hide()
                },
                shape = RoundedCornerShape(10.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "icon search field",
                        tint = Color.LightGray
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_in_imarket),
                        color = Color.LightGray,
                        fontSize = 16.sp
                    )
                },
                trailingIcon = {
                    Row (
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .width(80.dp),
                    ){
//                        Button clear search field
                        IconButton(
                            onClick = {
                                onClear()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "button clear search field",
                                tint = Color.Gray
                            )
                        }
                        IconButton(
                            onClick = {
                                onSend()
                                keyBoardController?.hide()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "button search",
                                tint = Color.Black
                            )
                        }
                    }
                }
            )
        } ,
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "icon back to main",
                    tint = Color.White
                )
            }
        },
        actions = {}
    )
}