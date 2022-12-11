package com.example.monprofil

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.monprofil.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun SearchView(state: MutableState<TextFieldValue>, viewModel: MainViewModel) {
    val navController: NavHostController = rememberNavController()
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        textStyle = TextStyle(fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(onClick = { state.value = TextFieldValue("") }) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        Modifier
                            .padding(15.dp)
                            .size(25.dp)
                    )

                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        keyboardActions = KeyboardActions(onDone = { navController.navigate("searchFilm") })
    )
}