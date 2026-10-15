
package com.example.tp2

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.PopupMenu
class MainActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var email2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnPopup = findViewById<android.widget.Button>(R.id.btn_popup)

        btnPopup.setOnClickListener { view ->
            val popup = PopupMenu(this, view)

            val inflater = menuInflater
            inflater.inflate(R.menu.popup_menu, popup.menu)

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.popup_search -> {
                        Toast.makeText(this, "Popup Search sélectionné", Toast.LENGTH_SHORT
                       ).show()
                        true
                    }

                    R.id.popup_exit -> {
                        Toast.makeText(this, "Popup Exit sélectionné", Toast.LENGTH_SHORT).show()

                        true
                    }

                    else -> false
                }
            }

            popup.show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        email = findViewById(R.id.email)
        email2 = findViewById(R.id.email2)

        registerForContextMenu(email)
        registerForContextMenu(email2)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> {
                Toast.makeText(this, "Menu Search sélectionné", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.menu_exit -> {
                Toast.makeText(this, "Menu Exit sélectionné", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.menu_rouge -> {
                item.isChecked = true
                Toast.makeText(this, "Rouge sélectionné", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.menu_bleu -> {
                item.isChecked = true
                Toast.makeText(this, "Bleu sélectionné", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.contextmenu, menu)
        menu.setHeaderTitle("ContextMenu")
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as? AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.menu_search -> {
                Toast.makeText(this, "Context Menu Search sélectionné", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_ch1 -> {
                Toast.makeText(this, "Context Menu ch1 sélectionné", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }
}
