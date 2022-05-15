package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/*import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;*/



public class MainActivity extends AppCompatActivity {
    private EditText display;
    private String cadenaDisplay = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (EditText) findViewById(R.id.display);
    }
    public void pintaTexto(View vista){

        Button boton = (Button) vista;
        cadenaDisplay += boton.getText();
        display.setText(cadenaDisplay);

    }
    public void limpiarDisplay(View vista){
        cadenaDisplay = "";
        display.setText(cadenaDisplay);
    }
    public void calcularOperacion(View vista)/* throws ScriptException*/{
        if(!validarParentesis(display.getText().toString())){
            System.out.println("error");
        }else{

        }
    }

    public static boolean validarParentesis(String cadena){
        return true;
    }
}