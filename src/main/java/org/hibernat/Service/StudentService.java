package org.hibernat.Service;

import org.hibernat.Entity.Student;
import org.hibernat.Repositary.StudentRepo;
import org.hibernat.Repositary.UserRepo;

import java.util.List;

public class StudentService {
    StudentRepo studentrepo = null;

    public StudentService(){
        studentrepo = new StudentRepo();
    }

    public void addStudent(Student student){
        studentrepo.saveUser(student);

    }

    public void updateStudent(Integer id,Student newUser){
        Student user = getStudent(id);
        user.setName(newUser.getName());
        studentrepo.updateUser(user);


    }

    public Student getStudent( Integer id){
        Student userById = studentrepo.getUserById(id);
        return userById ;
    }
    public List<Student> getAllStudent(){
        List<Student> student = studentrepo.getStudents();
        return  student;
    }
    public boolean removeStudent(Integer id){
        Student user = getStudent(id);
        boolean flag = false;
        if (user!=null) {
            studentrepo.removeUser(user);
            flag = true;
        }
        else
            flag= false;
        return flag;
    }

    public void removeAllStudent(){
        studentrepo.removeAllUser();

    }



}
