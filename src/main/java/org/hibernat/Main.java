package org.hibernat;


import org.hibernat.Entity.Users;
import org.hibernat.Factorys.FactoryClass;
import org.hibernat.Factorys.StudentFactory;
import org.hibernat.Factorys.UserFactory;
import org.hibernat.Service.UserService;



import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);



    Main() {


    }
    public static FactoryClass getfactory(String val){

        if (val.equals("student"))
                return new StudentFactory();
        else if (val.equals("user"))
                return new UserFactory();
        else
            return null;

    }
    public static void main(String[] args) {

        FactoryClass student = getfactory("user");
        student.operation();
    }
}
