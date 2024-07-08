package com.example.vibestore.ui.screen.registration.signup

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
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
import com.example.vibestore.R
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.ui.common.UiState
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current
        )
    )
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()
    var passwordVisibility by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }
    var email by rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val icon = if (passwordVisibility)
        painterResource(R.drawable.ic_visibility)
    else
        painterResource(R.drawable.ic_visibility_off)

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true
        )
    }

    val uiState by viewModel.uiState.observeAsState(initial = UiState.Loading)

    LaunchedEffect(uiState) {
        when (uiState) {
            is UiState.Loading -> {
                Toast.makeText(
                    context,
                    "Loading",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is UiState.Success -> {
                Toast.makeText(
                    context,
                    "Sign Success",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is UiState.Error -> {
                Toast.makeText(
                    context,
                    (uiState as UiState.Error).errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    SignUpContent(
        email = email,
        username = username,
        password = password,
        checked = checked,
        onEmailChange = { email = it },
        onUsernameChange = { username = it },
        onPasswordChange = { password = it },
        icon = icon,
        passwordVisibility = passwordVisibility,
        onPasswordVisibilityChange = { passwordVisibility = !passwordVisibility },
        onCheckedChange = { checked = it },
        onSignUpClick = {
            scope.launch {
                viewModel.register(
                    username.trim(),
                    email.trim(),
                    password.trim()
                )
            }
        }
    )

}

@Composable
fun SignUpContent(
    email: String,
    username: String,
    password: String,
    checked: Boolean,
    onEmailChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    icon: Painter,
    passwordVisibility: Boolean,
    onPasswordVisibilityChange: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    onSignUpClick: () -> Unit
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
        Text(
            text = "Vibe Store",
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp
        )
        Text(
            text = "Register or sign up and we'll get started",
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp
        )
        Column(
            modifier = Modifier
                .padding(
                    top = 32.dp
                )
        ) {
            CustomOutlinedTextField(
                value = email,
                hint = "Enter your email address",
                onValueChange = onEmailChange,
                trailingIcon = {},
                isError = false,
                errorMessage = ""
            )
            CustomOutlinedTextField(
                value = username,
                hint = "Enter your username",
                onValueChange = onUsernameChange,
                trailingIcon = {},
                isError = false,
                errorMessage = ""
            )
            CustomOutlinedTextField(
                value = password,
                hint = "Enter your password",
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
                isError = false,
                errorMessage = "",
                visualTransformation = if (passwordVisibility) VisualTransformation.None
                else PasswordVisualTransformation()
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.End)
        ) {
            RoundedCornerCheckbox(
                label = "Remember me",
                isChecked = checked,
                onValueChange = onCheckedChange,
            )
        }
        Button(
            modifier = Modifier
                .padding(top = 32.dp)
                .height(55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(40.dp),
            onClick = onSignUpClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color("#29bf12".toColorInt())
            )
        ) {
            Text(
                fontFamily = poppinsFontFamily,
                text = "Sign Up",
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 32.dp)
        ) {
            Text(
                text = "Already have an account?",
                fontFamily = poppinsFontFamily,
                fontSize = 14.sp,
                color = Color.Black
            )
            Text(
                text = " Login",
                fontFamily = poppinsFontFamily,
                fontSize = 14.sp,
                color = Color("#29bf12".toColorInt())
            )
        }
    }
}

@Composable
fun RoundedCornerCheckbox(
    label: String,
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    size: Float = 20f,
    checkedColor: Color = Color("#29bf12".toColorInt()),
    uncheckedColor: Color = Color.White,
    onValueChange: (Boolean) -> Unit
) {
    val checkboxColor: Color by animateColorAsState(if (isChecked) checkedColor else uncheckedColor,
        label = ""
    )
    val density = LocalDensity.current
    val duration = 200

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .heightIn(48.dp)
            .toggleable(
                value = isChecked,
                role = Role.Checkbox,
                onValueChange = onValueChange
            )
    ) {
        Box(
            modifier = Modifier
                .size(size.dp)
                .background(color = checkboxColor, shape = RoundedCornerShape(4.dp))
                .border(width = 1.5.dp, color = checkedColor, shape = RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = isChecked,
                enter = slideInHorizontally(animationSpec = tween(duration)) {
                    with(density) { (size * -0.5).dp.roundToPx() }
                } + expandHorizontally(
                    expandFrom = Alignment.Start,
                    animationSpec = tween(duration)
                ),
                exit = fadeOut()
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = uncheckedColor
                )
            }
        }
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = label,
            fontFamily = poppinsFontFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            color = Color("#29bf12".toColorInt())
        )
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

@Preview(
    showBackground = true,
)
@Composable
private fun SignUpContentPreview() {
    VibeStoreTheme {
        SignUpContent(
            email = "",
            username = "",
            password = "",
            checked = true,
            onEmailChange = {},
            onUsernameChange = {},
            onPasswordChange = {},
            icon = painterResource(R.drawable.ic_visibility_off),
            passwordVisibility = false,
            onPasswordVisibilityChange = {},
            onSignUpClick = {},
            onCheckedChange = {}
        )
    }
}