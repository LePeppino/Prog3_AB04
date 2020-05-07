package prog3.ab04;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Canvas canvas;
    Bitmap bitmap;
    Paint paint;
    int width = 800;
    int height = 800;
    final int textsize = 50;
    private Timer timer = new Timer();
    //derSpringendePunkt
    int grenzeLinks = 30;
    int grenzeRechts = 770;
    int grenzeOben = 30;
    int grenzeUnten = 770;
    int ballRadius = 20;
    float ballX = 100f;
    float ballY = 700f;
    float velociteX = 2.0f;
    float velociteY = 7.5f;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.bitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
        this.canvas = new Canvas(this.bitmap);
        this.imageView = new ImageView(this);
        this.imageView.setImageBitmap(this.bitmap);
        this.paint = new Paint();

        setContentView(imageView);

        this.canvas.drawColor(Color.argb(255, 0, 0, 255));
        this.paint.setTextSize(50);
        this.halloWelt();
        this.halloNachbarn();
        this.zeichneSmiley(200);
        this.derSpringendePunkt();

        this.timer.schedule(new TimerTask(){
            @Override
            public void run(){
                derSpringendePunkt();
            }
        }
        , 0, 17);
        //setContentView(R.layout.activity_main);
    }

    private void halloWelt() {
        String text = "Hallo Welt!";
        float textWidth = this.paint.measureText(text);
        this.paint.setColor(Color.WHITE);
        this.canvas.drawText(text, width / 2 - textWidth / 2, 100, this.paint);
    }

    private void textZentrieren(String text, int y){
        float textWidth = this.paint.measureText(text);
        this.canvas.drawText(text, width / 2 - textWidth / 2, 150, this.paint);
    }

    private void halloNachbarn() {
        String text = "Hallo uff + uff!";
        this.paint.setColor(Color.WHITE);
        textZentrieren(text, width);
    }

    public void zeichneSmiley(int radius) {
        this.paint.setColor(Color.YELLOW);
        this.canvas.drawCircle(width / 2, height / 2, radius, this.paint);
        //left eye
        this.paint.setColor(Color.BLACK);
        this.canvas.drawCircle(width / 2 - radius / 2,
                                width / 2 - radius / 2 + radius / 5,
                                radius / 4,
                                this.paint);
        //right eye
        this.canvas.drawCircle(width / 2 + radius / 2,
                width / 2 - radius / 2 + radius / 5,
                radius / 4,
                this.paint);
        //mouth
        this.canvas.drawLine(width / 2 - radius / 2,
                height / 2 + radius / 4,
                width / 2,
                height / 2 + radius / 2,
                this.paint);
        this.canvas.drawLine(width / 2 + radius / 2,
                height / 2 + radius / 4,
                width / 2,
                height / 2 + radius / 2,
                this.paint);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void derSpringendePunkt() {
        //Log-Test if method loops infinitely
        //Log.i("MainActivity", LocalDateTime.now() + ": der springende Punkt");
        this.paint.setColor(Color.BLUE);
        this.canvas.drawCircle(ballX,ballY,ballRadius, this.paint);

        this.paint.setColor(Color.RED);
        if(ballY >= grenzeUnten || ballY <= grenzeOben){
            velociteY *= (-1);
        }
        if(ballX >= grenzeRechts || ballX <= grenzeLinks){
            velociteX *= (-1);
        }
        ballY += velociteY;
        ballX += velociteX;

        this.canvas.drawCircle(ballX,ballY,ballRadius, this.paint);

        this.imageView.invalidate();
    }



    }
