package com.gymroutine.app.presentation.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.gymroutine.app.presentation.screens.exercise.ExerciseHistoryScreen
import com.gymroutine.app.presentation.screens.home.HomeScreen
import com.gymroutine.app.presentation.screens.howto.HowToUseScreen
import com.gymroutine.app.presentation.screens.progress.ProgressScreen
import com.gymroutine.app.presentation.screens.routine.CreateRoutineScreen
import com.gymroutine.app.presentation.screens.routine.RoutineDetailScreen
import com.gymroutine.app.presentation.screens.search.SearchScreen
import com.gymroutine.app.presentation.screens.settings.SettingsScreen
import com.gymroutine.app.presentation.screens.splash.SplashScreen
import com.gymroutine.app.presentation.screens.workout.ActiveWorkoutScreen
import com.gymroutine.app.presentation.screens.workout.StartWorkoutScreen
import com.gymroutine.app.presentation.screens.workout.WorkoutCompleteScreen
import com.gymroutine.app.presentation.screens.workout.WorkoutDayScreen

@Composable
fun GymRoutineNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Splash.route,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300)) +
                    fadeIn(animationSpec = tween(300))
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { -it / 3 }, animationSpec = tween(300)) +
                    fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { -it / 3 }, animationSpec = tween(300)) +
                    fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300)) +
                    fadeOut(animationSpec = tween(300))
        }
    ) {
        composable(NavRoutes.Splash.route) {
            SplashScreen(onNavigateToHome = {
                navController.navigate(NavRoutes.Home.route) {
                    popUpTo(NavRoutes.Splash.route) { inclusive = true }
                }
            })
        }

        composable(NavRoutes.Home.route) {
            HomeScreen(
                onNavigateToCreateRoutine = { navController.navigate(NavRoutes.CreateRoutine.route) },
                onNavigateToRoutineDetail = { navController.navigate(NavRoutes.RoutineDetail.createRoute(it)) },
                onNavigateToStartWorkout = { navController.navigate(NavRoutes.StartWorkout.route) },
                onNavigateToProgress = { navController.navigate(NavRoutes.Progress.route) },
                onNavigateToSearch = { navController.navigate(NavRoutes.Search.route) },
                onNavigateToSettings = { navController.navigate(NavRoutes.Settings.route) }
            )
        }

        composable(NavRoutes.CreateRoutine.route) {
            CreateRoutineScreen(
                onNavigateBack = { navController.popBackStack() },
                onRoutineCreated = { routineId ->
                    navController.navigate(NavRoutes.RoutineDetail.createRoute(routineId)) {
                        popUpTo(NavRoutes.Home.route)
                    }
                }
            )
        }

        composable(
            NavRoutes.RoutineDetail.route,
            arguments = listOf(navArgument("routineId") { type = NavType.LongType })
        ) {
            RoutineDetailScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToWorkoutDay = { navController.navigate(NavRoutes.WorkoutDay.createRoute(it)) }
            )
        }

        composable(
            NavRoutes.WorkoutDay.route,
            arguments = listOf(navArgument("dayId") { type = NavType.LongType })
        ) {
            WorkoutDayScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToExerciseHistory = { navController.navigate(NavRoutes.ExerciseHistory.createRoute(it)) }
            )
        }

        composable(NavRoutes.StartWorkout.route) {
            StartWorkoutScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToActiveWorkout = { sessionId, dayId ->
                    navController.navigate(NavRoutes.ActiveWorkout.createRoute(sessionId, dayId)) {
                        popUpTo(NavRoutes.Home.route)
                    }
                }
            )
        }

        composable(
            NavRoutes.ActiveWorkout.route,
            arguments = listOf(
                navArgument("sessionId") { type = NavType.LongType },
                navArgument("dayId") { type = NavType.LongType }
            )
        ) {
            ActiveWorkoutScreen(
                onNavigateToComplete = { sessionId ->
                    navController.navigate(NavRoutes.WorkoutComplete.createRoute(sessionId)) {
                        popUpTo(NavRoutes.Home.route)
                    }
                },
                onNavigateBack = {
                    navController.navigate(NavRoutes.Home.route) {
                        popUpTo(NavRoutes.Home.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            NavRoutes.WorkoutComplete.route,
            arguments = listOf(navArgument("sessionId") { type = NavType.LongType })
        ) {
            WorkoutCompleteScreen(
                onNavigateHome = {
                    navController.navigate(NavRoutes.Home.route) {
                        popUpTo(NavRoutes.Home.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            NavRoutes.ExerciseHistory.route,
            arguments = listOf(navArgument("exerciseId") { type = NavType.LongType })
        ) {
            ExerciseHistoryScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(NavRoutes.Progress.route) {
            ProgressScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(NavRoutes.Search.route) {
            SearchScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToRoutine = { navController.navigate(NavRoutes.RoutineDetail.createRoute(it)) },
                onNavigateToWorkoutDay = { navController.navigate(NavRoutes.WorkoutDay.createRoute(it)) },
                onNavigateToExerciseHistory = { navController.navigate(NavRoutes.ExerciseHistory.createRoute(it)) }
            )
        }

        composable(NavRoutes.Settings.route) {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToHowToUse = { navController.navigate(NavRoutes.HowToUse.route) }
            )
        }

        composable(NavRoutes.HowToUse.route) {
            HowToUseScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}
