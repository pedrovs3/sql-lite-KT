package br.senai.sp.jandira.agenda.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.senai.sp.jandira.agenda.R
import br.senai.sp.jandira.agenda.adapter.ContactAdapter
import br.senai.sp.jandira.agenda.databinding.ActivityNewContactBinding
import br.senai.sp.jandira.agenda.model.Contact
import br.senai.sp.jandira.agenda.repository.ContactRepository
import java.time.LocalDate
import java.util.*

class NewContactActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewContactBinding
    lateinit var repository: ContactRepository
    lateinit var contact: Contact
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getIntExtra("id_contact", 0)
        repository = ContactRepository(this)

        if(id > 0) {
            contact = repository.getContactById(id)
            getDataById(id)
            binding.buttonSave.text = "Update Contact"
            binding.buttonDelete.visibility = View.VISIBLE
        }

        binding.buttonDelete.setOnClickListener {
            delete()
        }

        binding.buttonSave.setOnClickListener {
            if (id > 0) {
                update()
            } else {
                save()
            }

        }
    }

    private fun delete() {
        val contact = repository.getContactById(id)
        val confirmation = AlertDialog.Builder(this)
        confirmation.setTitle("Exclusão")
        confirmation.setMessage("Tem certeza que deseja excluir o contato \"${contact.nome}\" ?")

        confirmation.setPositiveButton("Sim") { _, _ ->
            repository.delete(contact)
            finish()
        }

        confirmation.setNegativeButton("Não") { dialog, _ ->
            dialog.cancel()
        }

        confirmation.show()
    }

    private fun getDataById(codigo: Int) {
        val repository = ContactRepository(this)
        val contact = repository.getContactById(codigo)

        binding.editTextName.setText(contact.nome)
        binding.editTextEmail.setText(contact.email)
        binding.editTextPhone.setText(contact.telefone)
        binding.editTextDate.setText(contact.dataNascimento)
    }

    private fun save() {
        // Contact object
        var contato = getFormData()

        // Instancia do repository para salvar o contato no SQL-Lite
        val id = repository.save(contato)

        finish()
    }

    private fun update() {
        contact = getFormData()
        contact.id = id

        repository.update(contact)
        finish()
    }

    fun getFormData(): Contact {
        // Pegando os dados do formulario
        var name = binding.editTextName.text.toString()
        var dataNascimento = binding.editTextDate.text.toString()
        if(dataNascimento == "") {
            dataNascimento = "Nenhum dado foi enviado."
        }
        var email = binding.editTextEmail.text.toString()
        var telefone = PhoneNumberUtils.formatNumber(binding.editTextPhone.text.toString(), Locale.getDefault().country)

        val contato = Contact()
        contato.nome = name
        contato.dataNascimento = dataNascimento
        contato.email = email
        contato.telefone = telefone

        return contato
    }
}