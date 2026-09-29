package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.CodeEditorCard
import com.example.ui.components.LivePreviewFrame
import com.example.ui.theme.HtmlOrangePrimary
import com.example.ui.theme.TechCyan
import com.example.ui.viewmodel.LearnHtmlViewModel

@Composable
fun PlaygroundScreen(
    viewModel: LearnHtmlViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val currentCode by viewModel.playgroundCode.collectAsState()
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Editor, 1: Browser Preview

    val templates = listOf(
        "Boilerplate" to "<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n  <meta charset=\"UTF-8\">\n  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n  <title>My First Website</title>\n</head>\n<body>\n  <h1>Welcome to the Modern Web</h1>\n  <p>Learn HTML from zero with Awiskar Acharya.</p>\n</body>\n</html>",
        "Interactive Form" to "<!DOCTYPE html>\n<html>\n<body>\n  <h2>User Feedback</h2>\n  <form>\n    <label for=\"uname\">Your Name:</label><br>\n    <input type=\"text\" id=\"uname\" placeholder=\"Enter name\"><br><br>\n    <label for=\"comm\">Message:</label><br>\n    <textarea id=\"comm\" rows=\"3\" placeholder=\"What do you love about HTML?\"></textarea><br><br>\n    <button type=\"button\" onclick=\"alert('Form submitted successfully!')\">Send</button>\n  </form>\n</body>\n</html>",
        "Semantic Article" to "<!DOCTYPE html>\n<html>\n<body>\n  <header>\n    <h1>Code Chronicles</h1>\n    <nav><a href=\"#\">Home</a> | <a href=\"#\">Articles</a></nav>\n  </header>\n  <hr>\n  <main>\n    <article>\n      <h2>Why Semantic HTML Wins</h2>\n      <p>Clean markup makes the web faster, accessible, and easily indexed by search engines.</p>\n    </article>\n  </main>\n  <footer><p>&copy; 2026 Awiskar Acharya</p></footer>\n</body>\n</html>",
        "Menu Table" to "<!DOCTYPE html>\n<html>\n<body>\n  <h3>Coffee Shop Menu</h3>\n  <table border=\"1\" cellpadding=\"6\">\n    <tr><th>Beverage</th><th>Size</th><th>Price</th></tr>\n    <tr><td>Espresso</td><td>Single</td><td>$3.00</td></tr>\n    <tr><td>Cappuccino</td><td>Regular</td><td>$4.50</td></tr>\n    <tr><td>Cold Brew</td><td>Large</td><td>$5.00</td></tr>\n  </table>\n</body>\n</html>"
    )

    BoxWithConstraints(modifier = modifier.fillMaxSize().testTag("playground_screen")) {
        val isWideScreen = maxWidth > 700.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "HTML Playground",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Real-time editor with instant browser execution",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Surface(
                    color = HtmlOrangePrimary.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "LIVE HTML5",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        color = HtmlOrangePrimary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Starter Templates Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                templates.forEach { (name, code) ->
                    FilterChip(
                        selected = currentCode == code,
                        onClick = {
                            viewModel.playgroundCode.value = code
                            Toast.makeText(context, "Loaded $name template", Toast.LENGTH_SHORT).show()
                        },
                        label = { Text(name, fontSize = 12.sp, fontWeight = FontWeight.SemiBold) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = HtmlOrangePrimary,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (isWideScreen) {
                // Wide Screen Split View: Code on Left | Preview on Right
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        CodeEditorCard(
                            code = currentCode,
                            onCodeChange = { viewModel.playgroundCode.value = it },
                            onResetCode = { viewModel.playgroundCode.value = templates[0].second },
                            title = "index.html",
                            maxHeight = 600
                        )
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        LivePreviewFrame(
                            htmlContent = currentCode,
                            heightDp = 560
                        )
                    }
                }
            } else {
                // Mobile View: Segmented Tabs (HTML | Live Preview)
                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = HtmlOrangePrimary
                ) {
                    Tab(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Code, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("HTML Editor", fontWeight = FontWeight.Bold)
                            }
                        }
                    )
                    Tab(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Visibility, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Browser Preview", fontWeight = FontWeight.Bold)
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (selectedTab == 0) {
                    CodeEditorCard(
                        code = currentCode,
                        onCodeChange = { viewModel.playgroundCode.value = it },
                        onRunCode = { selectedTab = 1 },
                        onResetCode = { viewModel.playgroundCode.value = templates[0].second },
                        title = "playground.html",
                        maxHeight = 440
                    )
                } else {
                    LivePreviewFrame(
                        htmlContent = currentCode,
                        heightDp = 420
                    )
                }
            }
        }
    }
}
