package com.loften.bottomnavigationviewsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.loften.bottomnavigationviewsample.bottomnavigation.BottomNavigationActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MainActivity extends AppCompatActivity {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.bottom_navigation);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BottomNavigationActivity.class));
            }
        });
    }

    private void initData(){
//        classForName();
//        showDeclaredMethods();
//        showDeclaredFields();
//        showFields();
//        getParentFields();
//        showInterfaces();
    }

    private static void classForName() {
        try {
            // 获取 Class 对象
            Class<?> clz = Class.forName("com.loften.bottomnavigationviewsample.TestImp");
            // 通过 Class 对象获取 Constructor，TestImp 的构造函数有一个字符串参数
            // 因此这里需要传递参数的类型
            Constructor<?> constructor = clz.getConstructor(String.class, int.class, String.class, String.class);
            // 通过 Constructor 来创建 TestImp 对象
            Object obj = constructor.newInstance("anni", 20, "45kg", "女");
            Log.e(" obj :  ", obj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showDeclaredMethods() {
        TestImp testImp = new TestImp("anni", 20, "45kg");
        Method[] methods = testImp.getClass().getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("declared method name : " + method.getName());
        }

        try {
            Method learnMethod = testImp.getClass().getDeclaredMethod("showHeadImage", String.class);
            // 获取方法的参数类型列表
            Class<?>[] paramClasses = learnMethod.getParameterTypes() ;
            for (Class<?> class1 : paramClasses) {
                System.out.println("learn 方法的参数类型 : " + class1.getName());
            }
            // 是否是 private 函数，属性是否是 private 也可以使用这种方式判断
            System.out.println(learnMethod.getName() + " is private "
                    + Modifier.isPrivate(learnMethod.getModifiers()));
            learnMethod.invoke(testImp, "java ---> ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showDeclaredFields() {
        TestImp testImp = new TestImp("anni", 20, "45kg", "女", 180);
        // 获取当前类和父类的所有公有属性
        Field[] publicFields = testImp.getClass().getDeclaredFields();
        for (Field field : publicFields) {
            System.out.println("declared field name : " + field.getName());
        }

        try {
            // 获取当前类和父类的某个公有属性
            Field gradeField = testImp.getClass().getDeclaredField("sex");
            // 获取属性值
            System.out.println(" my sex is : " + gradeField.get(testImp));
            // 设置属性值
            gradeField.set(testImp, "男");
            System.out.println(" my sex is : " + gradeField.get(testImp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showFields() {
        TestImp testImp = new TestImp("anni", 20, "45kg", "女", 180);
        // 获取当前类和父类的所有公有属性
        Field[] publicFields = testImp.getClass().getFields();
        for (Field field : publicFields) {
            System.out.println("field name : " + field.getName());
        }

        try {
            // 获取当前类和父类的某个公有属性
            Field ageField = testImp.getClass().getField("age");
            System.out.println(" age is : " + ageField.get(testImp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getParentFields(){
        TestImp testImp = new TestImp("anni", 20, "45kg", "女", 180);
        Class<?> superClass = testImp.getClass().getSuperclass();
        while (superClass != null) {
            System.out.println("TestImp's super class is : " + superClass.getName());
            // 再获取父类的上一层父类，直到最后的 Object 类，Object 的父类为 null
            superClass = superClass.getSuperclass();
        }
    }

    private static void showInterfaces() {
        TestImp testImp = new TestImp("anni", 20, "45kg", "女", 180);
        Class<?>[] interfaceses = testImp.getClass().getInterfaces();
        for (Class<?> class1 : interfaceses) {
            System.out.println("TestImp's interface is : " + class1.getName());
        }
    }
}
