package com.metehanmengen.app.cinemainformation


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.metehanmengen.app.cinemainformation.data.CinemaTypes
import com.metehanmengen.app.cinemainformation.data.FilmData
import com.metehanmengen.app.cinemainformation.data.RegisterFilms
import com.metehanmengen.app.cinemainformation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding



    private fun initListButtonView()
    {
        binding.mainActivityButtonList.setOnClickListener{
            Intent(this, FilmListActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    private fun addButtonClickCallBack()
    {
        RegisterFilms.save(createFilmRegister())
    }

    private fun initAddButtonView()
    {
        binding.mainActivityButtonAdd.setOnClickListener {addButtonClickCallBack()}
    }


    private fun createFilmRegister() : FilmData
    {
        val name = binding.mainActivityEditTextCinemaName.text.toString()
        val year = binding.mainActivityEditTextProductYear.text.toString()
        var director = binding.mainActivityEditTextDirector.text.toString()
        val company = binding.mainActivityEditTextProductCompany.text.toString()
        val filmType = binding.mainActivitySpinnerType.selectedItem as String

        return FilmData(name, filmType, year, director, company);
    }
    /*
    private fun initRegisterSpinnerData(type : Array<String>)
    {
        binding.mainActivitySpinnerType.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, type)
        binding.mainActivitySpinnerType.setSelection(type.size - 1)

        /* bu şekilde de yapılabilir.
        binding.mainActivitySpinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long ) {
                Toast.makeText(applicationContext, "Selected : ${type[pos]}",Toast.LENGTH_SHORT).show()
                filmType = type[pos]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        */
    }

     */


    private fun initSpinnerView()
    {
        val type = CinemaTypes.values()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, type.map { type -> type.type })

        binding.mainActivitySpinnerType.adapter = adapter

    }

    private fun initViews()
    {
        initSpinnerView()
        initAddButtonView()
        initListButtonView()
    }

    private fun initBinding()
    {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initialize()
    {
        initBinding()
        initViews()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Sinema Bilgileri"
        initialize()
    }
}