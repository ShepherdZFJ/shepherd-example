package com.shepherd.basedemo.function;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/9/5 22:39
 */
public class StudentCallbackTask implements CallbackTask<Person, Student>{
    @Override
    public Student execute(Person person) {
         // 开学完成注册信息，从社会人士变成学生了
        Student student = new Student();
        student.setName(person.getName());
        return student;
    }

    @Override
    public void onSuccess(Student student) {
        System.out.println("student register success, student name:" + student.getName());
    }

    @Override
    public void onFailure(Throwable t) {
        System.out.println("student register failure");
    }
}
