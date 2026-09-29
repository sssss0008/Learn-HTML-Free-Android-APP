package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CodeAttrColor
import com.example.ui.theme.CodeCommentColor
import com.example.ui.theme.CodeEditorBg
import com.example.ui.theme.CodeLineNumber
import com.example.ui.theme.CodeStringColor
import com.example.ui.theme.CodeTagColor
import com.example.ui.theme.HtmlOrangePrimary

/**
 * Highlights basic HTML syntax: tags, attributes, strings, comments.
 */
fun highlightHtmlSyntax(code: String): AnnotatedString {
    return buildAnnotatedString {
        append(code)

        // Highlight comments <!-- ... -->
        val commentRegex = Regex("<!--[\\s\\S]*?-->")
        for (match in commentRegex.findAll(code)) {
            addStyle(
                SpanStyle(color = CodeCommentColor, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic),
                match.range.first,
                match.range.last + 1
            )
        }

        // Highlight tags <tag ...> or </tag>
        val tagRegex = Regex("</?[a-zA-Z0-9!\\-]+")
        for (match in tagRegex.findAll(code)) {
            addStyle(
                SpanStyle(color = CodeTagColor, fontWeight = FontWeight.Bold),
                match.range.first,
                match.range.last + 1
            )
        }

        // Highlight closing >
        val bracketEndRegex = Regex("/?>")
        for (match in bracketEndRegex.findAll(code)) {
            addStyle(
                SpanStyle(color = CodeTagColor, fontWeight = FontWeight.Bold),
                match.range.first,
                match.range.last + 1
            )
        }

        // Highlight attribute names (e.g. href=, src=, class=)
        val attrRegex = Regex("\\s+([a-zA-Z\\-:]+)=")
        for (match in attrRegex.findAll(code)) {
            val group = match.groups[1]
            if (group != null) {
                addStyle(
                    SpanStyle(color = CodeAttrColor),
                    group.range.first,
                    group.range.last + 1
                )
            }
        }

        // Highlight string values "..." or '...'
        val stringRegex = Regex("\"[^\"]*\"|'[^']*'")
        for (match in stringRegex.findAll(code)) {
            addStyle(
                SpanStyle(color = CodeStringColor),
                match.range.first,
                match.range.last + 1
            )
        }
    }
}

class HtmlVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            text = highlightHtmlSyntax(text.text),
            offsetMapping = OffsetMapping.Identity
        )
    }
}

@Composable
fun CodeEditorCard(
    code: String,
    onCodeChange: ((String) -> Unit)? = null,
    onRunCode: (() -> Unit)? = null,
    onResetCode: (() -> Unit)? = null,
    title: String = "HTML Editor",
    isReadOnly: Boolean = false,
    maxHeight: Int = 340,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var isCopied by remember { mutableStateOf(false) }

    val lines = code.lines()
    val lineCount = lines.size.coerceAtLeast(1)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("code_editor_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CodeEditorBg),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155))
    ) {
        Column {
            // Editor Header / Chrome Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0F172A))
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFFEF4444)))
                    Spacer(modifier = Modifier.width(6.dp))
                    Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFFF59E0B)))
                    Spacer(modifier = Modifier.width(6.dp))
                    Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFF10B981)))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = title,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF94A3B8)
                    )
                }

                // Header Action Buttons
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (onResetCode != null) {
                        IconButton(
                            onClick = onResetCode,
                            modifier = Modifier
                                .size(32.dp)
                                .testTag("code_editor_reset_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Reset Code",
                                tint = Color(0xFF94A3B8),
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    IconButton(
                        onClick = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("HTML Code", code)
                            clipboard.setPrimaryClip(clip)
                            isCopied = true
                            Toast.makeText(context, "Code copied to clipboard!", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .size(32.dp)
                            .testTag("code_editor_copy_button")
                    ) {
                        Icon(
                            imageVector = if (isCopied) Icons.Default.Check else Icons.Default.ContentCopy,
                            contentDescription = "Copy Code",
                            tint = if (isCopied) Color(0xFF10B981) else Color(0xFF94A3B8),
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    if (onRunCode != null) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Button(
                            onClick = onRunCode,
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = HtmlOrangePrimary,
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .height(30.dp)
                                .testTag("code_editor_run_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Run",
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Run", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // Code Content Area with Line Numbers
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp, max = maxHeight.dp)
                    .padding(8.dp)
            ) {
                // Line Numbers Column
                Column(
                    modifier = Modifier
                        .padding(end = 10.dp, top = 2.dp)
                        .width(28.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    for (i in 1..lineCount) {
                        Text(
                            text = "$i",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            color = CodeLineNumber
                        )
                    }
                }

                // Vertical Divider
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .heightIn(min = 80.dp)
                        .background(Color(0xFF1E293B))
                )

                // Editable / Viewable Code Text
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                        .horizontalScroll(rememberScrollState())
                ) {
                    if (isReadOnly || onCodeChange == null) {
                        Text(
                            text = highlightHtmlSyntax(code),
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            color = Color(0xFFE2E8F0)
                        )
                    } else {
                        BasicTextField(
                            value = code,
                            onValueChange = onCodeChange,
                            textStyle = TextStyle(
                                fontFamily = FontFamily.Monospace,
                                fontSize = 12.sp,
                                lineHeight = 18.sp,
                                color = Color(0xFFE2E8F0)
                            ),
                            cursorBrush = SolidColor(HtmlOrangePrimary),
                            visualTransformation = HtmlVisualTransformation(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("code_input_field")
                        )
                    }
                }
            }
        }
    }
}
