package com.devrachit.jetscribe.presentation.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.devrachit.jetscribe.common.ConnectivityObserver
import com.devrachit.jetscribe.common.NetworkConnectivityObserver
import com.devrachit.jetscribe.domain.model.Blog
import com.devrachit.jetscribe.presentation.navigation.Screen
import com.devrachit.jetscribe.presentation.screens.homeScreen.components.BlogItem
import com.devrachit.jetscribe.ui.theme.GrayShade4
import com.devrachit.jetscribe.ui.theme.GrayShade2
import com.devrachit.jetscribe.ui.theme.pink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
) {
    val viewModel: HomeScreenViewModel = hiltViewModel()
    val blogs = viewModel.blogs.collectAsLazyPagingItems()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val connectivityObserver =
        NetworkConnectivityObserver(LocalContext.current)
    var showConnectedSnackbar by remember {
        mutableStateOf(false)
    }
    val onItemClick2:(Blog) -> Unit = { blog ->
        viewModel.sharedModel.setBlog(blog)
        navController.navigate(Screen.BlogScreen.route)
    }

    val connectivityStatus = connectivityObserver.observe()
        .collectAsStateWithLifecycle(ConnectivityObserver.Status.Unavailable)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Jetscribe",
                        color = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                    )
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = GrayShade2)
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
        ,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = null, tint = pink) },
                text = { Text("My Favorite") },
                onClick = { navController.navigate(Screen.FavScreen.route) },
                containerColor = Color.White,
                contentColor = Color.Black
            )
        }
        ,
        floatingActionButtonPosition = FabPosition.Center
    ) {
        HomeScreenContent(blogs, it , onItemClick2)
    }

    LaunchedEffect(connectivityStatus.value) {
        when (connectivityStatus.value) {
            ConnectivityObserver.Status.Available -> {
                if(showConnectedSnackbar){
                    snackbarHostState.showSnackbar(
                        message = "Internet Connected",
                        duration = SnackbarDuration.Short
                    )
                }
            }

            ConnectivityObserver.Status.Losing -> {
                snackbarHostState.showSnackbar(
                    message = "Internet Losing",
                    duration = SnackbarDuration.Short
                )
            }

            ConnectivityObserver.Status.Lost -> {
                showConnectedSnackbar = true
                snackbarHostState.showSnackbar(
                    message = "Internet Lost",
                    duration = SnackbarDuration.Indefinite
                )
            }

            ConnectivityObserver.Status.Unavailable -> {
                snackbarHostState.showSnackbar(
                    message = "Internet Unavailable",
                    duration = SnackbarDuration.Indefinite
                )
            }
        }
    }
}

@Composable
fun HomeScreenContent(
    blogs: LazyPagingItems<Blog>,
    paddingValues: PaddingValues,
    onItemClick: (Blog) -> Unit
//    onItemClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayShade4)
            .padding(paddingValues)
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(blogs.itemCount) {
            blogs[it]?.let { blog ->
                BlogItem(
                    blog
                ) {
                    onItemClick(blog)
                }
            }
        }
        when (val state = blogs.loadState.refresh) {
            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier.fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            is LoadState.Error -> {
                item {
                    Column(
                        modifier = Modifier.fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        state.error.localizedMessage?.let {
                            Text(
                                text = it,
                                color = Color.Black,
                                fontSize = 16.sp,
                            )
                        }
                    }
                }
            }

            else -> {}
        }

        when (val state = blogs.loadState.append) {
            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            is LoadState.Error -> {
                item {
                    Column(
                        modifier = Modifier.fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        state.error.localizedMessage?.let {
                            Text(
                                text = it,
                                color = Color.Black,
                                fontSize = 16.sp,
                            )
                        }
                    }
                }
            }

            else -> {}
        }
    }
}