package com.lonton.ext.springboot.test.validation;

import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

@Slf4j
public class RegExpressionTests {

    @Test
    void testUsernameRegExpression() {
        log.debug("测试用户名的正则表达式...");
        log.debug("用户名正则表达式：{}", RegExpressions.REGEXP_USERNAME);
        log.debug("错误提示：{}", RegExpressions.MESSAGE_USERNAME);
        String regExp = RegExpressions.REGEXP_USERNAME;
        String value;
        boolean result;
        /**
         * 1. 正确：长度4-16个字符，由字母和数字组成，且首位为字母
         * 2. 不正确，长度大于16
         * 3. 不正确，长度小于4
         * 4. 不正确，由字母和数字以外的组成
         * 5. 不正确，首位为数字
         */
        value = "user001";
        log.debug("用户名正确为：{}时", value);
        result = value.matches(regExp);
        assertTrue(result);

        value = "user001111111111111111111111111";
        log.debug("用户名错误，长度大于16为：{}时", value);
        result = value.matches(regExp);
        assertFalse(result);

        value = "use";
        log.debug("用户名错误，长度小于4为：{}时", value);
        result = value.matches(regExp);
        assertFalse(result);

        value = "use0**";
        log.debug("用户名错误，由字母和数字以外的组成为：{}时", value);
        result = value.matches(regExp);
        assertFalse(result);

        value = "2use0";
        log.debug("用户名错误，首位为数字为：{}时", value);
        result = value.matches(regExp);
        assertFalse(result);
        log.debug("测试用户名的正则表达式完成!");
    }

    @Test
    void testPasswordRegExpression() {
        log.debug("测试密码的正则表达式...");
        log.debug("密码正则表达式：{}", RegExpressions.REGEXP_PASSWORD);
        log.debug("错误提示：{}", RegExpressions.MESSAGE_PASSWORD);
        String regExp = RegExpressions.REGEXP_PASSWORD;
        String value;
        boolean result;
        /**
         * 1. 正确：密码长度为4-16个字符
         * 2. 不正确，长度大于16
         * 3. 不正确，长度小于4
         */
        value = "12345";
        log.debug("密码正确为：{}时", value);
        result = value.matches(regExp);
        assertTrue(result);

        value = "001111111111111111111111111";
        log.debug("密码错误，长度大于16为：{}时", value);
        result = value.matches(regExp);
        assertFalse(result);

        value = "000";
        log.debug("密码错误，长度小于4为：{}时", value);
        result = value.matches(regExp);
        assertFalse(result);
        log.debug("测试密码的正则表达式完成!");
    }

    @Test
    void testPhoneRegExpression() {
        log.debug("测试手机号码的正则表达式...");
        log.debug("手机号码正则表达式：{}", RegExpressions.REGEXP_PHONE);
        log.debug("错误提示：{}", RegExpressions.MESSAGE_PHONE);
        String regExp = RegExpressions.REGEXP_PHONE;
        String value;
        boolean result;
        /**
         * 1. 正确：手机号码长度为11个字符
         * 2. 不正确，长度大于11
         * 3. 不正确，长度小于11
         * 4. 不正确，由数字以外的组成
         * 5. 不正确，手机号码非法
         */
        value = "13456789111";
        log.debug("手机号码正确为：{}时", value);
        result = value.matches(regExp);
        assertTrue(result);

        value = "134567891111";
        log.debug("手机号码错误，长度大于11为：{}时", value);
        result = value.matches(regExp);
        assertFalse(result);

        value = "134567891";
        log.debug("手机号码错误，长度小于11为：{}时", value);
        result = value.matches(regExp);
        assertFalse(result);

        value = "13467891*77";
        log.debug("手机号码错误，由数字以外的组成为：{}时", value);
        result = value.matches(regExp);
        assertFalse(result);

        value = "12245678888";
        log.debug("手机号码错误，手机号码非法为：{}时", value);
        result = value.matches(regExp);
        assertFalse(result);
        log.debug("测试手机号码的正则表达式完成!");
    }

}
