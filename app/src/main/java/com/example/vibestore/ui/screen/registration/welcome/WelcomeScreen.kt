@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.vibestore.ui.screen.registration.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.vibestore.R
import com.example.vibestore.data.local.entity.Notification
import com.example.vibestore.helper.DialogHelper
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.ui.navigation.model.Screen
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(
    navController: NavHostController,
    viewModel: WelcomeViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current
        )
    )
) {
    val systemUiController = rememberSystemUiController()
    val context = LocalContext.current
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val isVisible = remember { mutableStateOf(false) }
    var dialog by remember { mutableStateOf<SweetAlertDialog?>(null) }
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    
    if (isSheetOpen) {
        BottomSheetRegister(
            modifier = Modifier.wrapContentHeight(),
            onDismiss = {
                isSheetOpen = false
            },
            modalBottomSheetState = modalBottomSheetState,
            onLoginClick = {
                scope.launch {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = true
                    )
                    navController.navigate(Screen.Login.route)
                    isSheetOpen = false
                }
            },
            onSignUpClick = {
                scope.launch {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = true
                    )
                    navController.navigate(Screen.SignUp.route)
                    isSheetOpen = false
                }
            },
            onGuestClick = {
                scope.launch {
                    dialog?.dismissWithAnimation()
                    dialog = DialogHelper.showDialogLoading(
                        context = context,
                        textContent = "Please wait"
                    )
                    delay(1000)
                    viewModel.addNotification(
                        notification = Notification(
                            notificationType = "Info",
                            message = "Welcome to Vibe Store🎇",
                            messageDetail = "We’re thrilled to have you on board! " +
                                    "Explore amazing deals and start shopping now."
                        )
                    )
                    dialog?.dismissWithAnimation()
                    dialog = DialogHelper.showDialogSuccess(
                        context = context,
                        title = "Success",
                        textContent = "Welcome to Vibe Store",
                        onConfirm = {
                            systemUiController.setSystemBarsColor(
                                color = Color.Transparent,
                                darkIcons = true
                            )
                            navController.popBackStack()
                            navController.navigate(Screen.MainNav.route)
                            viewModel.saveLoginData(
                                username = "Guest",
                                token = "Guest"
                            )
                        }
                    )
                    isSheetOpen = false
                }
            }
        )
    }

    LaunchedEffect(Unit) {
        isVisible.value = true
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.ahahah),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.4f),
                            Color.Black
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .navigationBarsPadding()
                .align(Alignment.BottomCenter)
        ) {
            AnimatedVisibility(
                visible = isVisible.value,
                enter = slideInVertically(
                    initialOffsetY = { -40 },
                    animationSpec = tween(1000)
                ) + fadeIn(animationSpec = tween(1000)),
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        lineHeight = 45.sp,
                        text = stringResource(R.string.welcome_1),
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 45.sp,
                        color = Color.White
                    )
                    Text(
                        text = stringResource(R.string.welcome_2),
                        color = Color("#bcbcbc".toColorInt()),
                        fontFamily = poppinsFontFamily,
                        fontSize = 14.sp
                    )
                }
            }
            AnimatedVisibility(
                visible = isVisible.value,
                enter = slideInVertically(
                    initialOffsetY = { -40 },
                    animationSpec = tween(1000)
                ) + fadeIn(animationSpec = tween(1000)),
            ) {
                Button(
                    modifier = Modifier
                        .padding(vertical = 32.dp)
                        .height(55.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(40.dp),
                    onClick = { isSheetOpen = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        fontFamily = poppinsFontFamily,
                        text = stringResource(R.string.get_started),
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetRegister(
    onDismiss: () -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onGuestClick: () -> Unit,
    modifier: Modifier = Modifier,
    modalBottomSheetState: SheetState
) {
    ModalBottomSheet(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier,
        onDismissRequest = onDismiss,
        sheetState = modalBottomSheetState,
        windowInsets = WindowInsets(0, 0, 0, 0)
    ) {
        BottomSheetWelcomeContent(
            modifier = Modifier
                .navigationBarsPadding()
                .wrapContentHeight(),
            onLoginClick = onLoginClick,
            onSignUpClick = onSignUpClick,
            onGuestClick = onGuestClick
        )
    }
}

@Composable
fun BottomSheetWelcomeContent(
    modifier: Modifier = Modifier,
    onGuestClick: () -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 32.dp
                )
                .background(MaterialTheme.colorScheme.background)
                .align(Alignment.BottomCenter)
        ) {
            Button(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(55.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(40.dp),
                onClick = onSignUpClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    fontFamily = poppinsFontFamily,
                    text = stringResource(R.string.sign_up),
                    fontSize = 16.sp,
                    modifier = Modifier,
                    color = Color.White
                )
            }
            Button(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp
                    )
                    .height(55.dp)
                    .fillMaxWidth()
                    .border(
                        color = MaterialTheme.colorScheme.outline,
                        width = 1.dp,
                        shape = RoundedCornerShape(40.dp)
                    ),
                shape = RoundedCornerShape(40.dp),
                onClick = onLoginClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Text(
                    fontFamily = poppinsFontFamily,
                    text = stringResource(R.string.login) + " to Vibe Store",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                )
            }
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        bottom = 16.dp
                    )
                    .fillMaxWidth()
            ){
                Divider(
                    thickness = 1.dp,
                    color = Color(0xFFE3E3E3),
                    modifier = Modifier
                        .weight(2f)
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(end = 20.dp)
                )

                Text(text = "or")

                Divider(
                    thickness = 1.dp,
                    color = Color(0xFFE3E3E3),
                    modifier = Modifier
                        .weight(2f)
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(start = 20.dp)
                )
            }
            Button(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp
                    )
                    .height(60.dp)
                    .fillMaxWidth()
                    .border(
                        color = MaterialTheme.colorScheme.outline,
                        width = 1.dp,
                        shape = RoundedCornerShape(40.dp)
                    ),
                shape = RoundedCornerShape(40.dp),
                onClick = onGuestClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_google),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                    Text(
                        fontFamily = poppinsFontFamily,
                        text = stringResource(R.string.continue_with_google),
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.width(0.1.dp))
                }
            }
            Button(
                modifier = Modifier
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                    .height(60.dp)
                    .fillMaxWidth()
                    .border(
                        color = MaterialTheme.colorScheme.outline,
                        width = 1.dp,
                        shape = RoundedCornerShape(40.dp)
                    ),
                shape = RoundedCornerShape(40.dp),
                onClick = onGuestClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_facebook),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                    Text(
                        fontFamily = poppinsFontFamily,
                        text = stringResource(R.string.continue_with_facebook),
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.width(0.1.dp))
                }
            }
            Button(
                modifier = Modifier
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                    .height(60.dp)
                    .fillMaxWidth()
                    .border(
                        color = MaterialTheme.colorScheme.outline,
                        width = 1.dp,
                        shape = RoundedCornerShape(40.dp)
                    ),
                shape = RoundedCornerShape(40.dp),
                onClick = onGuestClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                    Text(
                        fontFamily = poppinsFontFamily,
                        text = stringResource(R.string.continue_as_guest),
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.width(0.1.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomSheetRegisterPreview() {
    VibeStoreTheme {
        BottomSheetWelcomeContent(
            onLoginClick = {},
            onSignUpClick = {},
            onGuestClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    VibeStoreTheme {
        WelcomeScreen(rememberNavController())
    }
}