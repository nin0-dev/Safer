
package com.nin0dev.safer

import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.provider.Settings
import android.provider.Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS
import android.provider.Settings.ACTION_SYNC_SETTINGS
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.google.android.material.color.DynamicColors
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyToActivityIfAvailable(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttons()
    }

    fun buttons()
    {
        val startSetupButton = findViewById<Button>(R.id.startButton)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val backButton = findViewById<Button>(R.id.backButton)
        val backButton2 = findViewById<Button>(R.id.backButton2)
        val backButton3 = findViewById<Button>(R.id.backButton3)
        val devOptionsButton = findViewById<Button>(R.id.showDevOptionsButton)
        val accountsButton = findViewById<Button>(R.id.accountSettingsButton)
        val checkPermsButton = findViewById<Button>(R.id.checkPermsButton)
        val aboutPhoneButton = findViewById<Button>(R.id.showAboutPhoneButton)

        startSetupButton.setOnClickListener {
            findViewById<CoordinatorLayout>(R.id.start).visibility = GONE
            findViewById<CoordinatorLayout>(R.id.accounts).visibility = VISIBLE
        }
        nextButton.setOnClickListener {
            findViewById<CoordinatorLayout>(R.id.accounts).visibility = GONE
            findViewById<CoordinatorLayout>(R.id.devOptions).visibility = VISIBLE
        }
        accountsButton.setOnClickListener {
            startActivity(Intent().setAction(ACTION_SYNC_SETTINGS))
            findViewById<Button>(R.id.nextButton).isEnabled = true
        }
        aboutPhoneButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.component =
                ComponentName("com.android.settings", "com.android.settings.Settings\$MyDeviceInfoActivity")
            startActivityForResult(intent, 2)

        }
        backButton.setOnClickListener {
            findViewById<CoordinatorLayout>(R.id.accounts).visibility = VISIBLE
            findViewById<CoordinatorLayout>(R.id.devOptions).visibility = GONE
        }
        backButton2.setOnClickListener {
            findViewById<CoordinatorLayout>(R.id.devOptions).visibility = VISIBLE
            findViewById<CoordinatorLayout>(R.id.adbDebugging).visibility = GONE
        }
        backButton3.setOnClickListener {
            findViewById<CoordinatorLayout>(R.id.adbDebugging).visibility = VISIBLE
            findViewById<CoordinatorLayout>(R.id.connectToPC).visibility = GONE
        }
        devOptionsButton.setOnClickListener {
            val intent = Intent(ACTION_APPLICATION_DEVELOPMENT_SETTINGS)
            startActivityForResult(intent, 3)

        }
        checkPermsButton.setOnClickListener {

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 2) {
            if(Settings.Global.getInt(contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED) == 1)
            {
                findViewById<CoordinatorLayout>(R.id.devOptions).visibility = GONE
                findViewById<CoordinatorLayout>(R.id.adbDebugging).visibility = VISIBLE
            }
            else
            {
                val contextView = findViewById<CoordinatorLayout>(R.id.devOptions)
                Snackbar.make(contextView, getString(R.string.devOptionsNotEnabled), Snackbar.LENGTH_LONG).setBackgroundTint(resources.getColor(R.color.colorsnackbarSurface)).setTextColor(resources.getColor(R.color.snackbarText)).show()
            }
        }
        if(requestCode == 3) {
            findViewById<CoordinatorLayout>(R.id.adbDebugging).visibility = GONE
            findViewById<CoordinatorLayout>(R.id.connectToPC).visibility = VISIBLE
        }
    }
}