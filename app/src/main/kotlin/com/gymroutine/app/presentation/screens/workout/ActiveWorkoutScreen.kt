package com.gymroutine.app.presentation.screens.workout

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gymroutine.app.domain.model.SetEntry
import com.gymroutine.app.presentation.components.*
import com.gymroutine.app.presentation.theme.*
import com.gymroutine.app.presentation.viewmodel.ActiveWorkoutViewModel
import com.gymroutine.app.presentation.viewmodel.ExerciseWithSets

@Composable
fun ActiveWorkoutScreen(
    onNavigateToComplete: (Long) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: ActiveWorkoutViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val elapsed = uiState.elapsedSeconds
    val timeString = remember(elapsed) {
        val h = elapsed / 3600
        val m = (elapsed % 3600) / 60
        val s = elapsed % 60
        if (h > 0) "%d:%02d:%02d".format(h, m, s) else "%02d:%02d".format(m, s)
    }

    if (uiState.showFinishDialog) {
        AlertDialog(
            onDismissRequest = viewModel::hideFinishDialog,
            containerColor = GymCardElevated,
            title = { Text("Finish Workout?", color = GymTextPrimary, fontWeight = FontWeight.Bold) },
            text = { Text("Your workout will be saved. Ready to finish?", color = GymTextSecondary) },
            confirmButton = {
                AccentButton("Finish", onClick = {
                    viewModel.finishWorkout { sessionId -> onNavigateToComplete(sessionId) }
                })
            },
            dismissButton = { OutlineButton("Continue", onClick = viewModel::hideFinishDialog) }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GymBlack)
    ) {
        // Workout Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.verticalGradient(listOf(GymDarkSurface, GymBlack)))
                .statusBarsPadding()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.size(36.dp).clip(CircleShape).background(GymCard)
                    ) {
                        Icon(Icons.Default.Close, "Stop", tint = GymTextPrimary, modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(uiState.dayName, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = GymTextPrimary)
                        Text("Active Workout", style = MaterialTheme.typography.bodySmall, color = GymTextTertiary)
                    }
                    // Timer chip
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(GymAccentSubtle)
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Icon(Icons.Default.Timer, null, tint = GymAccent, modifier = Modifier.size(14.dp))
                            Text(timeString, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = GymAccent)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                AccentButton(
                    text = "Finish Workout",
                    onClick = viewModel::showFinishDialog,
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = Icons.Default.CheckCircle
                )
            }
        }

        if (uiState.isLoading) {
            LoadingSpinner()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 40.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    if (uiState.exercisesWithSets.isEmpty()) {
                        EmptyState(
                            icon = Icons.Default.FitnessCenter,
                            title = "No Exercises",
                            subtitle = "This category has no exercises. Add exercises first."
                        )
                    }
                }

                items(uiState.exercisesWithSets, key = { it.exercise.id }) { exerciseWithSets ->
                    ExerciseLoggingCard(
                        exerciseWithSets = exerciseWithSets,
                        onToggle = { viewModel.toggleExercise(exerciseWithSets.exercise.id) },
                        onUpdateSet = { setIdx, weight, reps ->
                            viewModel.updateSet(exerciseWithSets.exercise.id, setIdx, weight, reps)
                        },
                        onAddSet = { viewModel.addSet(exerciseWithSets.exercise.id) },
                        onRemoveSet = { setIdx -> viewModel.removeSet(exerciseWithSets.exercise.id, setIdx) }
                    )
                }
            }
        }
    }
}

@Composable
fun ExerciseLoggingCard(
    exerciseWithSets: ExerciseWithSets,
    onToggle: () -> Unit,
    onUpdateSet: (Int, String, String) -> Unit,
    onAddSet: () -> Unit,
    onRemoveSet: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(GymCard)
            .border(1.dp, GymBorder, RoundedCornerShape(20.dp))
    ) {
        // Exercise header row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onToggle)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    exerciseWithSets.exercise.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = GymTextPrimary
                )
                // Last session hint
                val lw = exerciseWithSets.lastSessionWeight
                val lr = exerciseWithSets.lastSessionReps
                if (lw != null && lr != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(Icons.Default.History, null, tint = GymTextTertiary, modifier = Modifier.size(12.dp))
                        Text("Last: ${lw}kg × $lr", style = MaterialTheme.typography.labelSmall, color = GymTextTertiary)
                    }
                }
            }
            // Set count badge
            if (exerciseWithSets.sets.any { it.weight.isNotEmpty() || it.reps.isNotEmpty() }) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(GymAccentSubtle)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text("${exerciseWithSets.sets.size} sets", style = MaterialTheme.typography.labelSmall, color = GymAccent, fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
            Icon(
                if (exerciseWithSets.isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                null,
                tint = GymAccent,
                modifier = Modifier.size(24.dp)
            )
        }

        AnimatedVisibility(
            visible = exerciseWithSets.isExpanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Column headers
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("SET", style = MaterialTheme.typography.labelSmall, color = GymTextTertiary,
                        modifier = Modifier.width(36.dp), textAlign = TextAlign.Center, letterSpacing = 1.sp)
                    Text("KG", style = MaterialTheme.typography.labelSmall, color = GymTextTertiary,
                        modifier = Modifier.weight(1f), textAlign = TextAlign.Center, letterSpacing = 1.sp)
                    Text("REPS", style = MaterialTheme.typography.labelSmall, color = GymTextTertiary,
                        modifier = Modifier.weight(1f), textAlign = TextAlign.Center, letterSpacing = 1.sp)
                    Spacer(modifier = Modifier.width(32.dp))
                }

                exerciseWithSets.sets.forEachIndexed { index, set ->
                    SetRow(
                        set = set,
                        onWeightChange = { onUpdateSet(index, it, set.reps) },
                        onRepsChange = { onUpdateSet(index, set.weight, it) },
                        onRemove = if (exerciseWithSets.sets.size > 1) ({ onRemoveSet(index) }) else null
                    )
                }

                // Add set button
                TextButton(
                    onClick = onAddSet,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Add, null, tint = GymAccent, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Add Set", color = GymAccent, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
fun SetRow(
    set: SetEntry,
    onWeightChange: (String) -> Unit,
    onRepsChange: (String) -> Unit,
    onRemove: (() -> Unit)?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Set number badge
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(GymDarkSurface)
                .border(1.dp, GymBorder, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "${set.setNumber}",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = GymTextSecondary,
                fontSize = 12.sp
            )
        }

        // Weight input
        OutlinedTextField(
            value = set.weight,
            onValueChange = onWeightChange,
            modifier = Modifier.weight(1f).height(50.dp),
            singleLine = true,
            placeholder = {
                Text("0", color = GymTextDisabled,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
            },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Center,
                color = GymTextPrimary,
                fontWeight = FontWeight.SemiBold
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GymAccent,
                unfocusedBorderColor = GymBorder,
                focusedContainerColor = GymDarkSurface,
                unfocusedContainerColor = GymDarkSurface,
                cursorColor = GymAccent
            )
        )

        // Reps input
        OutlinedTextField(
            value = set.reps,
            onValueChange = onRepsChange,
            modifier = Modifier.weight(1f).height(50.dp),
            singleLine = true,
            placeholder = {
                Text("0", color = GymTextDisabled,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
            },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Center,
                color = GymTextPrimary,
                fontWeight = FontWeight.SemiBold
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GymAccent,
                unfocusedBorderColor = GymBorder,
                focusedContainerColor = GymDarkSurface,
                unfocusedContainerColor = GymDarkSurface,
                cursorColor = GymAccent
            )
        )

        // Remove set button
        Box(modifier = Modifier.width(32.dp), contentAlignment = Alignment.Center) {
            if (onRemove != null) {
                IconButton(onClick = onRemove, modifier = Modifier.size(28.dp)) {
                    Icon(Icons.Default.Remove, "Remove", tint = GymRed.copy(alpha = 0.7f), modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}
