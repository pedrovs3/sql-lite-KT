package br.senai.sp.jandira.agenda.model

import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "tbl_contact")
class Contact {

    @PrimaryKey(autoGenerate = true) var id = 0

    var nome = ""
    var email = ""
    var telefone = ""
    var foto:Drawable? = null
    @ColumnInfo(name = "data_nascimento") var dataNascimento:LocalDate? = null

}

/*
* @Entity(tableName = "tbl_contact")
* data class Contato(
*   @PrimaryKey(autoGenerate = true)var id:Int,
*   var nome:String,
*   var email:String,
*   var telefone:String,
*   foto:Drawable?,
*   @ColumnInfo(name = "data_nascimento") var dataNascimento:LocalDate?
* )
* */