package com.example.myandroidcompose


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.ui.tooling.preview.Preview
import com.example.myandroidcompose.ui.*
import androidx.compose.material.Surface as Surface

// some default material color themes
private val DarkColors = darkColors(
    primary = purple200,
    primaryVariant = purple700,
    secondary = teal200
)

private val LightColors = lightColors(
    primary = purple500,
    primaryVariant = purple700,
    secondary = teal200
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Greeting(name = "Vanh" )

            MyApp {
                NewsStory()
            }
            MyScreenContent()
            //Greeter()
        }
    }
}

@Composable
fun NewsStory(){
    val image = imageResource(R.drawable.sharkfin)
    MaterialTheme() {
        Column(modifier = Modifier.padding(16.dp)) {
            val imageMod = Modifier
                .preferredHeight(180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(13.dp))
            Image(image, modifier = imageMod, contentScale = ContentScale.Crop)
            Spacer(Modifier.preferredHeight(16.dp))
            Text("A day in Shark Fin Cove wondering around the cove looking for something interesting,"
                    + " I saw a shark!",
                    style = MaterialTheme.typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis)
            Text("Davenport, California",style = typography.body2)
            Text("December 2018",style = typography.body2)
        }
    }
}

@Composable
fun BasicsCodelabTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit) {
    Surface(color = Color.Yellow) {
        content()
   //     Text(text= "hi")
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {

    BasicsCodelabTheme{
//        //Text(text= "hi")
//        Surface(color = Color.Yellow) {
        content()
    }
}

@Composable
fun Greeter(){
    Surface(color = Color.Yellow) {
        Text(text = "Hi from Vanh and Jujy",modifier = Modifier.padding(24.dp))
    }
}
//Note: You can modify a predefined style by using the copy function
// — they're regular Kotlin data classes!
//For example, style = MaterialTheme.typography.body1.copy(color = Color.Yellow)
@Composable
fun Greeting(name: String){
    Text(text = "Hello $name!",
            modifier = Modifier.padding(10.dp),
            style = typography.h5,
            //color = MaterialTheme.typography.body1.copy(color = Color.Green),
            color  = Color.Green
    )
}

@Composable
fun MyScreenContent(names:List<String> = listOf("Pam","Jujy")) {
    val counterState = remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxHeight()) {
        Column(modifier = Modifier.weight(1f)) {
            for(name in names){
                Greeting(name = name)
                Divider(color = Color.Cyan)
            }
        }
  //      Greeting("Android")
        Divider(color = Color.Black)
        Greeting("there")
        Counter(count = counterState.value, {c -> counterState.value = c})
    }

}

@Composable
fun Counter(count:Int,updateCounter:(Int) -> Unit){
  //  val count = remember { mutableStateOf(0)}
//    Button(onClick = { count.value++ }) {
    Button(onClick = { updateCounter(count + 1) },
            colors = ButtonConstants.defaultButtonColors(
                    backgroundColor = if(count>5) Color.Green else Color.White
            )) {
        Text(text = "I've been clicked ${count} times",color = Color.Red,style = typography.h4)
    }
}
//MaterialTheme holds configuration for colors and typography.
@Composable
fun MyTheme(content: @Composable() () -> Unit){
    val darkTheme = isSystemInDarkTheme()
    val color = if(darkTheme) DarkColors else LightColors
    MaterialTheme(colors = color) {
        content()
    }
}

@Preview(showBackground = true,name="Text preview")
@Composable
fun DefaultPreview() {
    //val counterState = remember { mutableStateOf(0) }
    Column {
        MyApp{  NewsStory() }
        MyTheme {
            Text(text = "hi")
        }
//        Greeter()
//        Divider(color = Color.Green)
        Greeting(name = "Today is Friday!")
//        Divider(color = Color.Green)
//        Greeting(name = "then is going to be Saturday")


        MyScreenContent()
       // Counter(count=counterState.value,{ c -> counterState.value = c})

//        Spacer(modifier = Modifier.padding(10.dp))
    }
}

