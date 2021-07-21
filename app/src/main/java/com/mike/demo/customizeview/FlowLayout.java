package com.mike.demo.customizeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {
    //new FlowLayout
    public FlowLayout(Context context) {
        super(context);
    }

    //xml 里调用: 获取xml时候，xml 使用反射转换为java代码时候，setContentView 之后会反射有两个参数的构造函数
    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //主题style
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    int horizontalSpace = 10;
    int verticalSpace = 8;

    List<List<View>> allLineViews;  //保存信息给布局onLayout时候用
    List<Integer> allHeight;  //保存信息给布局onLayout时候用

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //widthMeasureSpec 哪来的，谁给的，意义是？
        //onMeasure 是父亲触发的，widthMeasureSpec是给孩子用的，
        int parentNeedWidth = 0;
        int parentNeedHeight = 0;

        allLineViews = new ArrayList<>();
        allHeight = new ArrayList<>();

        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);

        List<View> viewsInLine = new ArrayList<>();
        int widthUsedLine = 0;
        int lineHeight = 0;

        int padding_left = getPaddingLeft();
        int padding_right = getPaddingRight();
        int padding_Top = getPaddingTop();
        int padding_bottom = getPaddingBottom();

        // 先度量孩子的大小
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {   //测量孩子开始
            View childView = getChildAt(i);

            //LayoutParams是什么？
            //android:layout_width = "10dp"
            //android:layout_width = "wrap_content"
            //android:layout_width = "match_parent"
            LayoutParams layoutParams = childView.getLayoutParams();  //转换成具体的dimens值

            //MeasureSpec: int数据32位，高两位表示mode：低30位表示size，
            //mode: EXACTLY： 父容器已经得到自己View确切的大小
            //mode: AT_MOST： 父容器指定一个大小，孩子不可能超过这个值
            //mode: UNSPECIFIED：

            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, padding_left + padding_right, layoutParams.width);
            int childHeightMeasureSpec  = getChildMeasureSpec(heightMeasureSpec, padding_bottom + padding_Top, layoutParams.height);

            childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);

            int childMeasureWidth = childView.getMeasuredWidth();
            int childMeasureHeight = childView.getMeasuredHeight();

            if (widthUsedLine + childMeasureWidth + horizontalSpace > selfWidth) {
                allLineViews.add(viewsInLine);
                allHeight.add(lineHeight);

                parentNeedWidth = Math.max(parentNeedWidth, widthUsedLine + horizontalSpace);
                parentNeedHeight += lineHeight + verticalSpace;

                viewsInLine = new ArrayList<>();
                widthUsedLine = 0;
                lineHeight = 0;
            }

            //如果还没换行摆放
            viewsInLine.add(childView);
            widthUsedLine += childMeasureWidth + horizontalSpace;
            lineHeight = Math.max(lineHeight, childMeasureHeight);

            //不加这一段，会导致丢失最后一行view。
            if (i == childCount - 1) {
                allLineViews.add(viewsInLine);
                allHeight.add(lineHeight);

                parentNeedWidth = Math.max(parentNeedWidth, widthUsedLine + horizontalSpace);
                parentNeedHeight += lineHeight + verticalSpace;
            }

        } //测量孩子结束

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = (widthMode == MeasureSpec.EXACTLY) ? selfWidth : parentNeedWidth;
        int height = (heightMode == MeasureSpec.EXACTLY) ? selfHeight : parentNeedHeight;

        //再度量自己的大小: 宽，每一行宽的最大值， 高: 所有行高的和。
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int curLeft = getPaddingLeft();
        int curTop = getPaddingTop();

        for (int i = 0; i < allLineViews.size(); i++) {
            List<View> lineViews = allLineViews.get(i);
            int lineHeight = allHeight.get(i);
            for (int j = 0; j < lineViews.size(); j++) {
                View view = lineViews.get(j);
                int left = curLeft;
                int top = curTop;
                int right = left + view.getMeasuredWidth();
                int bottom = top + view.getMeasuredHeight();
                view.layout(left, top, right, bottom);
                curLeft = right + horizontalSpace;
            }

            curLeft = getPaddingLeft();
            curTop += lineHeight + verticalSpace;
        }
    }
}
