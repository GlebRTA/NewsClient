package com.example.newsclient.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsclient.R
import com.example.newsclient.domain.FeedPost
import com.example.newsclient.domain.StatisticItem
import com.example.newsclient.domain.StatisticType

@Composable
fun ClientPost(
    feedPost: FeedPost,
    modifier: Modifier = Modifier,
    onViewsClickListener: (StatisticItem) -> Unit,
    onSharesClickListener: (StatisticItem) -> Unit,
    onCommentsClickListener: (StatisticItem) -> Unit,
    onLikeClickListener: (StatisticItem) -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.background,
    ) {
        Column {
            ClientPostHeader(feedPost)
            ClientPostBody(feedPost)
            ClientPostFooter(
                statistics = feedPost.statistics,
                onViewsClickListener = onViewsClickListener,
                onSharesClickListener = onSharesClickListener,
                onCommentsClickListener = onCommentsClickListener,
                onLikeClickListener = onLikeClickListener
            )
        }
    }
}

@Composable
private fun ClientPostHeader(
    feedPost: FeedPost
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = feedPost.avatarResId),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .padding(4.dp)
        )

        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
        ) {
            Text(
                text = feedPost.communityName,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = feedPost.publicationDate,
                fontSize = 16.sp,
                color = MaterialTheme.colors.onSecondary
            )
        }

        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colors.onSecondary
        )
    }
}

@Composable
private fun ClientPostBody(
    feedPost: FeedPost
) {

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text(
            text = feedPost.contentText,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Image(
            painter = painterResource(id = feedPost.postImgId),
            contentDescription = "meme",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun ClientPostFooter(
    statistics: List<StatisticItem>,
    onViewsClickListener: (StatisticItem) -> Unit,
    onSharesClickListener: (StatisticItem) -> Unit,
    onCommentsClickListener: (StatisticItem) -> Unit,
    onLikeClickListener: (StatisticItem) -> Unit,
) {
    Row(
        modifier = Modifier.padding(top = 4.dp, bottom = 16.dp, start = 8.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.weight(1f),
        ) {
            val viewsType = statistics.getItemById(StatisticType.VIEWS)
            IconWithText(
                text = viewsType.count.toString(),
                iconId = R.drawable.ic_eye,
                onClickListener = {
                    onViewsClickListener(viewsType)
                }
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val sharesItem = statistics.getItemById(StatisticType.SHARES)
            IconWithText(
                text = sharesItem.count.toString(),
                iconId = R.drawable.ic_share,
                onClickListener = {
                    onSharesClickListener(sharesItem)
                }
            )
            Spacer(modifier = Modifier.width(25.dp))
            val commentsItem = statistics.getItemById(StatisticType.COMMENTS)
            IconWithText(
                text = commentsItem.count.toString(),
                iconId = R.drawable.ic_comments,
                onClickListener = {
                    onCommentsClickListener(commentsItem)
                }
            )
            Spacer(modifier = Modifier.width(25.dp))
            val likesItem = statistics.getItemById(StatisticType.LIKES)
            IconWithText(
                text = likesItem.count.toString(),
                iconId = R.drawable.ic_like,
                onClickListener = {
                    onLikeClickListener(likesItem)
                }
            )
        }
    }
}

private fun List<StatisticItem>.getItemById(type: StatisticType): StatisticItem {
    return this.find { it.type == type }
        ?: throw IllegalMonitorStateException("Incorrect statistic type in post footer")
}

@Composable
private fun IconWithText(
    text: String,
    iconId: Int,
    onClickListener: () -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            onClickListener()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "Eye icon",
            modifier = Modifier
                .size(20.dp),
            tint = MaterialTheme.colors.onSecondary
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
        )
    }
}