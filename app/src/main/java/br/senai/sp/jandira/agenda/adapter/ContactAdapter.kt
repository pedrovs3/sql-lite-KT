package br.senai.sp.jandira.agenda.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.agenda.R
import br.senai.sp.jandira.agenda.model.Contact
import br.senai.sp.jandira.agenda.ui.NewContactActivity

class ContactAdapter(val context: Context) : RecyclerView.Adapter<ContactAdapter.HolderPt>() {

    private var contactList = listOf<Contact>()

    fun updateContactList (contact: List<Contact>) {
        this.contactList = contact
        notifyDataSetChanged()
    }

    class HolderPt(view: View): RecyclerView.ViewHolder(view){
        val namePt = view.findViewById<TextView>(R.id.contact_name)
        val emailPt = view.findViewById<TextView>(R.id.contact_email)
        val phonePt = view.findViewById<TextView>(R.id.contact_phone)
        val bithdatePt = view.findViewById<TextView>(R.id.contact_birthdate)
        val imagePt = view.findViewById<TextView>(R.id.contact_initial_letter)

        val cardViewContact: CardView = view.findViewById(R.id.card_view_contact)

        fun bind(contact: Contact, context: Context) {
            namePt.text = contact.nome
            emailPt.text = contact.email
            phonePt.text = contact.telefone
            bithdatePt.text = contact.dataNascimento
            imagePt.text = contact.nome.substring(0, 1)

            cardViewContact.setOnClickListener {
                val startActivityEdit = Intent(context, NewContactActivity::class.java)

                startActivityEdit.putExtra("id_contact", contact.id)
                context.startActivity(startActivityEdit)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderPt {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_contact, parent, false)

        return HolderPt(view)
    }

    override fun onBindViewHolder(holder: HolderPt, position: Int) {
        holder.bind(this.contactList[position], context)
    }

    override fun getItemCount(): Int {
        return this.contactList.size
    }
}