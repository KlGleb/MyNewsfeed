package at.gleb.mynewsfeed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import at.gleb.mynewsfeed.databinding.ActivityMainBinding
import at.gleb.mynewsfeed.sources.presentation.SourcesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val fragment = SourcesFragment()
        supportFragmentManager.beginTransaction().run {
            replace(R.id.container, fragment)
            addToBackStack(null)
            commit()
        }
    }
}