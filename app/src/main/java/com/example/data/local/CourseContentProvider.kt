package com.example.data.local

import com.example.data.model.AchievementBadge
import com.example.data.model.CheatSheetItem
import com.example.data.model.CourseModule
import com.example.data.model.GlossaryItem
import com.example.data.model.GuidedProject
import com.example.data.model.HtmlTagInfo
import com.example.data.model.Lesson
import com.example.data.model.PracticeChallenge
import com.example.data.model.PracticeType
import com.example.data.model.TagAttribute

object CourseContentProvider {

    val achievements: List<AchievementBadge> = listOf(
        AchievementBadge(
            id = "first_step",
            title = "First Step",
            description = "Completed your first HTML lesson.",
            iconType = "FIRST_STEP"
        ),
        AchievementBadge(
            id = "html_explorer",
            title = "HTML Explorer",
            description = "Completed 10 lessons in the course.",
            iconType = "EXPLORER"
        ),
        AchievementBadge(
            id = "tag_master",
            title = "Tag Master",
            description = "Learned and inspected 25 HTML tags.",
            iconType = "TAG_MASTER"
        ),
        AchievementBadge(
            id = "form_builder",
            title = "Form Builder",
            description = "Completed the Forms & User Inputs module.",
            iconType = "FORM_BUILDER"
        ),
        AchievementBadge(
            id = "project_builder",
            title = "Project Builder",
            description = "Completed your first real website project.",
            iconType = "PROJECT_BUILDER"
        ),
        AchievementBadge(
            id = "html_complete",
            title = "HTML Complete",
            description = "Completed the full HTML curriculum course.",
            iconType = "COURSE_COMPLETE"
        )
    )

    val modules: List<CourseModule> = listOf(
        CourseModule(
            id = 1,
            title = "Module 1 — HTML Fundamentals",
            subtitle = "The building blocks of the web",
            description = "Learn what HTML is, how web browsers translate code, syntax, tags, attributes, and write your first document.",
            iconName = "code",
            estimatedTimeMinutes = 45,
            lessons = listOf(
                Lesson(
                    id = "m1_l1",
                    moduleId = 1,
                    lessonNumber = 1,
                    title = "What is HTML?",
                    subtitle = "The language of the World Wide Web",
                    estimatedTime = "6 min",
                    explanation = "HTML stands for HyperText Markup Language. Think of HTML as the skeleton of every website. Just like a human body needs bones to give it shape, a website needs HTML to structure paragraphs, headings, buttons, and images.\n\nEvery time you open Google, YouTube, or Wikipedia, your web browser downloads HTML code and draws it onto your screen. It is not a programming language with logic or loops; it is a markup language that labels what each piece of text or media is.",
                    keyTakeaways = listOf(
                        "HTML stands for HyperText Markup Language.",
                        "It provides the structural skeleton of every webpage.",
                        "Browsers read HTML from top to bottom and render it visually.",
                        "HTML is 100% free and runs in every modern device."
                    ),
                    codeExample = "<!DOCTYPE html>\n<html>\n  <body>\n    <h1>Hello World!</h1>\n    <p>This is my very first webpage.</p>\n  </body>\n</html>",
                    commonMistake = "Forgetting that HTML is about structure, not visual styling or logic. Don't worry about colors or animations yet — structure comes first!",
                    practicePrompt = "Write an <h1> heading with your name and a <p> paragraph welcoming visitors.",
                    practiceStarterCode = "<h1>Your Name Here</h1>\n<p>Welcome to my website!</p>",
                    practiceSolutionKeywords = listOf("<h1>", "</h1>", "<p>", "</p>"),
                    diagramType = "HOW_BROWSERS_WORK"
                ),
                Lesson(
                    id = "m1_l2",
                    moduleId = 1,
                    lessonNumber = 2,
                    title = "How Websites Work",
                    subtitle = "From server to browser to screen",
                    estimatedTime = "7 min",
                    explanation = "When you type a URL (like google.com) into your browser and press Enter, three things happen:\n\n1. Request: Your browser sends a message across the internet to a server.\n2. Response: The server sends back files: HTML (structure), CSS (style), and JavaScript (interaction).\n3. Parsing & Rendering: The browser reads the HTML line-by-line, builds an internal tree called the DOM (Document Object Model), and paints the pixels on your screen.",
                    keyTakeaways = listOf(
                        "Clients (your phone/laptop) request data; Servers send it back.",
                        "HTML is the raw text document delivered to the browser.",
                        "The browser converts HTML text into the DOM tree.",
                        "Without HTML, browsers have nothing to display."
                    ),
                    codeExample = "<!-- The browser receives this raw text -->\n<h1>The Web in Action</h1>\n<p>HTML is sent over HTTP to your device.</p>",
                    commonMistake = "Thinking that HTML needs special compilers to run. HTML runs natively inside every browser right out of the box!",
                    diagramType = "HOW_BROWSERS_WORK"
                ),
                Lesson(
                    id = "m1_l3",
                    moduleId = 1,
                    lessonNumber = 3,
                    title = "HTML Elements, Tags & Syntax",
                    subtitle = "Opening tags, content, and closing tags",
                    estimatedTime = "8 min",
                    explanation = "An HTML Element is composed of three main parts:\n\n1. An Opening Tag: <tagname>\n2. The Content: The text or media inside\n3. A Closing Tag: </tagname> (notice the forward slash /!)\n\nTogether, <p>Hello world</p> is an entire paragraph element. Some elements are 'empty' or self-closing (like <img> or <br>) because they don't enclose text content.",
                    keyTakeaways = listOf(
                        "Tags are wrapped in angle brackets: < >.",
                        "Closing tags MUST include a forward slash: </ >.",
                        "Element = Opening Tag + Content + Closing Tag.",
                        "Self-closing tags do not need a closing partner (e.g. <br>, <hr>, <img>)."
                    ),
                    codeExample = "<h2>Understanding Tags</h2>\n<p>This paragraph has an opening tag and a closing tag.</p>\n<hr>\n<p>Notice the horizontal line above was created by self-closing tag &lt;hr&gt;.</p>",
                    commonMistake = "Missing the forward slash in closing tags (e.g., writing <p>text<p> instead of <p>text</p>).",
                    practicePrompt = "Add a closing tag to fix the broken heading below.",
                    practiceStarterCode = "<h1>Learn HTML Today",
                    practiceSolutionKeywords = listOf("</h1>"),
                    diagramType = "TAG_SYNTAX"
                ),
                Lesson(
                    id = "m1_l4",
                    moduleId = 1,
                    lessonNumber = 4,
                    title = "HTML Attributes",
                    subtitle = "Giving extra powers to your tags",
                    estimatedTime = "8 min",
                    explanation = "Attributes provide additional information about HTML elements. They are ALWAYS specified in the opening tag, never the closing tag.\n\nAttributes usually come in name=\"value\" pairs. For example, in <a href=\"https://google.com\">Click Here</a>, 'href' is the attribute name, and the web address is its value.\n\nCommon attributes include 'class', 'id', 'src', 'alt', 'href', and 'style'.",
                    keyTakeaways = listOf(
                        "Attributes belong strictly inside the opening tag.",
                        "Format: attribute_name=\"attribute_value\".",
                        "Values should always be enclosed in double quotes.",
                        "Multiple attributes can be added, separated by spaces."
                    ),
                    codeExample = "<p id=\"intro-text\" class=\"highlight\">This paragraph has both an id and a class attribute.</p>\n<a href=\"https://developer.mozilla.org\" target=\"_blank\">Visit MDN Docs</a>",
                    commonMistake = "Placing attributes in the closing tag (e.g., </a href=\"...\"> is invalid!).",
                    practicePrompt = "Add a target=\"_blank\" attribute to the link so it opens in a new tab.",
                    practiceStarterCode = "<a href=\"https://wikipedia.org\">Read Wikipedia</a>",
                    practiceSolutionKeywords = listOf("target=\"_blank\"")
                ),
                Lesson(
                    id = "m1_l5",
                    moduleId = 1,
                    lessonNumber = 5,
                    title = "Nesting Elements & Comments",
                    subtitle = "Hierarchy, indentation, and developer notes",
                    estimatedTime = "7 min",
                    explanation = "HTML elements can be placed inside other elements — this is called nesting. When nesting, you must close tags in the reverse order of how you opened them (Last In, First Out).\n\nHTML Comments allow developers to leave notes that browsers will completely ignore during rendering. The syntax is: <!-- Your comment here -->.",
                    keyTakeaways = listOf(
                        "Proper nesting: <p><b>Bold</b></p> (Correct).",
                        "Improper nesting: <p><b>Bold</p></b> (Error!).",
                        "Indent nested child elements for clean, readable code.",
                        "Comments start with <!-- and end with -->."
                    ),
                    codeExample = "<!-- This is a helpful developer comment -->\n<div>\n  <p>Here is some <b>bold nested</b> text.</p>\n</div>",
                    commonMistake = "Crossing closing tags across parent boundaries (e.g., <div><p></div></p>).",
                    practicePrompt = "Nest an italic tag <i> inside a paragraph <p>.",
                    practiceStarterCode = "<p>This is a secret message.</p>",
                    practiceSolutionKeywords = listOf("<i>", "</i>")
                ),
                Lesson(
                    id = "m1_l6",
                    moduleId = 1,
                    lessonNumber = 6,
                    title = "The HTML Boilerplate",
                    subtitle = "The standard skeleton of every real webpage",
                    estimatedTime = "9 min",
                    explanation = "Every professional webpage starts with the exact same foundation called the boilerplate:\n\n1. <!DOCTYPE html>: Tells browser this is modern HTML5.\n2. <html lang=\"en\">: Root wrapper of entire document.\n3. <head>: Contains metadata, document title, character encoding, and links to stylesheets (invisible to users).\n4. <body>: Contains everything the user actually sees on screen.",
                    keyTakeaways = listOf(
                        "<!DOCTYPE html> must be on the very first line.",
                        "<head> contains metadata and the page <title>.",
                        "<body> contains the visible page content.",
                        "Always declare <meta charset=\"UTF-8\"> for universal character support."
                    ),
                    codeExample = "<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n  <meta charset=\"UTF-8\">\n  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n  <title>My First Real Page</title>\n</head>\n<body>\n  <h1>Welcome!</h1>\n  <p>My boilerplate is working perfectly.</p>\n</body>\n</html>",
                    commonMistake = "Putting visible content like <h1> or <p> inside the <head> tag instead of <body>.",
                    practicePrompt = "Inspect the boilerplate and add an <h2> subhead inside the <body>.",
                    practiceStarterCode = "<!DOCTYPE html>\n<html>\n<head>\n  <title>Practice</title>\n</head>\n<body>\n  <h1>Main Title</h1>\n</body>\n</html>",
                    practiceSolutionKeywords = listOf("<h2>", "</h2>"),
                    diagramType = "DOM_TREE"
                )
            )
        ),
        CourseModule(
            id = 2,
            title = "Module 2 — Text Formatting",
            subtitle = "Styling and structuring typography",
            description = "Master headings (h1 to h6), paragraphs, line breaks, bold, italic, quotations, superscript, subscript, and code formatting.",
            iconName = "text",
            estimatedTimeMinutes = 50,
            lessons = listOf(
                Lesson(
                    id = "m2_l1",
                    moduleId = 2,
                    lessonNumber = 1,
                    title = "Headings (h1 to h6)",
                    subtitle = "Hierarchy and document outline",
                    estimatedTime = "6 min",
                    explanation = "HTML offers six levels of section headings from <h1> (most important, largest) down to <h6> (least important, smallest).\n\nHeadings are critical for SEO (Search Engine Optimization) and accessibility screen readers. You should only have one primary <h1> per webpage representing the main topic.",
                    keyTakeaways = listOf(
                        "<h1> is the main topic; only use one per page.",
                        "Never skip heading levels (e.g. going from <h1> directly to <h3>).",
                        "Headings structure the page logically for search engines.",
                        "Do not use headings just to make text visually bigger."
                    ),
                    codeExample = "<h1>Article Main Title (h1)</h1>\n<h2>Major Section (h2)</h2>\n<h3>Subtopic Details (h3)</h3>\n<h4>Minor Point (h4)</h4>",
                    commonMistake = "Using multiple <h1> tags on a single page, which confuses search engine crawlers."
                ),
                Lesson(
                    id = "m2_l2",
                    moduleId = 2,
                    lessonNumber = 2,
                    title = "Paragraphs, Line Breaks & HR",
                    subtitle = "Structuring body copy and breaks",
                    estimatedTime = "6 min",
                    explanation = "Paragraphs are created with the <p> element. Browsers automatically add vertical margin before and after every paragraph.\n\nInside a paragraph, browsers ignore multiple consecutive spaces and regular line returns! To force a line break without starting a new paragraph, use <br>. To insert a thematic horizontal dividing rule, use <hr>.",
                    keyTakeaways = listOf(
                        "<p> represents a block of body text.",
                        "Browsers collapse multiple spaces and newlines into a single space.",
                        "<br> forces a line break inside text.",
                        "<hr> draws a horizontal thematic divider rule."
                    ),
                    codeExample = "<p>First line of poem.<br>Second line directly below.</p>\n<hr>\n<p>A new section begins here separated by an hr rule.</p>",
                    commonMistake = "Using multiple <br><br><br> tags to create spacing instead of using proper CSS or paragraph elements."
                ),
                Lesson(
                    id = "m2_l3",
                    moduleId = 2,
                    lessonNumber = 3,
                    title = "Bold, Strong, Italic & Em",
                    subtitle = "Visual vs Semantic emphasis",
                    estimatedTime = "7 min",
                    explanation = "In modern HTML, semantic meaning matters:\n\n• <b> vs <strong>: <b> makes text bold purely visually. <strong> indicates strong importance; screen readers announce it with urgent emphasis!\n• <i> vs <em>: <i> makes text italic (used for book titles, technical terms). <em> indicates linguistic stress and emphasis.",
                    keyTakeaways = listOf(
                        "Use <strong> when text has serious importance or urgency.",
                        "Use <em> for verbal conversational stress.",
                        "Use <b> and <i> when styling is purely visual without urgent importance.",
                        "Screen readers alter their speech tone for <strong> and <em>."
                    ),
                    codeExample = "<p>Warning: <strong>Do not touch the live wire!</strong></p>\n<p>I <em>really</em> love building websites with HTML.</p>",
                    commonMistake = "Using <b> everywhere instead of <strong> when conveying critical user alerts."
                ),
                Lesson(
                    id = "m2_l4",
                    moduleId = 2,
                    lessonNumber = 4,
                    title = "Mark, Small, Del, Ins, Sub & Sup",
                    subtitle = "Specialized typographical elements",
                    estimatedTime = "7 min",
                    explanation = "HTML has rich elements for specific editorial use cases:\n\n• <mark>: Highlights text with a yellow background.\n• <small>: Smaller text for disclaimers and copyrights.\n• <del> & <ins>: Strikethrough for deleted text and underline for inserted text.\n• <sub> & <sup>: Subscript (H<sub>2</sub>O) and superscript (E = mc<sup>2</sup>).",
                    keyTakeaways = listOf(
                        "<mark> indicates highlighted reference text.",
                        "<small> is perfect for copyright footers and fine print.",
                        "<del> and <ins> represent document revisions and price discounts.",
                        "<sub> lowers baseline; <sup> raises baseline."
                    ),
                    codeExample = "<p>Special Sale: <del>$99</del> <ins>$49</ins>!</p>\n<p>Water formula is H<sub>2</sub>O.</p>\n<p>Einstein discovered E = mc<sup>2</sup>.</p>\n<p><mark>Search term match</mark></p>",
                    commonMistake = "Confusing <sub> (subscript, down) with <sup> (superscript, up)."
                ),
                Lesson(
                    id = "m2_l5",
                    moduleId = 2,
                    lessonNumber = 5,
                    title = "Quotations, Abbreviations & Code",
                    subtitle = "Citing and displaying technical text",
                    estimatedTime = "7 min",
                    explanation = "When citing external sources or presenting code snippets:\n\n• <blockquote>: For multi-line quotation blocks (indented by default).\n• <q>: For short inline quotes (browser adds quote marks automatically).\n• <abbr title=\"...\">: Explains acronyms when hovered.\n• <code> and <pre>: Displays monospace code with preserved whitespace.",
                    keyTakeaways = listOf(
                        "<blockquote> is for long quotes; <q> is for inline quotes.",
                        "<abbr title=\"HyperText Markup Language\">HTML</abbr> provides hover definitions.",
                        "<code> styles inline code phrases.",
                        "<pre> preserves all spaces and line breaks exactly as typed."
                    ),
                    codeExample = "<blockquote>The journey of a thousand miles begins with a single step.</blockquote>\n<p>Press <abbr title=\"Control\">Ctrl</abbr> + S to save.</p>\n<pre>\nfunction sayHi() {\n  return 'Hello!';\n}\n</pre>",
                    commonMistake = "Trying to write multi-line code inside standard <p> tags without using <pre>."
                )
            )
        ),
        CourseModule(
            id = 3,
            title = "Module 3 — Links & Navigation",
            subtitle = "Connecting pages across the web",
            description = "Anchor tags, relative vs absolute URLs, opening new tabs, email links, phone call links, and page jumps.",
            iconName = "link",
            estimatedTimeMinutes = 40,
            lessons = listOf(
                Lesson(
                    id = "m3_l1",
                    moduleId = 3,
                    lessonNumber = 1,
                    title = "The Anchor Tag <a>",
                    subtitle = "The hyperlink that created the web",
                    estimatedTime = "7 min",
                    explanation = "The <a> element (short for anchor) creates hyperlinks to other webpages, files, locations within the same page, email addresses, or phone numbers.\n\nThe most important attribute is 'href' (hypertext reference), which specifies the destination URL.",
                    keyTakeaways = listOf(
                        "<a> creates clickable links.",
                        "The href attribute specifies the destination URL.",
                        "The text between <a> and </a> is what the user clicks.",
                        "Always write descriptive link text (avoid 'click here')."
                    ),
                    codeExample = "<p>Visit <a href=\"https://www.w3.org\">W3C Web Standards</a> to learn about open standards.</p>",
                    commonMistake = "Using vague link text like 'click here' instead of descriptive labels for screen readers."
                ),
                Lesson(
                    id = "m3_l2",
                    moduleId = 3,
                    lessonNumber = 2,
                    title = "Absolute vs Relative URLs",
                    subtitle = "Navigating internal and external destinations",
                    estimatedTime = "8 min",
                    explanation = "An Absolute URL includes the full protocol and domain name (e.g., https://example.com/about.html). It points to an exact external location anywhere on the internet.\n\nA Relative URL points to a file within your own website relative to the current file (e.g., /about.html or ./images/logo.png). This allows your site to work locally without needing a live domain.",
                    keyTakeaways = listOf(
                        "Absolute URLs include https:// and point to external domains.",
                        "Relative URLs point to local files in your project directory.",
                        "Use target=\"_blank\" with rel=\"noopener\" to open in a new tab.",
                        "rel=\"noopener\" protects security when opening new tabs."
                    ),
                    codeExample = "<!-- External link in new tab -->\n<a href=\"https://github.com\" target=\"_blank\" rel=\"noopener noreferrer\">GitHub</a>\n\n<!-- Internal local page link -->\n<a href=\"/contact.html\">Contact Us</a>",
                    commonMistake = "Forgetting rel=\"noopener noreferrer\" when using target=\"_blank\"."
                ),
                Lesson(
                    id = "m3_l3",
                    moduleId = 3,
                    lessonNumber = 3,
                    title = "Email, Phone & Page Anchors",
                    subtitle = "Direct contact and smooth page jumping",
                    estimatedTime = "7 min",
                    explanation = "HTML links can trigger native device actions:\n\n• mailto: opens the user's default email client.\n• tel: launches the phone dialer on mobile devices.\n• #id jumps directly to any element on the page that has that matching id.",
                    keyTakeaways = listOf(
                        "href=\"mailto:someone@example.com\" opens email app.",
                        "href=\"tel:+1234567890\" opens telephone dialer.",
                        "href=\"#section-two\" jumps straight to an element with id=\"section-two\".",
                        "href=\"#top\" jumps back to the top of the webpage."
                    ),
                    codeExample = "<a href=\"mailto:support@learnhtml.local\">Send Us an Email</a><br>\n<a href=\"tel:+15551234567\">Call Support</a><br>\n<a href=\"#reviews\">Jump to Customer Reviews</a>\n\n<div style=\"margin-top: 40px;\" id=\"reviews\">\n  <h3>Customer Reviews</h3>\n  <p>5/5 Stars - Best HTML course!</p>\n</div>",
                    commonMistake = "Forgetting the # symbol when linking to an internal element ID (e.g. href=\"reviews\" instead of href=\"#reviews\")."
                )
            )
        ),
        CourseModule(
            id = 4,
            title = "Module 4 — Images & Media",
            subtitle = "Visual storytelling on the web",
            description = "Master the <img> element, src, alt attributes for accessibility, width, height, aspect ratios, and image links.",
            iconName = "image",
            estimatedTimeMinutes = 35,
            lessons = listOf(
                Lesson(
                    id = "m4_l1",
                    moduleId = 4,
                    lessonNumber = 1,
                    title = "The <img> Element & Attributes",
                    subtitle = "Embedding visuals and photographs",
                    estimatedTime = "7 min",
                    explanation = "The <img> element embeds images into an HTML page. It is an empty element, meaning it has NO closing tag!\n\nTwo attributes are mandatory:\n1. src: Specifies the path or web URL to the image file.\n2. alt: Provides alternative text describing what is in the image.",
                    keyTakeaways = listOf(
                        "<img> is self-closing: no </img> closing tag.",
                        "src attribute defines image location.",
                        "alt attribute is required for accessibility and SEO.",
                        "Set width and height attributes to prevent layout shifts."
                    ),
                    codeExample = "<img src=\"https://images.unsplash.com/photo-1542838132-92c53300491e?w=500\" alt=\"Modern computer monitor displaying clean code\" width=\"320\" height=\"200\">",
                    commonMistake = "Omitting the alt attribute. Screen readers will read out the ugly file name if alt is missing!",
                    practicePrompt = "Add an alt attribute describing the image below.",
                    practiceStarterCode = "<img src=\"logo.png\" width=\"120\">",
                    practiceSolutionKeywords = listOf("alt=\"")
                ),
                Lesson(
                    id = "m4_l2",
                    moduleId = 4,
                    lessonNumber = 2,
                    title = "Clickable Images & Figure Tags",
                    subtitle = "Semantic captions and image links",
                    estimatedTime = "7 min",
                    explanation = "To make an image clickable, simply wrap the <img> tag inside an <a> anchor tag!\n\nTo semantically associate a caption with an image, use <figure> and <figcaption>. This signals to browsers and screen readers that the caption specifically belongs to that figure.",
                    keyTakeaways = listOf(
                        "Wrap <img> in <a> to make it a clickable image link.",
                        "<figure> wraps self-contained visual media.",
                        "<figcaption> provides an official caption for the media.",
                        "This semantic pairing boosts accessibility and page structure."
                    ),
                    codeExample = "<figure>\n  <a href=\"https://unsplash.com\">\n    <img src=\"https://picsum.photos/300/180\" alt=\"Scenic sunset over mountain peaks\">\n  </a>\n  <figcaption>Photo 1: High mountain peaks at sunset.</figcaption>\n</figure>",
                    commonMistake = "Using a plain <p> below an image instead of <figcaption> when a caption is needed."
                )
            )
        ),
        CourseModule(
            id = 5,
            title = "Module 5 — Lists",
            subtitle = "Organizing data sequentially and bulleted",
            description = "Unordered lists, ordered lists, list items, description lists, and nested menus.",
            iconName = "list",
            estimatedTimeMinutes = 35,
            lessons = listOf(
                Lesson(
                    id = "m5_l1",
                    moduleId = 5,
                    lessonNumber = 1,
                    title = "Unordered & Ordered Lists",
                    subtitle = "Bullets (<ul>) vs Numbers (<ol>)",
                    estimatedTime = "7 min",
                    explanation = "When presenting items:\n• <ul>: Creates an Unordered List where order does not matter (bullet points by default).\n• <ol>: Creates an Ordered List where sequence matters (numbers 1, 2, 3... by default).\n\nBoth types contain <li> (List Item) elements inside them.",
                    keyTakeaways = listOf(
                        "<ul> is for bulleted items; <ol> is for numbered steps.",
                        "Every child element must be an <li>.",
                        "You can change numbering style on <ol> using type=\"A\" or type=\"I\".",
                        "The start attribute specifies where numbered lists begin."
                    ),
                    codeExample = "<h3>Shopping List (Unordered)</h3>\n<ul>\n  <li>Apples</li>\n  <li>Bananas</li>\n  <li>Almond milk</li>\n</ul>\n\n<h3>Recipe Steps (Ordered)</h3>\n<ol>\n  <li>Preheat oven to 350F</li>\n  <li>Mix dry ingredients</li>\n  <li>Bake for 25 minutes</li>\n</ol>",
                    commonMistake = "Putting raw text or <p> tags directly inside <ul> without wrapping them in <li>."
                ),
                Lesson(
                    id = "m5_l2",
                    moduleId = 5,
                    lessonNumber = 2,
                    title = "Nested Lists & Description Lists",
                    subtitle = "Complex hierarchies and term definitions",
                    estimatedTime = "8 min",
                    explanation = "You can nest lists inside lists to create multi-level menus and outlines. The nested list must be placed INSIDE an <li> tag.\n\nA Description List (<dl>) is used for glossaries or term-definition pairs:\n• <dt>: Description Term\n• <dd>: Description Details",
                    keyTakeaways = listOf(
                        "Nested lists must live inside an <li> element.",
                        "<dl> is the parent for term-definition lists.",
                        "<dt> holds the term; <dd> holds the definition.",
                        "Navigation bars on websites are almost always built using <ul> and <li>."
                    ),
                    codeExample = "<dl>\n  <dt>HTML</dt>\n  <dd>HyperText Markup Language</dd>\n  <dt>CSS</dt>\n  <dd>Cascading Style Sheets</dd>\n</dl>",
                    commonMistake = "Placing a sub-list between <li> items rather than inside one of them."
                )
            )
        ),
        CourseModule(
            id = 6,
            title = "Module 6 — Tables",
            subtitle = "Displaying tabular data clearly",
            description = "Table structure, rows, data cells, headers, captions, colspan, rowspan, and semantic table sections.",
            iconName = "table",
            estimatedTimeMinutes = 40,
            lessons = listOf(
                Lesson(
                    id = "m6_l1",
                    moduleId = 6,
                    lessonNumber = 1,
                    title = "Table Structure & Cells",
                    subtitle = "Rows (<tr>), Headers (<th>), and Data (<td>)",
                    estimatedTime = "8 min",
                    explanation = "HTML tables are used strictly for displaying tabular data (like spreadsheets, schedules, or pricing grids). NEVER use tables to layout page designs!\n\n• <table>: Wrapper for the whole table.\n• <tr>: Table Row.\n• <th>: Table Header cell (bold and centered by default).\n• <td>: Table Data cell (regular cell content).",
                    keyTakeaways = listOf(
                        "Only use tables for tabular data, never page layouts.",
                        "<tr> defines a horizontal row.",
                        "<th> defines a header cell; <td> defines a standard data cell.",
                        "Use the <caption> element to provide a clear title for the table."
                    ),
                    codeExample = "<table border=\"1\" cellpadding=\"8\">\n  <caption>Student Test Scores</caption>\n  <tr>\n    <th>Name</th>\n    <th>Subject</th>\n    <th>Score</th>\n  </tr>\n  <tr>\n    <td>Awiskar</td>\n    <td>Web Tech</td>\n    <td>98%</td>\n  </tr>\n  <tr>\n    <td>Sarah</td>\n    <td>Database</td>\n    <td>95%</td>\n  </tr>\n</table>",
                    commonMistake = "Forgetting to wrap cells inside a <tr> row tag."
                ),
                Lesson(
                    id = "m6_l2",
                    moduleId = 6,
                    lessonNumber = 2,
                    title = "Colspan, Rowspan & Sections",
                    subtitle = "Merging cells and semantic tables",
                    estimatedTime = "8 min",
                    explanation = "You can merge cells across columns or rows using attributes:\n• colspan=\"2\": Merges cell across 2 columns horizontally.\n• rowspan=\"2\": Merges cell across 2 rows vertically.\n\nProfessional tables use semantic sections: <thead> for headers, <tbody> for body rows, and <tfoot> for summaries.",
                    keyTakeaways = listOf(
                        "colspan spans across multiple columns.",
                        "rowspan spans across multiple rows.",
                        "<thead>, <tbody>, and <tfoot> segment tables cleanly.",
                        "Semantic table tags help accessibility software read tables aloud."
                    ),
                    codeExample = "<table border=\"1\" cellpadding=\"6\">\n  <thead>\n    <tr>\n      <th>Item</th>\n      <th>Qty</th>\n      <th>Price</th>\n    </tr>\n  </thead>\n  <tbody>\n    <tr>\n      <td>HTML Course</td>\n      <td>1</td>\n      <td>$0.00 (Free)</td>\n    </tr>\n  </tbody>\n  <tfoot>\n    <tr>\n      <td colspan=\"2\">Total Cost</td>\n      <td>$0.00</td>\n    </tr>\n  </tfoot>\n</table>",
                    commonMistake = "Using colspan with a number greater than the total columns in the table, breaking the grid."
                )
            )
        ),
        CourseModule(
            id = 7,
            title = "Module 7 — Forms & User Inputs",
            subtitle = "Collecting interactive user submissions",
            description = "Form tags, text, email, password, radio buttons, checkboxes, dropdowns, textarea, labels, and validation.",
            iconName = "form",
            estimatedTimeMinutes = 55,
            lessons = listOf(
                Lesson(
                    id = "m7_l1",
                    moduleId = 7,
                    lessonNumber = 1,
                    title = "The <form> Tag & Text Inputs",
                    subtitle = "The container and standard text entry",
                    estimatedTime = "8 min",
                    explanation = "The <form> element is the interactive container where users enter information to submit to a server.\n\nTwo critical attributes on <form>:\n• action: URL where form data is sent.\n• method: Usually 'POST' (for secure data) or 'GET' (for searches).\n\nThe <input type=\"text\"> tag allows users to enter single-line text.",
                    keyTakeaways = listOf(
                        "<form> wraps all interactive inputs.",
                        "<input> is self-closing.",
                        "The 'name' attribute identifies the field when submitted.",
                        "The 'placeholder' attribute shows temporary hint text."
                    ),
                    codeExample = "<form action=\"/submit\" method=\"POST\">\n  <label for=\"username\">Username:</label><br>\n  <input type=\"text\" id=\"username\" name=\"username\" placeholder=\"e.g. dev_coder\">\n  <br><br>\n  <button type=\"submit\">Submit Form</button>\n</form>",
                    commonMistake = "Forgetting to give inputs a 'name' attribute, which prevents the data from being sent when submitted."
                ),
                Lesson(
                    id = "m7_l2",
                    moduleId = 7,
                    lessonNumber = 2,
                    title = "Labels & Accessibility",
                    subtitle = "Connecting text to inputs for all users",
                    estimatedTime = "8 min",
                    explanation = "Never put inputs on a page without <label> tags! The <label> element makes forms accessible to screen readers, AND when a user clicks the label text, the associated input field is automatically focused.\n\nTo connect them, set the <label for=\"xyz\"> matching the <input id=\"xyz\">.",
                    keyTakeaways = listOf(
                        "Always pair every input with a <label>.",
                        "The 'for' attribute on the label must match the 'id' on the input.",
                        "Clicking a label activates the connected input checkbox or cursor.",
                        "Essential requirement for web accessibility compliance."
                    ),
                    codeExample = "<form>\n  <label for=\"email-addr\">Email Address:</label><br>\n  <input type=\"email\" id=\"email-addr\" name=\"email\" required>\n</form>",
                    commonMistake = "Mismatched values between label 'for' and input 'id'."
                ),
                Lesson(
                    id = "m7_l3",
                    moduleId = 7,
                    lessonNumber = 3,
                    title = "Passwords, Numbers, Radio & Checkboxes",
                    subtitle = "Diverse input types for every scenario",
                    estimatedTime = "9 min",
                    explanation = "HTML5 includes built-in specialized inputs:\n\n• type=\"password\": Masks entered characters.\n• type=\"number\": Restricts input to digits.\n• type=\"radio\": Allows selecting ONE option from a group (must share the same 'name').\n• type=\"checkbox\": Allows selecting multiple independent checkboxes.",
                    keyTakeaways = listOf(
                        "type=\"password\" automatically hides characters with dots.",
                        "Radio buttons sharing the same 'name' form a mutually exclusive group.",
                        "Checkboxes allow multiple simultaneous choices.",
                        "Use the 'checked' attribute to pre-select an option."
                    ),
                    codeExample = "<!-- Radio group: only 1 can be chosen -->\n<p>Choose Experience Level:</p>\n<input type=\"radio\" id=\"beg\" name=\"exp\" value=\"beginner\" checked>\n<label for=\"beg\">Beginner</label><br>\n<input type=\"radio\" id=\"adv\" name=\"exp\" value=\"advanced\">\n<label for=\"adv\">Advanced</label><br><br>\n\n<!-- Checkbox -->\n<input type=\"checkbox\" id=\"terms\" name=\"terms\" required>\n<label for=\"terms\">I accept the free terms</label>",
                    commonMistake = "Giving radio buttons different names, which prevents them from behaving as a single group."
                ),
                Lesson(
                    id = "m7_l4",
                    moduleId = 7,
                    lessonNumber = 4,
                    title = "Dropdowns (<select>) & <textarea>",
                    subtitle = "Multi-line text and selectable lists",
                    estimatedTime = "8 min",
                    explanation = "When text inputs aren't enough:\n\n• <select>: Creates a dropdown menu containing <option> tags inside.\n• <textarea>: Creates a resizable multi-line text input field (perfect for messages, comments, and reviews).\n• <button type=\"submit\">: Triggers the submission.",
                    keyTakeaways = listOf(
                        "<select> creates dropdown menus.",
                        "<option> defines each choice in the dropdown.",
                        "<textarea> supports multi-line text; has a closing tag </textarea>.",
                        "Use rows and cols attributes to set textarea initial dimensions."
                    ),
                    codeExample = "<label for=\"country\">Select Country:</label><br>\n<select id=\"country\" name=\"country\">\n  <option value=\"us\">United States</option>\n  <option value=\"np\">Nepal</option>\n  <option value=\"uk\">United Kingdom</option>\n</select><br><br>\n\n<label for=\"msg\">Your Message:</label><br>\n<textarea id=\"msg\" name=\"message\" rows=\"4\" cols=\"30\" placeholder=\"Type here...\"></textarea>",
                    commonMistake = "Writing content inside <textarea value=\"...\"> instead of between <textarea>Content</textarea>."
                )
            )
        ),
        CourseModule(
            id = 8,
            title = "Module 8 — Semantic HTML",
            subtitle = "Writing meaningful, future-proof markup",
            description = "Learn header, nav, main, section, article, aside, footer, time, and why semantic markup matters for SEO and accessibility.",
            iconName = "semantic",
            estimatedTimeMinutes = 45,
            lessons = listOf(
                Lesson(
                    id = "m8_l1",
                    moduleId = 8,
                    lessonNumber = 1,
                    title = "The Power of Semantic Tags",
                    subtitle = "Meaning over generic <div> tags",
                    estimatedTime = "8 min",
                    explanation = "A semantic element clearly describes its meaning to both the browser and the developer. Non-semantic elements like <div> and <span> tell you nothing about their content.\n\nSemantic HTML creates a meaningful outline that search engine spiders and screen readers can understand instantly, boosting rankings and usability.",
                    keyTakeaways = listOf(
                        "Semantic elements describe their exact role.",
                        "Replaces the bad practice of 'div soup' (<div> inside <div>).",
                        "Makes code readable, maintainable, and accessible.",
                        "Core semantic layout tags: <header>, <nav>, <main>, <section>, <article>, <aside>, <footer>."
                    ),
                    codeExample = "<header>\n  <h1>Tech News Today</h1>\n  <nav>\n    <a href=\"#home\">Home</a> | <a href=\"#news\">Articles</a>\n  </nav>\n</header>\n\n<main>\n  <article>\n    <h2>HTML5 Adoption Reaches 100%</h2>\n    <p>Modern semantic web elements rule development.</p>\n  </article>\n</main>\n\n<footer>\n  <p>&copy; 2026 Awiskar Acharya</p>\n</footer>",
                    commonMistake = "Using <div class=\"header\"> instead of the native semantic <header> tag."
                ),
                Lesson(
                    id = "m8_l2",
                    moduleId = 8,
                    lessonNumber = 2,
                    title = "<section>, <article> & <aside>",
                    subtitle = "Segmenting content logically",
                    estimatedTime = "8 min",
                    explanation = "• <article>: A self-contained composition that makes sense on its own (like a blog post, news story, or forum reply).\n• <section>: A standalone thematic grouping of content, usually with a heading.\n• <aside>: Content indirectly related to main content (like a sidebar, glossary callout, or author bio).",
                    keyTakeaways = listOf(
                        "<article> should be independently distributable.",
                        "<section> groups related content within a page.",
                        "<aside> is for secondary sidebars or related links.",
                        "Combine with <time datetime=\"...\"> for readable dates."
                    ),
                    codeExample = "<section id=\"blog-posts\">\n  <h2>Latest Posts</h2>\n  <article>\n    <h3>Why Learn HTML First?</h3>\n    <p>Published on <time datetime=\"2026-09-28\">Sept 28, 2026</time></p>\n    <p>HTML is the true foundation of fullstack engineering.</p>\n  </article>\n</section>\n\n<aside>\n  <h4>About the Author</h4>\n  <p>Awiskar Acharya is a developer passionate about free education.</p>\n</aside>",
                    commonMistake = "Using <article> for tiny unrelated buttons or using <section> without a heading."
                )
            )
        ),
        CourseModule(
            id = 9,
            title = "Module 9 — Multimedia & Embeds",
            subtitle = "Audio, video, iframes, and media streams",
            description = "HTML5 native <audio>, <video>, controls, sources, posters, and <iframe> embedding.",
            iconName = "media",
            estimatedTimeMinutes = 35,
            lessons = listOf(
                Lesson(
                    id = "m9_l1",
                    moduleId = 9,
                    lessonNumber = 1,
                    title = "Native <audio> & <video>",
                    subtitle = "No third-party plugins required",
                    estimatedTime = "8 min",
                    explanation = "Before HTML5, playing audio or video required third-party plugins like Flash. Today, browsers play media natively via <audio> and <video>!\n\nCrucial attributes:\n• controls: Displays play, pause, and volume buttons.\n• autoplay & muted: Starts playing automatically (browsers require 'muted' for autoplay).\n• poster: Displays a preview thumbnail before video plays.",
                    keyTakeaways = listOf(
                        "Always include the 'controls' attribute so users can manage playback.",
                        "Use <source src=\"...\" type=\"...\"> for multiple format fallbacks.",
                        "The 'poster' attribute sets the video cover image.",
                        "Never autoplay unmuted audio — it creates a terrible user experience!"
                    ),
                    codeExample = "<video width=\"320\" height=\"180\" controls poster=\"https://picsum.photos/320/180\">\n  <source src=\"https://www.w3schools.com/html/mov_bbb.mp4\" type=\"video/mp4\">\n  Your browser does not support HTML5 video.\n</video>",
                    commonMistake = "Forgetting the 'controls' attribute, leaving users with an invisible or unplayable media box."
                ),
                Lesson(
                    id = "m9_l2",
                    moduleId = 9,
                    lessonNumber = 2,
                    title = "The <iframe> Element",
                    subtitle = "Embedding maps, YouTube, and external pages",
                    estimatedTime = "7 min",
                    explanation = "An <iframe> (Inline Frame) embeds another HTML document inside the current webpage. It is commonly used for Google Maps, YouTube videos, and interactive widgets.",
                    keyTakeaways = listOf(
                        "<iframe> embeds a webpage inside a webpage.",
                        "Use 'width' and 'height' to define frame size.",
                        "Always include a 'title' attribute for screen reader accessibility.",
                        "Use 'loading=\"lazy\"' to speed up initial page load time."
                    ),
                    codeExample = "<iframe width=\"100%\" height=\"200\" src=\"https://example.com\" title=\"Example Embedded Frame\" loading=\"lazy\"></iframe>",
                    commonMistake = "Omitting the 'title' attribute on an iframe, failing WCAG accessibility guidelines."
                )
            )
        ),
        CourseModule(
            id = 10,
            title = "Module 10 — HTML5 & Modern Web",
            subtitle = "Modern APIs, Canvas, SVG & Responsive Tags",
            description = "Modern HTML5 doctype, viewport meta tags for mobile devices, inline SVG graphics, canvas, and data attributes.",
            iconName = "html5",
            estimatedTimeMinutes = 40,
            lessons = listOf(
                Lesson(
                    id = "m10_l1",
                    moduleId = 10,
                    lessonNumber = 1,
                    title = "Responsive Meta Viewport & SVG",
                    subtitle = "Mobile-friendly scaling and vector graphics",
                    estimatedTime = "8 min",
                    explanation = "Every modern website must look great on smartphones. The viewport meta tag tells mobile browsers to render at the device's physical screen width rather than zooming out like a desktop:\n\n<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n\nAdditionally, SVG (Scalable Vector Graphics) allows crisp vector icons and shapes directly inside HTML code.",
                    keyTakeaways = listOf(
                        "Viewport meta tag is mandatory for mobile responsiveness.",
                        "SVG never blurs or loses quality on high-density Retina screens.",
                        "<svg> code lives right inside standard HTML.",
                        "Data attributes (data-*) let you store custom data on elements."
                    ),
                    codeExample = "<svg width=\"100\" height=\"100\">\n  <circle cx=\"50\" cy=\"50\" r=\"40\" stroke=\"#F97316\" stroke-width=\"4\" fill=\"#FED7AA\" />\n</svg>\n<button data-course-id=\"html101\" data-difficulty=\"easy\">Start Course</button>",
                    commonMistake = "Forgetting the viewport meta tag, causing mobile devices to display tiny zoomed-out desktop text."
                )
            )
        ),
        CourseModule(
            id = 11,
            title = "Module 11 — Web Accessibility (a11y)",
            subtitle = "Making the web usable for everyone",
            description = "Semantic a11y, descriptive alt text, accessible forms, keyboard navigation, and ARIA introduction.",
            iconName = "accessibility",
            estimatedTimeMinutes = 40,
            lessons = listOf(
                Lesson(
                    id = "m11_l1",
                    moduleId = 11,
                    lessonNumber = 1,
                    title = "Building for Everyone",
                    subtitle = "Good HTML is usable for all humans",
                    estimatedTime = "7 min",
                    explanation = "Good HTML is not only about making a page work. It's about making the web usable for everyone, including people who rely on screen readers, keyboard-only navigation, or high-contrast displays.\n\nAccessibility is not an afterthought; it is built directly into how you choose HTML tags.",
                    keyTakeaways = listOf(
                        "Use native buttons and links so keyboards can tab through them.",
                        "Provide descriptive alt text for images that convey information.",
                        "Maintain a strict, unbroken heading hierarchy (h1 -> h2 -> h3).",
                        "Ensure color contrast is strong and readable."
                    ),
                    codeExample = "<!-- Accessible button with clear label -->\n<button type=\"button\" aria-label=\"Close modal dialog\">\n  &times;\n</button>",
                    commonMistake = "Using a <div> with a click handler instead of a real <button>, which breaks keyboard navigation for disabled users."
                )
            )
        ),
        CourseModule(
            id = 12,
            title = "Module 12 — SEO HTML",
            subtitle = "Ranking high on Google and search engines",
            description = "Title tags, meta descriptions, canonical URLs, semantic structure, and Open Graph social sharing tags.",
            iconName = "seo",
            estimatedTimeMinutes = 35,
            lessons = listOf(
                Lesson(
                    id = "m12_l1",
                    moduleId = 12,
                    lessonNumber = 1,
                    title = "Meta Tags & Search Visibility",
                    subtitle = "How Google reads your HTML",
                    estimatedTime = "7 min",
                    explanation = "Search engine crawlers rely on your <head> tags to understand what your page is about:\n\n• <title>: Appears in search results and browser tab.\n• <meta name=\"description\" content=\"...\">: The snippet shown beneath your title in Google search results.\n• Open Graph tags (<meta property=\"og:title\">): Controls the card preview when your link is shared on Twitter, LinkedIn, or WhatsApp.",
                    keyTakeaways = listOf(
                        "<title> should be 50-60 characters and descriptive.",
                        "Meta description should be 150-160 characters summarizing the page.",
                        "Open Graph tags power social media card link previews.",
                        "Semantic headings give search engines an outline of topics."
                    ),
                    codeExample = "<head>\n  <title>Learn HTML from Zero | Free Course by Awiskar Acharya</title>\n  <meta name=\"description\" content=\"Master modern HTML from zero. Interactive code playground, real projects, and 100% free curriculum.\">\n  <meta property=\"og:title\" content=\"Learn HTML Free\">\n</head>",
                    commonMistake = "Leaving default or empty titles like 'Document' or 'Untitled Page'."
                )
            )
        ),
        CourseModule(
            id = 13,
            title = "Module 13 — HTML Best Practices",
            subtitle = "Writing clean, professional, maintainable code",
            description = "Proper indentation, naming conventions, valid HTML syntax, self-closing conventions, and avoiding div soup.",
            iconName = "star",
            estimatedTimeMinutes = 35,
            lessons = listOf(
                Lesson(
                    id = "m13_l1",
                    moduleId = 13,
                    lessonNumber = 1,
                    title = "Clean Code & Validation",
                    subtitle = "Writing code other engineers love to read",
                    estimatedTime = "7 min",
                    explanation = "Professional developers follow strict coding conventions:\n\n1. Indentation: Indent child elements by 2 spaces.\n2. Lowercase tags: Always write <p>, not <P>.\n3. Always quote attributes: class=\"hero\", not class=hero.\n4. Close all tags properly.\n5. Validate using W3C Markup Validation Service.",
                    keyTakeaways = listOf(
                        "Always use 2 or 4 space consistent indentation.",
                        "Use lowercase for tag names and attribute names.",
                        "Always wrap attribute values in double quotes.",
                        "Avoid unnecessary container divs when semantic tags exist."
                    ),
                    codeExample = "<!-- Professional clean indentation -->\n<nav>\n  <ul>\n    <li><a href=\"#home\">Home</a></li>\n    <li><a href=\"#about\">About</a></li>\n  </ul>\n</nav>",
                    commonMistake = "Writing messy, unindented single-line HTML that is impossible to debug."
                )
            )
        ),
        CourseModule(
            id = 14,
            title = "Module 14 — Real Website Projects",
            subtitle = "10 Practical Hands-On Guided Projects",
            description = "Learn → Code → Preview → Improve → Complete. Build complete real-world web pages from scratch.",
            iconName = "project",
            estimatedTimeMinutes = 120,
            lessons = listOf(
                Lesson(
                    id = "m14_l1",
                    moduleId = 14,
                    lessonNumber = 1,
                    title = "Web Development Workshop",
                    subtitle = "Putting it all together into production websites",
                    estimatedTime = "10 min",
                    explanation = "In this final module, you transition from isolated exercises to building full, complete websites. You will build personal portfolios, restaurant menus, product landing pages, registration systems, and multi-page layouts.",
                    keyTakeaways = listOf(
                        "Every project includes realistic HTML structure and content.",
                        "Use the Live Playground to test your website in real-time.",
                        "Check off requirements one by one to complete each project.",
                        "Earn the Project Builder and HTML Complete achievements!"
                    ),
                    codeExample = "<!DOCTYPE html>\n<html>\n<body>\n  <header><h1>Awiskar's Tech Portfolio</h1></header>\n  <main><p>Web developer passionate about clean HTML.</p></main>\n</body>\n</html>"
                )
            )
        )
    )

    val guidedProjects: List<GuidedProject> = listOf(
        GuidedProject(
            id = "proj_profile",
            projectNumber = 1,
            title = "Personal Profile Page",
            description = "Build a personal biography page with a profile picture, about me paragraph, skills list, and social contact links.",
            difficulty = "Beginner",
            estimatedTime = "15 min",
            requirements = listOf(
                "Add an <h1> heading with your name",
                "Include a profile <img> with an alt attribute",
                "Write a <p> introduction paragraph about yourself",
                "Create an <ul> list of 3 skills you have or are learning",
                "Add at least one <a> link to your LinkedIn or GitHub"
            ),
            starterHtml = "<!DOCTYPE html>\n<html>\n<head>\n  <title>My Profile</title>\n</head>\n<body>\n  <!-- 1. Add your main heading here -->\n  \n  <!-- 2. Add an image with alt text -->\n  \n  <!-- 3. Add an about paragraph -->\n  \n  <!-- 4. Add a list of skills -->\n  \n  <!-- 5. Add social links -->\n  \n</body>\n</html>",
            solutionPreviewHtml = "<!DOCTYPE html>\n<html>\n<head>\n  <title>Awiskar Acharya Profile</title>\n</head>\n<body style=\"font-family: sans-serif; padding: 16px;\">\n  <h1>Awiskar Acharya</h1>\n  <img src=\"https://picsum.photos/120/120\" alt=\"Profile photo of Awiskar\" style=\"border-radius: 50%;\">\n  <p>Hello! I am a passionate developer learning HTML, CSS, and modern web engineering.</p>\n  <h3>My Skills:</h3>\n  <ul>\n    <li>HTML5 Semantic Markup</li>\n    <li>Responsive Design Basics</li>\n    <li>Web Accessibility</li>\n  </ul>\n  <p>Connect with me on <a href=\"https://www.linkedin.com/in/awiskaracharya/\" target=\"_blank\">LinkedIn</a>!</p>\n</body>\n</html>",
            keyTagsUsed = listOf("<h1>", "<img>", "<p>", "<ul>", "<li>", "<a>")
        ),
        GuidedProject(
            id = "proj_restaurant",
            projectNumber = 2,
            title = "Restaurant Website & Menu",
            description = "Design a dining experience page featuring restaurant hours, specialty dishes in an organized table, and reservation form.",
            difficulty = "Beginner-Intermediate",
            estimatedTime = "20 min",
            requirements = listOf(
                "Use a <header> with the restaurant name and tagline",
                "Build a <table> displaying menu items, descriptions, and prices",
                "Include a customer reservation <form> with name, date, and guests inputs",
                "Use a <footer> with address and opening hours"
            ),
            starterHtml = "<!DOCTYPE html>\n<html>\n<body>\n  <header>\n    <h1>Bella Vista Bistro</h1>\n  </header>\n  <main>\n    <!-- Add menu table and reservation form here -->\n  </main>\n</body>\n</html>",
            solutionPreviewHtml = "<!DOCTYPE html>\n<html>\n<body style=\"font-family: sans-serif; padding: 16px;\">\n  <header>\n    <h1>Bella Vista Bistro</h1>\n    <p>Authentic Woodfired Italian Cuisine</p>\n  </header>\n  <hr>\n  <section>\n    <h2>Our Specialties</h2>\n    <table border=\"1\" cellpadding=\"8\">\n      <tr><th>Dish</th><th>Description</th><th>Price</th></tr>\n      <tr><td>Margherita Pizza</td><td>Fresh mozzarella & basil</td><td>$14</td></tr>\n      <tr><td>Truffle Pasta</td><td>Handmade fettuccine</td><td>$18</td></tr>\n    </table>\n  </section>\n  <section>\n    <h3>Book a Table</h3>\n    <form>\n      <label>Name: <input type=\"text\" required></label><br><br>\n      <label>Date: <input type=\"date\" required></label><br><br>\n      <button type=\"submit\">Reserve Now</button>\n    </form>\n  </section>\n</body>\n</html>",
            keyTagsUsed = listOf("<header>", "<table>", "<tr>", "<td>", "<form>", "<input>", "<button>")
        ),
        GuidedProject(
            id = "proj_portfolio",
            projectNumber = 3,
            title = "Developer Portfolio Website",
            description = "Build a modern multi-section portfolio showcasing featured projects, technologies, and a contact form.",
            difficulty = "Intermediate",
            estimatedTime = "25 min",
            requirements = listOf(
                "Use semantic layout: <header>, <nav>, <main>, <section>, <footer>",
                "Add in-page anchor links in <nav> that jump to sections (#projects, #contact)",
                "Showcase at least two <article> project cards",
                "Include a contact form with <textarea>"
            ),
            starterHtml = "<!DOCTYPE html>\n<html>\n<body>\n  <!-- Build your developer portfolio -->\n</body>\n</html>",
            solutionPreviewHtml = "<!DOCTYPE html>\n<html>\n<body style=\"font-family: sans-serif; padding: 16px;\">\n  <header>\n    <h1>Dev Portfolio</h1>\n    <nav>\n      <a href=\"#projects\">Projects</a> | <a href=\"#contact\">Contact</a>\n    </nav>\n  </header>\n  <main>\n    <section id=\"projects\">\n      <h2>Featured Projects</h2>\n      <article style=\"border: 1px solid #ccc; padding: 8px; margin-bottom: 8px;\">\n        <h3>Learn HTML App</h3>\n        <p>Interactive educational platform created for modern coders.</p>\n      </article>\n    </section>\n    <section id=\"contact\">\n      <h2>Contact Me</h2>\n      <form>\n        <label>Your Email: <input type=\"email\"></label><br><br>\n        <label>Message:<br><textarea rows=\"3\"></textarea></label><br><br>\n        <button type=\"submit\">Send Message</button>\n      </form>\n    </section>\n  </main>\n  <footer><p>&copy; 2026 Developer Portfolio</p></footer>\n</body>\n</html>",
            keyTagsUsed = listOf("<nav>", "<section>", "<article>", "<textarea>", "<footer>")
        ),
        GuidedProject(
            id = "proj_registration",
            projectNumber = 4,
            title = "Registration & Onboarding Form",
            description = "Create a comprehensive user signup form with proper labels, validation attributes, radio options, and terms checkbox.",
            difficulty = "Intermediate",
            estimatedTime = "20 min",
            requirements = listOf(
                "Include text, email, password, and number inputs",
                "Group options with radio buttons sharing the same name",
                "Add a <select> dropdown for country selection",
                "Include a required terms and conditions checkbox",
                "Wrap inputs with matching accessible <label for=\"...\"> tags"
            ),
            starterHtml = "<!DOCTYPE html>\n<html>\n<body>\n  <h2>Create Account</h2>\n  <form action=\"/register\" method=\"POST\">\n    <!-- Build form inputs -->\n  </form>\n</body>\n</html>",
            solutionPreviewHtml = "<!DOCTYPE html>\n<html>\n<body style=\"font-family: sans-serif; padding: 16px;\">\n  <h2>Create Your Account</h2>\n  <form>\n    <label for=\"uname\">Full Name:</label><br>\n    <input type=\"text\" id=\"uname\" required><br><br>\n    <label for=\"uemail\">Email:</label><br>\n    <input type=\"email\" id=\"uemail\" required><br><br>\n    <label for=\"upass\">Password:</label><br>\n    <input type=\"password\" id=\"upass\" required minlength=\"8\"><br><br>\n    <label for=\"ucountry\">Country:</label><br>\n    <select id=\"ucountry\">\n      <option>Nepal</option>\n      <option>United States</option>\n      <option>Other</option>\n    </select><br><br>\n    <input type=\"checkbox\" id=\"terms\" required>\n    <label for=\"terms\">I agree to the Terms</label><br><br>\n    <button type=\"submit\">Register</button>\n  </form>\n</body>\n</html>",
            keyTagsUsed = listOf("<form>", "<input>", "<label>", "<select>", "<option>", "<button>")
        ),
        GuidedProject(
            id = "proj_landing",
            projectNumber = 5,
            title = "Product Landing Page",
            description = "Create a high-converting product showcase with hero section, feature grid, video demo embed, and pricing call-to-action.",
            difficulty = "Intermediate",
            estimatedTime = "25 min",
            requirements = listOf(
                "Hero section with bold headline and CTA button",
                "Features section with 3 distinct feature cards",
                "Video or image product showcase",
                "Pricing plan comparison"
            ),
            starterHtml = "<!DOCTYPE html>\n<html>\n<body>\n  <!-- Build Product Landing Page -->\n</body>\n</html>",
            solutionPreviewHtml = "<!DOCTYPE html>\n<html>\n<body style=\"font-family: sans-serif; padding: 16px;\">\n  <header style=\"text-align: center;\">\n    <h1>CodeFlow Editor</h1>\n    <p>The fastest code playground for HTML learners.</p>\n    <a href=\"#buy\"><button style=\"padding: 8px 16px; font-weight: bold;\">Get Started Free</button></a>\n  </header>\n  <section style=\"margin-top: 24px;\">\n    <h2>Why Developers Love CodeFlow</h2>\n    <ul>\n      <li><b>Instant Live Preview:</b> See changes as you type.</li>\n      <li><b>100% Free:</b> No subscriptions or paywalls.</li>\n      <li><b>Accessible:</b> Built according to WCAG standards.</li>\n    </ul>\n  </section>\n</body>\n</html>",
            keyTagsUsed = listOf("<header>", "<h1>", "<section>", "<ul>", "<button>", "<a>")
        ),
        GuidedProject(
            id = "proj_blog",
            projectNumber = 6,
            title = "Editorial Blog Post",
            description = "Format a long-form article with author bio, publication date, headings, blockquotes, and code snippets.",
            difficulty = "Beginner",
            estimatedTime = "15 min",
            requirements = listOf(
                "Use <article> for the blog story",
                "Include a <time datetime=\"...\"> element",
                "Add a <blockquote> with a memorable quote",
                "Include a <pre><code> code example block"
            ),
            starterHtml = "<!DOCTYPE html>\n<html>\n<body>\n  <!-- Build your blog post -->\n</body>\n</html>",
            solutionPreviewHtml = "<!DOCTYPE html>\n<html>\n<body style=\"font-family: sans-serif; padding: 16px;\">\n  <article>\n    <h1>The Evolution of the Web</h1>\n    <p>By <b>Awiskar Acharya</b> &bull; Published <time datetime=\"2026-09-28\">September 2026</time></p>\n    <p>HTML has powered the internet for over three decades.</p>\n    <blockquote style=\"border-left: 4px solid #F97316; padding-left: 8px; color: #555;\">\n      \"The web does not just connect machines, it connects people.\"\n    </blockquote>\n    <h3>Basic Syntax</h3>\n    <pre style=\"background: #eee; padding: 8px;\"><code>&lt;p&gt;Hello World&lt;/p&gt;</code></pre>\n  </article>\n</body>\n</html>",
            keyTagsUsed = listOf("<article>", "<time>", "<blockquote>", "<pre>", "<code>")
        ),
        GuidedProject(
            id = "proj_news",
            projectNumber = 7,
            title = "News Website Layout",
            description = "Construct a multi-column newspaper style frontpage with breaking news alerts, lead story, and sidebar.",
            difficulty = "Advanced",
            estimatedTime = "30 min",
            requirements = listOf(
                "Header with publication name and current edition",
                "Lead news story with large image",
                "Sidebar with <aside> for trending topics",
                "Footer with editorial disclaimer"
            ),
            starterHtml = "<!DOCTYPE html>\n<html>\n<body>\n  <!-- News Layout -->\n</body>\n</html>",
            solutionPreviewHtml = "<!DOCTYPE html>\n<html>\n<body style=\"font-family: serif; padding: 16px;\">\n  <header style=\"text-align: center; border-bottom: 2px solid #000;\">\n    <h1 style=\"font-size: 28px;\">THE DAILY CHRONICLE</h1>\n    <p>Edition: Worldwide &bull; Free Open Access</p>\n  </header>\n  <main>\n    <section>\n      <h2>Global Web Literacy Rates Surge</h2>\n      <p>Millions of aspiring creators learn HTML from scratch this year.</p>\n    </section>\n    <aside style=\"background: #f9f9f9; padding: 8px;\">\n      <h3>Trending Tech</h3>\n      <ol><li>HTML5 Canvas</li><li>Semantic SEO</li><li>Web Accessibility</li></ol>\n    </aside>\n  </main>\n</body>\n</html>",
            keyTagsUsed = listOf("<header>", "<main>", "<section>", "<aside>", "<ol>")
        ),
        GuidedProject(
            id = "proj_college",
            projectNumber = 8,
            title = "College / University Department Page",
            description = "Design a department portal with faculty list, course syllabus table, and campus location iframe.",
            difficulty = "Intermediate",
            estimatedTime = "25 min",
            requirements = listOf(
                "Department banner and mission statement",
                "Course schedule table with <thead> and <tbody>",
                "Faculty directory with pictures and email mailto links",
                "Embedded campus map frame"
            ),
            starterHtml = "<!DOCTYPE html>\n<html>\n<body>\n  <!-- College Department Page -->\n</body>\n</html>",
            solutionPreviewHtml = "<!DOCTYPE html>\n<html>\n<body style=\"font-family: sans-serif; padding: 16px;\">\n  <header>\n    <h1>Department of Computer Science</h1>\n    <p>Empowering innovators since 1998</p>\n  </header>\n  <section>\n    <h2>Courses Offered</h2>\n    <table border=\"1\" cellpadding=\"6\">\n      <thead><tr><th>Course</th><th>Credits</th><th>Instructor</th></tr></thead>\n      <tbody>\n        <tr><td>CS101: Web Basics</td><td>3</td><td>Prof. Acharya</td></tr>\n        <tr><td>CS202: Data Structures</td><td>4</td><td>Dr. Smith</td></tr>\n      </tbody>\n    </table>\n  </section>\n</body>\n</html>",
            keyTagsUsed = listOf("<table>", "<thead>", "<tbody>", "<tr>", "<td>", "<a>")
        ),
        GuidedProject(
            id = "proj_travel",
            projectNumber = 9,
            title = "Travel Destination Guide",
            description = "Create an inspiring destination showcase with photo gallery, itinerary list, and weather forecast table.",
            difficulty = "Intermediate",
            estimatedTime = "25 min",
            requirements = listOf(
                "Destination hero banner with caption",
                "Day-by-day ordered travel itinerary",
                "Must-try local foods list",
                "External link to flight booking"
            ),
            starterHtml = "<!DOCTYPE html>\n<html>\n<body>\n  <!-- Travel Guide -->\n</body>\n</html>",
            solutionPreviewHtml = "<!DOCTYPE html>\n<html>\n<body style=\"font-family: sans-serif; padding: 16px;\">\n  <h1>Discover Nepal: The Himalayas</h1>\n  <figure>\n    <img src=\"https://picsum.photos/320/160\" alt=\"Snowcapped peaks of the Annapurna range\" style=\"width:100%; border-radius: 8px;\">\n    <figcaption>The breathtaking Himalayan mountain range.</figcaption>\n  </figure>\n  <h2>3-Day Itinerary</h2>\n  <ol>\n    <li>Explore the ancient temples of Kathmandu</li>\n    <li>Watch sunrise over Pokhara lake</li>\n    <li>Hike to Sarangkot viewpoint</li>\n  </ol>\n</body>\n</html>",
            keyTagsUsed = listOf("<figure>", "<figcaption>", "<img>", "<ol>", "<li>")
        ),
        GuidedProject(
            id = "proj_multipage",
            projectNumber = 10,
            title = "Complete Multi-Section Website",
            description = "The ultimate capstone project! Combine all 13 modules to build a complete modern website with navigation, about section, services, testimonials, and contact form.",
            difficulty = "Advanced",
            estimatedTime = "40 min",
            requirements = listOf(
                "Full HTML5 boilerplate with DOCTYPE, meta viewport, and title",
                "Sticky navigation bar with jump links to 4 sections",
                "Responsive image hero section",
                "Pricing / services table with semantic header & footer",
                "Interactive contact form with required fields",
                "Accessible footer with copyright and social links"
            ),
            starterHtml = "<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n  <meta charset=\"UTF-8\">\n  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n  <title>Complete Capstone Website</title>\n</head>\n<body>\n  <!-- Build your complete capstone project -->\n</body>\n</html>",
            solutionPreviewHtml = "<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n  <meta charset=\"UTF-8\">\n  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n  <title>Nexus Web Solutions</title>\n</head>\n<body style=\"font-family: sans-serif; margin: 0; padding: 16px;\">\n  <header style=\"background: #0F172A; color: white; padding: 16px; border-radius: 8px;\">\n    <h2>Nexus Solutions</h2>\n    <nav>\n      <a href=\"#about\" style=\"color: #38BDF8;\">About</a> | \n      <a href=\"#services\" style=\"color: #38BDF8;\">Services</a> | \n      <a href=\"#contact\" style=\"color: #38BDF8;\">Contact</a>\n    </nav>\n  </header>\n  <main>\n    <section id=\"about\" style=\"margin-top: 24px;\">\n      <h3>About Us</h3>\n      <p>We build accessible, high-performance websites using pure semantic HTML.</p>\n    </section>\n    <section id=\"services\">\n      <h3>Services</h3>\n      <ul>\n        <li>Frontend Architecture</li>\n        <li>Web Accessibility Auditing</li>\n        <li>SEO Optimization</li>\n      </ul>\n    </section>\n    <section id=\"contact\">\n      <h3>Get In Touch</h3>\n      <form>\n        <label>Name: <input type=\"text\" required></label><br><br>\n        <label>Email: <input type=\"email\" required></label><br><br>\n        <button type=\"submit\">Send Inquiry</button>\n      </form>\n    </section>\n  </main>\n  <footer style=\"margin-top: 32px; border-top: 1px solid #ccc; padding-top: 8px;\">\n    <p>&copy; 2026 Nexus Solutions &bull; Built with pride.</p>\n  </footer>\n</body>\n</html>",
            keyTagsUsed = listOf("<!DOCTYPE>", "<header>", "<nav>", "<section>", "<ul>", "<form>", "<footer>")
        )
    )

    val htmlTags: List<HtmlTagInfo> = listOf(
        HtmlTagInfo(
            tag = "<h1> to <h6>",
            name = "Headings",
            category = "Text",
            purpose = "Defines 6 levels of section headings, with <h1> being the most important and <h6> being the least.",
            syntax = "<h1>Your Heading Here</h1>",
            example = "<h1>Welcome to Learn HTML</h1>\n<h2>Module 1: Basics</h2>",
            attributes = listOf(TagAttribute("id", "Unique identifier for anchor links")),
            commonMistakes = "Skipping heading levels (e.g. <h1> directly to <h4>) or using more than one <h1> per page.",
            accessibilityNotes = "Screen readers use headings to generate a table of contents for users."
        ),
        HtmlTagInfo(
            tag = "<p>",
            name = "Paragraph",
            category = "Text",
            purpose = "Represents a block of body text. Browsers automatically add vertical margin before and after.",
            syntax = "<p>This is a paragraph.</p>",
            example = "<p>HTML is the foundation of the modern internet.</p>",
            attributes = listOf(TagAttribute("class", "CSS class name for styling")),
            commonMistakes = "Wrapping block elements like <div> or <table> inside a <p> tag.",
            accessibilityNotes = "Screen readers pause slightly after each paragraph for natural reading cadence."
        ),
        HtmlTagInfo(
            tag = "<a>",
            name = "Anchor / Hyperlink",
            category = "Links",
            purpose = "Creates hyperlinks to other web pages, email addresses, telephone numbers, or files.",
            syntax = "<a href=\"https://example.com\">Click here</a>",
            example = "<a href=\"https://www.linkedin.com/in/awiskaracharya/\" target=\"_blank\">Connect on LinkedIn</a>",
            attributes = listOf(
                TagAttribute("href", "Destination URL or in-page anchor (#id)", isRequired = true),
                TagAttribute("target", "Where to open (e.g. '_blank' for new tab)"),
                TagAttribute("rel", "Security attributes like 'noopener noreferrer'")
            ),
            commonMistakes = "Using generic link text like 'click here' instead of descriptive labels.",
            accessibilityNotes = "Links are keyboard focusable and announced with their accessible name."
        ),
        HtmlTagInfo(
            tag = "<img>",
            name = "Image",
            category = "Images",
            purpose = "Embeds an image into the document. It is an empty/self-closing element.",
            syntax = "<img src=\"url\" alt=\"description\">",
            example = "<img src=\"https://picsum.photos/200/100\" alt=\"Sample landscape\" width=\"200\" height=\"100\">",
            attributes = listOf(
                TagAttribute("src", "Path or URL to the image file", isRequired = true),
                TagAttribute("alt", "Text alternative describing image", isRequired = true),
                TagAttribute("loading", "'lazy' or 'eager' for performance")
            ),
            commonMistakes = "Forgetting the alt attribute, which breaks screen reader accessibility.",
            accessibilityNotes = "Screen readers read alt text out loud. If an image is purely decorative, use alt=\"\"."
        ),
        HtmlTagInfo(
            tag = "<form>",
            name = "Form Container",
            category = "Forms",
            purpose = "Wraps interactive user input controls to submit data to a web server.",
            syntax = "<form action=\"/submit\" method=\"POST\">...</form>",
            example = "<form action=\"/api\" method=\"POST\">\n  <input type=\"text\" name=\"user\">\n  <button type=\"submit\">Send</button>\n</form>",
            attributes = listOf(
                TagAttribute("action", "URL where data is submitted"),
                TagAttribute("method", "'GET' or 'POST'")
            ),
            commonMistakes = "Not defining the 'name' attribute on child inputs.",
            accessibilityNotes = "Forms should have logical tab order so keyboard users can navigate seamlessly."
        ),
        HtmlTagInfo(
            tag = "<input>",
            name = "Form Input",
            category = "Forms",
            purpose = "Accepts user input. The behavior varies greatly depending on the 'type' attribute.",
            syntax = "<input type=\"text\" name=\"field_name\">",
            example = "<input type=\"email\" id=\"mail\" name=\"email\" placeholder=\"you@example.com\" required>",
            attributes = listOf(
                TagAttribute("type", "Type of input: text, email, password, number, radio, checkbox, etc.", isRequired = true),
                TagAttribute("name", "Key name for submitted form data"),
                TagAttribute("required", "Enforces non-empty entry before submit")
            ),
            commonMistakes = "Failing to attach a <label> with a matching 'for' and 'id'.",
            accessibilityNotes = "Always provide a corresponding <label> for each input."
        ),
        HtmlTagInfo(
            tag = "<button>",
            name = "Button",
            category = "Forms",
            purpose = "Represents a clickable interactive button that can submit forms or trigger actions.",
            syntax = "<button type=\"submit\">Click Me</button>",
            example = "<button type=\"button\" onclick=\"alert('Hello!')\">Run Action</button>",
            attributes = listOf(
                TagAttribute("type", "'submit', 'reset', or 'button' (default is submit inside forms)")
            ),
            commonMistakes = "Forgetting that inside a <form>, <button> submits by default unless type=\"button\" is specified.",
            accessibilityNotes = "Native buttons are accessible by default and trigger on Space/Enter key presses."
        ),
        HtmlTagInfo(
            tag = "<header>",
            name = "Header",
            category = "Semantic",
            purpose = "Represents introductory content or a set of navigational links for a page or section.",
            syntax = "<header><h1>Site Title</h1></header>",
            example = "<header>\n  <h1>Learn HTML</h1>\n  <p>Free Web Course</p>\n</header>",
            attributes = emptyList(),
            commonMistakes = "Confusing <header> with the <head> metadata element.",
            accessibilityNotes = "Screen readers recognize <header> as an ARIA landmark banner."
        ),
        HtmlTagInfo(
            tag = "<nav>",
            name = "Navigation",
            category = "Semantic",
            purpose = "Defines a section containing primary navigation links to other pages or page parts.",
            syntax = "<nav><a href=\"#\">Home</a></nav>",
            example = "<nav>\n  <ul>\n    <li><a href=\"#home\">Home</a></li>\n    <li><a href=\"#docs\">Docs</a></li>\n  </ul>\n</nav>",
            attributes = emptyList(),
            commonMistakes = "Wrapping every single link in <nav>. Only major navigation blocks need <nav>.",
            accessibilityNotes = "Screen readers allow users to jump straight to the <nav> landmark."
        ),
        HtmlTagInfo(
            tag = "<main>",
            name = "Main Content",
            category = "Semantic",
            purpose = "Represents the dominant, central content unique to this specific document.",
            syntax = "<main>...</main>",
            example = "<main>\n  <h2>Today's Lesson</h2>\n  <p>Content goes here.</p>\n</main>",
            attributes = emptyList(),
            commonMistakes = "Having more than one non-hidden <main> element on a page.",
            accessibilityNotes = "Allows screen reader users to skip headers and jump straight to main content."
        ),
        HtmlTagInfo(
            tag = "<section>",
            name = "Section",
            category = "Semantic",
            purpose = "Represents a standalone generic section of a document with thematic grouping.",
            syntax = "<section><h2>Title</h2><p>...</p></section>",
            example = "<section id=\"about\">\n  <h2>About Us</h2>\n  <p>We teach coding.</p>\n</section>",
            attributes = emptyList(),
            commonMistakes = "Using <section> as a generic wrapper for styling (use <div> for pure styling).",
            accessibilityNotes = "Always provide a heading inside every <section>."
        ),
        HtmlTagInfo(
            tag = "<article>",
            name = "Article",
            category = "Semantic",
            purpose = "Represents a self-contained composition (blog post, news item, forum thread).",
            syntax = "<article><h2>Story</h2></article>",
            example = "<article>\n  <h2>HTML Complete</h2>\n  <p>You did it!</p>\n</article>",
            attributes = emptyList(),
            commonMistakes = "Using <article> for widgets or fragments that make no sense outside the page.",
            accessibilityNotes = "Helps reader modes extract the clean article body for distraction-free reading."
        ),
        HtmlTagInfo(
            tag = "<footer>",
            name = "Footer",
            category = "Semantic",
            purpose = "Represents a footer for its nearest sectioning content or page root (copyright, author, links).",
            syntax = "<footer><p>&copy; 2026</p></footer>",
            example = "<footer>\n  <p>Learn HTML &bull; Created by Awiskar Acharya</p>\n</footer>",
            attributes = emptyList(),
            commonMistakes = "Placing primary article content inside the footer.",
            accessibilityNotes = "Landmark element for assistive tech."
        ),
        HtmlTagInfo(
            tag = "<table>",
            name = "Table",
            category = "Tables",
            purpose = "Represents two-dimensional data structured into rows and columns.",
            syntax = "<table><tr><td>Data</td></tr></table>",
            example = "<table border=\"1\">\n  <tr><th>Course</th><th>Price</th></tr>\n  <tr><td>HTML</td><td>Free</td></tr>\n</table>",
            attributes = emptyList(),
            commonMistakes = "Using tables for page layout instead of CSS Grid / Flexbox.",
            accessibilityNotes = "Include <caption> and <th> headers with scope attribute for screen reader navigation."
        )
    )

    val glossary: List<GlossaryItem> = listOf(
        GlossaryItem(
            term = "HTML",
            simpleDefinition = "The language used to structure the content of every webpage.",
            technicalDefinition = "HyperText Markup Language: The standard markup language for documents designed to be displayed in a web browser.",
            whyItMatters = "Without HTML, web browsers have no structure or text to render on screen.",
            example = "<h1>Welcome to the Web</h1>"
        ),
        GlossaryItem(
            term = "Tag",
            simpleDefinition = "Special code enclosed in angle brackets (< >) that labels content.",
            technicalDefinition = "A syntactical construct marking the start (<p>) or end (</p>) of an element.",
            whyItMatters = "Tags tell the browser whether text is a heading, link, paragraph, or button.",
            example = "<p>Text inside tags</p>"
        ),
        GlossaryItem(
            term = "Element",
            simpleDefinition = "A complete building block consisting of an opening tag, content, and closing tag.",
            technicalDefinition = "A component of an HTML document representing a node in the Document Object Model.",
            whyItMatters = "Understanding elements is the core foundation of frontend web engineering.",
            example = "<strong>Urgent Notice</strong>"
        ),
        GlossaryItem(
            term = "Attribute",
            simpleDefinition = "Extra settings or instructions placed inside an opening tag.",
            technicalDefinition = "Modifier of an HTML element providing additional configuration (name=\"value\").",
            whyItMatters = "Attributes give elements destinations (href), image paths (src), and identifiers (id).",
            example = "<a href=\"https://google.com\">Search</a>"
        ),
        GlossaryItem(
            term = "DOM",
            simpleDefinition = "The browser's internal tree representation of your HTML code.",
            technicalDefinition = "Document Object Model: A programming interface representing HTML as an object tree.",
            whyItMatters = "JavaScript and CSS interact with the DOM to dynamically change web pages.",
            example = "document.getElementById('header')"
        ),
        GlossaryItem(
            term = "Semantic HTML",
            simpleDefinition = "Using tags that describe the real meaning of their content rather than generic boxes.",
            technicalDefinition = "Markup that introduces meaning to the web page rather than just presentation.",
            whyItMatters = "Improves SEO rankings, accessibility for disabled users, and code clarity.",
            example = "<article> instead of <div>"
        ),
        GlossaryItem(
            term = "Accessibility (a11y)",
            simpleDefinition = "Designing websites so everyone, including people with disabilities, can use them.",
            technicalDefinition = "Web Accessibility Initiative guidelines (WCAG) ensuring inclusive digital access.",
            whyItMatters = "The web should be usable by all humans regardless of hardware or physical ability.",
            example = "<img src=\"logo.png\" alt=\"Company Logo\">"
        ),
        GlossaryItem(
            term = "URL",
            simpleDefinition = "The web address used to find any page or file on the internet.",
            technicalDefinition = "Uniform Resource Locator: A reference to a web resource specifying its location on a network.",
            whyItMatters = "Hyperlinks use URLs to connect documents across the entire World Wide Web.",
            example = "https://www.linkedin.com/in/awiskaracharya/"
        ),
        GlossaryItem(
            term = "Boilerplate",
            simpleDefinition = "The standard starter template that every single HTML document needs.",
            technicalDefinition = "Sections of code that are included in many places with little or no alteration.",
            whyItMatters = "Ensures the browser handles character encoding and mobile screens correctly.",
            example = "<!DOCTYPE html><html><head></head><body></body></html>"
        ),
        GlossaryItem(
            term = "Responsive Design",
            simpleDefinition = "Making a webpage automatically adjust and look great on phones, tablets, and desktops.",
            technicalDefinition = "An approach to web design making web pages render well on a variety of devices and window sizes.",
            whyItMatters = "More than 60% of all internet browsing happens on mobile smartphones.",
            example = "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
        )
    )

    val cheatSheet: List<CheatSheetItem> = listOf(
        CheatSheetItem(
            category = "Basic Skeleton",
            title = "HTML5 Boilerplate",
            syntax = "<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n  <meta charset=\"UTF-8\">\n  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n  <title>Title</title>\n</head>\n<body>\n</body>\n</html>",
            description = "Standard foundation for all modern webpages."
        ),
        CheatSheetItem(
            category = "Text Formatting",
            title = "Headings",
            syntax = "<h1>Most Important</h1>\n<h2>Second Level</h2>\n<h3>Third Level</h3>",
            description = "Headings 1 through 6 in hierarchical order."
        ),
        CheatSheetItem(
            category = "Text Formatting",
            title = "Paragraph & Breaks",
            syntax = "<p>Text block</p>\n<br> <!-- Line break -->\n<hr> <!-- Divider -->",
            description = "Basic body copy formatting."
        ),
        CheatSheetItem(
            category = "Links & Images",
            title = "Hyperlinks",
            syntax = "<a href=\"https://example.com\" target=\"_blank\" rel=\"noopener\">Link</a>",
            description = "Clickable link opening in new tab."
        ),
        CheatSheetItem(
            category = "Links & Images",
            title = "Image Embed",
            syntax = "<img src=\"image.jpg\" alt=\"Description\" width=\"300\" height=\"200\">",
            description = "Self-closing image element."
        ),
        CheatSheetItem(
            category = "Lists",
            title = "Unordered & Ordered",
            syntax = "<ul><li>Bullet</li></ul>\n<ol><li>Numbered</li></ol>",
            description = "Bullet and sequential lists."
        ),
        CheatSheetItem(
            category = "Forms",
            title = "Form Input with Label",
            syntax = "<label for=\"email\">Email:</label>\n<input type=\"email\" id=\"email\" name=\"email\" required>",
            description = "Accessible form field."
        ),
        CheatSheetItem(
            category = "Semantic Layout",
            title = "Page Architecture",
            syntax = "<header></header>\n<nav></nav>\n<main>\n  <article></article>\n  <section></section>\n  <aside></aside>\n</main>\n<footer></footer>",
            description = "Modern semantic HTML5 layout."
        )
    )

    val practiceChallenges: List<PracticeChallenge> = listOf(
        PracticeChallenge(
            id = "pc_1",
            title = "Closing Tag Fix",
            category = "Fundamentals",
            difficulty = "Easy",
            type = PracticeType.FILL_IN_BLANK,
            instructions = "Complete the closing tag for the heading element below.",
            promptCode = "<h1>Welcome to HTML_____",
            correctBlankAnswers = listOf("</h1>"),
            explanation = "Every standard heading element must be closed with </h1> containing a forward slash.",
            hint = "Look for the forward slash inside angle brackets."
        ),
        PracticeChallenge(
            id = "pc_2",
            title = "Image Accessibility",
            category = "Images",
            difficulty = "Easy",
            type = PracticeType.FILL_IN_BLANK,
            instructions = "Fill in the required attribute name that provides alternative text for screen readers.",
            promptCode = "<img src=\"avatar.png\" _____=\"User Profile Picture\">",
            correctBlankAnswers = listOf("alt"),
            explanation = "The 'alt' attribute gives screen readers descriptive text for images.",
            hint = "Three letters: a-l-t."
        ),
        PracticeChallenge(
            id = "pc_3",
            title = "Find the Nesting Error",
            category = "Syntax",
            difficulty = "Medium",
            type = PracticeType.FIND_THE_ERROR,
            instructions = "Which line contains a critical HTML nesting violation?",
            promptCode = "1: <div>\n2:   <p>Here is <b>bold and <i>italic</i></b> text.</p>\n3:   <p>This is <strong>broken</p></strong>\n4: </div>",
            options = listOf("Line 1", "Line 2", "Line 3", "Line 4"),
            correctOptionIndex = 2,
            explanation = "Line 3 closes <p> before closing <strong>. You must close the inner tag </strong> first!",
            hint = "Last In, First Out: inner tags must close before outer parent tags."
        ),
        PracticeChallenge(
            id = "pc_4",
            title = "Predict the Output",
            category = "Text Formatting",
            difficulty = "Easy",
            type = PracticeType.PREDICT_OUTPUT,
            instructions = "What will appear on screen when rendering: Water formula: H<sub>2</sub>O?",
            promptCode = "<p>Water formula: H<sub>2</sub>O</p>",
            options = listOf(
                "Water formula: H2O (normal text)",
                "Water formula: H²O (2 is raised above)",
                "Water formula: H₂O (2 is lowered subscript)",
                "Water formula: H-2-O"
            ),
            correctOptionIndex = 2,
            explanation = "<sub> stands for subscript, which places the character slightly below the baseline (H₂O).",
            hint = "<sub> lowers the text, while <sup> raises it."
        ),
        PracticeChallenge(
            id = "pc_5",
            title = "Arrange the Document Hierarchy",
            category = "Boilerplate",
            difficulty = "Medium",
            type = PracticeType.ARRANGE_CODE,
            instructions = "Select the correct sequence of top-level tags for a valid HTML5 page.",
            promptCode = "Choose the correct order from start to finish:",
            options = listOf(
                "<!DOCTYPE html> -> <html> -> <head> -> <body>",
                "<html> -> <!DOCTYPE html> -> <body> -> <head>",
                "<body> -> <head> -> <html> -> <!DOCTYPE html>",
                "<!DOCTYPE html> -> <body> -> <head> -> <html>"
            ),
            correctOptionIndex = 0,
            explanation = "A valid document starts with <!DOCTYPE html>, followed by <html>, with <head> first, then <body>.",
            hint = "DOCTYPE always comes first; head comes before body."
        ),
        PracticeChallenge(
            id = "pc_6",
            title = "Tag Matching: Semantic Landmarks",
            category = "Semantic",
            difficulty = "Easy",
            type = PracticeType.MATCH_THE_TAG,
            instructions = "Which semantic tag should wrap the main navigation links of a website?",
            promptCode = "Which tag defines major navigation links?",
            options = listOf("<link>", "<nav>", "<menu>", "<route>"),
            correctOptionIndex = 1,
            explanation = "<nav> is the dedicated HTML5 semantic element for navigation blocks.",
            hint = "Short for navigation."
        )
    )
}
