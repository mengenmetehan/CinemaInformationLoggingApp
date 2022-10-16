package com.metehanmengen.app.cinemainformation.data

data class gFilmData(var name: String, var type: String, var productYear: String, var director: String, var productCompany: String) : java.io.Serializable
{
    override fun toString(): String {
        return String.format("$name - $type - $productYear - $director - $productCompany")
    }
}

