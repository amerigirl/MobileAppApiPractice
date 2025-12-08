package com.example.apiwork

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apiwork.ui.PostAdapter
import com.example.apiwork.data.Post



class MainActivity : AppCompatActivity() {
    // Store references as properties for easier access
    private lateinit var btnLoad: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_main)

        // Find views
        btnLoad = findViewById(R.id.btnLoad)
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PostAdapter(emptyList())
        recyclerView.adapter = adapter

        // Button click - FAKE posts loading :)
        btnLoad.setOnClickListener {
            loadFakePosts()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadFakePosts() {
        progressBar.visibility = View.VISIBLE
        btnLoad.isEnabled = false

        Handler(Looper.getMainLooper()).postDelayed({
            val fakePosts = listOf(
                Post(1, 1, "First Fake Post", "This simulates API data loading..."),
                Post(1, 2, "Second Post Title", "More fake content for testing."),
                Post(1, 3, "Third Post", "RecyclerView now works perfectly!"),
                Post(1, 4, "Fourth Post", "Ready for real JSONPlaceholder API."),
                Post(1, 5, "Fifth Post", "Click reload to test again.")
            )

            adapter.posts = fakePosts  // Updates RecyclerView

            progressBar.visibility = View.GONE
            btnLoad.isEnabled = true
            btnLoad.text = "Reload Posts"
        }, 1500)  // 1.5s delay simulates network
    }


}