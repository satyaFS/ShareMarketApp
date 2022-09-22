package com.explore.stockmarketapp.data.mapper

import androidx.compose.foundation.isSystemInDarkTheme
import com.explore.stockmarketapp.data.local.CompanyListingEntity
import com.explore.stockmarketapp.data.remote.dto.CompanyInfoDto
import com.explore.stockmarketapp.domain.model.CompanyInfo
import com.explore.stockmarketapp.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing():CompanyListing{
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity():CompanyListingEntity{
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo():CompanyInfo{
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description?:"",
        name = name?:"",
        country = country?:"",
        industry = industry?:"",
        fiftyTwoWeekHighval = fiftyTwoWeekHighval?:"",
        fiftyTwoWeekLow = fiftyTwoWeekLow?:"",
        beta = beta?:"",
        peRatio = peRatio?:"",
        priceToBookRatio = priceToBookRatio?:""
    )
}




















