/*key so that you don't forget
 1: line
 2: T
 3: square
 4: S  (_|-)
 5: Z (-|_)
 6; L
 7; J

 */

package trap1.bhaleraoomkar.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static HashMap<Integer, Integer> colors = new HashMap<Integer, Integer>();

    DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        colors.put(1, Color.CYAN);
        colors.put(2, Color.MAGENTA);
        colors.put(3, Color.YELLOW);
        colors.put(4, Color.RED);
        colors.put(5, Color.GREEN);
        colors.put(6, Color.BLUE);
        colors.put(7, Color.rgb(255, 165, 0));
        //Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        drawView=findViewById(R.id.drawView);
    }
}
