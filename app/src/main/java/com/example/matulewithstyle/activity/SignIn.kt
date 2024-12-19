package com.example.matulewithstyle.activity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import com.example.matulewithstyle.R
import com.example.matulewithstyle.validation.Validator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignIn(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) } // Состояние для видимости пароля

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Привет!",
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Заполните свои данные или\nпродолжите через социальные медиа",
                color = Color.Gray,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(30.dp))

            // Email Field
            Text(
                text = "Email",
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Start)
            )
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("xyz@gmail.com", color = Color(106, 106, 106, 255)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(15.dp)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(247, 247, 249, 255),
                    focusedBorderColor = Color(247, 247, 249, 255),
                    unfocusedBorderColor = Color(247, 247, 249, 255)
                )
            )
            Spacer(modifier = Modifier.height(30.dp))

            // Password Field
            Text(
                text = "Пароль",
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Start)
            )
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Введите пароль", color = Color(106, 106, 106, 255)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(12.dp)),
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                        Icon(
                            painter = if (passwordVisible.value) {
                                painterResource(id = R.drawable.visibility)
                            } else {
                                painterResource(id = R.drawable.not_visibility)
                            },
                            contentDescription = if (passwordVisible.value) "Скрыть пароль" else "Показать пароль",
                            modifier = Modifier.size(20.dp) // Устанавливаем размер иконки
                        )
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(247, 247, 249, 255),
                    focusedBorderColor = Color(247, 247, 249, 255),
                    unfocusedBorderColor = Color(247, 247, 249, 255)
                )
            )

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Восстановить",
                color = Color.Gray,
                fontSize = 15.sp,
                modifier = Modifier.align(Alignment.End)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                    errorMessage.value = when {
                        !Validator.isValidEmail(email.value) && !Validator.isValidPassword(password.value) -> "Заполните все поля корректно!"
                        email.value == "" && password.value == "" -> "Заполните все поля!"
                        !Validator.isValidEmail(email.value) -> "Некорректный email!"
                        !Validator.isValidPassword(password.value) -> "Некорректный пароль!"
                        else -> {
                            navController.navigate("home") // Переход на маршрут "home"
                            "" // Очистка сообщения об ошибке
                        }

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(72, 178, 231, 255)
                )
            ) {
                Text(
                    text = "Войти",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            if (errorMessage.value.isNotEmpty()) {
                Text(
                    text = errorMessage.value,
                    color = Color.Red,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.padding(bottom = 40.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Вы впервые? ",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
                Text(
                    text = "Создать пользователя",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
