/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.elyasasmad.my.prayertimes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.*
import com.elyasasmad.my.prayertimes.data.remote.PostsService
import com.elyasasmad.my.prayertimes.data.remote.dto.PrayerTime
import com.elyasasmad.my.prayertimes.data.remote.dto.PrayerTimeResponse
import com.elyasasmad.my.prayertimes.presentation.theme.PrayerTimesWearAppTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import java.util.*

class MainActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp()
        }
    }

}

val gson = Gson()

fun <T> T.serializeToMap(): Map<String, Any> {
    return convert()
}

inline fun <I, reified O> I.convert(): O {
    val json = gson.toJson(this)
    return gson.fromJson(json, object : TypeToken<O>() {}.type)
}

@Composable
fun WearApp() {

    val coroutineScope = rememberCoroutineScope()
    val service = PostsService.create()

    val (prayerTime, setPrayerTime) = remember {
        mutableStateOf<PrayerTime?>(null)
    }

    fun onResult(response: PrayerTimeResponse) {

        setPrayerTime(response.prayerTime[0])

    }

    PrayerTimesWearAppTheme {

        val listState = rememberScalingLazyListState()

        Scaffold(
            vignette = {
                Vignette(vignettePosition = VignettePosition.TopAndBottom)
            },
            positionIndicator = {
                PositionIndicator(
                    scalingLazyListState = listState
                )
            }
        ) {
            ScalingLazyColumn(
                state = listState,
                anchorType = ScalingLazyListAnchorType.ItemCenter
            ) {

                item {
                    Text("Waktu Solat", textAlign = TextAlign.Center, modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp, top = 24.dp),
                        fontWeight = FontWeight.Bold
                    )
                }

                if (prayerTime != null) {
                    items(items = prayerTime.serializeToMap().toList(), itemContent = {item ->
                        PrayerTimeItem(prayerName = item.first, prayerTime = item.second as String)
                    })
                }

                item {
                    Button(
                        onClick = {
                                  coroutineScope.launch {
                                      val result = service.getPrayerTimes()
                                      if (result != null) {
                                          onResult(result)
                                      }
                                  }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Rounded.Refresh, contentDescription = "refresh prayer times", modifier = Modifier
                                .size(24.dp)
                                .wrapContentSize(align = Alignment.Center))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Refresh")
                        }
                    }
                }

                item {
                    Text("Last updated at " + Date().time, modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp), fontSize = 10.sp, color = Color.Gray, textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@Composable
fun PrayerTimeItem(prayerName: String, prayerTime: String) {
    TitleCard(
        onClick = {},
        title = {
            Text(prayerName)
        }
    ) {
        Text(prayerTime)
    }
}

@Preview(
    widthDp = WEAR_PREVIEW_DEVICE_WIDTH_DP,
    heightDp = WEAR_PREVIEW_DEVICE_HEIGHT_DP,
    apiLevel = WEAR_PREVIEW_API_LEVEL,
    uiMode = WEAR_PREVIEW_UI_MODE,
    backgroundColor = WEAR_PREVIEW_BACKGROUND_COLOR_BLACK,
    showBackground = WEAR_PREVIEW_SHOW_BACKGROUND
)
@Composable
fun DefaultPreview() {
    WearApp()
}