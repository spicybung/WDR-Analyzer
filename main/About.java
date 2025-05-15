package main;

import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/* loaded from: About.class */
public class About extends JDialog {
    private String[] thanks;
    private int currentHeight = 150;
    private float fps = 1.0f;
    private float finalFPS = 240.0f;
    private long previousTime = 0;
    private float wait = 3.0f;
    private float currentWait = 0.0f;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JLabel labelDate;
    private JLabel labelName;
    private JLabel labelSL;
    private JLabel labelVersion;

    public About(String[] values, String[] thanks) {
        this.thanks = thanks;
        setIconImage(Toolkit.getDefaultToolkit().createImage("ico2.png"));
        initComponents();
        setText(values);
        setVisible(true);
    }

    public void setText(String[] values) {
        this.labelName.setText(values[0]);
        this.labelVersion.setText(values[1]);
        this.labelDate.setText(values[2]);
    }

    public void paintPanel(Graphics g) {
        for (int i = 0; i < this.thanks.length; i++) {
            g.drawString(this.thanks[i], (int) (105.0d - (this.thanks[i].length() * 2.6d)), (i * 10) + this.currentHeight);
        }
        if (this.currentWait >= this.wait) {
            if (this.currentHeight <= this.thanks.length * (-10)) {
                this.currentHeight = 140;
            } else {
                this.currentHeight--;
            }
            this.currentWait = 0.0f;
        } else {
            this.currentWait = (float) (this.currentWait + 0.7d);
        }
        updateFPS();
        repaint();
    }

    public void updateFPS() {
        long currentTime = System.currentTimeMillis();
        this.fps += 1.0f;
        if (currentTime - this.previousTime >= 1000) {
            this.previousTime = currentTime;
            this.finalFPS = this.fps;
            this.wait = this.finalFPS / 60.0f;
            this.fps = 0.0f;
        }
    }

    private void initComponents() {
        this.jPanel1 = new 1(this);
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.labelName = new JLabel();
        this.labelVersion = new JLabel();
        this.labelDate = new JLabel();
        this.labelSL = new JLabel();
        this.jLabel4 = new JLabel();
        this.jLabel3 = new JLabel();
        setDefaultCloseOperation(2);
        setTitle("DFF2WDR");
        setResizable(false);
        getContentPane().setLayout(new AbsoluteLayout());
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 220, 32767));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 140, 32767));
        getContentPane().add(this.jPanel1, new AbsoluteConstraints(260, 30, 220, 140));
        this.jLabel1.setText("Special Thanks");
        getContentPane().add(this.jLabel1, new AbsoluteConstraints(330, 10, -1, -1));
        this.jLabel2.setIcon(new ImageIcon(getClass().getResource("/Images/logo_about.png")));
        getContentPane().add(this.jLabel2, new AbsoluteConstraints(110, 0, 110, 110));
        this.labelName.setText("program Name");
        getContentPane().add(this.labelName, new AbsoluteConstraints(10, 10, -1, -1));
        this.labelVersion.setText("Version 1.0");
        getContentPane().add(this.labelVersion, new AbsoluteConstraints(10, 30, -1, -1));
        this.labelDate.setText("01-01-2000");
        getContentPane().add(this.labelDate, new AbsoluteConstraints(10, 50, -1, -1));
        this.labelSL.setText("©2009 Shadow-Link");
        getContentPane().add(this.labelSL, new AbsoluteConstraints(10, 130, -1, -1));
        this.jLabel4.setText("E-Mail: Prince_link_warppipe@hotmail.com");
        getContentPane().add(this.jLabel4, new AbsoluteConstraints(10, 150, -1, -1));
        this.jLabel3.setText("Website: http://www.Shadow-Link.nl");
        getContentPane().add(this.jLabel3, new AbsoluteConstraints(10, 170, -1, -1));
        pack();
    }
}
