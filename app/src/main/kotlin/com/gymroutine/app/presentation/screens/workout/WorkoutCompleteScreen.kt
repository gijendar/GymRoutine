package com.gymroutine.app.presentation.screens.workout

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gymroutine.app.presentation.components.*
import com.gymroutine.app.presentation.theme.*
import com.gymroutine.app.presentation.viewmodel.WorkoutCompleteViewModel
import kotlin.math.roundToInt

@Composable
fun WorkoutCompleteScreen(
    onNavigateHome: () -> Unit,
    viewModel: WorkoutCompleteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var animVisible by remember { mutableStateOf(false) }

    val badgeScale by animateFloatAsState(
        targetValue = if (animVisible) 1f else 0f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMediumLow),
        label = "badge"
    )

    LaunchedEffect(uiState.isLoading) {
        if (!uiState.isLoading) {
            kotlinx.coroutines.delay(100)
            animVisible = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(GymDarkSurface, GymBlack, GymBlack)))
            .statusBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // Trophy
        Box(
            modifier = Modifier
                .size(120.dp)
                .scale(badgeScale)
                .clip(CircleShape)
                .background(Brush.radialGradient(listOf(GymAccent, GymAccentDim))),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.EmojiEvents, null, tint = GymBlack, modifier = Modifier.size(64.dp))
        }

        Spacer(modifier = Modifier.height(28.dp))

        AnimatedVisibility(visible = animVisible, enter = fadeIn(tween(600)) + slideInVertically(tween(600)) { 40 }) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Workout Complete!", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Black, color = GymTextPrimary)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    uiState.dayName,
                    style = MaterialTheme.typography.bodyLarge,
                    color = GymAccent,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        AnimatedVisibility(visible = animVisible, enter = fadeIn(tween(800, 200))) {
            // Stats grid
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    CompletionStat(
                        icon = Icons.Default.Timer,
                        value = "${uiState.durationMinutes}",
                        unit = "min",
                        label = "Duration",
                        color = GymAccent,
                        modifier = Modifier.weight(1f)
                    )
                    CompletionStat(
                        icon = Icons.Default.FitnessCenter,
                        value = "${uiState.totalExercises}",
                        unit = "",
                        label = "Exercises",
                        color = GymBlue,
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    CompletionStat(
                        icon = Icons.Default.Repeat,
                        value = "${uiState.totalSets}",
                        unit = "",
                        label = "Total Sets",
                        color = GymOrange,
                        modifier = Modifier.weight(1f)
                    )
                    CompletionStat(
                        icon = Icons.Default.Scale,
                        value = "${uiState.totalVolume.roundToInt()}",
                        unit = "kg",
                        label = "Volume",
                        color = GymPurple,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        AnimatedVisibility(visible = animVisible, enter = fadeIn(tween(800, 400)) + slideInVertically(tween(600, 400)) { 60 }) {
            AccentButton(
                text = "Back to Home",
                onClick = onNavigateHome,
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = Icons.Default.Home
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun CompletionStat(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    unit: String,
    label: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(GymCard)
            .border(1.dp, GymBorder, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Icon(icon, null, tint = color, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Black, color = GymTextPrimary)
                if (unit.isNotEmpty()) {
                    Text(unit, style = MaterialTheme.typography.bodySmall, color = GymTextSecondary, modifier = Modifier.padding(bottom = 2.dp, start = 2.dp))
                }
            }
            Text(label, style = MaterialTheme.typography.labelSmall, color = GymTextTertiary)
        }
    }
}
