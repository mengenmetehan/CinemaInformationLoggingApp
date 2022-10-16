package com.metehanmengen.app.cinemainformation

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.metehanmengen.app.cinemainformation.data.CinemaTypes
import com.metehanmengen.app.cinemainformation.data.FilmData
import com.metehanmengen.app.cinemainformation.data.RegisterFilms
import com.metehanmengen.app.cinemainformation.databinding.ActivityFilmDetailsBinding

class FilmDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFilmDetailsBinding
    private lateinit var filmData : FilmData



    private fun newRegister() : FilmData
    {
        val name = binding.filmDetailsActivityEditTextCinemaName.toString()
        val type = binding.filmDetailsActivitySpinnerType.selectedItem.toString()
        val director = binding.filmDetailsActivityEditTextDirector.toString()
        val year = binding.filmDetailsActivityEditTextProductYear.toString()
        val company = binding.filmDetailsActivityEditTextProductCompany.toString()

        return FilmData(name, type, year, director, company)
    }


    private fun alertPositiveButtonProc()
    {
        Toast.makeText(this, R.string.register_updated, Toast.LENGTH_SHORT).show()
        // filmData = newRegister() -Bu çalışmadı, güncelleme işlemi repositorydeki metod ile yapılıyor.
        RegisterFilms.updateFilm(filmData, newRegister())
    }

    private fun alertNegativeButtonProc()
    {
        Toast.makeText(this, R.string.register_not_updated, Toast.LENGTH_SHORT).show()
        finish()
    }



    private fun alertButtonCallback(which: Int)
    {
        when(which) {
            DialogInterface.BUTTON_POSITIVE -> alertPositiveButtonProc()
            DialogInterface.BUTTON_NEGATIVE -> alertNegativeButtonProc()
            else -> throw NoSuchElementException()
        }
    }


    private fun updateButtonClickCallBack()
    {
        AlertDialog.Builder(this)
            .setPositiveButton("Yes") {_, w -> alertButtonCallback(w)}
            .setNegativeButton("No") {_, w -> alertButtonCallback(w)}
            .create().show()
    }

    private fun initUpdateButton()
    {
        binding.filmDetailsActivityButtonUpdate.setOnClickListener {_ -> updateButtonClickCallBack()}
    }


    private fun editSwitchChangeListenerCallBack(checked: Boolean)
    {
        binding.filmDetailsActivityEditTextCinemaName.isEnabled = checked
        binding.filmDetailsActivitySpinnerType.isEnabled = checked
        binding.filmDetailsActivityEditTextProductYear.isEnabled = checked
        binding.filmDetailsActivityEditTextProductCompany.isEnabled = checked
        binding.filmDetailsActivityEditTextDirector.isEnabled = checked
    }

    private fun initSpinner()
    {
        val array = resources.getStringArray(R.array.cinema_types)

        var idx = 0
        for(ch in CinemaTypes.values())
            if (ch.type == filmData.type)
                idx = ch.ordinal

        binding.filmDetailsActivitySpinnerType.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, array)
        binding.filmDetailsActivitySpinnerType.setSelection(idx)
        binding.filmDetailsActivitySpinnerType.isEnabled = false
 //CinemaTypes.valueOf(filmData.type).ordinal + 1
    }

    private fun initEditSwitch()
    {
        binding.filmDetailsActivitySwitchEditing.setOnCheckedChangeListener {_, checked -> editSwitchChangeListenerCallBack(checked) }
    }

    private fun editableViews() {

        binding.filmDetailsActivityEditTextCinemaName.hint  = filmData.name
        binding.filmDetailsActivityEditTextProductYear.hint = filmData.productYear
        binding.filmDetailsActivityEditTextDirector.hint = filmData.director
        binding.filmDetailsActivityEditTextProductCompany.hint= filmData.productCompany
        initSpinner()
    }

    private fun initIntentData()
    {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.TIRAMISU)
            filmData = intent.getSerializableExtra("Register") as FilmData
        else
            filmData = intent.getSerializableExtra("Register", FilmData::class.java)!!
    }

    private fun initViews()
    {
        initIntentData()
        editableViews()
        initEditSwitch()
        initUpdateButton()
    }


    private fun initBinding()
    {
        binding = ActivityFilmDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initialize()
    {
        initBinding()
        initViews()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }
}