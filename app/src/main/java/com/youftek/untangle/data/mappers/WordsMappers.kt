package com.youftek.untangle.data.mappers

import com.youftek.untangle.data.remote.words_api.dto.TodayWordDto
import com.youftek.untangle.domain.model.TodayWord

fun TodayWordDto.toTodayWord(): TodayWord {
    return TodayWord(
        word = word
    )
}
