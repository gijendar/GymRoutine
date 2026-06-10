package com.gymroutine.app.presentation.screens.howto

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gymroutine.app.presentation.components.GymTopBar
import com.gymroutine.app.presentation.theme.*

data class HowToStep(
    val number: Int,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val color: Color,
    val detail: String? = null
)

@Composable
fun HowToUseScreen(onNavigateBack: () -> Unit) {

    val steps = listOf(
        HowToStep(
            number = 1,
            title = "Create a Routine",
            description = "Start by creating a training routine — a named collection of your exercise categories.",
            icon = Icons.Default.AddCircle,
            color = GymAccent,
            detail = "Example: \"My Workout Plan\" · \"5-Day Split\" · \"Strength Program\""
        ),
        HowToStep(
            number = 2,
            title = "Create Categories",
            description = "Inside your routine, add exercise categories that group related muscles or movements.",
            icon = Icons.Default.Category,
            color = GymBlue,
            detail = "Examples: Chest · Back · Legs · Shoulders · Arms · Upper Body · Power Day"
        ),
        HowToStep(
            number = 3,
            title = "Add Exercises",
            description = "Inside each category, add your exercises. You define every exercise yourself — no restrictions.",
            icon = Icons.Default.FitnessCenter,
            color = GymOrange,
            detail = "Chest:\n  ├  Bench Press\n  ├  Incline DB Press\n  └  Cable Fly"
        ),
        HowToStep(
            number = 4,
            title = "Go To The Gym",
            description = "When you're ready to train, tap Start Workout on the home screen.",
            icon = Icons.Default.DirectionsRun,
            color = GymPurple,
            detail = null
        ),
        HowToStep(
            number = 5,
            title = "Select a Category",
            description = "Choose which category you're training today — for example, Chest or Back.",
            icon = Icons.Default.TouchApp,
            color = GymGreen,
            detail = "Example: Chest"
        ),
        HowToStep(
            number = 6,
            title = "Open an Exercise",
            description = "Tap any exercise in the list to expand its logging panel.",
            icon = Icons.Default.ExpandMore,
            color = GymAccent,
            detail = "Example: Bench Press → tap to open"
        ),
        HowToStep(
            number = 7,
            title = "Log Your Sets",
            description = "Enter weight and reps for each set. Add or remove sets freely. Previous session data is shown as a reference.",
            icon = Icons.Default.Edit,
            color = GymBlue,
            detail = "Weight · Reps · Sets\n\nLast session shown above each exercise"
        ),
        HowToStep(
            number = 8,
            title = "Finish & Save",
            description = "Tap Finish Workout when done. Duration, total sets, volume, and exercises are all saved automatically.",
            icon = Icons.Default.CheckCircle,
            color = GymGreen,
            detail = null
        ),
        HowToStep(
            number = 9,
            title = "View History",
            description = "Tap the history icon on any exercise to see every previous session — date, sets, weight, reps, and volume.",
            icon = Icons.Default.History,
            color = GymOrange,
            detail = null
        ),
        HowToStep(
            number = 10,
            title = "Track Progress",
            description = "Visit the Progress screen from the home screen to monitor your total sessions, volume history, and workout frequency.",
            icon = Icons.Default.TrendingUp,
            color = GymPurple,
            detail = null
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GymBlack)
    ) {
        GymTopBar(title = "How To Use", onNavigateBack = onNavigateBack)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            // Hero header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(GymAccentSubtle, GymCard)
                        )
                    )
                    .border(1.dp, GymAccent.copy(alpha = 0.3f), RoundedCornerShape(20.dp))
                    .padding(20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(GymAccentSubtle),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.MenuBook, null, tint = GymAccent, modifier = Modifier.size(28.dp))
                    }
                    Column {
                        Text(
                            "Quick Start Guide",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = GymTextPrimary
                        )
                        Text(
                            "Follow these 10 steps to master GymRoutine",
                            style = MaterialTheme.typography.bodySmall,
                            color = GymTextSecondary
                        )
                    }
                }
            }

            // Steps
            steps.forEachIndexed { index, step ->
                HowToStepCard(step = step, isLast = index == steps.lastIndex)
            }
        }
    }
}

@Composable
fun HowToStepCard(step: HowToStep, isLast: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Timeline column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(44.dp)
        ) {
            // Circle with number
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(step.color.copy(alpha = 0.15f))
                    .border(1.5.dp, step.color.copy(alpha = 0.5f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(step.icon, null, tint = step.color, modifier = Modifier.size(22.dp))
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(16.dp)
                        .background(GymBorder)
                )
            }
        }

        // Content card
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = if (isLast) 0.dp else 12.dp)
        ) {
            // Step header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(step.color.copy(alpha = 0.15f))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        "STEP ${step.number}",
                        style = MaterialTheme.typography.labelSmall,
                        color = step.color,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        letterSpacing = 1.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                step.title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = GymTextPrimary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                step.description,
                style = MaterialTheme.typography.bodyMedium,
                color = GymTextSecondary,
                lineHeight = 22.sp
            )

            // Detail block
            if (step.detail != null) {
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(GymCard)
                        .border(1.dp, GymBorder, RoundedCornerShape(10.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        step.detail,
                        style = MaterialTheme.typography.bodySmall,
                        color = GymTextTertiary,
                        fontFamily = FontFamily.Monospace,
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
