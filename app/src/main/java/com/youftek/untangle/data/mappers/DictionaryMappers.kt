package com.youftek.untangle.data.mappers

import com.youftek.untangle.data.remote.dictionary_api.dto.WordOverviewDto
import com.youftek.untangle.domain.model.Definition
import com.youftek.untangle.domain.model.Meaning
import com.youftek.untangle.domain.model.Phonetic
import com.youftek.untangle.domain.model.WordOverview

fun WordOverviewDto.toWordOverview(): WordOverview {
    val word = this[0].word
    val mainPhoneticText = this[0].phonetic
    val phonetics = mutableListOf<Phonetic>()
    val meanings = mutableListOf<Meaning>()

    this.forEach { wordOverviewItem ->
        phonetics.addAll(
            wordOverviewItem.phonetics
                .map {
                    Phonetic(
                        text = it.text ?: "",
                        audio = it.audio.ifEmpty { null },
                    )
                })

        meanings.addAll(wordOverviewItem.meanings.map { meaning ->
            Meaning(
                partOfSpeech = meaning.partOfSpeech,
                definitions = meaning.definitions.map { definition ->
                    Definition(
                        definition = definition.definition,
                        synonyms = definition.synonyms,
                        antonyms = definition.antonyms,
                        example = definition.example
                    )
                },
            )
        })
    }

    val phonetic = phonetics.find {
        it.audio != null
    }

    val groupedMeanings = meanings.groupBy {
        it.partOfSpeech
    }
        .map { entry ->
            Meaning(
                partOfSpeech = entry.key,
                definitions = entry.value.fold(mutableListOf()) { definitions, meaning ->
                    definitions.addAll(
                        meaning.definitions
                    )

                    definitions
                }
            )
        }

    val phoneticText = phonetic?.text?.ifEmpty { mainPhoneticText }

    return WordOverview(
        word = word,
        phonetic = Phonetic(
            text = phoneticText ?: mainPhoneticText,
            audio = phonetic?.audio
        ),
        meanings = groupedMeanings
    )
}
