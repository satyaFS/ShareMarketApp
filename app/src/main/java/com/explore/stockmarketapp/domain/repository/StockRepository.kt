package com.explore.stockmarketapp.domain.repository

import com.explore.stockmarketapp.domain.model.CompanyListing
import com.explore.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote:Boolean,
        query:String
    ): Flow<Resource<List<CompanyListing>>>


}