
package com.nin0dev.safer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.ACTION_SYNC_SETTINGS
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import androidx.coordinatorlayout.widget.CoordinatorLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttons()
    }

    fun buttons()
    {
        val startSetupButton = findViewById<Button>(R.id.startButton)
        val accountsButton = findViewById<Button>(R.id.accountSettingsButton)

        startSetupButton.setOnClickListener {
            findViewById<CoordinatorLayout>(R.id.start).visibility = GONE
            findViewById<CoordinatorLayout>(R.id.accounts).visibility = VISIBLE
        }
        accountsButton.setOnClickListener {
            startActivity(Intent().setAction(ACTION_SYNC_SETTINGS))
            findViewById<Button>(R.id.nextButton).isEnabled = true
        }
    }
}