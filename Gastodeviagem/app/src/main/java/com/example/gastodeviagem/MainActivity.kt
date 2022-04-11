package com.example.gastodeviagem

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.graphics.convertTo
import com.example.gastodeviagem.R.id.*
import java.math.BigDecimal


class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonCalculate = findViewById<TextView>(R.id.buttonCalculate)
        buttonCalculate.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonCalculate) {
            calculate()
        }
    }

    @SuppressLint("ResourceType")
    private fun calculate() {
        val distance = getText(editDistance)
        val price = getText(editPrice)
        val autonomy = getText(editAutonomy)
        var valueFinal = getText(totalValue)

//        operator fun BigDecimal.times(other: BigDecimal) {
//            val tot = (price * distance) / autonomy
//        }

        val totValue = (price * distance) / autonomy
        valueFinal = "R$ ${"%.2f".format(totValue)}"

    }

//    operator fun BigDecimal.times(other: BigDecimal) {
//        val tot = editPrice * editDistance / editAutonomy
//    }
}

private operator fun Any.div(autonomy: Any) {

}

private operator fun Any.times(distance: Any) {

}



