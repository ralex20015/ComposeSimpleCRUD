// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    val roomVersion = "2.6.1"
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose) apply false
    id("androidx.room") version roomVersion apply false
}