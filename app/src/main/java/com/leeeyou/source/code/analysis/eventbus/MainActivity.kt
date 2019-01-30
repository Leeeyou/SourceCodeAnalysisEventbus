package com.leeeyou.source.code.analysis.eventbus

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.leeeyou.source.code.analysis.eventbus.event.LoginSuccessEvent
import com.leeeyou.source.code.analysis.eventbus.event.RegisterSuccessEvent
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        EventBus.getDefault().register(this)

        findViewById<Button>(R.id.btn_goto_secondActivity).setOnClickListener {
            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
        }

        findViewById<Button>(R.id.btn_send_register_success_event).setOnClickListener {
            EventBus.getDefault().post(RegisterSuccessEvent())
            startActivity(Intent(this@MainActivity, GoToLoginActivity::class.java))
        }

        findViewById<Button>(R.id.btn_send_register_success_sticky_event).setOnClickListener {
            EventBus.getDefault().postSticky(RegisterSuccessEvent())
            startActivity(Intent(this@MainActivity, GoToLoginActivity::class.java))
        }
    }

    public override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun processLoginSuccess(event: LoginSuccessEvent?) {
        event?.also {
            Toast.makeText(this, this@MainActivity.javaClass.simpleName + " 接收到 " + event.javaClass.simpleName, Toast.LENGTH_SHORT).show()
        }
    }

}
