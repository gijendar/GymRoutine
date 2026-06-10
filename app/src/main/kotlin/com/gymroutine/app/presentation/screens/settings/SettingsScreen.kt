package com.gymroutine.app.presentation.screens.settings

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gymroutine.app.presentation.components.*
import com.gymroutine.app.presentation.theme.*
import com.gymroutine.app.presentation.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToHowToUse: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GymBlack)
    ) {
        GymTopBar(title = "Settings", onNavigateBack = onNavigateBack)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // ── Appearance ───────────────────────────────────────────────────
            SettingsSection(title = "Appearance") {
                SettingsToggleRow(
                    icon = Icons.Default.DarkMode,
                    iconColor = GymPurple,
                    title = "Dark Mode",
                    subtitle = "Use dark theme throughout the app",
                    checked = uiState.isDarkMode,
                    onCheckedChange = { viewModel.toggleDarkMode() }
                )
            }

            // ── Help ─────────────────────────────────────────────────────────
            SettingsSection(title = "Help") {
                SettingsActionRow(
                    icon = Icons.Default.MenuBook,
                    iconColor = GymAccent,
                    title = "How To Use",
                    subtitle = "Learn the complete app workflow",
                    onClick = onNavigateToHowToUse
                )
            }

            // ── App info ─────────────────────────────────────────────────────
            SettingsSection(title = "About") {
                SettingsInfoRow(
                    icon = Icons.Default.Info,
                    iconColor = GymBlue,
                    title = "Version",
                    value = "1.0.0"
                )
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = GymDivider)
                SettingsInfoRow(
                    icon = Icons.Default.FitnessCenter,
                    iconColor = GymOrange,
                    title = "App",
                    value = "GymRoutine"
                )
            }

            // Branding footer
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(GymAccentSubtle),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.FitnessCenter,
                            null,
                            tint = GymAccent,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "GymRoutine",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = GymTextPrimary
                    )
                    Text(
                        "Plan. Train. Progress.",
                        style = MaterialTheme.typography.bodySmall,
                        color = GymTextTertiary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun SettingsSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SectionHeader(title)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(GymCard)
                .border(1.dp, GymBorder, RoundedCornerShape(16.dp)),
            content = content
        )
    }
}

@Composable
fun SettingsToggleRow(
    icon: ImageVector,
    iconColor: Color,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!checked) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(iconColor.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = iconColor, modifier = Modifier.size(20.dp))
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = GymTextPrimary)
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = GymTextTertiary)
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = GymBlack,
                checkedTrackColor = GymAccent,
                uncheckedThumbColor = GymTextSecondary,
                uncheckedTrackColor = GymBorder
            )
        )
    }
}

@Composable
fun SettingsActionRow(
    icon: ImageVector,
    iconColor: Color,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(iconColor.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = iconColor, modifier = Modifier.size(20.dp))
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = GymTextPrimary)
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = GymTextTertiary)
        }
        Icon(Icons.Default.ChevronRight, null, tint = GymTextTertiary, modifier = Modifier.size(18.dp))
    }
}

@Composable
fun SettingsInfoRow(
    icon: ImageVector,
    iconColor: Color,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(iconColor.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = iconColor, modifier = Modifier.size(20.dp))
        }
        Text(
            title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = GymTextPrimary,
            modifier = Modifier.weight(1f)
        )
        Text(value, style = MaterialTheme.typography.bodySmall, color = GymTextTertiary)
    }
}
