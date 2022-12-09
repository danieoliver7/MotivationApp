package com.example.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.motivation.R
import com.example.motivation.databinding.ActivityUserBinding
import com.example.motivation.infrastructure.MotivationConstants
import com.example.motivation.infrastructure.SecurityPreferences

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.buttonSave.setOnClickListener(this)


        supportActionBar?.hide()

        verifyUserName()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_save) {
            handrleSave()
        }
    }

    private fun verifyUserName(){
        val name = SecurityPreferences(this).getString(MotivationConstants.key.USER_NAME)
        if ( name != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun handrleSave() {

        val name = binding.editName.text.toString()

        if (name != "") {

            SecurityPreferences(this).storeString(MotivationConstants.key.USER_NAME, name)

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(
                this, R.string.validation_mandatory_name,
                Toast.LENGTH_SHORT
            ).show()
        }


    }
}