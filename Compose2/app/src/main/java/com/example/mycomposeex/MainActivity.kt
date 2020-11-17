package com.example.mycomposeex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirlineSeatReclineNormal
import androidx.compose.material.icons.filled.BatteryStd
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

import com.example.mycomposeex.ui.MyComposeExTheme



// Modifiers allow you to decorate a composable. You can change its behavior, appearance,
// add information like accessibility labels,
// process user input or even add high-level interactions like making something clickable,
// scrollable, draggable or zoomable. Modifiers are regular Kotlin objects.
// You can assign them to variables and reuse them.
// You can also chain several modifiers one after the other to compose them.

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeExTheme {
                // A surface container using the 'background' color from the theme
               // Surface(color = MaterialTheme.colors.background) {
                    //Greeting("Android")
                //}
                LayoutsComposeEx()
            }
        }
    }
}

//Most composables accept an optional modifier parameter to make them more flexible,
// enabling the caller to modify them.
// If you're creating your own composable, consider having a modifier as a parameter,
// default it to Modifier (i.e. empty modifier that doesn't do anything)
// and apply it to the root composable of your function. In this case:
//Note: By convention, the modifier is specified as the first optional parameter of a function.
// This enables you to specify a modifier on a composable without having to name all parameters.

@Composable
fun MyContent(modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        Text(text = "I'm in compose")
        Text(text = "I'm fine")

    }
}

// Scaffold
// Scaffold allows you to implement a UI with the basic Material Design layout structure.
// It provides slots for the most common top-level Material components such as TopAppBar,
// BottomAppBar, FloatingActionButton and Drawer. With Scaffold,
// you make sure these components will be positioned and work together correctly.
@Composable
fun LayoutsComposeEx(){
    Scaffold(topBar = {
        Column {
            TopAppBar(title = {
                Text(text = "Scaffold",
                        style = MaterialTheme.typography.h3,
                        color = Color.Green
                ) },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.AirlineSeatReclineNormal)
                    }
                })
            Text(text = "Layout compose", style = MaterialTheme.typography.h5)
        }
    }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            MyContent(Modifier.padding(innerPadding).padding(10.dp))
        }
    }
}

// measuring composable and placement in a layout
// for individual compose
fun Modifier.firstBaselineToTop(firstBaselineToTop: Dp) =
                    Modifier.layout{ measurable, constraints ->
    val placeable = measurable.measure(constraints)

    // check the composable has a first baseline
    check(placeable[FirstBaseline]!= AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]

    // height of the composable with padding - firstbaseline
    val placeableY = firstBaselineToTop.toIntPx() - firstBaseline
    val height = placeable.height + placeableY
    layout(placeable.width,height ){
        // where the composable is placed
        placeable.placeRelative(0,placeableY)
    }
}

//Implementing a basic Column
@Composable
fun CustomLayout(modifier: Modifier = Modifier,children: @Composable ()->Unit){
//    Layout(modifier = modifier,children = children){
//            measurable, constraints ->
//            val placeables = measurable.map { m -> m.measure(constraints) }
//    }
}

@Composable
fun MyOwnColumn(modifier: Modifier=Modifier,children: @Composable() () -> Unit){
//    Layout(modifier = modifier,children = children){
//            measurables,constraints ->
//            // Don't constrain child views further, measure them with given constraints
//            // List of measured children
//            val placeables = measurables.map { measurable ->
//                    // Measure each children
//                measurable.measure(constraints) }
//    }
}



@Composable
fun PhotographerCard() {
    val image = imageResource(R.drawable.thai1)
    Row(modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colors.surface)
            .padding(9.dp)
            .clickable(onClick = {})
    ){
           Surface(modifier = Modifier.preferredSize(50.dp),
           shape = CircleShape,
           color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)) {
        //           val imageMod = Modifier
        //                   .preferredHeight(180.dp)
        //                   .fillMaxWidth()
        //                   .clip(shape = RoundedCornerShape(13.dp))
               Image(image)
       }
        Column(modifier = Modifier
                .padding(start=8.dp) // modifier order matter all concatenate into 1 value
                .align(Alignment.CenterVertically)) {// the order affect final value
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            Providers(AmbientContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun TextWithPaddingToBaselinePreview(){
    MyComposeExTheme() {
        Text("hello Jujy",Modifier.firstBaselineToTop(32.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun TextWithNormalPaddingPreview(){
    MyComposeExTheme() {
        Text("hello Jujy",Modifier.padding(32.dp))
    }
}



@Preview(showBackground = true)
@Composable
fun PhotographerCardPreview() {
    MyComposeExTheme {
        PhotographerCard()
        LayoutsComposeEx()
    }
}