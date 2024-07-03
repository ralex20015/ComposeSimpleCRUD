package com.earl.simplecrud.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.Note
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earl.simplecrud.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AdminScreen(
    users: List<User>,
    onLogout: () -> Unit = {},
    onDeleteUser: (User) -> Unit = {}
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    AdminNavigationDrawer(drawerState, onLogout) {
        AdminContent(drawerState, scope, users, onDeleteUser)
    }
}

@Composable
fun AdminNavigationDrawer(
    drawerState: DrawerState,
    onLogout: () -> Unit,
    drawerContent: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState, drawerContent = {
            AdminDrawerContent(onLogout)
        }, content = { drawerContent() }
    )
}

@Composable
fun AdminDrawerContent(
    onLogout: () -> Unit
) {
    ModalDrawerSheet {
        //TODO: Make this like a photo 
        Text("Drawer title", modifier = Modifier.padding(16.dp))
        HorizontalDivider()
        CustomNavigationDrawerItem(
            label = "Home",
            selected = false,
            onClick = {},
            iconImage = Icons.Filled.Home,
            contentDescription = "Home"
        )
        CustomNavigationDrawerItem(
            label = "My notes",
            selected = false,
            onClick = {},
            iconImage = Icons.AutoMirrored.Filled.Note,
            contentDescription = "Users"
        )
        CustomNavigationDrawerItem(
            label = "Settings",
            selected = false,
            onClick = {},
            iconImage = Icons.Filled.Settings,
            contentDescription = "Settings"
        )
        HorizontalDivider()
        CustomNavigationDrawerItem(
            label = "Log out",
            selected = false,
            onClick = onLogout,
            iconImage = Icons.AutoMirrored.Filled.Logout,
            contentDescription = "Log out"
        )
    }
}

@Composable
fun CustomNavigationDrawerItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    iconImage: ImageVector,
    contentDescription: String
) {
    NavigationDrawerItem(
        label = {
            Row {
                Icon(imageVector = iconImage, contentDescription = contentDescription)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = label)
            }
        },
        selected,
        onClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminContent(
    drawerState: DrawerState,
    scope: CoroutineScope = rememberCoroutineScope(),
    users: List<User>,
    onDeleteUser: (User) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Admin panel")
            }, navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
            })
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                UserList(users, onDeleteUser)
            }
        }
    )
}

@Composable
fun UserList(users: List<User>, onDeleteUser: (User) -> Unit) {
    LazyColumn {
        items(users) {
            UserListItem(
                user = it, modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                , onDeleteUser = onDeleteUser

            )
        }
    }
}

@Composable
fun UserListItem(user: User, modifier: Modifier, onDeleteUser: (User) -> Unit) {
    Card(modifier = modifier.padding(PaddingValues(bottom = 8.dp))) {
        Column(modifier = Modifier.padding(4.dp)) {
            Row {
                Column(modifier = Modifier.weight(3f)) {
                    Text(
                        text = "${user.firstName}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "${user.lastName}")
                }

                IconButton(onClick =  { onDeleteUser(user) } , modifier = Modifier.weight(1f)) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete user")
                }
            }
        }
    }
}

@Preview
@Composable
fun AdminScreenPreview() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    AdminNavigationDrawer(drawerState, {}) {
        AdminContent(
            users = listOf(
                User(0, "David", "Robles", "33333", "david@gmail.com", "12345")
            ),
            drawerState = drawerState
            , onDeleteUser = {}
        )
    }
}

