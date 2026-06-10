package com.gymroutine.app.presentation.navigation

sealed class NavRoutes(val route: String) {
    object Splash : NavRoutes("splash")
    object Home : NavRoutes("home")
    object CreateRoutine : NavRoutes("create_routine")
    object RoutineDetail : NavRoutes("routine_detail/{routineId}") {
        fun createRoute(routineId: Long) = "routine_detail/$routineId"
    }
    object WorkoutDay : NavRoutes("workout_day/{dayId}") {
        fun createRoute(dayId: Long) = "workout_day/$dayId"
    }
    object StartWorkout : NavRoutes("start_workout")
    object ActiveWorkout : NavRoutes("active_workout/{sessionId}/{dayId}") {
        fun createRoute(sessionId: Long, dayId: Long) = "active_workout/$sessionId/$dayId"
    }
    object WorkoutComplete : NavRoutes("workout_complete/{sessionId}") {
        fun createRoute(sessionId: Long) = "workout_complete/$sessionId"
    }
    object ExerciseHistory : NavRoutes("exercise_history/{exerciseId}") {
        fun createRoute(exerciseId: Long) = "exercise_history/$exerciseId"
    }
    object Progress : NavRoutes("progress")
    object Search : NavRoutes("search")
    object Settings : NavRoutes("settings")
    object HowToUse : NavRoutes("how_to_use")
}
