package com.programming_simplified.movieapp.common.base

interface Mapper<F,T> {

    fun mapFrom(from:F):T

}