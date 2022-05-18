package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

/*import org.mariuszgromada.math.mxparser.*;*/
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
    private String cadenaDisplay;
    final String mensajeError = "Syntax Error!";
    private boolean btnIgualPresionado = false;
    private boolean btnDelete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (EditText) findViewById(R.id.display);
        displayResults = (TextView) findViewById(R.id.resultados);
        this.cadenaDisplay = "";
    }

    public void limpiarDisplay(View vista) {
        this.btnIgualPresionado = false;
        this.btnDelete = false;
        cadenaDisplay = "";
        display.setText(cadenaDisplay);
        displayResults.setText("");
    }

    public void borrarDisplay(View view) {

        String displayText = this.display.getText().toString();
        this.btnDelete = true;
        if (!(displayText.length() == 0)) {
            int ultimoIndice = displayText.length() - 1;
            if (displayText.endsWith(" ")) ultimoIndice -= 2;
            this.cadenaDisplay = displayText.substring(0, ultimoIndice);
        }

        this.display.setText(this.cadenaDisplay);
    }

    //Calcular operacion Metodo2
    public void calcularoperacion2(View view) {

        this.btnDelete = false;
        this.btnIgualPresionado = true;
        String[] display = cadenaDisplay.split(" ");
        List<String> listAux = new ArrayList<>();
        String[] opds = {"/", "*", "+", "-"};
        double res = 0;
        String aux = "";
        int a = 0;
        try {
            for (a = 0; a < display.length; a++) {
                if (a == 0 && display[0].equals("")) {
                    if (display[1].equals("-") || display[1].equals("+")) {
                        aux = display[1] + "" + display[2];
                        listAux.add(aux);
                        a += 2;
                    }
                } else {
                    listAux.add(display[a]);
                }
            }

            if (listAux.get(listAux.size() - 1).equals("+") || listAux.get(listAux.size() - 1).equals("+") || listAux.get(listAux.size() - 1).equals("/") || listAux.get(listAux.size() - 1).equals("*") || listAux.get(listAux.size() - 1).equals("-")) {
                this.displayResults.setText(this.mensajeError);
                this.display.setText("");
                this.cadenaDisplay = "";
            } else {
                try {
                    for (int j = 0; j < opds.length; j++) {
                        int i = 0;
                        for (i = 0; i < listAux.size(); i++) {
                            if (opds[j].equals("/") && listAux.get(i).equals("/")) {
                                res = Math.round(Double.parseDouble(listAux.get(i - 1)) / Double.parseDouble(listAux.get(i + 1)) * 10000d) / 10000d;
                                int b = i + 1;
                                listAux.remove(b);
                                b = i - 1;
                                listAux.set(b, String.valueOf(res));
                                listAux.remove(i);
                                i = 0;
                            }
                            if (opds[j].equals("*") && listAux.get(i).equals("*")) {
                                res = Math.round(Double.parseDouble(listAux.get(i - 1)) * Double.parseDouble(listAux.get(i + 1)) * 10000d) / 10000d;
                                int b = i + 1;
                                listAux.remove(b);
                                b = i - 1;
                                listAux.set(b, String.valueOf(res));
                                listAux.remove(i);
                                i = 0;
                            }
                            if (j > 1) {
                                if (listAux.get(i).equals("+")) {
                                    res = Double.parseDouble(listAux.get(i - 1)) + Double.parseDouble(listAux.get(i + 1));
                                    int b = i + 1;
                                    listAux.remove(b);
                                    b = i - 1;
                                    listAux.set(b, String.valueOf(res));
                                    listAux.remove(i);
                                    i = 0;
                                }
                                if (listAux.get(i).equals("-")) {
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
                    }
                    this.displayResults.setText(listAux.get(0));
                } catch (Exception e) {
                    this.display.setText("");
                    this.cadenaDisplay = "";
                    this.displayResults.setText(this.mensajeError);
                }
            }
        }catch (Exception e){
            this.displayResults.setText(this.mensajeError);
        }
    }

    public void pintaNumeros(View vista) {
        Button boton = (Button) vista;
        if(this.btnIgualPresionado && !(this.btnDelete)){
            this.cadenaDisplay = "";
            this.btnIgualPresionado = false;
        }
        this.cadenaDisplay += boton.getText();
        this.display.setText(this.cadenaDisplay);
    }

    public void pintaOperadores(View vista) {
        Button boton = (Button) vista;

        if(this.btnIgualPresionado){
            this.cadenaDisplay = this.displayResults.getText().toString();
            if(this.cadenaDisplay.equals(this.mensajeError)){
                this.cadenaDisplay = "";
            }else{
                this.display.setText(this.cadenaDisplay);
                this.btnIgualPresionado = false;
            }
        }
        this.btnDelete = false;
        this.cadenaDisplay += " " + boton.getText() + " ";
        this.display.setText(this.cadenaDisplay);
    }

    /*public void calcularOperacion(View vista) {
        Expression exp = new Expression(this.cadenaDisplay);
        String result = String.valueOf(exp.calculate());

        if (result != "NaN") {
            this.display.setText(result);
        } else {
            this.display.setText("Syntax Error!");
        }
        this.cadenaDisplay = "";
    }*/
}