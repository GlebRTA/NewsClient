package com.example.newsclient.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsclient.domain.FeedPost
import com.example.newsclient.domain.PostComment
import com.example.newsclient.ui.theme.NewsClientTheme


@Composable
fun CommentsScreen(
    feedPost: FeedPost,
    postComments: List<PostComment>,
    onBackListener: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Comments for Feed Post Id ${feedPost.id}") },
                navigationIcon = {
                    IconButton(onClick = { onBackListener() }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Back Button"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(
                top = 16.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 72.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = postComments, key = {it.id}) {comment ->
                Comment(postComment = comment)
            }
        }
    }
}


@Composable
fun Comment(postComment: PostComment) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = postComment.authorAvatarId),
            contentDescription = "Comment Icon"
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "${postComment.authorName} CommentID: ${postComment.id}",
                color = MaterialTheme.colors.onPrimary,
                fontSize = 14.sp
            )
            Text(
                text = postComment.commentText,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 16.sp
            )
            Text(
                text = postComment.publicationDate,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 14.sp
            )
        }
    }
}
