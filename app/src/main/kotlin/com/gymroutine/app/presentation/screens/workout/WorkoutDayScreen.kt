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
import com.gymroutine.app.domain.model.Exercise
import com.gymroutine.app.presentation.components.*
import com.gymroutine.app.presentation.theme.*
import com.gymroutine.app.presentation.viewmodel.WorkoutDayViewModel

@Composable
fun WorkoutDayScreen(
    onNavigateBack: () -> Unit,
    onNavigateToExerciseHistory: (Long) -> Unit,
    viewModel: WorkoutDayViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.showAddExerciseDialog) {
        ExerciseDialog(
            title = "Add Exercise",
            value = uiState.newExerciseName,
            onValueChange = viewModel::updateExerciseName,
            onConfirm = viewModel::addExercise,
            onDismiss = viewModel::hideAddDialog
        )
    }

    if (uiState.showEditExerciseDialog) {
        ExerciseDialog(
            title = "Edit Exercise",
            value = uiState.newExerciseName,
            onValueChange = viewModel::updateExerciseName,
            onConfirm = viewModel::updateExercise,
            onDismiss = viewModel::hideEditDialog
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GymBlack)
    ) {
        GymTopBar(
            title = uiState.category?.name ?: "Category",
            onNavigateBack = onNavigateBack,
            actions = {
                IconButton(
                    onClick = viewModel::showAddDialog,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(GymAccentSubtle)
                ) {
                    Icon(Icons.Default.Add, "Add Exercise", tint = GymAccent, modifier = Modifier.size(20.dp))
                }
            }
        )

        if (uiState.isLoading) {
            LoadingSpinner()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    Row(modifier = Modifier.padding(bottom = 8.dp)) {
                        StatChip("Exercises", "${uiState.exercises.size}", GymAccent, Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    SectionHeader("Exercises")
                }

                if (uiState.exercises.isEmpty()) {
                    item {
                        EmptyState(
                            icon = Icons.Default.FitnessCenter,
                            title = "No Exercises Yet",
                            subtitle = "Add exercises like Bench Press, Squats, Deadlift"
                        )
                    }
                } else {
                    itemsIndexed(uiState.exercises, key = { _, ex -> ex.id }) { index, exercise ->
                        ExerciseCard(
                            exercise = exercise,
                            index = index,
                            onViewHistory = { onNavigateToExerciseHistory(exercise.id) },
                            onEdit = { viewModel.showEditDialog(exercise) },
                            onDelete = { viewModel.deleteExercise(exercise) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ExerciseCard(
    exercise: Exercise,
    index: Int,
    onViewHistory: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(GymCard)
            .border(1.dp, GymBorder, RoundedCornerShape(14.dp))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = "${index + 1}",
                style = MaterialTheme.typography.labelMedium,
                color = GymTextTertiary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(24.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    exercise.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = GymTextPrimary
                )
            }

            IconButton(onClick = onViewHistory, modifier = Modifier.size(32.dp)) {
                Icon(Icons.Default.History, "History", tint = GymBlue, modifier = Modifier.size(18.dp))
            }

            Box {
                IconButton(onClick = { showMenu = true }, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Default.MoreVert, null, tint = GymTextTertiary, modifier = Modifier.size(18.dp))
                }
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false },
                    modifier = Modifier.background(GymCardElevated)
                ) {
                    DropdownMenuItem(
                        text = { Text("Edit", color = GymTextPrimary) },
                        onClick = { showMenu = false; onEdit() },
                        leadingIcon = { Icon(Icons.Default.Edit, null, tint = GymTextSecondary) }
                    )
                    DropdownMenuItem(
                        text = { Text("Delete", color = GymRed) },
                        onClick = { showMenu = false; onDelete() },
                        leadingIcon = { Icon(Icons.Default.Delete, null, tint = GymRed) }
                    )
                }
            }
        }
    }
}

@Composable
fun ExerciseDialog(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = GymCardElevated,
        title = { Text(title, color = GymTextPrimary, fontWeight = FontWeight.Bold) },
        text = {
            GymTextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = "e.g. Bench Press, Squat, Deadlift"
            )
        },
        confirmButton = { AccentButton("Save", onConfirm, enabled = value.isNotBlank()) },
        dismissButton = { OutlineButton("Cancel", onDismiss) }
    )
}
