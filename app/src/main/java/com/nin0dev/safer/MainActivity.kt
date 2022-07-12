
package com.nin0dev.safer

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS
import android.provider.Settings.ACTION_SYNC_SETTINGS
import android.text.InputType
import android.util.Log
import android.view.View.*
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.text.set
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
        val savePINButton = findViewById<Button>(R.id.saveSaferPINButton)

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
            val manager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
            if(manager.isProfileOwnerApp("com.nin0dev.safer"))
            {
                findViewById<CoordinatorLayout>(R.id.connectToPC).visibility = GONE
                findViewById<CoordinatorLayout>(R.id.pin).visibility = VISIBLE
            }
            else
            {
                findViewById<CoordinatorLayout>(R.id.connectToPC).visibility = GONE
                findViewById<CoordinatorLayout>(R.id.accounts).visibility = VISIBLE
                val contextView = findViewById<CoordinatorLayout>(R.id.accounts)
                Snackbar.make(contextView, getString(R.string.permsNotGranted), Snackbar.LENGTH_LONG).setBackgroundTint(resources.getColor(R.color.colorsnackbarSurface)).setTextColor(resources.getColor(R.color.snackbarText)).show()
            }
        }
        savePINButton.setOnClickListener {
            val textField = findViewById<EditText>(R.id.setup_pin_field)
            var currentValue = textField.getText().toString()
            if(currentValue.length < 4)
            {
                val contextView = findViewById<CoordinatorLayout>(R.id.pin)
                Snackbar.make(contextView, getString(R.string.too_short), Snackbar.LENGTH_LONG).setBackgroundTint(resources.getColor(R.color.colorsnackbarSurface)).setTextColor(resources.getColor(R.color.snackbarText)).show()
            }
            else
            {
                val sp = getSharedPreferences("settings", Context.MODE_PRIVATE)
                val e = sp.edit()
                e.putString("pin", currentValue)
                e.apply()
            }
        }

        pinButtons()
    }

    fun pinButtons()
    {
        val textField = findViewById<EditText>(R.id.setup_pin_field)
        val button1 = findViewById<Button>(R.id.setup_pin_1)
        val button2 = findViewById<Button>(R.id.setup_pin_2)
        val button3 = findViewById<Button>(R.id.setup_pin_3)
        val button4 = findViewById<Button>(R.id.setup_pin_4)
        val button5 = findViewById<Button>(R.id.setup_pin_5)
        val button6 = findViewById<Button>(R.id.setup_pin_6)
        val button7 = findViewById<Button>(R.id.setup_pin_7)
        val button8 = findViewById<Button>(R.id.setup_pin_8)
        val button9 = findViewById<Button>(R.id.setup_pin_9)
        val button0 = findViewById<Button>(R.id.setup_pin_0)
        val buttonerase = findViewById<Button>(R.id.setup_pin_erase)
        val buttonshow = findViewById<Button>(R.id.setup_pin_visible)

        button1.setOnClickListener {
            textField.setText(textField.text.toString() + "1")
        }
        button2.setOnClickListener {
            textField.setText(textField.text.toString() + "2")
        }
        button3.setOnClickListener {
            textField.setText(textField.text.toString() + "3")
        }
        button4.setOnClickListener {
            textField.setText(textField.text.toString() + "4")
        }
        button5.setOnClickListener {
            textField.setText(textField.text.toString() + "5")
        }
        button6.setOnClickListener {
            textField.setText(textField.text.toString() + "6")
        }
        button7.setOnClickListener {
            textField.setText(textField.text.toString() + "7")
        }
        button8.setOnClickListener {
            textField.setText(textField.text.toString() + "8")
        }
        button9.setOnClickListener {
            textField.setText(textField.text.toString() + "9")
        }
        button0.setOnClickListener {
            textField.setText(textField.text.toString() + "0")
        }
        buttonerase.setOnClickListener {
            try {
                var currentValue = textField.getText().toString()
                Log.e("dd", currentValue)
                currentValue = currentValue.substringBefore(currentValue[currentValue.length - 1])
                Log.e("dd", currentValue)
                textField.setText(currentValue)
            }
            catch (e: java.lang.Exception) {

            }
        }
        buttonshow.setOnClickListener {
            textField.inputType = InputType.TYPE_CLASS_NUMBER
            buttonshow.visibility = INVISIBLE
            buttonshow.isEnabled = false
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
            if(Settings.Global.getInt(contentResolver, Settings.Secure.ADB_ENABLED) == 1)
            {
                findViewById<CoordinatorLayout>(R.id.adbDebugging).visibility = GONE
                findViewById<CoordinatorLayout>(R.id.connectToPC).visibility = VISIBLE
            }
            else
            {
                val contextView = findViewById<CoordinatorLayout>(R.id.adbDebugging)
                Snackbar.make(contextView, getString(R.string.usbDebugOff), Snackbar.LENGTH_LONG).setBackgroundTint(resources.getColor(R.color.colorsnackbarSurface)).setTextColor(resources.getColor(R.color.snackbarText)).show()
            }
        }
    }
}