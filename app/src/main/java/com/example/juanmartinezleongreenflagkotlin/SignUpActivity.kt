package com.example.juanmartinezleongreenflagkotlin

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.juanmartinezleongreenflag.Utilities
import com.google.android.material.textfield.TextInputEditText


class SignUpActivity : AppCompatActivity() {

    //region variable declaration
    private val errors = booleanArrayOf(true, true, true)
    private val utilitiesObj: Utilities = Utilities()
    private var loError: LinearLayout? = null
    private var btnNext: View? = null
    private var loEmail: View? = null
    private var loPass: View? = null
    //endregion

    //region Main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) supportActionBar!!.hide()
        setContentView(R.layout.activity_sign_up)
        loError = findViewById<LinearLayout>(R.id.loError)
        loEmail = findViewById<View>(R.id.loEmail)
        btnNext = findViewById<View>(R.id.btn_Next)
        loPass = findViewById<View>(R.id.loPass)
        loError?.visibility = View.GONE
        setupBackBtn()
        setupNextBtn()
        setupInputsValidationsListeners()
    }
    //endregion

    //region main program Logic
    private fun setupBackBtn() {
        val btnBack = findViewById<View>(R.id.iv_back)
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupNextBtn() {
        btnNext!!.setOnClickListener {
            val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("Success!")
            builder.setMessage("Data stored correctly")
            builder.setPositiveButton("Ok", DialogInterface.OnClickListener { _, _ ->

            })
            /*builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            // User cancelled the dialog
        }
    })*/
            val dialog: android.app.AlertDialog? = builder.create()
            dialog?.show()
        }
    }

    private fun setupInputsValidationsListeners() {
        validateEmail()
        validatePasswords()
    }

    private fun validateEmail() {
        val textInputViewEmail = findViewById<TextInputEditText>(R.id.tivEmail)
        val ivEmailCheck: ImageView = findViewById(R.id.ivEmailCheck)
        textInputViewEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                errors[0] = utilitiesObj.validateStringWithRegex(
                    textInputViewEmail.text.toString(),
                    utilitiesObj.emailRegEx
                )
                if (!errors[0]) {
                    ivEmailCheck.setImageResource(R.drawable.ic_baseline_check_24)
                    loEmail!!.setBackgroundResource(0)
                    loEmail!!.setBackgroundColor(resources.getColor(R.color.white))
                } else {
                    ivEmailCheck.setImageResource(0)
                    loEmail!!.setBackgroundResource(R.drawable.border_bwhite_red)
                }
                btnNext!!.isEnabled = validateErrors()
                setEnabledDisabledBtnBackground()
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }

    private fun validatePasswords() {
        val textInputViewPass = findViewById<TextInputEditText>(R.id.tivPass)
        val ivPassCheck: ImageView = findViewById(R.id.ivPassCheck)
        val textInputRepeatPass = findViewById<TextInputEditText>(R.id.itvRepeatPass)
        textInputViewPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                errors[1] = utilitiesObj.validateStringWithRegex(
                    textInputViewPass.text.toString(),
                    utilitiesObj.passwordRegEx
                )
                errors[2] = utilitiesObj.compareStrings(
                    textInputRepeatPass.text.toString(),
                    textInputViewPass.text.toString()
                )
                if (!errors[1]) {
                    ivPassCheck.setImageResource(R.drawable.ic_baseline_check_24)
                    loPass!!.setBackgroundResource(0)
                    loPass!!.setBackgroundColor(resources.getColor(R.color.white))
                } else {
                    ivPassCheck.setImageResource(0)
                    loPass!!.setBackgroundResource(R.drawable.border_bwhite_red)
                }
                btnNext!!.isEnabled = validateErrors()
                setEnabledDisabledBtnBackground()
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        textInputRepeatPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                errors[2] = utilitiesObj.compareStrings(
                    textInputRepeatPass.text.toString(),
                    textInputViewPass.text.toString()
                )
                if (!errors[2]) loError!!.visibility = View.GONE else loError!!.visibility =
                    View.VISIBLE
                btnNext!!.isEnabled = validateErrors()
                setEnabledDisabledBtnBackground()
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }

    private fun setEnabledDisabledBtnBackground() {
        if (btnNext!!.isEnabled) btnNext!!.setBackgroundResource(R.drawable.btn_background) else btnNext!!.setBackgroundResource(
            R.drawable.disabledbg
        )
    }

    //endregion

    //endregion
    //region inputs errors validation
    private fun validateErrors(): Boolean {
        var errorsCounter = 0
        for (error in errors) {
            if (error) errorsCounter++
        }
        return errorsCounter == 0
    }
    //endregion
}