package com.ruoyi.device.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * 管理系统密码加密工具类（基于BCrypt算法）
 */
public class PasswordEncryptUtil {

    /**
     * 对原始密码进行BCrypt哈希加密（自动生成随机盐值）
     * @param plainPassword 原始明文密码
     * @return 加密后的哈希字符串（包含盐值，可直接存入数据库）
     */
    public static String encryptPassword(String plainPassword) {
        // 校验入参，避免空指针
        if (plainPassword == null || plainPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("密码不能为空");
        }
        // gensalt()：生成随机盐值，参数10是计算强度（4-31之间，值越大越安全但耗时越长）
        String salt = BCrypt.gensalt(10);
        // 结合盐值加密密码
        return BCrypt.hashpw(plainPassword, salt);
    }

    /**
     * 验证输入的密码是否与数据库中存储的哈希密码匹配
     * @param plainPassword 登录时输入的明文密码
     * @param hashedPassword 数据库中存储的加密哈希密码
     * @return 匹配返回true，不匹配返回false
     */
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        // 入参校验
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }
        try {
            // BCrypt会自动从hashedPassword中提取盐值，与输入密码重新哈希后比对
            return BCrypt.checkpw(plainPassword, hashedPassword);
        } catch (Exception e) {
            // 哈希值格式错误等异常时，直接返回验证失败
            return false;
        }
    }

    // 测试示例
    public static void main(String[] args) {
        // 1. 模拟用户注册，加密密码
//        String originalPwd = "Admin@123456"; // 用户输入的原始密码
//        String hashedPwd = encryptPassword(originalPwd);
//        System.out.println("加密后的密码：" + hashedPwd);

        // 2. 模拟用户登录，验证密码
        String inputPwdCorrect = "123456"; // 正确密码


        boolean isCorrect = verifyPassword(inputPwdCorrect, "$2a$10$FojtKJ06hiG4ZtwlMzTqDevx69Symq473moY64f8MxPIOBLUegK6u");


        System.out.println(isCorrect); // 输出true

    }
}