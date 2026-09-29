package com.example.ui.components

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DesktopWindows
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Tablet
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.ui.theme.HtmlOrangePrimary
import com.example.ui.theme.TechCyan

enum class DeviceViewport {
    MOBILE,
    TABLET,
    DESKTOP
}

@Composable
fun LivePreviewFrame(
    htmlContent: String,
    modifier: Modifier = Modifier,
    heightDp: Int = 300
) {
    var refreshKey by remember { mutableIntStateOf(0) }
    var selectedDevice by remember { mutableStateOf(DeviceViewport.DESKTOP) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("live_preview_frame"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
    ) {
        Column {
            // Browser Address Bar / Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Address Bar Pill
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Secure Connection",
                        tint = Color(0xFF10B981),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "https://learn-html.local/preview",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Device Viewport Toggles
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { selectedDevice = DeviceViewport.MOBILE },
                        modifier = Modifier.size(32.dp).testTag("viewport_mobile_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.PhoneAndroid,
                            contentDescription = "Mobile View",
                            tint = if (selectedDevice == DeviceViewport.MOBILE) HtmlOrangePrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    IconButton(
                        onClick = { selectedDevice = DeviceViewport.TABLET },
                        modifier = Modifier.size(32.dp).testTag("viewport_tablet_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Tablet,
                            contentDescription = "Tablet View",
                            tint = if (selectedDevice == DeviceViewport.TABLET) HtmlOrangePrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    IconButton(
                        onClick = { selectedDevice = DeviceViewport.DESKTOP },
                        modifier = Modifier.size(32.dp).testTag("viewport_desktop_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.DesktopWindows,
                            contentDescription = "Desktop View",
                            tint = if (selectedDevice == DeviceViewport.DESKTOP) HtmlOrangePrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    IconButton(
                        onClick = { refreshKey++ },
                        modifier = Modifier.size(32.dp).testTag("preview_refresh_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh Preview",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            // WebView Rendering Box (constrained if mobile/tablet selected)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(heightDp.dp)
                    .background(Color.White),
                contentAlignment = Alignment.TopCenter
            ) {
                val previewWidthModifier = when (selectedDevice) {
                    DeviceViewport.MOBILE -> Modifier.width(320.dp)
                    DeviceViewport.TABLET -> Modifier.width(500.dp)
                    DeviceViewport.DESKTOP -> Modifier.fillMaxWidth()
                }

                Box(
                    modifier = previewWidthModifier
                        .fillMaxSize()
                        .border(
                            width = if (selectedDevice != DeviceViewport.DESKTOP) 1.dp else 0.dp,
                            color = Color(0xFFCBD5E1)
                        )
                ) {
                    AndroidView(
                        factory = { context ->
                            WebView(context).apply {
                                settings.javaScriptEnabled = true
                                settings.domStorageEnabled = true
                                settings.useWideViewPort = true
                                settings.loadWithOverviewMode = true
                                webChromeClient = WebChromeClient()
                                webViewClient = WebViewClient()
                                loadDataWithBaseURL("https://learn-html.local", htmlContent, "text/html", "UTF-8", null)
                            }
                        },
                        update = { webView ->
                            // Reload when htmlContent changes or refresh is clicked
                            val htmlWithReset = """
                                <!DOCTYPE html>
                                <html>
                                <head>
                                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                  <style>
                                    body { font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, sans-serif; margin: 12px; color: #1e293b; }
                                  </style>
                                </head>
                                <body>
                                  $htmlContent
                                </body>
                                </html>
                            """.trimIndent()
                            webView.loadDataWithBaseURL("https://learn-html.local", htmlWithReset, "text/html", "UTF-8", null)
                        },
                        modifier = Modifier.fillMaxSize().testTag("preview_webview")
                    )
                }
            }
        }
    }
}
