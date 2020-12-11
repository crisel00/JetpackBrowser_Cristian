package com.cromero.jetpackbrowseretccristianromero

import android.R.attr.key
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_calc.*
import kotlinx.android.synthetic.main.fragment_fr_camera.*


class MainActivity : AppCompatActivity() {

    val CAMERA_REQUEST = 2000

    public var resul = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    //Botones Menu Principal
    fun onBrowserClick(view: View){
        Navigation.findNavController(view).navigate(R.id.acBrowser)
    }

    fun onCameraClick(view: View){
        Navigation.findNavController(view).navigate(R.id.acCamera)
    }

    fun onCalcClick(view: View){
        Navigation.findNavController(view).navigate(R.id.acCalc)
    }

    //Botones Modo camara
    fun onPhotoClick(view: View){
        val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(i)
    }



    //Botones Modo Calcular
    fun onCalcularClick(view: View){
        val num1 = Integer.parseInt(tf_Num1.text.toString())
        val num2 = Integer.parseInt(tf_Num2.text.toString())



        when(rg_Operacion.checkedRadioButtonId){
            R.id.rb_Suma -> resul = num1 + num2
            R.id.rb_Resta -> resul = num1 - num2
            R.id.rb_Multi -> resul = num1 * num2
            R.id.rb_Divi -> resul = num1 / num2
        }

        val bundle = Bundle()
        bundle.putInt("RESULT", resul)
        Navigation.findNavController(view).navigate(R.id.acMulti, bundle)
    }

}