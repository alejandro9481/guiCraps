package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGridBagLayaout extends JFrame {
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
    private JButton lanzar,ayuda,salir;
    private JPanel panelDados, panelResultados;
    private ImageIcon imagenDado;
    private JTextArea mensajesSalidas, resultadosDados;
    private Escucha escucha;
    private ModelCraps modelCraps;
    
    public GUIGridBagLayaout(){
        initGUI();


        //Default JFrame configuration
        this.setTitle("Juego Craps");
        this.setUndecorated(true);
        this.setBackground(new Color(255,255,255,0));
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //Create Listener Object and Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();

        //Set up JComponents
        headerProject = new Header("Mesa Juego Craps", Color.BLACK);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);

        ayuda = new JButton("?");
        ayuda.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        this.add(ayuda,constraints);

        salir = new JButton("Salir");
        salir.addActionListener(escucha);
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        this.add(salir,constraints);



        imagenDado = new ImageIcon(getClass().getResource("/resources/dado.png"));
        dado1 = new JLabel(imagenDado);
        dado2 = new JLabel(imagenDado);



        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300,180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus Dados"));
        panelDados.add(dado1);
        panelDados.add(dado2);
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(panelDados,constraints);

        resultadosDados = new JTextArea(4,31);
        resultadosDados.setBorder(BorderFactory.createTitledBorder("Resultados"));
        resultadosDados.setText("Debes lanzar los dados");
        resultadosDados.setBackground(null);
        resultadosDados.setEditable(false);
        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(resultadosDados,constraints);


        lanzar = new JButton("lanzar");
        lanzar.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(lanzar,constraints);


        mensajesSalidas = new JTextArea(4,31);
        mensajesSalidas.setText("Usa el boton (?) para ver las reglas del juego");
        mensajesSalidas.setBorder(BorderFactory.createTitledBorder("Mensajes"));
        mensajesSalidas.setBackground(null);
        mensajesSalidas.setEditable(false);
        constraints.gridx=0;
        constraints.gridy=4;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(mensajesSalidas,constraints);

    }

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayaout miProjectGUI = new GUIGridBagLayaout();
        });
    }

    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==lanzar){
                modelCraps.calcularTiro();
                int[] caras = modelCraps.getCaras();
                imagenDado = new ImageIcon(getClass().getResource("/resources/"+caras[0]+".png"));
                dado1.setIcon(imagenDado);
                imagenDado = new ImageIcon(getClass().getResource("/resources/"+caras[1]+".png"));
                dado2.setIcon(imagenDado);
                modelCraps.determinarJuego();
                resultadosDados.setText(modelCraps.getEstadoToString()[0]);
                mensajesSalidas.setText(modelCraps.getEstadoToString()[1]);
            }else{
                if(e.getSource()==ayuda){
                    JOptionPane.showMessageDialog(null,MENSAJE_INICIO);
                }else{
                    System.exit(0);
                }
            }

        }
    }
}
