package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used as View Craps Class
 * @autor Alejandro-Villamil  alejandro.villamil@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {
    public static final String MENSAJE_INICIO = "Bienvenidos a Craps \n"
            + "Oprime el Botón lanzar para iniciar el juego"
            + "\nSi tu tiro de salida es 7 u 11 ganas con natural"
            + "\n Si tu tiro de salida es 2,3 u 12 pierdes con Craps"
            + "\n Si sacas cualquier otro valor estableceras el Punto"
            + "\nEstado en punto podras seguir lanzando los dados"
            + "\npero ahora ganaras si sacas nuevamente el valor de Punto"
            + "\nsin que previamente hayas sacado 7";


    private Header headerProject;
    private JLabel dado1, dado2;
    private JButton lanzar;
    private JPanel panelDados, panelResultados;
    private ImageIcon imagenDado;
    private JTextArea mensajesSalidas, resultadosDados;
    private JSeparator separador;
    private Escucha escucha;
    private ModelCraps modelCraps;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Juego Craps");
        //this.setSize(200,100);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Mesa Juego Craps", Color.BLACK);
        this.add(headerProject,BorderLayout.NORTH);


        imagenDado = new ImageIcon(getClass().getResource("/resources/dado.png"));
        dado1 = new JLabel(imagenDado);
        dado2 = new JLabel(imagenDado);

        lanzar = new JButton("lanzar");
        lanzar.addActionListener(escucha);


        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300,180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus Dados"));
        panelDados.add(dado1);
        panelDados.add(dado2);
        panelDados.add(lanzar);
        this.add(panelDados,BorderLayout.CENTER);

        mensajesSalidas = new JTextArea(7,31);
        mensajesSalidas.setText(MENSAJE_INICIO);
        //mensajesSalidas.setBorder(BorderFactory.createTitledBorder("Que debes hacer"));
        JScrollPane scroll = new JScrollPane(mensajesSalidas);


        panelResultados = new JPanel();
        panelResultados.setBorder(BorderFactory.createTitledBorder("Que debes hacer"));
        panelResultados.add(scroll);
        panelResultados.setPreferredSize(new Dimension(370,180));



        this.add(panelResultados,BorderLayout.EAST);

        resultadosDados = new JTextArea(4,31);
        separador = new  JSeparator();
        separador.setPreferredSize(new Dimension(320,7));
        separador.setBackground(Color.BLUE);
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            modelCraps.calcularTiro();
            int[] caras = modelCraps.getCaras();
            imagenDado = new ImageIcon(getClass().getResource("/resources/"+caras[0]+".png"));
            dado1.setIcon(imagenDado);
            imagenDado = new ImageIcon(getClass().getResource("/resources/"+caras[1]+".png"));
            dado2.setIcon(imagenDado);

            modelCraps.determinarJuego();

            panelResultados.removeAll();
            panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados"));
            panelResultados.add(resultadosDados);
            panelResultados.add(separador);
            panelResultados.add(mensajesSalidas);
            resultadosDados.setText(modelCraps.getEstadoToString()[0]);
            mensajesSalidas.setRows(4);
            mensajesSalidas.setText(modelCraps.getEstadoToString()[1]);
            revalidate();
            repaint();
        }
    }
}
