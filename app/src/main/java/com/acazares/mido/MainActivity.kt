package com.acazares.mido

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Settings
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acazares.mido.ui.theme.MiDoTheme

//import com.acazares.mido.assets.ScanMusicFiles

//MAIN ACTIVITY
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
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayerTopView(songName: String) {
    var expanded by remember { mutableStateOf(false) }
    var selectedViewMenu by remember { mutableStateOf("Songs") }
//    LaunchedEffect(Unit){
//        fetchData
//    }
    Column(
        modifier = Modifier
            .padding(4.dp, 21.dp)
            .fillMaxSize()
        //.verticalScroll(rememberScrollState())
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
        //scanMusicFiles()

        //NAVIGATION
        /*
        This part is for navigation views, each view should have its own composable functions.
        Views selection will depend on which option from the menu below is selected.
        By default, main view will be songs list.
         */
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(7f)) {
            NavigationViews(id = selectedViewMenu)
        }

//        LazyColumn(
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(7f)
//                .padding(4.dp)
//        ) {
//            item {
//                Text(
//                    text = "Song list",
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
//            // Add # items
//            items(44) { index ->
//                Text(text = "Song number: $index")
//            }
//        }
        // Footer
        Card(
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    //.border((4).dp, androidx.compose.ui.graphics.Color.Black)
                    .fillMaxSize()
                    //.weight(1f)
                    .padding(vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(4.dp)
                            //.horizontalScroll(rememberScrollState())
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Card(modifier = Modifier.clickable { selectedViewMenu = "Songs" }) {
                            Column() {
                                Icon(
                                    Icons.Rounded.Person,
                                    contentDescription = "Songs",
                                    modifier = Modifier
                                        .size(44.dp)
                                    //.clickable { println("CLICKED FORWARD BUTTON") }
                                )
                                Text(text = "Songs")
                            }
                        }
                        Card(modifier = Modifier.clickable { selectedViewMenu = "Albums" }) {
                            Column() {
                                Icon(
                                    Icons.Rounded.Person,
                                    contentDescription = "Albums",
                                    modifier = Modifier
                                        .size(44.dp)
                                    //.clickable { println("CLICKED FORWARD BUTTON") }
                                )
                                Text(text = "Albums")
                            }
                        }
                        Card(modifier = Modifier.clickable { selectedViewMenu = "Artists" }) {
                            Column() {
                                Icon(
                                    Icons.Rounded.MoreVert,
                                    contentDescription = "Forward",
                                    modifier = Modifier
                                        .size(44.dp)
                                )
                                Text(text = "Artists")
                            }
                        }
                        Card(modifier = Modifier.clickable { selectedViewMenu = "Playlists" }) {
                            Column() {
                                Icon(
                                    Icons.Rounded.List,
                                    contentDescription = "Forward",
                                    modifier = Modifier
                                        .size(44.dp)
                                )
                                Text(text = "Playlists")
                            }
                        }

                        Card(modifier = Modifier.clickable { selectedViewMenu = "Settings" }) {
                            Column() {
                                Icon(
                                    Icons.Rounded.Settings,
                                    contentDescription = "Forward",
                                    modifier = Modifier
                                        .size(44.dp)
                                )
                                Text(text = "Settings")
                            }
                        }
                    }
                }
            }
        }
    }
}

//Main view of player
@Composable
fun PlayerMain(songName: String, expanded: Boolean) {
    val context = LocalContext.current
    Text(
        "Now playing: $songName",
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.labelLarge
        //textDecoration = TextDecoration.Underline
    )
    AnimatedVisibility(expanded) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
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
                            Text(text = "Artist")
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
                                modifier = Modifier
                                    .size(44.dp)
                                    .clickable {
                                        Toast
                                            .makeText(
                                                context,
                                                "BACK",
                                                Toast.LENGTH_LONG
                                            )
                                            .show()
                                    }
                            )
                            Icon(
                                Icons.Rounded.PlayArrow,
                                contentDescription = "Play/Pause",
                                modifier = Modifier
                                    .size(44.dp)
                                    .clickable { println("CLICKED PLAY BUTTON") }
                            )
                            Icon(
                                Icons.Rounded.KeyboardArrowRight,
                                contentDescription = "Forward",
                                modifier = Modifier
                                    .size(44.dp)
                                    .clickable { println("CLICKED FORWARD BUTTON") }
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

//Just for testing purpose
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

//Views
@Composable
@ExperimentalFoundationApi
fun NavigationViews(id: String) {
    Text(
        text = id,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable { println("a") }
    )

    if (id != null) {
        SongsLists()
    }

}

//SONGS LISTS - TEST
@Composable
fun SongsLists() {
    /*TODO
    *  Fix songs list view and each card of it
    * */
    LazyColumn(
        modifier = Modifier
            .padding(14.dp)
            .fillMaxWidth()

            .padding(4.dp)
    ) {
        repeat(81) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(81.dp)
                        .padding(4.dp)
                ) {
                    Row(modifier = Modifier.size(width = 400.dp, height = 100.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.michi_facha),
                            contentDescription = "Testing"
                        )
                        Column(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "artist.name",
                                modifier = Modifier.paddingFromBaseline(top = 1.dp)
                            )
                            Text("artist.album")
                            Text("artist.song")
                        }
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