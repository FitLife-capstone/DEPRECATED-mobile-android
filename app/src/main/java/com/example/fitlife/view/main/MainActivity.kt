package com.example.fitlife.view.main

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fitlife.databinding.ActivityMainBinding
import com.example.fitlife.helper.ViewModelFactory
import com.example.fitlife.view.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityMainBinding

    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val layoutManager = LinearLayoutManager(this)
//        binding.rvStories.layoutManager = layoutManager

        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            } else {
                token = user.token
            }
//            setupAction(token)
        }

        setupView()
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

//    private fun setupAction(token:String) {
//        viewModel.getAllStories(token).observe(this) { result ->
//            if (result != null) {
//                when (result) {
//                    is Result.Loading -> {
//                        binding.progressBar.visibility = View.VISIBLE
//                    }
//
//                    is Result.Success -> {
//                        binding.progressBar.visibility = View.GONE
//                        setStories(result.data.listStory as List<ListStoryItem>)
//                        Toast.makeText(
//                            this,
//                            "success get stories",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//
//                    is Result.Error -> {
//                        Toast.makeText(
//                            this,
//                            result.error,
//                            Toast.LENGTH_SHORT
//                        ).show()
//
//                    }
//                }
//            }
//        }

//        binding.addFab.setOnClickListener{
//            val moveIntent = Intent(this@MainActivity, AddStoryActivity::class.java)
//            startActivity(moveIntent)
//        }

//        binding.logoutButton.setOnClickListener {
//            viewModel.logout()
//        }
//    }

//    private fun setStories(stories: List<ListStoryItem>) {
//        val storyAdapter = StoryAdapter(stories)
//        storyAdapter.submitList(stories)
//        binding.rvStories.adapter = storyAdapter
//
//        storyAdapter.setOnItemClickCallback(object : StoryAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: ListStoryItem) {
//                if (data.name!=null && data.description!=null && data.photoUrl!=null){
//                    val story : Story = Story(data.name, data.photoUrl, data.description)
//                    val moveWithDataIntent = Intent(this@MainActivity, DetailActivity::class.java)
//                    moveWithDataIntent.putExtra(DetailActivity.EXTRA_STORY, story)
//                    startActivity(moveWithDataIntent)
//                }
//            }
//        })
//    }

//    private fun playAnimation() {
//
//        val message = ObjectAnimator.ofFloat(binding.messageTextView, View.ALPHA, 1f).setDuration(400)
//        val btnLogout = ObjectAnimator.ofFloat(binding.logoutButton, View.ALPHA, 1f).setDuration(400)
//
//        AnimatorSet().apply {
//            playSequentially(message, btnLogout)
//            start()
//        }
//    }

}