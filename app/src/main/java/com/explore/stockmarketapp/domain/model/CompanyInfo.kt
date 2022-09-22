package com.explore.stockmarketapp.domain.model

data class CompanyInfo(
    val symbol :String,
    val description :String,
    val name:String,
    val country :String,
    val industry:String,
    val fiftyTwoWeekHighval :String,
    val fiftyTwoWeekLow:String,
    val beta:String,
    val peRatio:String,
    val priceToBookRatio:String
)
