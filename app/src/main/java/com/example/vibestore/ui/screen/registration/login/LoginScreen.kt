package com.example.vibestore.ui.screen.registration.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.vibestore.R
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LoginScreen() {

    val systemUiController = rememberSystemUiController()
    var passwordVisibility by remember { mutableStateOf(false) }
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

    Column(
        modifier = Modifier
            .padding(
                top = 120.dp,
                start = 32.dp,
                end = 32.dp
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome Back!",
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp
        )
        Text(
            text = "Find Your Style Now",
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 20.sp
        )
        Column(
            modifier = Modifier
                .padding(
                    top = 32.dp
                )
        ) {
            CustomOutlinedTextField(
                value = username,
                hint = "Enter your username",
                onValueChange = {
                    username = it
                },
                trailingIcon = {},
                isError = false,
                errorMessage = ""
            )
            CustomOutlinedTextField(
                value = password,
                hint = "Enter your password",
                onValueChange = {
                    password = it
                },
                trailingIcon = {
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                passwordVisibility = !passwordVisibility
                            }
                    )
                },
                isError = false,
                errorMessage = "",
                visualTransformation = if (passwordVisibility) VisualTransformation.None
                    else PasswordVisualTransformation()
            )
        }
        Text(
            text = "Forgot Password ?",
            fontFamily = poppinsFontFamily,
            fontSize = 14.sp,
            color = Color("#29bf12".toColorInt()),
            modifier = Modifier
                .align(Alignment.End)
        )
        Button(
            modifier = Modifier
                .padding(top = 32.dp)
                .height(55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(40.dp),
            onClick = {
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color("#29bf12".toColorInt())
            )
        ) {
            androidx.compose.material3.Text(
                fontFamily = poppinsFontFamily,
                text = "Login",
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
                text = "Don't have an account?",
                fontFamily = poppinsFontFamily,
                fontSize = 14.sp,
                color = Color.Black
            )
            Text(
                text = " Sign Up",
                fontFamily = poppinsFontFamily,
                fontSize = 14.sp,
                color = Color("#29bf12".toColorInt())
            )
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
private fun LoginScreenPreview() {
    VibeStoreTheme {
        LoginScreen()
    }
}