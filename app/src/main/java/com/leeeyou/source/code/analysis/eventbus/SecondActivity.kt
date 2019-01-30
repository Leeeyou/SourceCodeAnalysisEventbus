package com.leeeyou.source.code.analysis.eventbus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.leeeyou.source.code.analysis.eventbus.event.LoginSuccessEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<Button>(R.id.btn_normal_event1).setOnClickListener {
            EventBus.getDefault().post(LoginSuccessEvent())
        }
    }

    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun processLoginSuccessForNotify(event: LoginSuccessEvent?) {
        event?.also {
            Toast.makeText(this, this@SecondActivity.javaClass.simpleName + " 接收到 " + event.javaClass.simpleName, Toast.LENGTH_SHORT).show()
        }
    }

}
