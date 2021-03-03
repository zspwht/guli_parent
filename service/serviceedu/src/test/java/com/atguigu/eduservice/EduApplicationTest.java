package com.atguigu.eduservice;

import com.atguigu.commonutils.JWTUtils;
import com.atguigu.edu.EduApplication;
import com.atguigu.edu.entity.Teacher;
import com.atguigu.edu.service.CourseService;
import com.atguigu.edu.service.TeacherService;
import com.atguigu.edu.vo.CoursePublicVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

@SpringBootTest(classes = EduApplication.class)
@RunWith(SpringRunner.class)
public class EduApplicationTest {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
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

    @Test
    public void testSearchCoursePulishVo(){
        CoursePublicVo coursePublicVo = courseService.searchCoursePublishById("1355111567336235010");
        System.out.println(coursePublicVo);

    }
    /**
     * 根据id删除课程信息
     */
    @Test
    public void testDeleteCourseInfoById(){
        Boolean aBoolean = courseService.removeCourseById("1354969276256669697");
        System.out.println(aBoolean);
    }

    /**
     * 测试jwtToken
     */
    @Test
    public void testJwtToken(){
        String jwtToken = JWTUtils.getJWTToken("123456", "张三");
        System.out.println(jwtToken);

       /* String id = JWTUtils.getMemberIdByJwtToken();
        System.out.println(id);*/
    }
}
