import javax.swing.*;
import java.awt.*;

public class FormularioOptimizado extends JFrame {

    // El generador crea las declaraciones aquí
    private JPanel panelPrincipal;
    private JLabel lblUsuario;
    private JTextField txtUsuario;
    private JButton btnAceptar;

    public FormularioOptimizado() {
        // Configuración básica del Frame
        setTitle("Formulario Automatizado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Llamada al método que crearía tu editor/generador
        initialize();

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Este es el método que tu editor o script puede generar automáticamente.
     * Al usar GridBagLayout a mano, consigues un diseño de rejilla profesional
     * sin usar ni una sola librería externa de IntelliJ.
     */
    private void initialize() {
        // 1. Inicializar contenedor principal
        panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();

        // Configuración por defecto de la rejilla (separación entre celdas)
        gbc.insets = new Insets(5, 5, 5, 5);

        // 2. Instanciar los componentes (Fácil de automatizar por un script)
        lblUsuario = new JLabel("Usuario:");
        txtUsuario = new JTextField(15);
        btnAceptar = new JButton("Conectar");

        // 3. Colocar Componente 1: lblUsuario (Fila 0, Columna 0)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Alineado a la izquierda
        panelPrincipal.add(lblUsuario, gbc);

        // 4. Colocar Componente 2: txtUsuario (Fila 0, Columna 1)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Se estira a lo ancho
        gbc.weightx = 1.0; // Absorbe el espacio sobrante horizontal
        panelPrincipal.add(txtUsuario, gbc);

        // 5. Colocar Componente 3: btnAceptar (Fila 1, Columna 0 y 1 combinadas)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER; // Centrado
        gbc.weightx = 0.0;
        panelPrincipal.add(btnAceptar, gbc);

        // 6. Asignar al Frame
        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        if (System.getProperty("java.home") == null) {
            System.setProperty("java.home", ".");
        }
        SwingUtilities.invokeLater(() -> new FormularioOptimizado().setVisible(true));
    }
}
