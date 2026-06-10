package com.gymroutine.app.presentation.screens.search

import androidx.compose.animation.*
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gymroutine.app.domain.model.*
import com.gymroutine.app.presentation.components.*
import com.gymroutine.app.presentation.theme.*
import com.gymroutine.app.presentation.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    onNavigateBack: () -> Unit,
    onNavigateToRoutine: (Long) -> Unit,
    onNavigateToWorkoutDay: (Long) -> Unit,
    onNavigateToExerciseHistory: (Long) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val focusRequester = remember { FocusRequester() }
    val hasResults = uiState.routines.isNotEmpty() || uiState.categories.isNotEmpty() || uiState.exercises.isNotEmpty()

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(200)
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GymBlack)
    ) {
        // Search header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(GymDarkSurface)
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(GymCard)
                ) {
                    Icon(Icons.Default.ArrowBack, "Back", tint = GymTextPrimary, modifier = Modifier.size(20.dp))
                }

                OutlinedTextField(
                    value = uiState.query,
                    onValueChange = viewModel::updateQuery,
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(focusRequester),
                    placeholder = { Text("Search routines, categories, exercises...", color = GymTextTertiary) },
                    singleLine = true,
                    leadingIcon = {
                        if (uiState.isSearching) {
                            CircularProgressIndicator(modifier = Modifier.size(18.dp), color = GymAccent, strokeWidth = 2.dp)
                        } else {
                            Icon(Icons.Default.Search, null, tint = GymTextTertiary)
                        }
                    },
                    trailingIcon = if (uiState.query.isNotEmpty()) {
                        {
                            IconButton(onClick = { viewModel.updateQuery("") }) {
                                Icon(Icons.Default.Close, "Clear", tint = GymTextTertiary)
                            }
                        }
                    } else null,
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = GymTextPrimary,
                        unfocusedTextColor = GymTextPrimary,
                        focusedBorderColor = GymAccent,
                        unfocusedBorderColor = GymBorder,
                        cursorColor = GymAccent,
                        focusedContainerColor = GymCard,
                        unfocusedContainerColor = GymCard
                    )
                )
            }
        }

        if (uiState.query.isBlank()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                EmptyState(
                    icon = Icons.Default.Search,
                    title = "Search Everything",
                    subtitle = "Find routines, categories, and exercises instantly"
                )
            }
        } else if (!uiState.isSearching && !hasResults) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                EmptyState(
                    icon = Icons.Default.SearchOff,
                    title = "No Results",
                    subtitle = "Nothing matched \"${uiState.query}\""
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (uiState.routines.isNotEmpty()) {
                    item {
                        SearchSectionHeader(
                            label = "Routines",
                            count = uiState.routines.size,
                            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
                        )
                    }
                    items(uiState.routines, key = { "r_${it.id}" }) { routine ->
                        SearchResultItem(
                            icon = Icons.Default.FitnessCenter,
                            title = routine.name,
                            subtitle = "${routine.dayCount} ${if (routine.dayCount == 1) "category" else "categories"}",
                            accentColor = GymAccent,
                            onClick = { onNavigateToRoutine(routine.id) }
                        )
                    }
                }

                if (uiState.categories.isNotEmpty()) {
                    item {
                        SearchSectionHeader(
                            label = "Categories",
                            count = uiState.categories.size,
                            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
                        )
                    }
                    items(uiState.categories, key = { "d_${it.id}" }) { category ->
                        SearchResultItem(
                            icon = Icons.Default.Category,
                            title = category.name,
                            subtitle = "${category.exerciseCount} ${if (category.exerciseCount == 1) "exercise" else "exercises"}",
                            accentColor = GymBlue,
                            onClick = { onNavigateToWorkoutDay(category.id) }
                        )
                    }
                }

                if (uiState.exercises.isNotEmpty()) {
                    item {
                        SearchSectionHeader(
                            label = "Exercises",
                            count = uiState.exercises.size,
                            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
                        )
                    }
                    items(uiState.exercises, key = { "e_${it.id}" }) { exercise ->
                        SearchResultItem(
                            icon = Icons.Default.DirectionsRun,
                            title = exercise.name,
                            subtitle = "Tap to view history",
                            accentColor = GymOrange,
                            onClick = { onNavigateToExerciseHistory(exercise.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchSectionHeader(label: String, count: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SectionHeader(label)
        AccentTag("$count")
    }
}

@Composable
fun SearchResultItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    accentColor: androidx.compose.ui.graphics.Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(accentColor.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = accentColor, modifier = Modifier.size(20.dp))
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = GymTextPrimary)
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = GymTextTertiary)
        }
        Icon(Icons.Default.ChevronRight, null, tint = GymTextTertiary, modifier = Modifier.size(18.dp))
    }
}
