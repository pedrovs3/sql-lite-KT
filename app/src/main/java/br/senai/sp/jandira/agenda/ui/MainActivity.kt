package br.senai.sp.jandira.agenda.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.senai.sp.jandira.agenda.adapter.ContactAdapter
import br.senai.sp.jandira.agenda.databinding.ActivityMainBinding
import br.senai.sp.jandira.agenda.repository.ContactRepository

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var contactRepository: ContactRepository

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

    private fun loadHolders() {
        binding.rvContacts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        contactAdapter = ContactAdapter(this)
        contactRepository = ContactRepository(this)
        val contacts = contactRepository.getAll();
        contactAdapter.updateContactList(contacts)

        binding.rvContacts.adapter = contactAdapter
    }

    override fun onResume() {
        super.onResume()
        loadHolders()
    }
}