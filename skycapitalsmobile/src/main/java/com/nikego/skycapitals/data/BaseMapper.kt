package com.nikego.skycapitals.data

interface BaseMapper<in A, out B> {
    fun map(type: A): B?
}