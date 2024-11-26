package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.Purple40
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    var currentStep by remember { mutableStateOf(0) }
    var lemonPressCount by remember { mutableStateOf(Random.nextInt(2, 5)) }
    var pressesRemaining by remember { mutableStateOf(lemonPressCount) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (currentStep) {
            0 -> {
                Card(modifier = Modifier
                    .height(200.dp)
                    .width(200.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(contentColor = Purple40)
                ){
                    Image(painterResource(id = R.drawable.lemon_tree), contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                pressesRemaining = lemonPressCount
                                currentStep = 1
                            }
                    )

                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(stringResource(id = R.string.first_tap), fontSize = 18.sp, modifier = Modifier, textAlign = TextAlign.Center)
            }
            1 -> {
                LemonPressStep(
                    pressesRemaining = pressesRemaining,
                    onPress = {
                        pressesRemaining--
                        if (pressesRemaining <= 0) {
                            currentStep = 2
                        }
                    }
                )
            }
            2 -> {
                Card(modifier = Modifier
                    .height(200.dp)
                    .width(200.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(contentColor = Purple40)
                ){
                    Image(painterResource(id = R.drawable.lemon_drink), contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                pressesRemaining = lemonPressCount
                                currentStep = 3
                            }
                    )

                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(stringResource(id = R.string.third_tap), fontSize = 18.sp, modifier = Modifier, textAlign = TextAlign.Center)
            }
            3 -> {
                Card(modifier = Modifier
                    .height(200.dp)
                    .width(200.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(contentColor = Purple40)
                ){
                    Image(painterResource(id = R.drawable.lemon_restart), contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                pressesRemaining = lemonPressCount
                                currentStep = 0
                            }
                    )

                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(stringResource(id = R.string.fourth_tap), fontSize = 18.sp, modifier = Modifier, textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun LemonButton(onClick: () -> Unit, imageResId: Int) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = "Lemon Button",
        modifier = Modifier
            .size(64.dp)
            .clickable { onClick() }
    )
}

@Composable
fun LemonPressStep(pressesRemaining: Int, onPress: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(modifier = Modifier
            .height(200.dp)
            .width(200.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(contentColor = Purple40)
        ){
            Image(painterResource(id = R.drawable.lemon_squeeze), contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onPress() })

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(id = R.string.second_tap), fontSize = 18.sp, modifier = Modifier, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}