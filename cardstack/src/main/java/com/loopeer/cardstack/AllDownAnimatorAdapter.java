package com.loopeer.cardstack;

import android.animation.ObjectAnimator;
import android.view.View;

public class AllDownAnimatorAdapter extends AnimatorAdapter {
    public AllDownAnimatorAdapter(CardStackView cardStackView) {
        super(cardStackView);
    }

    protected void itemExpandAnimatorSet(CardStackView.ViewHolder viewHolder, int position) {
        View itemView = viewHolder.itemView;
        itemView.clearAnimation();
        ObjectAnimator oa = ObjectAnimator.ofFloat(itemView, View.Y, new float[]{itemView.getY(), (float) (this.mCardStackView.getScrollY() + this.mCardStackView.getPaddingTop())});
        this.mSet.play(oa);
        for (int i = 0; i < this.mCardStackView.getChildCount(); ++i) {
            if (i != this.mCardStackView.getSelectPosition()) {
                View child = this.mCardStackView.getChildAt(i);
                child.clearAnimation();
                ObjectAnimator oAnim;
                if (i != this.mCardStackView.getSelectPosition()) {
                    oAnim = ObjectAnimator.ofFloat(child, View.Y, new float[]{child.getY(), (float) (this.mCardStackView.getShowHeight() - child.getHeight() + (i * 150))});
                    this.mSet.play(oAnim);
                } else {
                    oAnim = ObjectAnimator.ofFloat(child, View.Y, new float[]{child.getY(), (float) (this.mCardStackView.getShowHeight() + this.mCardStackView.getScrollY())});
                    this.mSet.play(oAnim);
                }
            }
        }
    }

    protected void itemCollapseAnimatorSet(CardStackView.ViewHolder viewHolder) {

        if (mCardStackView.getSelectPosition() < 0) {
            mCardStackView.setSelectPosition(0);
            itemExpandAnimatorSet(viewHolder, 0);
            return;
        }
        itemExpandAnimatorSet(viewHolder, 0);
        return;
    }
}

