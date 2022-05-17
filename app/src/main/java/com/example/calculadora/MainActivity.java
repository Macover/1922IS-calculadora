package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.*;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText display;
    private TextView displayResults;
    private String cadenaDisplay = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (EditText) findViewById(R.id.display);
        displayResults = (TextView) findViewById(R.id.resultados);
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
        displayResults.setText("");
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
            if(a == 0 && display[0].equals("-") )
            {
                listAux.add(display[0]+""+display[1]);
                a++;
            }else
            {
                listAux.add(display[a]);
            }
        }
        double res = 0;
        String[] opds = {"/", "*", "+", "-"};
        if(listAux.get(listAux.size()-1).equals("+") || listAux.get(listAux.size()-1).equals("+") || listAux.get(listAux.size()-1).equals("/") || listAux.get(listAux.size()-1).equals("*") || listAux.get(listAux.size()-1).equals("-")){
            this.displayResults.setText("!Syntaxis Error");
        }else {
            for (int j = 0; j < opds.length; j++) {
                int i = 0;
                for (i = 0; i < listAux.size(); i++) {
                    if (opds[j].equals("/") && listAux.get(i).equals("/")) {
                        res = Double.parseDouble(listAux.get(i - 1)) / Double.parseDouble(listAux.get(i + 1));
                        int b = i + 1;
                        listAux.remove(b);
                        b = i - 1;
                        listAux.set(b, String.valueOf(res));
                        listAux.remove(i);
                        i = 0;
                    }
                    if (opds[j].equals("*") && listAux.get(i).equals("*")) {
                        res = Double.parseDouble(listAux.get(i - 1)) * Double.parseDouble(listAux.get(i + 1));
                        int b = i + 1;
                        listAux.remove(b);
                        b = i - 1;
                        listAux.set(b, String.valueOf(res));
                        listAux.remove(i);
                        i = 0;
                    }
                    if (opds[j].equals("+") && listAux.get(i).equals("+")) {
                        res = Double.parseDouble(listAux.get(i - 1)) + Double.parseDouble(listAux.get(i + 1));
                        int b = i + 1;
                        listAux.remove(b);
                        b = i - 1;
                        listAux.set(b, String.valueOf(res));
                        listAux.remove(i);
                        i = 0;
                    }
                    if (opds[j].equals("-") && listAux.get(i).equals("-")) {
                        res = Double.parseDouble(listAux.get(i - 1)) - Double.parseDouble(listAux.get(i + 1));
                        int b = i + 1;
                        listAux.remove(b);
                        b = i - 1;
                        listAux.set(b, String.valueOf(res));
                        listAux.remove(i);
                        i = 0;
                    }
                }
            }
            if(listAux.size() == 1)
                this.displayResults.setText(listAux.get(0));
            else
                this.displayResults.setText("!Syntaxis Error");
        }
    }
}