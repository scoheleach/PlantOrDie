package hu.ait.plantordieapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu

class SplashActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_file)

            Handler().postDelayed({
                    val intent = Intent(this, ScrollingActivity::class.java);
                    startActivity(intent);
                    finish();
            },3000)
    }
}
