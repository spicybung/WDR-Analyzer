package main;

import com.sun.opengl.util.Animator;
import file_io.ReadFunctions;
import java.awt.Checkbox;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import model.Camera;
import model.Model;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import wdr.ByteReader;
import wdr.DrawableModel;
import wdr.Geometry;
import wdr.Model2;
import wdr.ModelFile;
import wdr.ResourceFile;
import wdr.Shader;
import wdr.ShaderFx;
import wdr.ShaderParamTexture;
import wdr.ShaderParamVector;
import wdr.Vector4;

/* loaded from: Main.class */
public class Main extends JFrame implements GLEventListener {
    ResourceFile res;
    byte[] stream;
    Animator animator;
    Camera camera;
    private DefaultMutableTreeNode rootNode;
    private DefaultTableModel dataModel;
    private JCheckBoxMenuItem checkASS;
    private Checkbox checkBounds;
    private Checkbox checkCenter;
    private JCheckBoxMenuItem checkSaveDEC;
    private Checkbox checkUnknown;
    private Checkbox checkWDR;
    private JLabel compressionLabel;
    private GLCanvas gLCanvas1;
    private JLabel graphSizeLabel;
    private JButton jButton3;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenu jMenu3;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem2;
    private JMenuItem jMenuItem4;
    private JMenuItem jMenuItem5;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JTable jTable1;
    private JLabel nameLabel;
    private JLabel polyLabel;
    private JLabel sysSizeLabel;
    private JTree tree;
    private JLabel typeLabel;
    private JLabel vertLabel;
    LinkedList<Model> model = new LinkedList<>();
    Model mdl = new Model();
    private float modelZoom = -20.0f;
    private float rotationX = 0.0f;
    private float rotationY = 0.0f;
    private boolean inCanvas = false;
    private int dragX = 0;
    private int dragY = 0;
    private String fileName = "";
    public int sysSize = 0;
    private DrawableModel sys = new DrawableModel();
    private int totalVertCount = 0;
    private int totalFaceCount = 0;

    public Main(String[] args) {
        setLookAndFeel();
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().createImage("icon.png"));
        this.animator = new Animator(this.gLCanvas1);
        this.animator.start();
        this.camera = new Camera(0.0d, 2.0d, 5.0d, 0.0d, 2.5d, 0.0d, 0.0d, 1.0d, 0.0d);
        if (args.length > 0) {
            openWDRFile(args[0]);
        }
    }

    /* JADX WARN: Type inference failed for: r0v119, types: [java.lang.Object[][], java.lang.String[]] */
    private void initComponents() {
        this.jButton3 = new JButton();
        this.nameLabel = new JLabel();
        this.compressionLabel = new JLabel();
        this.typeLabel = new JLabel();
        this.gLCanvas1 = new GLCanvas();
        this.vertLabel = new JLabel();
        this.jSeparator1 = new JSeparator();
        this.polyLabel = new JLabel();
        this.jSeparator2 = new JSeparator();
        this.checkWDR = new Checkbox();
        this.sysSizeLabel = new JLabel();
        this.graphSizeLabel = new JLabel();
        this.jScrollPane1 = new JScrollPane();
        this.tree = new JTree();
        this.jScrollPane2 = new JScrollPane();
        this.jTable1 = new JTable();
        this.checkCenter = new Checkbox();
        this.checkBounds = new Checkbox();
        this.checkUnknown = new Checkbox();
        this.jMenuBar1 = new JMenuBar();
        this.jMenu1 = new JMenu();
        this.jMenuItem1 = new JMenuItem();
        this.jMenuItem2 = new JMenuItem();
        this.jMenuItem4 = new JMenuItem();
        this.jMenu2 = new JMenu();
        this.checkSaveDEC = new JCheckBoxMenuItem();
        this.checkASS = new JCheckBoxMenuItem();
        this.jMenu3 = new JMenu();
        this.jMenuItem5 = new JMenuItem();
        setDefaultCloseOperation(3);
        setTitle("WDR Analyze");
        setResizable(false);
        getContentPane().setLayout(new AbsoluteLayout());
        this.jButton3.setText("Save");
        this.jButton3.setEnabled(false);
        this.jButton3.addActionListener(new 1(this));
        getContentPane().add(this.jButton3, new AbsoluteConstraints(960, 420, 80, -1));
        this.nameLabel.setText("Name: ");
        this.nameLabel.setEnabled(false);
        getContentPane().add(this.nameLabel, new AbsoluteConstraints(760, 10, -1, -1));
        this.compressionLabel.setText("Compression:");
        this.compressionLabel.setEnabled(false);
        getContentPane().add(this.compressionLabel, new AbsoluteConstraints(760, 30, -1, -1));
        this.typeLabel.setText("Type: ");
        this.typeLabel.setEnabled(false);
        getContentPane().add(this.typeLabel, new AbsoluteConstraints(760, 50, -1, -1));
        this.gLCanvas1.addGLEventListener(this);
        this.gLCanvas1.addMouseWheelListener(new 2(this));
        this.gLCanvas1.addMouseListener(new 3(this));
        this.gLCanvas1.addMouseMotionListener(new 4(this));
        this.gLCanvas1.addKeyListener(new 5(this));
        getContentPane().add(this.gLCanvas1, new AbsoluteConstraints(290, 10, 460, 390));
        this.vertLabel.setText("Vertices: ");
        this.vertLabel.setEnabled(false);
        getContentPane().add(this.vertLabel, new AbsoluteConstraints(900, 10, -1, -1));
        getContentPane().add(this.jSeparator1, new AbsoluteConstraints(190, 410, 130, 10));
        this.polyLabel.setText("Polys: ");
        this.polyLabel.setEnabled(false);
        getContentPane().add(this.polyLabel, new AbsoluteConstraints(900, 30, -1, -1));
        getContentPane().add(this.jSeparator2, new AbsoluteConstraints(0, 410, 1040, 10));
        this.checkWDR.setEnabled(false);
        this.checkWDR.setLabel("Render WDR");
        this.checkWDR.setState(true);
        getContentPane().add(this.checkWDR, new AbsoluteConstraints(290, 420, -1, -1));
        this.sysSizeLabel.setText("System Size:");
        this.sysSizeLabel.setEnabled(false);
        getContentPane().add(this.sysSizeLabel, new AbsoluteConstraints(760, 70, -1, -1));
        this.graphSizeLabel.setText("Graphic Size: ");
        this.graphSizeLabel.setEnabled(false);
        getContentPane().add(this.graphSizeLabel, new AbsoluteConstraints(760, 90, -1, -1));
        DefaultMutableTreeNode treeNode1 = new DefaultMutableTreeNode("Drawable");
        this.tree.setModel(new DefaultTreeModel(treeNode1));
        this.tree.addTreeSelectionListener(new 6(this));
        this.jScrollPane1.setViewportView(this.tree);
        this.rootNode = treeNode1;
        getContentPane().add(this.jScrollPane1, new AbsoluteConstraints(10, 10, 270, 390));
        String[] col = {"Name", "Value"};
        this.dataModel = new DefaultTableModel((Object[][]) new String[0], col);
        this.jTable1.setModel(this.dataModel);
        this.jTable1.setCellSelectionEnabled(true);
        this.jScrollPane2.setViewportView(this.jTable1);
        getContentPane().add(this.jScrollPane2, new AbsoluteConstraints(760, 110, 270, 290));
        this.checkCenter.setEnabled(false);
        this.checkCenter.setLabel("Render Center");
        this.checkCenter.setState(true);
        getContentPane().add(this.checkCenter, new AbsoluteConstraints(390, 420, -1, -1));
        this.checkBounds.setEnabled(false);
        this.checkBounds.setLabel("Render Bounds");
        this.checkBounds.setState(true);
        getContentPane().add(this.checkBounds, new AbsoluteConstraints(500, 420, -1, -1));
        this.checkUnknown.setEnabled(false);
        this.checkUnknown.setLabel("Render Unk Vector");
        this.checkUnknown.setState(true);
        getContentPane().add(this.checkUnknown, new AbsoluteConstraints(610, 420, -1, -1));
        this.jMenu1.setText("File");
        this.jMenuItem1.setAccelerator(KeyStroke.getKeyStroke(79, 2));
        this.jMenuItem1.setText("Load Compressed");
        this.jMenuItem1.addActionListener(new 7(this));
        this.jMenu1.add(this.jMenuItem1);
        this.jMenuItem2.setText("Load Decompressed");
        this.jMenuItem2.setEnabled(false);
        this.jMenuItem2.addActionListener(new 8(this));
        this.jMenu1.add(this.jMenuItem2);
        this.jMenuItem4.setAccelerator(KeyStroke.getKeyStroke(115, 8));
        this.jMenuItem4.setText("Exit");
        this.jMenu1.add(this.jMenuItem4);
        this.jMenuBar1.add(this.jMenu1);
        this.jMenu2.setText("Edit");
        this.checkSaveDEC.setText("Save Decompressed file");
        this.jMenu2.add(this.checkSaveDEC);
        this.checkASS.setText("Associate WDR Files");
        this.checkASS.setEnabled(false);
        this.checkASS.addItemListener(new 9(this));
        this.jMenu2.add(this.checkASS);
        this.jMenuBar1.add(this.jMenu2);
        this.jMenu3.setText("Help");
        this.jMenuItem5.setText("About..");
        this.jMenuItem5.addActionListener(new 10(this));
        this.jMenu3.add(this.jMenuItem5);
        this.jMenuBar1.add(this.jMenu3);
        setJMenuBar(this.jMenuBar1);
        pack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jButton3ActionPerformed(ActionEvent evt) {
        new FileChooser(this, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gLCanvas1MouseWheelMoved(MouseWheelEvent evt) {
        if (evt.getScrollType() == 0) {
            this.modelZoom = (float) (this.modelZoom - (evt.getWheelRotation() / 1.5d));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gLCanvas1KeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == 73) {
            System.out.println("Tree Action");
            fillTree();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gLCanvas1MouseDragged(MouseEvent evt) {
        if (this.inCanvas && evt.getModifiers() == 4) {
            int newX = this.dragX - evt.getX();
            int newY = this.dragY - evt.getY();
            this.dragX = evt.getX();
            this.dragY = evt.getY();
            this.rotationX += newX;
            this.rotationY += newY;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gLCanvas1MouseReleased(MouseEvent evt) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gLCanvas1MouseExited(MouseEvent evt) {
        this.inCanvas = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gLCanvas1MouseEntered(MouseEvent evt) {
        this.inCanvas = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gLCanvas1MousePressed(MouseEvent evt) {
        this.dragX = evt.getX();
        this.dragY = evt.getY();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void treeValueChanged(TreeSelectionEvent evt) {
        String path = this.tree.getSelectionPath().getLastPathComponent().toString();
        if (path.startsWith("VertexBuffer")) {
            String[] first = this.tree.getSelectionPath().getPathComponent(1).toString().split(" ");
            String[] second = this.tree.getSelectionPath().getPathComponent(2).toString().split(" ");
            String[] third = this.tree.getSelectionPath().getPathComponent(4).toString().split(" ");
            String[] names = ((Geometry) ((Model2) this.sys.ModelCollection[Integer.valueOf(first[1]).intValue()]._items.get(Integer.valueOf(second[1]).intValue())).Geometries._items.get(Integer.valueOf(third[1]).intValue())).vertexBuffer.getDataNames();
            String[] values = ((Geometry) ((Model2) this.sys.ModelCollection[Integer.valueOf(first[1]).intValue()]._items.get(Integer.valueOf(second[1]).intValue())).Geometries._items.get(Integer.valueOf(third[1]).intValue())).vertexBuffer.getDataValues();
            fillTable(names, values);
        } else if (path.startsWith("IndexBuffer")) {
            String[] first2 = this.tree.getSelectionPath().getPathComponent(1).toString().split(" ");
            String[] second2 = this.tree.getSelectionPath().getPathComponent(2).toString().split(" ");
            String[] third2 = this.tree.getSelectionPath().getPathComponent(4).toString().split(" ");
            String[] names2 = ((Geometry) ((Model2) this.sys.ModelCollection[Integer.valueOf(first2[1]).intValue()]._items.get(Integer.valueOf(second2[1]).intValue())).Geometries._items.get(Integer.valueOf(third2[1]).intValue())).indexBuffer.getDataNames();
            String[] values2 = ((Geometry) ((Model2) this.sys.ModelCollection[Integer.valueOf(first2[1]).intValue()]._items.get(Integer.valueOf(second2[1]).intValue())).Geometries._items.get(Integer.valueOf(third2[1]).intValue())).indexBuffer.getDataValues();
            fillTable(names2, values2);
        } else if (path.startsWith("Geometry ")) {
            String[] first3 = this.tree.getSelectionPath().getPathComponent(1).toString().split(" ");
            String[] second3 = this.tree.getSelectionPath().getPathComponent(2).toString().split(" ");
            String[] third3 = this.tree.getSelectionPath().getPathComponent(4).toString().split(" ");
            String[] names3 = ((Geometry) ((Model2) this.sys.ModelCollection[Integer.valueOf(first3[1]).intValue()]._items.get(Integer.valueOf(second3[1]).intValue())).Geometries._items.get(Integer.valueOf(third3[1]).intValue())).getDataNames();
            String[] values3 = ((Geometry) ((Model2) this.sys.ModelCollection[Integer.valueOf(first3[1]).intValue()]._items.get(Integer.valueOf(second3[1]).intValue())).Geometries._items.get(Integer.valueOf(third3[1]).intValue())).getDataValues();
            this.vertLabel.setText("Vertices: " + ((Geometry) ((Model2) this.sys.ModelCollection[Integer.valueOf(first3[1]).intValue()]._items.get(Integer.valueOf(second3[1]).intValue())).Geometries._items.get(Integer.valueOf(third3[1]).intValue())).VertexCount + "/" + this.totalVertCount);
            this.polyLabel.setText("Polys: " + ((Geometry) ((Model2) this.sys.ModelCollection[Integer.valueOf(first3[1]).intValue()]._items.get(Integer.valueOf(second3[1]).intValue())).Geometries._items.get(Integer.valueOf(third3[1]).intValue())).FaceCount + "/" + this.totalFaceCount);
            fillTable(names3, values3);
        } else if (path.startsWith("ModelCollection ")) {
            String[] first4 = this.tree.getSelectionPath().getPathComponent(1).toString().split(" ");
            String[] names4 = this.sys.ModelCollection[Integer.valueOf(first4[1]).intValue()].getDataNames();
            String[] values4 = this.sys.ModelCollection[Integer.valueOf(first4[1]).intValue()].getDataValues();
            fillTable(names4, values4);
        } else if (path.startsWith("Model ")) {
            String[] first5 = this.tree.getSelectionPath().getPathComponent(1).toString().split(" ");
            String[] second4 = this.tree.getSelectionPath().getPathComponent(2).toString().split(" ");
            String[] names5 = ((Model2) this.sys.ModelCollection[Integer.valueOf(first5[1]).intValue()]._items.get(Integer.valueOf(second4[1]).intValue())).getDataNames();
            String[] values5 = ((Model2) this.sys.ModelCollection[Integer.valueOf(first5[1]).intValue()]._items.get(Integer.valueOf(second4[1]).intValue())).getDataValues();
            fillTable(names5, values5);
            for (int i = 0; i < this.model.size(); i++) {
                this.model.get(i).setModelLoaded(true);
            }
        } else if (path.startsWith("Drawable")) {
            String[] names6 = this.sys.getDataNames();
            String[] values6 = this.sys.getDataValues();
            fillTable(names6, values6);
        } else if (path.startsWith("ShaderGroup")) {
            String[] names7 = this.sys.shaderGroup.getDataNames();
            String[] values7 = this.sys.shaderGroup.getDataValues();
            fillTable(names7, values7);
        } else if (path.startsWith("ShaderFX ")) {
            String[] second5 = this.tree.getSelectionPath().getPathComponent(2).toString().split(" ");
            String[] names8 = ((ShaderFx) this.sys.shaderGroup.Shaders._items.get(Integer.valueOf(second5[1]).intValue())).getDataNames();
            String[] values8 = ((ShaderFx) this.sys.shaderGroup.Shaders._items.get(Integer.valueOf(second5[1]).intValue())).getDataValues();
            fillTable(names8, values8);
        } else if (path.startsWith("GeometryCollection ")) {
            String[] first6 = this.tree.getSelectionPath().getPathComponent(1).toString().split(" ");
            String[] second6 = this.tree.getSelectionPath().getPathComponent(2).toString().split(" ");
            String[] names9 = ((Model2) this.sys.ModelCollection[Integer.valueOf(first6[1]).intValue()]._items.get(Integer.valueOf(second6[1]).intValue())).Geometries.getDataNames();
            String[] values9 = ((Model2) this.sys.ModelCollection[Integer.valueOf(first6[1]).intValue()]._items.get(Integer.valueOf(second6[1]).intValue())).Geometries.getDataValues();
            fillTable(names9, values9);
        } else if (path.startsWith("Shader ")) {
            String[] selected = path.split(" ");
            String[] names10 = ((ShaderFx) this.sys.shaderGroup.Shaders._items.get(Integer.valueOf(selected[1]).intValue())).shader.getDataNames();
            String[] values10 = ((ShaderFx) this.sys.shaderGroup.Shaders._items.get(Integer.valueOf(selected[1]).intValue())).shader.getDataValues();
            fillTable(names10, values10);
        } else if (path.startsWith("Texture ")) {
            String[] selected2 = path.split(" ");
            String[] shader = this.tree.getSelectionPath().getPathComponent(3).toString().split(" ");
            ShaderParamTexture shad = (ShaderParamTexture) ((ShaderFx) this.sys.shaderGroup.Shaders._items.get(Integer.valueOf(shader[1]).intValue())).shader.ShaderParams.get(Integer.valueOf(selected2[1]).intValue());
            String[] names11 = shad.getDataNames();
            String[] values11 = shad.getDataValues();
            fillTable(names11, values11);
        } else if (path.startsWith("Vector4 ")) {
            String[] selected3 = path.split(" ");
            String[] shader2 = this.tree.getSelectionPath().getPathComponent(3).toString().split(" ");
            ShaderParamVector shad2 = (ShaderParamVector) ((ShaderFx) this.sys.shaderGroup.Shaders._items.get(Integer.valueOf(shader2[1]).intValue())).shader.ShaderParams.get(Integer.valueOf(selected3[1]).intValue());
            String[] names12 = shad2.getDataNames();
            String[] values12 = shad2.getDataValues();
            fillTable(names12, values12);
        } else if (path.startsWith("VertexDeclaration")) {
            String[] first7 = this.tree.getSelectionPath().getPathComponent(1).toString().split(" ");
            String[] second7 = this.tree.getSelectionPath().getPathComponent(2).toString().split(" ");
            String[] third4 = this.tree.getSelectionPath().getPathComponent(4).toString().split(" ");
            String[] names13 = ((Geometry) ((Model2) this.sys.ModelCollection[Integer.valueOf(first7[1]).intValue()]._items.get(Integer.valueOf(second7[1]).intValue())).Geometries._items.get(Integer.valueOf(third4[1]).intValue())).vertexBuffer.VertexDeclaration.getDataNames();
            String[] values13 = ((Geometry) ((Model2) this.sys.ModelCollection[Integer.valueOf(first7[1]).intValue()]._items.get(Integer.valueOf(second7[1]).intValue())).Geometries._items.get(Integer.valueOf(third4[1]).intValue())).vertexBuffer.VertexDeclaration.getDataValues();
            fillTable(names13, values13);
        }
        if (this.tree.getSelectionPath().getPathCount() > 4) {
            String geoPath = this.tree.getSelectionPath().getPathComponent(4).toString();
            if (geoPath.startsWith("Geometry ")) {
                String[] geo = geoPath.split(" ");
                for (int i2 = 0; i2 < this.model.size(); i2++) {
                    if (i2 != Integer.valueOf(geo[1]).intValue()) {
                        this.model.get(i2).setModelLoaded(false);
                    } else {
                        this.model.get(i2).setModelLoaded(true);
                    }
                }
                return;
            }
            this.vertLabel.setText("Vertices: 0/" + this.totalVertCount);
            this.polyLabel.setText("Polys: 0/" + this.totalFaceCount);
            return;
        }
        for (int i3 = 0; i3 < this.model.size(); i3++) {
            this.model.get(i3).setModelLoaded(true);
        }
        this.vertLabel.setText("Vertices: 0/" + this.totalVertCount);
        this.polyLabel.setText("Polys: 0/" + this.totalFaceCount);
    }

    private void fillTable(String[] names, String[] values) {
        int rowCount = this.dataModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            this.dataModel.removeRow(0);
        }
        for (int i2 = 0; i2 < names.length; i2++) {
            this.dataModel.addRow(new Object[]{names[i2], values[i2]});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jMenuItem1ActionPerformed(ActionEvent evt) {
        new FileChooser(this, "WDR");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jMenuItem2ActionPerformed(ActionEvent evt) {
        this.stream = loadUncompressed("decompressed.wdr");
        this.res.Write(this.stream, "compressed2.wdr");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jMenuItem5ActionPerformed(ActionEvent evt) {
        String[] values = {"WDR Analyze", "Beta 0.2", "15-07-2009"};
        String[] thanks = {"Aru ", "Cool Fire", "DeXx  ", "Gforce ", "Jost Vice", "Paroxum  ", "REspawn  ", "supermortalhuman ", "Tim  ", "", "Everyone I forgot"};
        About about = new About(values, thanks);
        about.setVisible(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkASSItemStateChanged(ItemEvent evt) {
    }

    private byte[] loadUncompressed(String file) {
        byte[] dataBuffer = new byte[1048576];
        ReadFunctions rf = new ReadFunctions();
        FileInputStream file_in = rf.openFile(file);
        DataInputStream br = new DataInputStream(file_in);
        for (int i = 0; i < dataBuffer.length; i++) {
            dataBuffer[i] = rf.readByte(br);
        }
        return dataBuffer;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new 11(args));
    }

    public void init(GLAutoDrawable drawable) {
        GLCapabilities caps = new GLCapabilities();
        caps.setHardwareAccelerated(true);
        caps.setDoubleBuffered(true);
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        gl.glClearColor(0.153f, 0.217f, 0.234f, 0.0f);
        gl.glEnable(3553);
        gl.glEnable(2884);
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        gl.glEnable(2929);
        gl.glClear(16640);
        gl.glLoadIdentity();
        glu.gluLookAt(this.camera.getPosX(), this.camera.getPosY(), this.camera.getPosZ(), this.camera.getViewX(), this.camera.getViewY(), this.camera.getViewZ(), this.camera.getUpX(), this.camera.getUpY(), this.camera.getUpZ());
        gl.glCullFace(1029);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 2.0f, this.modelZoom);
        gl.glRotatef(270.0f, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(this.rotationX, 0.0f, 0.0f, 1.0f);
        gl.glRotatef(this.rotationY, 0.0f, 1.0f, 0.0f);
        for (int i = 0; i < this.model.size(); i++) {
            Model mdlTest = this.model.get(i);
            if (mdlTest.isModelLoaded() && this.checkWDR.getState()) {
                mdlTest.render(gl);
            }
        }
        if (this.model.size() > 0 && this.checkUnknown.getState()) {
            renderUnknownVectors(gl);
        }
        if (this.model.size() > 0 && this.checkCenter.getState()) {
            renderCentre(gl);
        }
        if (this.model.size() > 0 && this.checkBounds.getState()) {
            renderBounds(gl);
        }
        gl.glPopMatrix();
    }

    public void drawCube(GL gl, float size, float red, float green, float blue) {
        gl.glColor3f(red, green, blue);
        gl.glBegin(7);
        gl.glVertex3f(size, size, -size);
        gl.glVertex3f(-size, size, -size);
        gl.glVertex3f(-size, size, size);
        gl.glVertex3f(size, size, size);
        gl.glVertex3f(size, -size, size);
        gl.glVertex3f(-size, -size, size);
        gl.glVertex3f(-size, -size, -size);
        gl.glVertex3f(size, -size, -size);
        gl.glVertex3f(size, size, size);
        gl.glVertex3f(-size, size, size);
        gl.glVertex3f(-size, -size, size);
        gl.glVertex3f(size, -size, size);
        gl.glVertex3f(size, -size, -size);
        gl.glVertex3f(-size, -size, -size);
        gl.glVertex3f(-size, size, -size);
        gl.glVertex3f(size, size, -size);
        gl.glVertex3f(-size, size, size);
        gl.glVertex3f(-size, size, -size);
        gl.glVertex3f(-size, -size, -size);
        gl.glVertex3f(-size, -size, size);
        gl.glVertex3f(size, size, -size);
        gl.glVertex3f(size, size, size);
        gl.glVertex3f(size, -size, size);
        gl.glVertex3f(size, -size, -size);
        gl.glEnd();
        gl.glColor3f(1.0f, 1.0f, 1.0f);
    }

    public void renderUnknownVectors(GL gl) {
        for (int i = 0; i < ((Model2) this.sys.ModelCollection[0]._items.get(0)).UnknownVectors.Count; i++) {
            gl.glPushMatrix();
            Vector4 vec = (Vector4) ((Model2) this.sys.ModelCollection[0]._items.get(0)).UnknownVectors.Values.get(i);
            gl.glTranslatef(vec.getX(), vec.getY(), vec.getZ());
            drawCube(gl, 0.1f, 0.0f, 1.0f, 0.0f);
            gl.glPopMatrix();
        }
        gl.glColor3f(1.0f, 1.0f, 1.0f);
    }

    public void renderCentre(GL gl) {
        gl.glPushMatrix();
        Vector4 vec = this.sys.Center;
        gl.glTranslatef(vec.getX(), vec.getY(), vec.getZ());
        drawCube(gl, 0.1f, 1.0f, 0.0f, 0.0f);
        gl.glPopMatrix();
    }

    public void renderBounds(GL gl) {
        gl.glPushMatrix();
        gl.glDisable(2884);
        gl.glPolygonMode(1032, 6913);
        gl.glBegin(7);
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(this.sys.BoundsMax.getX(), this.sys.BoundsMax.getY(), this.sys.BoundsMin.getZ());
        gl.glVertex3f(this.sys.BoundsMin.getX(), this.sys.BoundsMax.getY(), this.sys.BoundsMin.getZ());
        gl.glVertex3f(this.sys.BoundsMin.getX(), this.sys.BoundsMax.getY(), this.sys.BoundsMax.getZ());
        gl.glVertex3f(this.sys.BoundsMax.getX(), this.sys.BoundsMax.getY(), this.sys.BoundsMax.getZ());
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(this.sys.BoundsMax.getX(), this.sys.BoundsMin.getY(), this.sys.BoundsMax.getZ());
        gl.glVertex3f(this.sys.BoundsMin.getX(), this.sys.BoundsMin.getY(), this.sys.BoundsMax.getZ());
        gl.glVertex3f(this.sys.BoundsMin.getX(), this.sys.BoundsMin.getY(), this.sys.BoundsMin.getZ());
        gl.glVertex3f(this.sys.BoundsMax.getX(), this.sys.BoundsMin.getY(), this.sys.BoundsMin.getZ());
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(this.sys.BoundsMax.getX(), this.sys.BoundsMax.getY(), this.sys.BoundsMax.getZ());
        gl.glVertex3f(this.sys.BoundsMin.getX(), this.sys.BoundsMax.getY(), this.sys.BoundsMax.getZ());
        gl.glVertex3f(this.sys.BoundsMin.getX(), this.sys.BoundsMin.getY(), this.sys.BoundsMax.getZ());
        gl.glVertex3f(this.sys.BoundsMax.getX(), this.sys.BoundsMin.getY(), this.sys.BoundsMax.getZ());
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(this.sys.BoundsMax.getX(), this.sys.BoundsMin.getY(), this.sys.BoundsMin.getZ());
        gl.glVertex3f(this.sys.BoundsMin.getX(), this.sys.BoundsMin.getY(), this.sys.BoundsMin.getZ());
        gl.glVertex3f(this.sys.BoundsMin.getX(), this.sys.BoundsMax.getY(), this.sys.BoundsMin.getZ());
        gl.glVertex3f(this.sys.BoundsMax.getX(), this.sys.BoundsMax.getY(), this.sys.BoundsMin.getZ());
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(this.sys.BoundsMin.getX(), this.sys.BoundsMax.getY(), this.sys.BoundsMax.getZ());
        gl.glVertex3f(this.sys.BoundsMin.getX(), this.sys.BoundsMax.getY(), this.sys.BoundsMin.getZ());
        gl.glVertex3f(this.sys.BoundsMin.getX(), this.sys.BoundsMin.getY(), this.sys.BoundsMin.getZ());
        gl.glVertex3f(this.sys.BoundsMin.getX(), this.sys.BoundsMin.getY(), this.sys.BoundsMax.getZ());
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(this.sys.BoundsMax.getX(), this.sys.BoundsMax.getY(), this.sys.BoundsMin.getZ());
        gl.glVertex3f(this.sys.BoundsMax.getX(), this.sys.BoundsMax.getY(), this.sys.BoundsMax.getZ());
        gl.glVertex3f(this.sys.BoundsMax.getX(), this.sys.BoundsMin.getY(), this.sys.BoundsMax.getZ());
        gl.glVertex3f(this.sys.BoundsMax.getX(), this.sys.BoundsMin.getY(), this.sys.BoundsMin.getZ());
        gl.glEnd();
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glPopMatrix();
        gl.glPolygonMode(1028, 6914);
        gl.glEnable(2884);
        gl.glCullFace(1029);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        if (height <= 0) {
            height = 1;
        }
        float h = width / height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(5889);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0d, h, 0.1d, 1000.0d);
        gl.glMatrixMode(5888);
        gl.glLoadIdentity();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (IllegalAccessException ex2) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex2);
        } catch (InstantiationException ex3) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex3);
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Can't find system LookAndFeel\nSetting LookAndFeel to crossplatform");
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e2) {
                System.out.println("Unable to set the LookAndFeel");
            }
        }
    }

    public void setOpenedFile(String name) {
        this.fileName = name;
        this.nameLabel.setText("Name: " + name);
    }

    public void openWDRFile(String file) {
        ReadFunctions rf = new ReadFunctions();
        FileInputStream file_in = rf.openFile(file);
        this.res = null;
        this.res = new ResourceFile(this);
        try {
            this.stream = this.res.Read(file_in);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
        this.jButton3.setEnabled(true);
        this.checkWDR.setEnabled(true);
        this.checkCenter.setEnabled(true);
        this.checkBounds.setEnabled(true);
        this.checkUnknown.setEnabled(true);
        ModelFile mf = new ModelFile();
        ByteReader br = new ByteReader(this.stream, 0);
        System.out.println("Sys size: " + this.sysSize);
        br.setSysSize(this.sysSize);
        this.sys.readSystemMemory(this.stream, br);
        this.model.clear();
        this.totalVertCount = 0;
        this.totalFaceCount = 0;
        Model2 test = (Model2) this.sys.ModelCollection[0]._items.get(0);
        for (int i = 0; i < test.Geometries._items.size(); i++) {
            Geometry geo = (Geometry) test.Geometries._items.get(i);
            this.model.add(mf.loadModel(this.stream, geo.vertexBuffer.DataOffset + this.sysSize, geo.indexBuffer.DataOffset + this.sysSize, geo.VertexCount, geo.FaceCount, geo.VertexStride));
            this.totalVertCount += geo.VertexCount;
            this.totalFaceCount += geo.FaceCount;
        }
        this.vertLabel.setText("Vertices: 0/" + this.totalVertCount);
        this.polyLabel.setText("Polygons: 0/" + this.totalFaceCount);
        fillTree();
        this.jMenuItem1.setEnabled(false);
    }

    private void fillTree() {
        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("ShaderGroup " + Util.getStartOffset(this.sys.shaderGroupOffset));
        this.rootNode.add(treeNode);
        for (int i = 0; i < this.sys.shaderGroup.Shaders.Count; i++) {
            ShaderFx shader = (ShaderFx) this.sys.shaderGroup.Shaders._items.get(i);
            DefaultMutableTreeNode node = new DefaultMutableTreeNode("ShaderFX " + i + this.sys.shaderGroup.Shaders.getStartOffset());
            treeNode.add(node);
            DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("Shader " + i + shader.getStartOffset());
            node.add(node2);
            AddShaderIntree(node2, shader);
        }
        for (int i2 = 0; i2 < this.sys.ModelCollection.length; i2++) {
            DefaultMutableTreeNode treeNode2 = new DefaultMutableTreeNode("ModelCollection " + i2 + this.sys.ModelCollection[i2].getStartOffset());
            this.rootNode.add(treeNode2);
            for (int i22 = 0; i22 < this.sys.ModelCollection[i2]._items.size(); i22++) {
                DefaultMutableTreeNode treeNode22 = new DefaultMutableTreeNode("Model " + i22 + ((Model2) this.sys.ModelCollection[i2]._items.get(i22)).getStartOffset());
                treeNode2.add(treeNode22);
                DefaultMutableTreeNode treeNode3 = new DefaultMutableTreeNode("GeometryCollection " + i22 + ((Model2) this.sys.ModelCollection[i2]._items.get(i22)).Geometries.getStartOffset());
                treeNode22.add(treeNode3);
                for (int i3 = 0; i3 < ((Model2) this.sys.ModelCollection[i2]._items.get(i22)).Geometries.Count; i3++) {
                    DefaultMutableTreeNode treeNode4 = new DefaultMutableTreeNode("Geometry " + i3 + ((Geometry) ((Model2) this.sys.ModelCollection[i2]._items.get(i22)).Geometries._items.get(i3)).getStartOffset());
                    treeNode3.add(treeNode4);
                    DefaultMutableTreeNode treeNode5 = new DefaultMutableTreeNode("VertexBuffer " + ((Geometry) ((Model2) this.sys.ModelCollection[i2]._items.get(i22)).Geometries._items.get(i3)).vertexBuffer.getStartOffset());
                    DefaultMutableTreeNode treeNode6 = new DefaultMutableTreeNode("IndexBuffer " + ((Geometry) ((Model2) this.sys.ModelCollection[i2]._items.get(i22)).Geometries._items.get(i3)).indexBuffer.getStartOffset());
                    treeNode4.add(treeNode5);
                    treeNode4.add(treeNode6);
                    DefaultMutableTreeNode treeNode7 = new DefaultMutableTreeNode("VertexDeclaration");
                    treeNode5.add(treeNode7);
                }
            }
        }
    }

    public void AddShaderIntree(DefaultMutableTreeNode node, ShaderFx shaderFx) {
        Shader shader = shaderFx.shader;
        for (int i2 = 0; i2 < shader.ShaderParamCount; i2++) {
            DefaultMutableTreeNode shaderNode = new DefaultMutableTreeNode("Shader");
            switch (((Byte) shader.ShaderParamTypes.Values.get(i2)).byteValue()) {
                case 0:
                    ShaderParamTexture shad = (ShaderParamTexture) shader.ShaderParams.get(i2);
                    shaderNode = new DefaultMutableTreeNode("Texture " + i2 + shad.getStartOffset());
                    break;
                case 1:
                    ShaderParamVector shad2 = (ShaderParamVector) shader.ShaderParams.get(i2);
                    shaderNode = new DefaultMutableTreeNode("Vector4 " + i2 + shad2.getStartOffset());
                    break;
                case 4:
                    shaderNode = new DefaultMutableTreeNode("Matrix " + i2);
                    break;
            }
            node.add(shaderNode);
        }
    }

    public void enableLabels() {
        this.compressionLabel.setEnabled(true);
        this.typeLabel.setEnabled(true);
        this.nameLabel.setEnabled(true);
        this.polyLabel.setEnabled(true);
        this.vertLabel.setEnabled(true);
        this.graphSizeLabel.setEnabled(true);
        this.sysSizeLabel.setEnabled(true);
    }

    public void setCompression(String comp) {
        this.compressionLabel.setText("Compression: " + comp);
    }

    public void setType(String type) {
        this.typeLabel.setText("Type: " + type);
    }

    public void saveFile(String file) {
        this.res.Write(this.stream, file);
    }

    public void setGraphicSize(int size) {
        this.graphSizeLabel.setText("Graphics Size: " + size);
    }

    public void setSystemSize(int size) {
        this.sysSize = size;
        this.sysSizeLabel.setText("System Size: " + size);
    }

    public String getFileName() {
        return this.fileName;
    }

    public boolean isSaveDEC() {
        return this.checkSaveDEC.getState();
    }
}
