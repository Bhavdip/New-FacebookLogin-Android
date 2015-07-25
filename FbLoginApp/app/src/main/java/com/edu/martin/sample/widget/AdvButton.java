package com.edu.martin.sample.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Button;
import com.edu.martin.sample.R;
import com.edu.martin.sample.util.TypeFaceSingleTone;


public class AdvButton extends Button {

    public AdvButton(Context context) {
        super(context);
    }

    public AdvButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(attrs);
    }

    public AdvButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(attrs);
    }

    /**
     * set custom fonts property
     *
     * @param mAttrs
     */
    private void setCustomFont(AttributeSet mAttrs) {
        if (mAttrs == null) return;
        // Get Styled for Particular custom view in XML
        TypedArray mTypedArray = getContext().obtainStyledAttributes(mAttrs, R.styleable.advanceButton);
        // Get value of give style Attributes of custom view in XML
        String fontName = mTypedArray.getString(R.styleable.advanceButton_buttonfont);
        if (!isInEditMode() && !TextUtils.isEmpty(fontName)) {
            if (fontName.length() > 0 || fontName != null) {
                setCustomFontsToView(getContext(), fontName);
                mTypedArray.recycle();
            }
        } else {
            if (!isInEditMode())
                throw new IllegalArgumentException(getResources().getString(R.string.exception_font_specified));
        }
    }


    /**
     * To set the fonts to view the font name should display in xml attributes
     *
     * @param mContext
     * @param typefaceName
     * @return
     */
    private boolean setCustomFontsToView(Context mContext, String typefaceName) {
        // set type face either for catch or create new
        setTypeface(TypeFaceSingleTone.getSingleInstance().getCatchTypeFace(mContext, typefaceName));
        return true;
    }
}
