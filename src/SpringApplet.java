import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.util.Timer;
import javax.swing.JApplet;

public class SpringApplet extends JApplet {

    //stworzenie pola klasy SimEngine
    private SimEngine SE;
    //stworzenie pola klasy SimTask
    private SimTask ST;
    //stworzenie pola klasy Timer
    private Timer T;
    private Image buf;
    private Graphics bufG;


    int s=600, w=500; //szerokości i wysokość okna

    public void init() {

        double m=10, k=5, c=2, l0=100, t=0.01;
        setSize(s,w);
        //inicjalizacja obiektów
        Vector2D PozM = new Vector2D(350, 400);
        Vector2D PozUtw = new Vector2D(300,150);
        Vector2D PredM = new Vector2D();
        Vector2D G = new Vector2D(0, 10);
        SE=new SimEngine(m,k,c,l0,PozM,PredM,PozUtw,G);
        ST=new SimTask(this, SE, t);
        T=new Timer();
        //wywołanie metody scheduleAtFixedRate
        T.scheduleAtFixedRate(ST,0,(long)(t*100));

    }

    public void paint(Graphics g) {

        buf = createImage(s,w);
        bufG = buf.getGraphics();

        paintComponent(bufG);
        g.drawImage(buf,0,0,null);
    }

    public void paintComponent(Graphics g) {

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, s, w);

        g.setColor(Color.DARK_GRAY);//spręzyna i utwierdzenie
        g.fillRect((int)SE.getRutw().x-20, (int)SE.getRutw().y-10, 40, 20);
        g.drawLine((int)SE.getRutw().x, (int)SE.getRutw().y, (int)SE.getRm().x, (int)SE.getRm().y);
        g.drawLine((int)SE.getRutw().x, (int)SE.getRutw().y, (int)SE.getRm().x, (int)SE.getRm().y);

        g.setColor(Color.RED);//masa-kulka
        g.fillOval((int)SE.getRm().x-10, (int)SE.getRm().y-10, 20, 20);
    }
}

