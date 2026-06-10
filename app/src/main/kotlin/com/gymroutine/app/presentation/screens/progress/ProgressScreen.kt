package com.gymroutine.app.presentation.screens.progress

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gymroutine.app.presentation.components.*
import com.gymroutine.app.presentation.theme.*
import com.gymroutine.app.presentation.viewmodel.ProgressPoint
import com.gymroutine.app.presentation.viewmodel.ProgressViewModel

@Composable
fun ProgressScreen(
    onNavigateBack: () -> Unit,
    viewModel: ProgressViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GymBlack)
    ) {
        GymTopBar(title = "Progress", onNavigateBack = onNavigateBack)

        if (uiState.isLoading) {
            LoadingSpinner()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Overview stats
                SectionHeader("Overview")
                Spacer(modifier = Modifier.height(4.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    BigStatCard(
                        icon = Icons.Default.FitnessCenter,
                        value = "${uiState.totalSessions}",
                        label = "Total\nWorkouts",
                        color = GymAccent,
                        modifier = Modifier.weight(1f)
                    )
                    BigStatCard(
                        icon = Icons.Default.CalendarMonth,
                        value = "${uiState.thisWeekSessions}",
                        label = "This\nWeek",
                        color = GymBlue,
                        modifier = Modifier.weight(1f)
                    )
                    BigStatCard(
                        icon = Icons.Default.AccountTree,
                        value = "${uiState.totalRoutines}",
                        label = "Active\nRoutines",
                        color = GymOrange,
                        modifier = Modifier.weight(1f)
                    )
                }

                // Volume Chart
                SectionHeader("Volume History")
                Spacer(modifier = Modifier.height(4.dp))

                if (uiState.volumeData.isEmpty()) {
                    EmptyState(
                        icon = Icons.Default.BarChart,
                        title = "No Data Yet",
                        subtitle = "Complete workouts to see your volume progress"
                    )
                } else {
                    VolumeBarChart(data = uiState.volumeData)
                }

                // Workout frequency info
                SectionHeader("Frequency")
                Spacer(modifier = Modifier.height(4.dp))

                FrequencyInfo(
                    totalSessions = uiState.totalSessions,
                    weekSessions = uiState.thisWeekSessions
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun BigStatCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    label: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(GymCard)
            .border(1.dp, GymBorder, RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = color, modifier = Modifier.size(22.dp))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Black, color = GymTextPrimary)
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, style = MaterialTheme.typography.labelSmall, color = GymTextTertiary, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
    }
}

@Composable
fun VolumeBarChart(data: List<ProgressPoint>) {
    val maxVal = data.maxOfOrNull { it.value }?.takeIf { it > 0f } ?: 1f

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(GymCard)
            .border(1.dp, GymBorder, RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        Column {
            Text("Volume per Session (kg)", style = MaterialTheme.typography.labelSmall, color = GymTextTertiary)
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth().height(140.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                data.forEach { point ->
                    val fraction = if (maxVal > 0) point.value / maxVal else 0f
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        // Value label
                        if (fraction > 0.1f) {
                            Text(
                                "${point.value.toInt()}",
                                style = MaterialTheme.typography.labelSmall,
                                color = GymTextTertiary,
                                fontSize = 9.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                        // Bar
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(fraction)
                                .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(GymAccent, GymAccentDim)
                                    )
                                )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = GymBorder)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                data.forEach { point ->
                    Text(
                        point.label,
                        style = MaterialTheme.typography.labelSmall,
                        color = GymTextTertiary,
                        fontSize = 9.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun FrequencyInfo(totalSessions: Int, weekSessions: Int) {
    val avgPerWeek = if (totalSessions > 0) {
        // rough estimate
        weekSessions.toFloat()
    } else 0f

    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        InfoCard(
            title = "Avg This Week",
            value = "$weekSessions sessions",
            icon = Icons.Default.CalendarToday,
            modifier = Modifier.weight(1f)
        )
        InfoCard(
            title = "All Time",
            value = "$totalSessions sessions",
            icon = Icons.Default.TrendingUp,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun InfoCard(title: String, value: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(GymCard)
            .border(1.dp, GymBorder, RoundedCornerShape(14.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Icon(icon, null, tint = GymAccent, modifier = Modifier.size(16.dp))
            Text(title, style = MaterialTheme.typography.labelSmall, color = GymTextTertiary)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(value, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GymTextPrimary)
    }
}
