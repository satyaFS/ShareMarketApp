package com.explore.stockmarketapp.di

import com.explore.stockmarketapp.data.csv.CSVParser
import com.explore.stockmarketapp.data.csv.CompanyListingsParser
import com.explore.stockmarketapp.data.csv.IntradayInfoParser
import com.explore.stockmarketapp.data.repository.StockRepositoryImpl
import com.explore.stockmarketapp.domain.model.CompanyListing
import com.explore.stockmarketapp.domain.model.IntradayInfo
import com.explore.stockmarketapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 abstract class RepositoryModule {

     @Binds
     @Singleton
     abstract fun bindCompanyListingParser(
         companyListingParser:CompanyListingsParser
     ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ): CSVParser<IntradayInfo>

     @Binds
     @Singleton
     abstract fun bindStockRepository(
         stockRepositoryImpl:StockRepositoryImpl
     ):StockRepository

}