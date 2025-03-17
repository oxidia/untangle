package com.youftek.untangle.domain.model

data class Phonetic(
    val text: String,
    val audio: String?,
)

data class Definition(
    val definition: String,
    val synonyms: List<String>,
    val antonyms: List<String>,
    val example: String?
)

data class Meaning(
    val partOfSpeech: String,
    val definitions: List<Definition>
)

data class WordOverview(
    val word: String,
    val phonetic: Phonetic,
    val meanings: List<Meaning>
)
