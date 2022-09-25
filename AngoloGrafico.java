import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class AngoloGrafico extends JFrame {
    private JPanel sfondo;
    private JButton bottone1;
    private JButton bottone2;
    private JButton ferma;
    private MioPannello pannello;
    private JTextField casella;
    private Timer t;

    class Aziona implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Incrementa a = new Incrementa();
            t = new Timer(2000, a);
            t.start();
        }
    }

    class Incrementa implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            pannello.k++;
            AngoloGrafico.this.casella.setText(" " + pannello.k);
            pannello.repaint();
        }
    }


    class Stop implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            t.stop();
        }
    }


    class MioPannello extends JPanel {
        int k;
        double x;
        double y;

        public MioPannello() {

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.translate(getWidth() / 2, getHeight() / 2);
            g2.scale(3, 3);

            for (double a = 0; a < 360; a += 0.5) {
                double r = 90;
                x = r * Math.cos(a);
                y = r * Math.sin(k * a);

                Random r1 = new Random();
                int s = r1.nextInt(255);
                Random r2 = new Random();
                int s1 = r2.nextInt(255);
                Random r3 = new Random();
                int s2 = r3.nextInt(255);

                Color c = new Color(s, s1, s2);
                g2.setColor(c);

                Ellipse2D.Double e = new Ellipse2D.Double(x, y, 1, 1);
                g2.fill(e);
                g2.draw(e);

            }

        }
    }

    public AngoloGrafico() {
        this.sfondo = new JPanel();
        this.sfondo.setLayout(null);
        setContentPane(this.sfondo);

        this.pannello = new MioPannello();
        this.pannello.setSize(600, 600);
        this.pannello.setLocation(10, 10);
        this.pannello.setBackground(Color.black);
        this.sfondo.add(this.pannello);

        this.bottone1 = new JButton("Aziona");
        this.bottone1.setSize(300, 50);
        this.bottone1.setLocation(10, 620);
        Font f = new Font(Font.SANS_SERIF, Font.ITALIC, 36);
        this.bottone1.setFont(f);
        this.sfondo.add(this.bottone1);
        this.bottone1.setBackground(Color.PINK);

        this.bottone2 = new JButton("Incrementa");
        this.bottone2.setSize(300, 50);
        this.bottone2.setLocation(310, 620);
        this.bottone2.setFont(f);
        this.sfondo.add(this.bottone2);
        this.bottone2.setBackground(Color.orange);

        Incrementa c2 = new Incrementa();
        bottone2.addActionListener(c2);

        Aziona c = new Aziona();
        bottone1.addActionListener(c);

        this.casella = new JTextField();
        this.casella.setSize(50, 50);
        this.casella.setLocation(550, 20);
        this.casella.setFont(f);
        this.sfondo.add(this.casella);

        this.ferma = new JButton("STOP!");
        this.ferma.setSize(200, 50);
        this.ferma.setLocation(620, 20);
        this.ferma.setFont(f);
        this.sfondo.add(this.ferma);
        this.ferma.setBackground(Color.RED);

        Stop s = new Stop();
        this.ferma.addActionListener(s);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(850, 800);
        setVisible(true);
    }


    public static void main(String[] args) {
        new AngoloGrafico();
    }
}

