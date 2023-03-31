package com.example.newsclient.ui

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsclient.CommentsViewModel
import com.example.newsclient.CommentsViewModelFactory
import com.example.newsclient.domain.FeedPost
import com.example.newsclient.domain.PostComment


@Composable
fun CommentsScreen(
    onBackListener: () -> Unit,
    feedPost: FeedPost
) {
    val viewModel: CommentsViewModel = viewModel(factory = CommentsViewModelFactory(feedPost))
    val screenState = viewModel.screenState.observeAsState(CommentsScreenState.Initial)
    val currentState = screenState.value
    if (currentState is CommentsScreenState.Comments) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Comments for Feed Post Id ${currentState.feedPost.id}") },
                    navigationIcon = {
                        IconButton(onClick = { onBackListener() }) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = "Back Button"
                            )
                        }
                        BackHandler {
                            onBackListener()
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
                items(
                    items = currentState.comments,
                    key = { it.id }
                ) { comment ->
                    Comment(postComment = comment)
                }
            }
        }
    }
}


@Composable
fun Comment(postComment: PostComment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
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
