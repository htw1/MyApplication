package com.example.htw.myapplication.control;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.example.htw.myapplication.R;


public class FontTextView extends AppCompatTextView {
	private static final String TAG = "TextView";

	public FontTextView(Context context) {
		super(context);
	}

	public FontTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setCustomFont(context, attrs);
		setHtmlText(context, attrs);
	}

	public FontTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setCustomFont(context, attrs);
	}

	private void setCustomFont(Context ctx, AttributeSet attrs) {
		TypedArray a = ctx.obtainStyledAttributes(attrs,
				R.styleable.FontText);
		String customFont = a.getString(R.styleable.FontText_customFont);

        if (customFont != null) {
            setCustomFont(ctx, customFont);
        }

		a.recycle();
	}

	public boolean setCustomFont(Context ctx, String asset) {
		Typeface tf = null;
		try {
			tf = FontCache.createFromAsset(ctx.getAssets(), asset);
		} catch (Exception e) {
			Log.e(TAG, "Could not get typeface: " + e.getMessage());
			return false;
		}

		setTypeface(tf);

        setPaintFlags(getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);

		return true;
	}

	public void setHtmlText(Context ctx, AttributeSet attrs) {
		TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.FontText);
		String htmlText = a.getString(R.styleable.FontText_htmlText);
		if (htmlText != null) {
			
			if (isInEditMode()) {
				setText(htmlText);
			} else {
				setText(Html.fromHtml(htmlText));
			}
		}
		a.recycle();
	}

	public void setHtmlTextFromString(String htmlText){
		if (htmlText != null) {

			if (isInEditMode()) {
				setText(htmlText);
			} else {
				setText(Html.fromHtml(htmlText));
			}
		}
	}
}
