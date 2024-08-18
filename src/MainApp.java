/*
 * PRESENTACIÓN DEL TRABAJO Unidad 3: Tarea (13%) - GUI
 * Trabajo realizado por: Edwin Leonardo Alzate Avendaño
 * Asignatura: Lenguaje de Programación Avanzado 1 - 2407B04G1G2
 * Docente: Nixon Duarte Acosta.
 * Fecha entrega: 18/08/2024
 * Github: https://github.com/StudyEdwinA994/Tarea3_GUI.git
 */

//Importo librerias que voy a usar.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Declaracion de variables del programa.
public class MainApp {
    private JFrame entorno;
    private JTextField datosEntrada;
    private JTextArea datosSalida;
    private JComboBox<String> cuadroAlgoritmo;
    private JButton botonOrdenar;
    private JButton botonBuscar;

    public MainApp() {
        entorno = new JFrame("ORDENAMIENTO Y BUSQUEDA - TareaGUI");
        entorno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        entorno.setSize(700, 500);
        entorno.setLayout(new BorderLayout());

        //Ventana principal de entrada
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        panel.add(new JLabel("Digite el arreglo de números separados por comas:"));
        datosEntrada = new JTextField();
        panel.add(datosEntrada);

        //Lista desplegable
        panel.add(new JLabel("Seleccione un algoritmo de ordenamiento:"));
        String[] tipoAlgoritmo = {"Ordenamiento Burbuja", "Ordenamiento por Inserción", "Ordenamiento por Selección"};
        cuadroAlgoritmo = new JComboBox<>(tipoAlgoritmo);
        panel.add(cuadroAlgoritmo);

        entorno.add(panel, BorderLayout.NORTH);

        //Salida del programa
        datosSalida = new JTextArea();
        datosSalida.setEditable(false);
        entorno.add(new JScrollPane(datosSalida), BorderLayout.CENTER);

        //Configuracion de botones
        JPanel botonPanelPpal = new JPanel();
        botonOrdenar = new JButton("Ordenar arreglo");
        botonBuscar = new JButton("Buscar en el arreglo");

        botonPanelPpal.add(botonOrdenar);
        botonPanelPpal.add(botonBuscar);
        entorno.add(botonPanelPpal, BorderLayout.SOUTH);

        // Eventos
        botonOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenamientoMatriz();
            }
        });

        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarElemento();
            }
        });

        entorno.setVisible(true);
    }

    private void ordenamientoMatriz() {
        String entrada = datosEntrada.getText();
        String[] entradaDelArreglo = entrada.split(",");
        int[] arregloNumeros = new int[entradaDelArreglo.length];
        for (int i = 0; i < entradaDelArreglo.length; i++) {
            arregloNumeros[i] = Integer.parseInt(entradaDelArreglo[i].trim());
        }

        //Switch para invocar el ordenamiento correspondiente
        String seleccionAlgoritmo = (String) cuadroAlgoritmo.getSelectedItem();
        switch (seleccionAlgoritmo) {
            case "Ordenamiento Burbuja":
                ordBurbuja(arregloNumeros);
                break;
            case "Ordenamiento por Inserción":
                ordInsercion(arregloNumeros);
                break;
            case "Ordenamiento por Selección":
                ordSeleccion(arregloNumeros);
                break;
        }

        //Muestra final del arreglo ordenado
        datosSalida.append("El arreglo ordenado es: " + arregloCadena(arregloNumeros) + "\n\n");
    }

    //Funcion para buscar los numeros en el arreglo
    private void buscarElemento() {
        String entrada = JOptionPane.showInputDialog(entorno, "Digite el numero que desea buscar:");
        int obtenerValor = Integer.parseInt(entrada);

        String[] entradaDelElemento = datosEntrada.getText().split(",");
        int[] arreglo = new int[entradaDelElemento.length];
        for (int i = 0; i < entradaDelElemento.length; i++) {
            arreglo[i] = Integer.parseInt(entradaDelElemento[i].trim());
        }

        // Ordenar antes de buscar
        ordBurbuja(arreglo);
        int elementoEncontrado = busquedaElemento(arreglo, obtenerValor);

        if (elementoEncontrado == -1) {
            datosSalida.append("Número " + obtenerValor + " no esta en el arreglo.\n");
        } else {
            datosSalida.append("El número " + obtenerValor + " esta en la posición " + (elementoEncontrado + 1) + " del arreglo.\n");
        }
    }

    private void ordBurbuja(int[] arregloBurbuja) {
        datosSalida.append("************************************\n");
        datosSalida.append("ORDENAMIENTO BURBUJA: \n");
        datosSalida.append("************************************\n");
        int n = arregloBurbuja.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arregloBurbuja[j] > arregloBurbuja[j + 1]) {
                    // Swap
                    int numTemporal = arregloBurbuja[j];
                    arregloBurbuja[j] = arregloBurbuja[j + 1];
                    arregloBurbuja[j + 1] = numTemporal;
                }
                datosSalida.append("Paso " + (i * (n - 1) + j + 1) + ": " + arregloCadena(arregloBurbuja) + "\n");
            }
        }
        datosSalida.append("\n");
    }

    private void ordInsercion(int[] arregloInserccion) {
        datosSalida.append("************************************\n");
        datosSalida.append("ORDENAMIENTO INSERCION: \n");
        datosSalida.append("************************************\n");
        int tamArreglo = arregloInserccion.length;
        for (int i = 1; i < tamArreglo; i++) {
            int claveArreglo = arregloInserccion[i];
            int j = i - 1;
            while (j >= 0 && arregloInserccion[j] > claveArreglo) {
                arregloInserccion[j + 1] = arregloInserccion[j];
                j = j - 1;
                datosSalida.append("Paso " + i + ": " + arregloCadena(arregloInserccion) + "\n");
            }
            arregloInserccion[j + 1] = claveArreglo;
            datosSalida.append("Paso " + i + ": " + arregloCadena(arregloInserccion) + "\n");
        }
        datosSalida.append("\n");
    }

    private void ordSeleccion(int[] arregloSeleccion) {
        datosSalida.append("************************************\n");
        datosSalida.append("ORDENAMIENTO SELECCION: \n");
        datosSalida.append("************************************\n");
        int tamArreglo = arregloSeleccion.length;
        for (int i = 0; i < tamArreglo - 1; i++) {
            int indiceArreglo = i;
            for (int j = i + 1; j < tamArreglo; j++) {
                if (arregloSeleccion[j] < arregloSeleccion[indiceArreglo]) {
                    indiceArreglo = j;
                }
            }

            int varTemporal = arregloSeleccion[indiceArreglo];
            arregloSeleccion[indiceArreglo] = arregloSeleccion[i];
            arregloSeleccion[i] = varTemporal;
            datosSalida.append("Paso " + (i + 1) + ": " + arregloCadena(arregloSeleccion) + "\n");
        }
        datosSalida.append("\n");
    }

    private int busquedaElemento(int[] arregloBuscar, int valorBuscar) {
        int izq = 0;
        int der = arregloBuscar.length - 1;
        while (izq <= der) {
            int valorBuscado = izq + (der - izq) / 2;
            if (arregloBuscar[valorBuscado] == valorBuscar) {
                return valorBuscado;
            }
            if (arregloBuscar[valorBuscado] < valorBuscar) {
                izq = valorBuscado + 1;
            } else {
                der = valorBuscado - 1;
            }
        }
        return -1;
    }

    //Metodo para presentar el arreglo como cadena.
    private String arregloCadena(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i : array) {
            sb.append(i).append(" ");
        }
        return sb.toString();
    }

    //Metodo principal Main.
    public static void main(String[] args) {
        new MainApp();
    }
}