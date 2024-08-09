package com.example.design.animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyle

object AnimatedStyle {

    private const val CONTENT_ANIMATION_DURATION = 500

    object SlideInOut : DestinationStyle.Animated() {

        override val enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? =
            {
                slideInHorizontally(
                    animationSpec = tween(CONTENT_ANIMATION_DURATION),
                    initialOffsetX = { fullWidth -> fullWidth }
                )
            }

        override val exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? =
            {
                slideOutHorizontally(
                    animationSpec = tween(CONTENT_ANIMATION_DURATION),
                    targetOffsetX = { fullWidth -> -fullWidth }
                )
            }

        override val popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? =
            {
                slideInHorizontally(
                    animationSpec = tween(CONTENT_ANIMATION_DURATION),
                    initialOffsetX = { fullWidth -> -fullWidth }
                )
            }

        override val popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? =
            {
                slideOutHorizontally(
                    animationSpec = tween(CONTENT_ANIMATION_DURATION),
                    targetOffsetX = { fullWidth -> fullWidth }
                )
            }
    }
}