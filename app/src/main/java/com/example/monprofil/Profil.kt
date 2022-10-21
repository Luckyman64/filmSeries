package com.example.monprofil

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun photoProfil(id: Int){
    Image(painter = painterResource(id = id), contentDescription = "photo de profil", modifier = Modifier
        .clip(
            CircleShape
        )
        .size(200.dp)
        .border(4.dp, Color(0xEE82EE)))
    Text(text = "Clement Lantiat", fontSize = 25.sp)
    Text(text = "Etudiant en licence professionnelle DReAM")
}

@Composable
fun photo(id: Int){
    Image(painter = painterResource(id = id), contentDescription = "photo de profil", modifier = Modifier.size(18.dp))
}

@Composable
fun Screen(windowClass: WindowSizeClass, onNavigateToFriends: () -> Unit) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround)
            {
                photoProfil(R.drawable.photoprofil)
                Surface() {
                    Column(modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround) {

                        Spacer(modifier = Modifier.height(60.dp))
                        Row() {
                            photo(R.drawable.mail)
                            Text(text = "clement.lantiat@gmail.com", fontSize = 17.sp)
                        }
                        Row() {
                            photo(id = R.drawable.linkedinlogo)
                            Text(text = "www.linkedin.com/ln/clement-lantiat")
                        }
                        Spacer(modifier = Modifier.height(150.dp))
                        Button(onClick = onNavigateToFriends, shape = RoundedCornerShape(50), modifier = Modifier
                            .width(200.dp)
                            .height(50.dp)) {
                            Text(text = "Demarrer")
                        }
                    }
                }
            }
        }
        else -> {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically)
            {
                photoProfil(R.drawable.photoprofil)
                Surface() {
                    Column(modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround) {

                        Spacer(modifier = Modifier.height(60.dp))
                        Row() {
                            photo(R.drawable.mail)
                            Text(text = "clement.lantiat@gmail.com", fontSize = 17.sp)
                        }
                        Row() {
                            photo(id = R.drawable.linkedinlogo)
                            Text(text = "www.linkedin.com/ln/clement-lantiat")
                        }
                        Spacer(modifier = Modifier.height(150.dp))
                        Button(onClick = onNavigateToFriends, shape = RoundedCornerShape(50), modifier = Modifier
                            .width(200.dp)
                            .height(50.dp)) {
                            Text(text = "Demarrer")
                        }
                    }
                }
            }
        }
    }
}

