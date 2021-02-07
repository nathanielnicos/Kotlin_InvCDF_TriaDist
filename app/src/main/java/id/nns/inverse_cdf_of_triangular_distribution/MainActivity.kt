package id.nns.inverse_cdf_of_triangular_distribution

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.sqrt

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etU: EditText
    private lateinit var etA: EditText
    private lateinit var etM: EditText
    private lateinit var etB: EditText
    private lateinit var btnCalc: Button
    private lateinit var tvResult: TextView

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etU = findViewById(R.id.et_u)
        etA = findViewById(R.id.et_a)
        etM = findViewById(R.id.et_m)
        etB = findViewById(R.id.et_b)
        btnCalc = findViewById(R.id.btn_calc)
        tvResult = findViewById(R.id.tv_result)

        btnCalc.setOnClickListener(this)

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT) as String
            tvResult.text = result
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_calc) {
            val inputU = etU.text.toString()
            val inputA = etA.text.toString()
            val inputM = etM.text.toString()
            val inputB = etB.text.toString()

            var isEmpty = false

            if (inputU.isEmpty()) {
                isEmpty = true
                etU.error = "Empty field!"
            }

            if (inputA.isEmpty()) {
                isEmpty = true
                etA.error = "Empty field!"
            }

            if (inputM.isEmpty()) {
                isEmpty = true
                etM.error = "Empty field!"
            }

            if (inputB.isEmpty()) {
                isEmpty = true
                etB.error = "Empty field!"
            }

            if (!isEmpty) {
                val inverse =
                    if (inputU.toDouble() <= (inputM.toDouble() - inputA.toDouble()) / (inputB.toDouble() - inputA.toDouble())) {
                        inputA.toDouble() + sqrt(inputU.toDouble() * (inputM.toDouble() - inputA.toDouble()) * (inputB.toDouble() - inputA.toDouble()))
                    } else {
                        inputB.toDouble() - sqrt((1 - inputU.toDouble()) * (inputB.toDouble() - inputM.toDouble()) * (inputB.toDouble() - inputA.toDouble()))
                    }

                tvResult.text = "F^-1 (u | a, m, b) = $inverse"
            }
        }
    }
}