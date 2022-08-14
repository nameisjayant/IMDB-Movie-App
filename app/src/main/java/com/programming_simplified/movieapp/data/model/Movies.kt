package com.programming_simplified.movieapp.data.model

data class Movies(
    val page: Int?,
    val results: List<Results?>?
) {
    data class Results(
        val id: Long?,
        val original_title: String?,
        val overview: String?,
        val poster_path: String?,
        val vote_average:String?
    )
}