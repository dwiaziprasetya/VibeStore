package com.example.vibestore.ui.screen.registration.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true
        )
    }

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
            com.example.vibestore.ui.screen.registration.login.CustomOutlinedTextField(
                value = "",
                hint = "Enter your email address",
                onValueChange = {  },
                trailingIcon = {},
                isError = false,
                errorMessage = ""
            )
            com.example.vibestore.ui.screen.registration.login.CustomOutlinedTextField(
                value = "",
                hint = "Enter your username",
                onValueChange = {  },
                trailingIcon = {},
                isError = false,
                errorMessage = ""
            )
            com.example.vibestore.ui.screen.registration.login.CustomOutlinedTextField(
                value = "",
                hint = "Enter your password",
                onValueChange = {  },
                trailingIcon = {},
                isError = false,
                errorMessage = ""
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
private fun SignUpScreenPreview() {
    VibeStoreTheme {
        SignUpScreen()
    }
}