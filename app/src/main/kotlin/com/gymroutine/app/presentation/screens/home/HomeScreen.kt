package com.gymroutine.app.presentation.screens.home

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.gymroutine.app.domain.model.Routine
import com.gymroutine.app.presentation.components.*
import com.gymroutine.app.presentation.theme.*
import com.gymroutine.app.presentation.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(
    onNavigateToCreateRoutine: () -> Unit,
    onNavigateToRoutineDetail: (Long) -> Unit,
    onNavigateToStartWorkout: () -> Unit,
    onNavigateToProgress: () -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToSettings: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val dateFormat = remember { SimpleDateFormat("EEEE, MMMM d", Locale.getDefault()) }
    val today = remember { dateFormat.format(Date()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GymBlack)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            // Header
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(GymDarkSurface, GymBlack)
                            )
                        )
                        .statusBarsPadding()
                        .padding(horizontal = 24.dp, vertical = 24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Hey, Athlete 👋",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = GymTextPrimary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = today,
                                style = MaterialTheme.typography.bodyMedium,
                                color = GymTextSecondary
                            )
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            IconButton(
                                onClick = onNavigateToSearch,
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(GymCard)
                            ) {
                                Icon(Icons.Default.Search, "Search", tint = GymTextPrimary, modifier = Modifier.size(20.dp))
                            }
                            IconButton(
                                onClick = onNavigateToSettings,
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(GymCard)
                            ) {
                                Icon(Icons.Default.Settings, "Settings", tint = GymTextPrimary, modifier = Modifier.size(20.dp))
                            }
                        }
                    }
                }
            }

            // Stats Row
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatChip(
                        label = "Routines",
                        value = "${uiState.routineCount}",
                        color = GymAccent,
                        modifier = Modifier.weight(1f)
                    )
                    StatChip(
                        label = "Last Workout",
                        value = uiState.lastSession?.let {
                            val fmt = SimpleDateFormat("MMM d", Locale.getDefault())
                            fmt.format(Date(it.sessionDate))
                        } ?: "None",
                        color = GymBlue,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Action Buttons
            item {
                Column(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Start Workout - Primary CTA
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(GymAccent, GymAccentDim)
                                )
                            )
                            .clickable(onClick = onNavigateToStartWorkout)
                            .padding(20.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(GymBlack.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.PlayArrow, null, tint = GymBlack, modifier = Modifier.size(28.dp))
                            }
                            Column {
                                Text("Start Workout", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = GymBlack)
                                Text("Choose a routine to begin", style = MaterialTheme.typography.bodySmall, color = GymBlack.copy(alpha = 0.6f))
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(Icons.Default.ArrowForward, null, tint = GymBlack.copy(alpha = 0.6f))
                        }
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        // Create Routine
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(16.dp))
                                .background(GymCard)
                                .border(1.dp, GymBorder, RoundedCornerShape(16.dp))
                                .clickable(onClick = onNavigateToCreateRoutine)
                                .padding(16.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(Icons.Default.Add, null, tint = GymAccent, modifier = Modifier.size(28.dp))
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("New Routine", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.SemiBold, color = GymTextPrimary)
                            }
                        }

                        // Progress
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(16.dp))
                                .background(GymCard)
                                .border(1.dp, GymBorder, RoundedCornerShape(16.dp))
                                .clickable(onClick = onNavigateToProgress)
                                .padding(16.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(Icons.Default.TrendingUp, null, tint = GymBlue, modifier = Modifier.size(28.dp))
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Progress", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.SemiBold, color = GymTextPrimary)
                            }
                        }
                    }
                }
            }

            // Routines Header
            item {
                Row(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SectionHeader("My Routines", modifier = Modifier.weight(1f))
                    if (uiState.routines.isNotEmpty()) {
                        AccentTag("${uiState.routines.size}")
                    }
                }
            }

            if (uiState.isLoading) {
                item { LoadingSpinner() }
            } else if (uiState.routines.isEmpty()) {
                item {
                    EmptyState(
                        icon = Icons.Default.FitnessCenter,
                        title = "No Routines Yet",
                        subtitle = "Create your first routine to start tracking your workouts"
                    )
                }
            } else {
                items(uiState.routines, key = { it.id }) { routine ->
                    RoutineCard(
                        routine = routine,
                        onClick = { onNavigateToRoutineDetail(routine.id) },
                        onDelete = { viewModel.deleteRoutine(routine) },
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RoutineCard(
    routine: Routine,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showMenu by remember { mutableStateOf(false) }
    val dateFormat = remember { SimpleDateFormat("MMM d, yyyy", Locale.getDefault()) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(GymCard)
            .border(1.dp, GymBorder, RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = routine.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = GymTextPrimary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = dateFormat.format(Date(routine.createdAt)),
                        style = MaterialTheme.typography.bodySmall,
                        color = GymTextTertiary
                    )
                }
                Box {
                    IconButton(
                        onClick = { showMenu = true },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(Icons.Default.MoreVert, null, tint = GymTextTertiary, modifier = Modifier.size(18.dp))
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                        modifier = Modifier.background(GymCardElevated)
                    ) {
                        DropdownMenuItem(
                            text = { Text("Delete", color = GymRed) },
                            onClick = { showMenu = false; onDelete() },
                            leadingIcon = { Icon(Icons.Default.Delete, null, tint = GymRed) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                InfoBadge(
                    icon = Icons.Default.CalendarToday,
                    text = "${routine.dayCount} ${if (routine.dayCount == 1) "Category" else "Categories"}"
                )
                if (routine.exerciseCount > 0) {
                    InfoBadge(
                        icon = Icons.Default.FitnessCenter,
                        text = "${routine.exerciseCount} Exercises"
                    )
                }
            }
        }

        // Accent bar
        Box(
            modifier = Modifier
                .width(4.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(topEnd = 2.dp, bottomEnd = 2.dp))
                .background(GymAccent)
                .align(Alignment.CenterStart)
        )
    }
}

@Composable
fun InfoBadge(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(GymDarkSurface)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(icon, null, tint = GymTextTertiary, modifier = Modifier.size(12.dp))
        Text(text, style = MaterialTheme.typography.labelSmall, color = GymTextSecondary)
    }
}
