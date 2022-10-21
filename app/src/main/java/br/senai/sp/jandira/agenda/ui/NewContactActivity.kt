package br.senai.sp.jandira.agenda.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senai.sp.jandira.agenda.R
import br.senai.sp.jandira.agenda.databinding.ActivityNewContactBinding

class NewContactActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}