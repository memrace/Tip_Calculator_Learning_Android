package com.android.example.tipcalculator


import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable // Для обработки событий EditText
import android.text.TextWatcher // Слушатель EditText
import android.widget.EditText // Для ввода счета
import android.widget.SeekBar // Для измнения процентов
import android.widget.SeekBar.OnSeekBarChangeListener // Слушатель SeekBar
import android.widget.TextView // Для вывода текста
import androidx.databinding.DataBindingUtil
import com.android.example.tipcalculator.databinding.ActivityMainBinding
import java.lang.NumberFormatException


import java.text.NumberFormat // Для форматирования денежных сумм

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



    // Форматировщики денежных сумм и процентов
    private val currencyFormat:NumberFormat = NumberFormat.getCurrencyInstance()
    private val percentFormat:NumberFormat = NumberFormat.getPercentInstance()

    private var billAmount:Double = 0.0 // Сумма счета, введенная пользователем
    private var percent:Double = 0.15 // // Исходный процент чаевых
//    private lateinit var  amountEditText:EditText // Для отформатированной суммы счета
//    private lateinit var  percentTextView:TextView // Для вывода процента чаевых
//    private lateinit var  tipTextView:TextView // Для вывода вычисленных чаевых
//    private lateinit var  totalTextView:TextView // Для вычисленной общей суммы
//    private lateinit var amountTextView:TextView // Рез ввода
//    private lateinit var percentSeekBar:SeekBar
    private fun calculate() {
        binding.percentTextView.text = percentFormat.format(percent)

        var tip:Double = billAmount*percent
        var total:Double = billAmount+tip

    binding.tipTextView.setText(currencyFormat.format(tip))
    binding.totalTextView.setText(currencyFormat.format(total))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.tipTextView.setText(currencyFormat.format(0))
        binding.totalTextView.setText(currencyFormat.format(0))

        // Назначение слушателя TextWatcher для amountEditText
        binding.amountEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                try {
                     // Получить счет и вывести в денеж формате
                    billAmount = s.toString().toDouble()
                    binding.amountTextView.setText(currencyFormat.format(billAmount))
                } catch (e: NumberFormatException) {binding.amountTextView.text=""
                    billAmount = 0.0
                    }
                calculate()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        binding.percentSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                percent = progress / 100.0
                calculate()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })



    }
}


