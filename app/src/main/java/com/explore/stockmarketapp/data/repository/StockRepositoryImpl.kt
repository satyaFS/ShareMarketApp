package com.explore.stockmarketapp.data.repository

import com.explore.stockmarketapp.data.csv.CSVParser
import com.explore.stockmarketapp.data.csv.CompanyListingsParser
import com.explore.stockmarketapp.data.local.StockDatabase
import com.explore.stockmarketapp.data.mapper.toCompanyListing
import com.explore.stockmarketapp.data.mapper.toCompanyListingEntity
import com.explore.stockmarketapp.data.remote.StockApi
import com.explore.stockmarketapp.domain.model.CompanyListing
import com.explore.stockmarketapp.domain.repository.StockRepository
import com.explore.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockApi,
    val db :StockDatabase,
    val companyListingsParser: CSVParser<CompanyListing>
):StockRepository {
    private val dao = db.dao
    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {

        return flow {
            emit(Resource.Loading(isLoading = true ))
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListings.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldLoadFromCache){
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try{
                val response = api.getListings()
                companyListingsParser.parser(response.byteStream())
            } catch (e:IOException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e:HttpException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let{listings->
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map { it.toCompanyListingEntity() }
                )
                emit(Resource.Success(
                    data = dao
                        .searchCompanyListing("")
                        .map { it.toCompanyListing() }
                ))
                emit(Resource.Loading<List<CompanyListing>>(false))


            }
        }

    }
}















