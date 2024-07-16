package com.example.vibestore.ui.screen.registration.login

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.vibestore.R
import com.example.vibestore.helper.DialogHelper
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.model.LoginResponse
import com.example.vibestore.ui.common.UiState
import com.example.vibestore.ui.navigation.model.Screen
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current
        )
    ),
    navController: NavHostController
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var dialog by remember { mutableStateOf<SweetAlertDialog?>(null) }
    var passwordVisibility by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val icon = if (passwordVisibility)
        painterResource(R.drawable.icon_visibility)
    else
        painterResource(R.drawable.icon_visibility_off)

    val uiState by viewModel.uiState.observeAsState(initial = UiState.Loading)
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true
    }

    LaunchedEffect(uiState) {
        when (uiState) {
            is UiState.Loading -> {
                dialog?.dismissWithAnimation()
                dialog = DialogHelper.showDialogLoading(
                    context = context,
                    textContent = "Please wait"
                )
            }
            is UiState.Success -> {
                dialog?.dismissWithAnimation()
                dialog = DialogHelper.showDialogSuccess(
                    context = context,
                    title = "Success",
                    textContent = "Login Success",
                    onConfirm = {
                        navController.navigate(Screen.MainNav.route)
                        viewModel.saveLoginData(
                            username = username,
                            token = (uiState as UiState.Success<LoginResponse>).data.token
                        )
                    }
                )
            }
            is UiState.Error -> {
                dialog?.dismissWithAnimation()
                dialog = DialogHelper.showDialogError(
                    context = context,
                    title = "Failed",
                    textContent = (uiState as UiState.Error).errorMessage
                )
            }
            null -> {}
        }
    }

    LoginContent(
        isVisible = isVisible,
        username = username,
        password = password,
        onUsernameChange = { username = it },
        onPasswordChange = {
            password = it
            passwordError = password.contains(" ")
        },
        icon = icon,
        passwordVisibility = passwordVisibility,
        onPasswordVisibilityChange = {
            passwordVisibility = !passwordVisibility
        },
        onSignUpClick = {
            navController.navigate(Screen.SignUp.route)
            viewModel.resetUiState()
        },
        onLoginClick = {
            if (
                username.isEmpty() ||
                password.isEmpty()
            ) {
                Toast.makeText(
                    context,
                    "Please fill all fields",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                scope.launch {
                    viewModel.login(
                        username.trim(),
                        password.trim()
                    )
                }
            }
        },
        passwordError = passwordError,
        onGuestClick = {
            navController.navigate(Screen.Home.route)
        }
    )
}

@Composable
fun LoginContent(
    isVisible: Boolean,
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    icon: Painter,
    passwordVisibility: Boolean,
    onPasswordVisibilityChange: () -> Unit,
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit,
    onGuestClick: () -> Unit,
    passwordError: Boolean,
) {
    Column(
        modifier = Modifier
            .padding(
                top = 100.dp,
                start = 32.dp,
                end = 32.dp
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically(
                initialOffsetY = { -40 },
                animationSpec = tween(2000)
            ) + fadeIn(animationSpec = tween(2000)),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.welcome_back_1),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 35.sp
                )
                Text(
                    text = stringResource(R.string.welcome_back_2),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 20.sp
                )
            }
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically(
                initialOffsetY = { -40 },
                animationSpec = tween(2000)
            ) + fadeIn(animationSpec = tween(2000)),
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        top = 32.dp
                    )
            ) {
                CustomOutlinedTextField(
                    value = username,
                    hint = stringResource(R.string.enter_your_username),
                    onValueChange = onUsernameChange,
                    trailingIcon = {},
                    isError = false,
                    errorMessage = ""
                )
                CustomOutlinedTextField(
                    value = password,
                    hint = stringResource(R.string.enter_your_password),
                    onValueChange = onPasswordChange,
                    trailingIcon = {
                        Icon(
                            painter = icon,
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {
                                    onPasswordVisibilityChange()
                                }
                        )
                    },
                    isError = passwordError,
                    errorMessage = stringResource(R.string.error_password),
                    visualTransformation = if (passwordVisibility) VisualTransformation.None
                    else PasswordVisualTransformation()
                )
            }
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically(
                initialOffsetY = { -40 },
                animationSpec = tween(2000)
            ) + fadeIn(animationSpec = tween(2000)),
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.End)
        ) {
            Text(
                text = stringResource(R.string.forgot_password),
                fontFamily = poppinsFontFamily,
                fontSize = 14.sp,
                color = Color("#29bf12".toColorInt()),
            )
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically(
                initialOffsetY = { -40 },
                animationSpec = tween(2000)
            ) + fadeIn(animationSpec = tween(2000)),
        ) {
            Button(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .height(55.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(40.dp),
                onClick = onLoginClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color("#29bf12".toColorInt())
                )
            ) {
                Text(
                    fontFamily = poppinsFontFamily,
                    text = stringResource(R.string.login),
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier
                )
            }
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically(
                initialOffsetY = { -40 },
                animationSpec = tween(2000)
            ) + fadeIn(animationSpec = tween(2000)),
        ) {
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(
                        top = 48.dp,
                    )
                    .fillMaxWidth()
            ){
                Divider(
                    thickness = 1.dp,
                    color = Color(0xFFE3E3E3),
                    modifier = Modifier
                        .weight(2f)
                        .background(color = Color.White)
                        .padding(end = 20.dp)
                )

                Text(text = stringResource(R.string.or_continue_with))

                Divider(
                    thickness = 1.dp,
                    color = Color(0xFFE3E3E3),
                    modifier = Modifier
                        .weight(2f)
                        .background(color = Color.White)
                        .padding(start = 20.dp)
                )
            }
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically(
                initialOffsetY = { -40 },
                animationSpec = tween(2000)
            ) + fadeIn(animationSpec = tween(2000)),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 32.dp,
                        vertical = 32.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    modifier = Modifier
                        .height(60.dp)
                        .border(
                            color = MaterialTheme.colorScheme.outline,
                            width = 1.dp,
                            shape = RoundedCornerShape(40.dp)
                        ),
                    shape = RoundedCornerShape(40.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.padding(),
                            painter = painterResource(R.drawable.icon_google),
                            contentDescription = "",
                            tint = Color.Unspecified
                        )
                    }
                }
                Button(
                    modifier = Modifier
                        .height(60.dp)
                        .border(
                            color = MaterialTheme.colorScheme.outline,
                            width = 1.dp,
                            shape = RoundedCornerShape(40.dp)
                        ),
                    shape = RoundedCornerShape(40.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding()
                                .scale(1.3f),
                            painter = painterResource(R.drawable.icon_facebook),
                            contentDescription = "",
                            tint = Color.Unspecified
                        )
                    }
                }
                Button(
                    modifier = Modifier
                        .height(60.dp)
                        .border(
                            color = MaterialTheme.colorScheme.outline,
                            width = 1.dp,
                            shape = RoundedCornerShape(40.dp)
                        ),
                    shape = RoundedCornerShape(40.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding()
                                .scale(1.3f)
                                .clickable {
                                    onGuestClick()
                                },
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "",
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically(
                initialOffsetY = { -40 },
                animationSpec = tween(2000)
            ) + fadeIn(animationSpec = tween(2000)),
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 70.dp)
            ) {
                Text(
                    text = stringResource(R.string.dont_have_account),
                    fontFamily = poppinsFontFamily,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = " " + stringResource(R.string.sign_up),
                    fontFamily = poppinsFontFamily,
                    fontSize = 14.sp,
                    color = Color("#29bf12".toColorInt()),
                    modifier = Modifier
                        .clickable {
                            onSignUpClick()
                        }
                )
            }
        }
    }
}

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable () -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean,
    errorMessage: String,
    hint: String = ""
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        shape = RoundedCornerShape(32.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color(0xFFCAC8C8),
            unfocusedIndicatorColor = Color(0xFFCAC8C8),
        ),
        value = value,
        onValueChange = onValueChange,
        placeholder =  {
            Text(
                text = hint,
                color = MaterialTheme.colorScheme.outline,
                fontFamily = poppinsFontFamily,
                fontSize = 15.sp
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        trailingIcon = {
            trailingIcon()
        },
        visualTransformation = visualTransformation,
        textStyle = TextStyle(
            color = Color.Black,
            fontFamily = poppinsFontFamily,
            fontSize = 14.sp
        ),
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun LoginContentPreview() {
    VibeStoreTheme {
        LoginContent(
            username = "",
            password = "",
            onUsernameChange = {},
            onPasswordChange = {},
            icon = painterResource(R.drawable.icon_visibility_off),
            passwordVisibility = true,
            onPasswordVisibilityChange = {},
            onSignUpClick = {},
            onLoginClick = {},
            passwordError = true,
            onGuestClick = {},
            isVisible = false
        )
    }
}