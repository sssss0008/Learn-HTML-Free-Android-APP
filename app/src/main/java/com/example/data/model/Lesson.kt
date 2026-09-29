package com.example.data.model

data class CourseModule(
    val id: Int,
    val title: String,
    val subtitle: String,
    val description: String,
    val iconName: String,
    val estimatedTimeMinutes: Int,
    val lessons: List<Lesson>
)

data class Lesson(
    val id: String,
    val moduleId: Int,
    val lessonNumber: Int,
    val title: String,
    val subtitle: String,
    val estimatedTime: String,
    val explanation: String,
    val keyTakeaways: List<String>,
    val codeExample: String,
    val initialPlaygroundCode: String = codeExample,
    val commonMistake: String? = null,
    val practicePrompt: String? = null,
    val practiceStarterCode: String? = null,
    val practiceSolutionKeywords: List<String> = emptyList(),
    val diagramType: String? = null // e.g., "HOW_BROWSERS_WORK", "DOM_TREE", "TAG_SYNTAX"
)

enum class PracticeType {
    WRITE_CODE,
    FILL_IN_BLANK,
    FIND_THE_ERROR,
    ARRANGE_CODE,
    PREDICT_OUTPUT,
    MATCH_THE_TAG
}

data class PracticeChallenge(
    val id: String,
    val title: String,
    val category: String,
    val difficulty: String,
    val type: PracticeType,
    val instructions: String,
    val promptCode: String,
    val options: List<String> = emptyList(),
    val correctOptionIndex: Int = -1,
    val correctBlankAnswers: List<String> = emptyList(),
    val explanation: String,
    val hint: String
)

data class GuidedProject(
    val id: String,
    val projectNumber: Int,
    val title: String,
    val description: String,
    val difficulty: String,
    val estimatedTime: String,
    val requirements: List<String>,
    val starterHtml: String,
    val solutionPreviewHtml: String,
    val keyTagsUsed: List<String>
)

data class HtmlTagInfo(
    val tag: String,
    val name: String,
    val category: String,
    val purpose: String,
    val syntax: String,
    val example: String,
    val attributes: List<TagAttribute>,
    val commonMistakes: String,
    val accessibilityNotes: String
)

data class TagAttribute(
    val name: String,
    val description: String,
    val isRequired: Boolean = false,
    val example: String = ""
)

data class GlossaryItem(
    val term: String,
    val simpleDefinition: String,
    val technicalDefinition: String,
    val whyItMatters: String,
    val example: String
)

data class CheatSheetItem(
    val category: String,
    val title: String,
    val syntax: String,
    val description: String
)

data class AchievementBadge(
    val id: String,
    val title: String,
    val description: String,
    val iconType: String,
    val isUnlocked: Boolean = false
)
