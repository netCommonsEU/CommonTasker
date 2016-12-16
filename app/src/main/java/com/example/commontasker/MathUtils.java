package com.example.commontasker;

/**
 * Created by Αρης on 5/10/2016.
 */


        import android.graphics.RectF;


public final class MathUtils {

    protected static float truncate(float f, int decimalPlaces) {
        float decimalShift = (float) Math.pow(10, decimalPlaces);
        return Math.round(f * decimalShift) / decimalShift;
    }



    protected static boolean haveSameAspectRatio(RectF r1, RectF r2) {
// Reduces precision to avoid problems when comparing aspect ratios.
        float srcRectRatio = MathUtils.truncate(MathUtils.getRectRatio(r1), 2);
        float dstRectRatio = MathUtils.truncate(MathUtils.getRectRatio(r2), 2);

// Compares aspect ratios that allows for a tolerance range of [0, 0.01]
        return (Math.abs(srcRectRatio - dstRectRatio) <= 0.01f);
    }


    /**
     * Computes the aspect ratio of a given rect.
     * @param rect the rect to have its aspect ratio computed.
     * @return the rect aspect ratio.
     */
    protected static float getRectRatio(RectF rect) {
        return rect.width() / rect.height();
    }
}
