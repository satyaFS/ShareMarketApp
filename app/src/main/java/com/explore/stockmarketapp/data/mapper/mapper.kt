package com.explore.stockmarketapp.data.mapper

import androidx.compose.foundation.isSystemInDarkTheme
import com.explore.stockmarketapp.data.local.CompanyListingEntity
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