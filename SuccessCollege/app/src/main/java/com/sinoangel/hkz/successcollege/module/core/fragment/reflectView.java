package com.sinoangel.hkz.successcollege.module.core.fragment;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by lenovo on 2017/2/5.
 */

    public class reflectView
    {

        /**
         * Realize the reflection effect
         */
        private static int mReflectImageHeight = 234;

        /**
         * View interface into bitmap
         *
         * @param view
         * @return
         */
        public static Bitmap convertViewToBitmap(View view)
        {

//            int  cacheBitmapDirtyKey = 8998;
//            int cacheBitmapKey = 8999;
//
//            Bitmap bitmap = (Bitmap) view.getTag(cacheBitmapKey);
//            Boolean dirty = (Boolean) view.getTag(cacheBitmapDirtyKey);
//            int viewWidth = view.getWidth();
//            int viewHeight = view.getHeight();
//            if (bitmap == null || bitmap.getWidth() != viewWidth || bitmap.getHeight() != viewHeight) {
//                if (bitmap != null && !bitmap.isRecycled()) {
//                    bitmap.recycle();
//                }
//                bitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);
//                view.setTag(cacheBitmapKey, bitmap);
//                dirty = true;
//            }
//            if (dirty == true || !false ) {
//                bitmap.eraseColor(Color.BLUE);
//                Canvas canvas = new Canvas(bitmap);
//                view.draw(canvas);
//                view.setTag(cacheBitmapDirtyKey, false);
//            }
//            return bitmap;


//            view.setDrawingCacheEnabled(true);
//            view.measure(View.MeasureSpec.makeMeasureSpec(20, View.MeasureSpec.EXACTLY),
//                    View.MeasureSpec.makeMeasureSpec(20, View.MeasureSpec.EXACTLY));
//            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
////            view.buildDrawingCache();
//            Bitmap bitmap = view.getDrawingCache();
//            return bitmap;





            view.clearFocus();
            view.setPressed(false);

            boolean willNotCache = view.willNotCacheDrawing();
            view.setWillNotCacheDrawing(false);

            // Reset the drawing cache background color to fully transparent
            // for the duration of this operation
            int color = view.getDrawingCacheBackgroundColor();
            view.setDrawingCacheBackgroundColor(0);

            if (color != 0) {
                view.destroyDrawingCache();
            }
            view.buildDrawingCache();
            Bitmap cacheBitmap = view.getDrawingCache();
            if (cacheBitmap == null) {
//                Log.e("Folder", "failed getViewBitmap(" + v + ")", new RuntimeException());
                return null;
            }

            Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

            // Restore the view
            view.destroyDrawingCache();
            view.setWillNotCacheDrawing(willNotCache);
            view.setDrawingCacheBackgroundColor(color);

            return bitmap;
        }

        /**
         * Set the bitmap reflection
         *
         * @param bitmap
         * @return
         */
        public static Bitmap createReflectedImage(Bitmap bitmap, int reflectHeight)
        {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (height <= reflectHeight)
            {
                return null;
            }
            Matrix matrix = new Matrix();
            matrix.preScale(1, -1);
            Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height
                    - reflectHeight, width, reflectHeight, matrix, true);
            Bitmap bitmapWithReflection = Bitmap.createBitmap(width, reflectHeight,
                    Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapWithReflection);
            canvas.drawBitmap(reflectionImage, 0, 0, null);
            LinearGradient shader = new LinearGradient(0, 0, 0,
                    bitmapWithReflection.getHeight(), 0x80ffffff, 0x00ffffff,
                    TileMode.CLAMP);
            Paint paint = new Paint();
            paint.setShader(shader);
            paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
            canvas.drawRect(0, 0, width, bitmapWithReflection.getHeight(), paint);
            return bitmapWithReflection;
        }

        public static Bitmap createCutReflectedImage(Bitmap bitmap, int cutHeight)
        {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int totleHeight = mReflectImageHeight + cutHeight;
            if (height <= totleHeight)
            {
                return null;
            }
            Matrix matrix = new Matrix();
            matrix.preScale(1, -1);
            Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height
                            - mReflectImageHeight - cutHeight, width, mReflectImageHeight,
                    matrix, true);
            Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
                    mReflectImageHeight, Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapWithReflection);
            canvas.drawBitmap(reflectionImage, 0, 0, null);
            LinearGradient shader = new LinearGradient(0, 0, 0,
                    bitmapWithReflection.getHeight(), 0x80ffffff, 0x00ffffff,
                    TileMode.CLAMP);
            Paint paint = new Paint();
            paint.setShader(shader);
            paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
            canvas.drawRect(0, 0, width, bitmapWithReflection.getHeight(), paint);
            if (!reflectionImage.isRecycled())
            {
                reflectionImage.recycle();
            }
            System.gc();

            return bitmapWithReflection;
        }

        public static void reflectImage(ImageView imageView, View view)
        {
            Bitmap bitmap = createCutReflectedImage(convertViewToBitmap(view), 0);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageBitmap(bitmap);
        }
    }
