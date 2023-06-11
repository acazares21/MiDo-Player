package com.acazares.mido

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acazares.mido.ui.theme.MiDoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiDoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlayerTopView("current song")
                    //Greeting("Android")
                }
            }
        }
    }
}

//TOP PLAYER
/*
* Here I use a card where it contains all the essential stuffs related to music player:
* - Now playing, when it's clicked show a expanded card.
****Clicked****
* - Left side: Album art of current song played.
* - Right side: Song name, album name.
* - Player controls.
 */
@Composable
fun PlayerTopView(songName: String) {
    var expanded by remember { mutableStateOf(false) }
//    LaunchedEffect(Unit){
//        fetchData
//    }
    Column(
        modifier = Modifier
            .padding(4.dp, 4.dp)
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { expanded = !expanded }
        ) {
            Column {
                PlayerMain(songName, expanded)
            }
        }
        // Footer
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row() {
                    Text(text = "Menú inferior contextual")
                }
            }
        }
    }
}

//Main view of player
@Composable
fun PlayerMain(songName: String, expanded: Boolean) {
    Text(
        "Now playing: $songName",
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.labelLarge
        //textDecoration = TextDecoration.Underline
    )
    AnimatedVisibility(expanded) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            Text(
                text = " Sección del reproductor de música ",
                style = MaterialTheme.typography.bodyLarge
            )
            Card(
                border = BorderStroke((-1).dp, androidx.compose.ui.graphics.Color.Black),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                Card() {
                    Row(modifier = Modifier.padding(all = 8.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = " Album art ",
                            modifier = Modifier
                                // Set image size to 40 dp
                                .size(40.dp)
                                // Clip image to be shaped as a circle
                                .clip(CircleShape)
                        )
                        //Space between album art and song information
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(text = "Song Name")
                            // Add a vertical space between the author and message texts
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "Album")
                        }
                    }
                }
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Row() {
                            Icon(
                                Icons.Rounded.KeyboardArrowLeft,
                                contentDescription = "Back",
                                modifier = Modifier.size(44.dp).clickable { println("CLICKED BACK BUTTON")
                                System.out.println("CLICKED BACK BUTTON")}
                            )
                            Icon(
                                Icons.Rounded.PlayArrow,
                                contentDescription = "Play/Pause",
                                modifier = Modifier.size(44.dp).clickable { println("CLICKED PLAY BUTTON") }
                            )
                            Icon(
                                Icons.Rounded.KeyboardArrowRight,
                                contentDescription = "Forward",
                                modifier = Modifier.size(44.dp).clickable { println("CLICKED FORWARD BUTTON") }
                            )
                        }
                    }
                }
            }
            /*repeat(10) {
                Text("Item $it", modifier = Modifier.padding(2.dp))
            }*/
        }
    }
}

//For testing purpose
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Card() {
        var expanded by remember { mutableStateOf(false) }
        val context = LocalContext.current
        Column(Modifier.clickable { expanded = !expanded }) {
            Text(text = "AAAA")
            AnimatedVisibility(expanded) {
                Column(modifier.verticalScroll(rememberScrollState())) {
                    Text(
                        text = "Jetpack Compose ",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    repeat(10) {
                        Text("Item $it", modifier = Modifier.padding(2.dp))
                    }
                }
            }
        }
    }
}

//PREVIEWS
@Preview(showBackground = true)
@Composable
fun PlayerPreview() {
    MiDoTheme {
        PlayerTopView("Android")
    }
}