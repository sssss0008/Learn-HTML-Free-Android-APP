package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.Devices
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ui.screens.AboutCreatorScreen
import com.example.ui.screens.CelebrationCertificateScreen
import com.example.ui.screens.CurriculumScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LessonDetailScreen
import com.example.ui.screens.OnboardingExperienceScreen
import com.example.ui.screens.OnboardingGoalScreen
import com.example.ui.screens.OnboardingNameScreen
import com.example.ui.screens.PersonalizedStartScreen
import com.example.ui.screens.PlaygroundScreen
import com.example.ui.screens.PracticeScreen
import com.example.ui.screens.ProgressProfileScreen
import com.example.ui.screens.ProjectDetailScreen
import com.example.ui.screens.ProjectsScreen
import com.example.ui.screens.SearchScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.TagExplorerScreen
import com.example.ui.screens.WelcomeScreen
import com.example.ui.theme.HtmlOrangePrimary
import com.example.ui.theme.LearnHtmlTheme
import com.example.ui.viewmodel.LearnHtmlViewModel
import com.example.ui.viewmodel.MainNavTab
import com.example.ui.viewmodel.ScreenDestination

class MainActivity : ComponentActivity() {

    private val viewModel: LearnHtmlViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val profile by viewModel.userProfile.collectAsState()
            val themeMode = profile?.appTheme ?: "DEVELOPER_DARK"

            // Auto-route to MainNavigation if onboarding was already completed in DB
            LaunchedEffect(profile?.isOnboardingCompleted) {
                if (profile?.isOnboardingCompleted == true && viewModel.currentScreen.value is ScreenDestination.Welcome) {
                    viewModel.navigateTo(ScreenDestination.MainNavigation)
                }
            }

            LearnHtmlTheme(themeMode = themeMode) {
                MainContentRoot(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainContentRoot(viewModel: LearnHtmlViewModel) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val currentTab by viewModel.currentTab.collectAsState()

    AnimatedContent(
        targetState = currentScreen,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        label = "screen_transition"
    ) { screen ->
        when (screen) {
            is ScreenDestination.Welcome -> {
                WelcomeScreen(
                    onStartClick = { viewModel.navigateTo(ScreenDestination.OnboardingName) }
                )
            }
            is ScreenDestination.OnboardingName -> {
                OnboardingNameScreen(
                    viewModel = viewModel,
                    onContinue = { viewModel.navigateTo(ScreenDestination.OnboardingGoal) }
                )
            }
            is ScreenDestination.OnboardingGoal -> {
                OnboardingGoalScreen(
                    viewModel = viewModel,
                    onContinue = { viewModel.navigateTo(ScreenDestination.OnboardingExperience) }
                )
            }
            is ScreenDestination.OnboardingExperience -> {
                OnboardingExperienceScreen(
                    viewModel = viewModel,
                    onContinue = { viewModel.navigateTo(ScreenDestination.PersonalizedStart) }
                )
            }
            is ScreenDestination.PersonalizedStart -> {
                PersonalizedStartScreen(
                    viewModel = viewModel,
                    onStartCourse = {
                        viewModel.setTab(MainNavTab.HOME)
                    }
                )
            }
            is ScreenDestination.LessonDetail -> {
                LessonDetailScreen(
                    lessonId = screen.lessonId,
                    viewModel = viewModel
                )
            }
            is ScreenDestination.ProjectDetail -> {
                ProjectDetailScreen(
                    projectId = screen.projectId,
                    viewModel = viewModel
                )
            }
            is ScreenDestination.Search -> {
                SearchScreen(viewModel = viewModel)
            }
            is ScreenDestination.CelebrationCertificate -> {
                CelebrationCertificateScreen(viewModel = viewModel)
            }
            is ScreenDestination.AboutCreator -> {
                AboutCreatorScreen(viewModel = viewModel)
            }
            is ScreenDestination.Settings -> {
                SettingsScreen(viewModel = viewModel)
            }
            is ScreenDestination.MainNavigation, is ScreenDestination.TagDetail -> {
                MainNavigationScaffold(viewModel = viewModel, currentTab = currentTab)
            }
        }
    }
}

@Composable
fun MainNavigationScaffold(
    viewModel: LearnHtmlViewModel,
    currentTab: MainNavTab
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                modifier = Modifier.testTag("main_bottom_nav")
            ) {
                // Home
                NavigationBarItem(
                    selected = currentTab == MainNavTab.HOME,
                    onClick = { viewModel.setTab(MainNavTab.HOME) },
                    icon = {
                        Icon(
                            imageVector = if (currentTab == MainNavTab.HOME) Icons.Filled.Home else Icons.Outlined.Home,
                            contentDescription = "Home"
                        )
                    },
                    label = { Text("Home", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = HtmlOrangePrimary,
                        selectedTextColor = HtmlOrangePrimary,
                        indicatorColor = HtmlOrangePrimary.copy(alpha = 0.15f)
                    ),
                    modifier = Modifier.testTag("nav_tab_home")
                )

                // Learn (Curriculum)
                NavigationBarItem(
                    selected = currentTab == MainNavTab.LEARN,
                    onClick = { viewModel.setTab(MainNavTab.LEARN) },
                    icon = {
                        Icon(
                            imageVector = if (currentTab == MainNavTab.LEARN) Icons.Filled.Book else Icons.Outlined.Book,
                            contentDescription = "Learn"
                        )
                    },
                    label = { Text("Learn", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = HtmlOrangePrimary,
                        selectedTextColor = HtmlOrangePrimary,
                        indicatorColor = HtmlOrangePrimary.copy(alpha = 0.15f)
                    ),
                    modifier = Modifier.testTag("nav_tab_learn")
                )

                // Practice / Playground
                NavigationBarItem(
                    selected = currentTab == MainNavTab.PRACTICE,
                    onClick = { viewModel.setTab(MainNavTab.PRACTICE) },
                    icon = {
                        Icon(
                            imageVector = if (currentTab == MainNavTab.PRACTICE) Icons.Filled.Code else Icons.Outlined.Code,
                            contentDescription = "Playground"
                        )
                    },
                    label = { Text("Playground", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = HtmlOrangePrimary,
                        selectedTextColor = HtmlOrangePrimary,
                        indicatorColor = HtmlOrangePrimary.copy(alpha = 0.15f)
                    ),
                    modifier = Modifier.testTag("nav_tab_practice")
                )

                // Projects
                NavigationBarItem(
                    selected = currentTab == MainNavTab.PROJECTS,
                    onClick = { viewModel.setTab(MainNavTab.PROJECTS) },
                    icon = {
                        Icon(
                            imageVector = if (currentTab == MainNavTab.PROJECTS) Icons.Filled.Devices else Icons.Outlined.Devices,
                            contentDescription = "Projects"
                        )
                    },
                    label = { Text("Projects", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = HtmlOrangePrimary,
                        selectedTextColor = HtmlOrangePrimary,
                        indicatorColor = HtmlOrangePrimary.copy(alpha = 0.15f)
                    ),
                    modifier = Modifier.testTag("nav_tab_projects")
                )

                // Reference / Tags
                NavigationBarItem(
                    selected = currentTab == MainNavTab.REFERENCE,
                    onClick = { viewModel.setTab(MainNavTab.REFERENCE) },
                    icon = {
                        Icon(
                            imageVector = if (currentTab == MainNavTab.REFERENCE) Icons.Filled.Explore else Icons.Outlined.Explore,
                            contentDescription = "Reference"
                        )
                    },
                    label = { Text("Reference", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = HtmlOrangePrimary,
                        selectedTextColor = HtmlOrangePrimary,
                        indicatorColor = HtmlOrangePrimary.copy(alpha = 0.15f)
                    ),
                    modifier = Modifier.testTag("nav_tab_reference")
                )

                // Progress / Profile
                NavigationBarItem(
                    selected = currentTab == MainNavTab.PROFILE,
                    onClick = { viewModel.setTab(MainNavTab.PROFILE) },
                    icon = {
                        Icon(
                            imageVector = if (currentTab == MainNavTab.PROFILE) Icons.Filled.Person else Icons.Outlined.Person,
                            contentDescription = "Profile"
                        )
                    },
                    label = { Text("Profile", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = HtmlOrangePrimary,
                        selectedTextColor = HtmlOrangePrimary,
                        indicatorColor = HtmlOrangePrimary.copy(alpha = 0.15f)
                    ),
                    modifier = Modifier.testTag("nav_tab_profile")
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentTab) {
                MainNavTab.HOME -> HomeScreen(viewModel = viewModel)
                MainNavTab.LEARN -> CurriculumScreen(viewModel = viewModel)
                MainNavTab.PRACTICE -> PlaygroundScreen(viewModel = viewModel)
                MainNavTab.PROJECTS -> ProjectsScreen(viewModel = viewModel)
                MainNavTab.REFERENCE -> TagExplorerScreen(viewModel = viewModel)
                MainNavTab.PROGRESS, MainNavTab.PROFILE -> ProgressProfileScreen(viewModel = viewModel)
            }
        }
    }
}
