package br.senai.sp.jandira.agenda.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senai.sp.jandira.agenda.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.fbNewContact.setOnClickListener{
            openRegister()
        }
    }

    private fun openRegister() {
        val openNewContactActivity = Intent(this, NewContactActivity::class.java)
        startActivity(openNewContactActivity)
    }
}