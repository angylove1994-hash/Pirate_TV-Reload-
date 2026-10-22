package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        PirateTvApp()
      }
    }
  }
}

@Composable
fun PirateTvApp() {
    var showSplash by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(3000)
        showSplash = false
    }

    if (showSplash) {
        SplashScreen()
    } else {
        MainScreen()
    }
}

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.banner_trabajo_1782381281436),
            contentDescription = "Pirate TV Splash Logo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        bottomBar = { BottomNavigationBar() },
        containerColor = Color(0xFF111318)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFF111318))
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            HeaderSection()
            Spacer(modifier = Modifier.height(16.dp))
            HeroSection()
            Spacer(modifier = Modifier.height(16.dp))
            BentoGridSection(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFFFB400), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("☠️", fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Pirate TV",
                color = Color(0xFFE2E2E6),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .border(1.dp, Color(0xFF44474E), CircleShape)
                .background(Color(0xFF3F4759), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "JD",
                color = Color(0xFFD1E4FF),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun HeroSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(192.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFF1A1C1E), Color(0xFF3F4759))
                )
            )
            .border(1.dp, Color(0xFF44474E), RoundedCornerShape(32.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xFF111318)),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xFFFFB400), RoundedCornerShape(4.dp))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "DESTACADO",
                    color = Color(0xFF2D2D00),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Black Sails: T4",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Temporada completa disponible",
                color = Color(0xFFC4C6D0),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun BentoGridSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
            BentoCard(
                icon = "📡",
                title = "Canales en Vivo",
                subtitle = "1,240 señales",
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            BentoCard(
                icon = "🎬",
                title = "Películas",
                subtitle = "Recién agregadas",
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
            BentoCard(
                icon = "📺",
                title = "Series",
                subtitle = "Populares hoy",
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            BentoCard(
                icon = "⭐",
                title = "Mis Favoritos",
                subtitle = "Tu lista curada",
                backgroundColor = Color(0xFFD1E4FF),
                iconBackgroundColor = Color(0xFF003258),
                titleColor = Color(0xFF003258),
                subtitleColor = Color(0xFF003258).copy(alpha = 0.7f),
                borderColor = Color.Transparent,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun BentoCard(
    icon: String,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFF1A1C1E),
    iconBackgroundColor: Color = Color(0xFF3F4759),
    titleColor: Color = Color(0xFFE2E2E6),
    subtitleColor: Color = Color(0xFFC4C6D0),
    borderColor: Color = Color(0xFF44474E)
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(24.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(24.dp))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(iconBackgroundColor, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(icon, fontSize = 20.sp)
            }
            Column {
                Text(
                    text = title,
                    color = titleColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = subtitle,
                    color = subtitleColor,
                    fontSize = 11.sp
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF111318))
            .border(1.dp, Color(0xFF44474E))
            .padding(vertical = 12.dp, horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(icon = "🏠", label = "Inicio", isSelected = true)
        BottomNavItem(icon = "🔍", label = "Buscar", isSelected = false)
        BottomNavItem(icon = "📁", label = "Archivo", isSelected = false)
        BottomNavItem(icon = "⚙️", label = "Ajustes", isSelected = false)
    }
}

@Composable
fun BottomNavItem(icon: String, label: String, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .background(Color(0xFF3F4759), CircleShape)
                    .padding(horizontal = 20.dp, vertical = 4.dp)
            ) {
                Text(icon, fontSize = 20.sp)
            }
        } else {
            Text(icon, fontSize = 20.sp, modifier = Modifier.padding(vertical = 4.dp))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = if (isSelected) Color(0xFFD1E4FF) else Color(0xFFC4C6D0),
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

