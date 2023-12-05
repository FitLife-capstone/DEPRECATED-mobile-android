package com.example.fitlife.view.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.fitlife.R
import com.example.fitlife.data.Result
import com.example.fitlife.databinding.ActivitySignupBinding
import com.example.fitlife.helper.ViewModelFactory
import java.util.logging.Logger

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    val equipments: MutableList<String> = ArrayList()

    private val viewModel by viewModels<SignupViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
//        playAnimation()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.signupButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            Toast.makeText(
                this,
                equipments.toString(),
                Toast.LENGTH_LONG
            ).show()


            viewModel.register(name=name, email = email, password = password).observe(this) { result ->
                if (result != null) {
                    when (result) {
                        is Result.Loading -> {
                            binding.registerProgressBar.visibility = View.VISIBLE
                            binding.signupButton.text = ""
                        }

                        is Result.Success -> {
                            binding.registerProgressBar.visibility = View.GONE
                            binding.signupButton.text = "Sign Up"

                            AlertDialog.Builder(this).apply {
                                setTitle("Yeah!")
                                setMessage("Account with email: $email successfully created. Please login to post your story!")
                                setPositiveButton("Next") { _, _ ->
                                    finish()
                                }
                                create()
                                show()
                            }
                        }

                        is Result.Error -> {
                            binding.registerProgressBar.visibility = View.GONE
                            binding.signupButton.text = "Sign Up"
                            Toast.makeText(
                                this,
                                result.error,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

        }
    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.checkbox_equiments_bands -> {
                    if (checked) {
                        equipments.add("Bands")
                    }
                }
                R.id.checkbox_equiments_barbell -> {
                    if (checked) {
                        equipments.add("Barbell")
                    }
                }
                R.id.checkbox_equiments_kettlebells -> {
                    if (checked) {
                        equipments.add("Kettlebells")
                    }
                }
                R.id.checkbox_equiments_dumbbell -> {
                    if (checked) {
                        equipments.add("Dumbbell")
                    }
                }
                R.id.checkbox_equiments_cable -> {
                    if (checked) {
                        equipments.add("Cable")
                    }
                }
                R.id.checkbox_equiments_machine -> {
                    if (checked) {
                        equipments.add("Machine")
                    }
                }
                R.id.checkbox_equiments_body_only -> {
                    if (checked) {
                        equipments.add("Body Only")
                    }
                }
                R.id.checkbox_equiments_medicine_ball -> {
                    if (checked) {
                        equipments.add("Medicine Ball")
                    }
                }
                R.id.checkbox_equiments_exercise_ball -> {
                    if (checked) {
                        equipments.add("Exercise Ball")
                    }
                }
                R.id.checkbox_equiments_foam_roll -> {
                    if (checked) {
                        equipments.add("Foam roll")
                    }
                }
                R.id.checkbox_equiments_ez_curl_bar -> {
                    if (checked) {
                        equipments.add("E-Z Curl bar")
                    }
                }

            }
        }
    }
//    private fun playAnimation() {
//        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
//            duration = 6000
//            repeatCount = ObjectAnimator.INFINITE
//            repeatMode = ObjectAnimator.REVERSE
//        }.start()
//
//        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(200)
//        val tvName = ObjectAnimator.ofFloat(binding.nameTextView, View.ALPHA, 1f).setDuration(200)
//        val tlName = ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f).setDuration(200)
//        val tvEmail = ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(200)
//        val tlEmail = ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(200)
//        val tvPassword = ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(200)
//        val tlPassword = ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(200)
//        val btnSignUp = ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f).setDuration(200)
//
//        AnimatorSet().apply {
//            playSequentially(title, tvName, tlName, tvEmail, tlEmail, tvPassword, tlPassword, btnSignUp)
//            start()
//        }
//    }
}