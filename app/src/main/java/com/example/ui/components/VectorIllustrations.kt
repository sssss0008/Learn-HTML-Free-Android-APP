package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HtmlOrangeDark
import com.example.ui.theme.HtmlOrangeLight
import com.example.ui.theme.HtmlOrangePrimary
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.TechCyan

@Composable
fun WelcomeIllustration(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val floatOffset by infiniteTransition.animateFloat(
        initialValue = -6f,
        targetValue = 6f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "floating"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(210.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            // Background subtle radial ambient glow
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        HtmlOrangePrimary.copy(alpha = 0.25f),
                        TechCyan.copy(alpha = 0.12f),
                        Color.Transparent
                    ),
                    center = Offset(width * 0.5f, height * 0.5f),
                    radius = width * 0.45f
                ),
                center = Offset(width * 0.5f, height * 0.5f),
                radius = width * 0.45f
            )

            // Browser Window Backplate
            val browserLeft = width * 0.18f
            val browserTop = height * 0.14f + floatOffset
            val browserWidth = width * 0.64f
            val browserHeight = height * 0.68f

            drawRoundRect(
                color = Color(0xFF1E293B),
                topLeft = Offset(browserLeft, browserTop),
                size = Size(browserWidth, browserHeight),
                cornerRadius = CornerRadius(16f, 16f)
            )

            drawRoundRect(
                color = Color(0xFF334155),
                topLeft = Offset(browserLeft, browserTop),
                size = Size(browserWidth, browserHeight),
                cornerRadius = CornerRadius(16f, 16f),
                style = Stroke(width = 2.5f)
            )

            // Browser Top Bar
            drawRoundRect(
                color = Color(0xFF0F172A),
                topLeft = Offset(browserLeft, browserTop),
                size = Size(browserWidth, browserHeight * 0.22f),
                cornerRadius = CornerRadius(16f, 16f)
            )

            // Three Browser Dots (Traffic lights)
            val dotRadius = 4f
            val dotY = browserTop + (browserHeight * 0.11f)
            drawCircle(Color(0xFFEF4444), radius = dotRadius, center = Offset(browserLeft + 18f, dotY))
            drawCircle(Color(0xFFF59E0B), radius = dotRadius, center = Offset(browserLeft + 32f, dotY))
            drawCircle(Color(0xFF10B981), radius = dotRadius, center = Offset(browserLeft + 46f, dotY))

            // Address bar mockup
            drawRoundRect(
                color = Color(0xFF1E293B),
                topLeft = Offset(browserLeft + 64f, browserTop + 6f),
                size = Size(browserWidth - 84f, browserHeight * 0.14f),
                cornerRadius = CornerRadius(8f, 8f)
            )

            // Floating HTML < / > inside browser
            val bracketPath = Path().apply {
                // Left bracket <
                moveTo(browserLeft + browserWidth * 0.28f, browserTop + browserHeight * 0.46f)
                lineTo(browserLeft + browserWidth * 0.18f, browserTop + browserHeight * 0.58f)
                lineTo(browserLeft + browserWidth * 0.28f, browserTop + browserHeight * 0.70f)

                // Right bracket >
                moveTo(browserLeft + browserWidth * 0.72f, browserTop + browserHeight * 0.46f)
                lineTo(browserLeft + browserWidth * 0.82f, browserTop + browserHeight * 0.58f)
                lineTo(browserLeft + browserWidth * 0.72f, browserTop + browserHeight * 0.70f)
            }
            drawPath(
                path = bracketPath,
                color = HtmlOrangePrimary,
                style = Stroke(width = 5.5f, cap = StrokeCap.Round)
            )

            // Slash /
            val slashPath = Path().apply {
                moveTo(browserLeft + browserWidth * 0.56f, browserTop + browserHeight * 0.42f)
                lineTo(browserLeft + browserWidth * 0.44f, browserTop + browserHeight * 0.74f)
            }
            drawPath(
                path = slashPath,
                color = TechCyan,
                style = Stroke(width = 5.5f, cap = StrokeCap.Round)
            )

            // Floating decorative code tag pill (left side)
            drawRoundRect(
                color = Color(0xFF0F172A),
                topLeft = Offset(width * 0.06f, height * 0.40f - floatOffset * 0.8f),
                size = Size(86f, 34f),
                cornerRadius = CornerRadius(12f, 12f)
            )
            drawRoundRect(
                color = HtmlOrangePrimary.copy(alpha = 0.6f),
                topLeft = Offset(width * 0.06f, height * 0.40f - floatOffset * 0.8f),
                size = Size(86f, 34f),
                cornerRadius = CornerRadius(12f, 12f),
                style = Stroke(width = 1.5f)
            )

            // Floating decorative tag pill (right side)
            drawRoundRect(
                color = Color(0xFF0F172A),
                topLeft = Offset(width * 0.78f, height * 0.28f + floatOffset * 0.9f),
                size = Size(82f, 34f),
                cornerRadius = CornerRadius(12f, 12f)
            )
            drawRoundRect(
                color = TechCyan.copy(alpha = 0.6f),
                topLeft = Offset(width * 0.78f, height * 0.28f + floatOffset * 0.9f),
                size = Size(82f, 34f),
                cornerRadius = CornerRadius(12f, 12f),
                style = Stroke(width = 1.5f)
            )
        }

        // Floating text overlay badges
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(
                color = Color(0xFF0F172A).copy(alpha = 0.9f),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, HtmlOrangePrimary.copy(alpha = 0.5f))
            ) {
                Text(
                    text = "<html>",
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = HtmlOrangePrimary,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Surface(
                color = Color(0xFF0F172A).copy(alpha = 0.9f),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, TechCyan.copy(alpha = 0.5f))
            ) {
                Text(
                    text = "<body>",
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = TechCyan,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun HowBrowsersWorkDiagram(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Educational Diagram: How Browsers Work",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(12.dp))

            val steps = listOf(
                Triple("1. User Request", "You enter URL or click link", Icons.Default.Language),
                Triple("2. HTML Download", "Server sends raw text markup", Icons.Default.Description),
                Triple("3. DOM Tree Parsing", "Browser parses tags into nodes", Icons.Default.Code),
                Triple("4. Screen Rendering", "Pixels painted on your display", Icons.Default.Computer)
            )

            steps.forEachIndexed { index, step ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(if (index == 3) SuccessGreen.copy(alpha = 0.2f) else HtmlOrangePrimary.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = step.third,
                            contentDescription = step.first,
                            tint = if (index == 3) SuccessGreen else HtmlOrangePrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = step.first,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = step.second,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                if (index < steps.size - 1) {
                    Box(
                        modifier = Modifier
                            .padding(start = 17.dp)
                            .width(2.dp)
                            .height(18.dp)
                            .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
                    )
                }
            }
        }
    }
}

@Composable
fun DomTreeDiagram(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "HTML Document Tree (DOM Hierarchy)",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = TechCyan
            )
            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                color = Color(0xFF090D16),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = """
  &lt;html&gt; (Root Element)
    ├── &lt;head&gt;
    │     ├── &lt;title&gt;Page Title&lt;/title&gt;
    │     └── &lt;meta charset="UTF-8"&gt;
    └── &lt;body&gt; (Visible to User)
          ├── &lt;header&gt;
          │     └── &lt;h1&gt;Main Heading&lt;/h1&gt;
          ├── &lt;main&gt;
          │     ├── &lt;p&gt;Paragraph text&lt;/p&gt;
          │     └── &lt;img src="..." alt="..."&gt;
          └── &lt;footer&gt;
                └── &lt;p&gt;&copy; 2026 Awiskar Acharya&lt;/p&gt;
                    """.trimIndent(),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = Color(0xFFE2E8F0),
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}

@Composable
fun TagSyntaxDiagram(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Anatomy of an HTML Element",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = HtmlOrangePrimary
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF090D16))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "<p class=\"note\">",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF43F5E),
                    fontSize = 13.sp
                )
                Text(
                    text = "Hello World",
                    fontFamily = FontFamily.Monospace,
                    color = Color.White,
                    fontSize = 13.sp
                )
                Text(
                    text = "</p>",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF43F5E),
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "• <p ...> : Opening Tag with attribute",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "• class=\"note\" : Attribute (name=\"value\")",
                    style = MaterialTheme.typography.bodySmall,
                    color = TechCyan
                )
                Text(
                    text = "• Hello World : Content (visible text)",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "• </p> : Closing Tag (includes slash /)",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFF43F5E)
                )
            }
        }
    }
}

@Composable
fun AchievementBadgeIcon(iconType: String, isUnlocked: Boolean, modifier: Modifier = Modifier) {
    val bgColor = if (isUnlocked) HtmlOrangePrimary.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant
    val tintColor = if (isUnlocked) HtmlOrangePrimary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)

    Box(
        modifier = modifier
            .size(54.dp)
            .clip(CircleShape)
            .background(bgColor)
            .border(
                width = if (isUnlocked) 2.dp else 1.dp,
                color = if (isUnlocked) HtmlOrangePrimary else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        val icon = when (iconType) {
            "FIRST_STEP" -> Icons.Default.Star
            "EXPLORER" -> Icons.Default.Language
            "TAG_MASTER" -> Icons.Default.Code
            "FORM_BUILDER" -> Icons.Default.Description
            "PROJECT_BUILDER" -> Icons.Default.Devices
            "COURSE_COMPLETE" -> Icons.Default.WorkspacePremium
            else -> Icons.Default.Star
        }
        Icon(
            imageVector = icon,
            contentDescription = iconType,
            tint = tintColor,
            modifier = Modifier.size(28.dp)
        )
    }
}

@Composable
fun ConfettiCelebrationCanvas(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "confetti")
    val animVal by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "falling"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        val colors = listOf(
            HtmlOrangePrimary,
            TechCyan,
            SuccessGreen,
            Color(0xFFFBBF24),
            Color(0xFFEC4899),
            Color(0xFF8B5CF6)
        )

        // 35 particles
        for (i in 0 until 35) {
            val color = colors[i % colors.size]
            val xSeed = (i * 37) % 100 / 100f
            val ySeed = (i * 53) % 100 / 100f

            val currentY = ((ySeed + animVal) % 1.0f) * h
            val currentX = (xSeed * w) + (kotlin.math.sin((animVal * 6.28f) + i) * 20f)

            if (i % 2 == 0) {
                drawCircle(
                    color = color.copy(alpha = 0.85f),
                    radius = 4f + (i % 4),
                    center = Offset(currentX, currentY)
                )
            } else {
                drawRect(
                    color = color.copy(alpha = 0.85f),
                    topLeft = Offset(currentX, currentY),
                    size = Size(8f + (i % 4), 6f)
                )
            }
        }
    }
}
