package com.fyproject.fy_project_3as.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.fyproject.fy_project_3as.R
import com.fyproject.fy_project_3as.model.ModelUser
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    var email: EditText? = null
    var password: EditText? = null
    var login: Button? = null
    var register: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initComponent();
    }

    private fun initComponent() {
        email = findViewById(R.id.et_email)
        password = findViewById(R.id.et_password)
        login = findViewById(R.id.btn_login)
        register = findViewById(R.id.tv_register)

        login?.setOnClickListener(View.OnClickListener {
            val i = Intent(this,MainActivity::class.java)
            this.startActivity(i)
        })

        register?.setOnClickListener(View.OnClickListener {
            val i = Intent(this,RegisterActivity::class.java)
            this.startActivity(i)
        })
    }

    var email_value = ""
    var password_value = ""

    var db =FirebaseFirestore.getInstance()

    private fun sign_in() {
        email_value = email?.getText().toString().trim { it <= ' '}
        password_value = password?.getText().toString().trim { it <= ' '}

        if (email_value.isEmpty() || password_value.isEmpty()){
            Toast.makeText(this,"Fill both email & password", Toast.LENGTH_SHORT).show()
            return
        }
        db.collection("STUDENT_TABLE").whereEqualTo("email",email_value).get()
            .addOnSuccessListener(OnSuccessListener { queryDocumentSnapshots ->
                if (queryDocumentSnapshots == null){
                    Toast.makeText(this,"Email not found", Toast.LENGTH_SHORT).show()
                    return@OnSuccessListener
                }
                if (queryDocumentSnapshots.isEmpty){
                    Toast.makeText(this,"Email not found on our database",Toast.LENGTH_SHORT).show()
                    return@OnSuccessListener
                }

                val student = queryDocumentSnapshots.toObjects(
                    ModelUser::class.java
                )

                if (!student[0].password.equals(password_value)){
                    Toast.makeText(this,"Wrong password",Toast.LENGTH_SHORT).show()
                }
                if (logged_user_to_sql(student[0])){
                    Toast.makeText(this,"login success", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"failed to login",Toast.LENGTH_SHORT).show()
                }
            }).addOnFailureListener { e ->
                Toast.makeText(this,"failed to login because " + e.message, Toast.LENGTH_SHORT).show()
            }
    }

    private fun logged_user_to_sql(s: ModelUser?): Boolean {
        return try {
            if (s != null) {
                ModelUser.save(s)
            }
            true
        }catch (e: Exception){
            false
        }
    }
}