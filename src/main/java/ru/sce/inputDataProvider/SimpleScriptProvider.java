package ru.sce.inputDataProvider;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class SimpleScriptProvider implements ScriptProvider{
    @Override
    public String getScript(){
            return "select d.dname as department, count(e.empno) as total_employees, " +
                    "(select min(id) from emp) as t,\n"+
                    "max(ename||' ('||job||')') keep (dense_rank first order by hiredate desc) last_hired_employee\n" +
                    "from dept d, emp e\n" +
                    "where d.deptno = e.deptno(+)\n";

                    /*"and d.deptno > 100\n" +
                    "group by d.deptno,d.dname\n" +
                    "order by 2 desc";*/
    }
}
