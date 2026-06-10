package com.gymroutine.app.presentation.screens.exercise

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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gymroutine.app.domain.model.ExerciseLog
import com.gymroutine.app.presentation.components.*
import com.gymroutine.app.presentation.theme.*
import com.gymroutine.app.presentation.viewmodel.ExerciseHistoryViewModel
import com.gymroutine.app.presentation.viewmodel.HistoryEntry

@Composable
fun ExerciseHistoryScreen(
    onNavigateBack: () -> Unit,
    viewModel: ExerciseHistoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GymBlack)
    ) {
        GymTopBar(title = uiState.exercise?.name ?: "Exercise History", onNavigateBack = onNavigateBack)

        if (uiState.isLoading) {
            LoadingSpinner()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Stats row
                item {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        StatChip("Best Weight", uiState.bestWeight?.let { "${it}kg" } ?: "-", GymAccent, Modifier.weight(1f))
                        StatChip("Best Reps", uiState.bestReps?.toString() ?: "-", GymBlue, Modifier.weight(1f))
                        StatChip("Sessions", "${uiState.totalSessions}", GymOrange, Modifier.weight(1f))
                    }
                }

                item { SectionHeader("History") }

                if (uiState.historyEntries.isEmpty()) {
                    item {
                        EmptyState(
                            icon = Icons.Default.History,
                            title = "No History Yet",
                            subtitle = "Complete workouts to see your progress here"
                        )
                    }
                } else {
                    itemsIndexed(uiState.historyEntries) { index, entry ->
                        HistoryEntryCard(entry = entry, isFirst = index == 0)
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryEntryCard(entry: HistoryEntry, isFirst: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Timeline
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(if (isFirst) GymAccent else GymBorder)
            )
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(80.dp)
                    .background(GymBorder)
            )
        }

        // Content
        Column(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(GymCard)
                .border(1.dp, GymBorder, RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(entry.date, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.SemiBold, color = if (isFirst) GymAccent else GymTextSecondary)
                AccentTag("${entry.sets.size} sets")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                entry.sets.forEach { log ->
                    SetHistoryRow(log = log)
                }
            }

            // Volume total
            val volume = entry.sets.sumOf { (it.weight * it.reps).toDouble() }.toFloat()
            if (volume > 0) {
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = GymDivider)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Volume", style = MaterialTheme.typography.labelSmall, color = GymTextTertiary)
                    Text("${volume.toInt()} kg", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.SemiBold, color = GymTextSecondary)
                }
            }
        }
    }
}

@Composable
fun SetHistoryRow(log: ExerciseLog) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(GymDarkSurface),
            contentAlignment = Alignment.Center
        ) {
            Text("${log.setNumber}", style = MaterialTheme.typography.labelSmall, color = GymTextTertiary, fontWeight = FontWeight.Bold)
        }
        Text(
            "${log.weight}kg × ${log.reps} reps",
            style = MaterialTheme.typography.bodyMedium,
            color = GymTextPrimary,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.weight(1f))
        val vol = log.weight * log.reps
        Text("${vol.toInt()} kg", style = MaterialTheme.typography.labelSmall, color = GymTextTertiary)
    }
}
