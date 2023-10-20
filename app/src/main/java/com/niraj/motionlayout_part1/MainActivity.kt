package com.niraj.motionlayout_part1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.niraj.motionlayout_part1.ui.theme.MotionLayoutPart1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotionLayoutPart1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.DarkGray
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        MyCard()
    }
}


@OptIn(ExperimentalMotionApi::class)
@Composable
fun MyCard() {
    var state by remember {
        mutableStateOf(true)
    }
    val progress by animateFloatAsState(
        targetValue = if(state) 1f else 0f,
        animationSpec = TweenSpec(
            durationMillis = 300,
            easing = FastOutLinearInEasing
        ),
        label = "Motion Layout progress"
    )

    MotionLayout(
        start = normalState,
        end = expandedState,
        progress = progress,
        modifier = Modifier
            .clickable {
                state = !state
            }
            .fillMaxWidth()
    ) {
        SourceAuthorName()
        ImageView()
        NewsTitle()
        NewsBody()
    }
}

@Composable
fun ImageView(
    modifier: Modifier = Modifier.layoutId("imageView"),
    imageUrl: String = "https://source.unsplash.com/featured/1920x1080"
) {
    AsyncImage(
        modifier = modifier
            .clip(RoundedCornerShape(CornerSize(9.dp))),
        contentScale = ContentScale.Crop,
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build(),
        contentDescription = "NULL"
    )
}

@Composable
fun SourceAuthorName(
    sourceName: String = "Source Name",
    authorName: String = "Author Name"
) {
    Text(
        modifier = Modifier.layoutId("sourceName"),
        text = "$sourceName • $authorName",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.secondary
    )
}

@Composable
fun NewsTitle(
    title: String = "Cong shares videos of Bharat Jodo Yatra as march completes 1 month"
) {
    Text(
        modifier = Modifier.layoutId("title"),
        text = title,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun NewsBody(
    body: String = "Congress took to Twitter on Saturday to share videos and photographs from Bharat Jodo Yatra as the march completed one month. It said Congress leader Rahul Gandhi made the \"first call to unite India\" by starting the yatra on September 7. The party also shared a picture where Rahul Gandhi is seen delivering a speech amid heavy rain in Karnataka."
) {
    Text(
        modifier = Modifier.layoutId("body"),
        text = body,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}