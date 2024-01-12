package codesLA280;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.universe.*;
import org.jogamp.vecmath.*;

public class FinalCodeLA extends JPanel {

    private static final long serialVersionUID = 1L;
    private static JFrame frame;
    private static final int OBJ_NUM = 6;                  // a total of six cup components

    /* a function to build the content branch */
    private static BranchGroup create_Scene() {
        BranchGroup sceneBG = new BranchGroup();

        FinalShapesLA[] cup3D = new FinalShapesLA[OBJ_NUM];
        cup3D[0] = new CupBase();                          // create components of the folding cup
        cup3D[1] = new CupWall0();
        cup3D[2] = new CupWall1();
        cup3D[3] = new CupWall2();
        cup3D[4] = new CupWall3();
        cup3D[5] = new CupWall4();

        for (int i = 0; i < OBJ_NUM; i++)                  // attach components to 'sceneBG'
            sceneBG.addChild(cup3D[i].position_Object());

        return sceneBG;
    }

    /* NOTE: Keep the constructor for each of the labs and assignments */
    public FinalCodeLA(BranchGroup sceneBG) {
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas = new Canvas3D(config);

        SimpleUniverse su = new SimpleUniverse(canvas);    // create a SimpleUniverse
        CommonsLA.define_Viewer(su, new Point3d(1.0d, 2.0d, 4.0d));

        sceneBG.addChild(CommonsLA.add_Lights(CommonsLA.White, 1));
        sceneBG.addChild(CommonsLA.key_Navigation(su));     // allow key navigation
        sceneBG.compile();		                           // optimize the BranchGroup
        su.addBranchGraph(sceneBG);                        // attach the scene to SimpleUniverse


        setLayout(new BorderLayout());
        add("Center", canvas);
        frame.setSize(800, 800);                           // set the size of the JFrame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        frame = new JFrame("LA's Final Exam");             // NOTE: change LA to student's initials
        frame.getContentPane().add(new FinalCodeLA(create_Scene()));  // create an instance of the class
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}