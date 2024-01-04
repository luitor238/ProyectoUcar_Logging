package com.example.proyectoucar_logging

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SingInActivity : AppCompatActivity() {

    val fA :FirebaseAuth

    private lateinit var Email: EditText
    private lateinit var Name: EditText
    private lateinit var Surname: EditText
    private lateinit var Password1: EditText
    private lateinit var Password2: EditText
    private lateinit var Username: EditText
    private lateinit var btnSingIn: Button
    private lateinit var btnGoBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_in)

        Email = findViewById(R.id.editTextEmail)
        Name = findViewById(R.id.editTextName)
        Surname = findViewById(R.id.editTextSurname)
        Password1 = findViewById(R.id.editTextPassword)
        Password2 = findViewById(R.id.editTextRepeatPassword)
        Username = findViewById(R.id.editTextUsername)
        btnSingIn = findViewById(R.id.btnSingIn2)
        btnGoBack = findViewById(R.id.btnGoBack)

        //ACTION BUTON SING IN
        btnSingIn.setOnClickListener {
            var email = Email.text.toString()
            var password = Password2.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Email.setError("Invalid email")
                Email.isFocusable = true
            }else if (Password1.length()<8){
                Password1.setError("Minimum 8 characters")
                Password1.isFocusable = true
            }else if (Password2!=Password1){
                Password2.setError("Passwords must be the same")
                Password2.isFocusable = true
            }else{
                singInUser(email,password)
            }
        }

        //ACTION BUTON GO BACK
        btnGoBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    //METODO PARA REGISTRAR USUARIO
    private fun singInUser(correo: String, pass: String) {
        fA.createUserWithEmailAndPassword(correo, pass)
            .addOnCompleteListener(this) {
                //Si el registro es exitoso
                if (fA.isSuccessful) {
                    val user = fA.currentUser
                    val uid = user?.uid
                    val email = Email.text.toString()
                    val name = Name.text.toString()
                    val surname = Surname.text.toString()
                    val password = Password2.text.toString()
                    val username = Username.text.toString()

                    //Creamos hashmap para guardar los datos de registro
                    val datosUsuario = HashMap<String, String>()
                    datosUsuario["uid"] = uid!!
                    datosUsuario["email"] = email
                    datosUsuario["name"] = name
                    datosUsuario["surname"] = surname
                    datosUsuario["password"] = password
                    datosUsuario["username"] = username
                    datosUsuario["imagen"] = ""

                    //Iniciamos la instancia a la BD de firebase
                    val database = FirebaseDatabase.getInstance()
                    //Creamos la base de datos (USUARIOS_DE_APP es el nombre)
                    val reference = database.getReference("USUARIOS_DE_APP")
                    reference.child(uid).setValue(datosUsuario)

                    Toast.makeText(this, "Se registró", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, PerfilActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "No se registró", Toast.LENGTH_SHORT).show()
                }
            }
    }
}