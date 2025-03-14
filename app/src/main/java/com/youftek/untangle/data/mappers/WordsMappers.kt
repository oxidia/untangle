package com.youftek.untangle.data.mappers

import com.youftek.untangle.data.remote.dto.TodayWordDto
import com.youftek.untangle.domain.TodayWord

fun TodayWordDto.toTodayWord(): TodayWord {
    return TodayWord(
        word = word
    )
}
