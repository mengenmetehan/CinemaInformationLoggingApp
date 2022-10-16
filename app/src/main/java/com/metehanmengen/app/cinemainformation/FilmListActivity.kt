package com.metehanmengen.app.cinemainformation

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.metehanmengen.app.cinemainformation.data.FilmData
import com.metehanmengen.app.cinemainformation.data.RegisterFilms
import com.metehanmengen.app.cinemainformation.databinding.ActivityFilmDetailsBinding
import com.metehanmengen.app.cinemainformation.databinding.ActivityFilmListBinding
import com.metehanmengen.app.cinemainformation.databinding.ActivityMainBinding

class FilmListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilmListBinding


    private fun listItemClickCallback(pos: Int)
    {
        var registerFilm = binding.filmListActivityListViewFilms.getItemAtPosition(pos) as FilmData

        Intent(this, FilmDetailsActivity::class.java).apply {
            putExtra("Register", registerFilm)
            Toast.makeText(applicationContext, registerFilm.toString(), Toast.LENGTH_SHORT).show()
            startActivity(this)
        }
    }


    private fun getFilms()
    {
        var films = RegisterFilms.getAll()
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, films)

        binding.filmListActivityListViewFilms.adapter = adapter
    }

    private fun initView()
    {
        getFilms()
        binding.filmListActivityListViewFilms.setOnItemClickListener{_, _ , pos, _ -> listItemClickCallback(pos)}
    }


    private fun initBinding()
    {
        binding = ActivityFilmListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    private fun initialize()
    {
        initBinding()
        initView()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Film Listesi"
        initialize()
    }
}