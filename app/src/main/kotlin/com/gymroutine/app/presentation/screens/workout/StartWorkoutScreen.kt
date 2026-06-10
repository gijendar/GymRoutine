package com.gymroutine.app.presentation.screens.workout

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gymroutine.app.domain.model.*
import com.gymroutine.app.presentation.components.*
import com.gymroutine.app.presentation.theme.*
import com.gymroutine.app.presentation.viewmodel.StartWorkoutViewModel

@Composable
fun StartWorkoutScreen(
    onNavigateBack: () -> Unit,
    onNavigateToActiveWorkout: (Long, Long) -> Unit,
    viewModel: StartWorkoutViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GymBlack)
    ) {
        GymTopBar(title = "Start Workout", onNavigateBack = onNavigateBack)

        if (uiState.isLoading) {
            LoadingSpinner()
        } else if (uiState.routines.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                EmptyState(
                    icon = Icons.Default.FitnessCenter,
                    title = "No Routines Found",
                    subtitle = "Create a routine and add categories before starting a workout"
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    SectionHeader("Select a Routine")
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(uiState.routines) { routine ->
                    RoutineSelectCard(
                        routine = routine,
                        isSelected = uiState.selectedRoutineId == routine.id,
                        onClick = { viewModel.selectRoutine(routine.id) }
                    )
                }

                if (uiState.selectedRoutineId != null) {
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        SectionHeader("Select Category")
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    if (uiState.days.isEmpty()) {
                        item {
                            EmptyState(
                                icon = Icons.Default.Category,
                                title = "No Categories",
                                subtitle = "Add exercise categories to this routine first"
                            )
                        }
                    } else {
                        itemsIndexed(uiState.days) { index, category ->
                            CategorySelectCard(
                                category = category,
                                index = index,
                                isLoading = uiState.isStarting,
                                onClick = {
                                    viewModel.startWorkout(category.id) { sessionId, dayId ->
                                        onNavigateToActiveWorkout(sessionId, dayId)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RoutineSelectCard(routine: Routine, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) GymAccentSubtle else GymCard)
            .border(1.dp, if (isSelected) GymAccent else GymBorder, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) GymAccent else GymBorder)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    routine.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = if (isSelected) GymAccent else GymTextPrimary
                )
                Text(
                    "${routine.dayCount} ${if (routine.dayCount == 1) "category" else "categories"}",
                    style = MaterialTheme.typography.bodySmall,
                    color = GymTextTertiary
                )
            }
            Icon(Icons.Default.ChevronRight, null, tint = if (isSelected) GymAccent else GymTextTertiary)
        }
    }
}

@Composable
fun CategorySelectCard(
    category: WorkoutDay,
    index: Int,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    val colors = listOf(GymAccent, GymBlue, GymOrange, GymPurple, GymGreen)
    val color = colors[index % colors.size]

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(GymCard)
            .border(1.dp, GymBorder, RoundedCornerShape(16.dp))
            .clickable(enabled = !isLoading, onClick = onClick)
            .padding(18.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Category, null, tint = color, modifier = Modifier.size(22.dp))
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    category.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = GymTextPrimary
                )
                Text(
                    "${category.exerciseCount} ${if (category.exerciseCount == 1) "exercise" else "exercises"}",
                    style = MaterialTheme.typography.bodySmall,
                    color = GymTextTertiary
                )
            }
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = GymAccent,
                    strokeWidth = 2.dp
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        "Start",
                        style = MaterialTheme.typography.labelSmall,
                        color = GymAccent,
                        fontWeight = FontWeight.SemiBold
                    )
                    Icon(Icons.Default.PlayArrow, null, tint = GymAccent, modifier = Modifier.size(18.dp))
                }
            }
        }
    }
}
