package br.senai.sp.jandira.agenda.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senai.sp.jandira.agenda.R
import br.senai.sp.jandira.agenda.databinding.ActivityNewContactBinding
import br.senai.sp.jandira.agenda.model.Contact
import br.senai.sp.jandira.agenda.repository.ContactRepository
import java.time.LocalDate

class NewContactActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewContactBinding
    lateinit var contactRepository: ContactRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            save()
            finish()
        }
    }

    private fun save() {
        // Contact object
        var contato = getFormData()

        // Instancia do repository para salvar o contato no SQL-Lite
        contactRepository = ContactRepository(this)
        val id = contactRepository.save(contato)

        Toast.makeText(this, "ID: $id", Toast.LENGTH_LONG).show()
    }

    fun getFormData(): Contact {
        // Pegando os dados do formulario
        var name = binding.editTextName.text.toString()
        var dataNascimento = binding.editTextDate.text.toString()
        var email = binding.editTextEmail.text.toString()
        var telefone = binding.editTextPhone.text.toString()

        val contato = Contact()
        contato.nome = name
        contato.dataNascimento = dataNascimento
        contato.email = email
        contato.telefone = telefone

        return contato
    }
}