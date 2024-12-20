package com.example.matulewithstyle.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.matulewithstyle.R
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoarding(navController: NavController) {
    val pagerState = rememberPagerState()
    val pages = listOf(
        OnboardingPage("ДОБРО ПОЖАЛОВАТЬ", "", R.drawable.image1),
        OnboardingPage("Начнем путешествие", "Умная, великолепная и модная коллекция. Изучите сейчас!", R.drawable.image2),
        OnboardingPage("У Вас Есть Силы, Чтобы", "В вашей комнате много красивых и привлекательных растений.", R.drawable.image3)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(72, 178, 231),
                        Color(68, 169, 220),
                        Color(43, 107, 139)
                    )
                )
            )
    ) {
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                count = pages.size,
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { pageIndex ->
                val page = pages[pageIndex]
                OnboardingPageContent(page, pageIndex == 0)
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .padding(bottom = 150.dp),
                activeColor = Color.White,
                inactiveColor = Color(43, 107, 139),
                indicatorHeight = 8.dp,
                indicatorWidth = 30.dp,
                spacing = 8.dp
            )

            Button(
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage == pages.size - 1) {
                            navController.navigate("next_screen")
                        } else {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Gray
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = if (pagerState.currentPage == pages.size - 1) "Начать" else "Далее")
            }
            Spacer(modifier = Modifier.padding(bottom = 10.dp))
        }
    }
}

@Composable
fun OnboardingPageContent(page: OnboardingPage, isFirstPage: Boolean) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = if (isFirstPage) Arrangement.Top else Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (isFirstPage) {
                Text(
                    text = page.title,
                    color = Color.White,
                    fontSize = 40.sp,
                    lineHeight = 40.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(32.dp))
                Image(
                    painter = painterResource(id = page.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(600.dp)
                        .padding(bottom = 80.dp)
                )
            } else {
                Image(
                    painter = painterResource(id = page.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(400.dp)
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = page.title,
                    color = Color.White,
                    fontSize = 40.sp,
                    lineHeight = 40.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = page.text,
                    color = Color(216, 216, 216),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 30.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

data class OnboardingPage(val title: String, val text: String, val image: Int)
