package xinandaxue.java.lotteryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    //抽奖名单
    var names = listOf<String>("张三","李四","老王")
    //定时器 每隔一段时间切换一次名字
    lateinit var timer: Timer
    //纪录当前索引
    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        //设置默认显示第一个人
        mNameTextView.text = names[0]

        //响应按钮点击事件
        mStartBtn.setOnClickListener{
            //判断当前是STRAT还是STOP
            if (mStartBtn.text.toString() == "Start"){
                mStartBtn.text = "Stop"
                //创建定时器
                timer = Timer()
                //分配一个定时任务
                timer.schedule(object : TimerTask(){
                    override fun run() {
                        //判断是否越界
                        index = if (index+1 > names.size-1) 0 else index+1
                        //取出对应的名字
                        mNameTextView.text = names[index]
                    }
                },0,100)
            }else{
                mStartBtn.text = "Start"
                timer.cancel()
            }
        }
    }
}