package com.fyproject.fy_project_3as.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate
import com.fyproject.fy_project_3as.R
import com.fyproject.fy_project_3as.model.ModelUser
import com.fyproject.fy_project_3as.model.ModelUser.save
import com.fyproject.fy_project_3as.utility.Tools
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.lang.Exception

class RegisterActivity : AppCompatActivity(){

    var db = FirebaseFirestore.getInstance()
    var modelUser = ModelUser()
    var signed_in_user: ModelUser? = null
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        signed_in_user = Tools.get_signed_in_user()

        if (signed_in_user != null) {
            Toast.makeText(this, "You are logged", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        initComponent()
    }

    var user_name: EditText? = null
    var email: EditText? = null
    var school_name: EditText? = null
    var phone_number: EditText? = null
    var password: EditText? = null
    var confirm_password: EditText? = null
    var profile_photo: ImageView? = null
    var register: Button? = null
    var login: ImageView? = null

    private fun initComponent() {
        modelUser.student_id = db.collection("STUDENT_TABLE").document().id
        user_name = findViewById<EditText>(R.id.et_user_name)
        email = findViewById<EditText>(R.id.et_email)
        school_name = findViewById<EditText>(R.id.et_school_name)
        phone_number = findViewById<EditText>(R.id.et_phone)
        password = findViewById<EditText>(R.id.et_password)
        confirm_password = findViewById<EditText>(R.id.et_confirm_password)
        profile_photo = findViewById(R.id.iv_add_photo)
        register = findViewById(R.id.btn_register)
        login = findViewById(R.id.iv_next)

        register?.setOnClickListener(View.OnClickListener {
            validate()
            val i = Intent(this, MainActivity::class.java)
            this.startActivity(i)
        })

        profile_photo?.setOnClickListener(View.OnClickListener {
            selectImage()
        })

        login?.setOnClickListener(View.OnClickListener {
            val i = Intent(this,LoginActivity::class.java)
            this.startActivity(i)
        })
    }

    //
    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent,"Select Image"), PICK_IMAGE_REQUEST
        )
    }

    var imageurl: Uri? = null
    var storage_ref: StorageReference? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null ) {
            imageurl = data.data

            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageurl)
                profile_photo?.setImageBitmap(bitmap)
            }catch (e:IOException){
                e.printStackTrace()
            }
        }
    }


    //check validation
    private fun validate() {
        modelUser.user_name = user_name!!.text.toString()
        if (modelUser.user_name.isEmpty()) {
            Toast.makeText(this, "user name cannot be empty", Toast.LENGTH_SHORT).show()
        }

        modelUser.email = email!!.text.toString()
        if (modelUser.email.isEmpty()) {
            Toast.makeText(this, "email cannot be empty", Toast.LENGTH_SHORT).show()
        }

        modelUser.school_name = school_name!!.text.toString()
        if (modelUser.school_name.isEmpty()) {
            Toast.makeText(this, "school name cannot be empty", Toast.LENGTH_SHORT).show()
        }

        modelUser.phone_number = phone_number!!.text.toString()
        if (modelUser.phone_number.isEmpty()) {
            Toast.makeText(this, "phone number cannot be empty", Toast.LENGTH_SHORT).show()
        }

        modelUser.password = password!!.text.toString()
        if (modelUser.password.isEmpty()) {
            Toast.makeText(this, "password cannot be empty", Toast.LENGTH_SHORT).show()
        }

        modelUser.confirm_password = confirm_password!!.text.toString()
        if (modelUser.confirm_password.isEmpty()) {
            Toast.makeText(this, "Retype your password", Toast.LENGTH_SHORT).show()
        }


        storage_ref = FirebaseStorage.getInstance().reference
        storage_ref!!.child("customers/" + modelUser.student_id).putFile(imageurl!!)
            .addOnSuccessListener(
                OnSuccessListener {
                    Toast.makeText(this, "Uploaded successfully!", Toast.LENGTH_SHORT).show()

                    storage_ref!!.child("customers/" + modelUser.student_id).downloadUrl.addOnSuccessListener(
                        OnSuccessListener { uri ->

                            modelUser.profile_photo = uri.toString()
                            send_data()

                            return@OnSuccessListener

                        }
                    ).addOnFailureListener(OnFailureListener {
                        modelUser.profile_photo =
                            "https://www.pexels.com/photo/person-taking-a-photo-using-iphone-36675/"
                        send_data()
                        return@OnFailureListener
                    })

                }
            ).addOnFailureListener(OnFailureListener { e ->
                Toast.makeText(this, "failed to upload photo " + e.message, Toast.LENGTH_SHORT)
                    .show()
                Log.d("err", e.message.toString())
                modelUser.profile_photo =
                    "https://www.pexels.com/photo/person-taking-a-photo-using-iphone-36675/"
                send_data()
                return@OnFailureListener
            })
    }


    // send data to firebase
    private fun send_data(){
        db.collection("STUDENT_TABLE").whereEqualTo("email", modelUser.email).get()
            .addOnSuccessListener(OnSuccessListener {
                queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty){
                    Toast.makeText(this, "Email already exist", Toast.LENGTH_SHORT).show()
                    return@OnSuccessListener
                }
                modelUser.student_id = db.collection("STUDENT_TABLE").document().id
                db.collection("STUDENT_TABLE").document(modelUser.student_id).set(modelUser)
                    .addOnSuccessListener(OnSuccessListener {
                        Toast.makeText(this,"User account created successfully", Toast.LENGTH_SHORT).show()

                        if (logged_user_to_sql()){
                            Toast.makeText(this,"login successfully", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this,"login failed", Toast.LENGTH_SHORT).show()
                        }
                    }).addOnFailureListener {
                        Toast.makeText(this,"Failed to create an account", Toast.LENGTH_SHORT).show()
                    }
            }).addOnFailureListener {
                Toast.makeText(this,"Failed to create an account",Toast.LENGTH_SHORT).show()
            }

    }

    private fun logged_user_to_sql(): Boolean {
        return try {
            save(modelUser)
            true
        }catch (e: Exception){
            false
        }
    }
}