package com.metehanmengen.app.cinemainformation.data

object RegisterFilms {
    private var films : MutableList<FilmData> = ArrayList()

    public fun save(film: FilmData) =  if(findFilm(film) != film) films.add(film) else println("Invalid Insertion")

    public fun getAll() = films.toList()

    public fun findFilm (film: FilmData) = films.find { it.name == film.name &&
                it.type == film.type &&
                it.productYear == film.productYear &&
                it.productCompany == film.productCompany}

    public fun updateFilm (film: FilmData, other: FilmData) {
            if (findFilm(film) != null) {
                film.name = other.name
                film.type = other.type
                film.productCompany = other.productCompany
                film.productYear = other.productYear
                film.director = other.director
        }
    }


}