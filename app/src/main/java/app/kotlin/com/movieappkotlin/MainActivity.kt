package app.kotlin.com.movieappkotlin

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.view.Menu
import android.view.MenuItem
import app.kotlin.com.movieappkotlin.fragments.MainFragment
import app.kotlin.com.movieappkotlin.models.Configuration
import app.kotlin.com.movieappkotlin.utils.CONFIG
import app.kotlin.com.movieappkotlin.utils.ERROR_TAG

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)


      //  Log.e(ERROR_TAG,"-->"+(this.intent.getSerializableExtra(CONFIG) as Configuration).images.base_url)

        goToFragment(MainFragment())

    }

    fun goToFragment(fragment: Fragment){
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container,fragment)
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
