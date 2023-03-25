package com.example.newsclient.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsclient.MainViewModel
import com.example.newsclient.domain.FeedPost


@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    paddingValues: PaddingValues
) {
    val screenState = viewModel.screenState.observeAsState(HomeScreenState.Initial)

    when (val state = screenState.value) {
        is HomeScreenState.Posts -> {
            FeedPosts(
                viewModel = viewModel,
                paddingValues = paddingValues,
                posts = state.posts
            )
        }
        is HomeScreenState.Comments -> {
            CommentsScreen(
                feedPost = state.feedPost,
                postComments = state.comments,
                onBackListener = { viewModel.closeComments() }
            )
            BackHandler {
                viewModel.closeComments()
            }
        }
        is HomeScreenState.Initial -> {  }
    }

}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
private fun FeedPosts(
    viewModel: MainViewModel,
    paddingValues: PaddingValues,
    posts: List<FeedPost>
) {
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
        items(items = posts, key = { it.id }) { post ->
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                viewModel.delete(post)
            }

            SwipeToDismiss(
                modifier = Modifier.animateItemPlacement(),
                state = dismissState,
                background = {

                },
                directions = setOf(DismissDirection.EndToStart)
            ) {
                ClientPost(
                    feedPost = post,
                    onViewsClickListener = {
                        viewModel.changePostStats(
                            post,
                            it
                        )
                    },
                    onSharesClickListener = {
                        viewModel.changePostStats(
                            post,
                            it
                        )
                    },
                    onCommentsClickListener = {
                        viewModel.showComments(feedPost = post)
                    },
                    onLikeClickListener = {
                        viewModel.changePostStats(
                            post,
                            it
                        )
                    }
                )
            }
        }
    }
}