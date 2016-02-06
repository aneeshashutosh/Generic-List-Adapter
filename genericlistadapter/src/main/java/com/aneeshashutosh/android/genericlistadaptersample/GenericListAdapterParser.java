package com.aneeshashutosh.android.genericlistadaptersample;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.InputMismatchException;

/**
 * @author aneeshashutosh
 */
public class GenericListAdapterParser {
    protected static void parseView(View view, Object object) {
        if (view instanceof TextView) {
            handleTextView((TextView) view, object);
        } else if (view instanceof ImageView) {
            handleImageView((ImageView) view, object);
        } else { // view is not recognized, ignore
            Log.e(GenericListAdapter.ERROR_TAG, "View not recognized.");
        }
    }

    private static void handleTextView(TextView textView, Object object) {
        if (object instanceof CharSequence) {
            textView.setText((CharSequence) object);
        } else {
            throw new InputMismatchException("The object is not compatible with a TextView.");
        }
    }

    private static void handleImageView(ImageView imageView, Object object) {
        if (object instanceof Drawable) {
            imageView.setImageDrawable((Drawable) object);
        } else if (object instanceof Bitmap) {
            imageView.setImageBitmap((Bitmap) object);
        } else if (object instanceof Uri) {
            imageView.setImageURI((Uri) object);
        }  else if (object instanceof Matrix) {
            imageView.setImageMatrix((Matrix) object);
        } else { // view is not recognized, ignore
            throw new InputMismatchException("The object is not compatible with an ImageView.");
        }
    }
}
