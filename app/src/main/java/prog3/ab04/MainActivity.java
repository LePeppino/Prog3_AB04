package prog3.ab04;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Canvas canvas;
    Bitmap bitmap;
    Paint paint;
    int width = 800;
    int height = 800;
    final int textsize = 50;

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

        setContentView(R.layout.activity_main);
    }

    private void halloWelt() {
        String text = "Hallo Welt!";
        float textWidth = this.paint.measureText(text);
        this.paint.setColor(Color.WHITE);
        this.canvas.drawText(text, width / 2 - textWidth / 2, 100, this.paint);
    }

    private void textZentrieren(String text, int y){

    }
}
