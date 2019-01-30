package com.leeeyou.source.code.analysis.eventbus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.leeeyou.source.code.analysis.eventbus.event.RegisterSuccessEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class GoToLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_to_login)
    }

    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun processRegisterSuccessForSticky(event: RegisterSuccessEvent?) {
        event?.also {
            Toast.makeText(this, this@GoToLoginActivity.javaClass.simpleName + " 接收到 " + event.javaClass.simpleName, Toast.LENGTH_SHORT).show()
            EventBus.getDefault().removeStickyEvent(event) //清理掉这次的粘性事件
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun processRegisterSuccess(event: RegisterSuccessEvent?) {
        event?.also {
            Toast.makeText(this, this@GoToLoginActivity.javaClass.simpleName + " 接收到 " + event.javaClass.simpleName, Toast.LENGTH_SHORT).show()
        }
    }
}
