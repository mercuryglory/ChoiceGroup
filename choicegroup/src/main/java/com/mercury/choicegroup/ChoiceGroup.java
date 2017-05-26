package com.mercury.choicegroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang.zhonghao on 2016/12/20
 * descript:  自定义单选 多选按钮,类似radiogroup
 */
public class ChoiceGroup extends LinearLayout {

    private int column = 0;//列数  

    private int currentIndex = 0;//当前按钮下标最大值

    private String currentValue = "";//当前按钮值  

    private List<String> values = new ArrayList<>();//按钮文字列表  

    private Map<Integer, TextView> map = new HashMap<>();//按钮map

    private List<String> valueList = new ArrayList<>();

    //是否可以多选
    private boolean multiple = false;

    public ChoiceGroup(Context context) {
        super(context);
        init(context);
    }

    public ChoiceGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public ChoiceGroup(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        init(context);
    }

    //初始化容器  
    public void init(Context context) {
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);
    }

    public void setMultiple(boolean isMultiple) {
        this.multiple = isMultiple;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public void setValueList(List<String> valueLists) {
        valueList = valueLists;
    }

    public Map<Integer, TextView> getMap() {
        return map;
    }

    //设置当前被选下按钮
    public void setInitChecked(int index) {
        ((CustomCheckText) map.get(index)).setTouch(2);
        //如果是单选
        if (!multiple) {
            setCurrentValue((map.get(index)).getText().toString());
        }
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setCurrentValue(String value) {
        this.currentValue = value;
    }

    public String getCurrentValue() {
        return this.currentValue;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public int getIndex() {
        return currentIndex;
    }

    //如果是多选的,清除调所有选中的值
    public void clearValue() {
        valueList.clear();
    }

    //初始化容器所有视图   currentIndex表示所有按钮的个数
    public void setView(final Context context) {
        //集合中元素的个数
        int size = values.size();
        int row = size / column;
        int leftSize = size % column;
        int margin = DensityUtil.dip2px(context, 10);
        for (int i = 0; i < row; i++) {
            //每一行的高度占比
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(HORIZONTAL);
            linearLayout.setLayoutParams(new LayoutParams(LayoutParams
                    .MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < column; j++) {
                final CustomCheckText button = new CustomCheckText(context);
                button.setGravity(Gravity.CENTER);
                LayoutParams layoutParams = new LayoutParams
                        (0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
//                if (i % 2 == 0) {
//                    layoutParams.setMargins(margin, margin, margin, margin);
//                } else {
//                    layoutParams.setMargins(margin, margin, margin,0);
//                }
                layoutParams.setMargins(margin, margin, margin,0);
                button.setLayoutParams(layoutParams);
                button.setText(values.get(column * i + j));
                currentIndex = column * i + j;
                button.setOnValueChangedListner(new CustomCheckText.OnValueChangedListner() {
                    @Override
                    public void onValueChanged(String value) {
                        //可多选
                        if (multiple) {
                            if (button.getTouch() % 2 == 0) {
                                //将该按钮上的值从集合中去除
                                valueList.remove(value);
                            } else {
                                //将该按钮上的值添加到集合中
                                valueList.add(value);
                            }
                            //                            setCurrentValue(value);
                        } else {
                            setCurrentValue(value);
                            clearSelected(currentIndex);
                        }

                    }
                });
                map.put(column * i + j, button);
                linearLayout.addView(button);
            }
            addView(linearLayout);
        }

        //集合中的元素和列数并非成比例关系，此时要针对最后一行做判断
        if (leftSize != 0) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(HORIZONTAL);
            linearLayout.setLayoutParams(new LayoutParams(LayoutParams
                    .MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            for (int m = 0; m < column; m++) {
                if (m < leftSize) {
                    final CustomCheckText button = new CustomCheckText(context);
                    button.setGravity(Gravity.CENTER);
                    LayoutParams layoutParams = new LayoutParams(0,
                            ViewGroup.LayoutParams.WRAP_CONTENT, 1);
                    layoutParams.setMargins(margin, margin, margin, 0);
                    button.setLayoutParams(layoutParams);
                    button.setText(values.get(size - leftSize + m));
                    currentIndex = size - leftSize + m;
                    button.setOnValueChangedListner(new CustomCheckText.OnValueChangedListner() {
                        @Override
                        public void onValueChanged(String value) {
                            if (multiple) {
                                if (button.getTouch() % 2 == 0) {
                                    valueList.remove(value);
                                } else {
                                    valueList.add(value);
                                }
                                //                                setCurrentValue(value);
                            } else {
                                setCurrentValue(value);
                                clearSelected(currentIndex);
                            }
                        }
                    });
                    map.put(size - leftSize + m, button);
                    linearLayout.addView(button);
                } else {
                    LayoutParams layoutParams = new LayoutParams(0,
                            ViewGroup.LayoutParams.WRAP_CONTENT, 1);
                    layoutParams.setMargins(margin, margin, margin, 0);
                    CustomCheckText button = new CustomCheckText(context);
                    button.setLayoutParams(layoutParams);
                    button.setVisibility(INVISIBLE);
                    linearLayout.addView(button);
                }
            }
            addView(linearLayout);
        }
    }

    //清除所有选择  
    public void clearSelected(int Index) {
        System.out.println("length = " + map.size());
        for (int index = 0; index < map.size(); index++) {

            ((CustomCheckText) map.get(index)).setTouch(1);
        }
    }

}  
