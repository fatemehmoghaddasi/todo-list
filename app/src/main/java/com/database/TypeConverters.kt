package com.database

import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateConverters {
    @TypeConverter
    fun fromEpochDays(value : Long?): LocalDate? {
        return value?.let{ LocalDate.ofEpochDay(it) }
    }
    @TypeConverter
    fun toEpochDay(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }
}