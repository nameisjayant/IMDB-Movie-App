package com.programming_simplified.movieapp.common.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import com.programming_simplified.movieapp.common.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        dispatcher:CoroutineDispatcher = Dispatchers.IO,
        apiCall:suspend ()-> Response<T>
    ):Flow<Result<T>> = flow {
        emit(Result.Loading)
        val response = apiCall()
        if (response.isSuccessful){
            val data = response.body()
            if(data != null){
                emit(Result.Success(data))
            }else{
                val error = response.errorBody()
                if(error != null){
                    emit(Result.Failure(IOException(error.toString())))
                }else{
                    emit(Result.Failure(IOException("something went wrong")))
                }
            }
        }else{
            emit(Result.Failure(Throwable(response.errorBody().toString())))
        }
    }.catch { e->
        e.printStackTrace()
        emit(Result.Failure(Exception(e)))
    }.flowOn(dispatcher)

}