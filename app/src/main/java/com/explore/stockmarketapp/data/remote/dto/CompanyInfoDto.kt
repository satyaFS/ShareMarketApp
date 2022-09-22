package com.explore.stockmarketapp.data.remote.dto

import com.squareup.moshi.Json

data class CompanyInfoDto(
    @field:Json(name="Symbol")      val symbol :String?,
    @field:Json(name="Description") val description :String?,
    @field:Json(name="Name")        val  name:String?,
    @field:Json(name="Country")     val country :String?,
    @field:Json(name="Industry")    val industry:String?,
    @field:Json (name= "52WeekHigh")val fiftyTwoWeekHighval :String?,
    @field:Json (name= "52WeekLow") val fiftyTwoWeekLow:String?,
    @field:Json (name= "Beta")      val beta:String?,
    @field:Json (name= "PERatio")   val peRatio:String?,
    @field:Json (name= "PriceToBookRatio")  val priceToBookRatio:String?
    )
