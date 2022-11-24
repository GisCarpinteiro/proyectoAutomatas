import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author David Alejando Gonzalez Quezada
 * @project ProyectoAutomatas
 * 19/11/22
 */
public class Form extends JFrame {

    public Form() {
        initComponents();
    }

    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        tvIdentificador = new javax.swing.JLabel();
        tvOperadorRelacional = new javax.swing.JLabel();
        tvOperadorLogico = new javax.swing.JLabel();
        tvOperadorAritmetico = new javax.swing.JLabel();
        tvAsignacion = new javax.swing.JLabel();
        tvNumEnteros = new javax.swing.JLabel();
        tvNumDecimales = new javax.swing.JLabel();
        tvComentarios = new javax.swing.JLabel();
        tvParentesis = new javax.swing.JLabel();
        tvLlave = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Seleccionar");
        jButton1.setActionCommand("jSeleccionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("Tokens detectados:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tvLlave)
                                        .addComponent(tvParentesis)
                                        .addComponent(tvComentarios)
                                        .addComponent(tvNumDecimales)
                                        .addComponent(tvNumEnteros)
                                        .addComponent(jLabel1)
                                        .addComponent(tvIdentificador)
                                        .addComponent(tvOperadorRelacional)
                                        .addComponent(tvOperadorLogico)
                                        .addComponent(tvOperadorAritmetico)
                                        .addComponent(tvAsignacion))
                                .addGap(105, 105, 105))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tvIdentificador)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tvOperadorRelacional)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tvOperadorLogico)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tvOperadorAritmetico)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tvAsignacion)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tvNumEnteros)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tvNumDecimales)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tvComentarios)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tvParentesis)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tvLlave))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton1)))
                                .addContainerGap(144, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        File file = fc.getSelectedFile();
        Tokens tokens = new Tokens();
        int identifierToken = 0;
        int relationalOperatorToken = 0;
        int logicOperatorToken = 0;
        int arithmeticOperatorToken = 0;
        int assignmentToken = 0;
        int wholeNumberToken = 0;
        int decimalNumberToken = 0;
        int commentToken = 0;
        int parenthesisToken = 0;
        int bracketsToken = 0;
        int errorToken = 0;



        try{
            //Leer archivo
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder texto = new StringBuilder();
            String linea = "";
            while ((linea = br.readLine()) != null) texto.append(linea).append("\n");

            //Declaracion de StringTokenizer y ArrayList
            List<String> tokensList = new ArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(texto.toString());
            while (tokenizer.hasMoreElements()) {
                tokensList.add(tokenizer.nextToken());
            }

            // StrCharAt.java
            for (String s : tokensList) {
                //System.out.println("Char " + i + " is " + s.charAt(i));

                //Automata, idetificar
                if (tokens.identifier(s)) identifierToken++;
                else errorToken++;

                //Automata, operador aritemtico, evaluando: +, * y %
                if (tokens.arithmeticOperator(s)) arithmeticOperatorToken++;

                //Automata, asignacion, evaluando: =
                if (tokens.assignmentOperator(s)) assignmentToken++;

                //Automata, operadores logicos, evaluando &&, ||, !
                if (tokens.logicOperator(s)) logicOperatorToken++;

                //Automata, operadores logicos, evaluando <, >, <=, >=, ==, !=
                if (tokens.relationalOperator(s)) relationalOperatorToken++;

                //Automata, llaves: {, }
                if (tokens.brackets(s)) bracketsToken++;

                //Automata, parentesis: (, )
                if (tokens.parenthesis(s)) parenthesisToken++;

                //Automata, numeros enteros
                if (tokens.numberWholeValidation(s)) wholeNumberToken++;

                //Automata, numeros decimales
                if (tokens.numberDecimalValidation(s)) decimalNumberToken++;

                //Automata, comentario con formato: /* */
                if (tokens.comment(s)) commentToken++;

                //evaluar "-", numeros decimales y enteros
                    /*if (s.charAt(i) == '-') {

                        if (s.length() > 1) {
                            //evaluar si es numero
                            char c = s.charAt(i);
                            System.out.println(c);
                            if (c < '0' || c > '9') {

                                if (s.charAt(i) % 2 == 0) {
                                    System.out.println("es un numero entero");
                                } else {
                                    System.out.println("es un numero decimal");
                                }
                            }
                        } else {
                            System.out.println("puede ser operador aritmetico o numero negativo");
                        }
                    }*/

                //automata numeros enteros y decimales
                    /*if (s.charAt(i) == '0' || s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3'
                            || s.charAt(i) == '4' || s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '7'
                            || s.charAt(i) == '8' || s.charAt(i) == '9') {
                        System.out.println("puede ser operador aritmetico o numero negativo");


                    }*/

            }

            /*System.out.println("Tamaño del texto: " + tokensList.size());
            System.out.println("Texto: " + tokensList.get(0));
            System.out.println("Texto: " + tokensList.get(1));*/
            tvIdentificador.setText("Operadores de asignacion:" + errorToken);
            tvOperadorAritmetico.setText("Operadores aritmeticos encontrados: " + arithmeticOperatorToken);
            tvAsignacion.setText("Operadores de asignacion encontrados: " + assignmentToken);
            tvOperadorLogico.setText("Operadores logicos encontrados: " + logicOperatorToken);
            tvOperadorRelacional.setText("Operadores relacionales encontrados: " + relationalOperatorToken);
            tvLlave.setText("LLaves encontradas: " + bracketsToken);
            tvParentesis.setText("Parentesis encontrados: " + parenthesisToken);
            tvNumEnteros.setText("Numeros enteros econtrados: " + wholeNumberToken);
            tvNumDecimales.setText("Numeros decimales econtrados: " + decimalNumberToken);
            tvComentarios.setText("Comentarios encontrados: " + commentToken);
            jTextArea1.setText(texto.toString());

            JOptionPane.showMessageDialog(null, "Leido correctamente");
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form().setVisible(true);

            }
        });
    }

    public List<String> getTokens(String str) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(str);
        while (tokenizer.hasMoreElements()) {
            tokens.add(tokenizer.nextToken());
        }
        return tokens;
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel tvAsignacion;
    private javax.swing.JLabel tvComentarios;
    private javax.swing.JLabel tvIdentificador;
    private javax.swing.JLabel tvLlave;
    private javax.swing.JLabel tvNumDecimales;
    private javax.swing.JLabel tvNumEnteros;
    private javax.swing.JLabel tvOperadorAritmetico;
    private javax.swing.JLabel tvOperadorLogico;
    private javax.swing.JLabel tvOperadorRelacional;
    private javax.swing.JLabel tvParentesis;
    // End of variables declaration
}
