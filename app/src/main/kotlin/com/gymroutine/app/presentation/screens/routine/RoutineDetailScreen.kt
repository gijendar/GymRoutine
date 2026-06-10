package com.gymroutine.app.presentation.screens.routine

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gymroutine.app.domain.model.WorkoutDay
import com.gymroutine.app.presentation.components.*
import com.gymroutine.app.presentation.theme.*
import com.gymroutine.app.presentation.viewmodel.RoutineViewModel

@Composable
fun RoutineDetailScreen(
    onNavigateBack: () -> Unit,
    onNavigateToWorkoutDay: (Long) -> Unit,
    viewModel: RoutineViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Add category dialog
    if (uiState.showAddCategoryDialog) {
        CategoryDialog(
            title = "New Category",
            value = uiState.newCategoryName,
            onValueChange = viewModel::updateNewCategoryName,
            onConfirm = viewModel::addCategory,
            onDismiss = viewModel::hideAddCategoryDialog
        )
    }

    // Edit category dialog
    if (uiState.showEditCategoryDialog) {
        CategoryDialog(
            title = "Rename Category",
            value = uiState.newCategoryName,
            onValueChange = viewModel::updateNewCategoryName,
            onConfirm = viewModel::updateCategory,
            onDismiss = viewModel::hideEditCategoryDialog
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GymBlack)
    ) {
        GymTopBar(
            title = uiState.routine?.name ?: "Routine",
            onNavigateBack = onNavigateBack,
            actions = {
                IconButton(
                    onClick = viewModel::showAddCategoryDialog,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(GymAccentSubtle)
                ) {
                    Icon(Icons.Default.Add, "Add Category", tint = GymAccent, modifier = Modifier.size(20.dp))
                }
            }
        )

        if (uiState.isLoading) {
            LoadingSpinner()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Stats row
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        StatChip("Categories", "${uiState.categories.size}", GymAccent, Modifier.weight(1f))
                        StatChip(
                            "Exercises",
                            "${uiState.categories.sumOf { it.exerciseCount }}",
                            GymBlue,
                            Modifier.weight(1f)
                        )
                    }
                }

                item { SectionHeader("Exercise Categories") }

                if (uiState.categories.isEmpty()) {
                    item {
                        EmptyState(
                            icon = Icons.Default.Category,
                            title = "No Categories Yet",
                            subtitle = "Add categories like Chest, Back, Legs, Shoulders"
                        )
                    }
                } else {
                    itemsIndexed(uiState.categories, key = { _, cat -> cat.id }) { index, category ->
                        CategoryCard(
                            category = category,
                            index = index,
                            onClick = { onNavigateToWorkoutDay(category.id) },
                            onEdit = { viewModel.showEditCategoryDialog(category) },
                            onDelete = { viewModel.deleteCategory(category) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryCard(
    category: WorkoutDay,
    index: Int,
    onClick: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }
    val accentColors = listOf(GymAccent, GymBlue, GymOrange, GymPurple, GymGreen)
    val color = accentColors[index % accentColors.size]

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(GymCard)
            .border(1.dp, GymBorder, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Index badge
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

            Icon(Icons.Default.ChevronRight, null, tint = GymTextTertiary, modifier = Modifier.size(20.dp))

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
                        text = { Text("Rename", color = GymTextPrimary) },
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
fun CategoryDialog(
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
                placeholder = "e.g. Chest, Back, Legs, Shoulders"
            )
        },
        confirmButton = {
            AccentButton(text = "Save", onClick = onConfirm, enabled = value.isNotBlank())
        },
        dismissButton = {
            OutlineButton(text = "Cancel", onClick = onDismiss)
        }
    )
}
