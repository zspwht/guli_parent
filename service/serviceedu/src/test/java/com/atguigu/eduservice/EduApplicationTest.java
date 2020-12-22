package com.atguigu.eduservice;

import com.atguigu.edu.EduApplication;
import com.atguigu.edu.entity.Teacher;
import com.atguigu.edu.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = EduApplication.class)
@RunWith(SpringRunner.class)
public class EduApplicationTest {
    @Autowired
    private TeacherService teacherService;
    @Test
    public void list(){
        teacherService.list(null);
    }
    @Test
    public void testInsert(){
        Teacher teacher = new Teacher();
        teacher.setName("zzz");
        teacher.setLevel(2);
        teacher.setAvatar(".....");
        teacherService.save(teacher);
    }
}
