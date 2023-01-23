package com.example.eztap.ui

import android.annotation.SuppressLint
import android.app.ActionBar.LayoutParams
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.lifecycle.Observer
import com.example.datalayyer.model.Uidata
import com.example.eztap.DataStore
import com.example.eztap.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainActivityViewModel by viewModels();

    lateinit var linearLayout:LinearLayout

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        linearLayout  = findViewById<LinearLayout>(R.id.mainLayout)

        viewModel.dataLoading.observe(this, Observer {

            findViewById<ProgressBar>(R.id.loadingButton).visibility = if(it) View.VISIBLE else View.GONE

        })

        viewModel.isDataLoadingError.observe(this, Observer {

            findViewById<TextView>(R.id.errorMessage).visibility = if(it.first) View.VISIBLE else View.GONE
            findViewById<TextView>(R.id.errorMessage).text = it.second

        })


        findViewById<Button>(R.id.navButton).setOnClickListener{

            goToNextActivity()

        }
    }

    override fun onResume() {
        super.onResume()

        addViews()
    }

    private fun addViews() {

        linearLayout.removeAllViews()

        viewModel.data.observe(this, Observer {

            it.forEach { uiData->

                linearLayout.addView(getView(uiData))

            }

        })

    }

    fun goToNextActivity(){

        linearLayout.removeAllViews()
        var intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)

    }

    fun getView(uidata: Uidata): View {

        var params = LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT,LinearLayoutCompat.LayoutParams.WRAP_CONTENT)
        var view:View

        when(uidata.uitype){

            "label"->{
                view = TextView(this);
                view.setText(uidata.value)
                view.layoutParams = params
                view.setTextColor(getColor(R.color.black))
            }
            "edittext"->{
                view = EditText(this);
                view.hint = uidata.value
                view.layoutParams = params
                view.setTextColor(getColor(R.color.black))
            }
            else->{
                view = Button(this);
                view.setText(uidata.value)
                view.layoutParams = params
                view.setTextColor(getColor(R.color.black))
            }

        }

        uidata.key?.let {
            DataStore.addData(it,view)
        }

        return view

    }
}