package com.littlejoyindia.littlejoyindia.ui.dashboard.salon.subCat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.databinding.CategoryViewBinding;

public class CategoryViewHolder extends ParentViewHolder {

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;

    @NonNull
    private final ImageView mArrowExpandImageView;
    private TextView mRecipeTextView;
    private View mView ;


    CategoryViewBinding blogViewBinding ;
    public CategoryViewHolder(CategoryViewBinding blogViewBinding) {
        super(blogViewBinding.getRoot());
        this.blogViewBinding = blogViewBinding;
        mRecipeTextView = (TextView) itemView.findViewById(R.id.recipe_textview);
        mView = itemView.findViewById(R.id.viewSeprator);
        mArrowExpandImageView = (ImageView) itemView.findViewById(R.id.arrow_expand_imageview);
    }

    public void bind(@NonNull Category recipe) {

        mRecipeTextView.setText(recipe.getName());
        blogViewBinding.executePendingBindings();
    }

    @SuppressLint("NewApi")
    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (expanded) {
                mView.setBackgroundColor(Color.parseColor("#00BCE2"));
                mArrowExpandImageView.setImageResource(R.drawable.up_arrow);
                //mArrowExpandImageView.setRotation(ROTATED_POSITION);
            } else {
                mArrowExpandImageView.setImageResource(R.drawable.down_arrow);
                mView.setBackgroundColor(Color.parseColor("#f0f0f0"));
               // mArrowExpandImageView.setRotation(INITIAL_POSITION);
            }



        }
    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        super.onExpansionToggled(expanded);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            RotateAnimation rotateAnimation;
//            if (expanded) { // rotate clockwise
//                rotateAnimation = new RotateAnimation(ROTATED_POSITION,
//                        INITIAL_POSITION,
//                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
//                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//            } else { // rotate counterclockwise
//                rotateAnimation = new RotateAnimation(-1 * ROTATED_POSITION,
//                        INITIAL_POSITION,
//                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
//                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//            }
//
//            rotateAnimation.setDuration(200);
//            rotateAnimation.setFillAfter(true);
//            mArrowExpandImageView.startAnimation(rotateAnimation);
//        }
    }
}