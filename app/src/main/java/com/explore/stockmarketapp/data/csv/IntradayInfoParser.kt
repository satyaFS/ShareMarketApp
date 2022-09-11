package com.explore.stockmarketapp.data.csv

import com.explore.stockmarketapp.data.mapper.toIntradayInfo
import com.explore.stockmarketapp.data.remote.dto.IntradayInfoDto
import com.explore.stockmarketapp.domain.model.CompanyListing
import com.explore.stockmarketapp.domain.model.IntradayInfo
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntradayInfoParser @Inject constructor() :CSVParser<IntradayInfo> {
    override suspend fun parser(stream: InputStream): List<IntradayInfo> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO){
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line->
                val timestamp = line.getOrNull(0)?:return@mapNotNull null
                val close = line.getOrNull(4)?:return@mapNotNull  null
                val dto = IntradayInfoDto(
                    timestamp,
                    close.toDouble()
                )
                dto.toIntradayInfo()
            }
                .filter {
                    it.date.dayOfMonth == LocalDateTime.now().minusDays(1).dayOfMonth
                }
                .sortedBy {
                    it.date.hour
                }
                .also {
                    csvReader.close()
                }
        }
    }
}