package developer.android.compose_codelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import developer.android.compose_codelab.ui.theme.Compose_codelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MainScreenContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    Compose_codelabTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember { mutableStateOf(value = false) }
    val backgroundColor by animateColorAsState(targetValue = if (isSelected) Color.DarkGray else Color.White)
    val textColor by animateColorAsState(targetValue = if (isSelected) Color.White else Color.DarkGray)
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .clickable { isSelected = !isSelected }) {
        Text(text = name, color = textColor, modifier = Modifier.padding(24.dp))
    }
}

@Composable
fun MainScreenContent() {
    val count = remember { mutableStateOf(0) }
    val names = List(1000) { "Name #$it" }

    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names = names, modifier = Modifier.weight(1f))
        Counter(count.value) { newCount ->
            count.value = newCount
        }
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(onClick = { updateCount(count + 1) }, modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Text("I've been clicked $count times", modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(names) { name ->
            Greeting(name = name)
            Divider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MainScreenContent()
    }
}