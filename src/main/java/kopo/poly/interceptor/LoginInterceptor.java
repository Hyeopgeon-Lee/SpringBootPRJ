package kopo.poly.interceptor;

import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("난 Controller가 실행되기 전에 실행되는 함수다. 보통 AOP를 적용할 때 사용함");

        // 세션 값 가져오기
        HttpSession session = request.getSession();

        String user_id = CmmUtil.nvl((String) session.getAttribute("SESSION_USER_ID"));

        log.info("user_id : "+ user_id);

        if (user_id.length() > 0) {

            // 세션값이 존재한다면(즉, 로그인되었다면)
            return true;

        } else {

            // 로그인이 되지 않으면, 로그인 화면으로 이동
            response.sendRedirect("/index");
            return false;

        }
    }
}
