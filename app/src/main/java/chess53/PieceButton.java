package chess53;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

public class PieceButton extends androidx.appcompat.widget.AppCompatImageButton {
    public boolean selected = false;

    public PieceButton(Context context) {
        super(context);
    }

    public PieceButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PieceButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

}