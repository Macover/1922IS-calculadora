package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.*;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
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

    public void pintaTexto(View vista) {
        Button boton = (Button) vista;
        cadenaDisplay += boton.getText();
        display.setText(cadenaDisplay);
    }

    public void pintaTexto2(View vista) {
        Button boton = (Button) vista;
        cadenaDisplay += " " + boton.getText() + " ";
        display.setText(cadenaDisplay);
    }

    public void limpiarDisplay(View vista) {
        cadenaDisplay = "";
        display.setText(cadenaDisplay);
    }

    public void calcularOperacion(View vista) {
        Expression exp = new Expression(this.cadenaDisplay);
        String result = String.valueOf(exp.calculate());

        if (result != "NaN") {
            this.display.setText(result);
        } else {
            this.display.setText("Syntax Error!");
        }
        this.cadenaDisplay = "";
    }

    //Calcular operacion Metodo2
    public void calcularoperacion2(View view) {
        String[] display = cadenaDisplay.split(" ");
        List<String> listAux = new ArrayList<>();
        for (int a = 0; a < display.length; a++) {
            listAux.add(display[a]);
        }
        double res = 0;
        String[] opds = {"/", "*", "+", "-"};
        for (int j = 0; j < opds.length; j++) {
            int i = 0;
            for (i = 0; i < listAux.size(); i++) {
                if (opds[j].equals("/") && listAux.get(i).equals("/")) {
                    res = Double.parseDouble(listAux.get(i - 1)) / Double.parseDouble(listAux.get(i + 1));
                    //Correciones
                    int b = i + 1;
                    listAux.remove(b);
                    b = i - 1;
                    listAux.set(b, String.valueOf(res));
                    listAux.remove(i);
                    i = 0;
                }
                if (opds[j].equals("*") && listAux.get(i).equals("*")) {
                    res = Double.parseDouble(listAux.get(i - 1)) * Double.parseDouble(listAux.get(i + 1));
                    //Correciones
                    int b = i + 1;
                    listAux.remove(b);
                    b = i - 1;
                    listAux.set(b, String.valueOf(res));
                    listAux.remove(i);
                    i = 0;
                }
                if (opds[j].equals("+") && listAux.get(i).equals("+")) {
                    res = Double.parseDouble(listAux.get(i - 1)) + Double.parseDouble(listAux.get(i + 1));
                    //Correciones
                    int b = i + 1;
                    listAux.remove(b);
                    b = i - 1;
                    listAux.set(b, String.valueOf(res));
                    listAux.remove(i);
                    i = 0;
                }
                if (opds[j].equals("-") && listAux.get(i).equals("-")) {
                    res = Double.parseDouble(listAux.get(i - 1)) - Double.parseDouble(listAux.get(i + 1));
                    //Correciones
                    int b = i + 1;
                    listAux.remove(b);
                    b = i - 1;
                    listAux.set(b, String.valueOf(res));
                    listAux.remove(i);
                    i = 0;
                }
            }
        }
        if(listAux.get(0) != null && listAux.size() == 1)
            this.display.setText(listAux.get(0));
        else
            this.display.setText("!Syntaxis Error");

        this.cadenaDisplay = "";

    }
}