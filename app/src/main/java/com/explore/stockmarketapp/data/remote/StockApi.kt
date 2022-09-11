package com.explore.stockmarketapp.data.remote

import com.explore.stockmarketapp.data.remote.dto.CompanyInfoDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import  retrofit2.http.Query

interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey:String = API_KEY
    ):ResponseBody

    @GET("query?function=TIME_SERIES_INTRADAY&interval=6omin&datatype=csv")
    suspend fun getIntradayInfo(
        @Query("symbol") symbol:String,
        @Query("apikey") apiKey: String = API_KEY
    ):ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol:String,
        @Query("apikey") apiKey: String = API_KEY
    ):CompanyInfoDto

    companion object{
        const val  API_KEY = "Q63Y9NX3TUF587NF"
        const val  BASE_URL = "https://alphavantage.co"
    }

}