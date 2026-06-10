package com.gymroutine.app.presentation.screens.routine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gymroutine.app.presentation.components.*
import com.gymroutine.app.presentation.theme.*
import com.gymroutine.app.presentation.viewmodel.CreateRoutineViewModel

@Composable
fun CreateRoutineScreen(
    onNavigateBack: () -> Unit,
    onRoutineCreated: (Long) -> Unit,
    viewModel: CreateRoutineViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(uiState.createdRoutineId) {
        uiState.createdRoutineId?.let { onRoutineCreated(it) }
    }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(300)
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GymBlack)
    ) {
        GymTopBar(title = "New Routine", onNavigateBack = onNavigateBack)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Icon
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(GymAccentSubtle, shape = androidx.compose.foundation.shape.CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.FitnessCenter, null, tint = GymAccent, modifier = Modifier.size(40.dp))
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SectionHeader("Routine Name")
                Spacer(modifier = Modifier.height(4.dp))
                GymTextField(
                    value = uiState.routineName,
                    onValueChange = viewModel::updateRoutineName,
                    placeholder = "e.g. Push Pull Legs",
                    modifier = Modifier.focusRequester(focusRequester)
                )
                uiState.error?.let {
                    Text(it, style = MaterialTheme.typography.bodySmall, color = GymRed)
                }
            }

            Text(
                text = "Give your routine a clear, memorable name. You can always change it later.",
                style = MaterialTheme.typography.bodySmall,
                color = GymTextTertiary
            )

            Spacer(modifier = Modifier.weight(1f))

            AccentButton(
                text = if (uiState.isCreating) "Creating..." else "Create Routine",
                onClick = viewModel::createRoutine,
                enabled = uiState.routineName.isNotBlank() && !uiState.isCreating,
                leadingIcon = Icons.Default.Add,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
